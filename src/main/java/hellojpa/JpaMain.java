package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

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
            Member member = new Member();
            member.setId(2L);
            member.setName("HelloB");

            em.persist(member);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close(); // 사용을 다 하고나면 EntityManager 를 닫아주는 것이 굉장히 중요! 왜? EntityManager 가 내부적으로 데이터베이스 커넥션을 물고 동작하기 때문.
        }
        emf.close();

    }
}
