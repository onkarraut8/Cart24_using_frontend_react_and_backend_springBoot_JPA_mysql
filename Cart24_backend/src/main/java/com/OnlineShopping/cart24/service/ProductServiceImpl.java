package com.OnlineShopping.cart24.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.OnlineShopping.cart24.dao.DeletedProductDao;
import com.OnlineShopping.cart24.dao.ProductDao;
import com.OnlineShopping.cart24.model.DeletedProduct;
import com.OnlineShopping.cart24.model.Product;
import com.OnlineShopping.cart24.utility.StorageService;







@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired 
	private ProductDao productDao;
	
	@Autowired 
	private DeletedProductDao dproductDao;
	
	@Autowired
	private StorageService storageService;

	@Override
	public void addProduct(Product product, MultipartFile productImmage) {
		
		String productImageName = storageService.store(productImmage);
		
		product.setImageName(productImageName);
		
		this.productDao.save(product);
	}
	
	@Override
	public void updateProductImage(MultipartFile productImmage,String Folder,String OName) {
		storageService.copy(Folder,OName);
		storageService.storeUpdate(productImmage,Folder,OName);
		
	}
	
	@Override
	public void addProducts(Product product, MultipartFile productImmage,MultipartFile productImmage1,MultipartFile productImmage2,MultipartFile productImmage3) {
		
		String[] productImageName = storageService.stores(productImmage,productImmage1,productImmage2,productImmage3);
		
		product.setImageName(productImageName[0]);
		product.setImageName1(productImageName[1]);
		product.setImageName2(productImageName[2]);
		product.setImageName3(productImageName[3]);
		
		this.productDao.save(product);
	}
	
	@Override
	public void updateProduct(Product product) {
		
		this.productDao.save(product);
	}
	
	
	
	@Override
	 public List<Product> findProduct(String search) {
	        if (search != null) {
	            return productDao.search(search);
	        }
	        return productDao.findAll();
	    }
	@Override
	 public List<Product> findProductD(String search) {
	        if (search != null) {
	            return productDao.searchD(search);
	        }
	        return productDao.findAllD();
	    }
	
	@Override
	 public List<DeletedProduct> findDProduct(String search) {
	        if (search != null) {
	            return dproductDao.search(search);
	        }
	        return dproductDao.findAll();
	    }
	
	@Override
	public void deleteProductImage(String productImageName) {
		storageService.copyFolder(productImageName);
		storageService.deleteFolder(productImageName);
		
	}
	
	@Override
	public void deleteDProductImage(String productImageName) {
		storageService.deleteFolderRestore(productImageName);
		
	}
	
	@Override
	public void deleteProductImage(String Folder,String productImageName,String productImageName1,String productImageName2,String productImageName3) {
		storageService.copyFolder(Folder, productImageName);
		storageService.copyFolder(Folder, productImageName1);
		storageService.copyFolder(Folder, productImageName2);
		storageService.copyFolder(Folder, productImageName3);
		storageService.deleteFolder(productImageName);
		
	}
	
	@Override
	public void restoreProductImage(String Folder,String productImageName,String productImageName1,String productImageName2,String productImageName3) {
		storageService.copyFolderRestore(Folder, productImageName);
		storageService.copyFolderRestore(Folder, productImageName1);
		storageService.copyFolderRestore(Folder, productImageName2);
		storageService.copyFolderRestore(Folder, productImageName3);
		 storageService.deleteFolderRestore(productImageName);
		
	}

}
