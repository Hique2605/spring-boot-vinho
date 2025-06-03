package com.Hique2605.Course.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Hique2605.Course.entities.Order;
import com.Hique2605.Course.services.OrderService;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {
	
	@Autowired
	private OrderService service ;
	
	@GetMapping
	public ResponseEntity<List<Order>> findAll(){
		List<Order> list = service.findAll();
		return ResponseEntity.ok().body(list);
			
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Order> findById(@PathVariable Long id){
		Order obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}

	@GetMapping(value = "/users/{clientId}")
	public ResponseEntity<List<Order>> findByClient(@PathVariable Long clientId) {
		List<Order> list = service.findByClientId(clientId);
		//testar se o cliente tem pedido se nao ter , voltar com exception pq no postman volta 200 [vazio]
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/representante/{representanteId}")
	public ResponseEntity<List<Order>> findByRepresentante(@PathVariable Long representanteId) {
		List<Order> list = service.findByRepresentanteId(representanteId);
		return ResponseEntity.ok().body(list);
	}


	//fazer find findByRepresentanteId    E  Id do client ? exp seller id tal / do client tal idclient
	//http://localhost:8080/orders/representante/3/users/1



}



