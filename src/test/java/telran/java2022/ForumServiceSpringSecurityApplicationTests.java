package telran.java2022;




import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.awt.PageAttributes.MediaType;

import org.apache.tomcat.jni.User;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.modelmapper.internal.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ch.qos.logback.core.Context;
import telran.java2022.accounting.dao.UserAccountRepository;
import telran.java2022.accounting.dto.UserAccountResponseDto;
import telran.java2022.accounting.dto.UserRegisterDto;
import telran.java2022.accounting.model.UserAccount;
import telran.java2022.accounting.service.UserAccountServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
class ForumServiceSpringSecurityApplicationTests {
    private MockMvc mockMvc;
    String login="astrox";
	String firstName="Ivan";
	String  lastName="Petrovenko";
	String password="1234";
  //  @Autowired
  //  private  UserAccountRepository repository;
    @Autowired
private  WebApplicationContext context;
    ObjectMapper om= new ObjectMapper();
    UserAccount userAccount =new UserAccount();
	

    @BeforeEach
    public void setUp() {
	userAccount =new UserAccount(login,firstName,lastName,password);
	  mockMvc=MockMvcBuilders.webAppContextSetup(context).build();
    }
    
    @Test
    public void addUserTest() throws Exception {
	
	String jsonRequest=om.writeValueAsString(userAccount);
	MvcResult result=(MvcResult) mockMvc.perform(post("/account/register").content(jsonRequest).content(org.springframework.http.MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk());
	 String resultContent=result.getResponse().getContentAsString();
	 //UserAccountResponseDto response =om.readValue(resultContent, UserAccountResponseDto.class);
	assertEquals(resultContent, UserAccountResponseDto.class);
    }
    @Test
      void testCorrectName() {
	userAccount.setLastName("Korotchenov");
	assertEquals(lastName, userAccount.getLastName());
    }
    
    
    @Test
    void testCorrectFirstName() {
	userAccount.setFirstName("Artur");
	assertEquals(firstName, userAccount.getFirstName());
  }  
    
    @Test
    void testCorrectPassword() {
	userAccount.setPassword("1456");
	assertEquals(firstName, userAccount.getPassword());
  }  
}