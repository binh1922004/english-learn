package learn.ELP.service;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import learn.ELP.dto.ApiRespone;
import learn.ELP.dto.request.AuthenticationRequest;
import learn.ELP.dto.respone.AuthenticationRespone;
import learn.ELP.entity.Users;
import learn.ELP.repository.UsersRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationService {
    PasswordEncoder passwordEncoder;
    UsersRepository usersRepository;
    @NonFinal
    @Value("${jwt.signerKey}")
    protected String SIGNER_KEY;

    public AuthenticationRespone authenticate(AuthenticationRequest request) throws JOSEException {
         Users user = usersRepository.findByUsername(request.getUsername())
                 .orElseThrow(() -> new UsernameNotFoundException("Can not found: " + request.getUsername()));
         boolean authenticated = passwordEncoder.matches(request.getPassword(), user.getPassword());

         if (!authenticated) {
             throw new RuntimeException("Invalid password");
         }

         var token = generateToken(user);

         return AuthenticationRespone.builder()
                 .token(token)
                 .username(user.getUsername())
                 .build();
    }

    private String generateToken(Users user) throws JOSEException {
        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS512);

        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(user.getUsername())
                .issuer("ball") //issure: JWT from ?
                .issueTime(new Date())
                .expirationTime(new Date
                        (Instant.now().plus(1, ChronoUnit.HOURS ).toEpochMilli()))
                .build();

        Payload payload = new Payload(jwtClaimsSet.toJSONObject());

        JWSObject jwsObject = new JWSObject(jwsHeader, payload);

        try{
            jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));
            return jwsObject.serialize();
        }
        catch (JOSEException e){
            throw new RuntimeException(e);
        }
    }
}
