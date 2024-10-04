package br.com.braga.UserManagement.entity;

import java.util.Objects;

import org.springframework.beans.BeanUtils;

import br.com.braga.UserManagement.dto.UserDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="vb_user")
public class UserEntity {
      
      @Id
      @GeneratedValue(strategy=GenerationType.IDENTITY)
      private Long id;

      @Column(nullable = false)
      private String name;

      @Column(nullable = false, unique =  true)
      private String login;

      @Column(nullable = false)
      private String password;

      @Column(nullable = false)
      private String email;

      public UserEntity(UserDTO user){
            BeanUtils.copyProperties(user, this);
      }

      public UserEntity(){
            
      }

      public Long getId() {
            return id;
      }

      public void setId(Long id) {
            this.id = id;
      }

      public String getName() {
            return name;
      }

      public void setName(String name) {
            this.name = name;
      }

      public String getLogin() {
            return login;
      }

      public void setLogin(String login) {
            this.login = login;
      }

      public String getPassword() {
            return password;
      }

      public void setPassword(String password) {
            this.password = password;
      }

      public String getEmail() {
            return email;
      }

      public void setEmail(String email) {
            this.email = email;
      }

      @Override
      public int hashCode() {
            return Objects.hash(id);
      }

      @Override
      public boolean equals(Object obj) {
            if (this == obj)
                  return true;
            if (obj == null)
                  return false;
            if (getClass() != obj.getClass())
                  return false;
            UserEntity other = (UserEntity) obj;
            return Objects.equals(id, other.id);
      }



      
}
