package toupiao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TouPiaoAction extends HttpServlet {

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
		    int ryid = (Integer)req.getSession().getAttribute("ryid");
		    String tpdxid = req.getParameter("tpdxid");
		    String dpsStr = req.getParameter("dps");
		    int dpsl=1;
		    if(dpsStr!=null&&!("").equals(dpsStr)){
		    	dpsl = Integer.parseInt(dpsStr)+1;
		    }
			String sql ="select * from ryxxb where ryid='"+ryid+"' and sftp='是'";
			try {
				Class.forName(driver);
				cn = DriverManager.getConnection(url, user, password);
				PreparedStatement ps = cn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
					res.getWriter().print("{success:true,msg:'您已投过票，不可重复投票！'}");
					rs.close();
					ps.close();
					try {
						cn.close();
					} catch (Exception e) {

					}
					return;
				}else{
					check = true;
				}
				rs.close();
				ps = cn.prepareStatement("update tpdxb set dpsl='"+dpsl+"' where tpdxid='"+tpdxid+"'");
				ps.executeUpdate();
				ps = cn.prepareStatement("insert into rytpb(ryid,tpdxid)values('"+ryid+"','"+tpdxid+"')");
				ps.executeUpdate();
				ps = cn.prepareStatement("update ryxxb set sftp='是' where ryid='"+ryid+"'");
				ps.executeUpdate();
				rs.close();
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
				res.getWriter().print("{success:true,msg:'投票成功！'}");
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
