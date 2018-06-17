package br.unibh.loja.negocio;

import java.util.List;
import java.util.logging.Logger;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import br.unibh.loja.entidades.Categoria;

@Stateless
@LocalBean

public class ServicoCategoria implements DAO<Categoria, Long> {
	@Inject
	EntityManager em;
	@Inject
	private Logger log;

	@Override
	public Categoria insert(Categoria t) throws Exception {
		log.info("Persistindo " + t);
		em.persist(t);
		return t;

	}

	@Override
	public Categoria update(Categoria t) throws Exception {
		log.info("Atualizando " + t);
		return em.merge(t);

	}

	@Override
	public void delete(Categoria t) throws Exception {
		log.info("Removendo " + t);
		Object c = em.merge(t);
		em.remove(c);

	}

	@Override
	public Categoria find(Long k) throws Exception {
		log.info("Encontrando pela chave " + k);
		return em.find(Categoria.class, k);
	}

	@Override
	public List<Categoria> findAll() throws Exception {
		log.info("Encontrando todos os objetos");
		return em.createQuery("from Categoria").getResultList();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Categoria> findByName(String name) throws Exception {
		log.info("Encontrando o " + name);
		return em.createNamedQuery("Categoria.findByName").setParameter("nome", "%" + name + "%").getResultList();
	}

}
