package com.hezhe.security.distributed.uaa.mapper;

import com.hezhe.security.distributed.uaa.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 贺哲
 * @2020-01-12 12:59
 */
@Repository("userMapper")
public interface UserMapper {

    /**
     * 获取所有的用户数据和角色的名字，进行分页显示
     *
     * @return
     */
    public List<User> getAllPagerUser();

    /**
     * 通过用户名查询User对象
     *
     * @param username
     * @return
     */
    public User getUserByUsername(@Param("username") String username);

    /**
     * 登录
     *
     * @param user
     */
    public void insertSelective(User user);

    /**
     * 插入用户数据，结合这个insertUserRole实现同时更新插入
     *
     * @param user
     */
    public void insertUser(User user);

    /**
     * 更新对应的中间表，通过insertUser获取最新的UID值，和RID
     *
     * @param userId
     * @param roleName
     */
    public void insertUserRole(@Param("userId") Integer userId, @Param("roleName") String roleName);

    /**
     * 通过数组ID进行一个或多个删除用户数据
     *
     * @param ids
     */
    public void deleteUserById(@Param("ids") Integer[] ids);

    /**
     * 通过用户数组ID进行一个或多个删除用户角色表的中间数据
     *
     * @param ids
     */
    public void deleteUserRoleByUid(@Param("ids") Integer[] ids);

    /**
     * 实现选择性修改，当传过来的值为空的时候就不修改，否则就实现修改
     *
     * @param user
     */
    public void updateSelective(User user);

    public int userEditData(User user);

    public int updateUserRole(@Param("userId") int userId, @Param("roleId") String roleId);

    public List<User> getUserByName(@Param("userLikeName") String userLikeName);

}

