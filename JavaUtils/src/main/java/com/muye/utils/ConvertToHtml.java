package com.muye.utils;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class ConvertToHtml {

	public static String formatHtml(String html) {
		if (StringUtils.isNotBlank(html)) {
			try {
				Document doc = Jsoup.parseBodyFragment(html);
				html = doc.body().html();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return html;
	}

}
