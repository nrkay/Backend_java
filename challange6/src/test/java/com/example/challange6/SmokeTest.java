package com.example.challange6;

import com.example.challange6.controller.ProductController;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;


@SpringBootTest
public class SmokeTest {

    private ProductController productController;
    @Test
    void contextLoad(){
        Assertions.assertThat(productController).isNotNull();
    }
}
