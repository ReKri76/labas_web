package io.rekri.lab5.entities;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
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
    @Nullable
    private String head;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "picture")
    @Nullable
    private String picture;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="author")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Users author;

    @Column(name = "createdAt", nullable = false)
    private final String date = Instant.now().toString();

    public static class Builder{
        private String head;
        private String content;
        private Users author;
        private String picture;

        public Builder head(final String head){
            this.head = head;
            return this;
        }

        public Builder content(final String content){
            this.content = content;
            return this;
        }

        public Builder author(final Users author){
            this.author = author;
            return this;
        }

        public Builder picture(final String picture){
            this.picture = picture;
            return this;
        }

        public Notices build(){
            final var notices = new Notices();
            notices.head = this.head;
            notices.author = this.author;
            notices.content = this.content;
            notices.picture = this.picture;
            return notices;
        }
    }
}
