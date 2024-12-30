package learn.ELP.controller;

import learn.ELP.dto.ApiRespone;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class MessageController {
    @GetMapping("/message")
    public ApiRespone<String> message() {
        return ApiRespone.<String>builder()
                .result("Hello World!")
                .build();
    }
}
