package com.example.student.controller;

import com.example.student.dto.input.GradeInput;
import com.example.student.model.Grade;
import com.example.student.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/grades")
public class GradeController {
    @Autowired
    private GradeService gradeService;

    @GetMapping("/list")
    public List<Grade> getAllGrades(){
        return gradeService.getAllGrade();
    }
    @GetMapping("{id}")
    public Optional<Grade> getGradeById(@PathVariable(value="id") Integer id){
        return gradeService.findById(id);
    }
    @PostMapping("/save")
    public Grade saveGrade(@RequestBody GradeInput gradeInput) {
        return gradeService.saveGrade(gradeInput);
    }
    @PutMapping("/update/{id}")
    public Grade updateGrade(@PathVariable(value="id")Integer gradeId,@RequestBody GradeInput gradeInput) {
        return gradeService.updateGrade(gradeId, gradeInput);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteGrade(@PathVariable Integer id) {
        gradeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
