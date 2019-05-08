package com.yonyou.demo.dao;

import com.yonyou.demo.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@Mapper
public interface UserDao {

    public List<User> getAllUser();

    public User getUserByUserId(String userId);

    public User getUserByUserCode(String userCode);

    public User getUserByPhoneNumber(String phoneNumber);

    public int saveUser(User user);

    public int updateUser(User user);

}
