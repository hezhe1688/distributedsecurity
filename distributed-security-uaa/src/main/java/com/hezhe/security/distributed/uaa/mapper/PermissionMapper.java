package com.hezhe.security.distributed.uaa.mapper;

import com.hezhe.security.distributed.uaa.model.Permission;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * @author 贺哲
 * @2020-01-12 13:01
 */
@Repository("permissionMapper")
public interface PermissionMapper {
    int insert(Permission record);

    void insertSelective(Permission record);

    /**
     * 获取所有的资源文件
     *
     * @return
     */
    public List<Permission> selectAllPermission();

    /**
     * 通过数组ID进行一个或多个删除权限数据
     *
     * @param ids
     */
    public void deletePermissionById(@Param("ids") Integer[] ids);

    /**
     * 通过角色的ID进而获取权限的名字，从而获取权限，进行基于permission的权限控制
     *
     * @param rids 一个用户可能有多个角色ID，每个角色可能有多个权限
     *             Set：为什么使用set集合，因为多个角色之间可能含有相同的权限，set可以去重
     * @return
     */
    public Set<String> selectPerNameByRid(@Param("rids") Integer[] rids);

}
