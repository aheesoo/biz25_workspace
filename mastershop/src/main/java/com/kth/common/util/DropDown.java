
package com.kth.common.util;

public final class DropDown {

	//----------------------------------------------------------------------------------------------------//
	
	/**
	 * 생성자
	 */
	public DropDown() {

	}


	/**
	 * 드롭다운/메뉴 HTML Control을 구현함
	 * 실제값과 표현값이 각각 1차원 문자배열로 되어있음
	 * @param rDropDownName : 컴포넌트 명
	 * @param rValueMember : Value
	 * @param rDisplayMember : Display용 Text
	 * @param rDropDownType : 형태 1:콤보박스 2:리스트박스
	 * @param rDropWidth : 리스트박스인 경우 가로 너비
	 * @param rDropDownHeight : 리스트박스인 경우 세로 높이
	 * @param rDropDownMultipleAllow : 리스트박스인 경우 다중 선택 허용여부
	 * @param rSelectItem : 기본 선택 아이템
	 * @param rEventName : 발생시킬 이벤트 명
	 * @param rMethodName : 자바 스크립트에 연결할 메서드 명
	 * @param rParamValues : 메서드에 넘길 파라미터
	 * @param rDisabled : 사용여부
	 * @return
	 */
	public String DrawDropDown(String   rDropDownName, 
			                   String[] rValueMember,
							   String[] rDisplayMember,
							   int      rDropDownType, 
							   int      rDropWidth,
					           int      rDropDownHeight,
					           boolean  rDropDownMultipleAllow,
					           String   rSelectItem,
					           String   rEventName, 
					           String   rMethodName,
					           String   rParamValues, 
					           boolean  rDisabled) {
		
		return DrawDropDown(rDropDownName, 
				            rValueMember,
							rDisplayMember,
							rDropDownType, 
							rDropWidth,
				         	rDropDownHeight,
				         	rDropDownMultipleAllow,
				         	rSelectItem,
				         	rEventName, 
				         	rMethodName,
				         	rParamValues, 
				         	rDisabled,
				         	"",
				         	"");
		
	}

	
	/**
	 * 드롭다운/메뉴 HTML Control을 구현함
	 * 실제값과 표현값이 각각 1차원 문자배열로 되어있음
	 * @param rDropDownName : 컴포넌트 명
	 * @param rValueMember : Value
	 * @param rDisplayMember : Display용 Text
	 * @param rDropDownType : 형태 1:콤보박스 2:리스트박스
	 * @param rDropWidth : 리스트박스인 경우 가로 너비
	 * @param rDropDownHeight : 리스트박스인 경우 세로 높이
	 * @param rDropDownMultipleAllow : 리스트박스인 경우 다중 선택 허용여부
	 * @param rSelectItem : 기본 선택 아이템
	 * @param rEventName : 발생시킬 이벤트 명
	 * @param rMethodName : 자바 스크립트에 연결할 메서드 명
	 * @param rParamValues : 메서드에 넘길 파라미터
	 * @param rDisabled : 사용여부
	 * @param rClassName : class
	 * @return
	 */
	public String DrawDropDown(String   rDropDownName, 
			                   String[] rValueMember,
							   String[] rDisplayMember,
							   int      rDropDownType, 
							   int      rDropWidth,
					           int      rDropDownHeight,
					           boolean  rDropDownMultipleAllow,
					           String   rSelectItem,
					           String   rEventName, 
					           String   rMethodName,
					           String   rParamValues, 
					           boolean  rDisabled,
					           String   rClassName) {
		
		return DrawDropDown(rDropDownName, 
				            rValueMember,
							rDisplayMember,
							rDropDownType, 
							rDropWidth,
				         	rDropDownHeight,
				         	rDropDownMultipleAllow,
				         	rSelectItem,
				         	rEventName, 
				         	rMethodName,
				         	rParamValues, 
				         	rDisabled,
				         	rClassName,
				         	"");
		
	}
	
	
	/**
	 * 드롭다운/메뉴 HTML Control을 구현함
	 * 실제값과 표현값이 각각 1차원 문자배열로 되어있음
	 * @param rDropDownName : 컴포넌트 명
	 * @param rValueMember : Value
	 * @param rDisplayMember : Display용 Text
	 * @param rDropDownType : 형태 1:콤보박스 2:리스트박스
	 * @param rDropWidth : 리스트박스인 경우 가로 너비
	 * @param rDropDownHeight : 리스트박스인 경우 세로 높이
	 * @param rDropDownMultipleAllow : 리스트박스인 경우 다중 선택 허용여부
	 * @param rSelectItem : 기본 선택 아이템
	 * @param rEventName : 발생시킬 이벤트 명
	 * @param rMethodName : 자바 스크립트에 연결할 메서드 명
	 * @param rParamValues : 메서드에 넘길 파라미터
	 * @param rDisabled : 사용여부
	 * @param rClassName : class
	 * @param rStyle : style
	 * @return
	 */
	public String DrawDropDown(String   rDropDownName, 
			                   String[] rValueMember,
							   String[] rDisplayMember,
							   int      rDropDownType, 
							   int      rDropWidth,
                               int      rDropDownHeight,
                               boolean  rDropDownMultipleAllow,
                               String   rSelectItem,
                               String   rEventName, 
                               String   rMethodName,
                               String   rParamValues, 
                               boolean  rDisabled,
                               String   rClassName,
                               String   rStyle) {

		/**
		 * 드롭다운 컴포넌트에 부착될 아이템의 값
		 */
		String[] valueMember = rValueMember;

		/**
		 * 드롭다운 컴포넌트에 부착될 아이템의 텍스트
		 */
		String[] displayMember = rDisplayMember;

		/**
		 * 드롭다운 컴포넌트의 UI형태<br>
		 * [1] : 메뉴 형태 / [2] : 리스트 형태
		 */
		int dropDownType = rDropDownType;

		/**
		 * 아래 필드는 드롭다운 컴포넌트의 UI형태가<br>
		 * ([2]-리스트) 인 경우에만 설정 가능
		 * : 높이 값
		 */
		int dropDownHeight = rDropDownHeight;

		/**
		 * 아래 필드는 드롭다운 컴포넌트의 UI형태가<br>
		 * ([2]-리스트) 인 경우에만 설정 가능<br>
		 * : 복수 아이템 선택 여부 [기본값 : 단일 선택모드]
		 */
		boolean dropDownMultipleAllow = rDropDownMultipleAllow;

		/**
		 * 드롭 다운 컴포넌트의 너비 값<br>
		 * 원래 너비 값은 [style]을 적용하였을 경우에만 반영된다.<br>
		 * 설정하지 않으면 임의값이 지정된다.
		 */
		int dropDownWidth = rDropWidth;

		/**
		 * 임의의 아이템을 선택하여 화면에 보여준다.
		 */
		String selectItem = rSelectItem.trim();

		/**
		 * 드롭 다운 컴포넌트의 전체 아이템 갯수
		 */
		int itemTotalCount = rDisplayMember.length;

		/**
		 * 드롭 다운 컴포넌트의 이름
		 */
		String dropDownName = rDropDownName.trim();

		/**
		 * 컴포넌트에 대한 이벤트 핸들러 명
		 */
		String eventName = rEventName.trim();

		/**
		 * 발생한 이벤트 핸들러에 대한 처리를 할 메서드 명
		 */
		String methodName = rMethodName.trim();

		/**
		 * 메서드로 넘겨줄 파라미터들을 문자열화 함
		 */
		String paramValues = rParamValues;

		/**
		 * 컴포넌트를 사용하지 못하게 함
		 */
		boolean Disabled = rDisabled;

		//--- ---//

		StringBuffer temp = new StringBuffer("");
		temp.append("<Select name='" + dropDownName.trim() + "' ");

		if (dropDownType == 2) {
			temp.append("size='" + dropDownHeight + "' ");

			if (dropDownMultipleAllow)
				temp.append(" multiple ");
		}

		if (dropDownWidth > 0)
			temp.append("style='width:" + dropDownWidth + "px;' ");
		
		if (! (eventName.equals("") && methodName.equals("")))
			temp.append(eventName.trim() + "='javascript:" + methodName.trim() + "(" + paramValues.trim() + ")'");
		
		if (Disabled)
			temp.append(" disabled=true ");    

		if (rClassName != null && !"".equals(rClassName))
			temp.append("class='" + rClassName + "' ");
		
		if (rStyle != null && !"".equals(rStyle))
			temp.append("style='" + rStyle + "' ");
		
		temp.append(">");

		for (int i = 0; i < itemTotalCount; i++) {
			if (valueMember[i].trim().equals(selectItem.trim()))
				temp.append("<Option selected value='" + valueMember[i].trim() + "'>" + displayMember[i].trim() + "</Option>");
			else
				temp.append("<Option value='" + valueMember[i].trim() + "'>" + displayMember[i].trim() + "</Option>");
		}

		temp.append("</Select>");

		return temp.toString();
		
	}

	//----------------------------------------------------------------------------------------------------//

	/**
	 * 드롭다운/메뉴 HTML Control을 구현함
	 * 실제값과 표현값이 2차원 배열에 통합되어 있음
	 * 단, 0번열에는 값, 1번열에는 텍스트가 들어간다. 
     * @param rDropDownName : 컴포넌트 명
	 * @param rValueMember : Value
	 * @param rDisplayMember : Display용 Text
	 * @param rDropDownType : 형태 1:콤보박스 2:리스트박스
	 * @param rDropWidth : 리스트박스인 경우 가로 너비
	 * @param rDropDownHeight : 리스트박스인 경우 세로 높이
	 * @param rDropDownMultipleAllow : 리스트박스인 경우 다중 선택 허용여부
	 * @param rSelectItem : 기본 선택 아이템
	 * @param rEventName : 발생시킬 이벤트 명
	 * @param rMethodName : 자바 스크립트에 연결할 메서드 명
	 * @param rParamValues : 메서드에 넘길 파라미터
	 * @param rDisabled : 사용여부
	 * @param rClassName : class
	 * @param rStyle : style
	 * @return
	 */
	public String DrawDropDown(String     rDropDownName, 
			                   String[][] rDdlMember,
							   int        rDropDownType, 
							   int        rDropWidth,
							   int        rDropDownHeight,
							   boolean    rDropDownMultipleAllow,
							   String     rSelectItem,
							   String     rEventName, 
							   String     rMethodName,
							   String     rParamValues, 
							   boolean    rDisabled,
							   String     rClassName,
							   String     rStyle) {

		/**
		 * 드롭다운 컴포넌트에 부착될 아이템의 값과 텍스트
		 * 단, 0번열에는 값, 1번열에는 텍스트가 들어간다.
		 */
		String[][] ddlMember = rDdlMember;

		/**
		 * 드롭다운 컴포넌트의 UI형태<br>
		 * [1] : 메뉴 형태 / [2] : 리스트 형태
		 */
		int dropDownType = rDropDownType;

		/**
		 * 아래 필드는 드롭다운 컴포넌트의 UI형태가<br>
		 * ([2]-리스트) 인 경우에만 설정 가능
		 * : 높이 값
		 */
		int dropDownHeight = rDropDownHeight;

		/**
		 * 아래 필드는 드롭다운 컴포넌트의 UI형태가<br>
		 * ([2]-리스트) 인 경우에만 설정 가능<br>
		 * : 복수 아이템 선택 여부 [기본값 : 단일 선택모드]
		 */
		boolean dropDownMultipleAllow = rDropDownMultipleAllow;

		/**
		 * 드롭 다운 컴포넌트의 너비 값<br>
		 * 원래 너비 값은 [style]을 적용하였을 경우에만 반영된다.<br>
		 * 설정하지 않으면 임의값이 지정된다.
		 */
		int dropDownWidth = rDropWidth;

		/**
		 * 임의의 아이템을 선택하여 화면에 보여준다.
		 */
		String selectItem = rSelectItem.trim();

		/**
		 * 드롭 다운 컴포넌트의 전체 아이템 갯수
		 */
		int itemTotalCount = rDdlMember.length;

		/**
		 * 드롭 다운 컴포넌트의 이름
		 */
		String dropDownName = rDropDownName.trim();

		/**
		 * 컴포넌트에 대한 이벤트 핸들러 명
		 */
		String eventName = rEventName.trim();

		/**
		 * 발생한 이벤트 핸들러에 대한 처리를 할 메서드 명
		 */
		String methodName = rMethodName.trim();

		/**
		 * 메서드로 넘겨줄 파라미터들을 문자열화 함
		 */
		String paramValues = rParamValues;

		/**
		 * 컴포넌트를 사용하지 못하게 함
		 */
		boolean Disabled = rDisabled;

		//--- ---//

		StringBuffer temp = new StringBuffer("");
		temp.append("<Select name='" + dropDownName.trim() + "' ");

		if (dropDownType == 2) {
			temp.append("size='" + dropDownHeight + "' ");

			if (dropDownMultipleAllow)
				temp.append(" multiple ");
		}

		if (dropDownWidth > 0)
			temp.append("style='width:" + dropDownWidth + ";' ");

		if (! (eventName.equals("") && methodName.equals("")))
			temp.append(eventName.trim() + "='javascript:" + methodName.trim() + "(" + paramValues.trim() + ")'");
    
		if (Disabled)
			temp.append(" disabled=true ");
    	
		if (rClassName != null && !"".equals(rClassName))
			temp.append("class='" + rClassName + "' ");
		
		if (rStyle != null && !"".equals(rStyle))
			temp.append("style='" + rStyle + "' ");
		
		temp.append(">");

		for (int i = 0; i < itemTotalCount; i++) {
			if (ddlMember[i][0].trim().equals(selectItem.trim()))
				temp.append("<Option selected value='" + ddlMember[i][0].trim() + "'>" + ddlMember[i][1].trim() + "</Option>");
			else
				temp.append("<Option value='" + ddlMember[i][0].trim() + "'>" + ddlMember[i][1].trim() + "</Option>");
			
		}

		temp.append("</Select>");

		return temp.toString();
		
	}
	
}
