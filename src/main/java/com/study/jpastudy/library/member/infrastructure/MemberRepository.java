package com.study.jpastudy.library.member.infrastructure;

import com.study.jpastudy.library.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
