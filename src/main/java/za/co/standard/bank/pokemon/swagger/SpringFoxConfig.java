package za.co.standard.bank.pokemon.swagger;

import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SpringFoxConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
            .apiInfo(buildAppInfo())
            .select()
            .apis(RequestHandlerSelectors.any())
            .paths(PathSelectors.any())
            .build();
    }

    private ApiInfo buildAppInfo() {
        List<VendorExtension> vendorExtensionList = new ArrayList<>();
        Contact contact = new Contact("Christoph Sibiya", "N/A", "venussibiya@gmail.com");
        ApiInfo apiInfo = new ApiInfo(
            "Standard bank pokemon",
            "Java Backend Engineering challenge (Pokemon API)",
            "v1",
            "N/A", contact,
            "License: N/A",
            "License Url: N/A",
            vendorExtensionList);
        return apiInfo;
    }

}