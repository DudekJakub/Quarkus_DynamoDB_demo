package org.acme

import io.smallrye.mutiny.Uni
import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType.APPLICATION_JSON
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedAsyncClient
import software.amazon.awssdk.enhanced.dynamodb.TableSchema

@Produces(APPLICATION_JSON)
@Path("/hello")
class GreetingResource(
        dynamoClient: DynamoDbEnhancedAsyncClient
) {

    private val tableSchema = TableSchema.fromClass(Greeting::class.java)
    private val table = dynamoClient.table("Table", tableSchema)

    @GET
    fun hello(): Uni<Greeting> {
        return Uni.createFrom().completionStage { table.updateItem(Greeting("1")) }
    }
}