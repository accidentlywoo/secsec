package com.my.tag;

import java.io.IOException;

import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class HelloWorldSimpleTag extends SimpleTagSupport {
    @Override
    public void doTag() throws JspException, IOException {
//        getJspContext().getOut().write( "Hello, world!" );
    	JspContext jspContext = this.getJspContext();
    	PageContext pc = (PageContext) jspContext;
    	JspWriter out = pc.getOut();
    	out.print("<h1>Hello</h1>");
    }
}