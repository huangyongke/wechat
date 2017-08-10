package chat;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import dba.DBAccess;
import dba.Users;

/**
 * Servlet implementation class Picture
 */
@WebServlet({ "/Picture", "/picture" })
public class Picture extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Picture() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
        DiskFileItemFactory factory = new DiskFileItemFactory();
        response.setCharacterEncoding("UTF-8");
        File repository = new File("F:\\temp");
        factory.setRepository(repository);
        ServletFileUpload upload = new ServletFileUpload(factory);
        PrintWriter out = response.getWriter();
        try {
			List<FileItem> items = upload.parseRequest(request);
			int i=items.size();
			for(int j=0;j<i;j++)
			{
				HttpSession session=request.getSession();
				String username=(String)session.getAttribute("username");
				//String name=items.get(j).getName();
				//items.get(j).write(new File("F:\\temp1\\"+username+"\\"+name));
				if(items.get(j).getSize()<=65*1024){
					byte[] buffer=items.get(j).get();
					DBAccess db=new DBAccess();
					Users u=new Users();
					u.setUsername(username);
					u.setImages(buffer);
					if(db.putpicture(u))
						response.sendRedirect("back.jsp");
					else
					{
						out.print("ÉÏ´«Í¼ÏñÊ§°Ü<a href='back.jsp'>·µ»Ø</a>");
						//response.addHeader("refresh","3;back.jsp");
					}
				}else {
					out.print("ÕÕÆ¬Ì«´óÉÏ´«Í¼ÏñÊ§°Ü<a href='back.jsp'>·µ»Ø</a>");
					//response.addHeader("refresh","3;back.jsp");
				}
			}
				
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
