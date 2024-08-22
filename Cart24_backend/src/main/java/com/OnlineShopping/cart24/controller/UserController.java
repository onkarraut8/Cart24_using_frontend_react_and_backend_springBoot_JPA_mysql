package com.OnlineShopping.cart24.controller;

import java.util.List;
import java.util.Optional;
//import net.bytebuddy.utility.RandomString;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.OnlineShopping.cart24.dao.AddressDao;
import com.OnlineShopping.cart24.dao.OrderDao;
import com.OnlineShopping.cart24.dao.UserDao;
import com.OnlineShopping.cart24.dto.AddUserRequest;
import com.OnlineShopping.cart24.dto.Count;
import com.OnlineShopping.cart24.dto.ForgotRequest;
import com.OnlineShopping.cart24.dto.Profile;
import com.OnlineShopping.cart24.dto.UserLoginRequest;
import com.OnlineShopping.cart24.dto.Varify;
import com.OnlineShopping.cart24.model.Address;
import com.OnlineShopping.cart24.model.User;
import com.OnlineShopping.cart24.utility.Helper;

import jakarta.servlet.http.HttpSession;






@RestController
@RequestMapping("api/user")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
	String otp="";
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private OrderDao orderDao;

	@Autowired
	private AddressDao addressDao;

	@Autowired
	private JavaMailSender mailSender;

	@PostMapping("register")
	public ResponseEntity<?> registerUser(@RequestBody AddUserRequest userRequest) {
		
		List<User> per= userDao.findByEmailIdAndPhoneNo1(userRequest.getEmailId(),userRequest.getPhoneNo());
		System.out.println(per);
		if(!per.isEmpty())  {
			System.out.println("Enter Another Email and Mobile No.");
			return new ResponseEntity<>("Enter Another Email and Mobile No.", HttpStatus.EXPECTATION_FAILED);
        }
		else {
		System.out.println("recieved request for REGISTER USER");
		System.out.println(userRequest);
        String varify="NO";
        if(userRequest.getRole().equals("Customer")) {
        	varify="YES";
        }
		Address address = new Address();
		address.setCity(userRequest.getCity());
		address.setCountry(userRequest.getCountry());
		address.setState(userRequest.getState());
		address.setPincode(userRequest.getPincode());
		address.setStreet(userRequest.getStreet());

		Address addAddress = addressDao.save(address);

		User user = new User();
		user.setAddress(addAddress);
		System.out.println("response Lower Case Email!: "+userRequest.getEmailId().toLowerCase());
		user.setEmailId(userRequest.getEmailId().toLowerCase());
		user.setVarify(varify);
		user.setFirstName(userRequest.getFirstName());
		user.setLastName(userRequest.getLastName());
		user.setGender(userRequest.getGender());
		user.setPhoneNo(userRequest.getPhoneNo());
		user.setPassword(userRequest.getPassword());
		user.setRole(userRequest.getRole());
		User addUser = userDao.save(user);
		System.out.println("response sent!!!");
		return ResponseEntity.ok(addUser);
        }
	}

	@PostMapping("updates")
	public ResponseEntity<?> updateUser(@RequestBody AddUserRequest updates) {
		System.out.println("recieved request for update USER");
		System.out.println(updates);
		System.out.println("recieved password" + updates.getPassword());

		Optional<User> optionalUser = userDao.findById(updates.getId());

		User users = new User();

		if (optionalUser.isPresent()) {
			users = optionalUser.get();
		}
		Address address = new Address();
		address.setId(users.getAddress().getId());
		address.setCity(updates.getCity());
		address.setCountry(updates.getCountry());
		address.setState(updates.getState());
		address.setPincode(updates.getPincode());
		address.setStreet(updates.getStreet());
		Address addadd = addressDao.save(address);
		User user = new User();
		user.setId(updates.getId());
		user.setAddress(addadd);
		user.setEmailId(updates.getEmailId());
		user.setVarify(users.getVarify());
		user.setFirstName(updates.getFirstName());
		user.setLastName(updates.getLastName());
		user.setGender(updates.getGender());
		user.setPhoneNo(updates.getPhoneNo());

		user.setPassword(users.getPassword());
		user.setRole(users.getRole());

		User addUser = userDao.save(user);

		System.out.println("response sent!!!");
		return ResponseEntity.ok(addUser);
	}

	@PostMapping("update")
	public ResponseEntity<?> update(Profile uProfile) {
		System.out.println("recieved request for update profile");
		System.out.println(uProfile);

		User user1 = new User();
		Optional<User> optional = userDao.findById(uProfile.getId());
		if (optional.isPresent()) {
			user1 = optional.get();
		}
		System.out.println("a@#$%^&&* " + user1);

		Address address = new Address();
		address.setId(user1.getAddress().getId());
		address.setCity(uProfile.getCity());
		address.setCountry(uProfile.getCountry());
		address.setState(uProfile.getState());
		address.setPincode(uProfile.getPincode());
		address.setStreet(uProfile.getStreet());

		Address addAddress = addressDao.save(address);

		User user = new User();
		user.setFirstName(uProfile.getFirstName());
		user.setLastName(uProfile.getLastName());
		user.setGender(uProfile.getGender());
		user.setAddress(addAddress);
		user.setEmailId(uProfile.getEmailId());
		user.setId(uProfile.getId());
		user.setVarify(user1.getVarify());
		user.setRole(user1.getRole());
		user.setPhoneNo(uProfile.getPhoneNo());
		user.setPassword(user1.getPassword());
		User addUser = userDao.save(user);
		System.out.println("pass update response sent!!!");
		System.out.println("update " + addUser);
		return ResponseEntity.ok(addUser);
	}

	@PostMapping("login")
	public ResponseEntity<?> loginUser(@RequestBody UserLoginRequest loginRequest) {
		System.out.println("recieved request for LOGIN USER");
		System.out.println(loginRequest);
		System.out.println("response Lower Case Email!: "+loginRequest.getEmailId().toLowerCase());
        String varify ="Yes";
		User user = new User();
		user = userDao.findByEmailIdAndPasswordAndRoleAndVarify(loginRequest.getEmailId().toLowerCase(), loginRequest.getPassword(),
				loginRequest.getRole(),varify);
		System.out.println("a@#$%^&&* " + user);
		System.out.println("response sent!!!");
		return ResponseEntity.ok(user);
	}

	@PostMapping("forgot")
	public ResponseEntity<?> forgot(@RequestBody ForgotRequest forgot) {
		System.out.println("recieved request for change password");
		System.out.println(forgot);

		User user1 = new User();
		user1 = userDao.findByEmailIdAndPhoneNo(forgot.getEmailId(), forgot.getPhoneNo());
		System.out.println("a@#$%^&&* " + user1);
		User user = new User();
		user.setVarify(user1.getVarify());
		user.setFirstName(user1.getFirstName());
		user.setLastName(user1.getLastName());
		user.setGender(user1.getGender());
		user.setAddress(user1.getAddress());
		user.setEmailId(user1.getEmailId());
		user.setId(user1.getId());
		user.setRole(user1.getRole());
		user.setPhoneNo(user1.getPhoneNo());
		user.setPassword(forgot.getPassword());
		User addUser = userDao.save(user);
		System.out.println("pass update response sent!!!");
		return ResponseEntity.ok(addUser);
	}
	
	@PostMapping("refreshprofile")
	public ResponseEntity<?> refreshProfile(@RequestBody Profile profile) {
		System.out.println("recieved request for Refresh PRofile");
		System.out.println(profile);

		User user1 = new User();
		user1 = userDao.findByEmailIdOrPhoneNoOrId(profile.getEmailId(),profile.getPhoneNo(),profile.getId());
		System.out.println("a@#$%^&&* " + user1);
		
		System.out.println("pass update response sent!!!");
		return ResponseEntity.ok(user1);
	}
	
	@PostMapping("forgotpassword")
	public ResponseEntity<?> forgotPassword(@RequestBody ForgotRequest forgot) {
		System.out.println("recieved request for change password");
		System.out.println(forgot);
		
		User user1 = new User();
		user1 = userDao.findByEmailId(forgot.getEmailId());
		System.out.println("a@#$%^&&* " + user1);
		
		if(user1!=null) 
		{
		User user = new User();
		user.setVarify(user1.getVarify());
		user.setFirstName(user1.getFirstName());
		user.setLastName(user1.getLastName());
		user.setGender(user1.getGender());
		user.setAddress(user1.getAddress());
		user.setEmailId(user1.getEmailId());
		user.setId(user1.getId());
		user.setRole(user1.getRole());
		user.setPhoneNo(user1.getPhoneNo());
		user.setPassword(forgot.getPassword());
		User addUser = userDao.save(user);
		System.out.println("pass update response sent!!!");
		return ResponseEntity.ok(addUser);
		}
		else {
			return new ResponseEntity<>("EmailId is not Register",HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("forgotpass")
	public ResponseEntity<?> forgotPass(@RequestBody ForgotRequest forgotPass,HttpSession session) {
		System.out.println("recieved request for change password");
		System.out.println(forgotPass);

		User user1 = new User();
		user1 = userDao.findByEmailIdAndPhoneNo(forgotPass.getEmailId(), forgotPass.getPhoneNo());
		System.out.println("a@#$%^&&* " + user1);

		if (user1 != null) {
			String toemail = forgotPass.getEmailId();
			 //otp = RandomString.make(6);
			  otp =  Helper.getNumericOtp();
			
			String mob = forgotPass.getPhoneNo();
			//int otp=0;
			String body = "OTP for Change Password: " + otp;
			String subject = "Cart24 Forgot Password Request";

			SimpleMailMessage message = new SimpleMailMessage();
			message.setFrom("fromemail@gmail.com");
			message.setTo(toemail);
			message.setText(body);
			message.setSubject(subject);
			mailSender.send(message);
			System.out.println("Mail Send...");
			 
			session.setAttribute("toemail", toemail);
			session.setAttribute("Myootp", otp);
			session.setAttribute("mobb", mob);
			
			return ResponseEntity.ok(user1);
			/*
			 * long curenttime = System.currentTimeMillis(); long difference = curenttime -
			 * date; if (difference > (15 * 60 * 1000)) { Greater than 1 hour
			 * session.removeAttribute(toemail); session.removeAttribute(otp); }
			 */

		} else {
			return new ResponseEntity<>("Please Register First, EmailId is not register",HttpStatus.NOT_FOUND);
		}

	}
	
	@PostMapping("forgot/validateotp")
	public ResponseEntity<?> validateOtp(@RequestParam("Otp") String Otp, HttpSession session) {
		System.out.println("recieved request for validate otp");
		System.out.println("OTP@@ "+Otp);
		
		
		String MyOtp=(String)session.getAttribute("Myootp");
        String UOtp=Otp;
        
        
        System.out.println("OTP SESSION MYOTP: "+MyOtp+"| UOTP: "+UOtp);
        System.out.println("OTP: "+otp+" UOTP: "+UOtp);
        if(!UOtp.equals("")) {
        if(otp.equals(UOtp)) {
        	System.out.println("MYOTP: "+MyOtp+" UOTP: "+UOtp);
        	otp="";
        	System.out.println("OTP: "+otp);
        	return new ResponseEntity<>(HttpStatus.OK);
        }
        else {
			return new ResponseEntity<>("Wrong OTP, Enter valid Otp",HttpStatus.BAD_REQUEST);
		}
        }
        else {
        	return new ResponseEntity<>("Wrong OTP, Enter valid Otp",HttpStatus.BAD_REQUEST);
        }

	}

	@PostMapping("change")
	public ResponseEntity<?> change(ForgotRequest changePass) {
		System.out.println("recieved request for change password");
		System.out.println(changePass);

		User user1 = new User();
		user1 = userDao.findByEmailIdOrPhoneNoOrId(changePass.getEmailId(),changePass.getPhoneNo(), changePass.getId());
		System.out.println("a@#$%^&&* " + user1);
		if(user1!=null) {
		User user = new User();
		user.setVarify(user1.getVarify());
		user.setFirstName(user1.getFirstName());
		user.setLastName(user1.getLastName());
		user.setGender(user1.getGender());
		user.setAddress(user1.getAddress());
		user.setEmailId(user1.getEmailId());
		user.setId(user1.getId());
		user.setRole(user1.getRole());
		user.setPhoneNo(user1.getPhoneNo());
		user.setPassword(changePass.getPassword());
		User addUser = userDao.save(user);
		System.out.println("pass update response sent!!!");
		return ResponseEntity.ok(addUser);
		}
		else {
			return new ResponseEntity<>("Enter valid Register Email",HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("deliveryperson/all")
	public ResponseEntity<?> getAllDeliveryPersons() {
		System.out.println("recieved request for getting ALL Delivery Persons!!!");

		List<User> deliveryPersons = this.userDao.findByRoleAndVarify("Delivery");

		System.out.println("response sent!!!");
		return ResponseEntity.ok(deliveryPersons);
	}

	@GetMapping("id")
	public ResponseEntity<?> getUser(@RequestParam("id") int id) {
		System.out.println("recieved request for update USER");
		System.out.println("ID===" + id);

		Optional<User> optionalUser = userDao.findById(id);

		User user = new User();

		if (optionalUser.isPresent()) {
			user = optionalUser.get();
		}

		return ResponseEntity.ok(user);
	}
	
	@PostMapping("all")
	public ResponseEntity<List<User>> getAllUsers() {
		
        System.out.println("request came for getting all users++");
		
		List<User> users = userDao.varifyUser();
		
		ResponseEntity<List<User>> response = new ResponseEntity<>(users, HttpStatus.OK);
		
		System.out.println("response sent");
		
		return response;
		
	}
	
	@GetMapping("varifyall")
	public ResponseEntity<List<User>> getAllUserss() {
		
        System.out.println("request came for getting all users++");
		
		List<User> users = userDao.findAll();
		
		ResponseEntity<List<User>> response = new ResponseEntity<>(users, HttpStatus.OK);
		
		System.out.println("response sent");
		
		return response;
		
	}
	
	@PostMapping("remove")
	public ResponseEntity<?> deleteUser(@RequestParam("id") int id) {
		System.out.println("recieved request for remove USER");
		System.out.println("ID===" + id);

		Optional<User> optionalUser = userDao.findById(id);
		User user = new User();

		if (optionalUser.isPresent()) {
			user = optionalUser.get();
		}
		
		Optional<Address> optionalad = addressDao.findById(user.getAddress().getId());
		Address add = new Address();

		if (optionalad.isPresent()) {
			add = optionalad.get();
		}
		
		 
         this.userDao.delete(user);
         this.addressDao.delete(add);
		
		return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
	}
	
	@GetMapping("searchs")
	public ResponseEntity<?> getUserBySearch(@RequestParam("search") String search) {
		System.out.println("search="+ search);
		System.out.println("request came for getting all user by search");
		
		
			List<User> users = this.userDao.search(search);
			ResponseEntity<List<User>> response = new ResponseEntity<>(users, HttpStatus.OK);
			System.out.println("response sent!!!");
			return response;
	        
		}
		
	
	
	@PostMapping("varify")
	public ResponseEntity<?>  varify(@RequestBody Varify varify) {
		System.out.println("recieved request for varify user");
		System.out.println(varify);

		User user1 = new User();
		Optional<User> optional = userDao.findById(varify.getId());
		if (optional.isPresent()) {
			user1 = optional.get();
		}
		System.out.println("a@#$%^&&* " + user1);

		Address address = new Address();
		address.setId(user1.getAddress().getId());
		address.setCity(varify.getCity());
		address.setCountry(varify.getCountry());
		address.setState(varify.getState());
		address.setPincode(varify.getPincode());
		address.setStreet(varify.getStreet());

		Address addAddress = addressDao.save(address);

		User user = new User();
		user.setFirstName(user1.getFirstName());
		user.setLastName(user1.getLastName());
		user.setGender(user1.getGender());
		user.setAddress(addAddress);
		user.setEmailId(varify.getEmailId());
		user.setId(varify.getId());
		user.setVarify(varify.getVarify());
		user.setRole(user1.getRole());
		user.setPhoneNo(varify.getPhoneNo());
		user.setPassword(user1.getPassword());
		User addUser = userDao.save(user);
		System.out.println("varify " + addUser);
		return ResponseEntity.ok(addUser);
	}
	
	@GetMapping("count")
	public ResponseEntity<?> counts() {
		System.out.println("recieved request for Counts");
		Count count =new Count();
		count.setCountCustomer( userDao.countCustomer());
		count.setCountDelivery(userDao.countDelivery());
		count.setCountTodayOrder(orderDao.countTodayOrder());
		count.setCountThisMonthOrder(orderDao.countThisMonthOrder());
		System.out.println("Count: " + count);
		System.out.println("response sent!!!");
		return ResponseEntity.ok(count);
	}

}
