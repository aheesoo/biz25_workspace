package com.includesys.sm.webUtil;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

import com.includesys.sm.dto.PageHelper;

public class PageNavi 
{
/*
	private String firstImage	= "<img src='/resources/images/pagenavi/bt_pre01.gif' alt=''>";
	private String lastImage	= "<img src='/resources/images/pagenavi/bt_nxt01.gif' alt=''>";
	private String prevImage	= "<img src='/resources/images/pagenavi/bt_pre02.gif' alt=''>";
	private String nextImage	= "<img src='/resources/images/pagenavi/bt_nxt02.gif' alt=''>";
*/	
	private String firstImage	= "<img src='/resources/images/shopmaster/list_first.gif' border='0' align='absmiddle' alt='first'>";
	private String lastImage	= "<img src='/resources/images/shopmaster/list_end.gif' border='0' align='absmiddle' alt='last'>";
	private String prevImage	= "<img src='/resources/images/shopmaster/list_priv.gif' border='0' align='absmiddle' alt='prev'>";
	private String nextImage	= "<img src='/resources/images/shopmaster/list_next.gif' border='0' align='absmiddle' alt='next'>";

	private String method 	= "post";
	private String action 	= "";
	
	private String pageVar	= "page";
	private int totalCount	= 0;
	private int pageSize	= 10;
	private int nowPage		= 1;
	private int numberCount	= 10;
	private HashMap<String, String> parameters = new HashMap<String, String>();

	private String script	= "";
	private String navi		= "";

	
	
	public void setPageHelper(String action, int totalCount, PageHelper pageHelper){
		
		System.out.println("[CALL] PageNavi setPageHelper pageHelper.setPageSize="+pageHelper.getPageSize());

		this.action 	= action;
		this.totalCount = totalCount;
		this.nowPage 	= pageHelper.getPage();
		this.pageSize	= pageHelper.getPageSize();
		this.setParameters("searchColumn", pageHelper.getSearchColumn());
		this.setParameters("searchString", pageHelper.getSearchString());
		this.setParameters("state", pageHelper.getState());
		this.setParameters("start_date", pageHelper.getStart_date());
		this.setParameters("finish_date", pageHelper.getFinish_date());
		this.setParameters("search_depth1", pageHelper.getSearch_depth1());
		this.setParameters("search_depth2", pageHelper.getSearch_depth2());
		this.setParameters("search_type", pageHelper.getSearch_type());
	}

	
	/**
	 * <pre>
	 * &lt;form method='?'&gt;
	 * 기본값 : post
	 * </pre>
	 * @return 폼 메서드
	 */
	public String getMethod()
	{
		return this.method;
	}

	/**
	 * <pre>
	 * &lt;form method='?'&gt;
	 * 기본값 : post
	 * </pre>
	 * @param method 폼 메서드
	 */
	public void setMethod(String method)
	{
		this.method = method;
	}

	/**
	 * &lt;form action='?'&gt;
	 * @return 폼 엑션
	 */
	public String getAction()
	{
		return this.action;
	}
	
	/**
	 * &lt;form action='?'&gt;
	 * @param action 폼 엑션
	 */
	public void setAction(String action)
	{
		this.action = action;
	}
	
	/**
	 * <pre>
	 * &lt;input type='hidden' name='?' value='현재 페이지 번호'&gt;
	 * 기본값 : page
	 * </pre>
	 * @return 전달 페이지 변수명
	 */
	public String getPageVar()
	{
		return this.pageVar;
	}
	
	/**
	 * <pre>
	 * &lt;input type='hidden' name='?' value='현재 페이지 번호'&gt;
	 * 기본값 : page
	 * </pre>
	 * @param pageVar 전달 페이지 변수명
	 */
	public void setPageVar(String pageVar)
	{
		this.pageVar = pageVar;
	}
	
	/**
	 * @return 전체 레코드 카운트
	 */
	public int getTotalCount()
	{
		return this.totalCount;
	}
	
	/**
	 * @param totalCount 전체 레코드 카운트
	 */
	public void setTotalCount(int totalCount)
	{
		this.totalCount = totalCount; 
	}
	
	/**
	 * <pre>
	 * 화면상에 출력되는 레코드의 수
	 * 기본값 : 10
	 * </pre>
	 * @return 페이지 사이즈
	 */
	public int getPageSize()
	{
		return this.pageSize;
	}
	
	/**
	 * <pre>
	 * 화면상에 출력되는 레코드의 수
	 * 기본값 : 10
	 * </pre>
	 * @param pageSize 페이지 사이즈 
	 */
	public void setPageSize(int pageSize)
	{
		this.pageSize = pageSize;
	}	

	/**
	 * <pre>
	 * 페이지 총 갯수
	 * </pre>
	 */	
	public int getPageCount()
	{
		double count = ((this.getTotalCount() - 1) / this.getPageSize()) + 1;
		return (int)Math.round(count);
	}
	
	/**
	 * @return 현재 페이지 번호
	 */
	public int getNowPage()
	{
		return this.nowPage;
	}
	
	/**
	 * @param nowPage 현재 페이지 번호
	 */
	public void setNowPage(int nowPage)
	{
		this.nowPage = nowPage;
	}
	
	/**
	 * <p>페이지네비에 출력할 페이지 번호 갯수 </p>
	 * @return 페이지 번호 갯수
	 */
	public int getNumberCount()
	{
		return this.numberCount;
	}
	
	/**
	 * <p>페이지네비에 출력할 페이지 번호 갯수 </p>
	 * @param numberCount 페이지 번호 갯수
	 */
	public void setNumberCount(int numberCount)
	{
		this.numberCount = numberCount;
	}
	
	public String getParameters(String key)
	{
		return parameters.containsKey(key) ? parameters.get(key) : "";
	}
	
	public void setParameters(String key, String value)
	{
		if(parameters.containsKey(key))
		{
			parameters.remove(key);	
		}

		parameters.put(key, value);			
	}
	
	/**
	 * <p>페이지네비에 사용되는 JavaScript</p>
	 * @return PageNavi Script
	 */
	public String getScript()
	{
		return this.script;
	}
	
	/**
	 * <p>화면상에 출력되는 페이지네비 HTML</p>
	 * @return
	 */
	public String getNavi()
	{
		return this.navi;
	}
	
	/**
	 * 페이지네비에 필요한 HTML 과 JavaScript 생성
	 */
	public void make()
	{
		if(navi.equals(""))
		{
			String value = "";
			String iframe = "<iframe name='hidden_container' style='display:none;'></iframe>";
			String form = "\t\t var form = \"<form name='trans_frm' method='" + this.getMethod() + "' action='" + this.getAction() + " 'target='_parent'>\";";
			
			form += "\n\t\t form += \"<input type='hidden' name='" + this.getPageVar() + "' value=''>\";";
			
			for(Map.Entry<String, String> e : parameters.entrySet())
			{
				if(getAction().equals("get"))
				{
					try{ value = URLEncoder.encode(e.getValue(), "utf-8"); } catch(UnsupportedEncodingException ex){}
				}
				else
				{
					value = e.getValue();
				}
									
				form += "\n\t\t form += \"<input type='hidden' name='" + e.getKey() + "' value='" + value + "'>\";";
			}
			
			form += "\n\t\t form += \"</form>\";";
			
			script  = "<script language='javascript'>";
			script += "\n $(document).ready";
			script += "\n (";
			script += "\n 	function()";
			script += "\n 	{";
			script += "\n" + form + "\n";			
			script += "\n 		var iframe = document.hidden_container;";
			script += "\n 		iframe.document.write(form);";
			script += "\n 		iframe.document.close();";
			script += "\n	}";
			script += "\n );";
			script += "\n";
			script += "\n function goPage(num)";
			script += "\n {";
			script += "\n 	var form = hidden_container.document.trans_frm;";
			script += "\n 	if(num != null) form." + this.getPageVar() + ".value = num;";
			script += "\n 	form.submit();";
			script += "\n }";
			script += "\n </script>";
            
            navi += "\n <div id='paging' class='paginate'>";
            navi += this.getPrev();
            
            int blockPage, x = 1;
            double blockCnt = (this.getNowPage() - 1) / this.getNumberCount();
            blockPage = (int)Math.ceil(blockCnt) * this.getNumberCount() + 1;
            
            while(x <= this.getNumberCount() && blockPage <= this.getPageCount())
            {
            	if(blockPage == this.getNowPage())
            	{
            		navi += String.format("<font class='list_num_now'>%d</font>&nbsp;&nbsp;&nbsp;&nbsp;", blockPage);
            	}
            	else
            	{
            		navi += String.format("<a href='#' onclick='javascript:goPage(%d);' class='list_num'>%d</a>&nbsp;&nbsp;&nbsp;&nbsp;", blockPage, blockPage);
            	}
            	
            	blockPage++;
            	x++;
            }
            
            navi += this.getNext(blockPage);
            navi += "</div>";
            navi = iframe + navi;
		}		
	}
	
	private String getPrev()
	{
		String prev = "";
		prev += String.format("<a style='cursor:pointer' onclick='javascript:goPage(1);'>%s</a>&nbsp;", firstImage);
		
		if(this.getNowPage() < this.getNumberCount())
		{
			prev += String.format("<a style='cursor:pointer'>%s</a>&nbsp;&nbsp;&nbsp;&nbsp;", prevImage);
		}
		else
		{
			double prevCnt = (this.getNowPage() - 1) / this.getNumberCount();
			int prevPage = ((int)Math.ceil(prevCnt) * this.getNumberCount() + 1) - this.getNumberCount();
			prev += String.format("<a style='cursor:pointer' onclick='javascript:goPage(%s);'>%s</a>&nbsp;&nbsp;&nbsp;&nbsp;", prevPage, prevImage);					
		}
		
		prev += "";
					
		return prev;
	}
	
	private String getNext(int blockPage)
	{
		String next = "";
		
		if(blockPage > this.getPageCount())
		{
			next += String.format("<a class='next' style='cursor:pointer'>%s</a>&nbsp;", nextImage);
		}
		else
		{
			double nextCnt = (this.getNowPage() - 1) / this.getNumberCount();
			int nextPage = ((int)Math.ceil(nextCnt) * this.getNumberCount() + 1) + this.getNumberCount();
			next += String.format("<a class='next' style='cursor:pointer' onclick='javascript:goPage(%s);'>%s</a>", nextPage, nextImage);
		}
		
		next += String.format("<a class='next' style='cursor:pointer' onclick='javascript:goPage(%s);'>%s</a>", this.getPageCount(), lastImage);
		next += "";
		
		return next;
	}
}
