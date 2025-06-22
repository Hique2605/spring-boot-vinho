package com.Hique2605.Course.services;

import com.Hique2605.Course.entities.Representante;
import com.Hique2605.Course.entities.Vinho;
import com.Hique2605.Course.repositories.RepresentanteRepository;
import com.Hique2605.Course.services.exceptions.DatabaseException;
import com.Hique2605.Course.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RepresentanteService {

    @Autowired
    private RepresentanteRepository repository;

    public List<Representante> findAll() {
        return repository.findAll();
    }

    public Representante findById(Long id) {
        Optional<Representante> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    //buscar por email
    public Representante findByEmail(String email) {
        Optional<Representante>	obj = repository.findByEmail(email);
        return obj.orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + email));
    }

    public Representante insert(Representante obj) {
        return repository.save(obj);
    }

    public void delete(Long id) {
        try {
            if (!repository.existsById(id)) {
                throw new ResourceNotFoundException(id);
            }
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }


    public Representante update(Long id, Representante obj) {
        try {
            Representante entity = repository.getReferenceById(id);
            updateData(entity, obj);
            return repository.save(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    // Metodo auxiliar para atualizar somente os campos não-nulos
    private void updateData(Representante entity, Representante obj) {
        if (obj.getNome() != null) entity.setNome(obj.getNome());
        if (obj.getEmail() != null) entity.setEmail(obj.getEmail());
        if (obj.getTelefone() != null) entity.setTelefone(obj.getTelefone());
        if (obj.getPassword() != null) entity.setPassword(obj.getPassword());
        if (obj.getMeta() != null) entity.setMeta(obj.getMeta());
    }

}
