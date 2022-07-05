package serviceDesk.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import serviceDesk.model.Ticket;
import serviceDesk.model.User;

public class JPAUtil {
	
	private EntityManagerFactory factory;
	
	private static JPAUtil instance;

	private JPAUtil() {
		this.factory = Persistence.createEntityManagerFactory("JPAUtil");
	}

	public static synchronized JPAUtil getInstance() {
		if (instance == null) {
			instance = new JPAUtil();
			instance.new PopulatingDatabase(instance.factory).loadScriptSQL();
		}
		return instance;
	}
	
	public EntityManager getEntityManager() {
        EntityManager entityManager = factory.createEntityManager();
        return entityManager;
    }
	
	class PopulatingDatabase {	
		
		private EntityManagerFactory factory;		
	
		public PopulatingDatabase(EntityManagerFactory factory) {
			this.factory = factory;
		}

		public void loadScriptSQL() {
			
			List<User> users = new ArrayList<>();
			User u1 = new User(null, "Joana Pires", "9192-0595", "joana@email.com.br");
			Ticket t1 = new Ticket(null, null, null, null, null, null, u1);
			u1.addTicket(t1);	
			
			User u2 = new User(null, "Marcia Moura", "9192-3593", "marcia@email.com.br");
			Ticket t2 = new Ticket(null, null, null, null, null, null, u2);
			u2.addTicket(t2);
			
			User u3 = new User(null, "Olivia dos Santos", "9194-5596", "rita@email.com.br");
			Ticket t3 = new Ticket(null, null, null, null, null, null, u3);
			u3.addTicket(t3);
			
			User u4 = new User(null, "Bruna Alvez", "9192-6566", "bruninha@email.com.br");
			Ticket t4 = new Ticket(null, null, null, null, null, null, u4);
			u4.addTicket(t4);
			
			User u5 = new User(null, "Marta Rios dos Santos", "9195-3395", "marta@email.com.br");
			Ticket t5 = new Ticket(null, null, null, null, null, null, u5);
			u5.addTicket(t5);
			
			users.addAll(Arrays.asList(u1,u2,u3,u4,u5));
			users.forEach(c -> setDefaultEntity(c));		
		}
		
		private void setDefaultEntity(User user) {
			EntityManager em = factory.createEntityManager();
			em.getTransaction().begin();
			em.persist(user);
			em.getTransaction().commit();
			em.close();		
		}  
	}
}
