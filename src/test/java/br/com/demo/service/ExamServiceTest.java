package br.com.demo.service;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ExamServiceTest {
	
	
	public boolean test(String[] adn) {
		ExamService examService = new ExamService();
		return examService.isSimian(adn);
	}
	
	@Test
	public void testHuman() {
		String[] adn = {"TT","TT"}; 
		assertEquals(this.test(adn), false);
	}
	
	@Test
	public void testEap() {
		String[] adn = {"TTTT","TTTT","TTTT","TTTT"}; 
		assertEquals(this.test(adn), true);
	}
	
	@Test
	public void testVerticalEap() {
		String[] adn = {"TTCG","TTCA","TTAC","TTCA"}; 
		assertEquals(this.test(adn), true);
	}
	
	@Test
	public void testVerticalHuman() {
		String[] adn = {"TCCG","TTCA","TTAC","TCCA"}; 
		assertEquals(this.test(adn), false);
	}
	
	@Test
	public void testHorizontalEap() {
		String[] adn = {"TTTT","TTTT","TCAC","TCCA"}; 
		assertEquals(this.test(adn), true);
	}
	
	@Test
	public void testHorizontalHuman() {
		String[] adn = {"CTTT","GTTT","TCAC","TCCA"}; 
		assertEquals(this.test(adn), false);
	}
	
	@Test
	public void testDiagonalHuman() {
		String[] adn = {"CTTT","GTTT","TTAC","TCCA"}; 
		assertEquals(this.test(adn), false);
	}
	
	@Test
	public void testDiagonalEap() {
		String[] adn = {"TTTT","GTTT","TTAC","TCCA"}; 
		assertEquals(this.test(adn), true);
	}

}
