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
		String[] patterns = { "AAAA", "TTTT", "CCCC", "GGGG" };
		for (String pattern : patterns) {
			horizontal(dna, pattern);
			vertical(dna, pattern);
			diagonal(dna, pattern);
		}
		return  this.getCount() > 1;
	}

	private void horizontal(String[] dna, String pattern) {
		if (this.getCount() > 1) {
			return;
		}
		for (String nitrogenBase : dna) {
			KMPSearch(pattern, nitrogenBase);
		}

	}

	private void diagonal(String[] args, String pattern) {
		if (this.getCount() > 1) {
			return;
		}
		char[][] aux = new char[args.length][args[0].length()];
		for (int i = 0; i < args.length; i++) {
			aux[i] = args[i].toCharArray();
		}
		System.out.println("Arrays " + Arrays.toString(aux[0]));
		for (int s = 0; s < aux.length; s++) {
			String a = "";

			for (int i = s; i > -1; i--) {
				a += aux[i][s - i];
			}
			if (a.length() >= pattern.length()) {
				KMPSearch(pattern, a);
				System.out.println(a);
			}

		}
		
		for (int s=1; s<aux.length; s++) {
			String a = "";
		    for (int i=aux.length-1; i>=s; i--) {
		        a += aux[i][s+aux.length-1-i];
		    }
		    if (a.length() >= pattern.length()) {
				KMPSearch(pattern, a);
				System.out.println(a);
			}
		}
		
	}

	private void vertical(String[] args, String pattern) {
		if (this.getCount() > 1) {
			return;
		}
		char[][] aux = new char[args.length][args[0].length()];
		for (int i = 0; i < args.length; i++) {
			aux[i] = args[i].toCharArray();
		}
		for (int i = 0; i < args.length; i++) {
			String a = "";
			for (int x = 0; x < args.length; x++) {
				a += aux[x][i];
			}
			KMPSearch(pattern, a);
		}

	}

	private int count = 0;
	
	public int getCount() {
		return count;
	}

	private int addCount() {
		return count++;
	}

	private void KMPSearch(String pat, String txt) {
		int M = pat.length();
		int N = txt.length();

		int lps[] = new int[M];
		int j = 0;

		computeLPSArray(pat, M, lps);

		int i = 0;
		while (i < N) {
			if (pat.charAt(j) == txt.charAt(i)) {
				j++;
				i++;
			}
			if (j == M) {
				System.out.println("Found pattern " + "at index " + (i - j));
				this.addCount();
				if (this.getCount() > 1) {
					return;
				}
				j = lps[j - 1];
			} else if (i < N && pat.charAt(j) != txt.charAt(i)) {
				if (j != 0)
					j = lps[j - 1];
				else
					i = i + 1;
			}
		}
	}

	private void computeLPSArray(String pat, int M, int lps[]) {
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
