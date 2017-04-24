package com.bosssoft.itfinance.epay.v2.cashierdesk.common.mybatis.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.bosssoft.itfinance.epay.v2.cashierdesk.common.mybatis.dao.IBaseBiz;
import com.bosssoft.itfinance.epay.v2.cashierdesk.common.mybatis.dao.MybatisDao;
import com.bosssoft.itfinance.epay.v2.cashierdesk.common.mybatis.dao.OrderClause;
import com.bosssoft.itfinance.epay.v2.cashierdesk.common.mybatis.dao.WhereClause;
import com.bosssoft.itfinance.epay.v2.cashierdesk.common.mybatis.page.Pagination;

public class BaseBizImpl<T> implements IBaseBiz<T> {

    @Autowired
    public MybatisDao dao;

    protected String mapperKey = "";

    protected Pagination defaultPagination = new Pagination(0, 10000).isGetTotalCount(false);

    public BaseBizImpl(String mapperKey) {
        this.mapperKey = mapperKey;
    }

    public Integer delete(T t) {
        return dao.delete(mapperKey + ".delete", t);
    }

    public T getOne(T t) {
        return (T) dao.get(mapperKey + ".getOne", t);
    }

    public List<T> getList(T t) {
        return (List<T>) dao.getList(mapperKey + ".getList", t, defaultPagination);
    }

    public List<T> getList(T t, Pagination page) {
        return (List<T>) dao.getList(mapperKey + ".getList", t, page);
    }

    public List<T> getLikeList(T t, Pagination page) {
        return (List<T>) dao.getList(mapperKey + ".getLikeList", t, page);
    }

    public Integer save(T t) {
        return dao.save(mapperKey + ".save", t);
    }

    public Integer update(T t) {
        return dao.update(mapperKey + ".update", t);
    }

    public List<T> queryList(WhereClause where) {
        return queryList(where, null, defaultPagination);
    }

    public List<T> queryList(WhereClause where, OrderClause order) {
        return queryList(where, order, defaultPagination);
    }

    public List<T> queryList(WhereClause where, OrderClause order, Pagination page) {
        Map<String, Object> clauseMap = new HashMap<String, Object>();
        clauseMap.put("whereClause", where);
        clauseMap.put("orderClause", order);
        
        if (page == null) {
            return dao.getSqlSession().selectList(mapperKey + ".queryList", clauseMap);
        } else {
            return dao.getSqlSession().selectList(mapperKey + ".queryList", clauseMap, page);
        }
    }

    public Integer queryCount(WhereClause where) {
        Map<String, Object> clauseMap = new HashMap<String, Object>();
        clauseMap.put("whereClause", where);
        return dao.getSqlSession().selectOne(mapperKey + ".queryCount", clauseMap);
    }

	public List<T> getListByIds(List<Long> ids) {
		return dao.getListByIds(mapperKey + ".getListByIds", ids);
	}

	public List<T> getListByIds(List<Long> ids, Pagination page) {
		return dao.getListByIds(mapperKey + ".getListByIds", ids, page);
	}

	public List<T> getListByRelIds(String idColumnName, List<Long> idValues) {
		return dao.getListByRelIds(mapperKey + ".getListByRelIds", idColumnName, idValues);
	}

	public List<T> getListByRelIds(String idColumnName, List<Long> idValues, Pagination page) {
		return dao.getListByRelIds(mapperKey + ".getListByRelIds", idColumnName, idValues, page);
	}
}
