package com.my.exception;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

public class MethodOverridingExceptionSample {
	public class P{
		public void m() throws IOException{
		}
	}
	
	public class C extends P{
//		public void m() throws IOException {} 성공
//		public void m() throws FileNotFoundException {} 성공 하위 Exception은 사용가능
		public void m(){}
//		public void m() throws {} 컴파일 에러
//		public void m() throws SQLException {} 컴파일 에러
	}
}
