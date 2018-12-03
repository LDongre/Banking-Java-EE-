package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import pojos.Users;
import utilities.ConnectionPool;

public class UsersDao {

	public void create(Users users) {
		ConnectionPool pool = ConnectionPool.getInstance();
		pool.initialize();
		Connection conn = pool.getConnection();

		try {
			String sql = "insert into users(username, password, name, address, mobile, email) values (?,?,?,?,?,?);";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, users.getUsername());
			ps.setString(2, users.getPassword());
			ps.setString(3, users.getName());
			ps.setString(4, users.getAddress());
			ps.setString(5, users.getMobile());
			ps.setString(6, users.getEmail());
			
			System.out.println("user entered into table");
			ps.executeUpdate();
		} catch (SQLException sq) {
			System.out.println("Unable to create a new row." + sq);
		} finally {
			pool.putConnection(conn);
		}
	}

	public void edit(Users users) {
		ConnectionPool pool = ConnectionPool.getInstance();
		pool.initialize();
		Connection conn = pool.getConnection();
		try {
			String sql = "update users set username = ?, password = ?, name = ?, address = ?, mobile = ?, email = ? where uid = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, users.getUsername());
			ps.setString(2, users.getPassword());
			ps.setString(3, users.getName());
			ps.setString(4, users.getAddress());
			ps.setString(5, users.getMobile());
			ps.setString(6, users.getEmail());
			ps.setInt(7, users.getUid());
			ps.executeUpdate();
		} catch (SQLException sq) {
			System.out.println("Unable to edit the row." + sq);
		} finally {
			pool.putConnection(conn);
		}
	}

	public void remove(int uid) {
		ConnectionPool pool = ConnectionPool.getInstance();
		pool.initialize();
		Connection conn = pool.getConnection();
		try {
			String sql = "delete from users where uid = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, uid);
			ps.executeUpdate();
		} catch (SQLException sq) {
			System.out.println("Unable to delete the row." + sq);
		} finally {
			pool.putConnection(conn);
		}
	}

	public Users find(int uid) {
		ConnectionPool pool = ConnectionPool.getInstance();
		pool.initialize();
		Connection conn = pool.getConnection();
		Users users = new Users();
		try {
			String sql = "select * from users where uid= ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, uid);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				users.setUid(uid);
				users.setUsername(rs.getString("username"));
				users.setPassword(rs.getString("password"));
				users.setName(rs.getString("name"));
				users.setAddress(rs.getString("address"));
				users.setMobile(rs.getString("mobile"));
				users.setEmail(rs.getString("email"));
			}
		} catch (SQLException sq) {
			System.out.println("Unable to find a row." + sq);
		} finally {
			pool.putConnection(conn);
		}
		return users;
	}

	public Users findByUsername(String username) {
		ConnectionPool pool = ConnectionPool.getInstance();
		pool.initialize();
		Connection conn = pool.getConnection();
		Users users = new Users();
		try {
			String sql = "select * from users where username = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				users.setUid(rs.getInt("uid"));
				users.setUsername(username);
				users.setPassword(rs.getString("password"));
				users.setName(rs.getString("name"));
				users.setAddress(rs.getString("address"));
				users.setMobile(rs.getString("mobile"));
				users.setEmail(rs.getString("email"));
			}
		} catch (SQLException sq) {
			System.out.println("Unable to find a row." + sq);
		} finally {
			pool.putConnection(conn);
		}
		return users;
	}

	public ArrayList<Users> findAll() {
		ConnectionPool pool = ConnectionPool.getInstance();
		pool.initialize();
		Connection conn = pool.getConnection();
		ArrayList<Users> list = new ArrayList<Users>();
		try {
			String sql = "select * from users";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Users users = new Users();
				users.setUid(rs.getInt(rs.getInt("uid")));
				users.setUsername(rs.getString("username"));
				users.setPassword(rs.getString("password"));
				users.setName(rs.getString("name"));
				users.setAddress(rs.getString("address"));
				users.setMobile(rs.getString("mobile"));
				users.setEmail(rs.getString("email"));
			
				list.add(users);
			
			}
		} catch (SQLException sq) {
			System.out.println("unable to find the records." + sq);
		} finally {
			pool.putConnection(conn);
		}
		return list;
	}

	public int checkAvailablity(String uname) {
		// This will check available user of a particular name.
		ConnectionPool pool = ConnectionPool.getInstance();
		pool.initialize();
		Connection conn = pool.getConnection();
		try {
			String sql = "select * from users where username = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, uname);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				if (rs.getString("username").equals(uname)) {
					return rs.getInt("uid");
				}
				
			}
		} catch (SQLException sq) {
			System.out.println("unable to find the records." + sq);
		} finally {
			pool.putConnection(conn);
		}
		return 0;

	}

	public int checkAvailablity(String uname, String pwd) {
		// This will check available user based on username and password.
		ConnectionPool pool = ConnectionPool.getInstance();
		pool.initialize();
		Connection conn = pool.getConnection();
		try {
			String sql = "select * from users where username = ? && password = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, uname);
			ps.setString(2, pwd);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				if (rs.getString("username").equals(uname) && rs.getString("password").equals(pwd)) {
					
					return rs.getInt("uid");
				}
			}
		} catch (SQLException sq) {
			System.out.println("unable to find the records." + sq);
		} finally {
			pool.putConnection(conn);
		}
		return 0;

	}
	

	public int checkAvailablityWithEmail(String uname, String email) {
		// This will check available user based on username and password.
		ConnectionPool pool = ConnectionPool.getInstance();
		pool.initialize();
		Connection conn = pool.getConnection();
		try {
			String sql = "select * from users where username = ? && email = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, uname);
			ps.setString(2, email);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
			
				if (rs.getString("username").equals(uname) && rs.getString("email").equals(email)) {		
					return rs.getInt("uid");
				}
			}
		} catch (SQLException sq) {
			System.out.println("unable to find the records." + sq);
		} finally {
			pool.putConnection(conn);
		}
		return 0;

	}
	public Users authenticate(String un, String pwd) {
		n// This will return details of user which have given username and passw
		ConnectionPool pool = ConnectionPool.getInstance();
		pool.initialize();
		Connection conn = pool.getConnection();
		Users users = new Users();
		try {
			String sql = "select * from users where username = ? && password= ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			users.setUid(rs.getInt("uid"));
			users.setUsername(un);
			users.setPassword(pwd);
			users.setName(rs.getString("name"));
			users.setAddress(rs.getString("address"));
			users.setMobile(rs.getString("mobile"));
			users.setEmail(rs.getString("email"));
		} catch (SQLException sq) {
			System.out.println("unable to find the records." + sq);
		} finally {
			pool.putConnection(conn);
		}
		return users;
	}

}
