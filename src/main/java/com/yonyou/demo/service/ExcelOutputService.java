package com.yonyou.demo.service;

import com.yonyou.demo.entity.page.Page;

import javax.servlet.http.HttpServletResponse;

public interface ExcelOutputService {


    public void excelOutput(Page page, HttpServletResponse response);

}
