package com.wcp.gdufo2o.dao;

import com.wcp.gdufo2o.entity.LocalAuth;
import org.apache.ibatis.annotations.Param;

import java.util.Date;


/**
 * 本地账号认证
 */
public interface LocalAuthDao {

    /**
     * 通过账号和密码查询对应信息，登录用
     * @param username
     * @param password
     * @return
     */
    LocalAuth queryLocalByUserNameAndPwd(@Param("username") String username, @Param("password") String password);

    /**
     * 通过用户查询对应localAuth
     * @param UserId
     * @return
     */
    LocalAuth queryLocalByUserId(@Param("userId") long UserId);

    /**
     * 添加平台账号
     * @param localAuth
     * @return
     */
    int insertLocalAuth(LocalAuth localAuth);

    /**
     * 通过userId，username，password更改密码
     * @param userId
     * @param username
     * @param password
     * @param newPassword
     * @param lastEditTime
     * @return
     */
    int updateLocalAuth(@Param("userId") long userId, @Param("username") String username,
                        @Param("password") String password, @Param("newPassword") String newPassword,
                        @Param("lastEditTime") Date lastEditTime);

}
