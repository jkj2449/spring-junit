package com.spring.junit.member;

import com.spring.junit.domain.Member;

import java.util.Optional;

public interface MemberService {
    void validate() throws InvalidMemberException;

    Optional<Member> findById(Long memberId) throws MemberNotFoundException;

}
