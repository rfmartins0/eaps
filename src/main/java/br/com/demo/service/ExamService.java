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
		int count = 0;
		for (String pattern : patterns) {
			count += this.findOcurrencesHorizontal(dna, pattern);
			count += this.findOcurrencesVertical(dna, pattern);
			count += this.findOcurrencesDiagonal(dna, pattern);
			if (count > 1) {
				return true;
			}
		}
		return false;
	}

	private int findOcurrencesHorizontal(String[] dna, String pattern) {
		int count = 0;
		for (String nitrogenBase : dna) {
			count += tryKMPSearch(pattern, nitrogenBase);
		}
		return count;
	}

	private int findOcurrencesDiagonal(String[] args, String pattern) {
		int count = 0;
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
				count += tryKMPSearch(pattern, line.toString());
			}
		}

		for (int s = 1; s < aux.length; s++) {
			StringBuilder line = new StringBuilder();
			for (int i = aux.length - 1; i >= s; i--) {
				line.append(aux[i][s + aux.length - 1 - i]);
			}
			if (line.length() >= pattern.length()) {
				count += tryKMPSearch(pattern, line.toString());
			}
		}
		return count;
	}

	private int findOcurrencesVertical(String[] adn, String pattern) {
		int count = 0;
		char[][] aux = new char[adn.length][adn[0].length()];
		for (int i = 0; i < adn.length; i++) {
			aux[i] = adn[i].toCharArray();
		}
		for (int i = 0; i < adn.length; i++) {
			StringBuilder line = new StringBuilder();
			for (int x = 0; x < adn.length; x++) {
				line.append(aux[x][i]);
			}
			count += tryKMPSearch(pattern, line.toString());
		}
		return count;
	}

	private int tryKMPSearch(String pattern, String adn) {
		int count = 0;
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
				count++;
				if (count > 1) {
					return count;
				}
				j = lps[j - 1];
			} else if (i < adnLen && pattern.charAt(j) != adn.charAt(i)) {
				if (j != 0)
					j = lps[j - 1];
				else
					i = i + 1;
			}
		}
		return count;
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
