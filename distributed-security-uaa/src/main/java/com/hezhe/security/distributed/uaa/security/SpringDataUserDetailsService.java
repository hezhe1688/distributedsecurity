package com.hezhe.security.distributed.uaa.security;

import com.hezhe.security.distributed.uaa.mapper.PermissionMapper;
import com.hezhe.security.distributed.uaa.mapper.RoleMapper;
import com.hezhe.security.distributed.uaa.mapper.UserMapper;
import com.hezhe.security.distributed.uaa.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @author 贺哲
 * @2020-02-05 12:04
 */
@Service
public class SpringDataUserDetailsService implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private PermissionMapper permissionMapper;

    /**
     * 认证授权，根据账号查询用户信息
     *
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //从数据库中根据用户名查询数据，这个时候要求用户名唯一
        User user = userMapper.getUserByUsername(username);
        //如果没有查询到，就返回为空
        if (user == null) {
            return null;
        }
        //通过用户ID查询所关联的角色ID，可能是多个角色，一个用户对应多个角色
        Integer[] rid = roleMapper.selectRidByUid(user.getId());
        //根据角色ID查询对应的权限名称，一个角色拥有多个权限
        Set<String> permissionName = permissionMapper.selectPerNameByRid(rid);
        //将permissionName转成数组添加进authorities中
        String[] permissionNameArray = new String[permissionName.size()];
        permissionName.toArray(permissionNameArray);
        //security会将用户所输入的密码和userDetails数据库查询出的密码通过密码编码器进行比对投票
        UserDetails userDetails = org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername()) //数据库中取出的用户名
                .password(user.getPassword()) //数据库中取出的密码
                .authorities(permissionNameArray) //数据库中取出的权限
                .build();
        return userDetails;
    }

    /**
     * 随机生成动态盐进行加密，每次加密的结果不同，但是都和123456一样
     * 第一次结果 $2a$10$J4eszMDUTaGdIyd9qwM0xeQJT5Hcij6Eb78uRZfJJJsYusyr7FXLO
     * 第二次结果 $2a$10$G5NDdUn3YGAb8THng3DWQO8DUI4ekSs608WwzdNXeOzaLt/hMB5me
     *
     * @param args
     */
    public static void main(String[] args) {
        //一种基于随机生成salt的根据强大的哈希加密算法。
        String password = BCrypt.hashpw("123456", BCrypt.gensalt());

        boolean checkpw1 = BCrypt.checkpw("123456", "$2a$10$G5NDdUn3YGAb8THng3DWQO8DUI4ekSs608WwzdNXeOzaLt/hMB5me");
        boolean checkpw2 = BCrypt.checkpw("123456", "$2a$10$J4eszMDUTaGdIyd9qwM0xeQJT5Hcij6Eb78uRZfJJJsYusyr7FXLO");

        System.out.println(checkpw1);
        System.out.println(checkpw2);
    }
}
