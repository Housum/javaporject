package com.thinkgem.jeesite.modules.sys.security;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
/**
 *	自定义一个凭证匹配器
 */
public class ExtendCredentialsMatcher implements CredentialsMatcher{

	/**
	 * @param token 用户在Filter中传进来的  
	 * @param info  这个一般就是realm中返回的info
	 * 
	 */
	@Override
	public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
		MultiAccountToken mt=null;
		if(token instanceof MultiAccountToken){
			mt=(MultiAccountToken) token;
			//这是明文密码
			mt.getCredentials();
			//这是用户名集合  List
			mt.getPrincipal();
			
			//在这里进行密码的验证  
		}
		return false;
	}

}
