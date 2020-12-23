package com.central.base.service;

import com.central.base.model.BaseOrganization;
import com.central.common.model.PageResult;
import com.central.common.service.ISuperService;

import java.util.Map;

/**
 *
 *
 * @author zlt
 * @date 2020-12-23 09:59:30
 */
public interface IBaseOrganizationService extends ISuperService<BaseOrganization> {
    /**
     * 列表
     * @param params
     * @return
     */
    PageResult<BaseOrganization> findList(Map<String, Object> params);

    /**
     * 列表视图
     * @param params
     * @return
     */
    PageResult listView(Map<String, Object> params);
}

