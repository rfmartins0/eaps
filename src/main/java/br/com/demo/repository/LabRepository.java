package br.com.demo.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.demo.domain.LabResultEntity;

public interface LabRepository extends CrudRepository<LabResultEntity, String> {

	public long countByEap(Boolean type);

}
