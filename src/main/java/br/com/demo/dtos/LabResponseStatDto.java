package br.com.demo.dtos;

import java.math.BigDecimal;

public class LabResponseStatDto {
	
	private long count_mutant_dna;
	
	private long count_human_dna;
	
	private BigDecimal ratio;
	
	public long getCount_mutant_dna() {
		return count_mutant_dna;
	}

	public void setCount_mutant_dna(long count_mutant_dna) {
		this.count_mutant_dna = count_mutant_dna;
	}

	public long getCount_human_dna() {
		return count_human_dna;
	}

	public void setCount_human_dna(long count_human_dna) {
		this.count_human_dna = count_human_dna;
	}

	public BigDecimal getRatio() {
		return ratio;
	}

	public void setRatio(BigDecimal ratio) {
		this.ratio = ratio;
	}


	
	

}
