package ra.presentation;

import ra.business.entity.Author;
import ra.business.entity.Book;
import ra.business.feature.IAuthorFeature;
import ra.business.feature.impl.AuthorFeatureImpl;
import ra.business.feature.impl.BookFeatureImpl;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AuthorManagement {
	
	private static final IAuthorFeature authorFeature = new AuthorFeatureImpl();
	
	public static void menuAuthor(Scanner scanner) {
		boolean isLoop = true;
		do {
			System.out.println("================== MENU AUTHOR ==================\n" +
					  "1.\tHiển thị danh sách các tác giả\n" +
					  "2.\tThêm mới tác giả\n" +
					  "3.\tCập nhật thông tin tác giả\n" +
					  "4.\tTìm kiểm thông tin tác giả theo tên\n" +
					  "5.\tThống kê các tác giả có bao nhiêu quyển sách (sử dụng Map)\n" +
					  "6.\tXóa tác giả\n" +
					  "7.\tQuay lại\n");
			System.out.println("Nhập vào lựa chọn: ");
			int choice = Integer.parseInt(scanner.nextLine());
			switch (choice) {
				case 1:
					showAllAuthors();
					break;
				case 2:
					addNewAuthor(scanner);
					break;
				case 3:
					break;
				case 4:
					break;
				case 5:
					staticalAuthorBook();
					break;
				case 6:
					removeBook(scanner);
					break;
				case 7:
					isLoop = false;
					break;
				default:
					System.err.println("Vui lòng nhập lại từ 1 -> 7");
			}
		} while (isLoop);
	}
	
	private static void staticalAuthorBook() {
		for (Author a : authorFeature.findAll()) {
			int count = 0;
			for (Book b : BookFeatureImpl.books) {
				if (a.getAuthorId() == b.getAuthor().getAuthorId()) {
					count++;
				}
			}
			a.displayData();
			System.out.println("Số lượng sách = " + count);
		}
	}
	
	private static void removeBook(Scanner scanner) {
		System.out.println("Nhập vào id sách muốn xóa");
		int idDelete = Integer.parseInt(scanner.nextLine());
		authorFeature.deleteById(idDelete);
	}
	
	private static void addNewAuthor(Scanner scanner) {
		System.out.println("Nhập số lượng tác giả muốn thêm");
		int n = Integer.parseInt(scanner.nextLine());
		for (int i = 0; i < n; i++) {
			Author author = new Author();
			author.inputData(scanner);
			authorFeature.addOrUpdate(author);
		}
	}
	
	private static void showAllAuthors() {
		if (authorFeature.findAll().isEmpty()) {
			System.err.println("Chưa có tác giả nào");
			return;
		}
		for (Author author : authorFeature.findAll()) {
			author.displayData();
		}
	}
}
