package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import pojos.Incomes;
import utilities.ConnectionPool;
import utilities.DateUtils;

public class IncomesDao {
	
	public void create(Incomes incomes) {
		ConnectionPool pool = ConnectionPool.getInstance();
		pool.initialize();
		Connection conn = pool.getConnection();
		
		try {
			String sql = "insert into incomes(inc_ac, userid, inc_catid, amount, tran_date, receiveby, remark) values (?,?,?,?,?,?,?);";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, incomes.getInc_ac());
			ps.setDouble(2, incomes.getUserid());
			ps.setInt(3,  incomes.getInc_catid());
			ps.setDouble(4, incomes.getAmount());
			java.sql.Date dt = new java.sql.Date(incomes.getTran_date().getTime());
			ps.setDate(5, dt);
			ps.setString(6, incomes.getReceiveby());
			ps.setString(7, incomes.getRemark());
			ps.executeUpdate();
		} catch (SQLException sq) {
			System.out.println("Unable to create a new row." + sq);
		} finally {
			pool.putConnection(conn);
		}
	}
	
	public void edit(Incomes incomes) {
		ConnectionPool pool = ConnectionPool.getInstance();
		pool.initialize();
		Connection conn = pool.getConnection();
		try {
			String sql = "update incomes set inc_ac = ?, userid = ?, inc_catid = ?, amount = ?, tran_date = ?, receiveby = ?, remark = ? where exp_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, incomes.getInc_ac());
			ps.setInt(2, incomes.getUserid());
			ps.setInt(3, incomes.getInc_catid());
			ps.setDouble(4, incomes.getAmount());
			java.sql.Date dt = new java.sql.Date(incomes.getTran_date().getTime());
			ps.setDate(5, dt);
			ps.setString(6, incomes.getReceiveby());
			ps.setString(7, incomes.getRemark());
			ps.executeUpdate();
		} catch (SQLException sq) {
			System.out.println("Unable to edit the row." + sq);
		} finally {
			pool.putConnection(conn);
		}
	}
	
	public void remove(int inc_id) {
		ConnectionPool pool = ConnectionPool.getInstance();
		pool.initialize();
		Connection conn = pool.getConnection();
		try {
			String sql = "delete from incomes where exp_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, inc_id);
			ps.executeUpdate();
		} catch (SQLException sq) {
			System.out.println("Unable to delete the row." + sq);
		} finally {
			pool.putConnection(conn);
		}
	}

	public Incomes find(int inc_id) {
		ConnectionPool pool = ConnectionPool.getInstance();
		pool.initialize();
		Connection conn = pool.getConnection();
		Incomes incomes = new Incomes();
		try {
			String sql = "select * from incomes where inc_id= ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, inc_id);
			
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				incomes.setInc_id(inc_id);
				incomes.setInc_ac(rs.getString("inc_ac"));
				incomes.setUserid(rs.getInt("userid"));
				incomes.setInc_catid(rs.getInt("inc_catid"));
				incomes.setAmount(rs.getDouble("amount"));
	
				java.sql.Date dt = rs.getDate("tran_date");
				incomes.setTran_date(new java.util.Date(dt.getTime()));
				incomes.setReceiveby(rs.getString("receiveby"));
				incomes.setRemark(rs.getString("remark"));
			}
		} catch (SQLException sq) {
			System.out.println("Unable to find a row." + sq);
		} finally {
			pool.putConnection(conn);
		}
		return incomes;
	}
	
	public ArrayList<Incomes> findAll() {
		ConnectionPool pool = ConnectionPool.getInstance();
		pool.initialize();
		Connection conn = pool.getConnection();
		ArrayList<Incomes> list = new ArrayList<Incomes>();
		try {
			String sql = "select * from incomes";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Incomes incomes = new Incomes();
				incomes.setInc_id(rs.getInt("inc_id"));
				incomes.setInc_ac(rs.getString("inc_ac"));
				incomes.setUserid(rs.getInt("userid"));
				incomes.setInc_catid(rs.getInt("inc_catid"));
				incomes.setAmount(rs.getDouble("amount"));
				java.sql.Date dt = rs.getDate("tran_date");
				incomes.setTran_date(new java.util.Date(dt.getTime()));
				incomes.setReceiveby(rs.getString("receiveby"));
				incomes.setRemark(rs.getString("remark"));
				list.add(incomes);
			}
		} catch (SQLException sq) {
			System.out.println("unable to find the records." + sq);
		} finally {
			pool.putConnection(conn);
		}
		return list;
	}

	public ArrayList<Incomes> findAll(int userid) {
		ConnectionPool pool = ConnectionPool.getInstance();
		pool.initialize();
		Connection conn = pool.getConnection();
		ArrayList<Incomes> list = new ArrayList<Incomes>();
		try {
			String sql = "select * from incomes where userid = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, userid);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Incomes incomes = new Incomes();
				incomes.setInc_id(rs.getInt("inc_id"));
				incomes.setInc_ac(rs.getString("inc_ac"));
				incomes.setUserid(rs.getInt("userid"));
				incomes.setInc_catid(rs.getInt("inc_catid"));
				incomes.setAmount(rs.getDouble("amount"));
				java.sql.Date dt = rs.getDate("tran_date");
				incomes.setTran_date(new java.util.Date(dt.getTime()));
				incomes.setReceiveby(rs.getString("receiveby"));
				incomes.setRemark(rs.getString("remark"));
				list.add(incomes);
			}
		} catch (SQLException sq) {
			System.out.println("unable to find the records." + sq);
		} finally {
			pool.putConnection(conn);
		}
		return list;
	}
	
	public ArrayList<Incomes> findAllDateWise(String sdate, String edate, int userid) {
		ConnectionPool pool = ConnectionPool.getInstance();
		pool.initialize();
		Connection conn = pool.getConnection();
		ArrayList<Incomes> list = new ArrayList<Incomes>();
		try {
			String sql = "select * from incomes where tran_date >= ? && tran_date <= ?  && userid = ?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setDate(1, new java.sql.Date(DateUtils.convertDate(sdate).getTime()));
			ps.setDate(2, new java.sql.Date(DateUtils.convertDate(edate).getTime()));
			ps.setInt(3, userid);
			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Incomes incomes = new Incomes();
				incomes.setInc_id(rs.getInt("inc_id"));
				incomes.setInc_ac(rs.getString("inc_ac"));
				incomes.setUserid(rs.getInt("userid"));
				incomes.setInc_catid(rs.getInt("inc_catid"));
				incomes.setAmount(rs.getDouble("amount"));
				java.sql.Date dt = rs.getDate("tran_date");
				incomes.setTran_date(new java.util.Date(dt.getTime()));
				incomes.setReceiveby(rs.getString("receiveby"));
				incomes.setRemark(rs.getString("remark"));
				list.add(incomes);
			}
		} catch (SQLException sq) {
			System.out.println("unable to find the records." + sq);
		} finally {
			pool.putConnection(conn);
		}
		return list;
	}


	
	
}
