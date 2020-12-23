package com.central.youi.service;

import com.central.common.model.PageResult;
import com.central.common.model.Result;
import com.central.common.service.ISuperService;
import com.central.youi.model.YouiCodemap;

import java.util.List;
import java.util.Map;

/**
 * @author zlt
 * @date 2020-05-17 16:22:39
 */
public interface IYouiCodemapService extends ISuperService<YouiCodemap> {
    /**
     * 列表
     *
     * @param params
     * @return
     */
    PageResult<YouiCodemap> findList(Map<String, Object> params);

    void updateCache(String id);

    List<YouiCodemap> findCodemapByCode(String code);

    /**
     * 获取数据字典
     *
     * @param code
     * @return
     */
    Map<String, String> findCodeCacheByCode(String code);

    Result saveCodeMap(YouiCodemap youiCodemap);

    Result deleteCodeMap(String codemapId);
}

