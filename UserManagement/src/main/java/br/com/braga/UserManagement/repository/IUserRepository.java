package br.com.braga.UserManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.braga.UserManagement.entity.UserEntity;

public interface IUserRepository extends JpaRepository<UserEntity, Long>{
      
}
