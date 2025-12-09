package org.example.springbootrefresher.courses.repository;

import org.example.springbootrefresher.courses.model.Course;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Repository
public class CourseRepository {

    private final List<Course> courses;

    public CourseRepository() {
        this.courses = new LinkedList<>();
        courses.add(new Course(1, "Java Basics", 48));
        courses.add(new Course(2, "Java JPA Basics", 24));
        courses.add(new Course(3, "Java Spring Basics", 24));
    }

    public boolean add(Course course){
        return courses.add(course);
    }

    public Optional<Course> findById(int id) {
        return courses.stream().filter(course -> course.getId() == id).findFirst();
    }

    public List<Course> findAll() {
        return courses;
    }

}
