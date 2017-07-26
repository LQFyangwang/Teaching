package com.jh.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jh.bean.Account;
import com.jh.bean.Message;
import com.jh.common.EncryptUtil;

public class AccountDAOImpl implements AccountDAO {

	// 实现AccountDAO的接口的添加数据的方法：
	@Override
	public Account add(Account account) {
		String sql = "insert into t_account(number, pwd, nickname, gender, age, autograph, provice, city, area, status, mobile, skin) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		Connection conn = BaseDAO.getConn();
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, account.getNumber());
			ps.setString(2, EncryptUtil.encrypt(account.getPwd())); // 把字符串加密后存储到数据库
			ps.setString(3, account.getNickname());
			ps.setString(4, account.getGender());
			ps.setInt(5, account.getAge());
			ps.setString(6, account.getAutograph());
			ps.setString(7, account.getProvice());
			ps.setString(8, account.getCity());
			ps.setString(9, account.getArea());
			ps.setString(10, account.getStatus());
			ps.setString(11, account.getMobile());
			ps.setString(12, account.getSkin());
			ps.execute();
			ps.close();
			conn.close();
			return account;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Account> queryAll() {
		String sql = "select * from t_account";
		Connection conn = BaseDAO.getConn();
		List<Account> accounts = new ArrayList<Account>();
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Account a = new Account();
				a = new Account();
				a.setNumber(rs.getString("number"));
				a.setNickname(rs.getString("nickname"));
				a.setGender(rs.getString("gender"));
				a.setAge(rs.getInt("age"));
				a.setAutograph(rs.getString("autograph"));
				a.setProvice(rs.getString("provice"));
				a.setCity(rs.getString("city"));
				a.setArea(rs.getString("area"));
				a.setHead(rs.getString("head"));
				a.setStatus(rs.getString("status"));
				a.setMobile(rs.getString("mobile"));
				a.setSkin(rs.getString("skin"));
				accounts.add(a);
			}
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return accounts;
	}

	@Override
	public Account query(String number, String pwd) {
		String sql = "select * from t_account where number = ? and pwd = ?"; // 查询SQL语句
		Connection conn = BaseDAO.getConn(); // 连接到数据库
		Account a = null;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, number);
			ps.setString(2, EncryptUtil.encrypt(pwd)); // 去匹配加密后的字符串
			ResultSet rs = ps.executeQuery(); // 查询到的数据返回结果集
			if (rs.next()) {
				a = new Account();
				a.setNumber(number);
				a.setNickname(rs.getString("nickname"));
				a.setGender(rs.getString("gender"));
				a.setPwd(rs.getString("pwd"));
				a.setAge(rs.getInt("age"));
				a.setAutograph(rs.getString("autograph"));
				a.setProvice(rs.getString("provice"));
				a.setCity(rs.getString("city"));
				a.setArea(rs.getString("area"));
				a.setStatus(rs.getString("status"));
				a.setMobile(rs.getString("mobile"));
				a.setHead(rs.getString("head"));
				a.setSkin(rs.getString("skin"));
			}
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return a;
	}

	@Override
	public Account query(String number) {
		String sql = "select * from t_account where number = ?";
		Connection conn = BaseDAO.getConn();
		Account a = null;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, number);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				a = new Account();
				a.setNumber(number);
				a.setNickname(rs.getString("nickname"));
				a.setGender(rs.getString("gender"));
				a.setAge(rs.getInt("age"));
				a.setAutograph(rs.getString("autograph"));
				a.setProvice(rs.getString("provice"));
				a.setCity(rs.getString("city"));
				a.setArea(rs.getString("area"));
				a.setStatus(rs.getString("status"));
				a.setHead(rs.getString("head"));
				a.setMobile(rs.getString("mobile"));
				a.setSkin(rs.getString("skin"));
			}
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return a;
	}

	@Override
	public List<Account> queryAll(Account account) {
		String sql = "select * from t_account where number != ?";
		Connection conn = BaseDAO.getConn();
		List<Account> accounts = new ArrayList<Account>();
		try {
 			PreparedStatement ps = conn.prepareStatement(sql);
 			ps.setString(1, account.getNumber());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Account a = new Account();
				a = new Account();
				a.setNumber(rs.getString("number"));
				a.setNickname(rs.getString("nickname"));
				a.setGender(rs.getString("gender"));
				a.setAge(rs.getInt("age"));
				a.setAutograph(rs.getString("autograph"));
				a.setProvice(rs.getString("provice"));
				a.setCity(rs.getString("city"));
				a.setArea(rs.getString("area"));
				a.setHead(rs.getString("head"));
				a.setStatus(rs.getString("status"));
				a.setMobile(rs.getString("mobile"));
				a.setSkin(rs.getString("skin"));
				accounts.add(a);
			}
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return accounts;
	}

	@Override
	public List<Account> querySingle(String number, String nickname) {
		String sql = "select * from t_account where number = ? or nickname = ?";
		Connection conn = BaseDAO.getConn();
		List<Account> accounts = new ArrayList<Account>();
		try {
 			PreparedStatement ps = conn.prepareStatement(sql);
 			ps.setString(1, number);
 			ps.setString(2, nickname);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Account a = new Account();
				a = new Account();
				a.setNumber(rs.getString("number"));
				a.setNickname(rs.getString("nickname"));
				a.setGender(rs.getString("gender"));
				a.setAge(rs.getInt("age"));
				a.setAutograph(rs.getString("autograph"));
				a.setProvice(rs.getString("provice"));
				a.setCity(rs.getString("city"));
				a.setArea(rs.getString("area"));
				a.setHead(rs.getString("head"));
				a.setStatus(rs.getString("status"));
				a.setMobile(rs.getString("mobile"));
				a.setSkin(rs.getString("skin"));
				accounts.add(a);
			}
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return accounts;
	}

	@Override
	public void update(Account account) {
		String sql = "update t_account set nickname = ?, gender = ?, age = ?, autograph = ?, provice = ?, city = ?, area = ?, status = ?, mobile = ?, head = ?, skin = ? where number = ?";
		Connection conn = BaseDAO.getConn();
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, account.getNickname());
			ps.setString(2, account.getGender());
			ps.setInt(3, account.getAge());
			ps.setString(4, account.getAutograph());
			ps.setString(5, account.getProvice());
			ps.setString(6, account.getCity());
			ps.setString(7, account.getArea());
			ps.setString(8, account.getStatus());
			ps.setString(9, account.getMobile());
			ps.setString(10, account.getHead());
			ps.setString(11, account.getSkin());
			ps.setString(12, account.getNumber());
			ps.executeUpdate();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updatePwd(Account account) {
		String sql = "update t_account set pwd = ? where number = ?";
		Connection conn = BaseDAO.getConn();
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, EncryptUtil.encrypt(account.getPwd()));
			ps.setString(2, account.getNumber());
			ps.executeUpdate();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public List<Account> queryFriends(String number) {
		String sql = "select a.* from t_account a, t_friends f where a.number = f.account_number2 and f.account_number1 = ?";
		Connection conn = BaseDAO.getConn();
		List<Account> accounts = new ArrayList<Account>();
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, number);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Account a = new Account();
				a.setNumber(rs.getString("number"));
				a.setNickname(rs.getString("nickname"));
				a.setGender(rs.getString("gender"));
				a.setAge(rs.getInt("age"));
				a.setAutograph(rs.getString("autograph"));
				a.setProvice(rs.getString("provice"));
				a.setCity(rs.getString("city"));
				a.setArea(rs.getString("area"));
				a.setHead(rs.getString("head"));
				a.setStatus(rs.getString("status"));
				a.setMobile(rs.getString("mobile"));
				a.setSkin(rs.getString("skin"));
				accounts.add(a);
			}
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return accounts;
	}

	@Override
	public List<Account> queryNotOfflineFriends(String number) {
		String sql = "select a.* from t_account a, t_friends f where a.number = f.account_number2 and f.account_number1 = ? and a.status != 'offline'";
		Connection conn = BaseDAO.getConn();
		List<Account> accounts = new ArrayList<Account>();
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, number);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Account a = new Account();
				a.setNumber(rs.getString("number"));
				a.setNickname(rs.getString("nickname"));
				a.setGender(rs.getString("gender"));
				a.setAge(rs.getInt("age"));
				a.setAutograph(rs.getString("autograph"));
				a.setProvice(rs.getString("provice"));
				a.setCity(rs.getString("city"));
				a.setArea(rs.getString("area"));
				a.setHead(rs.getString("head"));
				a.setStatus(rs.getString("status"));
				a.setMobile(rs.getString("mobile"));
				a.setSkin(rs.getString("skin"));
				accounts.add(a);
			}
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return accounts;
	}
	
	@Override
	public void addFriends(String selfNumber, String number) {
		String sql = "insert t_friends(account_number1, account_number2) values(?, ?)";
		Connection conn = BaseDAO.getConn();
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, selfNumber);
			ps.setString(2, number);
			ps.execute();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean queryFriend(String number, String toNumber) {
		String sql = "select * from t_friends where account_number1 = ? and account_number2 = ?";
		Connection conn = BaseDAO.getConn();
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, number);
			ps.setString(2, toNumber);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Message add(Message message) {
		String sql = "insert into t_message(account_number1, account_number2, message, send_time) values(?, ?, ?, ?)";
		Connection conn = BaseDAO.getConn();
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, message.getFromAccount().getNumber());
			ps.setString(2, message.getToAccount().getNumber());
			ps.setString(3, message.getMessage());
			ps.setDate(4, new Date(message.getSendTime().getTime()));
			ps.execute();
			ps.close();
			conn.close();
			return message;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
