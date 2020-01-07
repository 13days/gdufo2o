package com.wcp.gdufo2o.dao;

import java.util.List;

import com.wcp.gdufo2o.entity.Area;

/**
 *   区域信息管理
 * @author WuChuPeng
 *
 */
public interface AreaDao {
	/**
	 *   区域列表查询
	 * @return
	 */
	List<Area> queryArea();
}
