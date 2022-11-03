package mk.ukim.finki.wp.lab.web.servlet;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.service.CourseService;
import mk.ukim.finki.wp.lab.service.StudentService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "student-servlet", urlPatterns = "/AddStudent")
public class ListStudentServlet extends HttpServlet {
    private final SpringTemplateEngine springTemplateEngine;
    private final StudentService studentService;
    private final CourseService courseService;

    public ListStudentServlet(SpringTemplateEngine springTemplateEngine, StudentService studentService, CourseService courseService) {
        this.springTemplateEngine = springTemplateEngine;
        this.studentService = studentService;

        this.courseService = courseService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Student> students = studentService.listAll();
        WebContext context = new WebContext(req, resp, req.getServletContext());
        Long courseId = ((Long) context.getSession().getAttribute("courseId"));
        Course course = courseService.findById(courseId);
        context.setVariable("course", course);
        context.setVariable("students", students);
        this.springTemplateEngine.process("listStudents.html", context, resp.getWriter());

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context = new WebContext(req, resp, req.getServletContext());
        Long courseId = ((Long) context.getSession().getAttribute("courseId"));
        String username = req.getParameter("username");

        try {
            courseService.addStudentInCourse(username, courseId);
            resp.sendRedirect("/StudentEnrollmentSummary");
        } catch (Exception ex) {
            Course course = courseService.findById(courseId);
            List<Student> students = studentService.listAll();
            context.setVariable("course", course);
            context.setVariable("students", students);
            context.setVariable("hasError", true);
            if (ex.getMessage() != null && ex.getMessage().length() > 0) {
                context.setVariable("error", ex.getMessage());
            } else {
                context.setVariable("error", "Unknown error");
            }
            springTemplateEngine.process("listStudents.html", context, resp.getWriter());
        }
    }
}
