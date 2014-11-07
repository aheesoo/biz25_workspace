package com.includesys.sm.dto;

public class PageHelper 
{
	private int page;
	private int pageStart;
	private int pageSize;
	private String searchColumn;
	private String searchString;
	private String state;
	private String start_date;
	private String finish_date;
	private String search_depth1;
	private String search_depth2;
	private String search_type;
	private String sortColumn;
	private String sortType;
	private String year;
	private String month;
	
	/**
	 * 생성자
	 */
	public PageHelper(){
		this(1, 0, 10, "", "");
	}
	
	
	
	public int getPage() {
		return page;
	}



	public void setPage(int page) {
		this.page = page;
	}



	public int getPageStart() {
		return pageStart;
	}



	public void setPageStart(int pageStart) {
		this.pageStart = pageStart;
	}



	public int getPageSize() {
		return pageSize;
	}



	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}



	public String getSearchColumn() {
		return searchColumn;
	}



	public void setSearchColumn(String searchColumn) {
		this.searchColumn = searchColumn;
	}



	public String getSearchString() {
		return searchString;
	}



	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}



	public String getState() {
		return state;
	}



	public void setState(String state) {
		this.state = state;
	}



	public String getStart_date() {
		return start_date;
	}



	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}



	public String getFinish_date() {
		return finish_date;
	}



	public void setFinish_date(String finish_date) {
		this.finish_date = finish_date;
	}


	public String getSearch_depth1() {
		return search_depth1;
	}



	public void setSearch_depth1(String search_depth1) {
		this.search_depth1 = search_depth1;
	}



	public String getSearch_depth2() {
		return search_depth2;
	}



	public void setSearch_depth2(String search_depth2) {
		this.search_depth2 = search_depth2;
	}

	public String getSearch_type() {
		return search_type;
	}



	public void setSearch_type(String search_type) {
		this.search_type = search_type;
	}



	public String getSortColumn() {
		return sortColumn;
	}



	public void setSortColumn(String sortColumn) {
		this.sortColumn = sortColumn;
	}



	public String getSortType() {
		return sortType;
	}



	public void setSortType(String sortType) {
		this.sortType = sortType;
	}



	public String getYear() {
		return year;
	}



	public void setYear(String year) {
		this.year = year;
	}



	public String getMonth() {
		return month;
	}



	public void setMonth(String month) {
		this.month = month;
	}



	public PageHelper(int page, int pageStart, int pageSize, String searchColumn, String searchString)	{
		this.page = page;
		this.pageStart = pageStart;
		this.pageSize = pageSize;
		this.searchColumn = searchColumn;
		this.searchString = searchString;
	}
	
	public PageHelper(int page, int pageStart, int pageSize, String searchColumn, String searchString, String state, String start_date, String finish_date){
		this.page = page;
		this.pageStart = pageStart;
		this.pageSize = pageSize;
		this.searchColumn = searchColumn;
		this.searchString = searchString;
		this.state = state;
		this.start_date = start_date;
		this.finish_date = finish_date;
	}
	
	public PageHelper(int page, int pageStart, int pageSize, String search_depth1, String search_depth2, String search_type)	{
		this.page 			= page;
		this.pageSize 		= pageSize;
		this.pageStart		= pageStart;
		this.search_depth1	= search_depth1;
		this.search_depth2	= search_depth2;
		this.search_type	= search_type;
	}
	
	/**
	 * 
	 * @param page
	 * @param pageStart
	 * @param pageSize
	 * @param searchColumn
	 * @param searchString
	 * @param sortColumn 정렬할 db컬럼 이름
	 * @param sortType 정렬순서('asc' or 'desc')
	 */
	public PageHelper(int page, int pageStart, int pageSize, String searchColumn, String searchString, String sortColumn, String sortType){
		this.page = page;
		this.pageSize = pageSize;
		this.searchColumn = searchColumn;
		this.searchString = searchString;
		this.sortColumn = sortColumn;
		this.sortType = sortType;
	}
	
}
