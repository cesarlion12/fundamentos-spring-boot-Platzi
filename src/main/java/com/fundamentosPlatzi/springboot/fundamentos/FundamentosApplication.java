package com.fundamentosPlatzi.springboot.fundamentos;

import com.fundamentosPlatzi.springboot.fundamentos.bean.MyBean;
import com.fundamentosPlatzi.springboot.fundamentos.bean.MyBeanWithDependency;
import com.fundamentosPlatzi.springboot.fundamentos.bean.MyBeanWithProperties;
import com.fundamentosPlatzi.springboot.fundamentos.bean.component.ComponentDependency;
import com.fundamentosPlatzi.springboot.fundamentos.entity.User;
import com.fundamentosPlatzi.springboot.fundamentos.pojo.UserPojo;
import com.fundamentosPlatzi.springboot.fundamentos.repository.UserRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
@Component
@SpringBootApplication
public class FundamentosApplication implements CommandLineRunner {

	private final Log LOGGER = LogFactory.getLog(FundamentosApplication.class);

	private ComponentDependency componentDependency;
	private MyBean myBean;
	private MyBeanWithDependency myBeanWithDependency;
	private MyBeanWithProperties myBeanWithProperties;
    private UserPojo userPojo;
	private UserRepository userRepository;

	public FundamentosApplication(@Qualifier("componentTwoImplement") ComponentDependency componentDependency, MyBean myBean, MyBeanWithDependency myBeanWithDependency, MyBeanWithProperties myBeanWithProperties, UserPojo userPojo, UserRepository userRepository) {
		this.componentDependency = componentDependency;
		this.myBean= myBean;
		this.myBeanWithDependency = myBeanWithDependency;
		this.myBeanWithProperties = myBeanWithProperties;
        this.userPojo = userPojo;
		this.userRepository = userRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(FundamentosApplication.class, args);
	}

    @Override
	public void run(String... args) throws Exception {
		//ejemplosAnteriotes();
		saveUserInDataBase();
		getInformationJpqlFromUser();
	}

	private void getInformationJpqlFromUser(){
//		LOGGER.info("Usuario con el metodo findByUserEmail" + userRepository.findByUserEmail("user2@gmail.com")
//				.orElseThrow(()->new RuntimeException("No se encontro el usuario")));
//
//		userRepository.findAndSort("user", Sort.by("id").descending())
//				.stream()
//				.forEach(user -> LOGGER.info("usuario con metodo sort " + user));

		userRepository.findByName("Cesar")
				.stream()
				.forEach(user -> LOGGER.info("Usuario con query method " + user));
	}

	private void saveUserInDataBase(){
		User user1 = new User("Cesar", "test@gmail.com", LocalDate.of(2023,01,20));
		User user2 = new User("user2", "user2@gmail.com", LocalDate.of(2023,05,12));
		User user3 = new User("user3", "user3@gmail.com", LocalDate.of(2023,04,11));
		User user4 = new User("user4", "user4@gmail.com", LocalDate.of(2023,07,9));
		User user5 = new User("user5", "user5@gmail.com", LocalDate.of(2023,02,25));
		User user6 = new User("user6", "user6@gmail.com", LocalDate.of(2023,06,21));
		User user7 = new User("user7", "user7@gmail.com", LocalDate.of(2023,01,22));
		User user8 = new User("user8", "user8@gmail.com", LocalDate.of(2023,10,24));
		User user9 = new User("user9", "user9@gmail.com", LocalDate.of(2023,11,27));
		User user10 = new User("user10", "user10@gmail.com", LocalDate.of(2023,03,28));
		User user11 = new User("user11", "user11@gmail.com", LocalDate.of(2023,04,29));
		User user12 = new User("user12", "user12@gmail.com", LocalDate.of(2023,06,15));
		List<User> list = Arrays.asList(user1, user2, user3, user4, user5, user6, user7, user8, user9, user10, user11, user12);
		list.stream().forEach(userRepository::save);
	}

	private void ejemplosAnteriotes(){
		componentDependency.saludar();
		myBean.print();
		myBeanWithDependency.printWithDependency();
		System.out.println(myBeanWithProperties.function());
		System.out.println(userPojo.getEmail() + "-" + userPojo.getPassword() + "-" + userPojo.getAge());

		try {
			int value = 10/0;
			LOGGER.debug("Mi valor :" + value);
		}catch (Exception e){
			LOGGER.error("Esto es un error al dividir por cero " + e.getMessage());
		}
	}
}
