package br.com.braga.UserManagement.entity;

import org.springframework.web.bind.annotation.CrossOrigin;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name= "vb_profile")
@CrossOrigin
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Profile {
      
      @Id
      @GeneratedValue(strategy= GenerationType.AUTO)
      private Long id;

      @Column(nullable= false)
      private String description;
}
