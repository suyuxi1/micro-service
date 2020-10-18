package com.soft1851.user.center.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @author su
 * @className User
 * @Description TODO
 * @Date 2020/9/29
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
@Builder
public class User implements Serializable {
    private static final long serialVersionUID = -21117904334438215L;


    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    @Column(name = "wx_id")
    private String wxId;

    @Column(name = "wx_nickname")
    private String wxNickname;

    @Column(name = "roles")
    private String roles;

    @Column(name = "avatar_url")
    private String avatarUrl;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    @Column(name = "bonus")
    private Integer bonus;

}

