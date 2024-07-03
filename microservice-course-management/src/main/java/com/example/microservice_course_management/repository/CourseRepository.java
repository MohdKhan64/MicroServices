package com.example.microservice_course_management.repository;

import com.example.microservice_course_management.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
