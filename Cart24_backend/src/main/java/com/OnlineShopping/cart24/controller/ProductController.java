package com.OnlineShopping.cart24.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.OnlineShopping.cart24.dao.CategoryDao;
import com.OnlineShopping.cart24.dao.DeletedProductDao;
import com.OnlineShopping.cart24.dao.ProductDao;
import com.OnlineShopping.cart24.dto.ProductAddRequest;
import com.OnlineShopping.cart24.dto.ProductUpdateImageRequest;
import com.OnlineShopping.cart24.dto.ProductUpdateRequest;
import com.OnlineShopping.cart24.model.Category;
import com.OnlineShopping.cart24.model.DeletedProduct;
import com.OnlineShopping.cart24.model.Product;
import com.OnlineShopping.cart24.service.ProductService;
import com.OnlineShopping.cart24.utility.StorageService;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;


@RestController
@RequestMapping("api/product")
@CrossOrigin(origins = "http://localhost:3000")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private ProductDao productDao;
	
	@Autowired
	private DeletedProductDao dproductDao;
	
	@Autowired
	private CategoryDao categoryDao;
	
	@Autowired
	private StorageService storageService;
	

	
	@PostMapping("add")
	public ResponseEntity<?> addProduct(ProductAddRequest productDto) {
		System.out.println("recieved request for ADD PRODUCT");
		System.out.println(productDto);
		Product product=ProductAddRequest.toEntity(productDto);
		
		Optional<Category> optional = categoryDao.findById(productDto.getCategoryId());
		Category category = null;
		if(optional.isPresent()) {
			category = optional.get();
		}
		
		product.setCategory(category);
		productService.addProducts(product, productDto.getImage(),productDto.getImage1(),productDto.getImage2(),productDto.getImage3());
		
		System.out.println("response sent!!!add");
		return ResponseEntity.ok(product);
		
	}
	
	@PostMapping("updateImageo")
	public ResponseEntity<?> updateImage(ProductUpdateImageRequest updateImage) {
		String fileName = updateImage.getImageName();
		String fileName1 = updateImage.getImageName1();
		String fileName2 = updateImage.getImageName2();
		String fileName3 = updateImage.getImageName3();
		System.out.println("recieved request for Update Image PRODUCT");
		System.out.println(updateImage);
		Product product=ProductUpdateImageRequest.toEntity(updateImage);
		
		Optional<Category> optional = categoryDao.findById(updateImage.getCategoryId());
		Category category = null;
		if(optional.isPresent()) {
			category = optional.get();
		}
		product.setCategory(category);
		
		if(updateImage.getImageName().equals("null")) {
			String ext = updateImage.getImage().getOriginalFilename().substring(updateImage.getImage().getOriginalFilename().lastIndexOf("."));
			System.out.println(ext);
			 fileName = UUID.randomUUID().toString().replaceAll("-", "") + ext;
			 product.setImageName(fileName);
		}
		if(updateImage.getImageName1().equals("null")) {
			String ext = updateImage.getImage1().getOriginalFilename().substring(updateImage.getImage1().getOriginalFilename().lastIndexOf("."));
			System.out.println(ext);
			 fileName1 = UUID.randomUUID().toString().replaceAll("-", "") + ext;
			 product.setImageName1(fileName1);
		}
		if(updateImage.getImageName2().equals("null")) {
			String ext = updateImage.getImage2().getOriginalFilename().substring(updateImage.getImage2().getOriginalFilename().lastIndexOf("."));
			System.out.println(ext);
			 fileName2 = UUID.randomUUID().toString().replaceAll("-", "") + ext;
			 product.setImageName2(fileName2);
		}
		if(updateImage.getImageName3().equals("null")) {
			String ext = updateImage.getImage3().getOriginalFilename().substring(updateImage.getImage3().getOriginalFilename().lastIndexOf("."));
			System.out.println(ext);
			 fileName3 = UUID.randomUUID().toString().replaceAll("-", "") + ext;
			 product.setImageName3(fileName3);
		}
		
		if(updateImage.getImage()!=null) {
			System.out.println("recieved request for Update Image PRODUCT im");
			productService.updateProductImage(updateImage.getImage(),updateImage.getImageName(),fileName);	
		}
		if(updateImage.getImage1()!=null) {
			System.out.println("recieved request for Update Image PRODUCT im1");
			productService.updateProductImage(updateImage.getImage1(),updateImage.getImageName(),fileName1);	
		}
		if(updateImage.getImage2()!=null) {
			productService.updateProductImage(updateImage.getImage2(),updateImage.getImageName(),fileName2);	
		}
		if(updateImage.getImage3()!=null) {
			productService.updateProductImage(updateImage.getImage3(),updateImage.getImageName(),fileName3);	
		}
		
		productDao.save(product);
		
		System.out.println("response sent!!!add");
		return ResponseEntity.ok(product);
		
	}
	
	
	@GetMapping("all")
	public ResponseEntity<?> getAllProducts() {
		
		System.out.println("request came for getting all products");
		
		List<Product> products = new ArrayList<Product>();
		
		products = productDao.findAll();
		
		System.out.println("response sent!!!");
		
		return ResponseEntity.ok(products);
		
	}
	
	@GetMapping("alld")
	public ResponseEntity<?> getAllProductsD() {
		
		System.out.println("request came for getting all products");
		
		List<Product> products = new ArrayList<Product>();
		
		products = productDao.findAllD();
		
		System.out.println("response sent!!!");
		
		return ResponseEntity.ok(products);
		
	}
	
	@GetMapping("allDeletedProduct")
	public ResponseEntity<?> getAllDeletedProducts() {
		
		System.out.println("request came for getting all Deleted products");
		List<DeletedProduct> products = new ArrayList<DeletedProduct>();
		products = dproductDao.findAll();
		System.out.println("response sent!!!");
		
		return ResponseEntity.ok(products);
		
	}
	
	@GetMapping("id")
	public ResponseEntity<?> getProductById(@RequestParam("productId") int productId) {
		
		System.out.println("request came for getting Product by Product Id");
		
		Product product = new Product();
		
		Optional<Product> optional = productDao.findById(productId);
		
		if(optional.isPresent()) {
			product = optional.get();
		}
		System.out.println("response sent!!!");
		
		return ResponseEntity.ok(product);
		
	}
	
	@GetMapping("category")
	public ResponseEntity<?> getProductsByCategories(@RequestParam("categoryId") int categoryId) {
		
		System.out.println("request came for getting all products by category");
		
		List<Product> products = new ArrayList<Product>();
		
		products = productDao.findByCategoryId(categoryId);
		
		System.out.println("response sent!!!");
		
		return ResponseEntity.ok(products);
		
	}
	
	@GetMapping("categoryd")
	public ResponseEntity<?> getProductsByCategoriesD(@RequestParam("categoryId") int categoryId) {
		
		System.out.println("request came for getting all products by category");
		
		List<Product> products = new ArrayList<Product>();
		
		products = productDao.findByCategoryIdD(categoryId);
		
		System.out.println("response sent!!!");
		
		return ResponseEntity.ok(products);
		
	}
	
	@GetMapping("dcategory")
	public ResponseEntity<?> getDProductsByCategories(@RequestParam("categoryId") int categoryId) {
		
		System.out.println("request came for getting all Deleted products by category");
		
		List<DeletedProduct> products = new ArrayList<DeletedProduct>();
		
		products = dproductDao.findByCategoryId(categoryId);
		
		System.out.println("response sent!!!");
		
		return ResponseEntity.ok(products);
		
	}
	
	@GetMapping("searchs")
	public ResponseEntity<?> getProductsBySearch(@RequestParam("search") String search) {
		System.out.println("search="+ search);
		System.out.println("request came for getting all products by search");
		
		List<Product> products = new ArrayList<Product>();
		
		products = productService.findProduct(search);
		
		System.out.println("response sent!!!");
		
		return ResponseEntity.ok(products);
		
	}
	
	@GetMapping("searchsd")
	public ResponseEntity<?> getProductsBySearchD(@RequestParam("search") String search) {
		System.out.println("search="+ search);
		System.out.println("request came for getting all products by search");
		
		List<Product> products = new ArrayList<Product>();
		
		products = productService.findProductD(search);
		
		System.out.println("response sent!!!");
		
		return ResponseEntity.ok(products);
		
	}
	
	@GetMapping("dsearchs")
	public ResponseEntity<?> getDProductsBySearch(@RequestParam("search") String search) {
		System.out.println("search="+ search);
		System.out.println("request came for getting all Deleted products by search");
		
		List<DeletedProduct> products = new ArrayList<DeletedProduct>();
		
		products = productService.findDProduct(search);
		
		System.out.println("response sent!!!");
		
		return ResponseEntity.ok(products);
		
	}
	
	@GetMapping(value="/{productImageName}", produces = "image/*")
	public void fetchProductImage(@PathVariable("productImageName") String productImageName, HttpServletResponse resp) {
		System.out.println("request came for fetching product pic");
		System.out.println("Loading file: " + productImageName);
		Resource resource = storageService.loads(productImageName);
		if(resource != null) {
			try(InputStream in = resource.getInputStream()) {
				ServletOutputStream out = resp.getOutputStream();
				FileCopyUtils.copy(in, out);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("response sent!");
	}
	
	@GetMapping(value="/D/{productImageName}", produces = "image/*")
	public void fetchDProductImage(@PathVariable("productImageName") String productImageName, HttpServletResponse resp) {
		System.out.println("request came for fetching Deleted product pic");
		System.out.println("Loading file: " + productImageName);
		Resource resource = storageService.Dloads(productImageName);
		if(resource != null) {
			try(InputStream in = resource.getInputStream()) {
				ServletOutputStream out = resp.getOutputStream();
				FileCopyUtils.copy(in, out);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("response sent!");
	}
	
	@GetMapping("remove")
	public ResponseEntity<?> removeProductById(@RequestParam("Id") int Id) {
		
		System.out.println("request came for remove Product by Product Id");
		
		Product product = new Product();
		DeletedProduct dproduct = new DeletedProduct();
		
		Optional<Product> optional = productDao.findById(Id);
		
		if(optional.isPresent()) {
			product = optional.get();
		}
		
		dproduct=Product.toEntity(product);
		this.dproductDao.save(dproduct);
		System.out.println("Product added to Deleted product");
		
		productService.deleteProductImage(product.getImageName(),product.getImageName(),product.getImageName1(),product.getImageName2(),product.getImageName3());
		System.out.println("Image deleted and copied");
		this.productDao.delete(product);
		System.out.println("Product Deleted");
		System.out.println("response sent!!!");
		return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
		
	}
	
	@GetMapping("restoreremove")
	public ResponseEntity<?> removeDProductById(@RequestParam("Id") int Id) {
		
		System.out.println("request came for remove DEleted Product by Product Id");
		
		
		DeletedProduct dproduct = new DeletedProduct();
		
		Optional<DeletedProduct> optional = dproductDao.findById(Id);
		
		if(optional.isPresent()) {
			dproduct = optional.get();
		}
		
		System.out.println("response sent!!!");
		productService.deleteDProductImage(dproduct.getImageName());
		this.dproductDao.delete(dproduct);
		return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
		
	}
	
	@PostMapping("update")
	public ResponseEntity<?> updateProduct(ProductUpdateRequest UpdateRequest) {
		System.out.println("recieved request for update PRODUCT");
		System.out.println("recieved request for ADD PRODUCT");
		System.out.println(UpdateRequest);
		Product product=ProductUpdateRequest.toEntity(UpdateRequest);
		
		Optional<Category> optional = categoryDao.findById(UpdateRequest.getCategoryId());
		Category category = null;
		if(optional.isPresent()) {
			category = optional.get();
		}
		product.setCategory(category);
		productService.updateProduct(product);
		System.out.println("response sent!!!add");
		return ResponseEntity.ok(product);
		
	}
	
	@PostMapping("restore")
	public ResponseEntity<?> restoreProduct(ProductUpdateRequest UpdateRequest) {
		System.out.println("recieved request for restore PRODUCT");
		System.out.println("recieved request for ADD PRODUCT");
		System.out.println(UpdateRequest);
		Product product=ProductUpdateRequest.toEntity(UpdateRequest);
		DeletedProduct dproduct = ProductUpdateRequest.toEntityD(UpdateRequest);
		Optional<Category> optional = categoryDao.findById(UpdateRequest.getCategoryId());
		Category category = null;
		if(optional.isPresent()) {
			category = optional.get();
		}
		product.setCategory(category);
		productService.updateProduct(product);
		productService.restoreProductImage(UpdateRequest.getImageName(),UpdateRequest.getImageName(), UpdateRequest.getImageName1(),UpdateRequest.getImageName2(), UpdateRequest.getImageName3());
		dproductDao.delete(dproduct);
		System.out.println("response sent!!!add");
		return ResponseEntity.ok(product);
		
	}
	
	@PostMapping("updateImage")
	public ResponseEntity<?> updateProductImage(ProductAddRequest update) {
		System.out.println("recieved request for Update  PRODUCT Image");
		System.out.println(update);
		
		Optional<Product> optional1 = productDao.findById(update.getId());
		Product productOld = null;
		if(optional1.isPresent()) {
			productOld = optional1.get();
		}
		String oldImangeName=productOld.getImageName();
		productService.deleteProductImage(oldImangeName);
		System.out.println("Product Image Delete Successfully");
		Product product=ProductAddRequest.toEntity(update);
		
		Optional<Category> optional = categoryDao.findById(update.getCategoryId());
		Category category = null;
		if(optional.isPresent()) {
			category = optional.get();
		}
		
		product.setCategory(category);
		productService.addProducts(product, update.getImage(),update.getImage1(),update.getImage2(),update.getImage3());
		
		System.out.println("response sent!!!add");
		return ResponseEntity.ok(product);
		
	}

}
