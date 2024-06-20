package ra.business.feature.impl;

import ra.business.entity.Book;
import ra.business.feature.IBookFeature;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BookFeatureImpl implements IBookFeature {
	public static List<Book> books = new ArrayList<>();
	
	@Override
	public List<Book> findAll() {
		return books;
	}
	
	@Override
	public void addOrUpdate(Book book) {
		int indexCheck = findIndexById(book.getBookId());
		if (indexCheck < 0) {
			// chức năng thêm mới
			books.add(book);
		} else {
			// chức năng update
			books.set(indexCheck, book);
		}
	}
	
	@Override
	public void deleteById(String id) {
		int indexDelete = findIndexById(id);
		if (indexDelete >= 0) {
			books.remove(indexDelete);
		} else {
			System.err.println("Không tồn tại book muốn xóa");
		}
	}
	
	@Override
	public int findIndexById(String id) {
		for (int i = 0; i < books.size(); i++) {
			if (books.get(i).equals(id)) {
				return i;
			}
		}
		return -1;
	}
}
