package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import pojos.Expenses;
import utilities.ConnectionPool;

public class ExpensesDao {
	
	public void create(Expenses expenses) {
		ConnectionPool pool = ConnectionPool.getInstance();
		pool.initialize();
		Connection conn = pool.getConnection();
		
		try {
			String sql = "insert into expenses(exp_ac, userid, exp_catid, amount, tran_date, payby, remark) values (?,?,?,?,?,?,?);";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, expenses.getExp_ac());
			ps.setDouble(2, expenses.getUserid());
			ps.setInt(3,  expenses.getExp_catid());
			ps.setDouble(4, expenses.getAmount());
			java.sql.Date dt = new java.sql.Date(expenses.getTran_date().getTime());
			ps.setDate(5, dt);
			ps.setString(6, expenses.getPayby());
			ps.setString(7, expenses.getRemark());
			ps.executeUpdate();
		} catch (SQLException sq) {
			System.out.println("Unable to create a new row." + sq);
		} finally {
			pool.putConnection(conn);
		}
	}
	
	public void edit(Expenses expenses) {
		ConnectionPool pool = ConnectionPool.getInstance();
		pool.initialize();
		Connection conn = pool.getConnection();
		try {
			String sql = "update expenses set exp_ac = ?, userid = ?, exp_catid = ?, amount = ?, tran_date = ?, payby = ?, remark = ? where exp_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, expenses.getExp_ac());
			ps.setInt(2, expenses.getUserid());
			ps.setInt(3, expenses.getExp_catid());
			ps.setDouble(4, expenses.getAmount());
			java.sql.Date dt = new java.sql.Date(expenses.getTran_date().getTime());
			ps.setDate(5, dt);
			ps.setString(6, expenses.getPayby());
			ps.setString(7, expenses.getRemark());
			ps.executeUpdate();
		} catch (SQLException sq) {
			System.out.println("Unable to edit the row." + sq);
		} finally {
			pool.putConnection(conn);
		}
	}
	
	public void remove(int exp_id) {
		ConnectionPool pool = ConnectionPool.getInstance();
		pool.initialize();
		Connection conn = pool.getConnection();
		try {
			String sql = "delete from expenses where exp_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, exp_id);
			ps.executeUpdate();
		} catch (SQLException sq) {
			System.out.println("Unable to delete the row." + sq);
		} finally {
			pool.putConnection(conn);
		}
	}

	public Expenses find(int exp_id) {
		ConnectionPool pool = ConnectionPool.getInstance();
		pool.initialize();
		Connection conn = pool.getConnection();
		Expenses expenses = new Expenses();
		try {
			String sql = "select * from expenses where exp_id= ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, exp_id);
			
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				expenses.setExp_id(exp_id);
				expenses.setExp_ac(rs.getString("exp_ac"));
				expenses.setUserid(rs.getInt("userid"));
				expenses.setExp_catid(rs.getInt("exp_catid"));
				expenses.setAmount(rs.getDouble("amount"));
	
				java.sql.Date dt = rs.getDate("tran_date");
				expenses.setTran_date(new java.util.Date(dt.getTime()));
				
				expenses.setPayby(rs.getString("payby"));
				expenses.setRemark(rs.getString("remark"));
			}
		} catch (SQLException sq) {
			System.out.println("Unable to find a row." + sq);
		} finally {
			pool.putConnection(conn);
		}
		return expenses;
	}
	
	public ArrayList<Expenses> findAll() {
		ConnectionPool pool = ConnectionPool.getInstance();
		pool.initialize();
		Connection conn = pool.getConnection();
		ArrayList<Expenses> list = new ArrayList<Expenses>();
		try {
			String sql = "select * from expenses";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Expenses expenses = new Expenses();
				expenses.setExp_id(rs.getInt("exp_id"));
				expenses.setExp_ac(rs.getString("exp_ac"));
				expenses.setUserid(rs.getInt("userid"));
				expenses.setExp_catid(rs.getInt("exp_catid"));
				expenses.setAmount(rs.getDouble("amount"));
				java.sql.Date dt = rs.getDate("tran_date");
				expenses.setTran_date(new java.util.Date(dt.getTime()));
				expenses.setPayby(rs.getString("payby"));
				expenses.setRemark(rs.getString("remark"));
				list.add(expenses);
			}
		} catch (SQLException sq) {
			System.out.println("unable to find the records." + sq);
		} finally {
			pool.putConnection(conn);
		}
		return list;
	}

	public ArrayList<Expenses> findAll(int userid) {
		ConnectionPool pool = ConnectionPool.getInstance();
		pool.initialize();
		Connection conn = pool.getConnection();
		ArrayList<Expenses> list = new ArrayList<Expenses>();
		try {
			String sql = "select * from expenses where userid=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, userid);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Expenses expenses = new Expenses();
				expenses.setExp_id(rs.getInt("exp_id"));
				expenses.setExp_ac(rs.getString("exp_ac"));
				expenses.setUserid(rs.getInt("userid"));
				expenses.setExp_catid(rs.getInt("exp_catid"));
				expenses.setAmount(rs.getDouble("amount"));
				java.sql.Date dt = rs.getDate("tran_date");
				expenses.setTran_date(new java.util.Date(dt.getTime()));
				expenses.setPayby(rs.getString("payby"));
				expenses.setRemark(rs.getString("remark"));
				list.add(expenses);
			}
		} catch (SQLException sq) {
			System.out.println("unable to find the records." + sq);
		} finally {
			pool.putConnection(conn);
		}
		return list;
	}
	
	public ArrayList<Expenses> findAllDateWise(String sdate, String edate, int userid) {		
		ConnectionPool pool = ConnectionPool.getInstance();
		pool.initialize();
		Connection conn = pool.getConnection();
		ArrayList<Expenses> list = new ArrayList<Expenses>();
		try {
			String sql = "select * from expenses where tran_date>= ? && tran_date <= ? && userid = ?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setDate(1, java.sql.Date.valueOf(sdate));
			ps.setDate(2, java.sql.Date.valueOf(edate));
			ps.setInt(3, userid);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Expenses expenses = new Expenses();
				expenses.setExp_id(rs.getInt("exp_id"));
				expenses.setExp_ac(rs.getString("exp_ac"));
				expenses.setUserid(rs.getInt("userid"));
				expenses.setExp_catid(rs.getInt("exp_catid"));
				expenses.setAmount(rs.getDouble("amount"));
				java.sql.Date dt = rs.getDate("tran_date");
				expenses.setTran_date(new java.util.Date(dt.getTime()));		
				expenses.setPayby(rs.getString("payby"));
				expenses.setRemark(rs.getString("remark"));
				list.add(expenses);
			}
		} catch (SQLException sq) {
			System.out.println("unable to find the records." + sq);
		} finally {
			pool.putConnection(conn);
		}
		return list;
	}
	

	public static void main(String arg[]) {
		new ExpensesDao().findAllDateWise("2018-01-01", "2018-04-01", 1);
	}
	
	
}
