package com.spring.junit.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@NoArgsConstructor
@Entity
public class Member {

    @Id
    @GeneratedValue
    private Long id;

    private String email;

    @Builder
    public Member(Long id, String email) {
        this.id = id;
        this.email = email;
    }
}