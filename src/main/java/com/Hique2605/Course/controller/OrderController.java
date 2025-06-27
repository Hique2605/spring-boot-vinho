package com.Hique2605.Course.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.Hique2605.Course.entities.Order;
import com.Hique2605.Course.services.OrderService;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {
	
	@Autowired
	private OrderService orderService ;


	@GetMapping
	public ResponseEntity<List<Order>> findAll(){
		List<Order> list = orderService.findAll();
		return ResponseEntity.ok().body(list);
			
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Order> findById(@PathVariable Long id){
		Order obj = orderService.findById(id);
		return ResponseEntity.ok().body(obj);
	}

	@GetMapping(value = "/users/{clientId}")
	public ResponseEntity<List<Order>> findByClient(@PathVariable Long clientId) {
		List<Order> list = orderService.findByClientId(clientId);
		//testar se o cliente tem pedido se nao ter , voltar com exception pq no postman volta 200 [vazio]
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/representante/{representanteId}")
	public ResponseEntity<List<Order>> findByRepresentante(@PathVariable Long representanteId) {
		List<Order> list = orderService.findByRepresentanteId(representanteId);
		return ResponseEntity.ok().body(list);
	}

	@PostMapping
	public ResponseEntity<Order> insert(@RequestBody Order obj) {
		obj = orderService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}


	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		orderService.deleteById(id);
		return ResponseEntity.noContent().build();
	}






}



