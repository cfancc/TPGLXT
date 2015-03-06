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

public class ZhuCeYHandDXAction extends HttpServlet {
	private String ryid;
	private String xm;
	private String xh;
	private String zy;
	private String dlm;
	private String mm;
	private String lxfs;
	private String sftp;
	private String rylb;

	public String getRyid() {
		return ryid;
	}

	public void setRyid(String ryid) {
		this.ryid = ryid;
	}

	public String getXm() {
		return xm;
	}

	public void setXm(String xm) {
		this.xm = xm;
	}

	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	public String getZy() {
		return zy;
	}

	public void setZy(String zy) {
		this.zy = zy;
	}

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

	public String getLxfs() {
		return lxfs;
	}

	public void setLxfs(String lxfs) {
		this.lxfs = lxfs;
	}

	public String getSftp() {
		return sftp;
	}

	public void setSftp(String sftp) {
		this.sftp = sftp;
	}

	public String getRylb() {
		return rylb;
	}

	public void setRylb(String rylb) {
		this.rylb = rylb;
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
		String sql = null;
		ryid = req.getParameter("ryid");
		xm = req.getParameter("xm");
		xh = req.getParameter("xh");
		zy = req.getParameter("zy");
		dlm = req.getParameter("dlm");
		mm = req.getParameter("mm");
		lxfs = req.getParameter("lxfs");
		sftp = req.getParameter("sftp");
		rylb = req.getParameter("rylb");
		if (ryid != null && !("").equals(ryid)) {
			sql = "update ryxxb set xm=?,xh=?,zy=?,dlm=?,mm=?,lxfs=?,rylb=?  where ryid=?";
			try {
				Class.forName(driver);
				cn = DriverManager.getConnection(url, user, password);
				PreparedStatement ps = cn.prepareStatement(sql);
				ps.setString(1, xm);
				ps.setString(2, xh);
				ps.setString(3, zy);
				ps.setString(4, dlm);
				ps.setString(5, mm);
				ps.setString(6, lxfs);
				ps.setString(7, rylb);
				ps.setString(8, ryid);
				int i = ps.executeUpdate();
				sql = "update tpdxb set xm='" + xm + "' where ryid='" + ryid
						+ "'";
				ps = cn.prepareStatement(sql);
				ps.executeUpdate();
				// 未更新tpdxb的xm字段
				if (i != 0) {
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
				res.getWriter().print("{success:true,msg:'更新成功！'}");
			}
		} else {
			try {
				Class.forName(driver);
				cn = DriverManager.getConnection(url, user, password);
				PreparedStatement ps = cn
						.prepareStatement("select * from ryxxb "
								+ "where dlm='" + dlm + "' and mm='" + mm + "'");
				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
					rs.close();
					ps.close();
					res.getWriter().print("{success:true,msg:'用户已存在,请更换登录名！'}");
					return;
				}
				sql = "insert into ryxxb(xm,xh,zy,dlm,mm,lxfs,sftp,rylb)values(?,?,?,?,?,?,?,?)";

				Class.forName(driver);
				cn = DriverManager.getConnection(url, user, password);
				ps = cn.prepareStatement(sql);
				ps.setString(1, xm);
				ps.setString(2, xh);
				ps.setString(3, zy);
				ps.setString(4, dlm);
				ps.setString(5, mm);
				ps.setString(6, lxfs);
				ps.setString(7, sftp);
				ps.setString(8, rylb);
				int i = ps.executeUpdate();
				if (i != 0) {
					check = true;
				}
				ps.close();
				if (rylb.equals("投票对象")) {
					ps = cn.prepareStatement("select max(ryid) from ryxxb");
					rs = ps.executeQuery();
					rs.next();
					int rid = rs.getInt(1);
					rs.close();
					ps = cn
							.prepareStatement("insert into tpdxb(xm,ryid)values(?,?)");
					ps.setString(1, xm);
					ps.setInt(2, rid);
					i = ps.executeUpdate();
					if (i != 0) {
						check = true;
					}
					ps.close();
				}

			} catch (Exception e) {
				System.out.println("error:" + e.getMessage());
			} finally {
				try {
					cn.close();
				} catch (Exception e) {

				}
			}
			if (check) {
				res.getWriter().print("{success:true,msg:'注册成功！'}");
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
