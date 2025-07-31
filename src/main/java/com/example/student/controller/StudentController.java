package com.example.student.controller;

import com.example.student.dto.StudentDto;
import com.example.student.dto.input.StudentInput;
import com.example.student.model.Student;
import com.example.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    // Simple test endpoint
    @GetMapping("/greeting")
    public ResponseEntity<StudentDto> greeting() {
        StudentDto student = new StudentDto();
        student.setStudentName("KoSai");
        student.setGender("Male");
        student.setEmail("example@gmail.com");
        student.setAddress("Myanmar");
        return ResponseEntity.ok(student);
    }

    // Get all students (main endpoint)
    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    // Alternative list endpoint
    @GetMapping("/list")
    public List<Student> getAllStudentsList() {
        return studentService.getAllStudents();
    }

    // Get single student
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Integer id) {
        return studentService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Create student
    @PostMapping("/save")
    public ResponseEntity<Student> saveStudent(@RequestBody StudentInput studentInput) {
        Student savedStudent = studentService.saveStudent(studentInput);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedStudent);
    }

    // Update student
    @PutMapping("/update/{id}")
    public ResponseEntity<Student> updateStudent(
            @PathVariable Integer id,
            @RequestBody StudentInput studentInput) {
        return ResponseEntity.ok(studentService.update(id, studentInput));
    }

    // Delete student
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Integer id) {
        studentService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
