package com.learning.api.infrastructure.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import springfox.documentation.swagger2.annotations.EnableSwagger2

@Configuration
@EnableSwagger2
@EnableWebMvc
class SwaggerConfig : WebMvcConfigurer {

    @Override
    override fun addViewControllers(registry: ViewControllerRegistry) {
        registry.addRedirectViewController("/api/v2/api-docs", "/v2/api-docs");
        registry.addRedirectViewController(
            "/api/swagger-resources/configuration/ui", "/swagger-resources/configuration/ui"
        );
        registry.addRedirectViewController(
            "/api/swagger-resources/configuration/security",
            "/swagger-resources/configuration/security"
        );
        registry.addRedirectViewController("/api/swagger-resources", "/swagger-resources");
    }

    @Override
    override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
        registry
            .addResourceHandler("/swagger-ui.html**")
            .addResourceLocations("classpath:/META-INF/resources/swagger-ui.html");
        registry
            .addResourceHandler("/webjars/**")
            .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}