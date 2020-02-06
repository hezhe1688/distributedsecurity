package com.hezhe.security.distributed.uaa.mapper;

import com.hezhe.security.distributed.uaa.model.Role;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("roleMapper")
public interface RoleMapper {
    int insert(Role record);

    int insertSelective(Role record);

    /**
     * 获取所有的角色信息
     *
     * @return
     */
    public List<Role> selectRoles();

    /**
     * 同时获取role以及对应的permission
     *
     * @return
     */
    public List<Role> selectRolePermission();

    /**
     * 新增role
     */
    public void insertRole(Role role);

    /**
     * 更新中间表
     */
    public void insertRolePermission(@Param("rid") Integer rid, @Param("pid") Integer pid);

    /**
     * 通过数组ID进行一个或多个删除角色数据
     *
     * @param ids
     */
    public void deleteRoleById(@Param("ids") Integer[] ids);

    /**
     * 通过用户数组ID进行一个或多个删除角色权限表的中间数据
     *
     * @param ids
     */
    public void deleteRolePermissionByRid(@Param("ids") Integer[] ids);

    /**
     * 通过用户ID去获取到相应角色的所有ID
     * @param uid
     * @return Integer[]，一个用户可能对应多个角色
     */
    public Integer[] selectRidByUid(@Param("uid") Integer uid);
}
