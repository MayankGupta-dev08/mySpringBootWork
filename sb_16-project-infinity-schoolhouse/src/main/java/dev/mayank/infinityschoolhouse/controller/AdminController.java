package dev.mayank.infinityschoolhouse.controller;

import dev.mayank.infinityschoolhouse.model.ContactDetail;
import dev.mayank.infinityschoolhouse.model.ISHClass;
import dev.mayank.infinityschoolhouse.model.Person;
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

    @Autowired
    public AdminController(ContactDetailService contactDetailService, PersonRepository personRepository, ISHClassRepository ishClassRepository) {
        this.contactDetailService = contactDetailService;
        this.personRepository = personRepository;
        this.ishClassRepository = ishClassRepository;
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
}