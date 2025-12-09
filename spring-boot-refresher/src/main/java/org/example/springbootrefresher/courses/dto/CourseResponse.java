package org.example.springbootrefresher.courses.dto;

import org.springframework.http.HttpStatus;

public record CourseResponse(Object data, HttpStatus code) {
}
