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

public class DengLuAction extends HttpServlet {
	private String dlm;
	private String mm;
	
	public String getDlm() {
		return dlm;
	}

	public void setDlm(String dlm) {
		this.dlm = dlm;
	}

	public String getMm() {
		return mm;
	}

	public void setMm(String mm) {
		this.mm = mm;
	}

	private static final long serialVersionUID = 1L;
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
			HttpSession session = req.getSession();
			String rylb =req.getParameter("rylb");
			dlm=req.getParameter("dlm");
			mm=req.getParameter("mm");
			String sql = "select * from ryxxb where dlm='"+dlm+"' and mm='"+mm+"' and rylb='"+rylb+"'";
			try {
					Class.forName(driver);
					cn = DriverManager.getConnection(url, user, password);
					PreparedStatement ps = cn.prepareStatement(sql);
					ResultSet rs = ps.executeQuery();
					if (rs.next()) {
						session.setAttribute("ryid", rs.getInt(1));
						
						check = true;
					}
					rs.close();
					ps.close();
				} catch (Exception e) {
					System.out.println("error" + e.getMessage());
				} finally {
					try {
						cn.close();
					} catch (Exception e) {

					}
				}
				
				
				if (check) {
					if(rylb.equals("管理员")){
						session.setAttribute("dlm", dlm);
						res.getWriter().print("{success:true,msg:'管理员登陆成功！'}");
					}else{
						session.setAttribute("dlm", dlm);
						res.getWriter().print("{success:true,msg:'普通用户登陆成功！'}");
					}
					
				}else{
					res.getWriter().print("{success:true,msg:'用户名或密码错误！'}");
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
