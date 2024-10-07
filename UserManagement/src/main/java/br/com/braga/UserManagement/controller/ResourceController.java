package br.com.braga.UserManagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.braga.UserManagement.dto.ResourceDTO;
import br.com.braga.UserManagement.service.ResourceService;

@RestController
@RequestMapping(value= "/resource")
@CrossOrigin
public class ResourceController {
      
      @Autowired
      private ResourceService resourceService;

      @GetMapping
      public List<ResourceDTO> listAll() {
            return resourceService.listAll();
      }

      @PostMapping
      public void create(@RequestBody ResourceDTO resource) {
            resourceService.create(resource);
      }

      @PutMapping
      public ResourceDTO alter (@RequestBody ResourceDTO resource) {
            return resourceService.alter(resource);
      }

      @DeleteMapping("/{id}")
      public ResponseEntity<Void> delete(@PathVariable Long id) {
            resourceService.delete(id);
            return ResponseEntity.ok().build();
      }
}     
