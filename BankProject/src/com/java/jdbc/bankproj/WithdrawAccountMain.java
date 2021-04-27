package com.java.jdbc.bankproj;

import java.sql.SQLException;
import java.util.Scanner;

public class WithdrawAccountMain {
	public static void main(String[] args) {
		int accountNo, withdrawAmount;
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter AccountNo  ");
		accountNo=sc.nextInt();
		System.out.println("Enter withdraw Amount  ");
		withdrawAmount =sc.nextInt();
		try {
			System.out.println(new AccountDAO().withdrawAccount( accountNo, withdrawAmount));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
