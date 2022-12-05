package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.model.Teacher;
import mk.ukim.finki.wp.lab.model.exceptions.CourseNameAlreadyExistsException;
import mk.ukim.finki.wp.lab.repository.CourseRepository;
import mk.ukim.finki.wp.lab.repository.StudentRepository;
import mk.ukim.finki.wp.lab.repository.TeacherRepository;
import mk.ukim.finki.wp.lab.service.CourseService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;

    public CourseServiceImpl(CourseRepository courseRepository, StudentRepository studentRepository, TeacherRepository teacherRepository) {
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
    }

    @Override
    public List<Student> listStudentsByCourse(Long courseId) {
            return courseRepository.findAllStudentsByCourse(courseId);
    }

    @Override
    public Course addStudentInCourse(String username, Long courseId) throws Exception {
        Student student = studentRepository.findByUsername(username);
        Course course = courseRepository.findById(courseId);
        return courseRepository.addStudentToCourse(student,course);
    }

    @Override
    public List<Course> listAll() {
        return courseRepository.findAllCourses();
    }

    @Override
    public Course findById(Long courseId) {
        return courseRepository.findById(courseId);
    }

    @Override
    public Course findByName(String name) {
        return courseRepository.findByName(name);
    }

    @Override
    public Course save(Long id, String name, String surname, Long teacherId) throws CourseNameAlreadyExistsException{
        Teacher teacher = this.teacherRepository.findById(teacherId);
//                .orElseThrow(()->new TeacherNotFoundException(teacherId));
//        Course course =this.findByName(name);
//        if(course.getName().equals(name))
//        if(this.courseRepository.nameExists(name)){
//            throw  new CourseNameAlreadyExistsException(name);
//        }
//        if(this.courseRepository.findById(id))
        return this.courseRepository.save(id, name, surname,teacher);
    }

    @Override
    public void deleteById(Long id) {
        courseRepository.deleteById(id);
    }
}
