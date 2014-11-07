package com.includesys.sm.util;

import java.io.*;
import java.util.*;

public class FileSystemManager 
{
	public FileSystemManager(){}
	
	/**
	 * <p>디렉토리 / 파일 삭제</p>
	 * @param fileOrDirectoryFullPath 디렉토리 / 파일의 전체 절대경로
	 * @return 성공 / 실패
	 */
	public boolean delete(String fileOrDirectoryFullPath)
	{
		return delete(new File(fileOrDirectoryFullPath));
	}
	
	/**
	 * <p>디렉토리 / 파일 삭제</p>
	 * @param fileOrDirectory 디렉토리 / 파일의 java.io.File 
	 * @return 성공 / 실패
	 */
	public boolean delete(File fileOrDirectory)
	{
		boolean result = true;
		
		try
		{
			if(fileOrDirectory.exists())
			{
				if(fileOrDirectory.isFile())
				{
					result = fileOrDirectory.delete();
				}
				else
				{
					for(File fod : fileOrDirectory.listFiles())
					{
						if(fod.isFile())
						{
							fod.delete();
						}
						else
						{
							delete(fod);
						}
					}
				}
			}
		}
		catch(Exception ex)
		{
			result = false;
		}
		
		return result;
	}	
	
	/**
	 * <p>디렉토리 / 파일 생성</p>
	 * @param fileOrDirectoryFullPath 디렉토리 / 파일의 전체 절대경로
	 * @return 성공 / 실패
	 */
	public boolean create(String fileOrDirectoryFullPath)
	{
		return create(new File(fileOrDirectoryFullPath));
	}
	
	/**
	 * <p>디렉토리 / 파일 생성</p>
	 * @param fileOrDirectoryFullPath 디렉토리 / 파일의 java.io.File
	 * @return 성공 / 실패
	 */	
	public boolean create(File fileOrDirectory)
	{
		boolean result = true;
		
		try
		{
			if(!fileOrDirectory.exists())
			{
				if(fileOrDirectory.getName().lastIndexOf(".") != -1)
				{
					result = fileOrDirectory.createNewFile();
				}
				else
				{
					result = fileOrDirectory.mkdirs();
				}
			}
		}
		catch(Exception ex)
		{
			result = false;
		}
		
		return result;
	}
	
	/**
	 * <pre>
	 * 디렉토리 / 파일 이동
	 * 목적지 디렉토리 기본값 : 없으면 자동 생성
	 * 소스 디렉토리 / 파일의 목적지 디렉토리 / 파일 이름 중복시 기본값 : 새로운 이름 생성
	 * </pre>
	 * @param fileOrDirectoryFullPath 소스 디렉토리 / 파일의 전체 절대경로
	 * @param targetDirectoryFullPath 목적지 디렉토리 전체 절대경로
	 * @return ResultSet.getData() 이름 중복 발생시 새로운 이름 반환
	 */
	public ResultSet<String> move(String fileOrDirectoryFullPath, String targetDirectoryFullPath)
	{
		return move(new File(fileOrDirectoryFullPath), new File(targetDirectoryFullPath));
	}
	
	/**
	 * <pre>
	 * 디렉토리 / 파일 이동
	 * 목적지 디렉토리 기본값 : 없으면 자동 생성
	 * 소스 디렉토리 / 파일의 목적지 디렉토리 / 파일 이름 중복시 기본값 : 새로운 이름 생성
	 * </pre>
	 * @param fileOrDirectory 소스 디렉토리 / 파일의 java.io.File
	 * @param targetDirectory 목적지 디렉토리 java.io.File
	 * @return ResultSet.getData() 이름 중복 발생시 새로운 이름 반환
	 */
	public ResultSet<String> move(File fileOrDirectory, File targetDirectory)
	{
		return move(fileOrDirectory, targetDirectory, Duplication.New);
	}
	
	/**
	 * <pre>
	 * 디렉토리 / 파일 이동
	 * 소스 디렉토리 / 파일의 목적지 디렉토리 / 파일 이름 중복시 기본값 : 새로운 이름 생성
	 * </pre> 
	 * @param fileOrDirectoryFullPath 소스 디렉토리 / 파일의 전체 절대경로
	 * @param targetDirectoryFullPath 목적지 디렉토리 전체 절대경로
	 * @param duplcation 소스 디렉토리 / 파일의 이름 중복에따른 행위 열거자 com.includesys.sm.util.Duplication 참조
	 * @return ResultSet.getData() 이름 중복 발생시 새로운 이름 반환
	 */
	public ResultSet<String> move(String fileOrDirectoryFullPath, String targetDirectoryFullPath, Duplication duplcation)
	{
		return move(new File(fileOrDirectoryFullPath), new File(targetDirectoryFullPath), duplcation);
	}
	
	/**
	 * <pre>
	 * 디렉토리 / 파일 이동
	 * 소스 디렉토리 / 파일의 목적지 디렉토리 / 파일 이름 중복시 기본값 : 새로운 이름 생성
	 * </pre>  
	 * @param fileOrDirectory 소스 디렉토리 / 파일의 java.io.File
	 * @param targetDirectory 목적지 디렉토리 java.io.File
	 * @param duplcation 소스 디렉토리 / 파일의 이름 중복에따른 행위 열거자 com.includesys.sm.util.Duplication 참조
	 * @return ResultSet.getData() 이름 중복 발생시 새로운 이름 반환
	 */
	public ResultSet<String> move(File fileOrDirectory, File targetDirectory, Duplication duplication)
	{
		return move(fileOrDirectory, targetDirectory, duplication, Destination.New);
	}
	
	/**
	 * <p>디렉토리 / 파일 이동</p>
	 * @param fileOrDirectoryFullPath 소스 디렉토리 / 파일의 전체 절대경로
	 * @param targetDirectoryFullPath 목적지 디렉토리 전체 절대경로
	 * @param duplication 소스 디렉토리 / 파일의 이름 중복에따른 행위 열거자 com.includesys.sm.util.Duplication 참조
	 * @param destination 목적지(대상) 디렉토리 존재 유무에 따른 행위 열거자 com.includesys.sm.util.Destination 참조
	 * @return ResultSet.getData() 이름 중복 발생시 새로운 이름 반환
	 */
	public  ResultSet<String> move(String fileOrDirectoryFullPath, String targetDirectoryFullPath, Duplication duplication, Destination destination)
	{
		return move(new File(fileOrDirectoryFullPath), new File(targetDirectoryFullPath), duplication, destination);
	}
	
	/**
	 * <p>디렉토리 / 파일 이동</p>
	 * @param fileOrDirectory 소스 디렉토리 / 파일의 java.io.File
	 * @param targetDirectory 목적지 디렉토리 java.io.File
	 * @param duplication 소스 디렉토리 / 파일의 이름 중복에따른 행위 열거자 com.includesys.sm.util.Duplication 참조
	 * @param destination 목적지(대상) 디렉토리 존재 유무에 따른 행위 열거자 com.includesys.sm.util.Destination 참조
	 * @return ResultSet.getData() 이름 중복 발생시 새로운 이름 반환
	 */
	public ResultSet<String> move(File fileOrDirectory, File targetDirectory, Duplication duplication, Destination destination)
	{
		ResultSet<String> rs;
		
		rs = copy(fileOrDirectory, targetDirectory, duplication, destination);
		if(rs.getResult())
		{
			if(!delete(fileOrDirectory))
			{
				rs.setResult(false);
				rs.setMessage("파일 이동후 원본 디렉토리를 삭제하는 중 에러가 발생했습니다.");
				rs.setData("");
			}
		}
		
		return rs;
	}
	
	/**
	 * <p>디렉토리 / 파일 이름변경</p>
	 * @param fileOrDirectory 소스 디렉토리 / 파일의 java.io.File
	 * @param targetDirectory 목적지 디렉토리 java.io.File
	 * @return 성공 / 실패
	 */
	public boolean rename(File fileOrDirectory, File changefileOrDirectory)
	{
		boolean result = true;
		
		if(!changefileOrDirectory.exists())
		{
			result = fileOrDirectory.renameTo(changefileOrDirectory);
		}
		else
		{
			result = false;
		}
		
		return result;
	}
	
	/**
	 * <pre>
	 * 디렉토리 / 파일 복사
	 * 목적지 디렉토리 기본값 : 없으면 자동 생성
	 * 소스 디렉토리 / 파일의 목적지 디렉토리 / 파일 이름 중복시 기본값 : 새로운 이름 생성
	 * </pre>
	 * @param fileOrDirectoryFullPath 소스 디렉토리 / 파일의 전체 절대경로
	 * @param targetDirectoryFullPath 목적지 디렉토리 전체 절대경로
	 * @return ResultSet.getData() 이름 중복 발생시 새로운 이름 반환
	 */
	public ResultSet<String> copy(String fileOrDirectoryFullPath, String targetDirectoryFullPath)
	{
		return copy(new File(fileOrDirectoryFullPath), new File(targetDirectoryFullPath));
	}
	
	/**
	 * <pre>
	 * 디렉토리 / 파일 복사
	 * 목적지 디렉토리 기본값 : 없으면 자동 생성
	 * 소스 디렉토리 / 파일의 목적지 디렉토리 / 파일 이름 중복시 기본값 : 새로운 이름 생성
	 * </pre>
	 * @param fileOrDirectory 소스 디렉토리 / 파일의 java.io.File
	 * @param targetDirectory 목적지 디렉토리 java.io.File
	 * @return ResultSet.getData() 이름 중복 발생시 새로운 이름 반환
	 */	
	public ResultSet<String> copy(File fileOrDirectory, File targetDirectory)
	{
		return copy(fileOrDirectory, targetDirectory, Duplication.New, Destination.New);
	}
	
	/**
	 * <pre>
	 * 디렉토리 / 파일 복사
	 * 소스 디렉토리 / 파일의 목적지 디렉토리 / 파일 이름 중복시 기본값 : 새로운 이름 생성
	 * </pre> 
	 * @param fileOrDirectoryFullPath 소스 디렉토리 / 파일의 전체 절대경로
	 * @param targetDirectoryFullPath 목적지 디렉토리 전체 절대경로
	 * @param duplcation 소스 디렉토리 / 파일의 이름 중복에따른 행위 열거자 com.includesys.sm.util.Duplication 참조
	 * @return ResultSet.getData() 이름 중복 발생시 새로운 이름 반환
	 */	
	public ResultSet<String> copy(String fileOrDirectoryFullPath, String targetDirectoryFullPath, Duplication duplication)
	{
		return copy(new File(fileOrDirectoryFullPath), new File(targetDirectoryFullPath), duplication);
	}
	
	/**
	 * <pre>
	 * 디렉토리 / 파일 복사
	 * 소스 디렉토리 / 파일의 목적지 디렉토리 / 파일 이름 중복시 기본값 : 새로운 이름 생성
	 * </pre>  
	 * @param fileOrDirectory 소스 디렉토리 / 파일의 java.io.File
	 * @param targetDirectory 목적지 디렉토리 java.io.File
	 * @param duplcation 소스 디렉토리 / 파일의 이름 중복에따른 행위 열거자 com.includesys.sm.util.Duplication 참조
	 * @return ResultSet.getData() 이름 중복 발생시 새로운 이름 반환
	 */	
	public ResultSet<String> copy(File fileOrDirectory, File targetDirectory, Duplication duplication)
	{
		return copy(fileOrDirectory, targetDirectory, duplication, Destination.New);
	}
	
	/**
	 * <p>디렉토리 / 파일 복사</p>
	 * @param fileOrDirectoryFullPath 소스 디렉토리 / 파일의 전체 절대경로
	 * @param targetDirectoryFullPath 목적지 디렉토리 전체 절대경로
	 * @param duplication 소스 디렉토리 / 파일의 이름 중복에따른 행위 열거자 com.includesys.sm.util.Duplication 참조
	 * @param destination 목적지(대상) 디렉토리 존재 유무에 따른 행위 열거자 com.includesys.sm.util.Destination 참조
	 * @return ResultSet.getData() 이름 중복 발생시 새로운 이름 반환
	 */	
	public ResultSet<String> copy(String fileOrDirectoryFullPath, String targetDirectoryFullPath, Duplication duplication, Destination destination)
	{
		return copy(new File(fileOrDirectoryFullPath), new File(targetDirectoryFullPath), duplication, destination);
	}
	
	/**
	 * <p>디렉토리 / 파일 복사</p>
	 * @param fileOrDirectory 소스 디렉토리 / 파일의 java.io.File
	 * @param targetDirectory 목적지 디렉토리 java.io.File
	 * @param duplication 소스 디렉토리 / 파일의 이름 중복에따른 행위 열거자 com.includesys.sm.util.Duplication 참조
	 * @param destination 목적지(대상) 디렉토리 존재 유무에 따른 행위 열거자 com.includesys.sm.util.Destination 참조
	 * @return ResultSet.getData() 이름 중복 발생시 새로운 이름 반환
	 */
	public ResultSet<String> copy(File fileOrDirectory, File targetDirectory, Duplication duplication, Destination destination) 
	{
		ResultSet<String> rs = new ResultSet<String>();
			
		if(!targetDirectory.exists())
		{
			if(destination == Destination.New)
			{
				create(targetDirectory);
			}
			else
			{
				rs.setResult(false);
				rs.setMessage("대상 디렉토리가 존재하지 않습니다.");
				rs.setData("");				
			}
		}

		boolean isDuplication = false;
		List<String> targetDirOrFileNames = (fileOrDirectory.isFile()) ? getFileNames(targetDirectory) : getDirectoryNames(targetDirectory);
		
		for(String name : targetDirOrFileNames)
		{
			if(fileOrDirectory.getName().equals(name))
			{
				isDuplication = true;
			}
		}
		
		if(isDuplication && duplication == Duplication.Fail)
		{
			rs.setResult(false);
			rs.setMessage("같은 이름의 파일 또는 디렉토리가 존재합니다.");
			rs.setData("");
		}
		else
		{
			String fileOrDirectoryName = fileOrDirectory.getName();
			if(fileOrDirectory.isFile())
			{					
				try
				{
					String newFileOrDirectoryName = fileOrDirectoryName;
					if(isDuplication)
					{
						if(duplication == Duplication.Overwrite)
						{
							delete(targetDirectory.getCanonicalPath() + System.getProperty("file.separator") + fileOrDirectoryName);
						}
						else
						{
							int i = 1;
							do
							{
								isDuplication = false;
								newFileOrDirectoryName  = fileOrDirectoryName.substring(0, fileOrDirectoryName.lastIndexOf(".")) + "_" + String.valueOf(i);
								newFileOrDirectoryName += fileOrDirectoryName.substring(fileOrDirectoryName.lastIndexOf("."));
								for(String name : targetDirOrFileNames)
								{
									if(newFileOrDirectoryName.equals(name))
									{
										isDuplication = true;
									}
								}
								i++;
								
							}while(isDuplication);
						}
					}
					
					File outputFile = new File(targetDirectory.getCanonicalPath() + System.getProperty("file.separator") + newFileOrDirectoryName);
					
					copyFile(fileOrDirectory, outputFile);
											
					rs.setResult(true);
					rs.setMessage("");
					rs.setData(newFileOrDirectoryName);
				}
				catch(Exception ex)
				{
					rs.setResult(false);
					rs.setMessage(ex.getMessage());
					rs.setData("");
				}
			}
			else
			{
				try
				{
					if(isDuplication)
					{
						if(duplication == Duplication.Overwrite)
						{
							delete(targetDirectory.getCanonicalPath() + System.getProperty("file.separator") + fileOrDirectoryName);
						}
						else
						{
							int i = 1;
							
							do
							{
								isDuplication = false;
								fileOrDirectoryName  = fileOrDirectoryName + "_" + String.valueOf(i);
								for(String name : targetDirOrFileNames)
								{
									if(fileOrDirectoryName.equals(name))
									{
										isDuplication = true;
									}
								}
								i++;
								
							}while(isDuplication);								
						}
						
						create(targetDirectory.getCanonicalPath() + System.getProperty("file.separator") + fileOrDirectoryName);
						List<File> files = Arrays.asList(fileOrDirectory.listFiles());
						
						for(File fd : files)
						{
							File target = new File(targetDirectory, fd.getName());
							if(fd.isDirectory())
							{
								copy(fd, target, duplication, destination);
							}
							else
							{
								copyFile(fd, target);
							}
						}
						
						rs.setResult(true);
						rs.setMessage("");
						rs.setData(fileOrDirectoryName);
						
					}
				}
				catch(Exception ex)
				{
					rs.setResult(false);
					rs.setMessage(ex.getMessage());
					rs.setData("");						
				}
			}
		}
		
		return rs;
	}
	
	private void copyFile(File source, File target) throws Exception
	{
		FileInputStream fis = null;
		FileOutputStream fos = null;

		try
		{
			create(target);
			
			int readByte = 0;
			byte[] Buffer = new byte[1024*10];
			
			fis = new FileInputStream(source);
			fos = new FileOutputStream(target);
			
			while(true) 
			{
				readByte = fis.read(Buffer);
				
				if(readByte > 0)
					fos.write(Buffer, 0, readByte);
				else
					break;
			}
		}
		catch(Exception ex) 
		{
			throw new Exception("파일 복사중 에러가 발생했습니다.");
		}
		finally
		{
			try
			{
				if(fis != null)
				{
					fis.close();
					fis = null;					
				}
				
				if(fos != null)
				{
					fos.close();
					fos = null;					
				}
			}
			catch(Exception ex) {ex.printStackTrace();}
		}
		
	}
	
	// #region 오버로딩 메서드
	/**
	 * <pre>
	 * 타겟 디렉토리의 파일 리스트를 가져온다.
	 * 파일이름 기본값 : 전체 절대경로 없이 파일명만
	 * </pre>
	 * @param targetDirectoryFullPath 타겟 디렉토리 전체 절대경로
	 * @return 파일 리스트
	 * @throws RuntimeException
	 */
	public List<String> getFileNames(String targetDirectoryFullPath) throws RuntimeException
	{
		return getFileNames(targetDirectoryFullPath, false);
	}
	
	/**
	 * <pre>
	 * 타겟 디렉토리의 파일 리스트를 가져온다.
	 * 파일이름 기본값 : 전체 절대경로 없이 파일명만
	 * </pre>
	 * @param targetDirectory 타겟 디렉토리 java.io.File
	 * @return 파일 리스트
	 * @throws RuntimeException
	 */	
	public List<String> getFileNames(File targetDirectory) throws RuntimeException
	{
		return getFileNames(targetDirectory, false);
	}
	
	/**
	 * <p>타겟 디렉토리의 파일 리스트를 가져온다.</p>
	 * @param targetDirectoryFullPath 타겟 디렉토리 전체 절대경로
	 * @param isFullPath 파일 리스트를 전체 절대경로로 가져올것인지 여부
	 * @return 파일 리스트
	 * @throws RuntimeException
	 */
	public List<String> getFileNames(String targetDirectoryFullPath, boolean isFullPath) throws RuntimeException
	{
		return getFileOrDirectoryNames(new File(targetDirectoryFullPath), true, isFullPath);
	}
	
	/**
	 * <p>타겟 디렉토리의 파일 리스트를 가져온다.</p>
	 * @param targetDirectory 타겟 디렉토리 java.io.File
	 * @param isFullPath 파일 리스트를 전체 절대경로로 가져올것인지 여부
	 * @return 파일 리스트
	 * @throws RuntimeException
	 */	
	public List<String> getFileNames(File targetDirectory, boolean isFullPath) throws RuntimeException
	{
		return getFileOrDirectoryNames(targetDirectory, true, isFullPath);
	}
	
	/**
	 * <pre>
	 * 타겟 디렉토리의 파일 리스트를 가져온다.
	 * </pre>
	 * @param targetDirectoryFullPath 타겟 디렉토리 전체 절대경로
	 * @return 파일 리스트
	 * @throws RuntimeException
	 */	
	public List<File> getFiles(String targetDirectoryFullPath) throws RuntimeException
	{
		return getFilesOrDirectorys(new File(targetDirectoryFullPath), true);
	}
	
	/**
	 * <pre>
	 * 타겟 디렉토리의 파일 리스트를 가져온다.
	 * </pre>
	 * @param targetDirectory 타겟 디렉토리 java.io.File
	 * @return 파일 리스트
	 * @throws RuntimeException
	 */	
	public List<File> getFiles(File targetDirectory) throws RuntimeException
	{
		return getFilesOrDirectorys(targetDirectory, true);
	}
	
	/**
	 * <pre>
	 * 타겟 디렉토리의 하위 디렉토리 리스트를 가져온다.
	 * 디렉토리 이름 기본값 : 전체 절대경로 없이 디렉토리명만
	 * </pre>
	 * @param targetDirectoryFullPath 타겟 디렉토리 전체 절대경로
	 * @return 하위 디렉토리 리스트
	 * @throws RuntimeException
	 */
	public List<String> getDirectoryNames(String targetDirectoryFullPath) throws RuntimeException
	{
		return getDirectoryNames(targetDirectoryFullPath, false);
	}

	/**
	 * <pre>
	 * 타겟 디렉토리의 하위 디렉토리 리스트를 가져온다.
	 * 디렉토리 이름 기본값 : 전체 절대경로 없이 디렉토리명만
	 * </pre>
	 * @param targetDirectory 타겟 디렉토리 java.io.File
	 * @return 하위 디렉토리 리스트
	 * @throws RuntimeException
	 */	
	public List<String> getDirectoryNames(File targetDirectory) throws RuntimeException
	{
		return getDirectoryNames(targetDirectory, false);
	}
	
	/**
	 * <p>타겟 디렉토리의 하위 디렉토리 리스트를 가져온다.</p>
	 * @param targetDirectoryFullPath 타겟 디렉토리 전체 절대경로
	 * @param isFullPath 디렉토리 리스트를 전체 절대경로로 가져올것인지 여부
	 * @return 하위 디렉토리 리스트
	 * @throws RuntimeException
	 */
	public List<String> getDirectoryNames(String targetDirectoryFullPath, boolean isFullPath) throws RuntimeException
	{
		return getFileOrDirectoryNames(new File(targetDirectoryFullPath), false, isFullPath);
	}
	
	/**
	 * <p>타겟 디렉토리의 하위 디렉토리 리스트를 가져온다.</p>
	 * @param targetDirectory 타겟 디렉토리 java.io.File
	 * @param isFullPath 디렉토리 리스트를 전체 절대경로로 가져올것인지 여부
	 * @return 하위 디렉토리 리스트
	 * @throws RuntimeException
	 */	
	public List<String> getDirectoryNames(File targetDirectory, boolean isFullPath) throws RuntimeException
	{
		return getFileOrDirectoryNames(targetDirectory, false, isFullPath);
	}
	
	/**
	 * <pre>
	 * 타겟 디렉토리의 하위 디렉토리 리스트를 가져온다.
	 * </pre>
	 * @param targetDirectoryFullPath 타겟 디렉토리 전체 절대경로
	 * @return 디렉토리 리스트
	 * @throws RuntimeException
	 */	
	public List<File> getDirectorys(String targetDirectoryFullPath) throws RuntimeException
	{
		return getFilesOrDirectorys(new File(targetDirectoryFullPath), false);
	}
	
	/**
	 * <pre>
	 * 타겟 디렉토리의 하위 디렉토리 리스트를 가져온다.
	 * </pre>
	 * @param targetDirectoryFullPath 타겟 디렉토리 java.io.File
	 * @return 디렉토리 리스트
	 * @throws RuntimeException
	 */	
	public List<File> getDirectorys(File targetDirectory) throws RuntimeException
	{
		return getFilesOrDirectorys(targetDirectory, false);
	}
	// #endregion
	
	private List<String> getFileOrDirectoryNames(File targetDirectory, boolean isFile, boolean isFullPath) throws RuntimeException
	{
		String fileOrDirectoryName = "";
		List<String> fileOrDirectoryNames = new ArrayList<String>();
		List<File> targetDirectorys = getFilesOrDirectorys(targetDirectory, isFile);
		
		for(File file : targetDirectorys)
		{
			fileOrDirectoryName = (isFullPath) ? file.getAbsolutePath() : file.getName();
			fileOrDirectoryNames.add(fileOrDirectoryName);
		}
		
		return fileOrDirectoryNames;			
	}
		
	private List<File> getFilesOrDirectorys(File targetDirectory, boolean isFile) throws RuntimeException
	{
		if(!targetDirectory.exists())
			throw new RuntimeException("해당 디렉토리 경로가 존재하지 않습니다.");
		
		File[] targetFileOrDirectorys = targetDirectory.listFiles();	
		List<File> fileOrDirectorys = new ArrayList<File>();
		
		for(File fileOrDirectory : targetFileOrDirectorys)
		{
			if(isFile)
			{
				if(fileOrDirectory.isFile())
				{
					fileOrDirectorys.add(fileOrDirectory);
				}				
			}
			else
			{
				if(fileOrDirectory.isDirectory())
				{
					fileOrDirectorys.add(fileOrDirectory);
				}				
			}
		}
		
		return fileOrDirectorys;		
	}
}
