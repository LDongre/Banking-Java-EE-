package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import pojos.Bank_book;
import utilities.ConnectionPool;

public class Bank_bookDao {

	public void create(Bank_book bank_book) {
		ConnectionPool pool = ConnectionPool.getInstance();
		pool.initialize();
		Connection conn = pool.getConnection();

		try {
			String sql = "insert into bank_book(account,tran_date,amount,userid,operation) values (?,?,?,?,?);";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, bank_book.getAccount());
			java.sql.Date dt = new java.sql.Date(bank_book.getTran_date().getTime());
			ps.setDate(2, dt);
			ps.setDouble(3, bank_book.getAmount());
			ps.setInt(4, bank_book.getUserid());
			ps.setString(5, bank_book.getOperation());
			ps.executeUpdate();
		} catch (SQLException sq) {
			System.out.println("Unable to create a new row." + sq);
		} finally {
			pool.putConnection(conn);
		}
	}

	public void edit(Bank_book bank_book) {
		ConnectionPool pool = ConnectionPool.getInstance();
		pool.initialize();
		Connection conn = pool.getConnection();
		try {
			String sql = "update bank_book set account = ?, tran_date = ?,amount=?, userid = ?, operation = ?  where acid = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, bank_book.getAccount());
			java.sql.Date dt = new java.sql.Date(bank_book.getTran_date().getTime());
			ps.setDate(2, dt);
			ps.setDouble(3, bank_book.getAmount());
			ps.setInt(4, bank_book.getUserid());
			ps.setString(5, bank_book.getOperation());
			ps.setInt(6, bank_book.getAcid());
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
			String sql = "delete from bank_book where acid = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, acid);
			ps.executeUpdate();
		} catch (SQLException sq) {
			System.out.println("Unable to delete the row." + sq);
		} finally {
			pool.putConnection(conn);
		}
	}

	public Bank_book find(int acid) {
		ConnectionPool pool = ConnectionPool.getInstance();
		pool.initialize();
		Connection conn = pool.getConnection();
		Bank_book bank_book = new Bank_book();
		try {
			String sql = "select * from bank_book where acid = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, acid);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				bank_book.setAcid(acid);
				bank_book.setAccount(rs.getString("account"));

				java.sql.Date dt = rs.getDate("tran_date");
				bank_book.setTran_date(new java.util.Date(dt.getTime()));

				bank_book.setAmount(rs.getDouble("amount"));
				bank_book.setOperation(rs.getString("operation"));
			}
		} catch (SQLException sq) {
			System.out.println("Unable to find a row." + sq);
		} finally {
			pool.putConnection(conn);
		}
		return bank_book;
	}

	public ArrayList<Bank_book> findAll() {
		ConnectionPool pool = ConnectionPool.getInstance();
		pool.initialize();
		Connection conn = pool.getConnection();
		ArrayList<Bank_book> list = new ArrayList<Bank_book>();
		try {
			String sql = "select * from bank_book";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Bank_book bank_book = new Bank_book();
				bank_book.setAcid(rs.getInt("acid"));
				bank_book.setAccount(rs.getString("account"));
				java.sql.Date dt = rs.getDate("tran_date");
				bank_book.setTran_date(new java.util.Date(dt.getTime()));
				bank_book.setAmount(rs.getDouble("amount"));
				bank_book.setUserid(rs.getInt("userid"));
				bank_book.setOperation(rs.getString("operation"));
				list.add(bank_book);
			}
		} catch (SQLException sq) {
			System.out.println("unable to find the record." + sq);
		} finally {
			pool.putConnection(conn);
		}
		return list;
	}

	public ArrayList<Bank_book> findAllDateWise(String sdate, String edate, int userid) {
		ConnectionPool pool = ConnectionPool.getInstance();
		pool.initialize();
		Connection conn = pool.getConnection();
		ArrayList<Bank_book> list = new ArrayList<Bank_book>();
		try {
			String sql = "select * from bank_book where userid = ? && tran_date >= ? && tran_date<= ?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			
			System.out.println(sdate);
			ps.setInt(1, userid);
			ps.setDate(2, java.sql.Date.valueOf(sdate));
			ps.setDate(3, java.sql.Date.valueOf(edate));
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				Bank_book bank_book = new Bank_book();
				bank_book.setAcid(rs.getInt("acid"));
				bank_book.setAccount(rs.getString("account"));
				java.sql.Date dt = rs.getDate("tran_date");
				bank_book.setTran_date(new java.util.Date(dt.getTime()));
				bank_book.setAmount(rs.getDouble("amount"));
				bank_book.setUserid(rs.getInt("userid"));
				bank_book.setOperation(rs.getString("operation"));
				list.add(bank_book);
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
			String sql = "select (SELECT sum(amount) FROM bank_book where userid = ? && operation ='receive') - (SELECT sum(amount) FROM bank_book where userid = ? && operation='pay') as 'ClosingBalance';";
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
