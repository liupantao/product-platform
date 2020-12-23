package com.central.base.mapper;

import com.central.base.model.BaseEmploy;
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
 * @date 2020-12-23 09:41:54
 */
@Mapper
public interface BaseEmployMapper extends SuperMapper<BaseEmploy> {
    /**
     * 分页查询用户列表
     * @param page
     * @param params
     * @return
     */
    List<BaseEmploy> findList(Page<BaseEmploy> page, @Param("p") Map<String, Object> params);
}
