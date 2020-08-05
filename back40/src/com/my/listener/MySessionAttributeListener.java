package com.my.listener;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

public class MySessionAttributeListener implements HttpSessionAttributeListener {

	private int loginedCnt;
	
    public MySessionAttributeListener() {
        // TODO Auto-generated constructor stub
    }

    public void attributeAdded(HttpSessionBindingEvent se)  {
    	HttpSession session = se.getSession();
    	String attrName = se.getName();
    	if(attrName.equals("loginInfo")) {
    		loginedCnt++;
    		String attrValue = (String) se.getValue();
    		System.out.println("총 로그인된 사용자 수 : " + loginedCnt +", 로그인된 ID : "+attrValue);
    	}
    }

    public void attributeRemoved(HttpSessionBindingEvent se)  { 
    	HttpSession session = se.getSession();
    	String attrName = se.getName();
    	if(attrName.equals("loginInfo")) {
    		loginedCnt--;
    		String attrValue = (String) se.getValue();
    		System.out.println("총 로그아웃된 사용자 수 : " + loginedCnt +", 로그인된 ID : "+attrValue);
    	}
    }

    public void attributeReplaced(HttpSessionBindingEvent se)  { 
         // TODO Auto-generated method stub
    }
}
