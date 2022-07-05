package serviceDesk.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import serviceDesk.Utils.JPAUtil;
import serviceDesk.dao.IUserDAO;
import serviceDesk.model.User;

public class UserDAO implements IUserDAO<User, Long>{

	private EntityManager em = JPAUtil.getInstance().getEntityManager();
	
	public User saveOrUpdate(User user) throws Exception{
		try {
			em.getTransaction().begin();
			
			if(user.getId() == null) {
				em.persist(user);
			} else {
				if(!em.contains(user)) {
					if(em.find(User.class, user.getId()) == null) {
						throw new Exception("Erro ao atualizar local");
					}
				}
				em.merge(user);
			} 
			em.getTransaction().commit();

			return user;
		} finally {
			em.close();	
		}
	}

	public void delete(Long id) {
		em.getTransaction().begin();
		em.remove(em.getReference(User.class, id));
		em.getTransaction().commit();
		em.close();
	}

	public User findById(Long id) {
		return em.find(User.class, id);
	}

	public List<User> findByName(String name) {
		TypedQuery<User> query = em
				.createQuery("from User u where u.name like :name", User.class);		
		query.setParameter("name", "%" + name + "%");
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<User> findAll() {
		return em.createQuery("from User").getResultList();
	}

}
