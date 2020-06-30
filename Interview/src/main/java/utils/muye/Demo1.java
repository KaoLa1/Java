package utils.muye;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.regex.Pattern;

public class Demo1 {

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

    private static String getSubStrByTagName(String str, String tagName){
        
        int tagStartIndex = str.indexOf("<"+tagName);
        int firstTagOverIndex = str.indexOf(">",tagStartIndex)+1;
        int tagEndIndex = str.indexOf("</"+tagName+">");
        String tempStr = str.substring(firstTagOverIndex,tagEndIndex);
        System.out.println("<"+tagName+">"+tempStr+"</"+tagName+">");
        return "<"+tagName+">"+tempStr+"</"+tagName+">";
    }

    public static void main(String[] args) {
		/*String html = "hearInfoBeginAtHear<tr><th class=\"vClock hClock\" style=\"z-index:2001;height:20px;\" colspan=\"1\" rowspan=\"2\" nowrap=\"\">���۵�Ԫ</th><th id=\"K25504\" class=\"vClock \" colspan=\"2\" rowspan=\"1\" nowrap=\"\" style=\"z-index:1000;border-left:0px;border-top:0px;padding-left:5px;padding-right:5px;height:20px;\">����1</th><th id=\"K25505\" class=\"vClock \" colspan=\"2\" rowspan=\"1\" nowrap=\"\" style=\"z-index:1000;border-left:0px;border-top:0px;padding-left:5px;padding-right:5px;height:20px;\">����2</th><th id=\"K25468\" class=\"vClock \" colspan=\"1\" rowspan=\"2\" nowrap=\"\" style=\"z-index:1000;border-left:0px;border-top:0px;padding-left:5px;padding-right:5px;height:20px;\">��̯ǰ������������</th><th id=\"K25469\" class=\"vClock \" colspan=\"1\" rowspan=\"2\" nowrap=\"\" style=\"z-index:1000;border-left:0px;border-top:0px;padding-left:5px;padding-right:5px;height:20px;\">��̯ǰ�����������</th><th id=\"K25470\" class=\"vClock \" colspan=\"1\" rowspan=\"2\" nowrap=\"\" style=\"z-index:1000;border-left:0px;border-top:0px;padding-left:5px;padding-right:5px;height:20px;\">��̯������</th><th id=\"K25471\" class=\"vClock \" colspan=\"1\" rowspan=\"2\" nowrap=\"\" style=\"z-index:1000;border-left:0px;border-top:0px;padding-left:5px;padding-right:5px;height:20px;\">��̯������������</th><th id=\"K25472\" class=\"vClock \" colspan=\"1\" rowspan=\"2\" nowrap=\"\" style=\"z-index:1000;border-left:0px;border-top:0px;padding-left:5px;padding-right:5px;height:20px;\">��̯�󣨿��������</th></tr>\\n<tr><th id=\"K25504_K25464\" class=\"vClock \" colspan=\"1\" rowspan=\"1\" nowrap=\"\" style=\"z-index:1000;border-left:0px;border-top:0px;padding-left:5px;padding-right:5px;height:20px;\">��̯�û���</th><th id=\"K25504_K25465\" class=\"vClock \" colspan=\"1\" rowspan=\"1\" nowrap=\"\" style=\"z-index:1000;border-left:0px;border-top:0px;padding-left:5px;padding-right:5px;height:20px;\"> ��̯�û�����������</th><th id=\"K25505_K25466\" class=\"vClock \" colspan=\"1\" rowspan=\"1\" nowrap=\"\" style=\"z-index:1000;border-left:0px;border-top:0px;padding-left:5px;padding-right:5px;height:20px;\">��̯�û����������</th><th id=\"K25505_K25467\" class=\"vClock \" colspan=\"1\" rowspan=\"1\" nowrap=\"\" style=\"z-index:1000;border-left:0px;border-top:0px;padding-left:5px;padding-right:5px;height:20px;\">��̯ǰ����</th></tr>\\n";
		html = "hearInfoBeginAtHear<tr><th class=\"vClock hClock\" style=\"z-index:2001;height:20px;\" colspan=\"1\" rowspan=\"2\" nowrap=\"\">销售单元</th><th id=\"K25504\" class=\"vClock \" colspan=\"2\" rowspan=\"1\" nowrap=\"\" style=\"z-index:1000;border-left:0px;border-top:0px;padding-left:5px;padding-right:5px;height:20px;\">标题1</th><th id=\"K25505\" class=\"vClock \" colspan=\"2\" rowspan=\"1\" nowrap=\"\" style=\"z-index:1000;border-left:0px;border-top:0px;padding-left:5px;padding-right:5px;height:20px;\">标题2</th><th id=\"K25468\" class=\"vClock \" colspan=\"1\" rowspan=\"2\" nowrap=\"\" style=\"z-index:1000;border-left:0px;border-top:0px;padding-left:5px;padding-right:5px;height:20px;\">分摊前（移网）收入</th><th id=\"K25469\" class=\"vClock \" colspan=\"1\" rowspan=\"2\" nowrap=\"\" style=\"z-index:1000;border-left:0px;border-top:0px;padding-left:5px;padding-right:5px;height:20px;\">分摊前（宽带）收入</th><th id=\"K25470\" class=\"vClock \" colspan=\"1\" rowspan=\"2\" nowrap=\"\" style=\"z-index:1000;border-left:0px;border-top:0px;padding-left:5px;padding-right:5px;height:20px;\">分摊后收入</th><th id=\"K25471\" class=\"vClock \" colspan=\"1\" rowspan=\"2\" nowrap=\"\" style=\"z-index:1000;border-left:0px;border-top:0px;padding-left:5px;padding-right:5px;height:20px;\">分摊后（移网）收入</th><th id=\"K25472\" class=\"vClock \" colspan=\"1\" rowspan=\"2\" nowrap=\"\" style=\"z-index:1000;border-left:0px;border-top:0px;padding-left:5px;padding-right:5px;height:20px;\">分摊后（宽带）收入</th></tr>\\n<tr><th id=\"K25504_K25464\" class=\"vClock \" colspan=\"1\" rowspan=\"1\" nowrap=\"\" style=\"z-index:1000;border-left:0px;border-top:0px;padding-left:5px;padding-right:5px;height:20px;\">分摊用户数</th><th id=\"K25504_K25465\" class=\"vClock \" colspan=\"1\" rowspan=\"1\" nowrap=\"\" style=\"z-index:1000;border-left:0px;border-top:0px;padding-left:5px;padding-right:5px;height:20px;\"> 分摊用户（移网）数</th><th id=\"K25505_K25466\" class=\"vClock \" colspan=\"1\" rowspan=\"1\" nowrap=\"\" style=\"z-index:1000;border-left:0px;border-top:0px;padding-left:5px;padding-right:5px;height:20px;\">分摊用户（宽带）数</th><th id=\"K25505_K25467\" class=\"vClock \" colspan=\"1\" rowspan=\"1\" nowrap=\"\" style=\"z-index:1000;border-left:0px;border-top:0px;padding-left:5px;padding-right:5px;height:20px;\">分摊前收入</th></tr>\\n";
//		String s = formatHtml(html);
//		s.contains("d");
//		System.out.println(s);
        if(html.contains("padding-left")){
            html = html.replace("padding-left", "PADDING-LEFT");
            System.out.println(html);
        }
        String aa = "adbc";
        if("aa".indexOf("b")>0){
            System.out.println("ddd");
        }
        System.out.println(aa.substring(aa.indexOf("a"),aa.indexOf("a")+1));
        System.out.println(aa.replaceAll("a","A"));
//        getSubStrByTagName(html,"tr");

        String m = "<th class=\"vClock hClock\" style=\"z-index:2001;height:20px;\" colspan=\"1\" rowspan=\"2\" nowrap=\"\">销售单元</th>";
        String substring = m.substring(m.indexOf("colspan=") + 9, m.indexOf("colspan=") + 10);
        String substring1 = m.substring(m.indexOf("rowspan=") + 9, m.indexOf("rowspan=") + 10);
        System.out.println(substring);
        System.out.println(substring1);

        String n = "<th class=\"vClock hClock\" style=\"z-index:2001;height:20px;\"  =\"1\" rowspan=\"2\" nowrap=\"\">销售单元</th>";
        if(n.indexOf("rowSpan")>0){
            System.out.println("aaaaaa");
        }*/

       /* String tdTemp = "<TH id=K25504 class=\"vClock \" style=\"BORDER-TOP: 0px; HEIGHT: 20px; PADDING-LEFT: 5px; BORDER-LEFT: 0px; Z-INDEX: 1000; TOP: 0px; PADDING-RIGHT: 5px\" colSpan=2 noWrap>标题1</TH>";
        String suojinStr  = tdTemp.substring(tdTemp.indexOf("PADDING-LEFT: ")+"PADDING-LEFT: ".length(),tdTemp.indexOf("px",tdTemp.indexOf("PADDING-LEFT: ")+"PADDING-LEFT ".length()));
        String as = "<th id=\"K25504\" class=\"vClock \" colspan=\"2\" rowspan=\"1\" nowrap=\"\" style=\"z-index:1000;" +
                "border-left:0px;border-top:0px;PADDING-LEFT: 5px;padding-right:5px;height:20px;\">标题1</th>";
        String suojinStr1 = as.substring(as.indexOf("PADDING-LEFT: ")+"PADDING-LEFT: ".length(),
                as.indexOf("px",as.indexOf("PADDING-LEFT: ")+"PADDING-LEFT ".length()));
        System.out.println(suojinStr+suojinStr1);*/
        String a = " 0";
        Pattern p = Pattern.compile("[^0-9]");
        String suojinStr1 = p.matcher(a).replaceAll("");
        int suojinInt = Integer.valueOf(suojinStr1);
        System.out.println(suojinInt);


    }

}

