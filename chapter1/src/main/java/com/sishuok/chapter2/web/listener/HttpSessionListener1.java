package com.sishuok.chapter2.web.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class HttpSessionListener1 implements HttpSessionListener{

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		System.out.println("����session ");
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		System.out.println("����session ");
	}

}
