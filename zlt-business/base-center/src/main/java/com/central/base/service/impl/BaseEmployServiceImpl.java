package com.central.base.service.impl;

import org.springframework.stereotype.Service;
import com.central.common.model.PageResult;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.central.common.service.impl.SuperServiceImpl;

import java.util.List;
import java.util.Map;
import org.apache.commons.collections4.MapUtils;
import lombok.extern.slf4j.Slf4j;

import com.central.base.model.BaseEmploy;
import com.central.base.mapper.BaseEmployMapper;
import com.central.base.service.IBaseEmployService;

/**
 * 
 *
 * @author zlt
 * @date 2020-12-23 09:41:54
 */
@Slf4j
@Service
public class BaseEmployServiceImpl extends SuperServiceImpl<BaseEmployMapper, BaseEmploy> implements IBaseEmployService {
    /**
     * 列表
     * @param params
     * @return
     */
    @Override
    public PageResult<BaseEmploy> findList(Map<String, Object> params){
        Page<BaseEmploy> page = new Page<>(MapUtils.getInteger(params, "page"), MapUtils.getInteger(params, "limit"));
        List<BaseEmploy> list  =  baseMapper.findList(page, params);
        return PageResult.<BaseEmploy>builder().data(list).code(0).count(page.getTotal()).build();
    }
}
