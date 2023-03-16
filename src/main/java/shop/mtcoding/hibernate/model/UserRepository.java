package shop.mtcoding.hibernate.model;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class UserRepository {
    private final EntityManager em;

    public User save(User user) {
        // persist = 물리적으로 기록(commit과 같은 맥락)
        em.persist(user);
        return user;
    }

    // dto로 만들어서 원래는 노출
    public User update(User user) {
        return em.merge(user);
    }

    public void delete(User user) {
        em.remove(user);
    }

    public User findById(Long id) {
        return em.find(User.class, id);
    }

    // native query 시
    // public List<User> findAll(int page) {
    // return em.createNamedQuery("select * from User limit 0,2", User.class)
    // .setFirstResult(page*2) // 한페이지에 나올 low page
    // .setMaxResults(2)
    // .getResultList();
    // }
    public List<User> findAll(int page, int row) {
        // select u from User u where u.id = 1 // 클래스(파스칼표기)
        return em.createQuery("select u from User u", User.class)
                .setFirstResult(page * row) // 한페이지에 나올 low page
                .setMaxResults(row)
                .getResultList();
    }

    public List<User> findAll() {
        return em.createQuery("select u from User u", User.class)
                .getResultList();
    }
}
