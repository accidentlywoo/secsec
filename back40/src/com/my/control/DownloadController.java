package com.my.control;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DownloadController implements Controller {
	private static final long serialVersionUID = 1L;
	private String realPath;
	public DownloadController() {
	}

	public DownloadController(String realPath) {
		this.realPath = realPath;
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("name");

		response.setContentType("application/octet-stream; charset=UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode(name, "UTF-8") + "\"");

		ServletOutputStream sos = response.getOutputStream();
		com.my.model.Download download = new com.my.model.Download(realPath, sos);
		return null;
	}

	public String getRealPath() {
		return realPath;
	}

	public void setRealPath(String realPath) {
		this.realPath = realPath;
	}
}