package com.jiubuntu.settlement.common.security;

import com.jiubuntu.settlement.common.exception.CommonCode;
import com.jiubuntu.settlement.common.exception.CommonException;
import com.jiubuntu.settlement.member.domain.Member;
import com.jiubuntu.settlement.member.infrastructure.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new CommonException(CommonCode.MEMBER_NOT_FOUND));
        return new CustomUserDetails(member);
    }
}
