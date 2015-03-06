package toupiao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class TouPiaoTongJiAction extends HttpServlet {

	private static final long serialVersionUID = 2L;
	String driver = "com.mysql.jdbc.Driver";
	String url = "jdbc:mysql://localhost/TPGLXT";
	String user = "root";
	String password = "123456";
	Connection cn = null;

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
	    req.setCharacterEncoding("utf-8");
	    res.setContentType("application/json;charset=utf-8");
	    boolean check = false;
		String sql="select xm,dpsl,tpdxid from tpdxb ";
			try {
				Class.forName(driver);
				cn = DriverManager.getConnection(url, user, password);
				PreparedStatement ps = cn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				JSONArray ja = new JSONArray();
				while(rs.next()){
					JSONObject json = new JSONObject();
					json.put("xm", rs.getString(1));
					json.put("dps", rs.getInt(2));
					json.put("tpdxid", rs.getInt(3));
					ja.add(json);
				}
				
				rs.close();
				ps.close();
				JSONObject json = new JSONObject();
				json.put("toupiaojglist", ja);
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
