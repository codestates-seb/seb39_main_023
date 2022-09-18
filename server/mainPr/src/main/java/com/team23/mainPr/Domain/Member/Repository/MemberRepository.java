package com.team23.mainPr.domain.Member.Repository;

import com.team23.mainPr.domain.Member.Entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Integer> {
    Member findByLoginId(String loginId);

    Optional<Member> findByEmail(String email);
}
