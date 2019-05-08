package com.yonyou.demo.service;

import com.yonyou.demo.entity.User;
import com.yonyou.demo.utils.ServiceResponse;

import java.util.List;

public interface UserService {
    public List<User> getAllUser();
    public User queryUserByUserId(String userId);
    public ServiceResponse<User> saveUser(User user);
    public ServiceResponse<User> updateUserInfo(User user);
    public ServiceResponse<User> queryUserByUserCode(String userCode);
    public ServiceResponse<User> queryUserByPhoneNumber(String phoneNumber);

}
