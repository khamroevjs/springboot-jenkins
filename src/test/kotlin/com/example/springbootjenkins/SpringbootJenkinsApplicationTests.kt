package com.example.springbootjenkins

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class SpringbootJenkinsApplicationTests {

    private val controller: MainController = MainController()

    @Test
    fun contextLoads() {
        assertThat(true).isTrue()
    }

    @Test
    fun indexTest() {
        assertEquals(controller.index(), "Hello World!")
    }
}
