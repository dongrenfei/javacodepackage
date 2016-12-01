import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HandleHTMLTag {

	private static final String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式 
	
	public static void main(String[] args) {
		String htmlStr = "<![CDATA[<div id=\"fb-root\"></div><p>[&hellip;]When\n speaking there&rsquo;s at Content Marketing World recently, I said something that surprised a few people: Modern sales and marketing require both disciplines to <a href=\"http://www.ianaltman.com/2014/04/22/why-you-cant-separate-sales-and-marketing/\" target=\"_blank\">act as one</a>.";
		htmlStr = htmlStr.replaceAll("\n", "");
		Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);  
        Matcher m_html = p_html.matcher(htmlStr);  
        htmlStr = m_html.replaceAll(""); // 过滤html标签
        htmlStr.replaceAll("\n", "");
        System.out.println(htmlStr);
	}

}
