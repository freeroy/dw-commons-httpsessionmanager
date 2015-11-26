package org.developerworld.commons.httpsessionmanager.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpSession;

import org.developerworld.commons.httpsessionmanager.HttpSessionFinder;
import org.developerworld.commons.httpsessionmanager.HttpSessionKeyBuilder;
import org.developerworld.commons.httpsessionmanager.HttpSessionManager;

/**
 * 标准会话管理器
 * 
 * @author Roy Huang
 *
 */
public class StandardHttpSessionManager implements HttpSessionManager {

	private Map<String, HttpSession> sessions;

	private HttpSessionKeyBuilder httpSessionKeyBuilder;

	public void destroy() {
		sessions.clear();
		sessions = null;
		httpSessionKeyBuilder = null;
	}

	public void init() {
		httpSessionKeyBuilder = new StandardHttpSessionKeyBuilder();
		sessions = new HashMap<String, HttpSession>();
	}

	private String getSessionKey(HttpSession session) {
		return httpSessionKeyBuilder.buildSessionKey(session);
	}

	public void putSession(HttpSession session) {
		putSession(getSessionKey(session), session);
	}

	public void putSession(String key, HttpSession session) {
		sessions.put(key, session);
	}

	public void removeSession(HttpSession session) {
		removeSession(getSessionKey(session));
	}

	public void removeSession(String key) {
		sessions.remove(key);
	}

	public boolean containsSession(String key) {
		return sessions.containsKey(key);
	}

	public boolean containsSession(HttpSession session) {
		return containsSession(getSessionKey(session));
	}

	public int sessionSize() {
		return sessions.size();
	}

	public List<HttpSession> getSessions() {
		return getSessions(null);
	}

	public List<HttpSession> getSessions(HttpSessionFinder finder) {
		List<HttpSession> rst = new ArrayList<HttpSession>();
		Iterator<Entry<String, HttpSession>> iterator = sessions.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<String, HttpSession> entry = iterator.next();
			HttpSession session = entry.getValue();
			if (session != null && (finder == null || finder.match(session)))
				rst.add(session);
		}
		return rst;
	}

	public HttpSession getSession(String key) {
		return sessions.get(key);
	}

	public void clear() {
		sessions.clear();
	}

}
