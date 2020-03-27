package com.ejercicio.ejercicio.entity;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;

/**
 *
 * @author Omar
 */
@Entity
@Table(name = "usr_role", indexes = { @Index(columnList = "name") })
public class Role implements GrantedAuthority, Serializable {

	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Id
	private UUID uuid;
	private String name;

	/**
	 * Full name of the entity with package info
	 */
	private String entityName;

	@OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
	private Set<AssignedRole> users = new HashSet<>();

	public Role() {

	}

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<AssignedRole> getUsers() {
		return users;
	}

	public void setUsers(Set<AssignedRole> users) {
		this.users = users;
	}

	@Override
	public String getAuthority() {
		return name;
	}

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

}
