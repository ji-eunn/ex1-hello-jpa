package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        /*
        Member member = new Member();
        // 쿼리를 작성하지 않았지만 JPA 가 매핑정보를 보고 넣어준 것.
        member.setId(2L);
        member.setName("HelloB");

        em.persist(member);

        tx.commit();

        em.close(); // 사용을 다 하고나면 EntityManager 를 닫아주는 것이 굉장히 중요! 왜? EntityManager 가 내부적으로 데이터베이스 커넥션을 물고 동작하기 때문.

        emf.close();
         */

        // 하지만 위 코드는 좋은 코드가 아니다. 왜? tx.commit(); 에서 에러가 발생하면 아래 코드는 실행이 되지 않기 때문에
        // 따라서, 아래와 같이 작성하는 것이 좋다

        try {
            //Member member = new Member();
            // 저장
//            member.setId(2L);
//            member.setName("HelloB");
//            em.persist(member);

            // 조회(찾기)
           //member = em.find(Member.class, 1L);
//            System.out.println("findMember.id = " + member.getId());
//            System.out.println("findMember.name = " + member.getName());

            // 조건이 있는 조회가 필요한 경우 JPQL 사용
            // JPQL 은 일반 쿼리와 다르게 테이블이 아니라 객체를 대상으로 한다.
            List<Member> result = em.createQuery("select m from Member as m", Member.class)
                    .setFirstResult(1)
                    .setMaxResults(10) // 1번부터 10개 가져와
                    .getResultList();

            for (Member member : result) {
                System.out.println("member.name = " + member.getName());
            }

            // 삭제
            //em.remove(member); // 찾은 애 넣어주기

            // 수정
            //member.setName("HelloJPA"); // 찾은 애 한테 수정할 값 set 해주기
                                        // 따로 저장할 필요 없음. 왜? 자바 컬렉션을 다루는 것 처럼 다루도록 설계되었기 때문

            tx.commit();
        } catch (Exception e) {
            tx.rollback(); // JPA 의 모든 변경은 트랜잭션 안에서 실행해야 한다.
        } finally {
            em.close(); // 사용을 다 하고나면 EntityManager 를 닫아주는 것이 굉장히 중요! 왜? EntityManager 가 내부적으로 데이터베이스 커넥션을 물고 동작하기 때문.
                        // 따라서, 엔티티매니저는 쓰레드 간 공유 X(사용하고 버려야 한다)
        }
        emf.close(); // EntityManagerFactory 는 하나만 생성해서 애플리케이션 전체에서 공유한다.

    }
}
