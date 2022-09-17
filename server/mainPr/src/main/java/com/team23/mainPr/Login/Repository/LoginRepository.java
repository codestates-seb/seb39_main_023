package com.team23.mainPr.Login.Repository;

import com.team23.mainPr.Login.Entity.Login;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginRepository extends JpaRepository<Login, Integer> {
    Login findByToken(String authorization);

    Login findByLoginId(String loginId);

    Login findByMemberId(Integer memberId);
}
