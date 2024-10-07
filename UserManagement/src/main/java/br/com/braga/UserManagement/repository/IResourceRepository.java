package br.com.braga.UserManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.braga.UserManagement.entity.ResourceEntity;

public interface IResourceRepository extends JpaRepository<ResourceEntity, Long>{
      
}
