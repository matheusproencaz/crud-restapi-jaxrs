package serviceDesk.dao;

import java.io.Serializable;
import java.util.List;

import serviceDesk.model.User;

public interface IUserDAO<T, PK extends Serializable>{

	User saveOrUpdate(T Users) throws Exception;
	
	void delete(PK id);
	
	T findById(PK id);
	
	
	List<T> findByName(String name);
	
	List<T> findAll();
}
