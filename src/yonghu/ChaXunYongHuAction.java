package yonghu;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ChaXunYongHuAction extends HttpServlet {
	
	private static final long serialVersionUID = 3L;
	String driver = "com.mysql.jdbc.Driver";
	String url = "jdbc:mysql://localhost/TPGLXT";
	String user = "root";
	String password = "123456";
	Connection cn = null;
	private String query;

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		    req.setCharacterEncoding("utf-8");
		    res.setContentType("application/json;charset=utf-8");
		    boolean check = false;
		    query=req.getParameter("query");
			String sql="select * from ryxxb where rylb='普通用户' ";
			if(query!=null&&!("").equals(query)){
				sql = sql+" and ( xm like '%"+query+"%' or " +
				                "zy like '%"+query+"%' or " +
				                "xh like '%"+query+"%' or " +
				                "lxfs like '%"+query+"%' or " +
				                "sftp like '%"+query+"%') ";
				}
				try {
					Class.forName(driver);
					cn = DriverManager.getConnection(url, user, password);
					PreparedStatement ps = cn.prepareStatement(sql);
					ResultSet rs = ps.executeQuery();
					JSONArray ja = new JSONArray();
					while(rs.next()){
						JSONObject json = new JSONObject();
						json.put("ryid", rs.getInt(1));
						json.put("xm", rs.getString(2));
						json.put("xh", rs.getString(3));
						json.put("dlm", rs.getString(4));
						json.put("mm", rs.getString(5));
						json.put("zy", rs.getString(6));
						json.put("lxfs", rs.getString(7));
						json.put("sftp", rs.getString(8));
						json.put("rylb", rs.getString(9));
						ja.add(json);
					}
					
					rs.close();
					ps.close();
					JSONObject json = new JSONObject();
					json.put("yhlist", ja);
					json.put("totalSize", ja.size());
					res.getWriter().print(json);
					
				} catch (Exception e) {
					System.out.println("error:" + e.getMessage());
				} finally {
					try {
						cn.close();
					} catch (Exception e) {

					}
				}
				
				}
			
			
	


	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doGet(req, res);
	}

	public void destroy() {
		super.destroy();
	}
}
