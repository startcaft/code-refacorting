package com.basic.core.entity.query;

import java.util.function.Predicate;

/**
 * 表示一个where条件
 */
public class Condition<T extends BaseQuery> {

    private Predicate<T> predicate;
    private String paramName;
    private Object paramValue;
    private ParamValueHandler handler;

    public Predicate<T> getPredicate() {
        return predicate;
    }

    public void setPredicate(Predicate<T> predicate) {
        this.predicate = predicate;
    }

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public Object getParamValue() {
        return paramValue;
    }

    public void setParamValue(Object paramValue) {
        this.paramValue = paramValue;
    }

    public ParamValueHandler getHandler() {
        return handler;
    }

    public void setHandler(ParamValueHandler handler) {
        this.handler = handler;
    }

    /**
     * @param paramName 查询参数名称
     * @param paramValue 查询参数值
     */
    public Condition(String paramName, Object paramValue) {
        this.paramName = paramName;
        this.paramValue = paramValue;
    }

    /**
     * @param predicate 判断查询是否达到要求（一般来说都是参数不为空）
     * @param paramName 查询参数名称
     * @param paramValue 查询参数值
     */
    public Condition(Predicate<T> predicate, String paramName, Object paramValue) {
        this(paramName,paramValue);
        this.predicate = predicate;
    }

    /**
     * @param predicate 判断查询是否达到要求（一般来说都是参数不为空）
     * @param paramName 查询参数名称
     * @param paramValue    查询参数值
     * @param handler  根据传递的参数值做进一步参数值构建（一般用来构建in查询比较常用）
            */
    public Condition(Predicate<T> predicate, String paramName, Object paramValue, ParamValueHandler handler) {
        this(predicate,paramName,paramValue);
        this.handler = handler;
    }
}