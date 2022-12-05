package mk.ukim.finki.wp.lab.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.PRECONDITION_FAILED)
public class CourseNameAlreadyExistsException extends RuntimeException{
    public CourseNameAlreadyExistsException(String name) {
        super("Course with name " + name + " already exists in the course list");
    }
}
