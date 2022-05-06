package edu.book.roadbookspringbootjpa.dto;

import lombok.*;

// 실제로 컴파일할 때 메서드가 생성되었는지 확인해보자
// target/classes/edu.book.roadbookspringbootjpa.dto/UserDto.class
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    @NonNull
    private String name;
    private Integer age;
}
