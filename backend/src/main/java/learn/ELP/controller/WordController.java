package learn.ELP.controller;

import learn.ELP.dto.ApiRespone;
import learn.ELP.dto.request.WordAddRequest;
import learn.ELP.dto.respone.WordAddRespone;
import learn.ELP.entity.Word;
import learn.ELP.service.WordService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/word")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class WordController {
    WordService wordService;

    @PostMapping("/add")
    public ApiRespone<WordAddRespone> addWord(@RequestBody WordAddRequest wordAddRequest) {
        return ApiRespone.<WordAddRespone>builder()
                .result(wordService.addWord(wordAddRequest))
                .build();
    }
}
