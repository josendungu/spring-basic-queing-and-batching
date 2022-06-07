package com.tl.pr.service;

import com.tl.pr.PerformanceReview;
import com.tl.pr.dto.EmployeeDto;
import com.tl.pr.model.Review;
import com.tl.pr.repository.PRRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class PRServiceImpl implements PRService{

    private final PRRepository prRepository;

    public PRServiceImpl(PRRepository prRepository) {
        this.prRepository = prRepository;
    }

    @Override
    public Boolean placeEmployeeOnReview(EmployeeDto employeeDto) {
        Review review = Review.builder().employeeId(employeeDto.getEmployeeId()).paramOne(employeeDto.getParamOne()).paramTwo(employeeDto.getParamTwo()).placedDate(LocalDate.now()).build();
        prRepository.save(review);
        return true;
    }


}
