package io.rekri.lab5.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "Users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "role", nullable = false)
    private String role;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "password", nullable = false)
    private String password;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "author", orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Notices> notices;

    public static class Builder {
        private String role;
        private String name;
        private String password;

        public Builder role(final String role) {
            this.role = role;
            return this;
        }

        public Builder name(final String name) {
            this.name = name;
            return this;
        }

        public Builder password(final String password) {
            this.password = password;
            return this;
        }

        public Users build() {
            final var users = new Users();
            users.role = this.role;
            users.name = this.name;
            users.password = this.password;
            return users;
        }
    }
}