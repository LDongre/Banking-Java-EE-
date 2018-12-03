package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import pojos.Expenses_category;
import utilities.ConnectionPool;

public class Expenses_categoryDao {
	
	public void create(Expenses_category expenses_category) {
		ConnectionPool pool = ConnectionPool.getInstance();
		pool.initialize();
		Connection conn = pool.getConnection();
		
		try {
			String sql = "insert into expenses_category(exp_catname, exp_catdetails, userid) values (?,?,?);";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, expenses_category.getExp_catname());
			ps.setString(2, expenses_category.getExp_catdetails());
			ps.setInt(3,  expenses_category.getUserid());
			ps.executeUpdate();
		} catch (SQLException sq) {
			System.out.println("Unable to create a new row." + sq);
		} finally {
			pool.putConnection(conn);
		}
	}
	
	public void edit(Expenses_category expenses_category) {
		ConnectionPool pool = ConnectionPool.getInstance();
		pool.initialize();
		Connection conn = pool.getConnection();
		try {
			String sql = "update expenses_category set exp_catname = ?, exp_catdetails= ?, userid = ? where exp_catid = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, expenses_category.getExp_catname());
			ps.setString(2, expenses_category.getExp_catdetails());
			ps.setInt(3, expenses_category.getUserid());
			ps.setInt(4, expenses_category.getExp_catid());
			ps.executeUpdate();
		} catch (SQLException sq) {
			System.out.println("Unable to edit the row." + sq);
		} finally {
			pool.putConnection(conn);
		}
	}
	
	public void remove(int exp_catid) {
		ConnectionPool pool = ConnectionPool.getInstance();
		pool.initialize();
		Connection conn = pool.getConnection();
		try {
			String sql = "delete from expenses_category where exp_catid = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, exp_catid);
			ps.executeUpdate();
		} catch (SQLException sq) {
			System.out.println("Unable to delete the row." + sq);
		} finally {
			pool.putConnection(conn);
		}
	}

	public Expenses_category find(int exp_catid) {
		ConnectionPool pool = ConnectionPool.getInstance();
		pool.initialize();
		Connection conn = pool.getConnection();
		Expenses_category expenses_category = new Expenses_category();
		try {
			String sql = "select * from expenses_category where exp_catid = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, exp_catid);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				expenses_category.setExp_catid(exp_catid);
				expenses_category.setExp_catname(rs.getString("exp_catname"));
				expenses_category.setExp_catdetails(rs.getString("exp_catdetails"));
				expenses_category.setUserid(rs.getInt("userid"));
			}
		} catch (SQLException sq) {
			System.out.println("Unable to find a row." + sq);
		} finally {
			pool.putConnection(conn);
		}
		return expenses_category;
	}
	
	public ArrayList<Expenses_category> findAll() {
		ConnectionPool pool = ConnectionPool.getInstance();
		pool.initialize();
		Connection conn = pool.getConnection();
		ArrayList<Expenses_category> list= new ArrayList<Expenses_category>();
		try {
			String sql = "select * from expenses_category";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Expenses_category expenses_category = new Expenses_category();
				expenses_category.setExp_catid(rs.getInt("exp_catid"));
				expenses_category.setExp_catname(rs.getString("exp_catname"));
				expenses_category.setExp_catdetails(rs.getString("exp_catdetails"));
				expenses_category.setUserid(rs.getInt("userid"));
				list.add(expenses_category);
			}
		} catch (SQLException sq) {
			System.out.println("unable to find the record." + sq);
		} finally {
			pool.putConnection(conn);
		}
		return list;
	}
	
	public int checkDuplicate(Expenses_category expenses_category) {
		
		ArrayList<Expenses_category> list = this.findAll(expenses_category.getUserid());
		for(Expenses_category expenses_cat: list) {
			if(expenses_cat.getExp_catname().equals(expenses_category.getExp_catname()) && expenses_cat.getExp_catdetails().equals(expenses_category.getExp_catdetails())) {
				return 0;
			}
		}
		return 1;
	}
	public ArrayList<Expenses_category> findAll(int userid) {
		ConnectionPool pool = ConnectionPool.getInstance();
		pool.initialize();
		Connection conn = pool.getConnection();
		ArrayList<Expenses_category> list= new ArrayList<Expenses_category>();
		try {
			String sql = "select * from expenses_category where userid=" + userid;
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Expenses_category expenses_category = new Expenses_category();
				expenses_category.setExp_catid(rs.getInt("exp_catid"));
				expenses_category.setExp_catname(rs.getString("exp_catname"));
				expenses_category.setExp_catdetails(rs.getString("exp_catdetails"));
				expenses_category.setUserid(rs.getInt("userid"));
				list.add(expenses_category);
			}
		} catch (SQLException sq) {
			System.out.println("unable to find the record." + sq);
		} finally {
			pool.putConnection(conn);
		}
		return list;
	}


public static void main(String args[]) {
	/*
	ArrayList<Expenses_category> list = new Expenses_categoryDao().findAll();
	for(Expenses_category expcat: list) {
		System.out.print("\n"+ expcat.getExp_catid() + "\t");
		System.out.print(expcat.getExp_catname()+ "\t");
		System.out.print(expcat.getExp_catdetails()+"\t");
		System.out.print(expcat.getUserid()+"\t");
		System.out.print("\n********************************************");
	}*/
}
	
}
