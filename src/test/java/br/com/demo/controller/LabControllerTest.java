package br.com.demo.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class LabControllerTest {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void testTabelaNaoQuadrada() throws URISyntaxException {
		final String baseUrl = "http://localhost:" + port + "/simian";
		URI uri = new URI(baseUrl);
		String[] input = {"TTTTTTTTTT"};
		HttpEntity<String[]> request = new HttpEntity<>(input);
		ResponseEntity<String> response = this.restTemplate.postForEntity(uri, request, String.class);
		assertThat(response.getBody()).contains("A tabela não é quadrada");
	}
	
	@Test
	public void testEntradasInvalidas() throws URISyntaxException {
		final String baseUrl = "http://localhost:" + port + "/simian";
		URI uri = new URI(baseUrl);
		String[] input = {"X"};
		HttpEntity<String[]> request = new HttpEntity<>(input);
		ResponseEntity<String> response = this.restTemplate.postForEntity(uri, request, String.class);
		assertThat(response.getBody()).contains("Existem alelos (entradas) inválidos");
	}
	
	@Test
	public void testTabelaVazia() throws URISyntaxException {
		final String baseUrl = "http://localhost:" + port + "/simian";
		URI uri = new URI(baseUrl);
		String[] input = {""};
		HttpEntity<String[]> request = new HttpEntity<>(input);
		ResponseEntity<String> response = this.restTemplate.postForEntity(uri, request, String.class);
		assertThat(response.getBody()).contains("Existem entradas nulas na tabela");
	}
	
	@Test
	public void testEntradaVazia() throws URISyntaxException {
		final String baseUrl = "http://localhost:" + port + "/simian";
		URI uri = new URI(baseUrl);
		String[] input = {"TT",""};
		HttpEntity<String[]> request = new HttpEntity<>(input);
		ResponseEntity<String> response = this.restTemplate.postForEntity(uri, request, String.class);
		assertThat(response.getBody()).contains("Existem entradas nulas na tabela");
	}
	
	@Test
	public void testStat() throws URISyntaxException {
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/stats", String.class))
		.contains("{\"count_mutant_dna\":0");
	}

}
