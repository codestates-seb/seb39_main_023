package com.team23.mainPr.Member.Repository;

import com.team23.mainPr.Member.Entity.Member;
import org.springframework.data.repository.CrudRepository;

public interface MemberRepository extends CrudRepository<Member, String> {
}

