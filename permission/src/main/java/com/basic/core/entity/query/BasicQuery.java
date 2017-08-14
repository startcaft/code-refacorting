package com.basic.core.entity.query;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 分页查询基类
 */
public abstract class BasicQuery {

    private int page = 1;
	private int rows = 20;
	private String sidx;
	private String sord = "desc";

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

	public String getSidx() {
		return sidx;
	}

	public void setSidx(String sidx) {
		this.sidx = sidx;
	}

	public String getSord() {
		return sord;
	}

	public void setSord(String sord) {
		this.sord = sord;
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