package mk.ukim.finki.wp.lab.model;

import lombok.Data;
import mk.ukim.finki.wp.lab.model.enumeration.Type;

import java.util.ArrayList;
import java.util.List;


@Data
public class Course {
    Long courseId;
    String name;
    String description;
    List<Student> students;

    Teacher teacher;
    Type type;

    public Course(String name, String description,Teacher teacher) {
        this.courseId = (long) (Math.random()*1000);
        this.name = name;
        this.description = description;
        this.students = new ArrayList<Student>();
        this.teacher = teacher;
        this.type =Type.ELECTIVE;
    }

}
