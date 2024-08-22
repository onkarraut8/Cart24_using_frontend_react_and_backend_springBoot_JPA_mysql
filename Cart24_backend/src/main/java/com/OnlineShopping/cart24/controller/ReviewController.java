package com.OnlineShopping.cart24.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.OnlineShopping.cart24.dao.ProductDao;
import com.OnlineShopping.cart24.dao.ReviewDao;
import com.OnlineShopping.cart24.dao.UserDao;
import com.OnlineShopping.cart24.dto.AddReviewRequest;
import com.OnlineShopping.cart24.model.Product;
import com.OnlineShopping.cart24.model.Review;
import com.OnlineShopping.cart24.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;


@RestController
@RequestMapping("api/review")
@CrossOrigin(origins = "http://localhost:3000")
public class ReviewController {

	@Autowired
	private ProductDao productDao;
	
	@Autowired
	private ReviewDao reviewDao;
	
	@Autowired
	private UserDao userDao;
	
	@PostMapping("add")
	public ResponseEntity<?> add(@RequestBody AddReviewRequest request) {
		
		System.out.println("request came for add review:"+request);
		Product p= new Product();
		Optional<Product> optionalProduct = this.productDao.findById(request.getProductId());
		if(optionalProduct.isPresent()) {
			p = optionalProduct.get();
		}
		
		User u= new User();
		Optional<User> optionalUser = this.userDao.findById(request.getUserId());
		if(optionalUser.isPresent()) {
			u = optionalUser.get();
		}
		Review r =new Review();
		r.setId(request.getId());
		r.setProduct(p);
		r.setUser(u);
		r.setReview(request.getReview());
		r.setStar(request.getStar());
		Review rr = reviewDao.save(r);
		if(rr != null) {
			
			System.out.println("response sent");
			return new ResponseEntity<>(rr,HttpStatus.OK);
		}
		else {
			System.out.println("response sent");
			return new ResponseEntity<>("Failed to add review!",HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
	}
	
	@PostMapping("get")
	public ResponseEntity<?> getReview(@RequestBody AddReviewRequest request) {
		
		System.out.println("request came for get review:"+request);
		Review r =new Review();
		r = reviewDao.findByProductIdAndUserId(request.getProductId(),request.getUserId());
		
		if( r!= null) {
			
			System.out.println("response sent:"+r);
			return new ResponseEntity<>(r,HttpStatus.OK);
		}
		else {
			System.out.println("f response sent:"+r);
			return new ResponseEntity<>("Failed to load review!",HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
	}
	
	@PostMapping("productreview")
	public ResponseEntity<List<Review>> getproductsReview(@RequestParam("productId") int id) throws JsonProcessingException {
		
        System.out.println("request came for getting all review by product id:"+id);
        List<Review> review = new ArrayList<Review>();
		 review = this.reviewDao.findAllByProductId(id);
		
		return ResponseEntity.ok(review);
	}
	
}
