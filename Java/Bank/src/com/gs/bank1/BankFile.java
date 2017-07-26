package com.gs.bank1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class BankFile {

	private static BufferedWriter getWriter(boolean append) {
		File file = new File("d:/bank");
		if (!file.exists()) {
			file.mkdir();
		}
		File file1 = new File(file, "accounts.txt");
		// new FileOutputStream(file, true) true或false表示往文件的末尾追加数据，覆盖数据。默认是false
		try {
			return new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file1, append), "utf-8"));
		} catch (UnsupportedEncodingException | FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void save(List<Account> accounts) {
		BufferedWriter bw = getWriter(false); // 先获取到BufferedWriter对象
		try {
			for (Account account : accounts) {
				String str = account.getNumber() + "," + account.getName() + "," + account.getPassword() + ","
						+ account.getMoney();
				bw.write(str + "\r\n"); // 直接在文件的末尾添加数据
			}
			bw.close();
		} catch(IOException e) {
			e.printStackTrace();
		}

	}

	public static void save(Account account) {
		BufferedWriter bw = getWriter(true);
		String str = account.getNumber() + "," + account.getName() + "," + account.getPassword() + ","
				+ account.getMoney();
		try {
			bw.append(str + "\r\n");
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static List<Account> read() {
		List<Account> accounts = new ArrayList<Account>();
		File file = new File("d:/bank/accounts.txt");
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf-8"));
			String str = null;
			while ((str = br.readLine()) != null) {
				String info[] = str.split(",");
				Account a = new Account();
				a.setNumber(Integer.valueOf(info[0]));
				a.setName(info[1]);
				a.setPassword(info[2]);
				a.setMoney(Double.valueOf(info[3]));
				accounts.add(a);
			}
			br.close();
		} catch (UnsupportedEncodingException | FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return accounts;
	}

}
