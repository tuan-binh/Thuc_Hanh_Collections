package ra.business.entity;

import ra.business.feature.impl.AuthorFeatureImpl;

import java.util.Scanner;

public class Author implements IOData {
	private int authorId;
	private String authorName;
	private String description;
	private int age;
	private boolean status;
	
	public Author() {
	}
	
	public Author(int authorId, String authorName, String description, int age, boolean status) {
		this.authorId = authorId;
		this.authorName = authorName;
		this.description = description;
		this.age = age;
		this.status = status;
	}
	
	public int getAuthorId() {
		return authorId;
	}
	
	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}
	
	public String getAuthorName() {
		return authorName;
	}
	
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public int getAge() {
		return age;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	public boolean isStatus() {
		return status;
	}
	
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	@Override
	public void inputData(Scanner scanner) {
		this.authorId = idAutoIncrement();
		this.authorName = inputAuthorName(scanner);
		this.description = inputDescription(scanner);
		this.age = inputAge(scanner);
		this.status = inputStatus(scanner);
	}
	
	private boolean inputStatus(Scanner scanner) {
		System.out.println("Nhập vào trạng thái tác giả: ");
		do {
			String status = scanner.nextLine().toLowerCase();
			if (status.equals("true") || status.equals("false")) {
				return Boolean.parseBoolean(status);
			} else {
				System.err.println("Vùi lòng nhập lại true hoặc false");
			}
		} while (true);
		
	}
	
	private int inputAge(Scanner scanner) {
		System.out.println("Nhập vào tuổi tác giả: ");
		do {
			String age = scanner.nextLine();
			if (age.trim().isEmpty()) {
				System.err.println("không được để trống");
			} else if (Integer.parseInt(age) < 18) {
				System.err.println("Tuổi phải lơn hơn 18");
			} else {
				return Integer.parseInt(age);
			}
		} while (true);
	}
	
	private String inputDescription(Scanner scanner) {
		System.out.println("Nhập vào mô tả tác giả: ");
		return scanner.nextLine();
	}
	
	private String inputAuthorName(Scanner scanner) {
		System.out.println("Nhập vào tên tác giả: ");
		do {
			String authorName = scanner.nextLine();
			if (authorName.trim().isEmpty()) {
				System.err.println("Không được để trống");
			} else {
				return authorName;
			}
		} while (true);
	}
	
	private int idAutoIncrement() {
		int maxId = 0;
		for (Author a : AuthorFeatureImpl.authors) {
			if (maxId < a.getAuthorId()) {
				maxId = a.getAuthorId();
			}
		}
		return maxId + 1;
	}
	
	@Override
	public void displayData() {
		System.out.printf(
				  "[ ID: %10d | Name: %20s | Desc: %20s | Age: %10d | Status: %20s ]\n",
				  this.authorId,
				  this.authorName,
				  this.description,
				  this.age,
				  this.status ? "Còn xuất bản" : "Không còn xuất bản"
		);
	}
}
