package com.includesys.sm.util;

/**
 * <p>소스 파일 또는 디렉토리 중복에따른 행위 열거자</p>
 */
public enum Duplication 
{
	/**
	 * 같은 이름의 리소스가 존재할시 덮어쓴다.
	 */
	Overwrite,
	/**
	 * 같은 이름의 리소스가 존재할시 새로운 이름을 생성한다.
	 */
	New,
	/**
	 * 같은 이름의 리소스가 존재할시 실패 처리한다.
	 */
	Fail
}
