package com.watchme.dspcoresupport.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

public class IMap extends HashMap<String, Object> {
	
	HttpServletRequest request;
	private String charset = "UTF-8";
	private static final long serialVersionUID = 2973700516712219678L;
	Map<String, Object> returnMap = null;
	
	// 普通的Map
	public IMap() {
		returnMap = new HashMap<String, Object>();
	}
	
	// 从页面获取数据
	public IMap(HttpServletRequest request) {
		this.request = request;
		// 返回值Map
		returnMap = new HashMap<String, Object>();
		// 参数Map
		//@SuppressWarnings("unchecked")
		@SuppressWarnings("unchecked")
		Map<String, String[]> properties = request.getParameterMap();
		Set<Entry<String, String[]>> entrySet = properties.entrySet();
		for (Entry<String, String[]> entry : entrySet) {
			String key = (String) entry.getKey() == null ? null : convert2Decode((String) entry.getKey(), charset);
			Object valueObj = entry.getValue();
			String value = "";
			if(null == valueObj){
				value = "";
			}else if(valueObj instanceof String[]){
				String[] values = (String[])valueObj;
				for(int i=0;i<values.length;i++){
					value = values[i].trim();
					if (request.getMethod().equalsIgnoreCase("get")) {
						value = values == null ? null : convert2Character(value, charset);
					}
					value = values == null ? null : convert2Decode(value, charset) + ",";
				}
				value = value.substring(0, value.length()-1);
			}else{
				value = valueObj.toString();
			}
			returnMap.put(key, value);
		}
	}


	public Object get(Object key) {
		return returnMap.get(key);
	}

	// 本方法只能用于从页面获取数组，例如相同名字的checkbox
	public String[] getValues(Object key) {
		return request == null ? null : request.getParameterValues((String) key);
	}

	public String getString(Object key) {
		Object o = get(key);
		return o == null ? null : o.toString();
	}

	public String getString(String name, String defaultValue) {
		String value = getString(name);
		return value == null ? defaultValue : value;
	}

	public String[] getNames() {
		String[] names = (String[]) keySet().toArray(new String[0]);
		Arrays.sort(names);
		return names;
	}

	/**
	 * get int
	 * 
	 * @param name
	 * @return int
	 */
	public int getInt(String name) {
		return getInt(name, 0);
	}

	/**
	 * get int
	 * 
	 * @param name
	 * @param defaultValue
	 * @return int
	 */
	public int getInt(String name, int defaultValue) {
		String value = getString(name, "");
		return "".equals(value) ? defaultValue : Integer.parseInt(value);
	}

	/**
	 * get double
	 * 
	 * @param name
	 * @return double
	 */
	public double getDouble(String name) {
		return getDouble(name, 0);
	}

	/**
	 * get double
	 * 
	 * @param name
	 * @param defaultValue
	 * @return double
	 */
	public double getDouble(String name, double defaultValue) {
		String value = getString(name, "");
		return "".equals(value) ? defaultValue : Double.parseDouble(value);
	}

	/**
	 * get boolean
	 * 
	 * @param name
	 * @return boolean
	 */
	public boolean getBoolean(String name) {
		return getBoolean(name, false);
	}

	/**
	 * get boolean
	 * 
	 * @param name
	 * @param defaultValue
	 * @return boolean
	 */
	public boolean getBoolean(String name, boolean defaultValue) {
		String value = getString(name, "");
		return "".equals(value) ? defaultValue : Boolean.valueOf(value)
				.booleanValue();
	}

	public Object put(String key, Object value) {
		return returnMap.put(key, value);
	}

	public Object remove(Object key) {
		return returnMap.remove(key);
	}

	public void clear() {
		returnMap.clear();
	}

	public boolean containsKey(Object key) {
		return returnMap.containsKey(key);
	}

	public boolean containsValue(Object value) {
		return returnMap.containsValue(value);
	}

	public Set<Entry<String, Object>> entrySet() {
		return returnMap.entrySet();
	}

	public boolean isEmpty() {
		return returnMap.isEmpty();
	}

	public Set<String> keySet() {
		return returnMap.keySet();
	}

	public void putAll(Map<? extends String, ? extends Object> map) {
		returnMap.putAll(map);
	}

	public int size() {
		return returnMap.size();
	}

	public Collection<Object> values() {
		return returnMap.values();
	}

	private static String convert2Decode(String target, String charset){
		try {
			System.out.println("---------URLDecoder处理前：--------" + target +"--->处理后===" + URLDecoder.decode(target, charset));
			return URLDecoder.decode(target, charset);
		} catch (UnsupportedEncodingException e) {
			return target;
		}
	}
	
	public String convert2Character(String target, String charset) {
        System.out.println("编码转换之前：" + target);
        try {
            return new String(target.trim().getBytes("ISO-8859-1"), charset);
        } catch (UnsupportedEncodingException e) {
        	e.printStackTrace();
            return target;
        }
    }
}
