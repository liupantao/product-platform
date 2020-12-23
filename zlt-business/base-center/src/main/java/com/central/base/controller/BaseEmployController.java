package com.central.base.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

import com.central.base.model.BaseEmploy;
import com.central.base.service.IBaseEmployService;
import com.central.common.model.PageResult;
import com.central.common.model.Result;

/**
 *
 *
 * @author zlt
 * @date 2020-12-23 09:41:54
 */
@Slf4j
@RestController
@RequestMapping("/baseEmploy")
@Api(tags = "")
public class BaseEmployController {
    @Autowired
    private IBaseEmployService baseEmployService;

    /**
     * 列表
     */
    @ApiOperation(value = "查询列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "分页起始位置", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "limit", value = "分页结束位置", required = true, dataType = "Integer")
    })
    @GetMapping
    public PageResult list(@RequestParam Map<String, Object> params) {
        return baseEmployService.findList(params);
    }

    /**
     * 查询
     */
    @ApiOperation(value = "查询")
    @GetMapping("/{id}")
    public Result findUserById(@PathVariable Long id) {
        BaseEmploy model = baseEmployService.getById(id);
        return Result.succeed(model, "查询成功");
    }

    /**
     * 新增or更新
     */
    @ApiOperation(value = "保存")
    @PostMapping
    public Result save(@RequestBody BaseEmploy baseEmploy) {
        baseEmployService.saveOrUpdate(baseEmploy);
        return Result.succeed("保存成功");
    }

    /**
     * 删除
     */
    @ApiOperation(value = "删除")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        baseEmployService.removeById(id);
        return Result.succeed("删除成功");
    }

    /**
     * 列表
     */
    @ApiOperation(value = "查询列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "分页起始位置", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "limit", value = "分页结束位置", required = true, dataType = "Integer")
    })
    @GetMapping("/listView")
    public PageResult listView(@RequestParam Map<String, Object> params) {
        return baseEmployService.listView(params);
    }
}
