package br.com.braga.UserManagement.dto;

import br.com.braga.UserManagement.entity.ProfileEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
public class ProfileDTO {
      
      private Long id;
      private String description;

      public ProfileDTO() {}

      public ProfileDTO(ProfileEntity profile) {
            BeanUtils.copyProperties(profile, this);
      }
}
