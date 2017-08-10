package chat;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dba.DBAccess;
import dba.Users;

/**
 * Servlet implementation class Insert
 */
@WebServlet(
		name = "Register", 
		urlPatterns = { 
				"/Register", 
				"/register"
		})
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
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
		String username=request.getParameter("name");
		String password=request.getParameter("password");
		String imagecheck=request.getParameter("imagecheck");
		if(username==null || password==null)
		{
			out.println("请正确");
			System.out.println("请正确注册");
			out.println("<a href='register.jsp'>注册</a>");
			response.addHeader("refresh", "2;url=register.jsp");
			return;
		}
		//username = new String(username.getBytes("ISO-8859-1"),"UTF-8");
		//password = new String(password.getBytes("ISO-8859-1"),"UTF-8");
		HttpSession session=request.getSession();
		int answer=((Integer)session.getAttribute("answer")).intValue();
		String image=Integer.toString(answer);
		if(image.equals(imagecheck)){
			DBAccess db=new DBAccess();
			if(!db.isexist(username))
			{
				Users u=new Users();
				u.setUsername(username);
				u.setPassword(password);
				if(db.register(u)){
					out.println("1");
				} else
				{
					out.println("0");
				}
					
				/*out.println("注册成功");
				response.addHeader("re户名已存在");
				response.addHeader("reffresh", "2;url=login.jsp");*/
			}
			else{
				out.println("0");
				/*out.println("注册失败！用resh", "2;url=register.html");*/
			}
		}else
			out.print("2");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(request, response);
	}

}
