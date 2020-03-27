package com.ejercicio.ejercicio.entity;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
*
* @author omar
*/

import java.io.Serializable;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author omar
 */

@Entity
@Table(name = "usr_user_roles", indexes = { @Index(columnList = "role_uuid"), @Index(columnList = "user_uuid") })
public class AssignedRole implements Serializable {

	static final long serialVersionUID = 687991492884005033L;

	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Id
	private UUID uuid;
	@ManyToOne
	@JoinColumn(name = "role_uuid")
	private Role role;
	@ManyToOne
	@JoinColumn(name = "user_uuid")
	private User user;

	@Column(columnDefinition = "boolean default false")
	private Boolean createPermission = Boolean.FALSE;
	@Column(columnDefinition = "boolean default false")
	private Boolean readPermission = Boolean.FALSE;
	@Column(columnDefinition = "boolean default false")
	private Boolean updatePermission = Boolean.FALSE;
	@Column(columnDefinition = "boolean default false")
	private Boolean deletePermission = Boolean.FALSE;

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Boolean getCreatePermission() {
		return createPermission;
	}

	public void setCreatePermission(Boolean createPermission) {
		this.createPermission = createPermission;
	}

	public Boolean getReadPermission() {
		return readPermission;
	}

	public void setReadPermission(Boolean readPermission) {
		this.readPermission = readPermission;
	}

	public Boolean getUpdatePermission() {
		return updatePermission;
	}

	public void setUpdatePermission(Boolean updatePermission) {
		this.updatePermission = updatePermission;
	}

	public Boolean getDeletePermission() {
		return deletePermission;
	}

	public void setDeletePermission(Boolean deletePermission) {
		this.deletePermission = deletePermission;
	}

}
