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

import br.com.braga.UserManagement.dto.UserDTO;
import br.com.braga.UserManagement.service.UserService;




@RestController
@RequestMapping(value="/user")
@CrossOrigin
public class UserController {
      
      @Autowired
      private UserService userService;

      @GetMapping
      public List<UserDTO> listAll () {
            return userService.listAll();
      }

      @PostMapping
      public void create(@RequestBody UserDTO user) {
            userService.create(user);
      }

      @PutMapping
      public UserDTO alter(@RequestBody UserDTO user) {
            return userService.alter(user);
      }


      @DeleteMapping("/{id}")
      public ResponseEntity<Void> delete(@PathVariable Long id) {
      userService.delete(id);
      return ResponseEntity.ok().build();
      }
}
