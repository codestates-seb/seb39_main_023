package com.team23.mainPr.Domain.RentHistory.Repository;

import com.team23.mainPr.Domain.RentHistory.Entity.RentHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RentHistoryRepository extends JpaRepository<RentHistory, Integer> {
    List<RentHistory> findAllByTargetMemberId(Integer memberId);
}
