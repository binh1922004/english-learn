package learn.ELP.dto.respone;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthenticationRespone {
    String token;
    String username;
}
