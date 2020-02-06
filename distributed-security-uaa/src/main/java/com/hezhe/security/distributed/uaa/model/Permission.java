package com.hezhe.security.distributed.uaa.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author 贺哲
 * @2020-01-12 12:57
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Permission implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 权限ID
     */
    private Integer id;
    /**
     * 权限名
     */
    private String permissionName;
    /**
     * 权限url
     */
    private String url;
}