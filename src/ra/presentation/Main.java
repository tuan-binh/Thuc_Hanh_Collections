package ra.presentation;


import ra.business.entity.Author;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
	public static void main(String[] args) {
//		//khai báo đối tượng bằng Scanner
//		Scanner scanner = new Scanner(System.in);
//		//thông báo nhập vào
//		System.out.println("Nhập n số nguyên tố");
//		//Lấy dữ liệu từ bàn phím nhập và lưu vào biến
//		int n = scanner.nextInt();
//		int count = 0;
//		int i = 2;
//		while (count < n) {
//			if (isPrimeNumber(i)) {
//				System.out.print(i + " ");
//				count++;
//			}
//			i++;
//		}
		
		List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
//		System.out.println(list.stream().map(item -> item * 2).collect(Collectors.toList()));
//
		System.out.println(list.stream().filter(item -> item % 2 == 0).count());
		List<Author> authors = new ArrayList<>();
//		authors.add(new Author(1, "Huấn", "mô tả về huấn", 18, true));
//		authors.add(new Author(2, "Dương", "mô tả về dương", 20, true));
//		authors.add(new Author(3, "Hải", "mô tả về hải", 21, true));
		
		Optional<Author> optionalAuthor = authors.stream().max(Comparator.comparingInt(Author::getAge));
//		optionalAuthor.get().displayData();
		//
//		Optional<Author> optionalAuthor = authors.stream().filter(item -> item.getAuthorId() == 10).findFirst();
		if (optionalAuthor.isPresent()) {
			Author author = optionalAuthor.get();
			author.displayData();
		} else {
			System.err.println("Không tồn tại");
		}
		
	}
	
	public static boolean isPrimeNumber(int n) {
		if (n < 2) {
			return false;
		}
		
		for (int i = 2; i <= Math.sqrt(n); i++) {
			if (n % i == 0) {
				return false;
			}
		}
		
		return true;
	}
}
