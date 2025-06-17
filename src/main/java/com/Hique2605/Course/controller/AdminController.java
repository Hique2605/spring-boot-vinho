package com.Hique2605.Course.controller;

import com.Hique2605.Course.entities.Admin;
import com.Hique2605.Course.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/admins")
public class AdminController {

    @Autowired
    private AdminService service;

    // GET todos os admins
    @GetMapping
    public ResponseEntity<List<Admin>> findAll() {
        List<Admin> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/buscarPorEmail/{email}")
    public ResponseEntity<Admin> findByEmail(@PathVariable String email){
        Admin obj = service.findByEmail(email);
        return ResponseEntity.ok().body(obj);
    }

    // GET admin por ID
    @GetMapping(value = "/{id}")
    public ResponseEntity<Admin> findById(@PathVariable Long id) {
        Admin obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    // POST novo admin
    @PostMapping
    public ResponseEntity<Admin> insert(@RequestBody Admin obj) {
        Admin saved = service.insert(obj);
        URI uri = URI.create("/admins/" + saved.getId());
        return ResponseEntity.created(uri).body(saved);
    }

    // PUT (atualiza admin)
    @PutMapping(value = "/{id}")
    public ResponseEntity<Admin> update(@PathVariable Long id, @RequestBody Admin obj) {
        Admin updated = service.update(id, obj);
        return ResponseEntity.ok().body(updated);
    }

    // DELETE (deleta admin)
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
