package edu.book.roadbookspringbootjpa.entity;

import edu.book.roadbookspringbootjpa.dto.MemberFormDto;
import edu.book.roadbookspringbootjpa.repository.CartRepository;
import edu.book.roadbookspringbootjpa.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application-test.properties")
class CartTest {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PersistenceContext
    private EntityManager entityManager;

    public Member createMember() {
        MemberFormDto memberFormDto = new MemberFormDto();
        memberFormDto.setEmail("test@email.com");
        memberFormDto.setName("홍길동");
        memberFormDto.setAddress("서울시 마포구 합정동");
        memberFormDto.setPassword("1234");
        return Member.createMember(memberFormDto, passwordEncoder);
    }

    @DisplayName("장바구니 회원 엔티티 매핑 조회 테스트")
    @Test
    public void findCartAndMemberTest() {
        Member member = createMember();
        memberRepository.save(member);

        Cart cart = new Cart();
        cart.setMember(member);
        cartRepository.save(cart);

        entityManager.flush(); // 영속성 컨텍스트에 데이터를 저장 후 트랜잭션이 끝날 때 flush()를 호출하여 데이터베이스에 반영한다
        entityManager.clear(); // 영속성 컨텍스트에 엔티티를 조회 후 없으면 데이터베이스에 조회하기 때문에 즉시 로딩 확인을 위해서 컨텍스트를 비운다

        Cart saved = cartRepository.findById(cart.getId())
                                   .orElseThrow(EntityNotFoundException::new);

        assertEquals(saved.getMember().getId(), member.getId());
    }
}
