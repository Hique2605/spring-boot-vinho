package com.Hique2605.Course.repositories;

import com.Hique2605.Course.entities.Representante;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RepresentanteRepository extends JpaRepository<Representante, Long> {

    Optional<Representante> findByEmail(String email);
}