package boardassignment.goorm.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@Table(name = "goormBoard")
public class Board {

    private Long id;

    private String title;

    private String content;

    
}
