package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.model.exceptions.CourseNameAlreadyExistsException;

import java.util.List;


public interface CourseService {
    List<Student> listStudentsByCourse(Long courseId);
    Course addStudentInCourse(String username, Long courseId) throws Exception;
    List<Course> listAll();

    Course findById(Long courseId);
    Course save(Long id,String name, String surname, Long teacherId)throws CourseNameAlreadyExistsException;
    void deleteById(Long id);
    Course findByName(String name);
}
