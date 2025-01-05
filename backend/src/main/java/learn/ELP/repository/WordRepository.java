package learn.ELP.repository;

import learn.ELP.entity.Word;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WordRepository extends JpaRepository<Word, Long> {
    List<Word> findWordByUser_Username(String username);
    @Query("SELECT w from Word  w where w.user.username = :username order by rand() limit 10")
    List<Word> findRandomWord(@Param("username") String username);
}
