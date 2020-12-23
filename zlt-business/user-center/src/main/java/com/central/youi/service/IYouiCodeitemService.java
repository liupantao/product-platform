package com.central.youi.service;

import com.central.common.model.PageResult;
import com.central.common.model.Result;
import com.central.common.service.ISuperService;
import com.central.youi.model.YouiCodeitem;
import com.zlt.vo.YouiCodeItemVo;

import java.util.List;
import java.util.Map;

/**
 *
 *
 * @author zlt
 * @date 2020-05-17 16:22:44
 */
public interface IYouiCodeitemService extends ISuperService<YouiCodeitem> {
    /**
     * 列表
     * @param params
     * @return
     */
    PageResult<YouiCodeitem> findList(Map<String, Object> params);

    List<YouiCodeitem> findItemByMid(String codemapId);

    Result saveCodeItem(YouiCodeitem youiCodeitem);

    List<YouiCodeItemVo> getCodeItemsByCode(String code);

}

