package labbd.dao;

import java.util.List;

public interface DAO<T> {
	
	public void adicionar(T obj) throws DAOException;
	public void remover(long id) throws DAOException;
	public void atualizar(T obj) throws DAOException;
	public List<T>pesquisar(String s) throws DAOException;

}
