package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.FormLoginRequestBuilder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrlPattern;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class VendasSpringWebJpaApplicationTests {
	
	private MockMvc mock;
	
	@Test
	public void oginWithValidUserThenAuthenticated() throws Exception{
		FormLoginRequestBuilder login = formLogin()
				.user("usuario")
				.password("senha");
			mock.perform(login)
	            .andExpect(authenticated().withUsername("usuario"));
	}
	
	@Test
	public void loginWithInvalidUserThenUnauthenticated() throws Exception{
		FormLoginRequestBuilder login = formLogin()
	            .user("invalido")
	            .password("senhainvalida");

	        mock.perform(login)
	            .andExpect(unauthenticated());
	}
	
	@Test
	public void accessUnsecuredResourceThenOk() throws Exception {
        mock.perform(get("/"))
            .andExpect(status().isOk());
    }
	
	@Test
    public void accessSecuredResourceUnauthenticatedThenRedirectsToLogin() throws Exception {
        mock.perform(get("/hello"))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrlPattern("**/login"));
	}
	
	@Test
    @WithMockUser
    public void accessSecuredResourceAuthenticatedThenOk() throws Exception {
        mock.perform(get("/logado"))
                .andExpect(status().isOk());
    }

}
