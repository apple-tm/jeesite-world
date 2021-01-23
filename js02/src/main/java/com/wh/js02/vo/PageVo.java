package com.wh.js02.vo;

import java.util.List;

public class PageVo<T> {

    private Integer pageNo;

    private Integer pageSize;
    // 总的记录数
    private Integer total;
    // 总的页数
    private Integer totalPage;

    private T listBody;

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public T getListBody() {
        return listBody;
    }

    public void setListBody(T listBody) {
        this.listBody = listBody;
    }
}
