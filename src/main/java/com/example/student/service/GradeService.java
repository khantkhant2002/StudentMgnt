package com.example.student.service;

import com.example.student.dto.input.GradeInput;
import com.example.student.model.Grade;
import com.example.student.repository.GradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GradeService {

    @Autowired
    private GradeRepository gradeRepository;

    public List<Grade> getAllGrade(){
        return gradeRepository.findAll();
    }
    public Grade saveGrade(GradeInput gradeInput){
        Grade grade=new Grade();
        grade.setName(gradeInput.name());
        grade.setStatus(gradeInput.status());
        return gradeRepository.save(grade);
    }

    public Boolean deleteById(Integer id) {
        gradeRepository.deleteById(id);
        return true;
    }

    public Grade updateGrade(Integer id, GradeInput gradeInput){
        Optional<Grade> optionalGrade = gradeRepository.findById(id);
        if(optionalGrade.isPresent()){
            Grade existingGrade = optionalGrade.get();
            existingGrade.setName(gradeInput.name());
            return gradeRepository.save(existingGrade);
        }else{
            throw new RuntimeException("Grade Not found");
        }
    }
    public Optional<Grade> findById(Integer id){
        return gradeRepository.findById(id);
    }
}
