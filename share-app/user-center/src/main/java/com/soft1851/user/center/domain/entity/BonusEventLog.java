package com.soft1851.user.center.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Table;
import java.util.Date;

/**
 * @author su
 * @className BonusEventLog
 * @Description TODO
 * @Date 2020/10/8
 * @Version 1.0
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "bonus_event_log")
public class BonusEventLog {

    /**
     * Id
     */
    private Integer id;
    /**
     * user.id
     */
    private Integer userId;
    /**
     * 积分操作值
     */
    private Integer value;
    /**
     * 发生的事件
     */
    private String event;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 描述
     */
    private String description;
}
