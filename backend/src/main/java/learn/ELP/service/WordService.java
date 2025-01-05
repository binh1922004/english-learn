package learn.ELP.service;

import learn.ELP.dto.request.WordAddRequest;
import learn.ELP.dto.respone.WordRespone;
import learn.ELP.entity.Users;
import learn.ELP.entity.Word;
import learn.ELP.mapper.WordMapper;
import learn.ELP.repository.WordRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class WordService {
    WordRepository wordRepository;
    UserService userService;
    WordMapper wordMapper;

    public WordRespone addWord(WordAddRequest wordAddRequest) {
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        Users user = userService.findByUsername(username);
        Word word = wordMapper.wordToWord(wordAddRequest);
        word.setUser(user);
        return wordMapper.wordToWordRespone(wordRepository.save(word));
    }

    public List<WordRespone> getAllWords() {
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        List<Word> listWord = wordRepository.findWordByUser_Username(username);

        return listWord.stream().map(wordMapper::wordToWordRespone).collect(Collectors.toList());
    }

    public List<WordRespone> getRandomWords(){
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        List<Word> listWord = wordRepository.findRandomWord(username);

        return listWord.stream().map(wordMapper::wordToWordRespone).collect(Collectors.toList());
    }
}
