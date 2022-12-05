package mk.ukim.finki.wp.lab.data;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.model.Teacher;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Course> courseList = new ArrayList<>();
    public static List<Student> students = new ArrayList<>();
    public static List<Teacher> teachers = new ArrayList<>();

    @PostConstruct
    public void init() {
        Student s1 = new Student("angela1", "12345", "Angela1", "Jovanovska1");
        Student s2 = new Student("angela2", "12345", "Angela2", "Jovanovska2");
        Student s3 = new Student("angela3", "12345", "Angela3", "Jovanovska3");
        Student s4 = new Student("angela4", "12345", "Angela4", "Jovanovska4");
        Student s5 = new Student("angela5", "12345", "Angela5", "Jovanovska5");

        students.add(s1);
        students.add(s2);
        students.add(s3);
        students.add(s4);
        students.add(s5);

        Teacher t1 = new Teacher( "Marko", "Markovski");
        Teacher t2 = new Teacher( "Todor", "Todorovski");
        Teacher t3 = new Teacher(  "Bojan", "Bojanovski");
        Teacher t4 = new Teacher(  "Jovana", "Jovanovska");
        Teacher t5 = new Teacher(  "Sara", "Sarovska");

        courseList.add(new Course( "Veb Programiranje", "Voved vo veb",t1));
        courseList.add(new Course( "Operativni Sistemi", "Voved vo veb", t2));
        courseList.add(new Course( "Softversko Inzenerstvo", "Voved vo veb", t3));
        courseList.add(new Course( "Diskretna Matematika", "Voved vo veb", t4));
        courseList.add(new Course( "Mobilni informaciski sistemi", "Voved vo veb", t5));

        courseList.get(0).getStudents().add(s1);
        courseList.get(0).getStudents().add(s2);
        courseList.get(0).getStudents().add(s3);
        courseList.get(0).getStudents().add(s4);

        courseList.get(1).getStudents().add(s3);
        courseList.get(1).getStudents().add(s4);

        courseList.get(2).getStudents().add(s4);
        courseList.get(2).getStudents().add(s5);

        courseList.get(3).getStudents().add(s2);
        courseList.get(3).getStudents().add(s3);
        courseList.get(3).getStudents().add(s4);
        courseList.get(3).getStudents().add(s5);


        courseList.get(4).getStudents().add(s5);



        teachers.add(t1);
        teachers.add(t2);
        teachers.add(t3);
        teachers.add(t4);
        teachers.add(t5);


//        courseList.get(0).setStudents(students.subList(0, 3));
//        courseList.get(1).setStudents(students.subList(2, 3));
//        courseList.get(2).setStudents(students.subList(3, 4));
//        courseList.get(3).setStudents(students.subList(1, 4));
//        courseList.get(4).setStudents(students.subList(4, 4));
    }
}
