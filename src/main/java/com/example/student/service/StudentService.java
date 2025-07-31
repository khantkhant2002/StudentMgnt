package com.example.student.service;

import com.example.student.dto.input.StudentInput;
import com.example.student.model.Grade;
import com.example.student.model.Student;
import com.example.student.repository.GradeRepository;
import com.example.student.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private GradeRepository gradeRepository;

    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }
    public Optional<Student> findById(Integer id){
        return studentRepository.findById(id);
    }
    public Boolean deleteById(Integer id) {
        studentRepository.deleteById(id);
        return true;
    }
    @Transactional
    public Student saveStudent(StudentInput studentInput) {
        // 1. Validate input
        if (studentInput.gradeId() == null) {
            throw new IllegalArgumentException("Grade ID must not be null");
        }

        // 2. Find the grade with proper error handling
        Grade grade = gradeRepository.findById(studentInput.gradeId())
                .orElseThrow(() -> new RuntimeException("Grade not found with id: " + studentInput.gradeId()));

        // 3. Create and populate student
        Student student = new Student();
        student.setName(studentInput.name());
        student.setGender(studentInput.gender());
        student.setEmail(studentInput.email());
        student.setAddress(studentInput.address());
        student.setGrade(grade);

        // 4. Save and return
        return studentRepository.save(student);
    }
    public Student update(Integer id,StudentInput newStudent) {
        Optional<Student> student= studentRepository.findById(id);
        if(student.isPresent()) {
            Optional<Grade> grade = gradeRepository.findById(newStudent.gradeId());
            Student existStudent = student.get();
            existStudent.setName(newStudent.name());
            existStudent.setGender(newStudent.gender());
            existStudent.setEmail(newStudent.email());
            existStudent.setAddress(newStudent.address());
            existStudent.setGrade(grade.get());
            return studentRepository.save(existStudent);
        }else throw new RuntimeException("Student not found");
    }
}

