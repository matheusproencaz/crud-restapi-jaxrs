package serviceDesk.Utils;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

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
			users.add(new User("Joana Pires", "9192-0595", "joana@email.com.br"));
			users.add(new User("Marcia Moura", "9192-3593", "marcia@email.com.br"));
			users.add(new User("Olivia dos Santos", "9194-5596", "rita@email.com.br"));
			users.add(new User("Bruna Alvez", "9192-6566", "bruninha@email.com.br"));
			users.add(new User("Marta Rios dos Santos", "9195-3395", "marta@email.com.br"));

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
