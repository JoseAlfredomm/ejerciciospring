package com.ejercicio.ejercicio;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import com.ejercicio.ejercicio.entity.User;
import com.ejercicio.ejercicio.repository.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
class EjercicioApplicationTests {
	
//	@Autowired
//	private UserRepository userRepository;
//	
	@Autowired
	private PasswordEncoder encoder;
	
	
	
	@Test
	public void saveUser() {
//		User owner = new User();
		String cryp  = encoder.encode("secret");
		System.out.println(cryp);
//		owner.setPassword(cryp);
//		owner.setUserName("ClientId");
//		owner.setName("Client");
//		
//		User insert =userRepository.save(owner);
//		
//		
//		
		
	}

}
