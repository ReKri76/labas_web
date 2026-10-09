package io.rekri.lab5.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.Instant;

@Entity
@Getter
@Setter
@Table(name = "Notices")
public class Notices {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "head")
    private String head;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "picture")
    private String picture;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="author")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Integer author;

    @Column(name = "createdAt", nullable = false)
    private String date = Instant.now().toString();
}
