package com.central.common.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 实体父类
 *
 * @author zlt
 */
@Setter
@Getter
public class SuperUEntity<T extends Model<?>> extends Model<T> {
    /**
     * 主键ID
     */
    @TableId(type = IdType.ASSIGN_UUID)
    private String  id;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
