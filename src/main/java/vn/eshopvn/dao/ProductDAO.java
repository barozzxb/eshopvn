package vn.eshopvn.dao;

import java.sql.Timestamp;
import java.util.List;

import vn.eshopvn.entity.Product;
import vn.eshopvn.model.TopSellingProduct;

public interface ProductDAO {

	List<Product> top3new();

	int countProducts();

	void delete(String pid) throws Exception;

	List<Product> findByName(String pname);

	void update(Product product);

	void insert(Product product);

	Product findById(String productId);

	List<Product> findAll();

	List<Product> searchAndFilter(String genreIds, Integer maxPrice);

	List<Product> filterByPrice(int maxPrice);

	List<Product> filterByGenre(List<String> genreIds);


	List<TopSellingProduct> topSellingProducts();

	List<Product> findAll(int page, int pagesize);

	List<Product> findByGenre(String gid);

	List<Product> searchProducts(String query, Integer rating, Timestamp createdAtFrom, Timestamp createdAtTo,
			Integer minPrice, Integer maxPrice, Integer minQuantity, Integer maxQuantity, String sortBy,
			boolean ascending);

}
