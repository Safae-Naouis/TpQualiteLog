package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.gestion.banque.dao.BanqueDaoImpl;
import org.gestion.banque.entities.Client;
import org.gestion.banque.entities.Compte;
import org.gestion.banque.entities.Versement;
import org.gestion.banque.metier.IBanqueMetier;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class testaddclt {
	IBanqueMetier metier;
	BanqueDaoImpl dao;

	@BeforeAll
	public void setUp() throws Exception {

		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "applicationContext.xml" });
		metier = (IBanqueMetier) context.getBean("metier");
		dao = (BanqueDaoImpl) context.getBean("dao");
	}

	@Test
	public void testaddclient() {

		Client client = new Client("C5", "AD5");

		metier.addClient(client);

		assertEquals("C5", metier.addClient(client).getNomClient());

	}

	@Test
	public void testversement() {

		Client client = new Client("C5", "AD5");

		metier.versement("1", 500.0, (long) 2);

		Versement operationVersement = new Versement(new Date(), 500.0);

		dao.addOperation(operationVersement, "1", (long) 2);

		Compte compteDuVersement = dao.getCompte("1");
		double nouveauSolde = compteDuVersement.getSolde() + 500.0;

		assertTrue(nouveauSolde == compteDuVersement.getSolde());

	}

}
