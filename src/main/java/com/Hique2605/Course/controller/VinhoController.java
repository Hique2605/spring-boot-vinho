package com.Hique2605.Course.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.Hique2605.Course.entities.Vinho;
import com.Hique2605.Course.services.VinhoService;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(value = "/vinhos")
public class VinhoController {
	
	@Autowired
	private VinhoService service ;
	
	@GetMapping
	public ResponseEntity<List<Vinho>> findAll(){
		List<Vinho> list = service.findAll();
				
		return ResponseEntity.ok().body(list);
			
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Vinho> findById(@PathVariable Long id){
		Vinho obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}

	@PostMapping
	public ResponseEntity<Vinho> insert(@RequestBody Vinho obj){
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);

	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Vinho> update(@PathVariable Long id ,@RequestBody Vinho obj){
		obj = service.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}


	
}



