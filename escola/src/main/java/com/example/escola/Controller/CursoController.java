package com.example.escola.Controller;

import com.example.escola.Entity.Curso;
import com.example.escola.Entity.Professor;
import com.example.escola.Repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/curso")
public class CursoController {
    @Autowired
    private CursoRepository cursoRepository;

    @PostMapping
    public ResponseEntity<Curso> create(@RequestBody Curso curso){
        Curso cursoBd = cursoRepository.save(curso);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(cursoBd);
    }

    @GetMapping
    public ResponseEntity<List<Curso>>getAll(){
        List<Curso> cursos = cursoRepository.findAll();

        if (cursos.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(cursos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody Curso updateCurso){
        Optional<Curso> optionalCurso = cursoRepository.findById(id);

        if (optionalCurso.isPresent()){
            Curso curso = optionalCurso.get();
            curso.setNome(updateCurso.getNome());
            curso.setNumeroSala(updateCurso.getNumeroSala());
            curso.setProfessor(updateCurso.getProfessor());
            return ResponseEntity.ok(cursoRepository.save(curso));
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Curso não encontrado.");
        }
    }
}
