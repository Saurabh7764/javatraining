package com.java.jdbc.bankproj;

import java.sql.SQLException;
import java.util.Scanner;

public class CreateAccountMain {
	
	public static void main(String[] args) {
		AccountDAO dao = new AccountDAO();
		Account account = new Account();
		int accno;
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter FirstName  ");
		account.setFirstName(sc.next());
		System.out.println("Enter LastName  ");
		account.setLastName(sc.next());
		System.out.println("Enter City  ");
		account.setCity(sc.next());
		System.out.println("Enter State  ");
		account.setState(sc.next());
		System.out.println("Enter Amount  ");
		account.setAmount(Integer.parseInt(sc.next()));
		System.out.println("Enter CheckFacil  ");
		account.setCheckFacil(sc.next());
		System.out.println("Enter AccountType  ");
		account.setAccountType(sc.next());
		try {
			accno = dao.generateAccountNo();
			account.setAccountNo(accno);
			//System.out.println(accno);
			System.out.println(dao.createAccount(account));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
