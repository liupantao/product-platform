package com.central.youi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.central.common.model.PageResult;
import com.central.common.model.Result;
import com.central.common.redis.template.RedisRepository;
import com.central.common.service.impl.SuperServiceImpl;
import com.central.youi.mapper.YouiCodemapMapper;
import com.central.youi.model.YouiCodeitem;
import com.central.youi.model.YouiCodemap;
import com.central.youi.service.IYouiCodeitemService;
import com.central.youi.service.IYouiCodemapService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author zlt
 * @date 2020-05-17 16:22:39
 */
@Slf4j
@Service
public class YouiCodemapServiceImpl extends SuperServiceImpl<YouiCodemapMapper, YouiCodemap> implements IYouiCodemapService {


    @Autowired
    private RedisRepository redisRepository;
    @Autowired
    private IYouiCodeitemService codeitemService;

    private static String GSOFT_CODE_CACHE = "gsoft_code";
    //private static  Long  GSOFT_CODE_CACHE_TIME=24*60*60*7;

    /**
     * 列表
     *
     * @param params
     * @return
     */
    @Override
    public PageResult<YouiCodemap> findList(Map<String, Object> params) {
        Page<YouiCodemap> page = new Page<> (MapUtils.getInteger(params, "page"), MapUtils.getInteger(params, "limit"));
        List<YouiCodemap> list = baseMapper.findList(page, params);
        log.info("-----" + list.size());
        long total = page.getTotal();
        return PageResult.<YouiCodemap>builder().data(list).code(0).count(page.getTotal()).build();
    }

    @Override
    public void updateCache(String id) {

        {
            Map<String, String> carrier_type = findCodeCacheByCode("carrier_type");
            System.out.println("-----");

        }

        YouiCodemap codemap = getById(id);
        List<YouiCodeitem> items = codeitemService.findItemByMid(id);
        redisRepository.setExpire(GSOFT_CODE_CACHE + ":" + codemap.getCode(), items, 24 * 60 * 60 * 7);
        // List<YouiCodeitem> list = (List<YouiCodeitem>) redisRepository.get(GSOFT_CODE_CACHE + ":" + codemap.getId());
    }

    @Override
    public List<YouiCodemap> findCodemapByCode(String code) {
        List<YouiCodemap> list = baseMapper.findCodemapByCode(code);
        if (CollectionUtils.isEmpty(list)) {
            log.info("获取数据字典为空--><--code=" + code);
            return new ArrayList<>();
        }
        return list;
    }

    @Override
    public Map<String, String> findCodeCacheByCode(String code) {
        Map<String, String> cache = new HashedMap (10);
        //1.先从缓存中拿
        List<YouiCodeitem> list = (List<YouiCodeitem>) redisRepository.get(GSOFT_CODE_CACHE + ":" + code);
        if (CollectionUtils.isEmpty(list)) {
            //2.从数据库拿，并放到缓存中
            List<YouiCodemap> codeMaps = findCodemapByCode(code);
            YouiCodemap codeMap = codeMaps.get(0);
            List<YouiCodeitem> items = codeitemService.findItemByMid(codeMap.getId());
            redisRepository.setExpire(GSOFT_CODE_CACHE + ":" + codeMap.getCode(), items, 24 * 60 * 60 * 7);
            list = items;
        }
        for (YouiCodeitem codeitem : list) {
            cache.put(codeitem.getItemValue(), codeitem.getItemCaption());
        }
        return cache;
    }

    @Override
    public Result saveCodeMap(YouiCodemap entity) {
        //查询该代码值是不是已经存在了
        Integer count = baseMapper.selectCount(new QueryWrapper<YouiCodemap> ().eq("code", entity.getCode()));
        if (count >0) {
            return Result.failed("代码已经存在");
        }
        super.saveOrUpdate(entity);
        return Result.succeed("保存成功");
    }

    @Override
    @Transactional
    public Result deleteCodeMap(String codemapId) {
        QueryWrapper<YouiCodeitem> queryWrapper = new QueryWrapper ();
        queryWrapper.eq("codemap_id", codemapId);
        codeitemService.remove(queryWrapper);
        this.removeById(codemapId);
        return Result.succeed("删除成功");
    }
}
