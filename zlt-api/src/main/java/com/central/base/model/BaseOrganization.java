package com.central.base.model;

import com.central.common.model.SuperEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import java.util.Date;

/**
 *
 *
 * @author zlt
 * @date 2020-12-23 09:59:30
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@TableName("base_organization")
public class BaseOrganization extends SuperEntity {
    private static final long serialVersionUID=1L;

        private String name;
        private String shortName;
        private String parentId;
        private String deep;
        private String status;
        private String deleteFlag;
    }
