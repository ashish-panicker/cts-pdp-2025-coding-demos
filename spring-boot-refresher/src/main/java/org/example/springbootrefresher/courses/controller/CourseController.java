package org.example.springbootrefresher.courses.controller;

import org.example.springbootrefresher.courses.dto.CourseRequest;
import org.example.springbootrefresher.courses.dto.CourseResponse;
import org.example.springbootrefresher.courses.model.Course;
import org.example.springbootrefresher.courses.repository.CourseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

/*
 * @RestController = @Controller + @ResponseBody
 * - Applied on Rest Controller classes
 * - Class is treated as a controller
 * - All methods return data as JSON/XML
 * - Spring will register this as a request handler
 * - Wraps the return values in HttpMessageConverter
 * -- JSON -> Jackson
 * -- XML -> JAXB
 * - Serialize objects into JSON
 *
 * Request mapping hierarchy
 * - Class level @RequestMapping, establishes the base path
 * - Method level mappings
 * - Path variables, Request parameters
 * - Content negotiation(using the Accept header)
 * Example:
 * Accept: application/xml
 * Spring will use MappingJackson2XmlHttpMessageConverter to convert response to xml format
 *
 * Accept: application/json
 * Spring will use MappingJackson2HttpMessageConverter to convert response to json format
 */
@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseRepository courseRepository;
    private final Logger log = LoggerFactory.getLogger(CourseController.class);

    public CourseController(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @PostMapping
    public ResponseEntity<CourseResponse> create(@RequestBody CourseRequest request) {

        log.info("Creating new course");

        Course course = new Course(request.id(), request.title(), request.duration());
        courseRepository.add(course);

        var headers = new HttpHeaders();
        headers.add("X-Creation-TimeStamp", String.valueOf(System.currentTimeMillis()));
        headers.add("X-Created-By", "CourseService");
        headers.add("X-Rate-Remaining", "99");
        headers.add("X-Request-Handler", "/api/courses");

        return ResponseEntity
                .created(URI.create("/api/courses/" + course.getId()))
                .headers(headers)
                .body(new CourseResponse(course, HttpStatus.CREATED));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseResponse> getById(@PathVariable int id) {
        log.info("Finding course  with id {}", id);
        var found = courseRepository.findById(id);
        if (found.isPresent()) {
            var response = new CourseResponse(found.get(), HttpStatus.OK);
            return ResponseEntity.ok(response);
        }
        log.error("Finding course failed for id {}", id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body(new CourseResponse(null, HttpStatus.NO_CONTENT));
    }
}
