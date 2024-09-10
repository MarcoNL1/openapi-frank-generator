package nl.wearefrank.openapifrankadapter;

import io.swagger.parser.OpenAPIParser;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.parser.core.models.SwaggerParseResult;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;

public class XmlGeneratorTest {

    @Test
    void testXmlGeneratorReceiver() {
        testXmlGenerator(Option.RECEIVER, "<Receiver");
    }

    @Test
    void testXmlGeneratorSender() {
        testXmlGenerator(Option.SENDER, "<Sender");
    }

    private void testXmlGenerator(Option templateOption, String expectedContent) {
        InputStream inputStream = TestGetElementAttribute.class.getResourceAsStream("/TestingOASFile/openapi.json");
        String source;
        try {
            assert inputStream != null;
            source = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read the input stream", e);
        }

        SwaggerParseResult result = new OpenAPIParser().readContents(source, null, null);
        OpenAPI openAPI = result.getOpenAPI();

        try {
            LinkedList<GenFiles> genFiles = XMLGenerator.execute(openAPI, templateOption);

            genFiles.stream()
                    .filter(genFile -> genFile.getName().endsWith(".xml"))
                    .forEach(genFile -> {
                        String fileContents = new String(genFile.getContent());
                        Assertions.assertTrue(fileContents.contains(expectedContent));
                    });
        } catch (Exception e) {
            Assertions.fail("Exception occurred during test: " + e.getMessage());
        }
    }
}