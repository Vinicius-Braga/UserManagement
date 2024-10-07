package br.com.braga.UserManagement.controller;

import br.com.braga.UserManagement.dto.ProfileDTO;
import br.com.braga.UserManagement.entity.ProfileEntity;
import br.com.braga.UserManagement.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @GetMapping
    public List<ProfileDTO> listAll() {
        return profileService.listAll();
    };

    @PostMapping
    public void create(@RequestBody ProfileDTO profileDTO) {
        profileService.create(profileDTO);
    }

    @PutMapping
    public ProfileDTO alter(@RequestBody ProfileDTO profileDTO) {
        return profileService.alter(profileDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProfileDTO> delete(@PathVariable Long id) {
        profileService.delete(id);
        return ResponseEntity.ok().build();
    }
}
