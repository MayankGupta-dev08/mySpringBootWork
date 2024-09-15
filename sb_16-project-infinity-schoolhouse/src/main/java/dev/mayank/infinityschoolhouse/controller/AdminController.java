package dev.mayank.infinityschoolhouse.controller;

import dev.mayank.infinityschoolhouse.model.ContactDetail;
import dev.mayank.infinityschoolhouse.model.Course;
import dev.mayank.infinityschoolhouse.model.ISHClass;
import dev.mayank.infinityschoolhouse.model.Person;
import dev.mayank.infinityschoolhouse.repository.CourseRepository;
import dev.mayank.infinityschoolhouse.repository.ISHClassRepository;
import dev.mayank.infinityschoolhouse.repository.PersonRepository;
import dev.mayank.infinityschoolhouse.service.ContactDetailService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("admin")
@SuppressWarnings("unused")
public class AdminController {
    private ContactDetailService contactDetailService;
    private PersonRepository personRepository;
    private ISHClassRepository ishClassRepository;
    private CourseRepository courseRepository;

    @Autowired
    public AdminController(ContactDetailService contactDetailService, PersonRepository personRepository,
                           ISHClassRepository ishClassRepository, CourseRepository courseRepository) {
        this.contactDetailService = contactDetailService;
        this.personRepository = personRepository;
        this.ishClassRepository = ishClassRepository;
        this.courseRepository = courseRepository;
    }

    @RequestMapping(value = {"/displayMessages"})
    public ModelAndView displayOpenMessages() {
        ModelAndView modelAndView = new ModelAndView();
        List<ContactDetail> messagesWithOpenStatus = contactDetailService.getAllMessagesWithOpenStatus();
        modelAndView.addObject("openMessages", messagesWithOpenStatus);
        modelAndView.setViewName("messages.html");
        return modelAndView;
    }

    @GetMapping(value = {"/closeMsg"})
    public String closeMessage(@RequestParam("id") int id) {
        boolean isUpdated = contactDetailService.updateMessageStatus(id);
        if (!isUpdated) log.error("Failed to close message with id: {}", id);
        return "redirect:/admin/displayMessages";
    }

    @RequestMapping(value = {"/displayClasses"})
    public ModelAndView displayAllClasses() {
        List<ISHClass> iSHClasses = ishClassRepository.findAll();
        ModelAndView classesMV = new ModelAndView("classes.html");
        classesMV.addObject("iSHClass", new ISHClass());
        classesMV.addObject("iSHClasses", iSHClasses);
        return classesMV;
    }

    @PostMapping(value = {"/addNewClass"})
    public ModelAndView addNewClass(Model model, @Valid @ModelAttribute("iSHClass") ISHClass ishClass) {
        ishClassRepository.save(ishClass);
        ModelAndView displayClassesMV = new ModelAndView("redirect:/admin/displayClasses");
        return displayClassesMV;
    }

    @RequestMapping(value = {"/deleteClass"})
    public ModelAndView deleteClass(Model model, @RequestParam int id) {
        ishClassRepository.findById(id).ifPresent(ishClass -> {
            for (Person student : ishClass.getStudents()) {
                student.setISHClass(null);
                personRepository.save(student);
            }
            ishClassRepository.deleteById(id);
        });
        ModelAndView displayClassesMV = new ModelAndView("redirect:/admin/displayClasses");
        return displayClassesMV;
    }

    @RequestMapping(value = {"/displayStudents"})
    public ModelAndView displayStudents(Model model, @RequestParam int classId, HttpSession session,
                                        @RequestParam(value = "error", required = false) String error) {
        ModelAndView studentsMV = new ModelAndView("students.html");
        ishClassRepository.findById(classId).ifPresent(ishClass -> {
            studentsMV.addObject("iSHClass", ishClass);
            studentsMV.addObject("person", new Person());
            session.setAttribute("iSHClass", ishClass);
        });
        if (error != null) studentsMV.addObject("errorMessage", "Invalid Email entered!!");
        return studentsMV;
    }

    @PostMapping(value = {"/addStudent"})
    public ModelAndView addStudent(Model model, @ModelAttribute("person") Person person, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        ISHClass iSHClass = (ISHClass) session.getAttribute("iSHClass");
        Person student = personRepository.readByEmail(person.getEmail());
        if (student == null || student.getPersonId() <= 0) {
            modelAndView.setViewName("redirect:/admin/displayStudents?classId=" + iSHClass.getClassId() + "&error=true");
        } else {
            student.setISHClass(iSHClass);
            personRepository.save(student);
            iSHClass.getStudents().add(student);
            ishClassRepository.save(iSHClass);
            modelAndView.setViewName("redirect:/admin/displayStudents?classId=" + iSHClass.getClassId());
        }
        return modelAndView;
    }

    @RequestMapping(value = {"/deleteStudent"})
    public ModelAndView deleteStudent(Model model, @RequestParam int personId, HttpSession session) {
        ISHClass iSHClass = (ISHClass) session.getAttribute("iSHClass");
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/displayStudents?classId=" + iSHClass.getClassId());
        personRepository.findById(personId).ifPresent(student -> {
            student.setISHClass(null);
            personRepository.save(student);
            iSHClass.getStudents().remove(student);
            ISHClass updatedISHClass = ishClassRepository.save(iSHClass);
            session.setAttribute("iSHClass", updatedISHClass);
        });
        return modelAndView;
    }

    @RequestMapping(value = {"/displayCourses"})
    public ModelAndView displayCourses() {
        List<Course> coursesList = courseRepository.findAll();
        ModelAndView coursesMV = new ModelAndView("courses_ish.html");
        coursesMV.addObject("courses", coursesList);
        coursesMV.addObject("course", new Course());
        return coursesMV;
    }

    @PostMapping(value = {"/addNewCourse"})
    public ModelAndView addNewCourse(Model model, @ModelAttribute("course") Course course) {
        courseRepository.save(course);
        ModelAndView displayCoursesMV = new ModelAndView("redirect:/admin/displayCourses");
        return displayCoursesMV;
    }

    @RequestMapping(value = {"/viewStudents"})
    public ModelAndView viewStudents(Model model, @RequestParam int courseId,
                                     HttpSession session, @RequestParam(value = "error", required = false) String error) {
        ModelAndView course_studentsMV = new ModelAndView("course_students.html");
        courseRepository.findById(courseId).ifPresent(course -> {
            course_studentsMV.addObject("course", course);
            course_studentsMV.addObject("person", new Person());
            session.setAttribute("course", course);
        });
        if (error != null) course_studentsMV.addObject("errorMessage", "Invalid Email entered!!");
        return course_studentsMV;
    }

    @PostMapping(value = {"/addStudentToCourse"})
    public ModelAndView addStudentToCourse(Model model, @ModelAttribute("person") Person person, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        Course course = (Course) session.getAttribute("course");
        Person student = personRepository.readByEmail(person.getEmail());
        if (student == null || student.getPersonId() <= 0) {
            modelAndView.setViewName("redirect:/admin/viewStudents?courseId=" + course.getCourseId() + "&error=true");
        } else {
            student.getCourses().add(course);
            personRepository.save(student);
            course.getPersons().add(student);
            session.setAttribute("course", course);
            modelAndView.setViewName("redirect:/admin/viewStudents?courseId=" + course.getCourseId());
        }
        return modelAndView;
    }

    @RequestMapping(value = {"/deleteStudentFromCourse"})
    public ModelAndView deleteStudentFromCourse(Model model, @RequestParam int personId, HttpSession session) {
        Course course = (Course) session.getAttribute("course");
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/viewStudents?courseId=" + course.getCourseId());
        personRepository.findById(personId).ifPresent(student -> {
            student.getCourses().remove(course);
            personRepository.save(student);
            course.getPersons().remove(student);
            session.setAttribute("course", course);
        });
        return modelAndView;
    }
}