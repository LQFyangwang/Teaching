package com.xk.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.xk.bean.Account;
import com.xk.common.EncryptUtil;

public class AccountDAOImpl extends BaseDAO implements AccountDAO {

	@Override // 添加信息到数据库，
	public Account add(Account account) {
		String sql = "insert into account(number,pwd,nickname,autograph,gender,age,adres,contact,birthday,states) values(?,?,?,?,?,?,?,?,?,'offline')";
		Connection con = getCon();
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, account.getNumber());
			ps.setString(2, EncryptUtil.encrypt(account.getPwd()));
			ps.setString(3, account.getNickname());
			ps.setString(4, account.getAutograph());
			ps.setString(5, account.getGender());
			ps.setInt(6, account.getAge());
			ps.setString(7, account.getAdres());
			ps.setString(8, account.getContact());
			ps.setString(9, account.getBirthday());
			ps.execute();
			ps.close();
			con.close();
			return account;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override // 查询是否有这个账号密码
	public Account query(String number, String pwd) {
		Connection con = getCon();
		String sql = "select * from account where number = ? and pwd = ?";
		Account a = null;
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, number);
			ps.setString(2, EncryptUtil.encrypt(pwd));
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				a = new Account();
				a.setNumber(number);
				a.setNickname(rs.getString("nickname"));
				a.setAutograph(rs.getString("autograph"));
				a.setGender(rs.getString("gender"));
				a.setAge(rs.getInt("age"));
				a.setAdres(rs.getString("adres"));
				a.setBirthday(rs.getString("birthday"));
				a.setState(rs.getString("states"));
				a.setContact(rs.getString("contact"));
				a.setHeadIcon(rs.getString("headicon"));
			}
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return a;
	}

	@Override // 查询是否有这个账号，有则在生成一个账号
	public boolean query(String number) {
		Connection con = getCon();
		String sql = "select count(*) from account where number = ?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, number);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getInt(1) == 1;
			}
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	@Override // 更新数据库里的个性签名
	public void update(Account account) {
		Connection con = getCon();
		String sql = "update account set nickname = ? , gender = ? , age = ? , birthday = ? , adres = ? , contact = ? ,  headicon = ? , autograph = ?  where number = ?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, account.getNickname());
			ps.setString(2, account.getGender());
			ps.setInt(3, account.getAge());
			ps.setString(4, account.getBirthday());
			ps.setString(5, account.getAdres());
			ps.setString(6, account.getContact());
			ps.setString(7, account.getHeadIcon());
			ps.setString(8, account.getAutograph());
			ps.setString(9, account.getNumber());
			ps.executeUpdate();
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override // 查询数据库里所有的人放在list中
	public List<Account> queryAll(String number) {
		Connection con = getCon();
		String sql = "select * from account where number != ?";
		List<Account> accounts = new ArrayList<Account>();
		try {

			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, number);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Account a = new Account();
				a.setNumber(rs.getString("number"));
				a.setNickname(rs.getString("nickname"));
				a.setGender(rs.getString("gender"));
				a.setAge(rs.getInt("age"));
				a.setAdres(rs.getString("adres"));
				a.setContact(rs.getString("contact"));
				a.setState(rs.getString("states"));
				accounts.add(a);
			}
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return accounts;
	}

	@Override // 根据账号昵称查这个人
	public List<Account> queryAll(String number, String nickName) {
		Connection con = getCon();
		String sql = "select * from account where number = ? or nickName = ?";
		List<Account> accounts = new ArrayList<Account>();
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, number);
			ps.setString(2, nickName);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Account a = new Account();
				a.setNumber(rs.getString("number"));
				a.setNickname(rs.getString("nickname"));
				a.setGender(rs.getString("gender"));
				a.setAge(rs.getInt("age"));
				a.setAdres(rs.getString("adres"));
				a.setContact(rs.getString("contact"));
				a.setState(rs.getString("states"));
				accounts.add(a);
			}
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return accounts;
	}

	@Override
	public Account queryFriend(String number) {
		Connection con = getCon();
		String sql = "select * from account where number = ?";
		Account a = null;
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, number);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				a = new Account();
				a.setNumber(number);
				a.setNickname(rs.getString("nickname"));
				a.setAutograph(rs.getString("autograph"));
				a.setGender(rs.getString("gender"));
				a.setAge(rs.getInt("age"));
				a.setAdres(rs.getString("adres"));
				a.setBirthday(rs.getString("birthday"));
				a.setContact(rs.getString("contact"));
				a.setState(rs.getString("states"));
				a.setHeadIcon(rs.getString("headicon"));
			}
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return a;
	}

	@Override
	public void addFriend(String selfNumber, String number) {
		Connection con = getCon();
		String sql = "insert into friends (account_number1,account_number2)values(?,?)";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, selfNumber);
			ps.setString(2, number);
			ps.execute();
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Account> queryFroemds(String number) {
		String sql = "select a.* from account a, friends f where a.number = f.account_number2 and f.account_number1 = ?";
		Connection con = getCon();
		List<Account> accounts = new ArrayList<Account>();
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, number);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Account a = new Account();
				a.setNumber(rs.getString("number"));
				a.setNickname(rs.getString("nickname"));
				a.setAutograph(rs.getString("autograph"));
				a.setGender(rs.getString("gender"));
				a.setAge(rs.getInt("age"));
				a.setAdres(rs.getString("adres"));
				a.setBirthday(rs.getString("birthday"));
				a.setState(rs.getString("states"));
				a.setHeadIcon(rs.getString("headicon"));
				a.setContact(rs.getString("contact"));
				accounts.add(a);
			}
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return accounts;
	}

	@Override
	public List<Account> queryNotLeaveFriends(String number) {
		String sql = "select a.* from account a, friends f where a.number = f.account_number2 and f.account_number1 = ? and a.states != 'offline'";
		Connection con = getCon();
		List<Account> accounts = new ArrayList<Account>();
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, number);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Account a = new Account();
				a.setNumber(rs.getString("number"));
				a.setNickname(rs.getString("nickname"));
				a.setAutograph(rs.getString("autograph"));
				a.setGender(rs.getString("gender"));
				a.setAge(rs.getInt("age"));
				a.setAdres(rs.getString("adres"));
				a.setBirthday(rs.getString("birthday"));
				a.setState(rs.getString("states"));
				a.setHeadIcon(rs.getString("headicon"));
				a.setContact(rs.getString("contact"));
				accounts.add(a);
			}
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return accounts;
	}

	@Override
	public void updateStatus(String number, String status) {
		String sql = "update account set states = ? where number = ? ";
		Connection con = getCon();
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, status);
			ps.setString(2, number);
			ps.execute();
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public boolean queryFriend(String number, String toNumber) {
		
		String sql = "select *from friends where account_number1 = ? and account_number2 = ?";
		Connection con = getCon();
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, number);
			ps.setString(2, toNumber);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return true;
			}
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public void deleteFriend(String number, String toNumber) {
		String sql = "delete  from friends where account_number1 = ? and account_number2 = ?";
		Connection con = getCon();
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, number);
			ps.setString(2, toNumber);
			ps.execute();
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void addBlack(String selfNumber, String number) {
		Connection con = getCon();
		String sql = "insert into blacks (account_number1,account_number2)values(?,?)";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, selfNumber);
			ps.setString(2, number);
			ps.execute();
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Account> BlackList(String number) {
		String sql = "select a.* from account a, blacks s where a.number = s.account_number2 and s.account_number1 = ?";
		Connection con = getCon();
		List<Account> accounts = new ArrayList<Account>();
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, number);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Account a = new Account();
				a.setNumber(rs.getString("number"));
				a.setNickname(rs.getString("nickname"));
				a.setAutograph(rs.getString("autograph"));
				a.setGender(rs.getString("gender"));
				a.setAge(rs.getInt("age"));
				a.setAdres(rs.getString("adres"));
				a.setBirthday(rs.getString("birthday"));
				a.setState(rs.getString("states"));
				a.setHeadIcon(rs.getString("headicon"));
				a.setContact(rs.getString("contact"));
				accounts.add(a);
			}
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return accounts;
	}


	@Override
	public void deleteBalck(String number, String toNumber) {
		String sql = "delete  from blacks where account_number1 = ? and account_number2 = ?";
		Connection con = getCon();
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, number);
			ps.setString(2, toNumber);
			ps.execute();
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
