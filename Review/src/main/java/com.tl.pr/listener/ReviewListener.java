package com.tl.pr.listener;

import com.tl.pr.constants.Constants;
import com.tl.pr.dto.EmployeeDto;
import com.tl.pr.service.PRServiceImpl;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class ReviewListener {

    private final PRServiceImpl prService;

    public ReviewListener(PRServiceImpl prService) {
        this.prService = prService;
    }

    @RabbitListener(queues = Constants.QUEUE)
    public void listener(EmployeeDto employeeDto){
        System.out.println(employeeDto);
        prService.placeEmployeeOnReview(employeeDto);
    }
}
