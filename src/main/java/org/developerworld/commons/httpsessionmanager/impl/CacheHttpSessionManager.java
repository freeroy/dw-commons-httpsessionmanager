package org.developerworld.commons.httpsessionmanager.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.developerworld.commons.cache.Cache;
import org.developerworld.commons.httpsessionmanager.HttpSessionFinder;
import org.developerworld.commons.httpsessionmanager.HttpSessionKeyBuilder;
import org.developerworld.commons.httpsessionmanager.HttpSessionManager;

/**
 * 抽象cache session管理器
 * 
 * @author Roy Huang
 * 
 */
public class CacheHttpSessionManager implements HttpSessionManager {

	private HttpSessionKeyBuilder httpSessionKeyBuilder;

	private Cache cache;

	public CacheHttpSessionManager(Cache cache) {
		this.cache = cache;
	}

	public void setHttpSessionKeyBuilder(
			HttpSessionKeyBuilder httpSessionKeyBuilder) {
		this.httpSessionKeyBuilder = httpSessionKeyBuilder;
	}

	/**
	 * 初始化方法
	 */
	synchronized public void init() {
		if (httpSessionKeyBuilder == null)
			httpSessionKeyBuilder = new StandardHttpSessionKeyBuilder();
	}

	synchronized public void destroy() {
		clear();
		httpSessionKeyBuilder = null;
	}

	synchronized public void clear() {
		cache.removeAll();
	}

	private String getSessionKey(HttpSession session) {
		return httpSessionKeyBuilder.buildSessionKey(session);
	}

	public void putSession(HttpSession session) {
		putSession(getSessionKey(session), session);
	}

	public void putSession(String key, HttpSession session) {
		cache.put(key, session);
	}

	public void removeSession(HttpSession session) {
		removeSession(getSessionKey(session));
	}

	public void removeSession(String key) {
		cache.remove(key);
	}

	public boolean containsSession(HttpSession session) {
		return containsSession(getSessionKey(session));
	}

	public boolean containsSession(String key) {
		return getSession(key) != null;
	}

	public int sessionSize() {
		return cache.size();
	}

	public HttpSession getSession(String key) {
		return (HttpSession) cache.get(key);
	}

	public List<HttpSession> getSessions() {
		return getSessions(null);
	}

	public List<HttpSession> getSessions(HttpSessionFinder finder) {
		List<HttpSession> rst = new ArrayList<HttpSession>();
		Set<String> keys = cache.getKeys();
		if (keys != null) {
			for (String key : keys) {
				HttpSession session = getSession(key);
				if (session != null
						&& (finder == null || finder.match(session)))
					rst.add(session);
			}
		}
		return rst;
	}

}
