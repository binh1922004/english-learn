package learn.ELP.mapper;

import learn.ELP.dto.request.WordAddRequest;
import learn.ELP.dto.respone.WordRespone;
import learn.ELP.entity.Word;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WordMapper {
    Word wordToWord(WordAddRequest wordAddRequest);
    WordRespone wordToWordRespone(Word word);
}
