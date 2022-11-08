package edu.book.roadbookspringbootjpa.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "cart")
@Getter
@Setter
@ToString
public class Cart {
    @Id
    @Column(name = "cart_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER) // 즉시 로딩이 기본 Fetch 전략으로 설정되어 있음
    @JoinColumn(name = "member_id")
    private Member member;
}
