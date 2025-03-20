package com.example.escola.Controller;

import com.example.escola.Entity.Professor;
import com.example.escola.Repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/professor")
public class ProfessorController {

    @Autowired
    private ProfessorRepository professorRepository;

    @PostMapping
    public ResponseEntity<Professor> create(@RequestBody Professor professor){
        Professor professorBd = professorRepository.save(professor);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(professorBd);
    }

    @GetMapping
    public ResponseEntity<List<Professor>>getAll(){
        List<Professor> professors = professorRepository.findAll();

        if (professors.isEmpty()){
            return  ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(professors);
    }

}
