package com.hibernate.dao;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.jdbc.ReturningWork;
import org.springframework.stereotype.Repository;

@Repository
public class BaseDaoImpl<T> implements IBaseDao<T> {

    @Resource
    protected SessionFactory sessionFactory;

    /**
     * 获得当前事物的session
     *
     * @return  org.hibernate.Session
     */
    public Session getCurrentSession() {
        return this.sessionFactory.getCurrentSession();
    }

    @Override
    public Serializable save(T o) {
        if (o !=  null) {
            return this.getCurrentSession().save(o);
        }
        return null;
    }

    @Override
    public void delete(T o) {
        if (o !=  null) {
            this.getCurrentSession().delete(o);
        }
    }

    @Override
    public void update(T o) {
        if (o !=  null) {
            this.getCurrentSession().update(o);
        }

    }

    @Override
    public void saveOrUpdate(T o) {
        if (o !=  null) {
            this.getCurrentSession().saveOrUpdate(o);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public T get(Class<T> c, Serializable id) {
        return (T) this.getCurrentSession().get(c, id);
    }

    @Override
    @SuppressWarnings("unchecked")
    public T get(String hql) {
        Query q = this.getCurrentSession().createQuery(hql);
        List<T> l = q.list();
        if (l != null && l.size() > 0) {
            return l.get(0);
        }
        return null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T get(String hql, Map<String, Object> params) {
        Query q = this.getCurrentSession().createQuery(hql);
        if (params != null && !params.isEmpty()) {
            for (String key : params.keySet()) {
                q.setParameter(key, params.get(key));
            }
        }
        List<T> l = q.list();
        if (l != null && l.size() > 0) {
            return l.get(0);
        }
        return null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<T> find(String hql) {
        Query q = this.getCurrentSession().createQuery(hql);
        return q.list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<T> find(String hql, Map<String, Object> params) {
        Query q = this.getCurrentSession().createQuery(hql);
        if (params != null && !params.isEmpty()) {
            for (String key : params.keySet()) {
                q.setParameter(key, params.get(key));
            }
        }
        return q.list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<T> find(String hql, int page, int rows) {
        Query q = this.getCurrentSession().createQuery(hql);
        return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<T> find(String hql, Map<String, Object> params, int page, int rows) {
        Query q = this.getCurrentSession().createQuery(hql);
        if (params != null && !params.isEmpty()) {
            for (String key : params.keySet()) {
                q.setParameter(key, params.get(key));
            }
        }
        return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
    }

    @Override
    public Long count(String hql) {
        Query q = this.getCurrentSession().createQuery(hql);
        return (Long) q.uniqueResult();
    }

    @Override
    public Long count(String hql, Map<String, Object> params) {
        Query q = this.getCurrentSession().createQuery(hql);
        if (params != null && !params.isEmpty()) {
            for (String key : params.keySet()) {
                q.setParameter(key, params.get(key));
            }
        }
        return (Long) q.uniqueResult();
    }

    @Override
    public int executeHql(String hql) {
        Query q = this.getCurrentSession().createQuery(hql);
        return q.executeUpdate();
    }

    @Override
    public int executeHql(String hql, Map<String, Object> params) {
        Query q = this.getCurrentSession().createQuery(hql);
        if (params != null && !params.isEmpty()) {
            for (String key : params.keySet()) {
                q.setParameter(key, params.get(key));
            }
        }
        return q.executeUpdate();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Object[]> findBySql(String sql) {
        Query q = this.getCurrentSession().createSQLQuery(sql);
        return q.list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Object[]> findBySql(String sql, int page, int rows) {
        Query q = this.getCurrentSession().createSQLQuery(sql);
        return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Object[]> findBySql(String sql, Map<String, Object> params) {
        Query q = this.getCurrentSession().createSQLQuery(sql);
        if (params != null && !params.isEmpty()) {
            for (String key : params.keySet()) {
                q.setParameter(key, params.get(key));
            }
        }
        return q.list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Object[]> findBySql(String sql, Map<String, Object> params, int page, int rows) {
        Query q = this.getCurrentSession().createSQLQuery(sql);
        if (params != null && !params.isEmpty()) {
            for (String key : params.keySet()) {
                q.setParameter(key, params.get(key));
            }
        }
        return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
    }

    @Override
    public int executeSql(String sql) {
        Query q = this.getCurrentSession().createSQLQuery(sql);
        return q.executeUpdate();
    }

    @Override
    public int executeSql(String sql, Map<String, Object> params) {
        Query q = this.getCurrentSession().createSQLQuery(sql);
        if (params != null && !params.isEmpty()) {
            for (String key : params.keySet()) {
                q.setParameter(key, params.get(key));
            }
        }
        return q.executeUpdate();
    }

    @Override
    public BigInteger countBySql(String sql) {
        Query q = this.getCurrentSession().createSQLQuery(sql);
        return (BigInteger) q.uniqueResult();
    }

    @Override
    public BigInteger countBySql(String sql, Map<String, Object> params) {
        Query q = this.getCurrentSession().createSQLQuery(sql);
        if (params != null && !params.isEmpty()) {
            for (String key : params.keySet()) {
                q.setParameter(key, params.get(key));
            }
        }
        return (BigInteger) q.uniqueResult();
    }

    @Override
    public ResultSet executeQuery(String sql) {
        ResultSet rs = this.getCurrentSession().doReturningWork(
                new ReturningWork<ResultSet>() {
                    @Override
                    public ResultSet execute(Connection connection) throws SQLException {
                        PreparedStatement ps = connection.prepareStatement(sql);
                        return ps.executeQuery();
                    }
                }
        );
        return rs;
    }

    @Override
    public ResultSet executeQuery(String sql, final Map<String, Object> params) {
        ResultSet rs = this.getCurrentSession().doReturningWork(
                new ReturningWork<ResultSet>() {
                    @Override
                    public ResultSet execute(Connection connection) throws SQLException {
                        PreparedStatement ps = connection.prepareStatement(sql);
                        if (params != null && !params.isEmpty()) {
                            int index = 1;
                            for (Object obj : params.values()) {
                                ps.setObject(index++, obj);
                            }
                        }
                        return ps.executeQuery();
                    }
                }
        );
        return rs;
    }

}
