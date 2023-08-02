package org.acme

import io.mockk.every
import io.quarkiverse.test.junit.mockk.InjectMock
import io.quarkus.test.junit.QuarkusTest
import io.restassured.module.kotlin.extensions.Extract
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.time.Clock
import java.time.OffsetDateTime
import java.time.ZoneOffset

@QuarkusTest
class GreetingResourceTest {

    @InjectMock
    private lateinit var clock: Clock

    private val timestamp: OffsetDateTime = OffsetDateTime.of(2024, 11, 9, 0, 0, 0, 0, ZoneOffset.UTC)

    @BeforeEach
    fun setUp() {
        every { clock.zone } returns timestamp.offset
        every { clock.instant() } returns timestamp.toInstant()
    }

    @Test
    fun greetingResourceTest() {
        When {
            get("/hello")
        } Then {
            statusCode(200)
        } Extract {
            response().body().prettyPrint()
        }
    }
}