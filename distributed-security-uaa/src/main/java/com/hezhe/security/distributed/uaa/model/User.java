package com.hezhe.security.distributed.uaa.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author 贺哲
 * @2020-01-12 12:52
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 用户ID
     */
    private Integer id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 用户密码
     */
    private String password;
    /**
     * 用户状态：1——》启用 0——》禁用
     */
    private int state;
    /**
     * 关联角色，role中的ID字段
     */
    private String roleName;


}
