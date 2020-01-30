package io.codebrews.wiremockdemo

import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.core.WireMockConfiguration
import org.springframework.boot.test.util.TestPropertyValues
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.context.event.ContextClosedEvent

class WireMockContextInitializer : ApplicationContextInitializer<ConfigurableApplicationContext> {
    override fun initialize(applicationContext: ConfigurableApplicationContext) {

        val wireMockServer = WireMockServer(WireMockConfiguration().dynamicPort())
        wireMockServer.start()

        applicationContext.beanFactory.registerSingleton("wireMockServer", wireMockServer)

        applicationContext.addApplicationListener { applicationEvent ->
            if (applicationEvent is ContextClosedEvent) {
                wireMockServer.stop()
            }
        }

        TestPropertyValues
            .of("app.openweather.baseurl=http://localhost:${wireMockServer.port()}")
            .applyTo(applicationContext)
    }
}
