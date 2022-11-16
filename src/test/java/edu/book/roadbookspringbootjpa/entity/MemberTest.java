package edu.book.roadbookspringbootjpa.entity;

import edu.book.roadbookspringbootjpa.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;

@Transactional
@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class MemberTest {
    @Autowired
    private MemberRepository memberRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @DisplayName("Auditing 테스트")
    @Test
    @WithMockUser(username = "gildong", roles = "USER")
    public void auditingTest() {
        Member member = new Member();
        memberRepository.save(member);

        entityManager.flush();
        entityManager.clear();

        Member saved = memberRepository.findById(member.getId())
                                       .orElseThrow(EntityNotFoundException::new);

        System.out.println("register time: " + saved.getRegTime());
        System.out.println("update time: " + saved.getUpdateTime());
        System.out.println("create member: " + saved.getCreatedBy());
        System.out.println("modify member: " + saved.getModifiedBy());
    }
}
