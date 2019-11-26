package com.watchme.dspcoresupport.utils;

import java.util.*;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.watchme.dspcoresupport.utils.resp.ResponseResult;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.util.TablesNamesFinder;

/**
 * SQL 表解析
 *
 * @author malz on 2019/8/8
 */
public class JSQLParserUtil {
    public static String parserTable(String sql) {
        StringBuffer buf = new StringBuffer();
        try {
            Statement statement = CCJSqlParserUtil.parse(sql);
            Select selectStatement = (Select) statement;
            TablesNamesFinder tablesNamesFinder = new TablesNamesFinder();
            List tableList = tablesNamesFinder.getTableList(selectStatement);
            for (Iterator iter = tableList.iterator(); iter.hasNext(); ) {
                String tableName = (String) iter.next();
                buf.append(tableName + ",");
            }
        } catch (JSQLParserException e) {
            e.printStackTrace();
        }
        return buf.toString().substring(0, buf.length() - 1);
    }

    public static Map<String, Object> ParseTree(ResponseResult responseResult) {
        Map treeMap = new HashMap();
        String s = JSON.toJSONString(responseResult);
        List list = new ArrayList();
        list.add(JSONObject.parseObject(s));
        treeMap.put("treeData", list);
        return treeMap;
    }

}
