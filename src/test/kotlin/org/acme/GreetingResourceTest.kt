package org.acme

import io.quarkus.test.junit.QuarkusTest
import io.restassured.module.kotlin.extensions.Extract
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import jakarta.inject.Inject
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import software.amazon.awssdk.services.dynamodb.DynamoDbClient
import software.amazon.awssdk.services.dynamodb.model.*

@QuarkusTest
class GreetingResourceTest {

    @Inject
    lateinit var client: DynamoDbClient

    @BeforeEach
    fun setUp() {
        client.createTable(
                CreateTableRequest.builder()
                        .attributeDefinitions(
                                AttributeDefinition.builder()
                                        .attributeName("id")
                                        .attributeType(ScalarAttributeType.S)
                                        .build()
                        )
                        .keySchema(
                                KeySchemaElement.builder()
                                        .attributeName("id")
                                        .keyType(KeyType.HASH)
                                        .build()
                        )
                        .provisionedThroughput(
                                ProvisionedThroughput.builder()
                                        .readCapacityUnits(10)
                                        .writeCapacityUnits(10)
                                        .build()
                        )
                        .tableName("Table")
                        .build()
        )
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