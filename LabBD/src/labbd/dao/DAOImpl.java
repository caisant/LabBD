package labbd.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class DAOImpl<T> implements DAO<T> {

	private Class<T> tipo;
	private String nomeCampoBusca;

	public DAOImpl(Class<T> tp, String nomeCampo) {
		tipo = tp;
		nomeCampoBusca = nomeCampo;
	}

	@Override
	public void adicionar(T obj) throws DAOException {
		try {
			EntityManager em = JPAUtil.getInstance().getEM();
			em.getTransaction().begin();
			;
			em.persist(obj);
			em.getTransaction().commit();
			em.close();
		} catch (Exception e) {
			throw new DAOException(e);
		}

	}

	@Override
	public void remover(long id) throws DAOException {
		try {
			EntityManager em = JPAUtil.getInstance().getEM();
			T obj = em.find(tipo, id);
			em.getTransaction().begin();
			;
			em.remove(obj);
			em.getTransaction().commit();
			em.close();
		} catch (Exception e) {
			throw new DAOException(e);
		}

	}

	@Override
	public void atualizar(T obj) throws DAOException {
		try {
			EntityManager em = JPAUtil.getInstance().getEM();
			em.getTransaction().begin();
			;
			em.merge(obj);
			em.getTransaction().commit();
			em.close();
		} catch (Exception e) {
			throw new DAOException(e);
		}

	}

	@Override
	public List<T> pesquisar(String s) throws DAOException {
		List<T> lista = new ArrayList<T>();
		EntityManager em = JPAUtil.getInstance().getEM();
		String sql = "select e from " + tipo.getName() + " e where e." + nomeCampoBusca + " like :valor";
		TypedQuery<T> qry = em.createQuery(sql, tipo);
		qry.setParameter("valor", "%" + s + "%");
		lista = qry.getResultList();
		em.close();
		return lista;
	}

}
