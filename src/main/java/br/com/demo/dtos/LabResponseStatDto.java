package br.com.demo.dtos;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonGetter;

public class LabResponseStatDto {
	
	private long mutantAdn;
	
	private long humanAdn;
	
	private BigDecimal ratio;
	
    @JsonGetter("count_mutant_dna")
	public long getMutantAdn() {
		return mutantAdn;
	}

	public void setMutantAdn(long mutantAdn) {
		this.mutantAdn = mutantAdn;
	}

    @JsonGetter("count_human_dna")
	public long getHumanAdn() {
		return humanAdn;
	}

	public void setHumanAdn(long count_human_dna) {
		this.humanAdn = count_human_dna;
	}

    @JsonGetter("ratio")
	public BigDecimal getRatio() {
		return ratio;
	}

	public void setRatio(BigDecimal ratio) {
		this.ratio = ratio;
	}


	
	

}
