package vn.eshopvn.dao.impl;

import java.sql.Timestamp;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import vn.eshopvn.config.JPAConfig;
import vn.eshopvn.dao.ProductDAO;
import vn.eshopvn.entity.Genre;
import vn.eshopvn.entity.Product;
import vn.eshopvn.model.TopSellingProduct;

public class ProductDAOImpl implements ProductDAO{

	@Override
	public List<Product> findAll() {
		EntityManager enma = JPAConfig.getEntityManager();
		TypedQuery<Product> pros = enma.createNamedQuery("product.findAll", Product.class);
		return pros.getResultList();
	}

	@Override
	public List<Product> findAll(int page, int pagesize) {
		EntityManager enma = JPAConfig.getEntityManager();
		TypedQuery<Product> query = enma.createNamedQuery("product.findAll", Product.class);
		query.setFirstResult(page * pagesize);
		query.setMaxResults(pagesize);
		return query.getResultList();
	}
	
	@Override
	public Product findById(String productId) {
		EntityManager enma = JPAConfig.getEntityManager();
		Product pro = enma.find(Product.class, productId);
		return pro;
	}

	@Override
	public void insert(Product product) {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			enma.persist(product);
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		} finally {
			enma.close();
		}
		
	}

	@Override
	public void update(Product product) {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			enma.merge(product); // edit
			trans.commit();
	
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		} finally {
			enma.close();
		}
	}

	@Override
	public List<Product> findByName(String pname) {
		EntityManager enma = JPAConfig.getEntityManager();
		String jpql = "SELECT p FROM Product p WHERE p.productName like : pname";
		TypedQuery<Product> query= enma.createQuery(jpql, Product.class);
		query.setParameter("pname", "%" + pname +"%");
		return query.getResultList();
	}

	@Override
	public void delete(String pid) throws Exception {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			Product product = enma.find(Product.class, pid);
			if (product !=null) {
				enma.remove(product);
			}
			else {
				throw new Exception("Khong tim thay");
			}
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		} finally {
			enma.close();
		}		
	}

	@Override
	public int countProducts() {
		 EntityManager em = JPAConfig.getEntityManager();
		 String jpql = "SELECT COUNT(p) FROM Product p";
		 TypedQuery<Long> query = em.createQuery(jpql, Long.class);
		 return query.getSingleResult().intValue();
	}

	@Override
	public List<Product> top3new() {
		EntityManager em = JPAConfig.getEntityManager();
		String jpql = "SELECT p FROM Product p ORDER BY p.createdat DESC";
		TypedQuery<Product> query = em.createQuery(jpql, Product.class);
		query.setMaxResults(4);
		return query.getResultList();
	}
	
	@Override
	public List<Product> searchProducts(String query, Integer rating, Timestamp createdAtFrom, Timestamp createdAtTo, Integer minPrice, Integer maxPrice, Integer minQuantity, Integer maxQuantity, String sortBy, boolean ascending) {
	    EntityManager em = JPAConfig.getEntityManager();
	    
	    // Bắt đầu xây dựng câu lệnh JPQL
	    StringBuilder jpql = new StringBuilder("SELECT p FROM Product p WHERE 1=1");
	    

	    if (query != null && !query.isEmpty()) {
	        jpql.append(" AND p.pname LIKE :query");
	    }
	    if (rating != null) {
	        jpql.append(" AND p.prating >= :rating");
	    }
	    if (createdAtFrom != null) {
	        jpql.append(" AND p.createdat >= :createdAtFrom");
	    }
	    if (createdAtTo != null) {
	        jpql.append(" AND p.createdat <= :createdAtTo");
	    }
	    if (minPrice != null) {
	        jpql.append(" AND p.pprice >= :minPrice");
	    }
	    if (maxPrice != null) {
	        jpql.append(" AND p.pprice <= :maxPrice");
	    }
	    if (minQuantity != null) {
	        jpql.append(" AND p.pquantity >= :minQuantity");
	    }
	    if (maxQuantity != null) {
	        jpql.append(" AND p.pquantity <= :maxQuantity");
	    }

	    // Thêm phần sắp xếp
	    if (sortBy != null) {
	        jpql.append(" ORDER BY p." + sortBy);
	        jpql.append(ascending ? " ASC" : " DESC");
	    }

	    // Tạo query và thiết lập các tham số
	    TypedQuery<Product> queryObj = em.createQuery(jpql.toString(), Product.class);
	    
	    if (query != null && !query.isEmpty()) {
	        queryObj.setParameter("query", "%" + query + "%");
	    }
	    if (rating != null) {
	        queryObj.setParameter("rating", rating);
	    }
	    if (createdAtFrom != null) {
	        queryObj.setParameter("createdAtFrom", createdAtFrom);
	    }
	    if (createdAtTo != null) {
	        queryObj.setParameter("createdAtTo", createdAtTo);
	    }
	    if (minPrice != null) {
	        queryObj.setParameter("minPrice", minPrice);
	    }
	    if (maxPrice != null) {
	        queryObj.setParameter("maxPrice", maxPrice);
	    }
	    if (minQuantity != null) {
	        queryObj.setParameter("minQuantity", minQuantity);
	    }
	    if (maxQuantity != null) {
	        queryObj.setParameter("maxQuantity", maxQuantity);
	    }

	    return queryObj.getResultList();
	}

	@Override
	public List<Product> filterByGenre(List<String> genreIds) {
		EntityManager em = JPAConfig.getEntityManager();
	    String query = "SELECT p FROM products p WHERE pgenre.gid IN :genreIds";
	    return em.createQuery(query, Product.class)
	                        .setParameter("genreIds", genreIds)
	                        .getResultList();
	}

	@Override
	public List<Product> filterByPrice(int maxPrice) {
		EntityManager em = JPAConfig.getEntityManager();
	    String query = "SELECT p FROM products p WHERE p.pprice <= :maxPrice";
	    return em.createQuery(query, Product.class)
	                        .setParameter("maxPrice", maxPrice)
	                        .getResultList();
	}

	@Override
	public List<Product> searchAndFilter(String genreIds, Integer maxPrice) {
		EntityManager em = JPAConfig.getEntityManager();
	    StringBuilder queryBuilder = new StringBuilder("SELECT p FROM products p WHERE 1=1");
	    
	    if (genreIds != "" && !genreIds.isBlank()) {
	        queryBuilder.append(" AND gid IN :genreIds");
	    }
	    
	    if (maxPrice != null) {
	        queryBuilder.append(" AND pprice <= :maxPrice");
	    }
	    
	    Query query = em.createQuery(queryBuilder.toString(), Product.class);
	    
	    if (genreIds != "" && !genreIds.isBlank()) {
	        query.setParameter("genreIds", genreIds);
	    }
	    
	    if (maxPrice != null) {
	        query.setParameter("maxPrice", maxPrice);
	    }
	    
	    return query.getResultList();
	}

	@Override
	public List<TopSellingProduct> topSellingProducts() {
		EntityManager enma = JPAConfig.getEntityManager();
        String jpql = "SELECT new vn.eshopvn.model.TopSellingProduct(p, SUM(od.dquantity)) " +
		        		"FROM Product p JOIN p.orderDetails od " +
		        		"GROUP BY p " + 
		        		"ORDER BY SUM(od.dquantity) DESC";
        return enma.createQuery(jpql, TopSellingProduct.class).getResultList();
    }
	
	@Override
	public List<Product> findByGenre(String gid) {
		 EntityManager enma = JPAConfig.getEntityManager();
		    try {
		        // JPQL truy vấn danh sách sản phẩm thuộc thể loại cụ thể
		        String jpql = "SELECT p FROM Product p WHERE p.genre.gid = :gid";
		        TypedQuery<Product> query = enma.createQuery(jpql, Product.class);
		        query.setParameter("gid", gid); // Thiết lập giá trị gid cho tham số trong JPQL

		        return query.getResultList(); // Trả về danh sách sản phẩm
		    } catch (Exception e) {
		        e.printStackTrace();
		        return null; // Trả về null nếu xảy ra lỗi
		    } finally {
		        if (enma != null && enma.isOpen()) {
		            enma.close(); // Đảm bảo đóng EntityManager
		        }
		    }
	}
}
