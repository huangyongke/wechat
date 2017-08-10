package dba;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DBAccess {
	public DBAccess(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("ÕÒ²»µ½Çý¶¯"); 
			e.printStackTrace();
		}
	}
	public boolean register(Users users){
		String url="jdbc:mysql://localhost:3306/wechat?useUnicode=true&characterEncoding=utf-8&useSSL=false";
			try {
				Connection conn= DriverManager.getConnection(url,"root","123456");
				PreparedStatement ps=conn.prepareStatement("insert into user(user_name,password) values(?,?)");
				ps.setString(1, users.username);
				ps.setString(2, users.password);
				if(ps.executeUpdate()>0)
					return true;
				ps.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return false;
	}
	public boolean input(Message user){
		String url="jdbc:mysql://localhost:3306/wechat?useUnicode=true&characterEncoding=utf-8&useSSL=false";
		try {
			Connection conn= DriverManager.getConnection(url,"root","123456");
			PreparedStatement ps=conn.prepareStatement("insert into talks values(?,?,?)");
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getTime());
			ps.setString(3, user.getTalks());
			if(ps.executeUpdate()>0)
				return true;
			ps.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	public boolean isexist(String name){
		String url="jdbc:mysql://localhost:3306/wechat?useUnicode=true&characterEncoding=utf-8&useSSL=false";
		Connection conn;
		try {
			conn = DriverManager.getConnection(url,"root","123456");
			Statement st=conn.createStatement();
			ResultSet rs= st.executeQuery("select count(*) from user where user_name='"+name+"';");
			if(rs.next()){
				if(rs.getInt(1)>0)
					return true;
				else
					return false;
			}
			st.close();
			st.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
	public ArrayList<Users> getuser(){
		String url="jdbc:mysql://localhost:3306/wechat?useUnicode=true&characterEncoding=utf-8&useSSL=false";
		ArrayList<Users> user=new ArrayList<Users>();
		try {
			Connection conn= DriverManager.getConnection(url,"root","123456");
			
			PreparedStatement ps=conn.prepareStatement("select user_name from user");
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				Users u=new Users();
				u.setUsername(rs.getString(1));
				user.add(u);
			}
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}
	public ArrayList<Message> gettalks(){
		String url="jdbc:mysql://localhost:3306/wechat?useUnicode=true&characterEncoding=utf-8&useSSL=false";
		ArrayList<Message> user=new ArrayList<Message>();
		try {
			Connection conn= DriverManager.getConnection(url,"root","123456");
			PreparedStatement ps=conn.prepareStatement("select * from talks");
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				Message u=new Message();
				u.setUsername(rs.getString(1));
				u.setTime(rs.getString(2));
				u.setTalks(rs.getString(3));
				user.add(u);
			}
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return user;
	}
	public boolean logining(Users users){
		String url="jdbc:mysql://localhost:3306/wechat?useUnicode=true&characterEncoding=utf-8&useSSL=false";
		try {
			Connection conn= DriverManager.getConnection(url,"root","123456");
			Statement ps=conn.createStatement();
			ResultSet rs=ps.executeQuery("select count(*) from user where user_name='"+users.username+"' and password='"+users.password+"';");
			if(rs.next())
			{
				if(rs.getInt(1)>0)
					return true;
			}
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	public boolean putpicture(Users users){
		String url="jdbc:mysql://localhost:3306/wechat?useUnicode=true&characterEncoding=utf-8&useSSL=false";
		try {
			Connection conn= DriverManager.getConnection(url,"root","123456");
			PreparedStatement ps=conn.prepareStatement("update user set image=(?) where user_name=(?);");
			ps.setBytes(1, users.images);
			ps.setString(2, users.username);
			if(ps.executeUpdate()>0)
				return true;
			ps.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	public ArrayList<Users> getpicture(ArrayList<String> username){
		String url="jdbc:mysql://localhost:3306/wechat?useUnicode=true&characterEncoding=utf-8&useSSL=false";
		ArrayList<Users> user= new ArrayList<Users>();
		try {
			Connection conn= DriverManager.getConnection(url,"root","123456");
			for(String name:username){
			PreparedStatement ps=conn.prepareStatement("select image from user where user_name=(?)");
			ps.setString(1, name);
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				Users u=new Users();
				u.setUsername(name);
				u.setImages(rs.getBytes(1));
				user.add(u);
			}
			rs.close();
			ps.close();
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		

	}

}
