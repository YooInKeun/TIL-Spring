package com.spring.auth.springauth.member.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spring.auth.springauth.member.domain.Member;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.Objects;

@Slf4j
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(of = {"id"})
public class LoginMember implements Serializable {
    private static final long serialVersionUID = 123456123456L;

    @Getter private Long id;

    @JsonIgnore
    public boolean is(Member member) {
        return member != null && Objects.equals(member.getId(), this.id);
    }
}
