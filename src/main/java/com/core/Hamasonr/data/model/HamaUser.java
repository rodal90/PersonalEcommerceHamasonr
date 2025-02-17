package com.core.Hamasonr.data.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Entity
@Table(name = "USERS")

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Slf4j
public class HamaUser implements Serializable, UserDetails {
	
	@Override
	public int hashCode(){
		
		return Objects.hash(email,
				phoneNumber,
				fullname, 
				address, 
				password, 
				expiryDateAccount, 
				expiryDateCredentials,
				enabled, 
				lockedAccount,
				username,
				roleSet
				);
	}
	
	@Override
	public boolean equals(Object obj) {
		//son los punteros los que se comparan y se ve si apuntan al mismo objeto
				if (this == obj)
					return true;
				//aca se compara un puntero y se verifica si uno esta vacia, si el puntero no tiene objeto, es null.
				if (obj == null)
					return false;
				//aca se compara si la clase del objeto es la misma si no es la misma retorna falso.
				if (getClass() != obj.getClass())
					return false;
				//aqui estamos haciend un casting, estamos convirtiendo el parámetro obj que pasamos
				// en un HamaUser
				HamaUser other = (HamaUser) obj;
				//aca se compara si aunque sean dos objetos distintos, verifica si tienen el mismo user name. 
				return Objects.equals(username, other.username);
		
	}
	
	private static final long serialVersionUID = 1L;
	
	@Id
	private String username;

	private String email;
	private String phoneNumber;
	private String fullname;
	private String address;
	private String password;//asegurarse del algoritmo de encriptación para saber cuantos caractéres hay que guardar.No-60
	
	private LocalDate expiryDateAccount; /* Fecha de expiración de la password */
	private LocalDate expiryDateCredentials; /* Fecha de expiración de los privilegios */
	private Boolean enabled;
	private Boolean lockedAccount; 
	
	

	
	@ManyToMany
	@JoinTable(name="USERS_HAVE_ROLES")
	private Set<HamaRole> roleSet;
	
	public Collection<? extends GrantedAuthority> getAuthorities(){
		
		List<SimpleGrantedAuthority> simpleGrantedAuthorityList = new ArrayList<>();
		
		// obtener los roles del usuario autenticados
				this.getRoleSet().stream().map(x -> x.getRolename())
						.forEach(x -> simpleGrantedAuthorityList.add(new SimpleGrantedAuthority(x)));

				// Show granted authorities
				log.info("Roles from " + this.getUsername() + ": " +

						simpleGrantedAuthorityList.stream().map(x -> x.getAuthority())
								.collect(Collectors.joining("|", "{", "}"))
						);

				return simpleGrantedAuthorityList;
	}
	
	@Override
	public boolean isAccountNonExpired() {

		// return UserDetails.super.isAccountNonExpired();
		return this.getExpiryDateAccount().isAfter(LocalDate.now());
	}

	@Override
	public boolean isAccountNonLocked() {

		// return UserDetails.super.isAccountNonLocked();
		return !this.getLockedAccount();
	}

	@Override
	public boolean isCredentialsNonExpired() {

		// return UserDetails.super.isCredentialsNonExpired();
		return this.getExpiryDateCredentials().isAfter(LocalDate.now());
	}

	@Override
	public boolean isEnabled() {

		// return UserDetails.super.isEnabled();

		return this.getEnabled();
	}
}
