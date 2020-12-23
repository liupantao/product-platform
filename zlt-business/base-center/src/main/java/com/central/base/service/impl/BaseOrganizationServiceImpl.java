package com.central.base.service.impl;

import org.springframework.stereotype.Service;
import com.central.common.model.PageResult;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.central.common.service.impl.SuperServiceImpl;

import java.util.List;
import java.util.Map;
import org.apache.commons.collections4.MapUtils;
import lombok.extern.slf4j.Slf4j;

import com.central.base.model.BaseOrganization;
import com.central.base.mapper.BaseOrganizationMapper;
import com.central.base.service.IBaseOrganizationService;

/**
 *
 *
 * @author zlt
 * @date 2020-12-23 09:59:30
 */
@Slf4j
@Service
public class BaseOrganizationServiceImpl extends SuperServiceImpl<BaseOrganizationMapper, BaseOrganization> implements IBaseOrganizationService {
    /**
     * 列表
     * @param params
     * @return
     */
    @Override
    public PageResult<BaseOrganization> findList(Map<String, Object> params){
        Page<BaseOrganization> page = new Page<>(MapUtils.getInteger(params, "page"), MapUtils.getInteger(params, "limit"));
        List<BaseOrganization> list  =  baseMapper.findList(page, params);
        return PageResult.<BaseOrganization>builder().data(list).code(0).count(page.getTotal()).build();
    }

    /**
     * 列表视图
     *
     * @param params
     * @return
     */
    @Override
    public PageResult listView(Map<String, Object> params) {
        Page<BaseOrganization> page = new Page<>(MapUtils.getInteger(params, "page"), MapUtils.getInteger(params, "limit"));
        List<BaseOrganization> list  =  baseMapper.findList(page, params);
        return PageResult.<BaseOrganization>builder().data(list).code(0).count(page.getTotal()).build();
    }
}
