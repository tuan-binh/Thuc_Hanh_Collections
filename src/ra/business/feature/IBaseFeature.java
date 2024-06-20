package ra.business.feature;

import java.util.List;

public interface IBaseFeature<T, E> {
	// T - sẽ là đối tượng cần làm việc
	// E - sẽ là kiểu dữ liệu id của đối tượng đang làm việc
	
	List<T> findAll();
	
	void addOrUpdate(T t);
	
	void deleteById(E id);
	
	int findIndexById(E id);
}
