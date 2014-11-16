package net.multiremote.server.dao.impl;

import java.util.List;
import java.util.Optional;
import net.multiremote.server.data.AbstractDataEO;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author gaetan
 * @param <T>
 */
public abstract class AbstractDAO<T extends AbstractDataEO> {

	private final Class<T> entityClass;
	
//	@Autowired
	private SessionFactory sessionFactory;
	
	public AbstractDAO(Class<T> entityClass) {
		this.entityClass = entityClass;
	}
	
	private Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	
	protected Query createQuery(String query){
		return getSession().createQuery(query);
	}
	
	protected String getEOName(){
		return entityClass.getSimpleName();
	}
	
	public Optional<T> getById(Integer id){
		return Optional.ofNullable((T) getSession().get(entityClass, id));
	}
	
	protected List<T> findAllEntities(){
		return createQuery("FROM "+getEOName()).list();
	}
	
	protected T saveEntity(T entity){
		return (T) getSession().merge(entity);
	}
	
	protected void removeEntity(T entity){
		getSession().delete(entity);
	}
	
	protected void removeEntityById(Integer id){
		Optional.ofNullable(getById(id)).ifPresent(obj -> getSession().delete(obj));
	}
}
