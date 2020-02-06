package com.hezhe.security.distributed.uaa.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * @author 贺哲
 * @2020-01-12 12:55
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 角色ID
     */
    private Integer id;
    /**
     * 角色名
     */
    private String roleName;
    /**
     * 角色代码
     */
    private String code;
    /**
     * 角色所关联的权限
     */
    private Set<Permission> permissions = new HashSet<>();
}