package com.devmayankg.learnspringcore.rest;

import com.devmayankg.learnspringcore.util.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SportsController {

    private Coach myCoach;

    @Autowired  // for constructor injection
    public SportsController(Coach myCoach) {
        this.myCoach = myCoach;
    }

    //    @Autowired //for setter injection
    public void setMyCoach(Coach myCoach) {
        this.myCoach = myCoach;
    }

    @GetMapping("/")
    private String getContentForHomePage() {
        return "HOME PAGE!!";
    }

    @GetMapping("/getWorkoutPlan")
    private String getContentForWorkoutPage() {
        return myCoach.getDailyWorkout();
    }
}