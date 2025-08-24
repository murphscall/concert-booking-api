package io.github.murphscall.concertbooking.user.domain;

import io.github.murphscall.concertbooking.global.entity.BaseEntity;
import jakarta.persistence.*;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Table(name = "users")
@Entity
public class User extends BaseEntity {

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[a-z0-9._-]+@[a-z]+[.]+[a-z]{2,3}$");
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("(?=.*[A-Za-z])(?=.*\\d)(?=.*[!@#$%^&*]).{8,}");
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "email" , nullable = false , unique = true)
    private String email;

    @Column(name = "password" , nullable = false)
    private String password;

    @Column(name = "nickname" , nullable = false)
    private String nickname;


    protected User() {}

    public User(final String email, final String password, final String nickname) {

        validateEmail(email);

        this.email = email;
        this.password = password;
        this.nickname = nickname;
    }

    private void validateEmail(final String email) {
        Matcher matcher = EMAIL_PATTERN.matcher(email);
        if(!matcher.matches()) {
            throw new IllegalArgumentException("Invalid email");
            // 추후 커스텀 익셉션 으로 변경
        }
    }

    private void validatePassword(final String password) {
        Matcher matcher = PASSWORD_PATTERN.matcher(password);
        
        if(!matcher.matches()) {
            throw new IllegalArgumentException("Invalid password");
            // 추후 커스텀 익셉션으로 변경
        }
    }

    public boolean hasSameId(final Long userId){
        return Objects.equals(id, userId);
    }
}
