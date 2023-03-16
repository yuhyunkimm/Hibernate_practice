package shop.mtcoding.hibernate.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

// dto는 임시저장소
// 여긴 다른세상에 있는 것을 그대로 넣어 줘야한다 = 옮겨 담는 용도
// getter만 만들어준다
@NoArgsConstructor
@Getter
@Table(name = "user_tb")
@Entity
public class User {
    // import : javax 또는 자카르타
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 전략 : 정책성에 맞게 사용하는 것
    private Long id;
    private String username;
    private String password;
    private String email;
    private Timestamp crearedAt;

    @Builder // 선택적 매개변수의 역할 , 원하는 것을 사용
    public User(Long id, String username, String password, String email, Timestamp crearedAt) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.crearedAt = crearedAt;
    }

    // 이것을 setter 의미 있는 것을 지정
    public void update(String password, String email) {
        this.password = password;
        this.email = email;
    }

    // 이렇게 의미있게 지정
    // public void passwordUpdate(String password) {

    // }
}
