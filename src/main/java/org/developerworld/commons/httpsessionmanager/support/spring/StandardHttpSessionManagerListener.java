package org.developerworld.commons.httpsessionmanager.support.spring;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.lang.StringUtils;
import org.developerworld.commons.httpsessionmanager.AbstractHttpSessionManagerListener;
import org.developerworld.commons.httpsessionmanager.HttpSessionManager;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * Session 监听器
 * 
 * @author Roy Huang
 * @version 20110226
 * 
 */
public class StandardHttpSessionManagerListener extends
		AbstractHttpSessionManagerListener implements ServletContextListener {

	private final static String SESSION_MANAGER_BEAN_NAME = "SESSION_MANAGER_BEAN_NAME";

	private HttpSessionManager manager;

	protected HttpSessionManager getHttpSessionManager() {
		return manager;
	}

	/**
	 * 容器关闭
	 */
	public void contextDestroyed(ServletContextEvent arg0) {
		// 不做任何操作，由spring代劳
	}

	/**
	 * 容器启动
	 */
	public void contextInitialized(ServletContextEvent arg0) {
		ServletContext servletContext = arg0.getServletContext();
		String beanName = servletContext
				.getInitParameter(SESSION_MANAGER_BEAN_NAME);
		if (StringUtils.isBlank(beanName))
			throw new RuntimeException("the bean name have not set:"
					+ SESSION_MANAGER_BEAN_NAME);
		WebApplicationContext webApplicationContext = WebApplicationContextUtils
				.getWebApplicationContext(servletContext);
		manager = webApplicationContext.getBean(beanName,
				HttpSessionManager.class);
		if (manager == null)
			throw new RuntimeException("the bean name of " + beanName
					+ " is not fournd!");
	}
}
