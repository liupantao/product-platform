package com.central.youi.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.central.db.mapper.SuperMapper;
import com.central.youi.model.YouiCodeitem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 *
 *
 * @author zlt
 * @date 2020-05-17 16:22:44
 */
@Mapper
public interface YouiCodeitemMapper extends SuperMapper<YouiCodeitem> {
    /**
     * 分页查询用户列表
     * @param page
     * @param params
     * @return
     */
    List<YouiCodeitem> findList(Page<YouiCodeitem> page, @Param("u") Map<String, Object> params);

    List<YouiCodeitem> findItemByMid(@Param("codemapId") String codemapId);
}
