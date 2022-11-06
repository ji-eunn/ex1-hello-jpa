package hellojpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
// 만약, 테이블 이름이 DB 와 다를 경우
// @Table(name = "USER")
// 와 같이 써주면(맵핑해주면) JPA 가 알아서 USER 테이블에 데이터를 넣어준다.
public class Member {

    @Id
    private Long id;

    // 만약, 컬럼의 이름이 DB 와 다를 경우
    // @Column(name = "username")
    // 와 같이 써주면(맵핑해주면) JPA 가 알아서 username 이라는 컬럼에 데이터를 넣어준다.
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
