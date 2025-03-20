package com.example.escola.Repository;

import com.example.escola.Entity.Curso;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {
}
