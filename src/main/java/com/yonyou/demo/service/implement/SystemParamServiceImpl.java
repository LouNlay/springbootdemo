package com.yonyou.demo.service.implement;

import com.alibaba.fastjson.JSONObject;
import com.yonyou.demo.dao.SystemParamMapper;
import com.yonyou.demo.entity.SystemParam;
import com.yonyou.demo.entity.page.Page;
import com.yonyou.demo.entity.page.PageQueryEntity;
import com.yonyou.demo.service.SystemParamService;
import com.yonyou.demo.utils.ServiceResponse;
import com.yonyou.demo.utils.ServiceResponseStatusEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SystemParamServiceImpl implements SystemParamService {
    private static final Logger logger = LoggerFactory.getLogger(SystemParamServiceImpl.class);

    @Autowired
    private SystemParamMapper sysParamMapper;

    @Override
    public ServiceResponse<SystemParam> querySysParamByCode(String code) {
        ServiceResponse<SystemParam> response = new ServiceResponse<>();

        try {
            SystemParam systemParam = sysParamMapper.queryParamByCode(code);
            response = constructResponse(systemParam);
        } catch (Exception e) {
            logger.error("查询系统参数发生异常:{}", e);
            setErrorMsg(response,e,"查询出错");
        }

        return response;
    }

    @Override
    public ServiceResponse<SystemParam> saveSysParam(SystemParam systemParam) {
        ServiceResponse<SystemParam> response = new ServiceResponse<>();

        try {
            sysParamMapper.saveParam(systemParam);
            response = constructResponse(sysParamMapper.queryParamByCode(systemParam.getCode()));
        } catch (Exception e) {
            logger.error("保存出错:",e);
            setErrorMsg(response,e,"保存出错");
        }

        return response;
    }

    @Override
    public ServiceResponse<SystemParam> updateSysParam(SystemParam systemParam) {
        ServiceResponse<SystemParam> response = new ServiceResponse<>();

        try {
            sysParamMapper.updateParam(systemParam);
            response = constructResponse(sysParamMapper.queryParamByCode(systemParam.getCode()));
        } catch (Exception e) {
            logger.error("更新出错:",e);
            setErrorMsg(response,e,"更新出错");
        }
        return response;
    }

    @Override
    public long getSysParamNumber() {
        return sysParamMapper.countNumber();
    }

    @Override
    public ServiceResponse<SystemParam> deleteSysParam(SystemParam systemParam) {
        ServiceResponse<SystemParam> response = new ServiceResponse<>();

        try {
            boolean success = sysParamMapper.deleteParam(systemParam);
           if(success){
               response.setResponeCode(ServiceResponseStatusEnum.SUCCESS);
           }else {
               response.setResponeCode(ServiceResponseStatusEnum.FAILURE);
               response.setErrorMsg("操作失败");
           }
        } catch (Exception e) {
            logger.error("删除出错:", e);
            setErrorMsg(response,e,"删除出错");
        }
        return response;
    }


    @Override
    public Page<SystemParam> pageQuery(Page page) {
        try {
            int start = (page.getPageIndex()-1)*page.getPageSize();
            int end = start+page.getPageSize();
            PageQueryEntity queryEntity = new PageQueryEntity();
            queryEntity.setPageSize(page.getPageSize());
            queryEntity.setPageStart(start);
            List<SystemParam> systemParamList = sysParamMapper.pageQuery(queryEntity);
            page.setData(systemParamList);
            logger.info("查询结果:{}",JSONObject.toJSONString(page));
        } catch (Exception e) {
            logger.error("分页查询系统参数出错:",e);
        }

        return page;
    }

    public ServiceResponse<SystemParam> constructResponse(SystemParam systemParam) {

        ServiceResponse<SystemParam> response = new ServiceResponse<>();
        try {

            if (null != systemParam) {
                response.setResponeCode(ServiceResponseStatusEnum.SUCCESS);
                response.setResult(systemParam);
            } else {
                response.setResponeCode(ServiceResponseStatusEnum.FAILURE);
                response.setErrorMsg("系统参数为空");
            }

        } catch (Exception e) {
            logger.error("操作系统参数发生异常:{}", e);
            response.setResponeCode(ServiceResponseStatusEnum.FAILURE);
            response.setErrorMsg("操作出错");
            response.setDetail(e.getLocalizedMessage());
        }
        return response;
    }

    private void setErrorMsg( ServiceResponse<SystemParam> response,Exception e,String msg){
        response.setResponeCode(ServiceResponseStatusEnum.FAILURE);
        response.setErrorMsg(msg);
        response.setDetail(e.getLocalizedMessage());

    }
}
