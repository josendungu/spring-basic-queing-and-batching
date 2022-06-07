package com.tl.pr.service;

import com.tl.pr.model.EmployeeWarning;
import com.tl.pr.repository.WarningRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class WarningServiceImpl implements WarningService{

    private final WarningRepository warningRepository;
    private final PRServiceImpl prService;

    public WarningServiceImpl(WarningRepository warningRepository, PRServiceImpl prService) {
        this.warningRepository = warningRepository;
        this.prService = prService;
    }

    @Override
    public Boolean addWarningCount(Long employeeID) {

        Optional<EmployeeWarning> employeeWarningOptional = warningRepository.fetchWarningByEmployeeId(employeeID);

        if (employeeWarningOptional.isPresent()){

            EmployeeWarning employeeWarning = employeeWarningOptional.get();

            int warningCount = employeeWarning.getWarningCount();

            if (warningCount == 2){
//                return prService.placeEmployeeOnReview(employeeID);
                return false;
            } else {
                employeeWarning.setWarningCount(warningCount+1);
                warningRepository.save(employeeWarning);
                return true;
            }
        } else {
            EmployeeWarning employeeWarning = EmployeeWarning.builder().employeeId(employeeID).warningCount(1).build();
            warningRepository.save(employeeWarning);
            return true;
        }





    }
}
