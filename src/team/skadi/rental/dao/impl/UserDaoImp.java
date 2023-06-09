package team.skadi.rental.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import team.skadi.rental.bean.User;
import team.skadi.rental.dao.UserDao;
import team.skadi.rental.utils.DBUtil;

public class UserDaoImp implements UserDao {

	@Override
	public User findUserById(String userId) {
		Connection connection = DBUtil.getConnection();
		if (userId == null) {
			return null;
		}
		String sql = "select * from users WHERE id=?;";
		PreparedStatement stat = null;
		ResultSet rs = null;
		User user = null;
		try {
			stat = connection.prepareStatement(sql);
			stat.setString(1, userId);
			rs = stat.executeQuery();// 执行SQL语句
			List<User> users = getlist(rs);
			if (users.size() > 0) {
				user = users.get(0);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeAll(connection, stat, rs);
		}
		if (user != null && user.getId() != null) {
			return user;
		} else {
			return null;
		}
	}

	@Override
	public void updateUser(User user) {
		Connection connection = DBUtil.getConnection();
		String sql = "UPDATE users SET name=?,phoneNumber=?,password=?,balance=?,email=?,credit=? WHERE serialnum=?;";
		PreparedStatement stat = null;
		ResultSet rs = null;
		try {
			stat = connection.prepareStatement(sql);
			stat.setString(1, user.getName());
			stat.setString(2, user.getPhoneNumber());
			stat.setString(3, user.getPassword());
			stat.setDouble(4, user.getBalance());
			stat.setString(5, user.getEmail());
			stat.setInt(6, user.getCredit());
			stat.setInt(7, user.getSerialnum());
			stat.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeAll(connection, stat, rs);
		}
	}

	@Override
	public List<User> getAllUser() {
		Connection connection = DBUtil.getConnection();
		String sql = "SELECT * FROM users;";
		PreparedStatement stat = null;
		ResultSet rs = null;
		List<User> users = null;
		try {
			stat = connection.prepareStatement(sql);
			rs = stat.executeQuery();
			users = getlist(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeAll(connection, stat, rs);
		}
		return users;
	}

	@Override
	public List<User> getUsersByBalance(double balance) {
		Connection connection = DBUtil.getConnection();
		String sql = "SELECT * FROM users WHERE balance<?;";
		PreparedStatement stat = null;
		ResultSet rs = null;
		List<User> users = null;
		try {
			stat = connection.prepareStatement(sql);
			stat.setDouble(1, balance);
			rs = stat.executeQuery();
			users = getlist(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeAll(connection, stat, rs);
		}
		return users;
	}

	@Override
	public List<User> getUsersByCredit(int credit) {
		Connection connection = DBUtil.getConnection();
		String sql = "SELECT * FROM users WHERE credit<?;";
		PreparedStatement stat = null;
		ResultSet rs = null;
		List<User> users = null;
		try {
			stat = connection.prepareStatement(sql);
			stat.setDouble(1, credit);
			rs = stat.executeQuery();
			users = getlist(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeAll(connection, stat, rs);
		}
		return users;
	}

	@Override
	public void createNewUser() {
		Connection connection = DBUtil.getConnection();
		String sql = "INSERT users (id,name,phoneNumber,password,balance,email) VALUE (null,null,null,null,null,null);";
		PreparedStatement stat = null;
		ResultSet rs = null;
		try {
			stat = connection.prepareStatement(sql);
			stat.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeAll(connection, stat, rs);
		}
	}

	@Override
	public void addNewUser(String userId, int serialnum) {
		Connection connection = DBUtil.getConnection();
		String sql = "UPDATE users SET id=? WHERE serialnum=?";
		PreparedStatement stat = null;
		ResultSet rs = null;
		try {
			stat = connection.prepareStatement(sql);
			stat.setString(1, userId);
			stat.setInt(2, serialnum);
			stat.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeAll(connection, stat, rs);
		}
	}

	@Override
	public int getSerialnum() {
		int serialnum = 0;
		Connection connection = DBUtil.getConnection();
		String sql = "SELECT * FROM users WHERE id is null";
		PreparedStatement stat = null;
		ResultSet rs = null;
		try {
			stat = connection.prepareStatement(sql);
			rs = stat.executeQuery();
			while (rs.next()) {
				serialnum = rs.getInt("serialnum");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return serialnum;
	}

	private List<User> getlist(ResultSet rs) throws SQLException {
		ArrayList<User> users = new ArrayList<>();
		while (rs.next()) {
			String id = rs.getString("id");
			if (id != null) {
				User user = new User();
				user.setId(id);
				user.setSerialnum(rs.getInt("serialnum"));
				user.setName(rs.getString("name"));
				user.setPhoneNumber(rs.getString("phoneNumber"));
				user.setPassword(rs.getString("password"));
				user.setBalance(rs.getDouble("balance"));
				user.setEmail(rs.getString("email"));
				user.setCredit(rs.getInt("credit"));
				users.add(user);
			}
		}
		return users;
	}
}
