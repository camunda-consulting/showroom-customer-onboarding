package com.camunda.demo.customeronboarding.facade;

import java.time.ZoneId;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.camunda.demo.customeronboarding.ProcessConstants;
import com.camunda.demo.customeronboarding.model.NewApplication;

@RestController
@RequestMapping("creditagency")
public class CreditAgencySimulator {

    @PostMapping(path="/score")
    public Map<String, Integer> creditScore(@RequestBody NewApplication application) {
        int yearLastDigit = application.getApplicant().getBirthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getYear() % 10;

        int score = 97;

        if(yearLastDigit == 3) {
            score = 93;
        } else if (yearLastDigit == 5) {
            score = 82;
        } 
        
        return Map.of("score", score);
    }
    
}
