package com.team23.mainPr.Domain.RentPost.Repository;

import com.team23.mainPr.Domain.RentPost.Entity.RentPost;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RentPostRepository extends JpaRepository<RentPost, Integer> {
    Page<RentPost> findAllByRentStatusFalse(Pageable pageable);

    Page<RentPost> findAllByRentStatusTrue(Pageable pageable);

    Page<RentPost> findAllByCategory(Pageable pageable, String category);

    Page<RentPost> findAllByRentStatusAndCategory(Pageable pageable, Boolean rentstatus, String category);

    Page<RentPost> findAllByRentStatusAndCategoryContaining(Pageable pageable, Boolean rentStatus, String category);

    @Query(value = "SELECT p.RENT_POST_ID\n" +
            "FROM RENT_POST as p\n" +
            "WHERE p.rent_post_name REGEXP ?1 ;", nativeQuery = true)
//native query only use order parameter
    List<Object> search(String phrase);
}
