package com.basic.core.entity.query;

import javax.ws.rs.QueryParam;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 基础查询对象，包含page，rows，sort，order，分页查询的几个重要属性，当前页默认1，每页记录数默认20，排序字段，排序方向默认desc
 */
public abstract class BasicQuery {

	@QueryParam("page")
    private int page = 1;									// 当前页
	@QueryParam("size")
	private int rows = 20;									// 每页显示记录数
	@QueryParam("sort")
	private String sort;									// 排序字段
	@QueryParam("order")
	private String order = "desc";							// asc/desc
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}

	public final Map<String,Object> dynamicBuildWhereConditions(List<Condition> conditions){
		{
			Map<String, Object> params = new HashMap<String, Object>();
			for (Condition condition : conditions) {
				//如果没有条件判断，则直接赋值
				if (condition.getPredicate() == null) {
					params.put(condition.getParamName(), condition.getParamValue());
				} else {
					//条件判断成立，且参数值不需要进一步处理
					if (condition.getPredicate().test(this) && condition.getHandler() == null) {
						params.put(condition.getParamName(), condition.getParamValue());
					}
					//条件判断成立，且参数值需要进一步处理
					if (condition.getPredicate().test(this) && condition.getHandler() != null) {
						params.put(condition.getParamName(), condition.getHandler().process(condition.getParamValue()));
					}
				}

			}
			return params;
		}
	}
}