
package com.kth.common.util;

public final class PageNavi {
	
	// 처음, 끝, 이전, 다음 이미지
	String _firstImage;
	String _lastImage;
	String _backImage;
	String _nextImage;	

	//----------------------------------------------------------------------------------------------------//

	/**
	 * 생성자
	 */
	public PageNavi() {
		
		// 이미지들이 각 폴더에 퍼져 있어 
		// 하나의 이미지로 통일하기 위해 
		// 강제적으로 이미지를 고정시킨다.
		_firstImage	= "/images/common/contents/event/ic_pprev.gif";
		_lastImage	= "/images/common/contents/event/ic_nnext.gif";
		_backImage	= "/images/common/contents/event/ic_prev.gif";
		_nextImage	= "/images/common/contents/event/ic_next.gif";
		
	}

	//----------------------------------------------------------------------------------------------------//
	
	/**
	 * 전체 목록 수와 한 페이지에 보여질 목록 수를 이용하여 전체 페이지 수를 구함
	 * @param rTotCount : 전체 목록 수
	 * @param rPageSize : 한 페이지에 보여질 목록 수
	 * @return : 전체 페이지 수
	 */
	public int getPageCount(int rTotCount, int rPageSize) {
    
		int pageCount  = 0;
		int extraCount = 0;
		
		try {
			pageCount  = rTotCount / rPageSize;
			extraCount = rTotCount % rPageSize;

			if (extraCount > 0)
				pageCount += 1;      
   
		} catch (Exception ex) {
			
		}
    
		return (pageCount < 1 ? 1 : pageCount);
			
	}

	//----------------------------------------------------------------------------------------------------//

	/**
	 * 페이지 네비게이션을 구현하여 문자열 형태로 반환<br>
	 * 현재 페이지 인덱스의 변수명을 지정하지 않는다.<br>
	 * 무조건 [curPage]라는 변수명으로 현재 페이지 인덱스를 처리한다.<br>
	 * 상하위 페이지 관계에 있지 않거나 둘중 한 곳에서만 페이징 처리를 하는 경우<br>
	 * 주로 사용한다.
	 * @param rPageSize         : 한 페이지에 보여줄 목록 수 
	 * @param rPageGroup        : 한번에 보여지는 페이지 이동 링크 (1~10, 1~5페이지) 
	 * @param rTotCount         : 전체 목록 수
	 * @param rPageCount        : 전체 페이지 수
	 * @param rCurPage          : 현재 페이지 인덱스
	 * @param rUrlPage          : 이동할 페이지 
	 * @param rBackImage        : 이전 이미지
	 * @param rNextImage        : 다음 이미지
	 * @param rFirstImage       : 맨 처음 이미지
	 * @param rLastImage        : 맨 끝 이미지
	 * @param rSelectForeColor  : 현재 선택된 페이지 넘버 색상
	 * @param rDefaultForeColor : 현재 선택되지 않은 페이지 넘버 색상
	 * @return : 완성된 페이지 네비게이션 태그 (Table 타입)
	 */
	public String drawPageNavi(int rPageSize, int rPageGroup, int rTotCount,
			int rPageCount, int rCurPage, String rUrlPage,
			/*String rParams,*/ String rCurPageName,
			String rBackImage, String rNextImage,
			String rFirstImage, String rLastImage,
			String rSelectForeColor, String rDefaultForeColor) {
		
		return this.drawPageNavi(rPageSize, rPageGroup, rTotCount,
								  rPageCount, rCurPage, rUrlPage,
								  /*rParams,*/ rCurPageName,
								  rBackImage, rNextImage,
								  rFirstImage, rLastImage,
								  rSelectForeColor, rDefaultForeColor, "");
		
	}	
	
	//----------------------------------------------------------------------------------------------------//
	
	/**
	 * 페이지 네비게이션을 구현하여 문자열 형태로 반환<br>
	 * 현재 페이지 인덱스의 변수명을 지정할 수 있다.<br>
	 * 주로 상하위 관계에 있는 페이지간에 각기 페이지 네비게이션을 가지는 경우<br>
	 * 사용한다. 현재 페이지 인덱스를 관리하는 변수명을 별도로 지정할 수 있어서<br>
	 * 페이지간의 현재 페이지 인덱스 변수간의 충돌을 방지하여 각각 제대로 된<br>
	 * 페이지 네비게이션을 구현할 수 있다.<br>
	 * @param rPageSize         : 한 페이지에 보여줄 목록 수 
	 * @param rPageGroup        : 한번에 보여지는 페이지 이동 링크 (1~10, 1~5페이지) 
	 * @param rTotCount         : 전체 목록 수
	 * @param rPageCount        : 전체 페이지 수
	 * @param rCurPage          : 현재 페이지 인덱스
	 * @param rUrlPage          : 이동할 페이지 
	 * @param rBackImage        : 이전 이미지
	 * @param rNextImage        : 다음 이미지
	 * @param rFirstImage       : 맨 처음 이미지
	 * @param rLastImage        : 맨 끝 이미지
	 * @param rSelectForeColor  : 현재 선택된 페이지 넘버 색상
	 * @param rDefaultForeColor : 현재 선택되지 않은 페이지 넘버 색상
	 * @param rTableStyle 		 : 테이블의 클래스나 스타일의 풀텍스트 	 
	 * @return : 완성된 페이지 네비게이션 태그 (Table 타입)
	 */
	public String drawPageNavi(int rPageSize, int rPageGroup, int rTotCount,
								int rPageCount, int rCurPage, String rUrlPage,
								/*String rParams,*/ String rCurPageName,
								String rBackImage, String rNextImage,
								String rFirstImage, String rLastImage,
								String rSelectForeColor, String rDefaultForeColor,
								String rTableStyle) {

		/**
		 * 네비게이션-바에 나열할 페이지의 수
		 * (예시 : pageGroup = 10 -> < 1 2 3 4 5 6 7 8 9 10 >
		 * (예시 : pageGroup =  5 -> < 1 2 3 4 5 >
		 */
		int pageGroup = rPageGroup;
		if(pageGroup < 1)
			pageGroup = 1;		
		if(pageGroup > 30)
			pageGroup = 30;
		
		/**
		 * 전체 게시물의 수
		 */
		int totCount = rTotCount;
		if(totCount < 1)
			totCount = 0;
		
	    /**
	     * 한 페이지에 보여줄 목록 수	    
	     */
	    int pageSize = rPageSize;
	    if(pageSize < 1)
	    	pageSize = 0;
	    	
	    /**
	     * 전체 페이지의 수
	     */
	    int pageCount = rPageCount;
	    if(pageCount < 1)
	    	pageCount = 1;
	    
	    /**
	     * 현재 선택된 페이지 인덱스 값
	     * 페이지는 1부터, 인덱스는 0부터..
	     */
	    int curPage = rCurPage;
	    if(curPage < 0)
	    	curPage = 0;
	    if(curPage >= pageCount)
	    	curPage = pageCount - 1;

	    /**
	     * 현재 선택된 페이지 인덱스 값을 저장하는 변수명
	     */
	    String curPageName = rCurPageName;
	    if(curPageName.trim().length() < 1)
	    	curPageName = "curPage";
	    
	    /**
	     * 이전 페이지로 이동할 이미지 경로
	     */
	    //String backImage = rBackImage;
	    String backImage = this._backImage;
	    if(backImage != null && backImage.trim().length() > 0) {
	    	
	    	if( backImage.toLowerCase().indexOf("gif") >= 0 ||
		    		backImage.toLowerCase().indexOf("jpg") >= 0 ||
		    		backImage.toLowerCase().indexOf("png") >= 0 ||
		    		backImage.toLowerCase().indexOf("bmp") >= 0 )
		    		backImage = "<img src='" + backImage.trim() + "' alt='이전' title='' />";

	    }
	    
	    /**
	     * 다음 페이지로 이동할 이미지 경로
	     */
	    //String nextImage = rNextImage;
	    String nextImage = this._nextImage;
	    if(nextImage != null && nextImage.trim().length() > 0) {
	    	
	    	if( nextImage.toLowerCase().indexOf("gif") >= 0 ||
	    		nextImage.toLowerCase().indexOf("jpg") >= 0 ||
	    		nextImage.toLowerCase().indexOf("png") >= 0 ||
	    		nextImage.toLowerCase().indexOf("bmp") >= 0 )
	    		nextImage = "<img src='" + nextImage.trim() + "' alt='다음' title='' />";

	    }
	    
	    /**
	     * 처음 페이지로 이동할 이미지 경로
	     * set 가능, get 가능
	     */
	    //String firstImage = rFirstImage;
	    String firstImage = this._firstImage;
	    if(firstImage != null && firstImage.trim().length() > 0) {
	    	
	    	if( firstImage.toLowerCase().indexOf("gif") >= 0 ||
	    		firstImage.toLowerCase().indexOf("jpg") >= 0 ||
	    		firstImage.toLowerCase().indexOf("png") >= 0 ||
	    		firstImage.toLowerCase().indexOf("bmp") >= 0 )
	    		firstImage = "<img src='" + firstImage.trim() + "' alt='처음' title='' />";
	    	
	    }

	    /**
	     * 마지막 페이지로 이동할 이미지 경로
	     * set 가능, get 가능
	     */
	    //String lastImage = rLastImage;
	    String lastImage = this._lastImage;
	    if(lastImage != null && lastImage.trim().length() > 0) {
	    	
	    	if( lastImage.toLowerCase().indexOf("gif") >= 0 ||
	    		lastImage.toLowerCase().indexOf("jpg") >= 0 ||
	    		lastImage.toLowerCase().indexOf("png") >= 0 ||
	    		lastImage.toLowerCase().indexOf("bmp") >= 0 )
	    		lastImage = "<img src='" + lastImage.trim() + "' alt='마지막' title='' />";
	    	
	    }

	    /**
	     * 이동할 페이지의 url
	     * set 가능, get 가능
	     */
	    String urlPage = rUrlPage;
	    if(urlPage.trim().length() < 1)
	    	urlPage = "#";
	
	    /**
	     * 이동할 페이지에 넘겨보낼 파라미터
	     * set 가능, get 가능
	     */
	    //String params = rParams;
	
	    /**
	     * 현재 선택된 페이지의 폰트 컬러
	     */
	    String selectForeColor = rSelectForeColor;
	    if(selectForeColor.trim().length() < 1)
	    	selectForeColor = "red";
	    
	    /**
	     * 선택되지 않은 페이지의 폰트 컬러
	     */
	    String defaultForeColor = rDefaultForeColor;
	    if(defaultForeColor.trim().length() < 1)
	    	defaultForeColor = "black";
	    
	    /**
	     * 현재 표시되는 페이징의 테이블 스타일 
	     */
	    String tableStyle = rTableStyle;
	    if(tableStyle.trim().length() < 1)
	    	tableStyle = ""; 
	    
	    //-- --//
	    
	    /*
	     * 이전 버튼의 이동 타켓
	     */
	    String prevPageGroup_by_PageUrl = "";
	
	    /*
	     * 다음 버튼의 이동 타켓
	     */
	    String nextPageGroup_by_PageUrl = "";
	
	    /*
	     * 처음 버튼의 이동 타켓
	     */
	    String firstPageGroup_by_PageUrl = "";
	
	    /*
	     * 마지막 버튼의 이동 타켓
	     */
	    String lastPageGroup_by_PageUrl = "";


	    StringBuffer sb = new StringBuffer();
	    sb.append("<!--[페이지 네비게이션 시작]-->                      			\n");
	    sb.append("<script language='javascript'>                       \n");
	    sb.append("  function pageMove(_form, _curPage) {               \n");
	    sb.append("    _form." + curPageName.trim() + ".value=_curPage; \n");
		sb.append("    _form.target='';                                 \n");
	    sb.append("    _form.action='" + urlPage.trim() + "';           \n");
	    sb.append("    _form.submit();                                  \n");
	    sb.append("  }                                                  \n");
	    
	    sb.append("  function emptyAction() {               			\n");	    
	    sb.append("  }                                                  \n");
	    sb.append("</script>                                            \n");
	    
	    //-- --//
	    
	    // CSS
	    /*
	    sb.append("	<style>																					\n");	    
	    sb.append("	#paging {width:100%; overflow:hidden; margin-top:11px; text-align:center;}              \n");
	    sb.append("	#paging img {padding-top:2px;}                                                    		\n");
	    sb.append("	#paging a,#paging a:link, #paging a:visited {margin:0 3px; font-size:11px;}       		\n");
	    sb.append("	#paging .selfpage {margin:0 3px; font-size:11px; color:#f15753; font-weight:bold;}		\n");
	    sb.append("	</style>																				\n");
	    */

	    /*
	     * 현재 나열된 네비게이션의 시작페이지 인덱스
	     */
	    int startIndex = (curPage / pageGroup) * pageGroup;
	
	    /*
	     * 현재 나열된 네비게이션의 종료페이지 인덱스
	     */
	    int endIndex = ( (curPage / pageGroup) * pageGroup) + pageGroup;
	
	    /*
	     * 잉여 페이지를 제외한 전체 페이지수
	     * ex : 총 23페이지라하면 아래 변수에는 잉여 3페이지를 제외한 20페이가 셋팅된다.
	     */
	    int normal_pageGroup = pageCount - (pageCount % pageGroup);
	
	    /*
	     * 현재 나열된 네비게이션의 시작페이지 인덱스가 잉여페이지를 제외한 값과 같다면,
	     * [>]버튼을 클릭하여 나열될 네비게이션의 종료페이지 인덱스를 전체 페이지수와 맞추어
	     * [>]버튼을 더이상 사용하지 못하도록 잠그고, 페이지 네비게이션에 나열될 잉여페이지를 정돈한다.
	     */
	    if (startIndex == normal_pageGroup)
	    	endIndex = pageCount;	
	    
	    //sb.append("<table cellspacing='0' cellpadding='0' border='0'>");
	    //sb.append("<tr valign='middle'>");
	    //sb.append("<td align='right'>");
	    //sb.append("<table cellpadding='0' cellspacing='0' border='0'>");
	    //sb.append("<tr valign='middle'>");
	    //sb.append("<td>&nbsp;</td>");	    
	    
	    sb.append("<ul>\n");
	    
	    /**
	     * 현재 페이지 인덱스가 0보다 큰 경우에만 [<<]버튼을 활성화 시킨다.
	     */
	    if(curPage > 0)
	    	firstPageGroup_by_PageUrl = "javascript:pageMove(document.Form, 0)";	    
	    
		//[<<]	    
	    if (firstPageGroup_by_PageUrl != null && firstPageGroup_by_PageUrl.trim().length() > 0) {
	    			    
	    	sb.append("<li class='prev'><a href='" + firstPageGroup_by_PageUrl.trim() + "' class='pre' >");
		    sb.append(firstImage);
		    sb.append("</a></li>\n");
		    
	    } else {
	    	
	    	sb.append("<li class='prev'><a class='pre' >");
		    sb.append(firstImage);
		    sb.append("</a></li>\n");
	    		    		    	
	    }


	    /*
	     * 기본적으로 설정되어 있는 페이지 그룹보다 큰 경우만 [<]버튼을 활성화 시킨다.
	     * 예 : pageGroup = 10 일 경우 적어도 앞으로 이동할 10페이지가 확보된 경우만
	     * [<]버튼을 활성화 시켜준다.
	     */
	    if (startIndex >= pageGroup) {
	    	
	    	prevPageGroup_by_PageUrl = "javascript:pageMove(document.Form, " + (startIndex - pageGroup) + ")";
	    	
	    	/*
			if (!params.equals(""))
				prevPageGroup_by_PageUrl = urlPage + "?curPage=" + (startIndex - pageGroup) + "&" + params;
			else
				prevPageGroup_by_PageUrl = urlPage + "?curPage=" + (startIndex - pageGroup);
			*/    	
	    	
	    }
	    
	    //[<]
	    if (prevPageGroup_by_PageUrl != null && prevPageGroup_by_PageUrl.trim().length() > 0) {
	    	
	    	sb.append("<li class='prev'><a href='" + prevPageGroup_by_PageUrl.trim() + "' class='pre' >");
		    sb.append(backImage);
		    sb.append("</a></li>\n");
		    
	    } else {
	    	
	    	sb.append("<li class='prev'><a class='pre' >");
		    sb.append(backImage);
		    sb.append("</a></li>\n");
		    
	    }	

	    /*
	     * 네비게이션에 나열될 페이지의 구현부분
	     */

	    for (int i = startIndex; i < endIndex; i++) {	
	    	
	    	// Current Page
	    	if (i == curPage) {

				sb.append("<li class='number'><strong>");
				sb.append( (i + 1) );
				sb.append("</strong></li>\n");

			// other Page	    		
	    	}else {
				
	    		String temp_url = "javascript:pageMove(document.Form, " + (i) + ")";
		        
		        sb.append("<li class='number'><a title='" + (i+1) + "' href='" + temp_url.trim() + "' >");				
				sb.append( (i + 1) );
				sb.append("</a></li>\n");

	    	}
	    	
	    	// 마지막 부분에는 dot을 제거
	    	if( i < endIndex-1 ) {
	    		sb.append("<li class='dotted'>.</li>\n");
	    	}
    
	    }
	   
	    /*
	     * 종료 페이지 인덱스가 전체 페이지 갯수보다 작은 경우까지만
	     * [>]버튼을 활성화 한다.
	     */
	    if (endIndex < pageCount) {
	      
	    	nextPageGroup_by_PageUrl = "javascript:pageMove(document.Form, " + (endIndex) + ")";
	    	
	    	/*
			if(! param.equals(""))
				nextPageGroup_by_PageUrl = urlPage + "?curPage=" + endIndex + "&" + param;
			else
				nextPageGroup_by_PageUrl = urlPage + "?curPage=" + endIndex;
			*/	    	
	      
	    }
	    
	    //[>]
	    if (nextPageGroup_by_PageUrl != null && nextPageGroup_by_PageUrl.length() > 0) {
	    	
	    	sb.append("<li class='next'><a href='" + nextPageGroup_by_PageUrl.trim() + "' class='next' >");
		    sb.append(nextImage);
		    sb.append("</a></li>\n");	    	
		    
	    } else {
	    	
	    	sb.append("<li class='next'><a class='next'>");
		    sb.append(nextImage);
		    sb.append("</a></li>\n");
		    
	    }

	    /**
	     * 현재 페이지 인덱스가 끝 페이지보다 작은 경우 [>>]버튼을 활성화 시킨다.
	     */
	    if(curPage < (pageCount-1))
	    	lastPageGroup_by_PageUrl = "javascript:pageMove(document.Form, " + (pageCount - 1) + ")";
	    
		
	    //[>>]    
	    if (lastPageGroup_by_PageUrl != null && lastPageGroup_by_PageUrl.length() > 0) {
	    	
	    	sb.append("<li class='next'><a href='" + lastPageGroup_by_PageUrl.trim() + "' class='next' >");
		    sb.append(lastImage);
		    sb.append("</a></li>\n");
		    
	    } else {
	    	
	    	sb.append("<li class='next'><a class='next' >");
		    sb.append(lastImage);
		    sb.append("</a></li>\n");
		    
	    }
	    
	    sb.append("</ul>\n");
		
		sb.append("<div class='numberRtTxt'>(<strong>"+(curPage+1)+"</strong> / "+rPageCount+")</div>\n");

	    sb.append("<!--[페이지 네비게이션 종료]-->");
	
	    return sb.toString();
	
	}
	
}
