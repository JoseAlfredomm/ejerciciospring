package com.ejercicio.ejercicio.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.userdetails.UserDetails;

import com.ejercicio.ejercicio.utils.utilclasses.UserType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.UUID;
import javax.persistence.Index;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Omar
 */

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "tbl_user")
public class User implements UserDetails, Serializable {
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Id
	private UUID uuid;

	private String name;

	private String userName;

	private String password;

	@ManyToOne(fetch = FetchType.LAZY)
	private User owner;

	@Enumerated(EnumType.STRING)
	private UserType type;

	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_uuid")
	private Set<AssignedRole> roles = new HashSet<>();

	private String pin;

	@ManyToOne(fetch = FetchType.LAZY)
	private User parent;

	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent")
	private Set<User> children = new HashSet<>();

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String name) {
		this.userName = name;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<AssignedRole> getRoles() {
		return roles;
	}

	public void setRoles(Set<AssignedRole> roles) {
		this.roles = roles;
	}

	/**
	 * Asigna un rol de acceso a un tipo de objeto con el CRUD a False
	 * 
	 * @param role
	 */
	public void addRole(Role role) {

		AssignedRole assignedRole = new AssignedRole();

		assignedRole.setUuid(UUID.randomUUID());

		assignedRole.setRole(role);
		assignedRole.setUser(this);

		assignedRole.setCreatePermission(Boolean.FALSE);
		assignedRole.setReadPermission(Boolean.FALSE);
		assignedRole.setUpdatePermission(Boolean.FALSE);
		assignedRole.setDeletePermission(Boolean.FALSE);

		role.getUsers().add(assignedRole);
		this.getRoles().add(assignedRole);

	}

	public void removeRole(Role role) {
		for (AssignedRole assignedRole : this.getRoles()) {
			if (assignedRole.getRole().getName().equals(role.getName())) {
				this.getRoles().remove(assignedRole);
			}
		}
	}

	@Override
	public int hashCode() {
		int hash = 3;
		hash = 67 * hash + Objects.hashCode(this.uuid);
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final User other = (User) obj;
		if (!Objects.equals(this.uuid, other.uuid)) {
			return false;
		}
		return true;
	}

	@Override
	@JsonIgnoreProperties("users")
	public Collection<Role> getAuthorities() {
		Set<Role> roles = new HashSet<>();
		for (AssignedRole role : this.getRoles()) {
			roles.add(role.getRole());
		}
		return roles;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	/**
	 * @return the pin
	 */
	public String getPin() {
		return pin;
	}

	/**
	 * @return the type
	 */
	public UserType getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(UserType type) {
		this.type = type;
	}

	/**
	 * @param pin
	 *            the pin to set
	 */
	public void setPin(String pin) {
		this.pin = pin;
	}

	/**
	 * @return the owner
	 */
	public User getOwner() {
		return owner;
	}

	/**
	 * @param owner
	 *            the owner to set
	 */
	public void setOwner(User owner) {
		this.owner = owner;
	}

	/**
	 * return true if contain rol
	 * 
	 * @param searchedRoles
	 *            list of roles
	 * @return
	 */
	public boolean hasRole(String[] searchedRoles) {
		boolean result = false;
		for (AssignedRole assignedRole : this.roles) {
			String userRole = assignedRole.getRole().getName();
			for (String roleString : searchedRoles) {
				if (roleString.equals(userRole)) {
					result = true;
					break;
				}
			}

			if (result) {
				break;
			}
		}

		return result;
	}

	@Override
	public String getUsername() {
		return this.userName;
	}

	public User getParent() {
		return parent;
	}

	public void setParent(User parent) {
		this.parent = parent;
	}

	public Set<User> getChildren() {
		return children;
	}

	public void setChildren(Set<User> children) {
		this.children = children;
	}

}
