package com.Hique2605.Course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.Hique2605.Course.entities.User;
import com.Hique2605.Course.repositories.UserRepository;
import com.Hique2605.Course.services.exceptions.DatabaseException;
import com.Hique2605.Course.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;
	
	public List<User> findAll(){
		return repository.findAll();
		
	}
	
	public User findById(long id ) {
	Optional<User>	obj = repository.findById(id);
	return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	public User findByEmail(String email) {
		Optional<User>	obj = repository.findByEmail(email);
		return obj.orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + email));
	}
	
	//cria user 
	public User insert(User obj) {
		return repository.save(obj);
	}
	
	//deleta user 
	public void delete(Long id) {
	    try {
	        // Verifica se o usuário existe no repositório
	        if (!repository.existsById(id)) {
	            throw new ResourceNotFoundException(id); // Lança exceção se o usuário não existir
	        }
	        // Deleta o usuário se existir
	        repository.deleteById(id);

	    } catch (EmptyResultDataAccessException e) {
	        // Caso ocorra erro devido a um ID inexistente ou já deletado
	        throw new ResourceNotFoundException(id);
	    } catch (DataIntegrityViolationException e) {
	        // Caso ocorra erro devido a integridade de dados (exemplo: o usuário tem registros relacionados)
	        throw new DatabaseException(e.getMessage());
	    }
	}

	//update user
		public User update(Long id, User obj) {
			try {
			User entity = repository.getReferenceById(id);
			updateData(entity, obj);
			return repository.save(entity);
			} catch(EntityNotFoundException e) {
				throw new ResourceNotFoundException(id);
			}
		}

		//metodo que atualiza
		private void updateData(User entity, User obj) {
			if (obj.getName() != null) entity.setName(obj.getName());
			if (obj.getEmail() != null) entity.setEmail(obj.getEmail());
			if (obj.getPhone() != null) entity.setPhone(obj.getPhone());
			if (obj.getCidade() != null) entity.setCidade(obj.getCidade());
			if (obj.getEndereco() != null) entity.setEndereco(obj.getEndereco());
			if (obj.getPassword() != null) entity.setPassword(obj.getPassword());
		}
	
}
