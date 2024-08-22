package com.OnlineShopping.cart24.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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

import com.OnlineShopping.cart24.dao.CartDao;
import com.OnlineShopping.cart24.dao.OrderDao;
import com.OnlineShopping.cart24.dao.ProductDao;
import com.OnlineShopping.cart24.dao.UserDao;
import com.OnlineShopping.cart24.dto.MyOrderResponse;
import com.OnlineShopping.cart24.dto.UpdateDeliveryStatusRequest;
import com.OnlineShopping.cart24.model.Cart;
import com.OnlineShopping.cart24.model.Orders;
import com.OnlineShopping.cart24.model.Product;
import com.OnlineShopping.cart24.model.User;
import com.OnlineShopping.cart24.utility.Constants.DeliveryStatus;
import com.OnlineShopping.cart24.utility.Constants.DeliveryTime;
import com.OnlineShopping.cart24.utility.Constants.IsDeliveryAssigned;
import com.OnlineShopping.cart24.utility.Helper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;



@RestController
@RequestMapping("api/user/")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class OrderController {

	@Autowired
	private OrderDao orderDao;

	@Autowired
	private CartDao cartDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private ProductDao productDao;

	private ObjectMapper objectMapper = new ObjectMapper();

	@PostMapping("order")
	public ResponseEntity<?> customerOrder(@RequestParam("userId") int userId) throws JsonProcessingException {

		System.out.println("request came for ORDER FOR CUSTOMER ID : " + userId);

		String orderId = Helper.getAlphaNumericOrderId();

		List<Cart> userCarts = cartDao.findByUser_id(userId);
		
		LocalDateTime currentDateTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
		String formatDateTime = currentDateTime.format(formatter);
		
		
		/*-----------TO update Remaining Stock--------------------------------*/
		
		for (Cart cart : userCarts) {
			
			Product product = new Product();
			
			Optional<Product> optional = productDao.findById(cart.getProduct().getId());
			
			if(optional.isPresent()) {
				product = optional.get();
			}
			System.out.println("####%%%%%"+product);
			
			Product cartData = new Product();
			
			System.out.println("OQUA:"+cart.getQuantity());
			System.out.println(" Stock:"+product.getQuantity());
			int qua=product.getQuantity()-cart.getQuantity();
			System.out.println("Remaining Stock:"+qua);
			cartData.setId(product.getId());
			cartData.setDisplay(product.getDisplay());
			cartData.setTitle(product.getTitle());
			cartData.setDescription(product.getDescription());
			cartData.setQuantity(qua);
			cartData.setPrice(product.getPrice());
			cartData.setImageName(product.getImageName());
			cartData.setImageName1(product.getImageName1());
			cartData.setImageName2(product.getImageName2());
			cartData.setImageName3(product.getImageName3());
			cartData.setCategory(product.getCategory());
			productDao.save(cartData);
			/*
			 * Product cartData = new Product(); cartData.setQuantity(cart.getQuantity());
			 * cartData.setId(cart.getProduct().getId());
			 */
				
		}
		 
		  /*-------------------------------------------*/

		for (Cart cart : userCarts) {

			Orders order = new Orders();
			order.setOrderId(orderId);
			order.setUser(cart.getUser());
			order.setProduct(cart.getProduct());
			order.setQuantity(cart.getQuantity());
			order.setOrderDate(formatDateTime);
			order.setDeliveryDate(DeliveryStatus.PENDING.value());
			order.setDeliveryStatus(DeliveryStatus.PENDING.value());
			order.setDeliveryTime(DeliveryTime.DEFAULT.value());
			order.setDeliveryAssigned(IsDeliveryAssigned.NO.value());

			orderDao.save(order);
			cartDao.delete(cart);
		}

		System.out.println("response sent!!!");

		return new ResponseEntity<>("ORDER SUCCESS", HttpStatus.OK);

	}

	@GetMapping("myorder")
	public ResponseEntity<?> getMyOrder(@RequestParam("userId") int userId) throws JsonProcessingException {

		System.out.println("request came for MY ORDER for USER ID : " + userId);

		List<Orders> userOrder = orderDao.findByUser_id(userId);

		

		List<MyOrderResponse> orderDatas = new ArrayList<>();

		for (Orders order : userOrder) {
			MyOrderResponse orderData = new MyOrderResponse();
			orderData.setOrderId(order.getOrderId());
			orderData.setProductDescription(order.getProduct().getDescription());
			orderData.setProductName(order.getProduct().getTitle());
			orderData.setProductImage(order.getProduct().getImageName());
			orderData.setQuantity(order.getQuantity());
			orderData.setOrderDate(order.getOrderDate());
			orderData.setProductId(order.getProduct().getId());
			orderData.setDeliveryDate(order.getDeliveryDate() + " " + order.getDeliveryTime());
			orderData.setDeliveryStatus(order.getDeliveryStatus());
			orderData.setTotalPrice(
					String.valueOf(order.getQuantity() * Double.parseDouble(order.getProduct().getPrice().toString())));
			if (order.getDeliveryPersonId() == 0) {
				orderData.setDeliveryPersonContact(DeliveryStatus.PENDING.value());
				orderData.setDeliveryPersonName(DeliveryStatus.PENDING.value());
			}

			else {

				User deliveryPerson = null;

				Optional<User> optionalDeliveryPerson = this.userDao.findById(order.getDeliveryPersonId());

				deliveryPerson = optionalDeliveryPerson.get();

				orderData.setDeliveryPersonContact(deliveryPerson.getPhoneNo());
				orderData.setDeliveryPersonName(deliveryPerson.getFirstName());
			}
			orderDatas.add(orderData);
		}

		String json = objectMapper.writeValueAsString(orderDatas);

		System.out.println(json);

		return new ResponseEntity<>(orderDatas, HttpStatus.OK);

	}

	@GetMapping("admin/allorder")
	public ResponseEntity<?> getAllOrder() throws JsonProcessingException {

		System.out.println("request came for FETCH ALL ORDERS");

		List<Orders> userOrder = orderDao.findAll();

		

		List<MyOrderResponse> orderDatas = new ArrayList<>();

		for (Orders order : userOrder) {
			MyOrderResponse orderData = new MyOrderResponse();
			orderData.setId(order.getId());
			orderData.setOrderId(order.getOrderId());
			orderData.setProductDescription(order.getProduct().getDescription());
			orderData.setProductName(order.getProduct().getTitle());
			orderData.setProductImage(order.getProduct().getImageName());
			orderData.setQuantity(order.getQuantity());
			orderData.setOrderDate(order.getOrderDate());
			orderData.setProductId(order.getProduct().getId());
			orderData.setDeliveryDate(order.getDeliveryDate() + " " + order.getDeliveryTime());
			orderData.setDeliveryStatus(order.getDeliveryStatus());
			orderData.setTotalPrice(
					String.valueOf(order.getQuantity() * Double.parseDouble(order.getProduct().getPrice().toString())));
			orderData.setUserId(order.getUser().getId());
			orderData.setUserName(order.getUser().getFirstName() + " " + order.getUser().getLastName());
			orderData.setUserPhone(order.getUser().getPhoneNo());
			orderData.setAddress(order.getUser().getAddress());
			if (order.getDeliveryPersonId() == 0) {
				orderData.setDeliveryPersonContact(DeliveryStatus.PENDING.value());
				orderData.setDeliveryPersonName(DeliveryStatus.PENDING.value());
			}

			else {
				User deliveryPerson = null;

				Optional<User> optionalDeliveryPerson = this.userDao.findById(order.getDeliveryPersonId());

				deliveryPerson = optionalDeliveryPerson.get();

				orderData.setDeliveryPersonContact(deliveryPerson.getPhoneNo());
				orderData.setDeliveryPersonName(deliveryPerson.getFirstName());
			}
			orderDatas.add(orderData);

		}

		String json = objectMapper.writeValueAsString(orderDatas);

		System.out.println(json);

		System.out.println("response sent !!!");

		return new ResponseEntity<>(orderDatas, HttpStatus.OK);

	}
	
	@GetMapping("admins/NoDeliveryPerson/allorder")
	public ResponseEntity<?> getAllOrders() throws JsonProcessingException {

		System.out.println("request came for FETCH ALL ORDERS NoDeliveryPerson");

		List<Orders> userOrder = orderDao.findAllNoDeliveryPerson();

		

		List<MyOrderResponse> orderDatas = new ArrayList<>();

		for (Orders order : userOrder) {
			MyOrderResponse orderData = new MyOrderResponse();
			orderData.setOrderId(order.getOrderId());
			orderData.setProductDescription(order.getProduct().getDescription());
			orderData.setProductName(order.getProduct().getTitle());
			orderData.setProductImage(order.getProduct().getImageName());
			orderData.setQuantity(order.getQuantity());
			orderData.setOrderDate(order.getOrderDate());
			orderData.setProductId(order.getProduct().getId());
			orderData.setDeliveryDate(order.getDeliveryDate() + " " + order.getDeliveryTime());
			orderData.setDeliveryStatus(order.getDeliveryStatus());
			orderData.setTotalPrice(
					String.valueOf(order.getQuantity() * Double.parseDouble(order.getProduct().getPrice().toString())));
			orderData.setUserId(order.getUser().getId());
			orderData.setUserName(order.getUser().getFirstName() + " " + order.getUser().getLastName());
			orderData.setUserPhone(order.getUser().getPhoneNo());
			orderData.setAddress(order.getUser().getAddress());
			if (order.getDeliveryPersonId() == 0) {
				orderData.setDeliveryPersonContact(DeliveryStatus.PENDING.value());
				orderData.setDeliveryPersonName(DeliveryStatus.PENDING.value());
			}

			else {
				User deliveryPerson = null;

				Optional<User> optionalDeliveryPerson = this.userDao.findById(order.getDeliveryPersonId());

				deliveryPerson = optionalDeliveryPerson.get();

				orderData.setDeliveryPersonContact(deliveryPerson.getPhoneNo());
				orderData.setDeliveryPersonName(deliveryPerson.getFirstName());
			}
			orderDatas.add(orderData);

		}

		String json = objectMapper.writeValueAsString(orderDatas);

		System.out.println(json);

		System.out.println("response sent !!!");

		return new ResponseEntity<>(orderDatas, HttpStatus.OK);

	}

	@GetMapping("admin/showorder")
	public ResponseEntity<?> getOrdersByOrderId(@RequestParam("orderId") String orderId) throws JsonProcessingException {

		System.out.println("request came for FETCH ORDERS BY ORDER ID : " + orderId);

		List<Orders> userOrder = orderDao.findByOrderId(orderId);

		List<MyOrderResponse> orderDatas = new ArrayList<>();

		for (Orders order : userOrder) {
			MyOrderResponse orderData = new MyOrderResponse();
			orderData.setOrderId(order.getOrderId());
			orderData.setProductDescription(order.getProduct().getDescription());
			orderData.setProductName(order.getProduct().getTitle());
			orderData.setProductImage(order.getProduct().getImageName());
			orderData.setQuantity(order.getQuantity());
			orderData.setOrderDate(order.getOrderDate());
			orderData.setProductId(order.getProduct().getId());
			orderData.setDeliveryDate(order.getDeliveryDate() + " " + order.getDeliveryTime());
			orderData.setDeliveryStatus(order.getDeliveryStatus());
			orderData.setTotalPrice(
					String.valueOf(order.getQuantity() * Double.parseDouble(order.getProduct().getPrice().toString())));
			orderData.setUserId(order.getUser().getId());
			orderData.setUserName(order.getUser().getFirstName() + " " + order.getUser().getLastName());
			orderData.setUserPhone(order.getUser().getPhoneNo());
			orderData.setAddress(order.getUser().getAddress());
			if (order.getDeliveryPersonId() == 0) {
				orderData.setDeliveryPersonContact(DeliveryStatus.PENDING.value());
				orderData.setDeliveryPersonName(DeliveryStatus.PENDING.value());
			}

			else {
				User deliveryPerson = null;

				Optional<User> optionalDeliveryPerson = this.userDao.findById(order.getDeliveryPersonId());

				deliveryPerson = optionalDeliveryPerson.get();

				orderData.setDeliveryPersonContact(deliveryPerson.getPhoneNo());
				orderData.setDeliveryPersonName(deliveryPerson.getFirstName());
			}
			orderDatas.add(orderData);

		}

		String json = objectMapper.writeValueAsString(orderDatas);

		System.out.println(json);

		System.out.println("response sent !!!");

		return new ResponseEntity<>(orderDatas, HttpStatus.OK);

	}

	@PostMapping("admin/order/deliveryStatus/update")
	public ResponseEntity<?> updateOrderDeliveryStatus(@RequestBody UpdateDeliveryStatusRequest deliveryRequest)
			throws JsonProcessingException {

		System.out.println("response came for UPDATE DELIVERY STATUS");

		System.out.println(deliveryRequest);

		List<Orders> orders = orderDao.findByOrderId(deliveryRequest.getOrderId());

		for (Orders order : orders) {
			order.setDeliveryDate(deliveryRequest.getDeliveryDate());
			order.setDeliveryStatus(deliveryRequest.getDeliveryStatus());
			order.setDeliveryTime(deliveryRequest.getDeliveryTime());
			orderDao.save(order);
		}

		List<Orders> userOrder = orderDao.findByOrderId(deliveryRequest.getOrderId());

		List<MyOrderResponse> orderDatas = new ArrayList<>();

		for (Orders order : userOrder) {
			MyOrderResponse orderData = new MyOrderResponse();
			orderData.setOrderId(order.getOrderId());
			orderData.setProductDescription(order.getProduct().getDescription());
			orderData.setProductName(order.getProduct().getTitle());
			orderData.setProductImage(order.getProduct().getImageName());
			orderData.setQuantity(order.getQuantity());
			orderData.setOrderDate(order.getOrderDate());
			orderData.setProductId(order.getProduct().getId());
			orderData.setDeliveryDate(order.getDeliveryDate() + " " + order.getDeliveryTime());
			orderData.setDeliveryStatus(order.getDeliveryStatus());
			orderData.setTotalPrice(
					String.valueOf(order.getQuantity() * Double.parseDouble(order.getProduct().getPrice().toString())));
			orderData.setUserId(order.getUser().getId());
			orderData.setUserName(order.getUser().getFirstName() + " " + order.getUser().getLastName());
			orderData.setUserPhone(order.getUser().getPhoneNo());
			orderData.setAddress(order.getUser().getAddress());
			if (order.getDeliveryPersonId() == 0) {
				orderData.setDeliveryPersonContact(DeliveryStatus.PENDING.value());
				orderData.setDeliveryPersonName(DeliveryStatus.PENDING.value());
			}

			else {
				User deliveryPerson = null;

				Optional<User> optionalDeliveryPerson = this.userDao.findById(order.getDeliveryPersonId());

				deliveryPerson = optionalDeliveryPerson.get();

				orderData.setDeliveryPersonContact(deliveryPerson.getPhoneNo());
				orderData.setDeliveryPersonName(deliveryPerson.getFirstName());
			}
			orderDatas.add(orderData);

		}

		String orderJson = objectMapper.writeValueAsString(orderDatas);

		System.out.println(orderJson);

		System.out.println("response sent !!!");

		return new ResponseEntity<>(orderDatas, HttpStatus.OK);
	}

	@PostMapping("admin/order/assignDelivery")
	public ResponseEntity<?> assignDeliveryPersonForOrder(@RequestBody UpdateDeliveryStatusRequest deliveryRequest)
			throws JsonProcessingException {

		System.out.println("response came for ASSIGN DELIVERY PERSON FPOR ORDERS");

		System.out.println(deliveryRequest);

		List<Orders> orders = orderDao.findByOrderId(deliveryRequest.getOrderId());

		User deliveryPerson1 = null;

		Optional<User> optionalDeliveryPerson = this.userDao.findById(deliveryRequest.getDeliveryId());

		if (optionalDeliveryPerson.isPresent()) {
			deliveryPerson1 = optionalDeliveryPerson.get();
		}

		for (Orders order : orders) {
			order.setDeliveryAssigned(IsDeliveryAssigned.YES.value());
			order.setDeliveryPersonId(deliveryRequest.getDeliveryId());
			orderDao.save(order);
		}

		List<Orders> userOrder = orderDao.findByOrderId(deliveryRequest.getOrderId());

		List<MyOrderResponse> orderDatas = new ArrayList<>();

		for (Orders order : userOrder) {
			MyOrderResponse orderData = new MyOrderResponse();
			orderData.setOrderId(order.getOrderId());
			orderData.setProductDescription(order.getProduct().getDescription());
			orderData.setProductName(order.getProduct().getTitle());
			orderData.setProductImage(order.getProduct().getImageName());
			orderData.setQuantity(order.getQuantity());
			orderData.setOrderDate(order.getOrderDate());
			orderData.setProductId(order.getProduct().getId());
			orderData.setDeliveryDate(order.getDeliveryDate() + " " + order.getDeliveryTime());
			orderData.setDeliveryStatus(order.getDeliveryStatus());
			orderData.setTotalPrice(
					String.valueOf(order.getQuantity() * Double.parseDouble(order.getProduct().getPrice().toString())));
			orderData.setUserId(order.getUser().getId());
			orderData.setUserName(order.getUser().getFirstName() + " " + order.getUser().getLastName());
			orderData.setUserPhone(order.getUser().getPhoneNo());
			orderData.setAddress(order.getUser().getAddress());

			if (order.getDeliveryPersonId() == 0) {
				orderData.setDeliveryPersonContact(DeliveryStatus.PENDING.value());
				orderData.setDeliveryPersonName(DeliveryStatus.PENDING.value());
			}

			else {
				User dPerson = null;

				Optional<User> optionalPerson = this.userDao.findById(order.getDeliveryPersonId());

				dPerson = optionalPerson.get();

				orderData.setDeliveryPersonContact(dPerson.getPhoneNo());
				orderData.setDeliveryPersonName(dPerson.getFirstName());
			}

			orderDatas.add(orderData);

		}

		String orderJson = objectMapper.writeValueAsString(orderDatas);

		System.out.println(orderJson);

		System.out.println("response sent !!!");

		return new ResponseEntity<>(orderDatas, HttpStatus.OK);
	}

	@GetMapping("delivery/myorder")
	public ResponseEntity<?> getMyDeliveryOrders(@RequestParam("deliveryPersonId") int deliveryPersonId)
			throws JsonProcessingException {

		System.out.println("request came for MY DELIVERY ORDER for USER ID : " + deliveryPersonId);

		User person = null;

		Optional<User> oD = this.userDao.findById(deliveryPersonId);

		if (oD.isPresent()) {
			person = oD.get();
		}
        
		List<Orders> userOrder = orderDao.findByDeliveryPersonId(deliveryPersonId);

		List<MyOrderResponse> orderDatas = new ArrayList<>();

		for (Orders order : userOrder) {
			MyOrderResponse orderData = new MyOrderResponse();
			orderData.setOrderId(order.getOrderId());
			orderData.setProductDescription(order.getProduct().getDescription());
			orderData.setProductName(order.getProduct().getTitle());
			orderData.setProductImage(order.getProduct().getImageName());
			orderData.setQuantity(order.getQuantity());
			orderData.setOrderDate(order.getOrderDate());
			orderData.setProductId(order.getProduct().getId());
			orderData.setDeliveryDate(order.getDeliveryDate() + " " + order.getDeliveryTime());
			orderData.setDeliveryStatus(order.getDeliveryStatus());
			orderData.setTotalPrice(
					String.valueOf(order.getQuantity() * Double.parseDouble(order.getProduct().getPrice().toString())));
			orderData.setUserId(order.getUser().getId());
			orderData.setUserName(order.getUser().getFirstName() + " " + order.getUser().getLastName());
			orderData.setUserPhone(order.getUser().getPhoneNo());
			orderData.setAddress(order.getUser().getAddress());

			if (order.getDeliveryPersonId() == 0) {
				orderData.setDeliveryPersonContact(DeliveryStatus.PENDING.value());
				orderData.setDeliveryPersonName(DeliveryStatus.PENDING.value());
			}

			else {
				orderData.setDeliveryPersonContact(person.getPhoneNo());
				orderData.setDeliveryPersonName(person.getFirstName());
			}

			orderDatas.add(orderData);

		}

		String json = objectMapper.writeValueAsString(orderDatas);

		System.out.println(json);

		return new ResponseEntity<>(orderDatas, HttpStatus.OK);

	}
	
	@GetMapping("delivery/myordernot")
	public ResponseEntity<?> getMyDeliveryOrdersNOt(@RequestParam("deliveryPersonId") int deliveryPersonId)
			throws JsonProcessingException {

		System.out.println("request came for MY DELIVERY ORDER not delivered for USER ID : " + deliveryPersonId);

		User person = null;

		Optional<User> oD = this.userDao.findById(deliveryPersonId);

		if (oD.isPresent()) {
			person = oD.get();
		}
        
		List<Orders> userOrder = orderDao.findByDeliveryPersonIdNot(deliveryPersonId);

		List<MyOrderResponse> orderDatas = new ArrayList<>();

		for (Orders order : userOrder) {
			MyOrderResponse orderData = new MyOrderResponse();
			orderData.setOrderId(order.getOrderId());
			orderData.setProductDescription(order.getProduct().getDescription());
			orderData.setProductName(order.getProduct().getTitle());
			orderData.setProductImage(order.getProduct().getImageName());
			orderData.setQuantity(order.getQuantity());
			orderData.setOrderDate(order.getOrderDate());
			orderData.setProductId(order.getProduct().getId());
			orderData.setDeliveryDate(order.getDeliveryDate() + " " + order.getDeliveryTime());
			orderData.setDeliveryStatus(order.getDeliveryStatus());
			orderData.setTotalPrice(
					String.valueOf(order.getQuantity() * Double.parseDouble(order.getProduct().getPrice().toString())));
			orderData.setUserId(order.getUser().getId());
			orderData.setUserName(order.getUser().getFirstName() + " " + order.getUser().getLastName());
			orderData.setUserPhone(order.getUser().getPhoneNo());
			orderData.setAddress(order.getUser().getAddress());

			if (order.getDeliveryPersonId() == 0) {
				orderData.setDeliveryPersonContact(DeliveryStatus.PENDING.value());
				orderData.setDeliveryPersonName(DeliveryStatus.PENDING.value());
			}

			else {
				orderData.setDeliveryPersonContact(person.getPhoneNo());
				orderData.setDeliveryPersonName(person.getFirstName());
			}

			orderDatas.add(orderData);

		}

		String json = objectMapper.writeValueAsString(orderDatas);

		System.out.println(json);

		return new ResponseEntity<>(orderDatas, HttpStatus.OK);

	}
	
	@GetMapping("order/remove")
	public ResponseEntity<?> removeOrderById(@RequestParam("Id") int Id) {
		
		System.out.println("request came for remove Product order by order Id");
		Orders order =new Orders();
		Optional<Orders> optional =  orderDao.findById(Id);
		if(optional.isPresent()) {
			order = optional.get();
		}
		orderDao.delete(order);
		
		return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
		
	}

}
