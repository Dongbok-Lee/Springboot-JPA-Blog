package com.bok.blog.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bok.blog.dto.ResponseDto;
import com.bok.blog.model.RoleType;
import com.bok.blog.model.User;
import com.bok.blog.service.UserService;

@RestController
public class UserApiController {
	
	@Autowired
	private UserService userService;
	
	
	
	@PostMapping("/auth/joinProc")
	public ResponseDto<Integer> save(@RequestBody User user) {
		System.out.println("UserController:save 호출됨");
		//실제로 DB에 insert를 하고 아래에서 return이 되면 됨.
		userService.회원가입(user);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);//자바오브젝트를 JSON으로 변환해서 리턴(JACKSON라이브러리) 
	} 
}	