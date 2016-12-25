package com.thinkgem.jeesite.modules.sys.security;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.authc.HostAuthenticationToken;
import org.apache.shiro.authc.RememberMeAuthenticationToken;

import com.google.common.collect.Lists;
/**
 * 多账号的Token
 * @author Hotusm
 *
 */
public class MultiAccountToken implements HostAuthenticationToken, RememberMeAuthenticationToken{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 可以在登录页面中输入多个账号   但是只要其中的一个账号满足就可以了
	 */
	private final List<String> accounts=Lists.newArrayList();
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 用户的ip
	 */
	private String host;
	/**
	 * 是否记住
	 */
	private boolean rememberMe;

	@Override
	public Object getPrincipal() {
		return new ArrayList<>(accounts);
	}
	
	@Override
	public Object getCredentials() {
		return getPassword();
	}

	@Override
	public boolean isRememberMe() {
		return rememberMe;
	}
	
	public void setRememberMe(boolean rememberMe) {
		this.rememberMe = rememberMe;
	}
	public void setHost(String host) {
		this.host = host;
	}
	@Override
	public String getHost() {
		return host;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public void setAccount(String account){
		accounts.add(account);
	}
}
