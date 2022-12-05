package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Teacher;
import mk.ukim.finki.wp.lab.model.exceptions.CourseNameAlreadyExistsException;
import mk.ukim.finki.wp.lab.service.CourseService;
import mk.ukim.finki.wp.lab.service.TeacherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

import java.util.List;

@Controller
@RequestMapping("/courses")
public class CourseController {
    private final CourseService courseService;
    private final TeacherService teacherService;

    public CourseController(CourseService courseService, TeacherService teacherService) {
        this.courseService = courseService;
        this.teacherService = teacherService;
    }


    @GetMapping
    public String getCoursesPage(Model model) {

        List<Course> courses = courseService.listAll();
        model.addAttribute("courses", courses);
        return "listCourses";
    }

    @PostMapping(params = "delete")
//    @PostMapping("/delete/{id}")
    public String deleteProduct(@RequestParam Long courseId) {
        this.courseService.deleteById(courseId);
        return "redirect:/courses";
    }

    @PostMapping(params = "submit")
    public String getStudents(@RequestParam Long courseId, HttpSession session) {
        session.setAttribute("courseId", courseId);
        return "redirect:/AddStudent";
    }

    @PostMapping(params = "edit")
    public String editCourse(@RequestParam Long courseId, Model model, HttpSession session) {
        session.setAttribute("courseId", courseId);
        Course course = this.courseService.findById(courseId);
        if(course == null){
            return "redirect:/courses?error=Course not found";
        }
        List<Teacher> teachers = this.teacherService.findAll();
        model.addAttribute("teachers", teachers);
        model.addAttribute("course", course);
        return "redirect:/courses/add-form/" + courseId;
    }

    @GetMapping(value = {"/add-form", "/add-form/{id}"})
    public String getAddCoursePage(@PathVariable(required = false) Long id,@RequestParam(required = false) String error, Model model){
        if(error!= null && !error.isEmpty()){
            model.addAttribute("hasError2",true);
            model.addAttribute("error2", error);
        }
        List<Teacher> teachers = this.teacherService.findAll();
        if(id == null){
            List<Course> courses = this.courseService.listAll();
            model.addAttribute("courses",courses);
        } else {
            Course course = this.courseService.findById(id);
            model.addAttribute("course",course);
        }
        model.addAttribute("teachers", teachers);
        return "/add-course";
    }

    @PostMapping(value = {"/add","/add/{id}"})
    public String saveCourse(
                            @PathVariable(required = false) Long id,
                            @RequestParam String name,
                            @RequestParam String description,
                            @RequestParam Long teacherId) {

        try{
            this.courseService.save(id, name,description, teacherId);
            return "redirect:/courses";
        }catch (CourseNameAlreadyExistsException e){
            return "redirect:/courses/add-form?error="+e.getMessage();
        }

//        this.courseService.save(id, name,description, teacherId);
//        return "redirect:/courses";
    }
}
