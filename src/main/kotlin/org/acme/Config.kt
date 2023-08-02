package org.acme

import jakarta.enterprise.context.ApplicationScoped
import java.time.Clock

class Config {

    @ApplicationScoped
    fun clock(): Clock {
        return Clock.systemUTC()
    }
}