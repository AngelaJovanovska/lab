package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.model.Teacher;
import mk.ukim.finki.wp.lab.model.exceptions.CourseNameAlreadyExistsException;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;


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
    public Course findByName(String name) {

        return courseList.stream().filter(course -> course.getName().equals(name)).findFirst().orElse(null);

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
    public Course save(Long id, String name, String description, Teacher teacher){
        Course course;

        if(id != null){
            // update
            course = courseList.stream().filter(c -> c.getCourseId().equals(id)).findFirst().get();
            course.setName(name);
            course.setDescription(description);
            course.setTeacher(teacher);

        } else {

            if(courseList.stream().filter(course1 -> course1.getName().equals(name)).count() > 0){
                throw  new CourseNameAlreadyExistsException(name);
            }
//            courseList.removeIf(course1 -> course1.getName().equals(name));
            course = new Course(name, description, teacher);

            courseList.add(course);

        }
        return course;
    }

//    public boolean nameExists(String name){
//        return courseList.stream().filter(course -> course.getName().equals(name)).count() > 0;
//    }
    public void deleteById(Long id){
        courseList.removeIf(course -> course.getCourseId().equals(id));
    }

}
