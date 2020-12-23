package com.central.youi.controller;

import com.central.common.exception.BusinessException;
import com.central.common.model.PageResult;
import com.central.common.model.Result;
import com.central.youi.model.YouiCodeitem;
import com.central.youi.service.IYouiCodeitemService;
import com.zlt.vo.YouiCodeItemVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author zlt
 * @date 2020-05-17 16:22:44
 */
@Slf4j
@RestController
@RequestMapping("/codeItem")
@Api(tags = "数据字典子记录")
public class YouiCodeitemController {
    @Autowired
    private IYouiCodeitemService youiCodeitemService;

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
        return youiCodeitemService.findList(params);
    }

    /**
     * 查询
     */
    @ApiOperation(value = "查询")
    @GetMapping("/{itemId}")
    public Result findUserById(@PathVariable String itemId) {
        YouiCodeitem model = youiCodeitemService.getById(itemId);
        return Result.succeed(model, "查询成功");
    }

    /**
     * 新增or更新
     */
    @ApiOperation(value = "保存")
    @PostMapping("/save")
    public Result save(@RequestBody YouiCodeitem youiCodeitem) {
        return youiCodeitemService.saveCodeItem(youiCodeitem);
    }

    /**
     * 删除
     */
    @ApiOperation(value = "删除")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable("id") String itemId) {
        youiCodeitemService.removeById(itemId);
        return Result.succeed("删除成功");
    }

    @GetMapping("getCodeItemsByCode")
    public Result<List<YouiCodeItemVo>> getCodeItemsByCode(@RequestParam("code") String code) {
        if (StringUtils.isBlank(code)) {
            throw new BusinessException ("code 不能为空");
        }
        List<YouiCodeItemVo> result = youiCodeitemService.getCodeItemsByCode(code);
        return Result.succeed(result, "查询成功");
    }

    @GetMapping("feign/getCodeItemsByCode")
    public List<YouiCodeItemVo> feignGetCodeItemsByCode(@RequestParam("code") String code) {
        if (StringUtils.isBlank(code)) {
            throw new BusinessException ("code 不能为空");
        }
        return youiCodeitemService.getCodeItemsByCode(code);

    }
}
