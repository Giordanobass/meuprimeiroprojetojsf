package br.com.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.com.jpautil.JPAUtil;

public class DaoGeneric<E> {

	public void salvar(E entidade) {
		EntityManager entityManager = JPAUtil.getEntityManager();// estabelece o entitymanager chamando jpautil
		EntityTransaction entityTransaction = entityManager.getTransaction();// criar uma transação com o banco
		entityTransaction.begin();// inicia a transação, deixando tudo pronto para a gravar no banco

		entityManager.persist(entidade);// invoca o persist para salvar

		entityTransaction.commit();// faz o commit(entrega) ao banco
		entityManager.close();
	}

	public E merge(E entidade) {// E é de entidade
		EntityManager entityManager = JPAUtil.getEntityManager();// estabelece o entitymanager chamando
		// jpautil.getEntityManager()
		EntityTransaction entityTransaction = entityManager.getTransaction();// criar uma transação com o banco
		entityTransaction.begin();// inicia a transação, deixando tudo pronto para a gravar no banco

		E retorno = entityManager.merge(entidade);// vai atribuir entidade a retorno

		entityTransaction.commit();// faz o commit(entrega) ao banco
		entityManager.close();

		return retorno;
	}

	public void delete(E entidade) {
		EntityManager entityManager = JPAUtil.getEntityManager();// estabelece o entitymanager chamando
		// jpautil.getEntityManager()
		EntityTransaction entityTransaction = entityManager.getTransaction();// criar uma transação com o banco
		entityTransaction.begin();// inicia a transação, deixando tudo pronto para a deletar no banco

		entityManager.remove(entidade);// invoca o remove para deletar

		entityTransaction.commit();// faz o commit(entrega) ao banco
		entityManager.close();
	}

	public void deleteById(E entidade) {

		EntityManager entityManager = JPAUtil.getEntityManager();// estabelece o entitymanager chamando
		// jpautil.getEntityManager()

		EntityTransaction entityTransaction = entityManager.getTransaction();// criar uma transação com o banco
		entityTransaction.begin();// inicia a transação, deixando tudo pronto para a deletar no banco

		Object id = JPAUtil.getPrimaryKey(entidade);
		entityManager.createQuery("delete from " + entidade.getClass().getCanonicalName() + " where id = " + id)
				.executeUpdate();

		entityTransaction.commit();// faz o commit(entrega) ao banco
		entityManager.close();
	}

	public List<E> getListEntity(Class<E> entidade) {
		EntityManager entityManager = JPAUtil.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();

		List<E> retorno = entityManager.createQuery("from " + entidade.getName()).getResultList();

		entityTransaction.commit();
		entityManager.close();

		return retorno;
	}

}
