package ra.business.feature.impl;

import ra.business.entity.Author;
import ra.business.entity.Book;
import ra.business.feature.IAuthorFeature;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AuthorFeatureImpl implements IAuthorFeature {
	public static List<Author> authors = new ArrayList<>();
	
	static {
		Author author1 = new Author(1, "hải trần", "trần hải", 18, true);
		Author author2 = new Author(2, "đức trần", "trần đức", 19, true);
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Book book;
		try {
			book = new Book("B001", "Babybird", 1200.0, sdf.parse("20/06/2024"), author1, (byte) 0);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
		authors.add(author1);
		authors.add(author2);
		BookFeatureImpl.books.add(book);
	}
	
	@Override
	public List<Author> findAll() {
		return authors;
	}
	
	@Override
	public void addOrUpdate(Author author) {
		int indexCheck = findIndexById(author.getAuthorId());
		if (indexCheck < 0) {
			// chức năng thêm mới
			authors.add(author);
		} else {
			// chức năng cập nhật
			authors.set(indexCheck, author);
		}
	}
	
	@Override
	public void deleteById(Integer id) {
		int indexDelete = findIndexById(id);
		if (indexDelete >= 0) {
			boolean isExist = false;
			for (Book b : BookFeatureImpl.books) {
				if (b.getAuthor().getAuthorId() == id) {
					isExist = true;
					break;
				}
			}
			if (isExist) {
				Author author = authors.get(indexDelete);
				author.setStatus(!author.isStatus());
				addOrUpdate(author);
			} else {
				authors = authors.stream().filter(item -> item.getAuthorId() != id).collect(Collectors.toList());
			}
		} else {
			System.err.println("Không tồn tại author muốn xáo");
		}
	}
	
	@Override
	public int findIndexById(Integer id) {
		for (int i = 0; i < authors.size(); i++) {
			if (authors.get(i).getAuthorId() == id) {
				return i;
			}
		}
		return -1;
	}
}
