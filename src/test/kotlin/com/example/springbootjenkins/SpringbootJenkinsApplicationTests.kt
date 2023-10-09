package com.example.springbootjenkins

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class SpringbootJenkinsApplicationTests {

    @Test
    fun contextLoads() {
        assertThat(true).isTrue()
    }
}
