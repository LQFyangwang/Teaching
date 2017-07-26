package com.gs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gs.bean.Account;
import com.gs.common.EncryptUtil;

public class AccountDAOImpl extends BaseDAO implements AccountDAO {

	@Override
	public Account add(Account account) {
		String sql = "insert into t_account(number, nickname, pwd, gender, status) values(?, ?, ?, ?, 'offline')";
		Connection conn = getConn();
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, account.getNumber());
			ps.setString(2, account.getNickname());
			ps.setString(3, EncryptUtil.encrpty(account.getPwd()));
			ps.setString(4, account.getGender());
			ps.execute();
			return account;
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Account query(Account account) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account query(String number, String pwd) {
		String sql = "select * from t_account where number = ? and pwd =?";
		Connection conn = getConn();
		Account a = null;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1,number);
			ps.setString(2, EncryptUtil.encrpty(pwd));
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				a = new Account();
				a.setNumber(number);
				a.setNickname(rs.getString("nickname"));
				a.setGender(rs.getString("gender"));
				a.setHeadIcon(rs.getString("headIcon"));
			}
			ps.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return a;
	}
	
	@Override
	public List<Account> queryAll() {
		String sql = "select * from t_account";
		Connection conn = getConn();
		List<Account> accounts = new ArrayList<Account>();
		try {
 			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Account a = new Account();
				a.setNumber(rs.getString("number"));
				a.setNickname(rs.getString("nickname"));
				a.setGender(rs.getString("gender"));
				a.setHeadIcon(rs.getString("headIcon"));
				accounts.add(a);
			}
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return accounts;
	}

	@Override
	public Account query(String number) {
		String sql = "select * from t_account where number = ?";
		Connection conn = getConn();
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
				a.setHeadIcon(rs.getString("headIcon"));
			}
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return a;
	}
	
	@Override
	public void addFriend(String selfNumber, String number) {
		String sql = "insert into t_friends(account_number1, account_number2) values(?, ?)";
		Connection conn = getConn();
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, selfNumber);
			ps.setString(2, number);
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public List<Account> queryFriends(String number) {
		String sql = "select a.* from t_account a, t_friends f where a.number = f.account_number2 and f.account_number1 = ?";
		Connection conn = getConn();
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
				a.setHeadIcon(rs.getString("headIcon"));
				a.setStatus(rs.getString("status"));
				accounts.add(a);
			}
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return accounts;
	}
	
	@Override
	public List<Account> queryNotLeaveFriends(String number) {
		String sql = "select a.* from t_account a, t_friends f where a.number = f.account_number2 and f.account_number1 = ? and a.status !='offline'";
		Connection conn = getConn();
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
				a.setHeadIcon(rs.getString("headIcon"));
				a.setStatus(rs.getString("status"));
				accounts.add(a);
			}
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return accounts;
	}
	
	@Override
	public void updateStatus(String number, String status) {
		String sql = "update t_account set status = ? where number = ?";
		Connection conn = getConn();
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, status);
			ps.setString(2, number);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
