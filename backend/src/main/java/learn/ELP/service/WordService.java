package learn.ELP.service;

import learn.ELP.dto.request.WordAddRequest;
import learn.ELP.dto.respone.WordAddRespone;
import learn.ELP.entity.Users;
import learn.ELP.entity.Word;
import learn.ELP.mapper.WordMapper;
import learn.ELP.repository.WordRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class WordService {
    WordRepository wordRepository;
    UserService userService;
    WordMapper wordMapper;

    public WordAddRespone addWord(WordAddRequest wordAddRequest) {
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        Users user = userService.findByUsername(username);
        Word word = wordMapper.wordToWord(wordAddRequest);
        word.setUser(user);
        return wordMapper.wordToWordAddRespone(wordRepository.save(word));
    }
}
