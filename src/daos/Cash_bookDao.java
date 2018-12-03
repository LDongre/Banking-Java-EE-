package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import pojos.Cash_book;
import utilities.ConnectionPool;
import utilities.DateUtils;

public class Cash_bookDao {
	
	public void create(Cash_book cash_book) {
		ConnectionPool pool = ConnectionPool.getInstance();
		pool.initialize();
		Connection conn = pool.getConnection();
		
		try {
			String sql = "insert into cash_book(account, tran_date, amount, userid, operation) values (?,?,?,?,?);";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, cash_book.getAccount());
			java.sql.Date dt = new java.sql.Date(cash_book.getTran_date().getTime());
			ps.setDate(2, dt);
			ps.setDouble(3, cash_book.getAmount());
			ps.setInt(4,  cash_book.getUserid());
			ps.setString(5, cash_book.getOperation());
			ps.executeUpdate();
		} catch (SQLException sq) {
			System.out.println("Unable to create a new row." + sq);
		} finally {
			pool.putConnection(conn);
		}
	}
	
	public void edit(Cash_book cash_book) {
		ConnectionPool pool = ConnectionPool.getInstance();
		pool.initialize();
		Connection conn = pool.getConnection();
		try {
			String sql = "update cash_book set account = ?, tran_date = ?,amount=?, userid = ?, operation =?  where acid = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, cash_book.getAccount());
			java.sql.Date dt = new java.sql.Date(cash_book.getTran_date().getTime());
			ps.setDate(2, dt);
			ps.setDouble(3, cash_book.getAmount());
			ps.setInt(4, cash_book.getUserid());
			ps.setString(5, cash_book.getOperation());
			ps.setInt(6, cash_book.getAcid());
			ps.executeUpdate();
		} catch (SQLException sq) {
			System.out.println("Unable to edit the row." + sq);
		} finally {
			pool.putConnection(conn);
		}
	}
	
	public void remove(int acid) {
		ConnectionPool pool = ConnectionPool.getInstance();
		pool.initialize();
		Connection conn = pool.getConnection();
		try {
			String sql = "delete from cash_book where acid = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, acid);
			ps.executeUpdate();
		} catch (SQLException sq) {
			System.out.println("Unable to delete the row." + sq);
		} finally {
			pool.putConnection(conn);
		}
	}

	public Cash_book find(int acid) {
		ConnectionPool pool = ConnectionPool.getInstance();
		pool.initialize();
		Connection conn = pool.getConnection();
		Cash_book cash_book = new Cash_book();
		try {
			String sql = "select * from cash_book where acid = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, acid);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				cash_book.setAcid(acid);
				cash_book.setAccount(rs.getString("account"));
				
				java.sql.Date dt = rs.getDate("tran_date");
				cash_book.setTran_date(new java.util.Date(dt.getTime()));
				cash_book.setUserid(rs.getInt("userid"));
				cash_book.setAmount(rs.getDouble("amount"));
				cash_book.setOperation(rs.getString("operation"));
			}
		} catch (SQLException sq) {
			System.out.println("Unable to find a row." + sq);
		} finally {
			pool.putConnection(conn);
		}
		return cash_book;
	}
	
	public ArrayList<Cash_book> findAll() {
		ConnectionPool pool = ConnectionPool.getInstance();
		pool.initialize();
		Connection conn = pool.getConnection();
		ArrayList<Cash_book> list= new ArrayList<Cash_book>();
		try {
			String sql = "select * from cash_book";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Cash_book cash_book = new Cash_book();
				cash_book.setAcid(rs.getInt("acid"));
				cash_book.setAccount(rs.getString("account"));
				java.sql.Date dt = rs.getDate("tran_date");
				cash_book.setTran_date(new java.util.Date(dt.getTime()));
				cash_book.setAmount(rs.getDouble("amount"));
				cash_book.setUserid(rs.getInt("userid"));
				cash_book.setOperation(rs.getString("operation"));
				list.add(cash_book);
			}
		} catch (SQLException sq) {
			System.out.println("unable to find the record." + sq);
		} finally {
			pool.putConnection(conn);
		}
		return list;
	}

	public ArrayList<Cash_book> findAllDateWise(String sdate, String edate, int userid) {
		ConnectionPool pool = ConnectionPool.getInstance();
		pool.initialize();
		Connection conn = pool.getConnection();
		ArrayList<Cash_book> list = new ArrayList<Cash_book>();
		try {
			String sql = "select * from cash_book where userid = ? && tran_date >= ? && tran_date <= ? ;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, userid);
			
			java.sql.Date fd = new java.sql.Date(DateUtils.convertDate(sdate).getTime());
			ps.setDate(2, fd);
			java.sql.Date td = new java.sql.Date(DateUtils.convertDate(edate).getTime());
			ps.setDate(3, td);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Cash_book cash_book = new Cash_book();
				cash_book.setAcid(rs.getInt("acid"));
				cash_book.setAccount(rs.getString("account"));
				java.sql.Date dt = rs.getDate("tran_date");
				cash_book.setTran_date(new java.util.Date(dt.getTime()));
				cash_book.setAmount(rs.getDouble("amount"));
				cash_book.setUserid(rs.getInt("userid"));
				cash_book.setOperation(rs.getString("operation"));
				list.add(cash_book);
			}
		} catch (SQLException sq) {
			System.out.println("unable to find the record." + sq);
		} finally {
			pool.putConnection(conn);
		}
		return list;

	}

	public double closingBalance(int userid) {
		ConnectionPool pool = ConnectionPool.getInstance();
		pool.initialize();
		Connection conn = pool.getConnection();
		double closingBalance = 0;
		try {
			String sql = "select (SELECT sum(amount) FROM cash_book where userid =+" + userid
					+ " and operation ='receive') - (SELECT sum(amount) FROM cash_book where userid ="
					+ userid + " and operation='pay') as 'ClosingBalance';";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			closingBalance = rs.getDouble("ClosingBalance") ;
		} catch (SQLException sq) {
			System.out.println("unable to find the record." + sq);
		} finally {
			pool.putConnection(conn);
		}
		return closingBalance;
	}



	
	
}
