package com.hotusm.jackson;

import java.util.ArrayList;
import java.util.List;

public class User {
	
	private String name;
	private List<Article> articles=new ArrayList<Article>();
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Article> getArticles() {
		return articles;
	}
	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}
	
}
