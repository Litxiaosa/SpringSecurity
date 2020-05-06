package com.springsecurityjwtdemo.demo.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Descript: 用户
 * @author: xiaosa
 * @create: 2020-05-03 14:50
 */
@Data
@Entity
@Table(name = "user")
public class User implements Serializable {

    private static final long serialVersionUID = 869718755613973374L;

    /**
     * 主键id
     */
    @Column(name = "id")
    private Long id;


    /**
     * 名称
     */
    @Column(name = "user_name")
    private String userName;

    /**
     * 密码
     */
    @Column(name = "password")
    private String password;

    /**
     * 角色
     */
    @Column(name = "role")
    private String role;
}

