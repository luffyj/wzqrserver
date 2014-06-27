/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hello;

import javax.inject.Inject;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.luffy.wzqr.wzqrserver.config.RestDataConfig;
import org.luffy.wzqr.wzqrserver.config.RootConfig;
import org.luffy.wzqr.wzqrserver.entity.Person;
import org.luffy.wzqr.wzqrserver.repositories.PersonRepository;
import org.luffy.wzqr.wzqrserver.web.GreetingController;
import org.mockito.InjectMocks;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import org.springframework.web.context.WebApplicationContext;

/**
 *
 * @author luffy
 */
@ActiveProfiles("test")
@ContextConfiguration(classes = {RestDataConfig.class,RootConfig.class,IRuntimeConfig.class})
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class PersonTest {
    
    @Inject
    private PersonRepository personRepository;
    @Inject
    private WebApplicationContext context;
    MockMvc mockMvc;

//    @InjectMocks
//    GreetingController controller;
    
    public PersonTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

//        mockMvc = standaloneSetup(controller).build();
        
//        when(personRepository.findOne(anyLong())).then;
//        when(personRepository.count()).thenReturn(2L);
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void person() throws Exception{
        MockMvc mvc = webAppContextSetup(context).build();        
        
        mvc.perform(get("/api/people/1")).andExpect(status().isNotFound());
        
        //test with mockito
//        https://cwiki.apache.org/confluence/display/CLOUDSTACK/Unit+Testing+with+JUnit+and+Spring
        
        mvc.perform(get("/api/people"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/hal+json"));
        
//        MvcResult result = mvc.perform(post("/people","{  \"firstName\" : \"Frodo\",  \"lastName\" : \"Baggins\" }"))
//                .andExpect(status().isCreated())
//                .andReturn();
        
        
        System.out.println(mvc);
//        System.out.println(personRepository.count());
        System.out.println(new Person());
        assertNotNull(new Person());
    }
    
//
//    /**
//     * Test of getFirstName method, of class Person.
//     */
//    @Test
//    public void testGetFirstName() {
//        System.out.println("getFirstName");
//        Person instance = new Person();
//        String expResult = "";
//        String result = instance.getFirstName();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setFirstName method, of class Person.
//     */
//    @Test
//    public void testSetFirstName() {
//        System.out.println("setFirstName");
//        String firstName = "";
//        Person instance = new Person();
//        instance.setFirstName(firstName);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getLastName method, of class Person.
//     */
//    @Test
//    public void testGetLastName() {
//        System.out.println("getLastName");
//        Person instance = new Person();
//        String expResult = "";
//        String result = instance.getLastName();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setLastName method, of class Person.
//     */
//    @Test
//    public void testSetLastName() {
//        System.out.println("setLastName");
//        String lastName = "";
//        Person instance = new Person();
//        instance.setLastName(lastName);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    
}
