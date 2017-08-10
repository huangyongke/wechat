package chat;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



/**
 * Servlet implementation class Back
 */
@WebServlet({ "/Back", "/back" })
public class Back extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Back() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8;pageEncoding=UTF-8");
		PrintWriter out=response.getWriter();
		HttpSession session=request.getSession();
		String username = (String)session.getAttribute("username");
		ServletContext application = request.getServletContext();
		if(application.getAttribute("onlineuser")==null)
    	{
    		ArrayList<String> user=new ArrayList<String>();
    		application.setAttribute("onlineuser", user);
    		session.removeAttribute("username");
    		out.print(1);
    		System.out.println("移除用户");
    	}
    	else
    	{
    		@SuppressWarnings("unchecked")
			ArrayList<String> user=(ArrayList<String>) application.getAttribute("onlineuser");
    		user.remove(username);
    		System.out.println("移除用户："+username);
    		application.setAttribute("onlineuser", user);
    		session.removeAttribute("username");
    		out.print("1");
    	}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(request, response);
	}

}
