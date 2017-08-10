package chat;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



import dba.DBAccess;
import dba.Message;

/**
 * Servlet implementation class Validate
 */
@WebServlet({ "/Validate", "/validate" })
public class Validate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Validate() {
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
		String text=request.getParameter("text");
		if(text==null ||text=="")
		{
			out.println("请<a href='login.html'>登录</a>");
			response.addHeader("refresh", "3;url='login.jsp'");
			return;
		}
			
		//text = new String(text.getBytes("ISO-8859-1"),"UTF-8");
		Date data=new Date();
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time=dateformat.format(data);
		HttpSession session=request.getSession();
		String name=(String)session.getAttribute("username");
		if(name != null)
		{
			DBAccess db= new DBAccess();
			Message user =new Message();
			user.setUsername(name);
			user.setTime(time);
			user.setTalks(text);
			db.input(user);
			response.sendRedirect("frame_c.html");
		} else
		{
			out.println("你的账户已过期！请<a href='login.jsp'>重新登录</a>");
			response.addHeader("refresh", "3;url='login.jsp'");
			
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
