package learn.ELP.controller;

import learn.ELP.dto.ApiRespone;
import learn.ELP.dto.request.WordAddRequest;
import learn.ELP.dto.respone.WordRespone;
import learn.ELP.service.WordService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/word")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class WordController {
    WordService wordService;

    @PostMapping("/add")
    public ApiRespone<WordRespone> addWord(@RequestBody WordAddRequest wordAddRequest) {
        return ApiRespone.<WordRespone>builder()
                .result(wordService.addWord(wordAddRequest))
                .build();
    }

    @GetMapping()
    public ApiRespone<List<WordRespone>> getAllWords() {
        return ApiRespone.<List<WordRespone>>builder()
                .result(wordService.getAllWords())
                .build();
    }

    @GetMapping("/random")
    public ApiRespone<List<WordRespone>> getRandomWords() {
        return ApiRespone.<List<WordRespone>>builder()
                .result(wordService.getRandomWords())
                .build();
    }
}
