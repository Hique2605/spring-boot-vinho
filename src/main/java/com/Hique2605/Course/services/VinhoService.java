package com.Hique2605.Course.services;

import java.util.List;
import java.util.Optional;

import com.Hique2605.Course.services.exceptions.DatabaseException;
import com.Hique2605.Course.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.Hique2605.Course.entities.Vinho;
import com.Hique2605.Course.repositories.VinhoRepository;

@Service
public class VinhoService {
	
	@Autowired
	private VinhoRepository repository;

	//busca todos
	public List<Vinho> findAll(){
		return repository.findAll();
		
	}
	//busca por id
	public Vinho findById(long id) {
		return repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	//cria vinho
	public Vinho insert(Vinho obj) {
		return repository.save(obj);
	}

	//deleta vinho
	public void delete(Long id) {
		try {
			// Verifica se o vinho existe no repositório
			if (!repository.existsById(id)) {
				throw new ResourceNotFoundException(id); // Lança exceção se o vinho não existir
			}
			// Deleta o vinho se existir
			repository.deleteById(id);

		} catch (EmptyResultDataAccessException e) {
			// Caso ocorra erro devido a um ID inexistente ou já deletado
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			// Caso ocorra erro devido a integridade de dados (exemplo: o usuário tem registros relacionados)
			throw new DatabaseException(e.getMessage());
		}
	}

	// Atualiza Vinho
	public Vinho update(Long id, Vinho obj) {
		try {
			Vinho entity = repository.getReferenceById(id);
			updateData(entity, obj);
			return repository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	// Metodo auxiliar para atualizar dados
	private void updateData(Vinho entity, Vinho obj) {
		if (obj.getNome() != null) entity.setNome(obj.getNome());
		if (obj.getSafra() != null) entity.setSafra(obj.getSafra());
		if (obj.getTipo() != null) entity.setTipo(obj.getTipo());
		if (obj.getUva() != null) entity.setUva(obj.getUva());
		if (obj.getTeorAlcoolico() != null) entity.setTeorAlcoolico(obj.getTeorAlcoolico());
		if (obj.getVolume() != null) entity.setVolume(obj.getVolume());
		if (obj.getNotasDegustacao() != null) entity.setNotasDegustacao(obj.getNotasDegustacao());
		if (obj.getHarmonizacao() != null) entity.setHarmonizacao(obj.getHarmonizacao());
		if (obj.getPrecoUnitario() != null) entity.setPrecoUnitario(obj.getPrecoUnitario());
		if (obj.getImgUrl() != null) entity.setImgUrl(obj.getImgUrl());
		if (obj.getQuantidade() != null) entity.setQuantidade(obj.getQuantidade());
		if (obj.getEmEstoque() != null) entity.setEmEstoque(obj.getEmEstoque());
	}


}
