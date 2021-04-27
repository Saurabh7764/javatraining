package com.java.jdbc.bankproj;

import java.util.Scanner;

public class SearchAccountMain {
	public static void main(String[] args) {
		int accountNo;
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Account No  ");
		accountNo = sc.nextInt();
		Account account = new AccountDAO().searchAccount(accountNo);
		if (account!=null) {
			System.out.println(account);
		} else {
			System.out.println("*** Record Not Found ***");
		}
	}
}
