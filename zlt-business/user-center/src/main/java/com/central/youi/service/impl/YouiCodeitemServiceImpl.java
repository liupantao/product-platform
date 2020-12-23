package com.central.youi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.central.common.model.PageResult;
import com.central.common.model.Result;
import com.central.common.service.impl.SuperServiceImpl;
import com.central.youi.mapper.YouiCodeitemMapper;
import com.central.youi.mapper.YouiCodemapMapper;
import com.central.youi.model.YouiCodeitem;
import com.central.youi.model.YouiCodemap;
import com.central.youi.service.IYouiCodeitemService;
import com.zlt.vo.YouiCodeItemVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author zlt
 * @date 2020-05-17 16:22:44
 */
@Slf4j
@Service
public class YouiCodeitemServiceImpl extends SuperServiceImpl<YouiCodeitemMapper, YouiCodeitem> implements IYouiCodeitemService {
    @Autowired
    private YouiCodemapMapper youiCodemapMapper;

    /**
     * 列表
     *
     * @param params
     * @return
     */
    @Override
    public PageResult<YouiCodeitem> findList(Map<String, Object> params) {
        Page<YouiCodeitem> page = new Page<> (MapUtils.getInteger(params, "page"), MapUtils.getInteger(params, "limit"));
        List<YouiCodeitem> list = baseMapper.findList(page, params);
        return PageResult.<YouiCodeitem>builder().data(list).code(0).count(page.getTotal()).build();
    }

    @Override
    public List<YouiCodeitem> findItemByMid(String codemapId) {
        List<YouiCodeitem> list = baseMapper.findItemByMid(codemapId);
        return list;
    }

    @Override
    @Transactional
    public Result saveCodeItem(YouiCodeitem youiCodeitem) {
        QueryWrapper<YouiCodeitem> queryWrapper = new QueryWrapper<> ();
        queryWrapper.eq("item_value", youiCodeitem.getItemValue());
        queryWrapper.eq("codemap_id", youiCodeitem.getCodemapId());
        Integer count = baseMapper.selectCount(queryWrapper);
        if (count > 0) {
            return Result.failed("代码值已经存在");
        }
        baseMapper.insert(youiCodeitem);
        return Result.succeed("操作成功");
    }

    @Override
    public List<YouiCodeItemVo> getCodeItemsByCode(String code) {
        List<YouiCodeItemVo> voList = new ArrayList<>();
        //通过code 查询对应的id
        List<YouiCodemap> codemapByCode = youiCodemapMapper.findCodemapByCode(code);
        if (codemapByCode.isEmpty()) {
            return voList;
        }
        YouiCodemap youiCodemap = codemapByCode.get(0);
        //通过id 查询codeitem
        List<YouiCodeitem> youiCodeitems = baseMapper.findItemByMid(youiCodemap.getId());

        YouiCodeItemVo youiCodeItemVo;

        for (YouiCodeitem youiCodeitem : youiCodeitems) {
            youiCodeItemVo = new YouiCodeItemVo();
            BeanUtils.copyProperties(youiCodeitem, youiCodeItemVo);
            voList.add(youiCodeItemVo);
        }
        return voList;
    }
}
