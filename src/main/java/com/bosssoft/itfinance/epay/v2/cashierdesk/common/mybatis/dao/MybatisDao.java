package com.bosssoft.itfinance.epay.v2.cashierdesk.common.mybatis.dao;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bosssoft.itfinance.epay.v2.cashierdesk.common.mybatis.page.Pagination;

@SuppressWarnings("unchecked")
public class MybatisDao extends SqlSessionDaoSupport {
	private final static Logger log = LoggerFactory.getLogger(MybatisDao.class);

	/**
	 * 增加
	 * @param key
	 * @param obj
	 * @return
	 */
	public Integer save(String key,Object obj){
		if(obj==null){
			log.warn("save(String key,Object obj)　obj is null");
			return getSqlSession().update(key);
		}
		return getSqlSession().insert(key, obj);
	}
	/**
	 * 删除
	 * @param key
	 * @param obj
	 * @return
	 */
	public Integer delete(String key,Object obj){
		if(obj==null){
			log.warn("delete(String key,Object obj)　obj is null");
			return getSqlSession().update(key);
		}
		return getSqlSession().delete(key, obj);
	}
	/**
	 * 修改
	 * @param key
	 * @param obj
	 * @return
	 */
	public Integer update(String key,Object obj){
		if(obj==null){
			log.warn("update(String key,Object obj)　obj is null");
			return getSqlSession().update(key);
		}
		return getSqlSession().update(key, obj);
	}
	/**
	 * 根据ｋｅｙ查询　一条记录
	 * @param <T>
	 * @param key
	 * @return
	 */
	
	public <T> T getOne(String key){
		return (T)getSqlSession().selectOne(key);
	}
	/**
	 * 根据ｋｅｙ　和对象字段查询　一条记录
	 * @param <T>
	 * @param key
	 * @param t
	 * @return
	 */
	public <T> T getOne(String key,T t){
		if(t==null){
			log.warn("get(String key,T t)　t is null");
			return getOne( key);
		}
		return (T)getSqlSession().selectOne(key,t);
	}
	/**
	 * 根据ｋｅｙ查询　一条记录
	 * @param <T>
	 * @param key
	 * @return
	 */
	public <T> T get(String key){
		return getOne(key);
	}
	/**
	 * 根据ｋｅｙ　和对象字段查询　一条记录
	 * @param <T>
	 * @param key
	 * @param t
	 * @return
	 */
	public <T> T get(String key,T t){
		return getOne(key,t);
	}
	/**
	 * 根据ｋｅｙ查询列表
	 * @param <T>
	 * @param key
	 * @return
	 */
	public <T> List<T> getList(String key){
		return getSqlSession().selectList(key);
	}
	/**
	 * 根据ｋｅｙ　,　对象属性 和ｐａｇｅ　分页类　　查询列表
	 * @param <T>
	 * @param key
	 * @param t
	 * @param page
	 * @return
	 */
	public <T> List<T> getList(String key,T t,Pagination page){
		if(page==null){
			log.warn("getList(String key,T t,Pagination page)　page is null");
			return getList(key, t);
		}
		page.setList(getSqlSession().selectList(key, t, page));
		return (List<T>)page.getList();
	}
	/**
	 * 根据ｋｅｙ　和　ID列表　查询列表
	 * @param <T>
	 * @param key
	 * @param t
	 * @return
	 */
	public <T> List<T> getList(String key,T t){
		if(t==null){
			log.warn("getList(String key,T t)　t is null");
			return getList( key);
		}
		return getSqlSession().selectList(key,t);
	}
	/**
	 * 根据ｋｅｙ　,ｍａｐ　　查询列表
	 * @param <T>
	 * @param key
	 * @param map
	 * @return
	 */
	public <T> List<T> getListByMap(String key,Map<?, ?> map){
		if(map==null){
			log.warn("getListByMap(String key,Map map)　map is null");
			return getList( key);
		}
		return getSqlSession().selectList(key,map);
	}
	/**
	 * 根据ｋｅｙ　,　ｍａｐ 和ｐａｇｅ　分页类　　查询列表
	 * @param <T>
	 * @param key
	 * @param map
	 * @param page
	 * @return
	 */
	public <T> List<T> getListByMap(String key,Map<?, ?> map,Pagination page){
		if(page==null){
			log.warn("getListByMap(String key,Map map,Pagination page)　page is null");
			return getListByMap( key, map);
		}
		page.setList(getSqlSession().selectList(key,map,page));
		return (List<T>)page.getList();
	}
	/**
	 * 根据ｋｅｙ　执行存储过程
	 * @param key
	 * @param map
	 * @return
	 */
	public Object getProc(String key){
		return getSqlSession().selectList(key);
	}
	/**
	 * 根据ｋｅｙ　和ｍａｐ　执行存储过程
	 * @param key
	 * @param map
	 * @return
	 */
	public Object getProc(String key,Map<?, ?> map){
		if(map==null){
			log.warn("getProc(String key,Map map)　map is null");
			return getProc( key);
		}
		return getSqlSession().selectList(key,map);
	}
	
	public List<Object> test(String...sqls){
		try {
			Map<String, String> map = new HashMap<String, String>();
			for (int i = 0; i < sqls.length; i++) {
				map.put("s"+(i+1), sqls[i]);
			}
			return getSqlSession().selectList("Imall_userMapper.getsql", map);
		} catch (Exception e) {
			log.error("", e);
		}
		return null;
	}

	/**
	 * 根据ｋｅｙ　和　对象属性　查询列表
	 * @param <T>
	 * @param key
	 * @param t
	 * @return
	 */
	public <T> List<T> getListByIds(String key, List<?> ids){
		if (ids == null || ids.size() == 0) {
			return new LinkedList<T>();
		} else {
			return getSqlSession().selectList(key, ids);
		}
	}
	
	/**
	 * 根据ｋｅｙ　和　对象属性　查询列表
	 * @param <T>
	 * @param key
	 * @param t
	 * @return
	 */
	public <T> List<T> getListByIds(String key, List<?> ids,Pagination page){
		if (ids == null || ids.size() == 0) {
			return new LinkedList<T>();
		} else {
			return getSqlSession().selectList(key, ids, page);
		}
	}

	/**
	 * 根据ｋｅｙ　和　对象属性　查询列表
	 * @param <T>
	 * @param key
	 * @param t
	 * @return
	 */
	public <T> List<T> getListByRelIds(String key, String idColumnName, List<?> idValues){
		if (idColumnName == null || idColumnName.trim().equals("") || idValues == null || idValues.size() == 0) {
			return new LinkedList<T>();
		} else {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("idColumnName", idColumnName);
			params.put("idValues", idValues);
			return getSqlSession().selectList(key, params);
		}
	}
	
	/**
	 * 根据ｋｅｙ　和　对象属性　查询列表
	 * @param <T>
	 * @param key
	 * @param t
	 * @return
	 */
	public <T> List<T> getListByRelIds(String key, String idColumnName, List<?> idValues, Pagination page){
		if (idColumnName == null || idColumnName.trim().equals("") || idValues == null || idValues.size() == 0) {
			return new LinkedList<T>();
		} else {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("idColumnName", idColumnName);
			params.put("idValues", idValues);
			return getSqlSession().selectList(key, params, page);
		}
	}
	
	/**
	 * @Title: getCount
	 * @author yejuncai
	 * @date 2016年5月4日 上午9:44:49
	 * @Description: TODO(获取条件查询的数量)
	 * @param key
	 * @param map
	 * @return int    返回类型
	 * @throws
	 */
/*	public int getCount(String key, Map<?, ?> map){
	    int count = 0;
	    Map<String, Object> result = getSqlSession().selectOne(key, map);
	    
	    if(result != null && result.get("count") != null){
	        count = CommUtil.null2Int(result.get("count"));
	    }
	    return count;
	}*/
}
