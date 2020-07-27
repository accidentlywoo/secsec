package com.my.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletOutputStream;

public class Download {

	private String realPath;
	private OutputStream os;
	public Download(String realPath, OutputStream os) throws IOException {
		this.realPath = realPath;
		this.os = os;
		
		FileInputStream fis = null;
		fis = new FileInputStream(realPath);
		byte[] bArr = new byte[1024];
		int readCnt = -1; // 읽은 바이트 수
		while ((readCnt = fis.read(bArr)) != -1) {
			os.write(bArr, 0, readCnt);
		}
		fis.close();
		os.close();
	}
	
}
