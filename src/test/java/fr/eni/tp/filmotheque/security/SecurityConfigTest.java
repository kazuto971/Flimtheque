package fr.eni.tp.filmotheque.security;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
public class SecurityConfigTest {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void passwordEncoderTest(){
        System.out.println(passwordEncoder.encode("azerty"));
    }

    @Test
    public void passwordEncoderTest2(){
        System.out.println(passwordEncoder.encode("qwerty"));
    }
}
