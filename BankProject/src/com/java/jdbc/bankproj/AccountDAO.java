package com.java.jdbc.bankproj;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountDAO {
	
	Connection con;
	PreparedStatement pst;
	
	public int generateTransId() throws SQLException {
		con = ConnectionHelper.getConnection();
		String cmd = "select case when max(transId) is NULL then 1 "
				+ " else max(transId)+1 end tid from Transaction";
		pst = con.prepareStatement(cmd);
		ResultSet rs = pst.executeQuery();
		rs.next();
		int accNo=rs.getInt("tid");
		return accNo;
	}
	public String depositAccount(int accountNo, int depositAmount) throws SQLException {
		Account account = searchAccount(accountNo);
		String result="";
		int tid = generateTransId();
		if (account!=null) {
			con = ConnectionHelper.getConnection();
			String cmd = "update account set amount=amount+?  "
					+ " where accountNo=?";
			pst = con.prepareStatement(cmd);
			pst.setInt(1, depositAmount);
			pst.setInt(2, accountNo);
			pst.executeUpdate();
			cmd = "Insert into transaction(transid,accountNo,TranAmount,TransType) "
					+ " values(?,?,?,?)";
			pst = con.prepareStatement(cmd);
			pst.setInt(1, tid);
			pst.setInt(2, accountNo);
			pst.setInt(3, depositAmount);
			pst.setString(4, "C");
			pst.executeUpdate();
			result = "Amount Credited...";
		} else {
			result = "Account No Not Found...";
		}
		return result;
	}
	
	public String withdrawAccount(int accountNo, int withdrawAmount) throws SQLException {
		
		
		Account account = searchAccount(accountNo);
		String result="";
		int tid = generateTransId();
		if (account!=null) {
			con = ConnectionHelper.getConnection();
			String cmd = "update account set amount=amount-?  "
					+ " where accountNo=?";
			pst = con.prepareStatement(cmd);
			pst.setInt(1, withdrawAmount);
			
			pst.setInt(2, accountNo);
			pst.executeUpdate();
			cmd = "Insert into transaction(transid,accountNo,TranAmount,TransType) "
					+ " values(?,?,?,?)";
			pst = con.prepareStatement(cmd);
			pst.setInt(1, tid);
			pst.setInt(2, accountNo);
			pst.setInt(3, withdrawAmount);
			pst.setString(4, "D");
			pst.executeUpdate();
			result = "Amount Withdrawn...";
			
		} else {
			result = "Account No. Not Found...";
		}
		return result;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public String updateAccount(int accountNo, String city, String state) throws SQLException {
		Account account = searchAccount(accountNo);
		String result = "";
		if (account!=null) {
			con = ConnectionHelper.getConnection();
			String cmd = "update account set City=?, State=? WHERE accountNo=?";
			pst = con.prepareStatement(cmd);
			pst.setString(1, city);
			pst.setString(2, state);
			pst.setInt(3, accountNo);
			pst.executeUpdate();
			result ="Account Updated successfully...";
		} else {
			result = "Invalid Account No...";
		}
		return result;
	}
	
	public Account searchAccount(int accountNo) {
		Account account = null;
		con = ConnectionHelper.getConnection();
		String cmd = "select * from Account where accountNo=?";
		try {
			pst = con.prepareStatement(cmd);
			pst.setInt(1, accountNo);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				account = new Account();
				account.setAccountNo(rs.getInt("accountNo"));
				account.setFirstName(rs.getString("firstName"));
				account.setLastName(rs.getString("lastName"));
				account.setCity(rs.getString("city"));
				account.setState(rs.getString("State"));
				account.setAmount(rs.getInt("amount"));
				account.setCheckFacil(rs.getString("checkFacil"));
				account.setAccountType(rs.getString("accountType"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return account;
	}
	public String createAccount(Account account) throws SQLException {
		con = ConnectionHelper.getConnection();
		String cmd = "insert into Account(accountNo,FirstName,LastName,City,state, "
				+ "amount,checkFacil,AccountType) values(?,?,?,?,?,?,?,?)";
		pst = con.prepareStatement(cmd);
		pst.setInt(1, account.getAccountNo());
		pst.setString(2, account.getFirstName());
		pst.setString(3, account.getLastName());
		pst.setString(4, account.getCity());
		pst.setString(5, account.getState());
		pst.setInt(6, account.getAmount());
		pst.setString(7, account.getCheckFacil());
		pst.setString(8, account.getAccountType());
		pst.executeUpdate();
		return "Account Created Successfully...";
	}
	public int generateAccountNo() throws SQLException {
		con = ConnectionHelper.getConnection();
		String cmd = "select case when max(accountNo) is NULL then 1 "
				+ " else max(accountNo)+1 end accno from Account";
		pst = con.prepareStatement(cmd);
		ResultSet rs = pst.executeQuery();
		rs.next();
		int accNo=rs.getInt("accno");
		return accNo;
	}

}
