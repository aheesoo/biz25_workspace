
package com.kth.common.secure;

public final class Secure {

	/**
	 * 공격 위험성이 존재하는 문자들을 필터링
	 * 입력값: SQL 입력값
	 * @param str
	 * @return
	 */
	public String sqlFilter(String str) {
		
		return sqlFilter(str, false);
		
	}
	
	
	/**
	 * 공격 위험성이 존재하는 문자들을 필터링 (공백 문자 입력 허용 여부)
	 * @param str
	 * @param allowSpace
	 * @return
	 */
	public String sqlFilter(String str, boolean allowSpace) {
		
		if( str != null ) {
			
			str = str.replaceAll("'","''");
			str = str.replaceAll("\"","\"\"");
			//str = str.replaceAll("\\","\\\\");
			str = str.replaceAll(";","");
			str = str.replaceAll("#","");
			str = str.replaceAll("--","");
			
			if(!allowSpace) {
				
				str = str.replaceAll(" ","");
				
			}
			
		}else {
			
			str = "";
			
		}

		return (str);
	}

	
	/**
	 * clearXSS()
	 * @param str
	 * @return
	 */
	public String clearXSS(String str) {

		return clearXSS(str, "");

	}


	/**
	 * clearXSS()
	 * XSS 필터 함수
 	 * $str - 필터링할 출력값
	 * $avatag - 허용할 태그 리스트 예)  $avatag = "p,br" 
	 * @param str
	 * @param avatag
	 * @return
	 */
	public String clearXSS(String str, String avatag) {
		str = str.replaceAll("<","&lt;");
		str = str.replaceAll("\0","");

		//허용할 태그를 지정할 경우
		if (!avatag.equals("")) {
			avatag.replaceAll(" ","");

			String [] st = avatag.split(",");

			//허용할 태그를 존재 여부를 검사하여 원상태로 변환
			for(int x = 0; x < st.length; x++ ) {
				str = str.replaceAll("&lt;"+st[x]+" ", "<"+st[x]+" ");
				str = str.replaceAll("&lt;"+st[x]+">", "<"+st[x]+">");
				str = str.replaceAll("&lt;/"+st[x], "</"+st[x]);
			}

		}

		return (str);
	}

	
	/**
	 * checkpath()
	 * 다운로드 경로 체크 함수
	 * $dn_dir - 다운로드 디렉토리 경로(path)
	 * $fname - 다운로드 파일명
	 * 리턴 - true:파운로드 파일 경로, false: "error" 
	 * @param dn_path
	 * @param fname
	 * @return
	 */
	public String checkpath(String dn_path, String fname) {
		
		//입력되는 디렉토리명에서 특수문자 유무 검사
		if((dn_path.indexOf("..\\") != -1) || (dn_path.indexOf("../") != -1)) {
			return "error";
		}

		//사용자 입력값으로 다운로드 파일 경로 생성
		if (dn_path.equals("")) {
		}
		else {
			dn_path = dn_path + "/";
		}
		String origfile = dn_path + fname;

		//fname에서 파일명만 분리 - 파일명에 공격 위험성 문자 필터링
		//fname.replaceAll("\\","/"); //일부 버전에서 오류 발생

		String filename3 = fname.substring(fname.lastIndexOf('/') + 1);
		//fname.replaceAll("\\","/")가 사용할 수 없는 경우 아래
		String filename4 = fname.substring(fname.lastIndexOf('\\') + 1);

		//분리한 파일명과 절대 경로를 재구성
		String FilePath = dn_path + filename4;

		//사용자 입력값과 재구성한 입력값을 비교하여 공격 위험성이 존재하는지 확인
		if (origfile.equals(FilePath)) {
			return (FilePath);
		}
		else {
			return "error";
		}
	}

	
	/**
	 * 확장자 검사
	 * $filename: 파일명
	 * $avaext: 허용할 확장자 예) $avaext = "jpg,gif,pdf"
	 * 리턴값: true-"ok", false-"error" 
	 * @param fileName
	 * @param avaExt
	 * @return
	 */
	public String checkext(String fileName, String avaExt) {
		
		String chkExt = "false";

		if (fileName.indexOf("\0") > -1) { chkExt = "false"; }

		//업로드 금지 확장자 체크
		String file_ext = fileName.substring(fileName.lastIndexOf('.') + 1);
		if(( file_ext.equalsIgnoreCase("jsp") || file_ext.equalsIgnoreCase("htm") || file_ext.equalsIgnoreCase("html")) ) {
			//out.println("업로드 금지 확장자");
			chkExt = "false";
		}

		//허용 확장자가 설정된 경우
		if (!avaExt.equals("")) {
			//공백 제거
			avaExt.replaceAll(" ","");

			String compStr[] = avaExt.split(",");

			for (int i = 0;i < compStr.length; i++) {
				if (file_ext.equalsIgnoreCase(compStr[i])) {
					chkExt = "true";
				}
			}
		}
		else {
			chkExt = "true";
		}

		return chkExt;
	}

}