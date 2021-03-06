package com.central.base.service;

import com.central.base.model.BaseEmploy;
import com.central.common.model.PageResult;
import com.central.common.service.ISuperService;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 *
 *
 * @author zlt
 * @date 2020-12-23 09:41:54
 */
public interface IBaseEmployService extends ISuperService<BaseEmploy> {
    /**
     * 列表
     * @param params
     * @return
     */
    PageResult<BaseEmploy> findList(Map<String, Object> params);
    /**
     * 列表view
     * @param params
     * @return
     */
    PageResult<BaseEmploy> listView(@Param("u")Map<String, Object> params);
}

