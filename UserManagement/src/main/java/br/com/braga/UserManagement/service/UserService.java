package br.com.braga.UserManagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.braga.UserManagement.dto.UserDTO;
import br.com.braga.UserManagement.entity.UserEntity;
import br.com.braga.UserManagement.repository.IUserRepository;

@Service
public class UserService {
      
      @Autowired
      private IUserRepository userRepository;

      public List<UserDTO> listAll(){
            List<UserEntity> users = userRepository.findAll();
            return users.stream().map(UserDTO::new).toList();
      }

      public UserDTO findByID(Long id) {
            return new UserDTO(userRepository.findById(id).get());
      }

      public void create(UserDTO user){
            UserEntity userEntity = new UserEntity(user);
            userRepository.save(userEntity);
      }

      public UserDTO alter(UserDTO user) {
            UserEntity userEntity = new UserEntity(user);
            return new UserDTO(userRepository.save(userEntity));
      }

      public void delete(Long id)  {
            UserEntity user = userRepository.findById(id).get();
            userRepository.delete(user);
      }
}
