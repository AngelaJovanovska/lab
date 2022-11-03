//package mk.ukim.finki.wp.lab.repository;
//
//import mk.ukim.finki.wp.lab.model.Student;
//import mk.ukim.finki.wp.lab.podatoci.DataHolder;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Repository
//public class StudentRepository {
//
//    public List<Student> findAllStudents(){
//        return DataHolder.studentList;
//    }
//
//    public List<Student> findAllByNameOrSurname(String text){
//        return DataHolder.studentList.stream().filter(r->r.getName().equals(text) || r.getSurname().equals(text)).collect(Collectors.toList());
//    }
//    public Student save(String username,String password, String name, String surname){
//        if(username == null || password == null || name == null || surname==null){
//            return null;
//        }
//
//        DataHolder.studentList.removeIf(r->r.getName().equals(name));
//        DataHolder.studentList.add(new Student(username,password,name,surname));
//        return new Student(username,password,name,surname);
//    }
//}

package mk.ukim.finki.wp.lab.repository;

import static mk.ukim.finki.wp.lab.data.DataHolder.students;

import mk.ukim.finki.wp.lab.model.Student;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class StudentRepository {

    public StudentRepository() {

    }

    public List<Student> findAllStudents() {
        return students;
    }

    public List<Student> findAllByNameOrSurname(String text) {
        return students.stream().filter(r -> r.getName().equals(text) || r.getSurname().equals(text)).collect(Collectors.toList());
    }

    public Student findByUsername(String username) {
        return students.stream().filter(r -> r.getUsername().equals(username)).findFirst().get();
    }

    public Student save(String username, String password, String name, String surname) throws Exception {
        if (username == null || password == null || username.isEmpty() || password.isEmpty()) {
            return null;
        }
        boolean exists = students.stream().anyMatch(e -> e.getUsername().equals(username));
        if (exists) {
            throw new Exception("Student with that username exist!");
        } else {
            Student s = new Student(username, password, name, surname);
            students.add(s);
            return s;
        }
    }
}