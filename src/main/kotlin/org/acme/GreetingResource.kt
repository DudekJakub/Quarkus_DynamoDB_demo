package org.acme

import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType.APPLICATION_JSON
import java.time.Clock

@Produces(APPLICATION_JSON)
@Path("/hello")
class GreetingResource(
        private val clock: Clock
) {

    @GET
    fun hello(): String {
        return clock.instant().toString()
    }
}