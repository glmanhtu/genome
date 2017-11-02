package com.ebi.genome.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ITUserServiceTest {

    @Test
    public void test() {
        Assert.assertEquals(true, true);
    }

//    @Autowired
//    private UserService userService;
//
//    @Autowired
//    private BCryptPasswordEncoder passwordEncoder;
//
//    @Before
//    public void init() throws Exception{
//        userService.delete("nguyenlamit86@gmail.com");
//    }
//
//    @Test
//    public void getOne() throws Exception {
//        User user = userService.getOne("nguyenlamit86@gmail.com");
//        System.out.println(user.toString());
//
//    }
//
//    @Test
//    public void createOne() throws Exception {
//        User user = new User();
//        user.setId("nguyenlamit86@gmail.com");
//        user.setFirstName("Lam");
//        user.setLastName("Nguyen");
//        user.setPassword("123456");
//
//        user = userService.create(user);
//        System.out.println(user.toString());
//    }

}