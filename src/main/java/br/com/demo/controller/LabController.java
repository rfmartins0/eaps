package br.com.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.demo.dtos.LabResponseDto;
import br.com.demo.dtos.LabResponseStatDto;
import br.com.demo.exception.ValidateException;
import br.com.demo.service.ExamService;
import br.com.demo.service.LabService;

@RestController
public class LabController {
	
	@Autowired
	private LabService labService;

	@Autowired
	private ExamService examService;

	@PostMapping(path = "/simian", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LabResponseDto> examSimian(@RequestBody final String[] dna) {
        this.validate(dna);
		boolean result = examService.isSimianSaveResult(dna);
		LabResponseDto labResponseDto = new LabResponseDto();
		labResponseDto.setSimian(result);
		if (result) {
			return new ResponseEntity<LabResponseDto>(labResponseDto, HttpStatus.FORBIDDEN);
		}
		return ResponseEntity.ok(labResponseDto);
	}

	@GetMapping(path = "/stats", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LabResponseStatDto> getStats() {
		return ResponseEntity.ok(labService.getStat());
	}
	
	private void validate(String[] adn) {
		long size = adn.length;
		if (adn == null || size < 1) {
			throw new ValidateException("O DNA não é válido. Verifique se ele é nulo ou está vazio.");
		}
        String pattern = "(A|C|T|G)*";
		for (String baseNitrogen : adn) {
			if (baseNitrogen == null || baseNitrogen.isEmpty()) {
				throw new ValidateException("O DNA não é válido. Existem entradas nulas na tabela.");
			}
			if (baseNitrogen.length() != size) {
				throw new ValidateException("O DNA não é válido. A tabela não é quadrada (NxN).");
			}
			if (!baseNitrogen.matches(pattern)) {
				throw new ValidateException("O DNA não é válido. Existem alelos (entradas) inválidos.");
	        }
		}	
	}

}