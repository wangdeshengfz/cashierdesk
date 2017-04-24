package com.bosssoft.itfinance.epay.v2.cashierdesk.common.mybatis.dao;

import java.util.List;

import com.bosssoft.itfinance.epay.v2.cashierdesk.common.mybatis.page.Pagination;

public interface IBaseBiz<T> {

    public Integer save(T t);

    public Integer update(T t);

    public Integer delete(T t);

    public T getOne(T t);

    public List<T> getList(T t);

    public List<T> getList(T t, Pagination page);

    public List<T> getLikeList(T t, Pagination page);

    public List<T> queryList(WhereClause where);

    public List<T> queryList(WhereClause where, OrderClause order);

    public List<T> queryList(WhereClause where, OrderClause order, Pagination page);

    public Integer queryCount(WhereClause where);

	public List<T> getListByIds(List<Long> ids);

	public List<T> getListByIds(List<Long> ids, Pagination page);

	public List<T> getListByRelIds(String idColumnName, List<Long> idValues);

	public List<T> getListByRelIds(String idColumnName, List<Long> idValues, Pagination page);
}
