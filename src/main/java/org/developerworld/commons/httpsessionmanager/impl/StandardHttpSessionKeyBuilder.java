package org.developerworld.commons.httpsessionmanager.impl;

import javax.servlet.http.HttpSession;

import org.developerworld.commons.httpsessionmanager.HttpSessionKeyBuilder;

/**
 * 基础session key构建器
 * @author Roy Huang
 * @version 20140611
 *
 */
public class StandardHttpSessionKeyBuilder implements HttpSessionKeyBuilder{

	public String buildSessionKey(HttpSession session) {
		return session.getId();
	}

}
