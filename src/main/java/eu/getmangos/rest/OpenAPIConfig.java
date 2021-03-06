package eu.getmangos.rest;

import javax.ws.rs.ApplicationPath;

import org.eclipse.microprofile.openapi.annotations.ExternalDocumentation;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.info.License;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@ApplicationPath("/")
@OpenAPIDefinition(
    tags = {
        @Tag(name = "realm", description="Operations about realms"),
        @Tag(name = "link", description="Manages links between accounts and realms"),
        @Tag(name = "status", description="Operations about realms status")
    },
    externalDocs = @ExternalDocumentation(
        description = "Instructions on how to deploy this WebApp",
        url = "https://github.com/Warkdev/realm-service/blob/master/README.md"
    ),
    info = @Info(
            title = "Mangos Realms API",
            version = "1.0",
            description = "API allowing to interact with the Mangos Realms information",
            license = @License(
                    name = "Apache 2.0"
            )
    )
)
public class OpenAPIConfig {
}
