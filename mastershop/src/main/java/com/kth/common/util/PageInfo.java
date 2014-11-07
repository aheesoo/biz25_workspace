package com.kth.common.util;

public class PageInfo {

	private boolean debugPrint = false;
	private int PAGE_SIZE = 10;
	private int PAGE_GROUP = 10;
	private String CUR_PAGE_NAME= "curPage";
	private String URL_PAGE= "#";

	public void setPageSize(int iTemp) {
		this.PAGE_SIZE = iTemp;
	}

	public void setPageGroup(int iTemp) {
		this.PAGE_GROUP = iTemp;
	}
	
	public int getTotalPage(int iTotalCount) {
		int iTotalPage = 0;

		if (iTotalCount == 0) {
			iTotalPage = 1;
		} else if (iTotalCount % PAGE_SIZE == 0) {
			iTotalPage = iTotalCount / PAGE_SIZE;
		} else {
			iTotalPage = iTotalCount / PAGE_SIZE + 1;
		}

		return iTotalPage;
	}

	public int getTotalGroup(int iTotalPage) {
		int iTotalGroup = 0;

		if (iTotalPage % PAGE_GROUP == 0) {
			iTotalGroup = iTotalPage / PAGE_GROUP;
		} else {
			iTotalGroup = iTotalPage / PAGE_GROUP + 1;
		}

		return iTotalGroup;
	}

	public String makeIndex(int iCurPage, int iTotalCount) {
		return makeIndex(iCurPage, iTotalCount, PAGE_SIZE, PAGE_GROUP,
				"curPage", "#",
				"", "", "", "");
	}
	
	/**
	 * @param iCurPage : 현재페이지
	 * @param iTotalCount : 전체레코드수
	 * @param iPageSize : 페이지에 포함되는 레코드 수 
	 * @return
	 */
	public String makeIndex(int iCurPage, int iTotalCount, int iPageSize) {
		setPageSize(iPageSize);
		return makeIndex(iCurPage, iTotalCount);
	}
	
	/**
	 * @param iCurPage : 현재페이지
	 * @param iTotalCount : 전체레코드수
	 * @param iPageSize : 페이지에 포함되는 레코드 수 
	 * @param iPageGroup : 
	 * @return
	 */
	public String makeIndex(int iCurPage, int iTotalCount, int iPageSize, int iPageGroup) {
		setPageGroup(iPageGroup);
		return makeIndex(iCurPage, iTotalCount, iPageSize);
	}
	
	/**
	 * @param iCurPage		: 현재 페이지
	 * @param iTotalCount	: 전체 목록 수
	 * @param iTotalPage	: 전체 페이지 수
	 * @param iTotalGroup	: 전체 페이지 그룹수
	 * @param iCurGroup		: 현재 그룹
	 * @param sCurPageName	: 스크립트 현재페이지 속성 명
	 * @param sUrlPage		: 이동할 페이지
	 * @param sFirstImage	: 맨 처음 이미지
	 * @param sBackImage	: 이전 이미지
	 * @param sNextImage	: 다음 이미지
	 * @param sLastImage	: 맨 끝 이미지
	 * @return
	 */
	public String makeIndex(int iCurPage, int iTotalCount, int iPageSize, int iPageGroup,
			String sCurPageName, String sUrlPage,
			String sFirstImage, String sBackImage, String sNextImage, String sLastImage) {
		
		setPageSize(iPageSize);
		setPageGroup(iPageGroup);
		
		int iTotalPage = getTotalPage(iTotalCount); // 총 페이지
		int iTotalGroup = getTotalGroup(iTotalPage); // 총 페이지 그룹
		
		int iCurGroup = 1;
		int iTmp = iTotalPage;

		for (int i = 1; i <= iTotalGroup; i++) {
			if ((iCurPage <= i * iPageGroup) && (iCurPage > i)) {
				iCurGroup = i;
				break;
			}
			iTmp = iTmp - PAGE_GROUP;
		}
		
		if (sCurPageName != null && sCurPageName.trim().length() > 0) 
			sCurPageName = sCurPageName.trim();
		else sCurPageName = CUR_PAGE_NAME;
		
		if (sUrlPage != null && sUrlPage.trim().length() > 0) 
			sUrlPage = sUrlPage.trim();
		else sUrlPage = URL_PAGE;
		
		if (debugPrint) {
			System.out.println("==========================================================");
			System.out.println("iCurPage     : " + iCurPage);
			System.out.println("iTotalCount  : " + iTotalCount);
			System.out.println("iPageSize    : " + iPageSize + "\t PAGE_SIZE  : " + PAGE_SIZE);
			System.out.println("iPageGroup   : " + iPageGroup + "\t PAGE_GROUP : " + PAGE_GROUP);
			System.out.println("iTotalPage   : " + iTotalPage);
			System.out.println("iTotalGroup  : " + iTotalGroup);
			System.out.println("iCurGroup    : " + iCurGroup);
			System.out.println("sCurPageName : " + sCurPageName);
			System.out.println("sUrlPage     : " + sUrlPage);
			System.out.println("==========================================================");
		}
	    
		/**
	     * 처음 페이지로 이동할 이미지 경로
	     */
	    if(sFirstImage != null && sFirstImage.trim().length() > 0) {
	    	if( sFirstImage.toLowerCase().indexOf("gif") >= 0 ||
	    			sFirstImage.toLowerCase().indexOf("jpg") >= 0 ||
	    			sFirstImage.toLowerCase().indexOf("png") >= 0 ||
	    			sFirstImage.toLowerCase().indexOf("bmp") >= 0 )
	    		sFirstImage = "<img src='" + sFirstImage.trim() + "' class=\"mr5\">";
	    } else sFirstImage = "[처음]";
		
	    /**
	     * 이전 페이지로 이동할 이미지 경로
	     */
	    if(sBackImage != null && sBackImage.trim().length() > 0) {
	    	if( sBackImage.toLowerCase().indexOf("gif") >= 0 ||
	    			sBackImage.toLowerCase().indexOf("jpg") >= 0 ||
	    			sBackImage.toLowerCase().indexOf("png") >= 0 ||
	    			sBackImage.toLowerCase().indexOf("bmp") >= 0 )
	    		sBackImage = "<img src='" + sBackImage.trim() + "' class=\"mr10\">";
	    } else sBackImage = "[이전]";
	    


	    /**
	     * 다음 페이지로 이동할 이미지 경로
	     */
	    if(sNextImage != null && sNextImage.trim().length() > 0) {
	    	if( sNextImage.toLowerCase().indexOf("gif") >= 0 ||
	    			sNextImage.toLowerCase().indexOf("jpg") >= 0 ||
	    			sNextImage.toLowerCase().indexOf("png") >= 0 ||
	    			sNextImage.toLowerCase().indexOf("bmp") >= 0 )
	    		sNextImage = "<img src='" + sNextImage.trim() + "' class=\"ml10\">";
	    	
	    } else sNextImage = "[다음]";
	    
	    /**
	     * 끝 페이지로 이동할 이미지 경로
	     */
	    if(sLastImage != null && sLastImage.trim().length() > 0) {
	    	if( sLastImage.toLowerCase().indexOf("gif") >= 0 ||
	    			sLastImage.toLowerCase().indexOf("jpg") >= 0 ||
	    			sLastImage.toLowerCase().indexOf("png") >= 0 ||
	    			sLastImage.toLowerCase().indexOf("bmp") >= 0 )
	    		sLastImage = "<img src='" + sLastImage.trim() + "' class=\"ml5\">";
	    } else sLastImage = "[끝]";
		
		StringBuffer sb = new StringBuffer();

		sb.append("<script language='javascript'>                          \n");
	    sb.append("  function changePage(_form, _curPage) {                \n");
	    sb.append("    _form." + sCurPageName.trim() + ".value = _curPage; \n");
	    sb.append("    _form.action='" + sUrlPage + "';                    \n");
	    sb.append("    _form.submit();                                     \n");
	    sb.append("  }                                                     \n");
	    sb.append("</script>                                               \n");

	    sb.append("<td class=\"pad_t2\">");
		if (iCurPage != 1) {
			sb.append("<a href='javascript:changePage(document.Form, 1)'>");
			sb.append(sFirstImage);
			sb.append("</a>");
		} else {
			sb.append(sFirstImage);
		}
		sb.append("</td> \n");
		sb.append("<td class=\"pad_t2\">");
		if (iCurGroup > 1) {
			sb.append("<a href=\"javascript:changePage(document.Form, '" 
					+ Integer.toString((iCurGroup * PAGE_GROUP) - (PAGE_GROUP - 1) - PAGE_GROUP) + "');\"  title=" 
					+ Integer.toString((iCurGroup * PAGE_GROUP) - (PAGE_GROUP - 1) - PAGE_GROUP)+">");
			sb.append(sBackImage);
			sb.append("</a>");
		} else {
			sb.append(sBackImage);
		}
		sb.append("</td> \n");

		for (int i = (iCurGroup * PAGE_GROUP) - (PAGE_GROUP - 1); i < (iCurGroup * PAGE_GROUP) + 1; i++) {
			if (i <= iTotalPage) {
				if (i == iCurPage) {
					if (i == iTotalPage) {
						sb.append("<td class=\"num_on_last\">");
					} else {
						if (i == (iCurGroup * PAGE_GROUP)) {
							sb.append("<td class=\"num_on_last\">");
						} else {
							sb.append("<td class=\"num_on\">");
						}
					}
				} else if (i == (iCurGroup * PAGE_GROUP)) {
					sb.append("<td class=\"num_last\">");
				} else {
					if (i == iTotalPage) {
						sb.append("<td class=\"num_last\">");
					} else {
						sb.append("<td class=\"num\">");
					}
				}
				if (i == iCurPage) {
					sb.append(Integer.toString(i));
				} else {
					sb.append("<a href=\"javascript:changePage(document.Form, '" + Integer.toString(i) + "');\"  title=" + 
							Integer.toString(i)+">" + Integer.toString(i) + "</a>");
				}
				sb.append("</td> \n");
			}
			if (i == iTotalPage) {
				break;
			}

		}

		sb.append("<td class=\"pad_t2\">");
		if ((iCurGroup < iTotalGroup) && (iCurPage < iTotalPage)) {
			sb.append("<a href=\"javascript:changePage(document.Form, '" + Integer.toString(iCurGroup * PAGE_GROUP + 1) 
					+ "');\"  title="+Integer.toString(iCurGroup * PAGE_GROUP + 1)+">");
			sb.append(sNextImage);
			sb.append("</a>");
		} else {
			sb.append(sNextImage);
		}
		sb.append("</td> \n");
		sb.append("<td class=\"pad_t2\">");
		if (iCurPage != iTotalPage) {
			sb.append("<a href=\"javascript:changePage(document.Form, '" + iTotalPage + "')\" title="+iTotalPage+">");
			sb.append(sLastImage);
			sb.append("</a>");
		} else {
			sb.append(sLastImage);
		}
		sb.append("</td> \n");

		return sb.toString();
	}
	
	public String makeIndexAjax(int iCurPage, int iTotalCount, int iPageSize, int iPageGroup,
			String selectName, String selectValue,
			String sFirstImage, String sBackImage, String sNextImage, String sLastImage) {
		
		setPageSize(iPageSize);
		setPageGroup(iPageGroup);
		
		int iTotalPage = getTotalPage(iTotalCount); // 총 페이지
		int iTotalGroup = getTotalGroup(iTotalPage); // 총 페이지 그룹
		
		int iCurGroup = 1;
		int iTmp = iTotalPage;

		for (int i = 1; i <= iTotalGroup; i++) {
			if ((iCurPage <= i * iPageGroup) && (iCurPage > i)) {
				iCurGroup = i;
				break;
			}
			iTmp = iTmp - PAGE_GROUP;
		}
		
		if (debugPrint) {
			System.out.println("==========================================================");
			System.out.println("iCurPage     : " + iCurPage);
			System.out.println("iTotalCount  : " + iTotalCount);
			System.out.println("iPageSize    : " + iPageSize + "\t PAGE_SIZE  : " + PAGE_SIZE);
			System.out.println("iPageGroup   : " + iPageGroup + "\t PAGE_GROUP : " + PAGE_GROUP);
			System.out.println("iTotalPage   : " + iTotalPage);
			System.out.println("iTotalGroup  : " + iTotalGroup);
			System.out.println("iCurGroup    : " + iCurGroup);
			System.out.println("==========================================================");
		}
	    
		/**
	     * 처음 페이지로 이동할 이미지 경로
	     */
	    if(sFirstImage != null && sFirstImage.trim().length() > 0) {
	    	if( sFirstImage.toLowerCase().indexOf("gif") >= 0 ||
	    			sFirstImage.toLowerCase().indexOf("jpg") >= 0 ||
	    			sFirstImage.toLowerCase().indexOf("png") >= 0 ||
	    			sFirstImage.toLowerCase().indexOf("bmp") >= 0 )
	    		sFirstImage = "<img src='" + sFirstImage.trim() + "' class=\"mr5\">";
	    } else sFirstImage = "[처음]";
		
	    /**
	     * 이전 페이지로 이동할 이미지 경로
	     */
	    if(sBackImage != null && sBackImage.trim().length() > 0) {
	    	if( sBackImage.toLowerCase().indexOf("gif") >= 0 ||
	    			sBackImage.toLowerCase().indexOf("jpg") >= 0 ||
	    			sBackImage.toLowerCase().indexOf("png") >= 0 ||
	    			sBackImage.toLowerCase().indexOf("bmp") >= 0 )
	    		sBackImage = "<img src='" + sBackImage.trim() + "' class=\"mr10\">";
	    } else sBackImage = "[이전]";
	    


	    /**
	     * 다음 페이지로 이동할 이미지 경로
	     */
	    if(sNextImage != null && sNextImage.trim().length() > 0) {
	    	if( sNextImage.toLowerCase().indexOf("gif") >= 0 ||
	    			sNextImage.toLowerCase().indexOf("jpg") >= 0 ||
	    			sNextImage.toLowerCase().indexOf("png") >= 0 ||
	    			sNextImage.toLowerCase().indexOf("bmp") >= 0 )
	    		sNextImage = "<img src='" + sNextImage.trim() + "' class=\"ml10\">";
	    	
	    } else sNextImage = "[다음]";
	    
	    /**
	     * 끝 페이지로 이동할 이미지 경로
	     */
	    if(sLastImage != null && sLastImage.trim().length() > 0) {
	    	if( sLastImage.toLowerCase().indexOf("gif") >= 0 ||
	    			sLastImage.toLowerCase().indexOf("jpg") >= 0 ||
	    			sLastImage.toLowerCase().indexOf("png") >= 0 ||
	    			sLastImage.toLowerCase().indexOf("bmp") >= 0 )
	    		sLastImage = "<img src='" + sLastImage.trim() + "' class=\"ml5\">";
	    } else sLastImage = "[끝]";
	    
		StringBuffer sb = new StringBuffer();

	    sb.append("<td class=\"pad_t2\">");
		if (iCurPage != 1) {
			sb.append("<a href=\"javascript:doSearch('GETLIST', 1, '"+ selectName +"', '"+ selectValue +"')\">");
			sb.append(sFirstImage);
			sb.append("</a>");
		} else {
			sb.append(sFirstImage);
		}
		sb.append("</td> \n");
		sb.append("<td class=\"pad_t2\">");
		if (iCurGroup > 1) {
			sb.append("<a href=\"javascript:doSearch('GETLIST', '" 
					+ Integer.toString((iCurGroup * PAGE_GROUP) - (PAGE_GROUP - 1) - PAGE_GROUP) + "', " +
							"'"+ selectName +"', '"+ selectValue +"');\"  title=" 
					+ Integer.toString((iCurGroup * PAGE_GROUP) - (PAGE_GROUP - 1) - PAGE_GROUP)+">");
			sb.append(sBackImage);
			sb.append("</a>");
		} else {
			sb.append(sBackImage);
		}
		sb.append("</td> \n");

		for (int i = (iCurGroup * PAGE_GROUP) - (PAGE_GROUP - 1); i < (iCurGroup * PAGE_GROUP) + 1; i++) {
			if (i <= iTotalPage) {
				if (i == iCurPage) {
					if (i == iTotalPage) {
						sb.append("<td class=\"num_on_last\">");
					} else {
						if (i == (iCurGroup * PAGE_GROUP)) {
							sb.append("<td class=\"num_on_last\">");
						} else {
							sb.append("<td class=\"num_on\">");
						}
					}
				} else if (i == (iCurGroup * PAGE_GROUP)) {
					sb.append("<td class=\"num_last\">");
				} else {
					if (i == iTotalPage) {
						sb.append("<td class=\"num_last\">");
					} else {
						sb.append("<td class=\"num\">");
					}
				}
				if (i == iCurPage) {
					sb.append(Integer.toString(i));
				} else {
					sb.append("<a href=\"javascript:doSearch('GETLIST', '" + Integer.toString(i) + "', " +
							"'"+ selectName +"', '"+ selectValue +"');\"  title=" + 
							Integer.toString(i)+">" + Integer.toString(i) + "</a>");
				}
				sb.append("</td> \n");
			}
			if (i == iTotalPage) {
				break;
			}

		}

		sb.append("<td class=\"pad_t2\">");
		if ((iCurGroup < iTotalGroup) && (iCurPage < iTotalPage)) {
			sb.append("<a href=\"javascript:doSearch('GETLIST', '" + Integer.toString(iCurGroup * PAGE_GROUP + 1) + 
					"', '"+ selectName +"', '"+ selectValue +"');\"  title="+Integer.toString(iCurGroup * PAGE_GROUP + 1)+">");
			sb.append(sNextImage);
			sb.append("</a>");
		} else {
			sb.append(sNextImage);
		}
		sb.append("</td> \n");
		sb.append("<td class=\"pad_t2\">");
		if (iCurPage != iTotalPage) {
			sb.append("<a href=\"javascript:doSearch('GETLIST', '" + iTotalPage + "', '"+ selectName +"', '"+ selectValue +"')\" title="+iTotalPage+">");
			sb.append(sLastImage);
			sb.append("</a>");
		} else {
			sb.append(sLastImage);
		}
		sb.append("</td> \n");

		return sb.toString();
	}
}
