package com.school.util;

import java.io.Serializable;
import java.util.List;

/**
 * @Author mjz
 * @Date 2022/3/23 15:59
 * @Version 1.0
 */
public class PageListUtil<T> implements Serializable {
    public static final int DEF_PAGE_INDEX=1;
    public static final int DEF_PAGE_SIZE=10;

    /**
     * 当前页
     */
    private int pageIndex;
    /**
     * 页大小
     */
    private int pageSize;
    /**
     * 总记录
     */
    private long totalSize;
    /**
     * 总页数
     */
    private int totalPage;
    /**
     * 分页数据
     */
    private List<T> list;

    public PageListUtil(int pageIndex,int pageSize){
        this.pageIndex=pageIndex;
        this.pageSize=pageSize;
        if(this.pageIndex<=0){
            this.pageIndex=PageListUtil.DEF_PAGE_INDEX;
        }
        if(this.pageSize<=0){
            this.pageSize=PageListUtil.DEF_PAGE_SIZE;
        }
    }
    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(long totalSize) {
        this.totalSize = totalSize;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
