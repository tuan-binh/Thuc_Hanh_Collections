package ra.business.entity;

import ra.business.feature.impl.AuthorFeatureImpl;
import ra.business.feature.impl.BookFeatureImpl;

import java.util.Date;
import java.util.Scanner;

public class Book implements IOData {
	private String bookId;
	private String bookName;
	private Double exportPrice;
	private Date created;
	private Author author;
	private Byte status;
	
	public Book() {
	}
	
	public Book(String bookId, String bookName, Double exportPrice, Date created, Author author, Byte status) {
		this.bookId = bookId;
		this.bookName = bookName;
		this.exportPrice = exportPrice;
		this.created = created;
		this.author = author;
		this.status = status;
	}
	
	public String getBookId() {
		return bookId;
	}
	
	public void setBookId(String bookId) {
		this.bookId = bookId;
	}
	
	public String getBookName() {
		return bookName;
	}
	
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	
	public Double getExportPrice() {
		return exportPrice;
	}
	
	public void setExportPrice(Double exportPrice) {
		this.exportPrice = exportPrice;
	}
	
	public Date getCreated() {
		return created;
	}
	
	public void setCreated(Date created) {
		this.created = created;
	}
	
	public Author getAuthor() {
		return author;
	}
	
	public void setAuthor(Author author) {
		this.author = author;
	}
	
	public Byte getStatus() {
		return status;
	}
	
	public void setStatus(Byte status) {
		this.status = status;
	}
	
	@Override
	public void inputData(Scanner scanner) {
		this.bookId = idAutoIncrement();
		this.bookName = inputBookName(scanner);
		this.exportPrice = inputExportPrice(scanner);
		this.created = new Date();
		this.author = inputAuthor(scanner);
		this.status = inputStatus(scanner);
	}
	
	private Byte inputStatus(Scanner scanner) {
		do {
			System.out.println("1. Đang bán");
			System.out.println("2. Hết hàng");
			System.out.println("3. Không bán");
			System.out.println("Lựa chọn trạng thái: ");
			int choice = Integer.parseInt(scanner.nextLine());
			if (choice > 0 && choice < 4) {
				return Byte.parseByte(String.valueOf(choice - 1));
			} else {
				System.err.println("Vui lòng nhập lại từ 1 -> 3");
			}
		} while (true);
	}
	
	private Author inputAuthor(Scanner scanner) {
		// show ra list tác giả
		for (Author a : AuthorFeatureImpl.authors) {
//			a.displayData();
			System.out.printf("[ ID: %d | Name: %10s ]\n", a.getAuthorId(), a.getAuthorName());
		}
		// cho nó nhập id tác giả và tìm ra
		System.out.println("Nhập vào ID tác giả muốn thêm: ");
		do {
			int authorIdChoice = Integer.parseInt(scanner.nextLine());
			int indexAuthor = findAuthorIndexById(authorIdChoice);
			if (indexAuthor >= 0) {
				return AuthorFeatureImpl.authors.get(indexAuthor);
			} else {
				System.err.println("không tìm thây author, vui lòng nhập lại id");
			}
		} while (true);
	}
	
	public int findAuthorIndexById(int authorId) {
		for (int i = 0; i < AuthorFeatureImpl.authors.size(); i++) {
			if (AuthorFeatureImpl.authors.get(i).getAuthorId() == authorId) {
				return i;
			}
		}
		return -1;
	}
	
	private Double inputExportPrice(Scanner scanner) {
		System.out.println("Nhập vào giá sách: ");
		do {
			String price = scanner.nextLine();
			if (price.trim().isEmpty()) {
				System.err.println("Không được để trống");
			} else {
				if (Double.parseDouble(price) < 0) {
					System.err.println("Giá sách phải lơn hơn 0");
				} else {
					return Double.parseDouble(price);
				}
			}
		} while (true);
	}
	
	private String inputBookName(Scanner scanner) {
		System.out.println("Nhập tên sách: ");
		do {
			String bookName = scanner.nextLine();
			if (bookName.trim().isEmpty()) {
				System.err.println("Không được để trống");
			} else {
				boolean isExist = false;
				for (Book b : BookFeatureImpl.books) {
					if (b.getBookName().equals(bookName)) {
						isExist = true;
						break;
					}
				}
				if (isExist) {
					System.err.println("Tên sách đã tồn tại");
				} else {
					return bookName;
				}
			}
		} while (true);
	}
	
	private String idAutoIncrement() {
		// B001, B002
		String result = "B";
		int maxId = 0;
		for (Book b : BookFeatureImpl.books) {
			// B001
			String bookId = b.getBookId();
			// 0001 -> 1
			int numberId = Integer.parseInt(bookId.replaceAll("B", "0"));
			if (maxId < numberId) {
				maxId = numberId;
			}
		}
		result += String.format("%03d", maxId + 1);
		return result;
	}
	
	@Override
	public void displayData() {
		System.out.printf(
				  "[ ID: %10s | Name: %20s | Price: %10f | Created: %10s | Author: %10s | Status: %20s ]\n",
				  this.bookId,
				  this.bookName,
				  this.exportPrice,
				  this.created.toString(),
				  this.author.getAuthorName(),
				  this.status == 0 ? "Đang bán" : this.status == 1 ? "Hết hàng" : "Không bán"
		);
	}
}
