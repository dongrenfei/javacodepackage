import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.velocity.Template;
import org.apache.velocity.context.Context;
import org.apache.velocity.tools.view.VelocityViewServlet;

public class Hello extends VelocityViewServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected Template handleRequest(HttpServletRequest request, HttpServletResponse response, Context ctx) {
		ctx.put("var", "Turtle"); // set $var to "Turtle"
		return getTemplate("hello.vm");// template name
	}
}