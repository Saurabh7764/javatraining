package com.java.jdbc.bankproj;
import java.sql.SQLException;
import java.util.Scanner;

public class UpdateAccountMain {
	
	public static void main(String[] args) {
		int accountNo;
		String city,state;
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Account No ");
		accountNo=sc.nextInt();
		System.out.println("Enter City  ");
		city=sc.next();
		System.out.println("Enter State  ");
		state = sc.next();
		try {
			System.out.println(new AccountDAO().updateAccount(accountNo, city, state));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
