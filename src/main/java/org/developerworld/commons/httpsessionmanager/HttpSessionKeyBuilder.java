package org.developerworld.commons.httpsessionmanager;

import javax.servlet.http.HttpSession;

/**
 * session健构造器
 * @author Roy Huang
 * @version 20140611
 *
 */
public interface HttpSessionKeyBuilder {

	/**
	 * 创建session key
	 * @param session
	 * @return
	 */
	public String buildSessionKey(HttpSession session);
}
