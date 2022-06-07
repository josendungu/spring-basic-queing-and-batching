package com.tl.pr.repository;

import com.tl.pr.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PRRepository extends JpaRepository<Review, Long> {
}
