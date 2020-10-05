package my.example.service.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static my.example.service.core.GitHubUserInfoController.validUser;

@SpringBootTest
public class GitHubUserInfoControllerTests {

    @Test
    void validUserTest(){
        //Test arbitrary username
        Assertions.assertTrue(validUser("abc0_5-5"));
        //Test username with two non sequential '-'
        Assertions.assertTrue(validUser("abc-ef-ghij"));
        //Test username starting with '-'
        Assertions.assertFalse(validUser("-abcde"));
        //Test username ending with '-'
        Assertions.assertFalse(validUser("abcde-"));
        //Test username containing two sequential '-''s
        Assertions.assertFalse(validUser("a--bcde"));
        //Test username with special symbols
        Assertions.assertFalse(validUser("aback&^%#"));
        //Test username with escape characters
        Assertions.assertFalse(validUser("\\"));
    }
}
