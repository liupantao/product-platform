package com.central.youi.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.central.common.model.SuperUEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 *
 *
 * @author zlt
 * @date 2020-05-17 16:22:39
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@TableName("youi_codemap")
public class YouiCodemap extends SuperUEntity {
    private static final long serialVersionUID=1L;

        private String capation;
        private String code;
        private String expression;
        private String type;
    }
