package cl.dgac.empresasmandantes.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    // Lee la URL desde el application.properties
    @Value("${empresas-proveedoras.base-url:http://dgac-ms-empresas-proveedoras}")
    private String proveedoresBaseUrl;

    @Bean
    @LoadBalanced // <--- Fundamental para la resolución dinámica vía Eureka
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }

    @Bean
    public WebClient webClientEmpresasProveedoras(WebClient.Builder builder) {
        // Construye el cliente apuntando al microservicio de proveedores
        return builder.baseUrl(proveedoresBaseUrl).build();
    }
}