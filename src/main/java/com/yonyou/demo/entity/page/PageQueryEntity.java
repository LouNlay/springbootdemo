package com.yonyou.demo.entity.page;

import java.io.Serializable;

public class PageQueryEntity implements Serializable {

    private static final long serialVersionUID = -2319823124874l;
    private int pageSize;
    private int pageStart;

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageStart() {
        return pageStart;
    }

    public void setPageStart(int pageStart) {
        this.pageStart = pageStart;
    }
}
