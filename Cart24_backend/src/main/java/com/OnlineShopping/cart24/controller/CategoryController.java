package com.OnlineShopping.cart24.controller;


import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.OnlineShopping.cart24.dao.CategoryDao;
import com.OnlineShopping.cart24.dao.DeletedCategoryDao;
import com.OnlineShopping.cart24.model.Category;
import com.OnlineShopping.cart24.model.DeletedCategory;
import com.fasterxml.jackson.core.JsonProcessingException;



@RestController
@RequestMapping("api/category")
@CrossOrigin(origins = "http://localhost:3000")
public class CategoryController {

	@Autowired
	private CategoryDao categoryDao;
	
	@Autowired
	private DeletedCategoryDao dcategoryDao;

	@GetMapping("all")
	public ResponseEntity<List<Category>> getAllCategories() {
		
        System.out.println("request came for getting all categories");
		
		List<Category> categories = this.categoryDao.findAll();
		
		ResponseEntity<List<Category>> response = new ResponseEntity<>(categories, HttpStatus.OK);
		
		System.out.println("response sent");
		
		return response;
		
	}
	
	@GetMapping("allDeletedCategory")
	public ResponseEntity<List<DeletedCategory>> getAllDCategories() {
		
        System.out.println("request came for getting all Deleted categories");
		
		List<DeletedCategory> categories = this.dcategoryDao.findAll();
		
		ResponseEntity<List<DeletedCategory>> response = new ResponseEntity<>(categories, HttpStatus.OK);
		
		System.out.println("response sent");
		
		return response;
		
	}
	
	@PostMapping("add")
	public ResponseEntity<?> add(@RequestBody Category category) {
		
		System.out.println("request came for add category");
		
		Category c = categoryDao.save(category);
		
		if(c != null) {
			System.out.println("response sent");
			return new ResponseEntity<>( c ,HttpStatus.OK);
		}
		
		else {
			System.out.println("response sent");
			return new ResponseEntity<>("Failed to add category!",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@PostMapping("restore")
	public ResponseEntity<?> restore(@RequestBody Category category) {
		
		System.out.println("request came for restore category");
		
		Category c = categoryDao.save(category);
		DeletedCategory dc = new DeletedCategory();
		dc.setId(category.getId());
		dc.setTitle(category.getTitle());
		dc.setDescription(category.getDescription());
		dcategoryDao.delete(dc);
		if(c != null) {
			System.out.println("response sent");
			return new ResponseEntity<>( c ,HttpStatus.OK);
		}
		
		else {
			System.out.println("response sent");
			return new ResponseEntity<>("Failed to add restore category!",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@PostMapping("remove")
	public ResponseEntity<?> removeCategory(@RequestParam("id") int id) throws JsonProcessingException {
		
		System.out.println("request came for DELETE Category WHOSE ID IS : "+id);
		
		Optional<Category> optionalCategory = this.categoryDao.findById(id);
		Category cat = new Category();
		DeletedCategory dcat = new DeletedCategory();
		
		if(optionalCategory.isPresent()) {
			cat = optionalCategory.get();
		}
		dcat=cat.toEntity(cat);
		this.dcategoryDao.save(dcat);
		this.categoryDao.delete(cat);
		
		return new ResponseEntity<>("SUCCESS", HttpStatus.OK);	
	}
	
	@PostMapping("deletedremove")
	public ResponseEntity<?> removeDCategory(@RequestParam("id") int id) throws JsonProcessingException {
		
		System.out.println("request came for DELETE DCategory WHOSE ID IS : "+id);
		
		Optional<DeletedCategory> optionalCategory = this.dcategoryDao.findById(id);
		DeletedCategory dcat = new DeletedCategory();
		
		if(optionalCategory.isPresent()) {
			dcat = optionalCategory.get();
		}
	
		this.dcategoryDao.delete(dcat);
		
		
		return new ResponseEntity<>("SUCCESS", HttpStatus.OK);	
	}
	
	@GetMapping("id")
	public ResponseEntity<?> add(@RequestParam("ids") int ids) throws JsonProcessingException {
		
		System.out.println("request came for category by id "+ids);
		
		Optional<Category> optionalCategory = this.categoryDao.findById(ids);
        Category cat = new Category();
		
		if(optionalCategory.isPresent()) {
			cat = optionalCategory.get();
		}
		
		return new ResponseEntity<>(cat, HttpStatus.OK);
		
	}
	
	@PostMapping("update")
	public ResponseEntity<?> update(@RequestBody Category category) {
		
		System.out.println("request came for update category");
		
		Category c = categoryDao.save(category);
		
		if(c != null) {
			System.out.println("response sent");
			return new ResponseEntity<>( c ,HttpStatus.OK);
		}
		
		else {
			System.out.println("response sent");
			return new ResponseEntity<>("Failed to update category!",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@GetMapping("searchs")
	public ResponseEntity<?> getCategoryBySearch(@RequestParam("search") String search) {
		System.out.println("search="+ search);
		System.out.println("request came for getting all category by search");
		
		if (search != null) {
			List<Category> categories = this.categoryDao.search(search);
			ResponseEntity<List<Category>> response = new ResponseEntity<>(categories, HttpStatus.OK);
			System.out.println("response sent!!!");
			return response;
	        }
		else {
			List<Category> categories=this.categoryDao.findAll();
			ResponseEntity<List<Category>> response = new ResponseEntity<>(categories, HttpStatus.OK);
			System.out.println("response sent!!!");
			return response;
		}
		
	}
	
	@GetMapping("Dsearchs")
	public ResponseEntity<?> getDCategoryBySearch(@RequestParam("search") String search) {
		System.out.println("search="+ search);
		System.out.println("request came for getting all Deleted category by search");
		
		if (search != null) {
			List<DeletedCategory> categories = this.dcategoryDao.search(search);
			ResponseEntity<List<DeletedCategory>> response = new ResponseEntity<>(categories, HttpStatus.OK);
			System.out.println("response sent!!!");
			return response;
	        }
		else {
			List<DeletedCategory> categories=this.dcategoryDao.findAll();
			ResponseEntity<List<DeletedCategory>> response = new ResponseEntity<>(categories, HttpStatus.OK);
			System.out.println("response sent!!!");
			return response;
		}
		
	}
	
	
}

