package com.central.base.mapper;

import com.central.base.model.BaseOrganization;
import com.central.db.mapper.SuperMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author zlt
 * @date 2020-12-23 09:59:30
 */
@Mapper
public interface BaseOrganizationMapper extends SuperMapper<BaseOrganization> {
    /**
     * 分页查询用户列表
     * @param page
     * @param params
     * @return
     */
    List<BaseOrganization> findList(Page<BaseOrganization> page, @Param("p") Map<String, Object> params);
}
