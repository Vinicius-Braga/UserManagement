package br.com.braga.UserManagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.braga.UserManagement.dto.ResourceDTO;
import br.com.braga.UserManagement.entity.ResourceEntity;
import br.com.braga.UserManagement.repository.IResourceRepository;

@Service
public class ResourceService {
      
      @Autowired
      private IResourceRepository resourceRepository;

      public List<ResourceDTO> listAll() {
            List<ResourceEntity> resource = resourceRepository.findAll();
            return resource.stream().map(ResourceDTO::new).toList();
      }

      public void create(ResourceDTO resource) {
            ResourceEntity resourceEntity = new ResourceEntity(resource);
            resourceRepository.save(resourceEntity);
      }

      public ResourceDTO alter(ResourceDTO resource) {
            ResourceEntity resourceEntity = new ResourceEntity(resource);
            return new ResourceDTO(resourceRepository.save(resourceEntity));
      }

      public void delete(Long id) {
            ResourceEntity resourceEntity = resourceRepository.findById(id).get();
            resourceRepository.delete(resourceEntity);
      }

      public ResourceDTO findById(Long id) {
            return new ResourceDTO(resourceRepository.findById(id).get()); 
      }
}
