package learn.ELP.entity;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Word {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    String word;
    String definition;
    String example;

    @ManyToOne
    @JoinColumn(name = "user_id")
    Users user;
}
