package chat;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dba.DBAccess;
import dba.Users;

/**
 * Servlet implementation class Image
 */
@WebServlet({ "/Images", "/images" })
public class Images extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Images() {
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
		response.setContentType("image/jpeg");
		response.addDateHeader("expires", 0);  
        response.addHeader("Cache-Control", "no-cache");  
        response.addHeader("Pragma", "no-cache"); 
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        DBAccess db=new DBAccess();
        Users u=new Users();
        u.setUsername(username);
        ArrayList<String> user = new ArrayList<String>();
        user.add(username);
        byte[] buffer=db.getpicture(user).get(0).getImages();
        if(buffer != null){
        	InputStream buffin = new ByteArrayInputStream(buffer);
        	BufferedImage img = ImageIO.read(buffin);
        	Image image = img.getScaledInstance(150, 80, Image.SCALE_DEFAULT);
        	BufferedImage i=new BufferedImage(150, 80, BufferedImage.TYPE_INT_RGB);
        	Graphics g=i.getGraphics();
        	//Color c=Color.WHITE;
        	g.drawRect(1, 1,150, 80);
        	g.drawImage(image, 0, 0, null);
       	 	g.dispose();
        	ImageIO.write(i, "jpeg",response.getOutputStream());
        	//response.getOutputStream().write(buffer, 0, buffer.length);
        } else{
        	
        }
        	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
