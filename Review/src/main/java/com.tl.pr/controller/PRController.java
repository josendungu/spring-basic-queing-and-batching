package com.tl.pr.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/pr")
public class PRController {



    @PostMapping("/{employeeId}")
    public void placeEmployeeOnPR(@PathVariable(name = "employeeId") Long employee_id){

    }


}
