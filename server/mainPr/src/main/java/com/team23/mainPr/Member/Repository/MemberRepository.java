package com.team23.mainPr.Member.Repository;

import com.team23.mainPr.Member.Entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface MemberRepository extends JpaRepository<Member, Integer> {
}

