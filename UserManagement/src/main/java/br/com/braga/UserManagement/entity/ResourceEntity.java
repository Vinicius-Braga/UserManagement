package br.com.braga.UserManagement.entity;

import java.util.Objects;

import org.springframework.beans.BeanUtils;

import br.com.braga.UserManagement.dto.ResourceDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name= "vb_resource")
public class ResourceEntity {


      @Id
      @GeneratedValue(strategy = GenerationType.AUTO)
      private Long id;

      @Column(nullable= false)
      private String name;

      @Column(nullable= false)
      private String code;

      public ResourceEntity(ResourceDTO resource) {
            BeanUtils.copyProperties(resource, this);
      }

      public ResourceEntity() {
            
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

      public String getCode() {
            return code;
      }

      public void setCode(String code) {
            this.code = code;
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
            return Objects.equals(id, other.getId());
      }

      
}