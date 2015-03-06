package yonghu;

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

public class ShanChuYHandDHAction extends HttpServlet {
	private String ryid;
	

	public String getRyid() {
		return ryid;
	}

	public void setRyid(String ryid) {
		this.ryid = ryid;
	}


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
		    ryid = req.getParameter("ryid");
			String sql="delete from ryxxb where ryid=?";
				try {
					Class.forName(driver);
					cn = DriverManager.getConnection(url, user, password);
					PreparedStatement ps = cn.prepareStatement(sql);
					ps.setString(1, ryid);
					int i = ps.executeUpdate();
					
					sql="delete from tpdxb where ryid=?";
					ps = cn.prepareStatement(sql);
					ps.setString(1, ryid);
					ps.executeUpdate();
					if (i!=0) {
						check = true;
					}
					ps.close();
					
				} catch (Exception e) {
					System.out.println("error:" + e.getMessage());
				} finally {
					try {
						cn.close();
					} catch (Exception e) {

					}
				}
				if (check) {
					res.getWriter().print("{success:true,msg:'删除成功！'}");
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
