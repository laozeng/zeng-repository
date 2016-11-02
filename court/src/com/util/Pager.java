package com.util;

import java.util.List;

/**
 * 分页实体，每一个对象代表一页数据 
 * @author user
 * @param <T>
 */
public class Pager<T> {
	private Integer pageIndex; //当前页码
	private Integer pageSize;  //每页显示的行数
	private Integer totalCount; //总记录数
	private Integer totalPage;  //总页数
	private List<T> result; //每页的结果集
	public Integer getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	public Integer getTotalPage() {
		totalPage = totalCount % pageSize == 0 ? totalCount / pageSize : totalCount / pageSize + 1; 
		return totalPage;
	}
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	public List<T> getResult() {
		return result;
	}
	public void setResult(List<T> result) {
		this.result = result;
	}
}
