package chat;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ImageCheck
 */
@WebServlet({ "/CheckImage", "/checkimage" })
public class CheckImage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckImage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("image/jpeg");
		response.addDateHeader("expires", 0);  
        response.addHeader("Cache-Control", "no-cache");  
        response.addHeader("Pragma", "no-cache");  
        int width=100;
        int heigth=30;
		BufferedImage images=new BufferedImage(width, heigth,BufferedImage.TYPE_INT_RGB);
		Graphics g=images.getGraphics();
		Random random=new Random();
		Color c = Color.WHITE; 
		g.setColor(c);
		g.fillRect(0, 0, width,30);
		c = Color.BLUE;  
        g.setColor(c);  
        g.drawRect(1, 1,width-2, heigth-2);
        c=Color.GREEN;
        g.setColor(c);
        for(int i=0;i<5;i++)
        {
        	g.drawLine(random.nextInt(width), random.nextInt(heigth), random.nextInt(width), random.nextInt(heigth));
        }
        int a=random.nextInt(10);
        int b=random.nextInt(10);
        HttpSession session=request.getSession();
        ServletContext application = session.getServletContext();
        c=Color.RED;
        g.setColor(c);
        Font f = new Font("����",Font.PLAIN,20);  
        g.setFont(f);  
        Graphics2D g2d = (Graphics2D) g;  
        g2d.rotate(0.05); 
        int j=random.nextInt(1);
        if(j==0){
        	
        	g.drawString(a+"+"+b+"=", 10, 20);
        	
        	session.setAttribute("answer", a+b);
        	application.setAttribute("session1", session);
        }
        else
        {
        	g.drawString(a+"-"+b+"=", 10, 20);
        	session.setAttribute("answer", a-b);
        	application.setAttribute("session", session);
        }
        ImageIO.write(images, "jpeg",response.getOutputStream());
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
