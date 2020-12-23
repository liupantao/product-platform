package com.central.youi.controller;

import com.central.common.model.PageResult;
import com.central.common.model.Result;
import com.central.youi.model.YouiCodemap;
import com.central.youi.service.IYouiCodemapService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author zlt
 * @date 2020-05-17 16:22:39
 */
@Slf4j
@RestController
@RequestMapping("/codeMap")
@Api(tags = "数据字典")
public class YouiCodemapController {
    @Autowired
    private IYouiCodemapService youiCodemapService;


    /**
     * 列表
     */
    @ApiOperation(value = "查询列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "分页起始位置", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "limit", value = "分页结束位置", required = true, dataType = "Integer")
    })
    @GetMapping("/list")
    public PageResult list(@RequestParam Map<String, Object> params) {
        return youiCodemapService.findList(params);
    }

    /**
     * 查询
     */
    @ApiOperation(value = "查询")
    @GetMapping("/{codemapId}")
    public Result findUserById(@PathVariable String codemapId) {
        YouiCodemap model = youiCodemapService.getById(codemapId);
        return Result.succeed(model, "查询成功");
    }

    /**
     * 新增or更新
     */
    @ApiOperation(value = "保存")
    @PostMapping("/save")
    public Result save(@RequestBody YouiCodemap youiCodemap) {
        return youiCodemapService.saveCodeMap(youiCodemap);
    }

    /**
     * 删除
     */
    @ApiOperation(value = "删除")
    @DeleteMapping("/{codemapId}")
    public Result delete(@PathVariable String codemapId) {
        return youiCodemapService.deleteCodeMap(codemapId);
    }

    /**
     * 同步缓存
     */
    @ApiOperation(value = "同步缓存")
    @PutMapping("/updateCache/{id}")
    public Result updateCache(@PathVariable String id) {
        youiCodemapService.updateCache(id);
        return Result.succeed("保存成功");
    }
}
