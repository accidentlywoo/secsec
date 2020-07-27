package com.my.control;

import java.io.IOException;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		ServletInputStream sis = req.getInputStream();
		
		Scanner scanner = new Scanner(sis, "UTF-8");
		
		while (scanner.hasNext()) {
			System.out.println(scanner.next());
		}
		scanner.close();
		sis.close();
	}
}
