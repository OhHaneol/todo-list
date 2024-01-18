package todolist.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class TodoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

//    public TodoEntity(String title) {
//        this.title = title;
//    }
//
//    public TodoEntity update(String title) {
//        this.title = title;
//        return this;
//    }

}
