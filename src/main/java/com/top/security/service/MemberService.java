package com.top.security.service;

import com.top.security.domain.Member;

public interface MemberService {
    Long getMemberId(String email, String password);

    Member getMember(Long memberId);
}
