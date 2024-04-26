package dev.mayank.thymeleaf.demo.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CourseCodeConstraintValidator implements ConstraintValidator<CourseCode, String> {

    private String coursePrefix;

    /**
     * @param courseCode annotation instance for a given constraint declaration
     */
    @Override
    public void initialize(CourseCode courseCode) {
        this.coursePrefix = courseCode.value();
    }

    /**
     * @param theCourseCode object to validate
     * @param context       context in which the constraint is evaluated
     * @return true if code starts with coursePrefix otherwise false
     */
    @Override
    public boolean isValid(String theCourseCode, ConstraintValidatorContext context) {
        return (theCourseCode != null) ? theCourseCode.startsWith(coursePrefix) : true;
    }
}