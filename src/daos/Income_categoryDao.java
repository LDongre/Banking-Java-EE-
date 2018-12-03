package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import pojos.Income_category;
import utilities.ConnectionPool;

public class Income_categoryDao {
	
	public void create(Income_category income_category) {
		ConnectionPool pool = ConnectionPool.getInstance();
		pool.initialize();
		Connection conn = pool.getConnection();
		
		try {
			String sql = "insert into income_category(inc_catname, inc_catdetails, userid) values(?,?,?);";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, income_category.getInc_catname());
			ps.setString(2, income_category.getInc_catdetails());
			ps.setInt(3,  income_category.getUserid());
			ps.executeUpdate();
		} catch (SQLException sq) {
			System.out.println("Unable to create a new row." + sq);
		} finally {
			pool.putConnection(conn);
		}
	}
	
	public void edit(Income_category income_category) {
		ConnectionPool pool = ConnectionPool.getInstance();
		pool.initialize();
		Connection conn = pool.getConnection();
		try {
			String sql = "update income_category set inc_catname = ?, inc_catdetails= ?, userid = ? where inc_catid = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, income_category.getInc_catname());
			ps.setString(2, income_category.getInc_catdetails());
			ps.setInt(3, income_category.getUserid());
			ps.setInt(4, income_category.getInc_catid());
			ps.executeUpdate();
		} catch (SQLException sq) {
			System.out.println("Unable to edit the row." + sq);
		} finally {
			pool.putConnection(conn);
		}
	}
	
	public void remove(int inc_catid) {
		ConnectionPool pool = ConnectionPool.getInstance();
		pool.initialize();
		Connection conn = pool.getConnection();
		try {
			String sql = "delete from income_category where inc_catid = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, inc_catid);
			ps.executeUpdate();
		} catch (SQLException sq) {
			System.out.println("Unable to delete the row." + sq);
		} finally {
			pool.putConnection(conn);
		}
	}

	public Income_category find(int inc_catid) {
		ConnectionPool pool = ConnectionPool.getInstance();
		pool.initialize();
		Connection conn = pool.getConnection();
		Income_category income_category = new Income_category();
		try {
			String sql = "select * from income_category where inc_catid = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, inc_catid);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				income_category.setInc_catid(inc_catid);
				income_category.setInc_catname(rs.getString("inc_catname"));
				income_category.setInc_catdetails(rs.getString("inc_catdetails"));
				income_category.setUserid(rs.getInt("userid"));
			}
		} catch (SQLException sq) {
			System.out.println("Unable to find a row." + sq);
		} finally {
			pool.putConnection(conn);
		}
		return income_category;
	}
	
	public ArrayList<Income_category> findAll() {
		ConnectionPool pool = ConnectionPool.getInstance();
		pool.initialize();
		Connection conn = pool.getConnection();
		ArrayList<Income_category> list= new ArrayList<Income_category>();
		try {
			String sql = "select * from income_category";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Income_category income_category = new Income_category();
				income_category.setInc_catid(rs.getInt("inc_catid"));
				income_category.setInc_catname(rs.getString("inc_catname"));
				income_category.setInc_catdetails(rs.getString("inc_catdetails"));
				income_category.setUserid(rs.getInt("userid"));
				list.add(income_category);
			}
		} catch (SQLException sq) {
			System.out.println("unable to find the record." + sq);
		} finally {
			pool.putConnection(conn);
		}
		return list;
	}

	public ArrayList<Income_category> findAll(int userid) {
		ConnectionPool pool = ConnectionPool.getInstance();
		pool.initialize();
		Connection conn = pool.getConnection();
		ArrayList<Income_category> list= new ArrayList<Income_category>();
		try {
			String sql = "select * from income_category where userid=" + userid;
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Income_category income_category = new Income_category();
				income_category.setInc_catid(rs.getInt("inc_catid"));
				income_category.setInc_catname(rs.getString("inc_catname"));
				income_category.setInc_catdetails(rs.getString("inc_catdetails"));
				income_category.setUserid(rs.getInt("userid"));
				list.add(income_category);
			}
		} catch (SQLException sq) {
			System.out.println("unable to find the record." + sq);
		} finally {
			pool.putConnection(conn);
		}
		return list;
	}

public int checkDuplicate(Income_category income_category) {
		
		ArrayList<Income_category> list = this.findAll(income_category.getUserid());
		for(Income_category income_cat: list) {
			if(income_cat.getInc_catname().equals(income_category.getInc_catname()) && income_cat.getInc_catdetails().equals(income_category.getInc_catdetails())) {
				return 0;
			}
		}
		return 1;
	}
	
	
}
