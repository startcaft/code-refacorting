package com.basic.core.entity.query;

/**
 * 查询参数处理器(函数式接口)，接收一个实际的查询参数值，然后做进一步处理，最后返回结果
 */
@FunctionalInterface
public interface ParamValueHandler {

    Object process(Object paramValue);
}
