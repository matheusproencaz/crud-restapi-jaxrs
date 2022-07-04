package serviceDesk.dao;

import java.io.Serializable;
import java.util.List;

public interface IUserDAO<T, PK extends Serializable>{

	void saveOrUpdate(T Users) throws Exception;
	
	void delete(PK id);
	
	T findById(PK id);
	
	
	List<T> findByName(String name);
	
	List<T> findAll();
}
