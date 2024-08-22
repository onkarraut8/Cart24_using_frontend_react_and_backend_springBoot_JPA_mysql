package com.OnlineShopping.cart24.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.OnlineShopping.cart24.model.DeletedProduct;
import com.OnlineShopping.cart24.model.Product;





public interface ProductService {
	
	void addProduct(Product product, MultipartFile productImmage);
	
	public void updateProduct(Product product);
	
	 public List<Product> findProduct(String keyword);
	 
	 public List<Product> findProductD(String keyword);
	 
	 public List<DeletedProduct> findDProduct(String keyword);
	 
	 void deleteProductImage(String productImageName);
	 
	void addProducts(Product product, MultipartFile productImmage, MultipartFile productImmage1,
			MultipartFile productImmage2, MultipartFile productImmage3);
	
	void updateProductImage(MultipartFile productImmage, String Folder, String OName);
	
	void deleteProductImage(String Folder, String productImageName, String productImageName1, String productImageName2,
			String productImageName3);


	void restoreProductImage(String Folder, String productImageName, String productImageName1, String productImageName2,
			String productImageName3);

	void deleteDProductImage(String productImageName);
}
