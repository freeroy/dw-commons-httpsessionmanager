package org.developerworld.commons.httpsessionmanager;

import java.util.List;

import javax.servlet.http.HttpSession;

/**
 * session管理器 接口
 * @author Roy Huang
 *
 */
public interface HttpSessionManager{

	/**
	 * 写入session
	 * @param session
	 */
	public void putSession(HttpSession session);

	/**
	 * 写入session
	 * @param key
	 * @param session
	 */
	public void putSession(String key, HttpSession session) ;

	/**
	 * 删除session
	 * @param session
	 */
	public void removeSession(HttpSession session);

	/**
	 * 删除session
	 * @param key
	 */
	public void removeSession(String key) ;

	/**
	 * 判断session是否存在
	 * @param key
	 * @return
	 */
	public boolean containsSession(String key);

	/**
	 * 判断session是否存在
	 * @param session
	 * @return
	 */
	public boolean containsSession(HttpSession session);

	/**
	 * 获取容器session数量
	 * @return
	 */
	public int sessionSize() ;

	/**
	 * 获取所有session集合
	 * @return
	 */
	public List<HttpSession> getSessions();

	/**
	 * 查找session
	 * @param finder
	 * @return
	 */
	public List<HttpSession> getSessions(HttpSessionFinder finder);

	/**
	 * 获取指定session
	 * @param key
	 * @return
	 */
	public HttpSession getSession(String key);
	
	/**
	 * 清空会话信息
	 */
	public void clear();
}
