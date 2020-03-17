package com.ejercicio.ejercicio.provider;

import org.apache.olingo.commons.api.edm.FullQualifiedName;
import org.apache.olingo.commons.api.edm.provider.CsdlAbstractEdmProvider;
import org.apache.olingo.commons.api.edm.provider.CsdlEntityType;
import org.apache.olingo.commons.api.ex.ODataException;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EntityProvider extends CsdlAbstractEdmProvider {

	private String namespace;

	private String entityName;

	private String entitySetName;

	private String containerName;

	private FullQualifiedName container;

	private FullQualifiedName entityFqn;

	
	@Override
	public CsdlEntityType getEntityType(FullQualifiedName entityTypeName) throws ODataException {

		if (entityTypeName.equals(entityFqn)) {
			
		}

		return null;
	}

}
