package labbd.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
	private static JPAUtil instancia;
	private EntityManagerFactory emf;
	
	private JPAUtil(){
		emf = Persistence.createEntityManagerFactory("ps_museu");
		
	}
	
	public static JPAUtil getInstance(){
		if (instancia == null){
			instancia = new JPAUtil();
		}
		return instancia;
	}
	
	public EntityManager getEM(){
		return emf.createEntityManager();
	}
}
