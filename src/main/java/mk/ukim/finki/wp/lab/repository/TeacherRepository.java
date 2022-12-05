package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.data.DataHolder;
import mk.ukim.finki.wp.lab.model.Teacher;
import org.springframework.stereotype.Repository;

import java.util.List;

import static mk.ukim.finki.wp.lab.data.DataHolder.teachers;

@Repository
public class TeacherRepository {

    public List<Teacher> findAll(){
        return DataHolder.teachers;
    }
    public Teacher findById(Long id){
        return teachers.stream().filter(course -> course.getId().equals(id)).findFirst().orElse(null);
    }



}
