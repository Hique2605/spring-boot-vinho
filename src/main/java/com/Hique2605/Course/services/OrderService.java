package com.Hique2605.Course.services;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import com.Hique2605.Course.entities.*;
import com.Hique2605.Course.repositories.*;
import com.Hique2605.Course.services.exceptions.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

	@Autowired
	private OrderRepository repository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RepresentanteRepository representanteRepository;

	@Autowired
	private VinhoRepository vinhoRepository;

	@Autowired
	private OrderItemRepository orderItemRepository;


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

	//cria order
	@Transactional
	public Order insert(Order obj) {
		// Buscar e setar o cliente
		Order finalObj1 = obj;
		User client = userRepository.findById(obj.getClient().getId())
				.orElseThrow(() -> new ResourceNotFoundException(finalObj1.getClient().getId()));
		obj.setClient(client);

		// Buscar e setar o representante
		Order finalObj = obj;
		Representante representante = representanteRepository.findById(obj.getRepresentante().getId())
				.orElseThrow(() -> new ResourceNotFoundException(finalObj.getRepresentante().getId()));
		obj.setRepresentante(representante);

		//  Setar a data e hora atual
		obj.setMoment(Instant.now());

		//  Processar os itens do pedido
		for (OrderItem item : obj.getItems()) {
			// Buscar o vinho do item
			Vinho vinho = vinhoRepository.findById(item.getVinho().getId())
					.orElseThrow(() -> new ResourceNotFoundException(item.getVinho().getId()));

			// Setar o vinho no item
			item.setVinho(vinho);

			// Setar o pedido no item (ligação bidirecional)
			item.setOrder(obj);

			// Setar o preço do item (usando o preço do vinho)
			item.setPrice(vinho.getPrecoUnitario());
		}

		//  Se tiver pagamento, associar o pedido ao pagamento
		if (obj.getPayment() != null) {
			obj.getPayment().setOrder(obj);
		}

		//  Salvar o pedido principal
		obj = repository.save(obj);

		//  Salvar os itens (se não tiver Cascade nos itens)
		orderItemRepository.saveAll(obj.getItems());

		// Retornar o pedido completo (com ID, moment, etc)
		return obj;
	}


}
