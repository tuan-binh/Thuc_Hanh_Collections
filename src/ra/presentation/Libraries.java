package ra.presentation;

import java.util.Scanner;

public class Libraries {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		do {
			System.out.println("================== MENU LIBRARIES ==================\n" +
					  "1.\tQuản lý tác giả\n" +
					  "2.\tQuản lý quyển sách\n" +
					  "3.\tThoát\n");
			System.out.println("Nhập vào lựa chọn: ");
			int choice = Integer.parseInt(scanner.nextLine());
			switch (choice) {
				case 1:
					AuthorManagement.menuAuthor(scanner);
					break;
				case 2:
					BookManagement.menuBook(scanner);
					break;
				case 3:
					System.exit(0);
				default:
					System.err.println("Vui lòng chọn lại từ 1 -> 3");
			}
		} while (true);
	}
}
