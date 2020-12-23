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
 * @date 2020-05-17 16:22:44
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@TableName("youi_codeitem")
public class YouiCodeitem extends SuperUEntity {
    private static final long serialVersionUID=1L;

        private String itemCaption;
        private String itemGroup;
        private String itemValue;
        private String codemapId;
        private String itemIndex;
    }
