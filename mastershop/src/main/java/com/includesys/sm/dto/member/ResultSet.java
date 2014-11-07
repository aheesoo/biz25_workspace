package com.includesys.sm.dto.member;

/**
 * 결과 모음셋 빈
 * @param <T>
 */
public class ResultSet<T> 
{
	private boolean result;
	
	/**
	 * @return 처리결과
	 */
	public boolean getResult()
	{
		return this.result;
	}
	
	/**
	 * @param result 처리결과
	 */
	public void setResult(boolean result)
	{
		this.result = result;
	}
	
	private String message;
	
	/**
	 * @return 처리결과 메세지
	 */
	public String getMessage()
	{
		return this.message;
	}
	
	/**
	 * @param message 처리결과 메세지
	 */
	public void setMessage(String message)
	{
		this.message = message;
	}
	
	private T data;
	
	/**
	 * @return 처리결과 값
	 */
	public T getData()
	{
		return this.data;
	}
	
	/**
	 * @param data 처리결과 값
	 */
	public void setData(T data)
	{
		this.data = data;
	}
}
