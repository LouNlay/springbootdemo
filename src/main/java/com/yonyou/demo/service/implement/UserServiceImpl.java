package com.yonyou.demo.service.implement;

import com.alibaba.fastjson.JSONObject;
import com.yonyou.demo.dao.UserDao;
import com.yonyou.demo.entity.User;
import com.yonyou.demo.service.UserService;
import com.yonyou.demo.utils.ServiceResponse;
import com.yonyou.demo.utils.ServiceResponseStatusEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserDao userDao;

    @Override
    public List<User> getAllUser() {
        return userDao.getAllUser();
    }

    @Override
    public ServiceResponse<User> saveUser(User user) {
        ServiceResponse<User> userServiceResponse = new ServiceResponse<>();
        User userByUserCode = userDao.getUserByUserCode(user.getCode());
        if (null != userByUserCode) {
            userServiceResponse.setResponeCode(ServiceResponseStatusEnum.FAILURE);
            userServiceResponse.setErrorMsg("用户编码已存在，请更换用户编码后再试！");
            return userServiceResponse;
        }

        int i = 0;
        try {
            i = userDao.saveUser(user);
            if (1 == i) {
                userServiceResponse.setResponeCode(ServiceResponseStatusEnum.SUCCESS);
                userServiceResponse.setResult(user);
            } else {
                userServiceResponse.setResponeCode(ServiceResponseStatusEnum.FAILURE);

            }
        } catch (Exception e) {
            logger.error("保存用户出错:{}", e);
            userServiceResponse.setResponeCode(ServiceResponseStatusEnum.FAILURE);
            userServiceResponse.setErrorMsg("保存用户出错");
            userServiceResponse.setDetail(JSONObject.toJSONString(e));
        }

        return userServiceResponse;
    }



    @Override
    public ServiceResponse<User> queryUserByUserCode(String userCode) {
        ServiceResponse<User> userInfo = new ServiceResponse<>();
        try {
            User user = userDao.getUserByUserCode(userCode);
            logger.info("根据用户编码查询到的用户信息:{}", JSONObject.toJSONString(user));
            if (null != user) {
                userInfo.setResponeCode(ServiceResponseStatusEnum.SUCCESS);
                userInfo.setResult(user);
            } else {
                userInfo.setResponeCode(ServiceResponseStatusEnum.FAILURE);
                userInfo.setErrorMsg("查询结果为空");
            }
        } catch (Exception e) {
            logger.error("通过用户编码查询用户出错:{}", e);
            userInfo.setResponeCode(ServiceResponseStatusEnum.FAILURE);
            userInfo.setErrorMsg("通过用户编码查询用户出错");
            userInfo.setDetail(JSONObject.toJSONString(e));
        }
        return userInfo;
    }

    @Override
    public ServiceResponse<User> queryUserByPhoneNumber(String phoneNumber) {
        ServiceResponse<User> userInfo = new ServiceResponse<>();
        try {
            User user = userDao.getUserByPhoneNumber(phoneNumber);
            if (null != user) {
                userInfo.setResponeCode(ServiceResponseStatusEnum.SUCCESS);
                userInfo.setResult(user);
            } else {
                userInfo.setResponeCode(ServiceResponseStatusEnum.FAILURE);
                userInfo.setErrorMsg("查询结果为空");
            }
        } catch (Exception e) {
            logger.error("通过手机号查询用户出错:{}", e);
            userInfo.setResponeCode(ServiceResponseStatusEnum.FAILURE);
            userInfo.setErrorMsg("通过手机号查询用户出错");
            userInfo.setDetail(JSONObject.toJSONString(e));
        }
        return userInfo;
    }

    @Override
    public ServiceResponse<User> updateUserInfo(User user) {
        ServiceResponse<User> userServiceResponse = new ServiceResponse<>();

        try {
            userDao.updateUser(user);
            user = queryUserByUserId(user.getId());
            userServiceResponse.setResponeCode(ServiceResponseStatusEnum.SUCCESS);
            userServiceResponse.setResult(user);
        } catch (Exception e) {
            logger.error("更新用户出错");
            userServiceResponse.setResponeCode(ServiceResponseStatusEnum.FAILURE);
            userServiceResponse.setErrorMsg("更新用户信息出错！");
            userServiceResponse.setDetail(JSONObject.toJSONString(e));
        }

        return userServiceResponse;
    }

    @Override
    public User queryUserByUserId(String userId) {
        return userDao.getUserByUserId(userId);
    }
}
