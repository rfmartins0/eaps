package br.com.demo.repository;

import static org.junit.Assert.assertTrue;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.demo.domain.LabResultEntity;

@RunWith(SpringRunner.class)
@DataJpaTest
public class LabRepositoryTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private LabRepository labRepository;

	@Test
	public void testaIdPersistencia() {
		LabResultEntity labResultEntity = new LabResultEntity();
		String adn = "GTAA,GTAA,GTAA,GTAA";
		labResultEntity.setAdn(adn);
		labResultEntity.setEap(true);
		labResultEntity.setHashAdn(DigestUtils.sha256Hex(adn));
		this.entityManager.persistAndFlush(labResultEntity);
		assertTrue(this.labRepository.count() > 0);
		System.out.println(this.labRepository.count());
	}

}