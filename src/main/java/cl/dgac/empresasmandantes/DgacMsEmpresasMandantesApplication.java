package cl.dgac.empresasmandantes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class DgacMsEmpresasMandantesApplication {

    public static void main(String[] args) {
        SpringApplication.run(DgacMsEmpresasMandantesApplication.class, args);
    }

}