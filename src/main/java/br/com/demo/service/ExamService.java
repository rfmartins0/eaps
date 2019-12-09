package br.com.demo.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExamService {

	@Autowired
	private LabService labService;

	public boolean isSimianSaveResult(String[] dna) {
		boolean result = this.isSimian(dna);
		this.labService.saveLabExam(Arrays.toString(dna), result);
		return result;
	}

	public boolean isSimian(String[] dna) {
		final String[] patterns = { "AAAA", "TTTT", "CCCC", "GGGG" };
		for (String pattern : patterns) {
			this.searchHorizontal(dna, pattern);
			this.searchVertical(dna, pattern);
			this.searchDiagonal(dna, pattern);
		}
		return this.getCount() > 1;
	}

	private void searchHorizontal(String[] dna, String pattern) {
		if (this.getCount() > 1) {
			return;
		}
		for (String nitrogenBase : dna) {
			tryKMPSearch(pattern, nitrogenBase);
		}

	}

	private void searchDiagonal(String[] args, String pattern) {
		if (this.getCount() > 1) {
			return;
		}
		char[][] aux = new char[args.length][args[0].length()];
		for (int i = 0; i < args.length; i++) {
			aux[i] = args[i].toCharArray();
		}
		for (int s = 0; s < aux.length; s++) {
		    StringBuilder line = new StringBuilder();
			for (int i = s; i > -1; i--) {
				line.append(aux[i][s - i]);
			}
			if (line.length() >= pattern.length()) {
				tryKMPSearch(pattern, line.toString());
			}
		}

		for (int s = 1; s < aux.length; s++) {
		    StringBuilder line = new StringBuilder();
			for (int i = aux.length - 1; i >= s; i--) {
				line.append(aux[i][s + aux.length - 1 - i]);
			}
			if (line.length() >= pattern.length()) {
				tryKMPSearch(pattern, line.toString());
			}
		}

	}

	private void searchVertical(String[] args, String pattern) {
		if (this.getCount() > 1) {
			return;
		}
		char[][] aux = new char[args.length][args[0].length()];
		for (int i = 0; i < args.length; i++) {
			aux[i] = args[i].toCharArray();
		}
		for (int i = 0; i < args.length; i++) {
		    StringBuilder line = new StringBuilder();
			for (int x = 0; x < args.length; x++) {
				line.append(aux[x][i]);
			}
			tryKMPSearch(pattern, line.toString());
		}

	}

	private int count = 0;

	public int getCount() {
		return count;
	}

	private void addCount() {
		count++;
	}

	private void tryKMPSearch(String pattern, String adn) {
		int patternLen = pattern.length();
		int adnLen = adn.length();

		int lps[] = new int[patternLen];
		int j = 0;

		this.computeLPS(pattern, patternLen, lps);

		int i = 0;
		while (i < adnLen) {
			if (pattern.charAt(j) == adn.charAt(i)) {
				j++;
				i++;
			}
			if (j == patternLen) {
				this.addCount();
				if (this.getCount() > 1) {
					return;
				}
				j = lps[j - 1];
			} else if (i < adnLen && pattern.charAt(j) != adn.charAt(i)) {
				if (j != 0)
					j = lps[j - 1];
				else
					i = i + 1;
			}
		}
	}

	private void computeLPS(String pat, int M, int lps[]) {
		int len = 0;
		int i = 1;
		lps[0] = 0;

		while (i < M) {
			if (pat.charAt(i) == pat.charAt(len)) {
				len++;
				lps[i] = len;
				i++;
			} else {
				if (len != 0) {
					len = lps[len - 1];
				} else {
					lps[i] = len;
					i++;
				}
			}
		}
	}

}
