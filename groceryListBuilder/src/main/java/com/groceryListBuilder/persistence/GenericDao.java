package com.groceryListBuilder.persistence;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import jakarta.persistence.criteria.CriteriaUpdate;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * The  Generic dao.
 *
 * @param <T> the type being passed in(user, recipe, etc)
 *
 * @author Sydney St. Clair
 */
public class GenericDao<T> {
    private Class<T> type;
    private final Logger logger = LogManager.getLogger(this.getClass());

    private final Session session = getSession();
    /**
     * Instantiates a new Generic dao.
     *
     * @param type the type of entity (user, recipe, ect)
     */
    public GenericDao(Class<T> type) {
        this.type = type;
    }

    /**
     * Gets all entities.
     *
     * @return  all the entities
     */
    public List<T> getAll() {


        CriteriaBuilder builder = (CriteriaBuilder) session.getCriteriaBuilder();

        CriteriaQuery<T> query = builder.createQuery(type);
        Root<T> root = query.from(type);
        List<T> list = session.createQuery((CriteriaUpdate) query).getResultList();
        session.close();
        return list;
    }

    /**
     * Gets entity by id.
     *
     * @param <T> the type of entity
     * @param id  the id of entity being retrieved
     * @return the entity by id
     */
    public <T> T getById(int id) {

        T entity = (T)session.get(type, id);
        logger.debug("here is the type: " + type);
        logger.debug("here is the id entered: " + id);
        logger.debug("here is the entity: " + entity);
        session.close();
        return entity;
    }

    /**
     * Delete entity.
     *
     * @param entity the type of entity
     */
    public void delete(T entity) {

        Transaction transaction = session.beginTransaction();
        session.delete(entity);
        transaction.commit();
        session.close();
    }

    /**
     * Insert entity.
     *
     * @param entity the entity being inserted
     * @return the id of the entity being inserted
     */
    public int insert(T entity) {
        int id = 0;

        Transaction transaction = session.beginTransaction();
        id = (int)session.save(entity);
        transaction.commit();
        session.close();
        return id;
    }

    /**
     * Save or update.
     *
     * @param entity the entity being updated
     */
    public void saveOrUpdate(T entity) {

        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(entity);
        transaction.commit();
        session.close();
    }

    /**
     * Find by property equal list.
     *
     * @param propertyName the property name
     * @param value        the value
     * @return a list of entities that match the search criteria
     */
    public List<T> findByPropertyEqual(String propertyName, Object value) {

        CriteriaBuilder builder = (CriteriaBuilder) session.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(type);
        Root<T> root = query.from(type);
        query.select(root).where(builder.equal(root.get(propertyName),value));

        return session.createQuery((CriteriaUpdate) query).getResultList();
    }

    /**
     * Finds entities by multiple properties.
     * Inspired by https://stackoverflow.com/questions/11138118/really-dynamic-jpa-criteriabuilder
     * @param propertyMap property and value pairs
     * @return entities with properties equal to those passed in the map
     *
     *
     */
    public List<T> findByPropertyEqual(Map<String, Object> propertyMap) {

        CriteriaBuilder builder = (CriteriaBuilder) session.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(type);
        Root<T> root = query.from(type);
        List<Predicate> predicates = new ArrayList<Predicate>();
        for (Map.Entry entry: propertyMap.entrySet()) {
            predicates.add(builder.equal(root.get((String) entry.getKey()), entry.getValue()));
        }
        query.select(root).where(builder.and(predicates.toArray(new Predicate[predicates.size()])));

        return session.createQuery((CriteriaUpdate) query).getResultList();
    }

    /**
     * Returns an open session from the SessionFactory
     * @return session
     */
    private Session getSession() {
        return SessionFactoryProvider.getSessionFactory().openSession();
    }
}