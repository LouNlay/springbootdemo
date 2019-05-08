package com.yonyou.demo.service;

import com.yonyou.demo.entity.SystemParam;
import com.yonyou.demo.entity.page.Page;
import com.yonyou.demo.utils.ServiceResponse;

public interface SystemParamService {

    public ServiceResponse<SystemParam> querySysParamByCode(String code);

    public ServiceResponse<SystemParam> saveSysParam(SystemParam systemParam);

    public ServiceResponse<SystemParam> updateSysParam(SystemParam systemParam);

    public ServiceResponse<SystemParam> deleteSysParam(SystemParam systemParam);

    public long getSysParamNumber();

    public Page<SystemParam> pageQuery(Page page);
}
