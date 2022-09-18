package com.team23.mainPr.Domain.Login.Repository;

import com.team23.mainPr.Domain.Login.Entity.Login;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginRepository extends JpaRepository<Login, Integer> {
    Login findByToken(String token);

    Login findByLoginId(String loginId);

    Login findByMemberId(Integer memberId);
}
