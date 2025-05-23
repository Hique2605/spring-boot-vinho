package com.Hique2605.Course.services;

import java.util.List;
import java.util.Optional;

import com.Hique2605.Course.entities.Representante;
import com.Hique2605.Course.entities.User;
import com.Hique2605.Course.repositories.RepresentanteRepository;
import com.Hique2605.Course.repositories.UserRepository;
import com.Hique2605.Course.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Hique2605.Course.entities.Order;
import com.Hique2605.Course.repositories.OrderRepository;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository repository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RepresentanteRepository representanteRepository;

	public List<Order> findAll(){
		return repository.findAll();
		
	}
	
	public Order findById(long id ) {
	Optional<Order>	obj = repository.findById(id);
	return obj.get();
	}

	public List<Order> findByClientId(Long clientId) {
		User client = userRepository.findById(clientId)
				.orElseThrow(() -> new ResourceNotFoundException(clientId));
		return repository.findByClient(client);
	}

	public List<Order> findByRepresentanteId(Long representanteId) {
		Representante representante = representanteRepository.findById(representanteId)
				.orElseThrow(() -> new ResourceNotFoundException(representanteId));
		return repository.findByRepresentante(representante);
	}

	//fazer find findByRepresentanteId e Id do client ? exp seller id tal / do client tal idclient

}
