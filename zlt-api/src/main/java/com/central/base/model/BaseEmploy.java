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
 * @date 2020-12-23 09:41:54
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@TableName("base_employ")
public class BaseEmploy extends SuperEntity {
    private static final long serialVersionUID=1L;

        private String name;
        private String phone;
        private String status;
        private String organizationId;
        private String deleteFlag;
    }
