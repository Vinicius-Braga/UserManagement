package br.com.braga.UserManagement.dto;

import org.springframework.beans.BeanUtils;

import br.com.braga.UserManagement.entity.ResourceEntity;

public class ResourceDTO {
      
      private Long id;
      private String name;
      private String code;

      public ResourceDTO() {

      }

      public ResourceDTO(ResourceEntity resource) {
            BeanUtils.copyProperties(resource, this);
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

      
}
