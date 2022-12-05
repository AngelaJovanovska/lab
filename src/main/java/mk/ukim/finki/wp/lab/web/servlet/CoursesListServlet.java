package mk.ukim.finki.wp.lab.web.servlet;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.service.CourseService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "courses-list-servlet",urlPatterns = "/servlet/listCourses")
public class CoursesListServlet extends HttpServlet {
    private final CourseService courseService;
    private final SpringTemplateEngine springTemplateEngine;

    public CoursesListServlet(SpringTemplateEngine springTemplateEngine, CourseService courseService) {
        this.springTemplateEngine = springTemplateEngine;
        this.courseService = courseService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Course> courses = courseService.listAll();
        WebContext context = new WebContext(req,resp,req.getServletContext());
        context.setVariable("courses",courses);
        this.springTemplateEngine.process("listCourses.html",context,resp.getWriter());
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        Long courseId = Long.valueOf(req.getParameter("courseId"));
        String courseID = req.getParameter("courseId");
        if(courseID == null){
            resp.sendRedirect("/listCourses");
        }else{
            Long courseId = (Long.valueOf(courseID));
            req.getSession().setAttribute("courseId",courseId);
            resp.sendRedirect("/AddStudent");
        }
//        req.getSession().setAttribute("courseId", courseId);
//        resp.sendRedirect("/AddStudent");
    }
}
