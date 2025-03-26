package com.example.escola.Controller;

import com.example.escola.Entity.Aluno;
import com.example.escola.Repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/aluno")
public class AlunoController {

    @Autowired
    private AlunoRepository alunoRepository;

    @PostMapping
    public ResponseEntity<Aluno> create(@RequestBody Aluno aluno){
        Aluno alunoBd = alunoRepository.save(aluno);
        return ResponseEntity.status(HttpStatus.CREATED).body(aluno);
    }

    @GetMapping
    public ResponseEntity<List<Aluno>> getAll(){
        List<Aluno> alunos = alunoRepository.findAll();
        if (alunos.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(alunos);
    }

    @PutMapping
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody Aluno updateAluno){
        Optional<Aluno> optionalAluno = alunoRepository.findById(id);

        if (optionalAluno.isPresent()){
            Aluno aluno = optionalAluno.get();
            aluno.setNome(updateAluno.getNome());
            aluno.setCpf(updateAluno.getCpf());
            aluno.setCurso(updateAluno.getCurso());
            return ResponseEntity.ok(alunoRepository.save(aluno));
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aluno não encontrado.");
        }
    }
}
