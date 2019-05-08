package com.yonyou.demo.dao;

import com.yonyou.demo.entity.SystemParam;
import com.yonyou.demo.entity.page.PageQueryEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface SystemParamMapper {

    public SystemParam queryParamByCode(String paramCode);

    public boolean saveParam(SystemParam systemParam);

    public boolean updateParam(SystemParam systemParam);

    public boolean deleteParam(SystemParam systemParam);

    public long countNumber();

    public List<SystemParam> pageQuery(PageQueryEntity page);
}
