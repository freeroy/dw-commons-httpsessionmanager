package org.developerworld.commons.httpsessionmanager.impl;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.lang.StringUtils;
import org.developerworld.commons.cache.Cache;
import org.developerworld.commons.httpsessionmanager.AbstractHttpSessionManagerListener;
import org.developerworld.commons.httpsessionmanager.HttpSessionManager;

/**
 * Session 监听器
 * 
 * @author Roy Huang
 * 
 */
public abstract class AbstractCacheHttpSessionManagerListener extends
		AbstractHttpSessionManagerListener implements ServletContextListener {

	private final static String HTTP_SESSION_MANAGER_SERVLET_CONTEXT_ATTRIBUTE_NAME = "HTTP_SESSION_MANAGER_SERVLET_CONTEXT_ATTRIBUTE_NAME";

	private CacheHttpSessionManager manager;

	@Override
	protected HttpSessionManager getHttpSessionManager() {
		return manager;
	}

	public void contextDestroyed(ServletContextEvent arg0) {
		manager.destroy();
		manager = null;
	}

	public void contextInitialized(final ServletContextEvent arg0) {
		manager = new CacheHttpSessionManager(buildCache(arg0));
		manager.init();
		String attributeName = arg0.getServletContext().getInitParameter(
				HTTP_SESSION_MANAGER_SERVLET_CONTEXT_ATTRIBUTE_NAME);
		if (StringUtils.isNotBlank(attributeName))
			arg0.getServletContext().setAttribute(attributeName, manager);
	}

	abstract protected Cache buildCache(ServletContextEvent arg0);

}
