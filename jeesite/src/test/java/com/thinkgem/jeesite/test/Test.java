package com.thinkgem.jeesite.test;

import org.apache.commons.lang3.StringEscapeUtils;

public class Test {

	public static void main(String[] args) {
		String escapeHtml4 = StringEscapeUtils.escapeHtml4("<a href='#'>链接</a>");
		String unescapeHtml4 = StringEscapeUtils.unescapeHtml4(escapeHtml4);
		System.out.println(escapeHtml4);
		System.out.println(unescapeHtml4);
	}
}
