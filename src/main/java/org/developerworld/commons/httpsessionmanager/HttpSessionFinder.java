package org.developerworld.commons.httpsessionmanager;

import javax.servlet.http.HttpSession;

/**
 * 会话过滤器
 * @author Roy Huang
 *
 */
public interface HttpSessionFinder {

	/**
	 * 回调执行方法，符合的返回true，否则false
	 * @param session
	 * @return
	 */
	public boolean match(HttpSession session);

}
