package com.desafioldm.domain.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity(name="User")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
	
	@Id
	@ApiModelProperty(value = "ID do usuário")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ApiModelProperty(value = "Nome de usuário")
	@NotBlank
	@Column(name = "name", nullable = false, unique = true)
	private String login;

	@ApiModelProperty(value = "Senha de usuário")
	@Column(name = "password", nullable = false)
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String password;
	
	@JsonIgnore
	@CreationTimestamp
	@Column(name = "registration_date", nullable = false, columnDefinition = "datetime")
	private LocalDateTime registrationDate;
	
	@JsonIgnore
	@UpdateTimestamp
	@Column(name = "update_date", nullable = false, columnDefinition = "datetime")
	private LocalDateTime updateDate;

}
