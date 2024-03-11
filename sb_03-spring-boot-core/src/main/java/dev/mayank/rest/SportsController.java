package dev.mayank.rest;

/**
 * Constructor Injection: preferred for required dependencies
 * Setter Injection: preferred for optional dependencies
 * Field Injection: not preferred, makes the code harder for unit tests (maybe used in legacy projects)
 */

import dev.mayank.util.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class SportsController {
    //    @Autowired // field injection
    private Coach myCoach;
    private Coach anotherCoach;

    @Autowired  // for constructor injection
    public SportsController(@Qualifier("cricketCoach") Coach myCoach, @Qualifier("swimCoach") Coach anotherCoach) {
        // if both @qualifier and @Primary are being used for a field then @Qualifier will have a higher priority
        System.out.println("Constructor initiated of " + getClass().getSimpleName());
        this.myCoach = myCoach;
        this.anotherCoach = anotherCoach;
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
        // return "Workout1: " + myCoach.getDailyWorkout() + "\n" + "Workout2: " + anotherCoach.getDailyWorkout();
        // return "Workout1: " + myCoach.getDailyWorkout() + "System.lineSeparator()" + "Workout2: " + anotherCoach.getDailyWorkout();
        return "Workout1: " + myCoach.getDailyWorkout() + "<br>" + "Workout2: " + anotherCoach.getDailyWorkout();
    }

    @GetMapping("/checkKar")
    private String isEqual() {
        return "Comparing beans: myCoach == anotherCoach, " + (myCoach == anotherCoach);
    }
}