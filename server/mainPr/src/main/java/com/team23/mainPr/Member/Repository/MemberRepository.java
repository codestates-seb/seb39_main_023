package com.team23.mainPr.Member.Repository;

import com.team23.mainPr.Member.Entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Integer> {
    Member findByLoginId(String loginId);

}
