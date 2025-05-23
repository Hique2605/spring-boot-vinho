package com.Hique2605.Course.services;

import com.Hique2605.Course.entities.Admin;
import com.Hique2605.Course.repositories.AdminRepository;
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
public class AdminService {

    @Autowired
    private AdminRepository repository;

    // Buscar todos os admins
    public List<Admin> findAll() {
        return repository.findAll();
    }

    // Buscar admin por ID
    public Admin findById(Long id) {
        Optional<Admin> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    // Inserir novo admin
    public Admin insert(Admin obj) {
        return repository.save(obj);
    }

    // Atualizar admin
    public Admin update(Long id, Admin obj) {
        try {
            Admin entity = repository.getReferenceById(id);
            updateData(entity, obj);
            return repository.save(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    // Metodo auxiliar para atualizar apenas os campos não-nulos
    private void updateData(Admin entity, Admin obj) {
        if (obj.getNome() != null) entity.setNome(obj.getNome());
        if (obj.getEmail() != null) entity.setEmail(obj.getEmail());
        if (obj.getPhone() != null) entity.setPhone(obj.getPhone());
    }

    // Deletar admin
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
}
