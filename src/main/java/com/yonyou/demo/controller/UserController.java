package com.yonyou.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.yonyou.demo.entity.User;
import com.yonyou.demo.service.UserService;
import com.yonyou.demo.utils.ServiceResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;

    @RequestMapping("/queryAllUser")
    public String queryAllUser() {
        List<User> allUser = userService.getAllUser();
        return JSONObject.toJSONString(allUser);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveUser(@RequestBody User user) {

        ServiceResponse<User> userServiceResponse = userService.saveUser(user);

        return JSONObject.toJSONString(userServiceResponse);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateUser(@RequestBody User user) {

        logger.error("入参:", JSONObject.toJSONString(user));
        logger.error("入参:{}", JSONObject.toJSONString(user));
        ServiceResponse<User> userServiceResponse = userService.updateUserInfo(user);
        return JSONObject.toJSONString(userServiceResponse);
    }

    @RequestMapping(value = "/query", method = RequestMethod.GET)
    public String queryUser(@RequestParam(value = "code") String code, @RequestParam(value = "phone") String phoneNumber) {
        logger.info("入参查询参数为:{}", "code:" + code + "--phoneNumber:" + phoneNumber);
        ServiceResponse<User> userServiceResponse = null;
        if (null != code && !code.isEmpty()) {
            userServiceResponse = userService.queryUserByUserCode(code);
//            logger.info("responseCode.code:{},responseCode.value:{}", userServiceResponse.getResponeCode().getCode(), userServiceResponse.getResponeCode().getValue());
        } else if (null != phoneNumber && !phoneNumber.isEmpty()) {
            userServiceResponse = userService.queryUserByPhoneNumber(phoneNumber);
        }
        logger.info("查询结果为:{}", JSONObject.toJSONString(userServiceResponse));

        return JSONObject.toJSONString(userServiceResponse);

    }
}
