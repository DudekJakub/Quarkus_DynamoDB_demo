package org.acme

import io.quarkus.arc.DefaultBean
import jakarta.enterprise.context.ApplicationScoped
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedAsyncClient
import software.amazon.awssdk.enhanced.dynamodb.extensions.AutoGeneratedTimestampRecordExtension
import software.amazon.awssdk.enhanced.dynamodb.extensions.VersionedRecordExtension
import software.amazon.awssdk.services.dynamodb.DynamoDbAsyncClient

class Config {

    @DefaultBean
    @ApplicationScoped
    fun enhancedClient(client: DynamoDbAsyncClient): DynamoDbEnhancedAsyncClient {
        return DynamoDbEnhancedAsyncClient.builder()
                .dynamoDbClient(client)
                .extensions(
                        AutoGeneratedTimestampRecordExtension.builder()
                                .build(),
                        VersionedRecordExtension.builder()
                                .build()
                )
                .build()
    }
}