package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static mk.ukim.finki.wp.lab.data.DataHolder.courseList;

@Repository
public class CourseRepository {

    public CourseRepository() {

    }

    public List<Course> findAllCourses() {
        return courseList;
    }

    public Course findById(Long courseId) {

        return courseList.stream().filter(course -> course.getCourseId().equals(courseId)).findFirst().orElse(null);

    }

    public List<Student> findAllStudentsByCourse(Long courseId) {
        return courseList.stream().filter(course -> course.getCourseId().equals(courseId)).findFirst().get().getStudents();

    }

    public Course addStudentToCourse(Student student, Course course) throws Exception {
        boolean exists = course.getStudents().stream().anyMatch(
                s -> s.getUsername().equals(student.getUsername())
        );
        if (exists) {
            throw new Exception("Student is already enrolled in this course!");
        } else {
                course.getStudents().add(student);
                return course;

        }
    }
}
