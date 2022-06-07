package com.tl.pr.controller;

import com.tl.pr.service.WarningServiceImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/warning")
public class WarningController {

    private final WarningServiceImpl warningService;

    public WarningController(WarningServiceImpl warningService) {
        this.warningService = warningService;
    }


    @PostMapping("/add")
    public Boolean addWarningCount(Long employeeId){
        return warningService.addWarningCount(employeeId);
    }
}
