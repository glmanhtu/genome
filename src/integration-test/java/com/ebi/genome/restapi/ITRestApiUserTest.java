package com.ebi.genome.restapi;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by Mrs Hoang on 12/02/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ITRestApiUserTest {

    @Test
    public void test() {
        Assert.assertEquals(true, true);
    }

//    @Autowired
//    private TestRestTemplate testRestTemplate;
//
//    @Autowired
//    private UserService userService;
//
//    @Test
//    public void getUserInfo(){
//
//        User user = userService.getOne("member_1@gmail.com");
//        Assert.assertNotNull(user);
//        Object body = this.testRestTemplate.getForObject("/user/member_1@gmail.com", UserHandler.class);
//        //assertThat(body).isEqualTo(user);
//    }
}
