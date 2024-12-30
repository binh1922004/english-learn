package learn.ELP.dto.respone;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
// if field null it will not display
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiRespone<T>{
        @Builder.Default
        int code = 1000;
        String message;
        T result;
}