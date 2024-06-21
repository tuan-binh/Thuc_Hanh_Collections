package ra.presentation;

import ra.business.entity.Author;
import ra.business.entity.Book;
import ra.business.feature.IAuthorFeature;
import ra.business.feature.impl.AuthorFeatureImpl;
import ra.business.feature.impl.BookFeatureImpl;

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
					updateAuthor(scanner);
					break;
				case 4:
					searchAuthorByName(scanner);
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
	
	private static void searchAuthorByName(Scanner scanner) {
		if (authorFeature.findAll().isEmpty()) {
			System.err.println("Chưa có tác giả nào");
			return;
		}
		boolean hasAuthor = false;
		System.out.println("Nhập vào tên muốn tìm kiếm");
		String keyword = scanner.nextLine().toLowerCase();
		for (Author author : authorFeature.findAll()) {
			if (author.getAuthorName().toLowerCase().contains(keyword)) {
				hasAuthor = true;
				author.displayData();
			}
		}
		if (!hasAuthor) {
			System.err.println("Không tìm thấy tác giả có tên là: " + keyword);
		}
	}
	
	private static void updateAuthor(Scanner scanner) {
		System.out.println("nhập vào id author muốn update: ");
		int idUpdate = Integer.parseInt(scanner.nextLine());
		int indexUpdate = authorFeature.findIndexById(idUpdate);
		if (indexUpdate >= 0) {
			Author authorUpdate = AuthorFeatureImpl.authors.get(indexUpdate);
			boolean isLoop = true;
			authorUpdate.displayData();
			do {
				System.out.println("1. Update name");
				System.out.println("2. Update description");
				System.out.println("3. Update age");
				System.out.println("4. Update status");
				System.out.println("5. Exit");
				System.out.println("Lựa chọn update: ");
				int choiceUpdate = Integer.parseInt(scanner.nextLine());
				switch (choiceUpdate) {
					case 1:
						System.out.println("Nhập lại tên: ");
						authorUpdate.setAuthorName(scanner.nextLine());
						break;
					case 2:
						System.out.println("Nhập lại mô tả");
						authorUpdate.setDescription(scanner.nextLine());
						break;
					case 3:
						System.out.println("Nhập lại tuổi");
						authorUpdate.setAge(Integer.parseInt(scanner.nextLine()));
						break;
					case 4:
						System.out.println("Nhập lại trạng thái");
						authorUpdate.setStatus(Boolean.parseBoolean(scanner.nextLine()));
						break;
					case 5:
						isLoop = false;
						break;
					default:
						System.err.println("Vui lòng chọn lại từ 1 -> 5");
				}
			} while (isLoop);
			System.out.println("Đã cập nhật thành công");
			authorFeature.addOrUpdate(authorUpdate);
		} else {
			System.err.println("Không tồn tại author đó");
		}
	}
	
	private static void staticalAuthorBook() {
		for (Author a : authorFeature.findAll()) {
			long count = BookFeatureImpl.books.stream().filter(book -> book.getAuthor().getAuthorId() == a.getAuthorId()).count();
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
