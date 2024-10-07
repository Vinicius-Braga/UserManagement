package br.com.braga.UserManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.braga.UserManagement.entity.ProfileEntity;

public interface  IProfileRepository extends JpaRepository<ProfileEntity, Long> {
      
}
