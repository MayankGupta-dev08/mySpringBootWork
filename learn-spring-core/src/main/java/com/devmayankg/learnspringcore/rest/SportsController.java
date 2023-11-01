package com.devmayankg.learnspringcore.rest;

/**
* Constructor Injection: preferred for required dependencies
* Setter Injection: preferred for optional dependencies
* Field Injection: not preferred, makes the code harder for unit tests (maybe used in legacy projects)
*/

import com.devmayankg.learnspringcore.util.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SportsController {

//    @Autowired    // field injection
    private Coach myCoach;

    @Autowired  // for constructor injection
    public SportsController(@Qualifier("cricketCoach") Coach myCoach) {
        // if both @qualifier and @Primary are being used for a field then @Qualifier will have a higher priority
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