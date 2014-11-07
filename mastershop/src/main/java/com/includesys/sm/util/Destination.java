package com.includesys.sm.util;

/**
 * <p>목적지(대상) 디렉토리 존재 유무에 따른 행위 열거자</p>
 * @author Administrator
 *
 */
public enum Destination 
{
	/**
	 * 대상 디렉토리가 없으면 새로 만든다.
	 */
	New,
	/**
	 * 대상 디렉토리가 없으면 실패 처리한다.
	 */
	Fail
}
