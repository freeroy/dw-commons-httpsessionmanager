package org.developerworld.commons.httpsessionmanager;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * 抽象Session管理器 监听器
 * 
 * @author Roy Huang
 * 
 */
public abstract class AbstractHttpSessionManagerListener implements HttpSessionListener {

	abstract protected HttpSessionManager getHttpSessionManager();

	/**
	 * 会话创建执行
	 */
	public void sessionCreated(HttpSessionEvent event) {
		getHttpSessionManager().putSession(event.getSession());
	}

	/**
	 * 会话注销执行
	 */
	public void sessionDestroyed(HttpSessionEvent event) {
		getHttpSessionManager().removeSession(event.getSession());
	}
}
