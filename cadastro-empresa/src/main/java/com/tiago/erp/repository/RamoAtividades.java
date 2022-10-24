package com.tiago.erp.repository;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.tiago.erp.model.RamoAtividade;

public class RamoAtividades implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private EntityManager manager;
	
	public RamoAtividades() {
		
	}
	
	public RamoAtividades(EntityManager manager) {
		this.manager = manager;
	}
	
	//Aqui buscamos no banco de dados usando tecnica quetira tem o JPQL na classe Empresas
	public List<RamoAtividade> pesquisar(String descricao) {
		//Aqui está chamando uma classe utilitaria.
		CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
		
		//Aqui estou inicializando a query.
		CriteriaQuery<RamoAtividade> criteriaQuery = criteriaBuilder.createQuery(RamoAtividade.class);
		Root<RamoAtividade> root = criteriaQuery.from(RamoAtividade.class);
		criteriaQuery.select(root);
		//Aqui e para construir a clasula where.-
		criteriaQuery.where(criteriaBuilder.like(root.get("descricao"), descricao + "%"));
		
		TypedQuery<RamoAtividade> query = manager.createQuery(criteriaQuery);
		
		return query.getResultList();
	}

}
