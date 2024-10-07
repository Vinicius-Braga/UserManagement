package br.com.braga.UserManagement.service;

import java.util.List;

import br.com.braga.UserManagement.dto.ResourceDTO;
import br.com.braga.UserManagement.entity.ResourceEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

import br.com.braga.UserManagement.dto.ProfileDTO;
import br.com.braga.UserManagement.entity.ProfileEntity;
import br.com.braga.UserManagement.repository.IProfileRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@Service
public class ProfileService {
      
      @Autowired
      private IProfileRepository profileRepository;

      @GetMapping
      public List<ProfileDTO> listAll() {
            List<ProfileEntity> resource = profileRepository.findAll();
            return resource.stream().map(ProfileDTO::new).toList();
      }

      @PostMapping
      public void create(ProfileDTO profile) {
            ProfileEntity profileEntity = new ProfileEntity(profile);
            profileRepository.save(profileEntity);
      }

      @PutMapping
      public ProfileDTO alter(ProfileDTO profile) {
            ProfileEntity profileEntity = profileRepository.findById(profile.getId()).get();
            return new ProfileDTO(profileRepository.save(profileEntity));
      }

      @DeleteMapping()
      public void delete(Long id) {
            ProfileEntity profileEntity = profileRepository.findById(id).get();
            profileRepository.delete(profileEntity);
      }

      @GetMapping
      public ProfileDTO findById(Long id) {
            return new ProfileDTO(profileRepository.findById(id).get());
      }
}
