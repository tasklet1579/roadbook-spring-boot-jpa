package edu.book.roadbookspringbootjpa.entity;

import edu.book.roadbookspringbootjpa.constant.Role;
import edu.book.roadbookspringbootjpa.dto.MemberFormDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Entity
@Table(name = "member")
@Getter
@Setter
@ToString
public class Member extends BaseEntity {

    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    private String password;

    private String address;

    @Enumerated(EnumType.STRING)
    private Role role;

    public static Member createMember(MemberFormDto memberFormDto, PasswordEncoder passwordEncoder) {
        Member member = new Member();
        member.setName(memberFormDto.getName());
        member.setEmail(memberFormDto.getEmail());
        member.setAddress(memberFormDto.getEmail());
        String password = passwordEncoder.encode((memberFormDto.getPassword()));
        member.setPassword(password);
        member.setRole(Role.USER);
        return member;
    }

    public static Member createAdmin(MemberFormDto memberFormDto, PasswordEncoder passwordEncoder) {
        Member member = new Member();
        member.setName(memberFormDto.getName());
        member.setEmail(memberFormDto.getEmail());
        member.setAddress(memberFormDto.getEmail());
        String password = passwordEncoder.encode((memberFormDto.getPassword()));
        member.setPassword(password);
        member.setRole(Role.ADMIN);
        return member;
    }
}
