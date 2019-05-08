package com.yonyou.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.yonyou.demo.entity.SystemParam;
import com.yonyou.demo.entity.page.Page;
import com.yonyou.demo.service.ExcelOutputService;
import com.yonyou.demo.service.SystemParamService;
import com.yonyou.demo.utils.ServiceResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/sysParam")
public class SysParamController {

    private static final Logger logger = LoggerFactory.getLogger(SysParamController.class);
    @Autowired
    private SystemParamService sysParamService;

    @Autowired
    private ExcelOutputService excelOutputService;

    @RequestMapping(value = "/query",method = RequestMethod.GET)
    public String querySysParam(@RequestParam String sysParamCode){

        if (StringUtils.isEmpty(sysParamCode)) {
            return null;
        }
        ServiceResponse<SystemParam> systemParamServiceResponse = sysParamService.querySysParamByCode(sysParamCode);
        logger.info("系统参数查询结果:{}",JSONObject.toJSONString(systemParamServiceResponse));
        return JSONObject.toJSONString(systemParamServiceResponse);
    }

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public String saveSysParam(@RequestBody SystemParam sysParam){

        logger.debug("保存入参:{}",JSONObject.toJSONString(sysParam));
        if (null == sysParam) {
            return "参数信息不能为空！";
        }
        ServiceResponse<SystemParam> systemParamServiceResponse = sysParamService.saveSysParam(sysParam);
        logger.info("系统参数保存结果:{}", JSONObject.toJSONString(systemParamServiceResponse));
        return JSONObject.toJSONString(systemParamServiceResponse);
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public String update(@RequestBody SystemParam sysParam){
        logger.debug("更新入参:{}",JSONObject.toJSONString(sysParam));
        if (null == sysParam) {
            return "参数信息不能为空！";
        }
        ServiceResponse<SystemParam> systemParamServiceResponse = sysParamService.saveSysParam(sysParam);
        logger.info("系统参数更新结果:{}", JSONObject.toJSONString(systemParamServiceResponse));
        return JSONObject.toJSONString(systemParamServiceResponse);
    }

    @RequestMapping("/delete")
    public String delete(@RequestBody SystemParam sysParamm) {

        ServiceResponse<SystemParam> systemParamServiceResponse = sysParamService.deleteSysParam(sysParamm);
        logger.debug("删除结果:{}", JSONObject.toJSONString(systemParamServiceResponse));
        return JSONObject.toJSONString(systemParamServiceResponse);
    }

    @RequestMapping("/pageQuery")
    public String pageQuery(@RequestBody Page<SystemParam> page) {

        logger.info("查询入参：{}",JSONObject.toJSONString(page));
        page = sysParamService.pageQuery(page);
        logger.info("查询结果：{}",JSONObject.toJSONString(page));
        return JSONObject.toJSONString(page);
    }

    @RequestMapping("/excel")
    public void excelOutput(HttpServletRequest request, HttpServletResponse response) {

        Page page = new Page();
        try {
            excelOutputService.excelOutput(page,response);
        } catch (Exception e) {
            logger.error("excel导出时发生异常:{}",e);
        }
    }

}
