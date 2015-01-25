package akvelon.artemgvozdik.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import akvelon.artemgvozdik.ConnectionFactory;
import akvelon.artemgvozdik.bean.User;

public class UserDAO {
	static Logger logger = Logger.getLogger(UserDAO.class);

	public List<User> getAll() {
		logger.info("getAll method started");
		List<User> ulist = new ArrayList<User>();
		try (Connection con = ConnectionFactory.getConnection()) {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM akvelon.users");
			while (rs.next()) {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setBalance(rs.getDouble("balance"));
				user.setFname(rs.getString("fname"));
				user.setLname(rs.getString("lname"));
				user.setLogin(rs.getString("login"));
				user.setBirthday(rs.getDate("birthday"));
				ulist.add(user);
			}
		} catch (SQLException e) {
			logger.error("Exception ", e);
		}
		logger.info("getAll method finished");
		return ulist;
	}

	public User getUser(int id) {
		logger.info("getUser method started");
		User user = new User();
		try (Connection con = ConnectionFactory.getConnection()) {
			PreparedStatement st = con
					.prepareStatement("SELECT * FROM akvelon.users WHERE id = ?");
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				user.setId(rs.getInt("id"));
				user.setBalance(rs.getDouble("balance"));
				user.setFname(rs.getString("fname"));
				user.setLname(rs.getString("lname"));
				user.setLogin(rs.getString("login"));
				user.setBirthday(rs.getDate("birthday"));
			}
		} catch (SQLException e) {
			logger.error("Exception ", e);
		}
		logger.info("getUser method finished");
		return user;
	}

	public void addUser(User user) {
		logger.info("addUser method started");
		try (Connection con = ConnectionFactory.getConnection()) {
			PreparedStatement st = con
					.prepareStatement("insert into akvelon.users (fname, lname, login, balance, birthday) values (?, ?, ?, ?, ?)");
			st.setString(1, user.getFname());
			st.setString(2, user.getLname());
			st.setString(3, user.getLogin());
			st.setDouble(4, user.getBalance());
			st.setDate(5, new Date(user.getBirthday().getTime()));
			st.executeUpdate();

		} catch (SQLException e) {
			logger.error("Exception ", e);
		}
		logger.info("addUser method finished");

	}

	public void delete(int id) {
		logger.info("delete method started");
		try (Connection con = ConnectionFactory.getConnection()) {
			PreparedStatement st = con
					.prepareStatement("DELETE FROM akvelon.users WHERE id=?");
			st.setInt(1, id);
			st.executeUpdate();

		} catch (SQLException e) {
			logger.error("Exception ", e);
		}
		logger.info("delete method finished");

	}

	public void update(User user) {
		logger.info("update method started");
		try (Connection con = ConnectionFactory.getConnection()) {
			PreparedStatement st = con
					.prepareStatement("update akvelon.users set fname=?, lname=?, login=?, balance=?, birthday=? where id=? ");
			st.setInt(6, user.getId());
			st.setString(1, user.getFname());
			st.setString(2, user.getLname());
			st.setString(3, user.getLogin());
			st.setDouble(4, user.getBalance());
			st.setDate(5, new Date(user.getBirthday().getTime()));
			st.executeUpdate();

		} catch (SQLException e) {
			logger.error("Exception ", e);
		}
		logger.info("update method finished");
	}

}
