package br.com.demo.service;

import java.math.BigDecimal;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.demo.domain.LabResultEntity;
import br.com.demo.dtos.LabResponseStatDto;
import br.com.demo.repository.LabRepository;

@Service
public class LabService {

	@Autowired
	private LabRepository labRepository;

	public void saveLabExam(String adn, Boolean result) {
		final LabResultEntity labResultEntity = new LabResultEntity();
		labResultEntity.setAdn(adn);
		labResultEntity.setEap(result);
		labResultEntity.setHashAdn(DigestUtils.sha256Hex(adn));
		this.labRepository.save(labResultEntity);
	}

	public LabResponseStatDto getStat() {
		long eaps = this.labRepository.countByEap(true);
		long humans = this.labRepository.countByEap(false);
		LabResponseStatDto labResponseStatDto = new LabResponseStatDto();
		labResponseStatDto.setCount_human_dna(humans);
		labResponseStatDto.setCount_mutant_dna(eaps);
		if (eaps > 0 && humans > 0) {
			BigDecimal ratio = BigDecimal.valueOf(eaps).divide(BigDecimal.valueOf(humans));
			labResponseStatDto.setRatio(ratio);
		}
		return labResponseStatDto;
	}

}
