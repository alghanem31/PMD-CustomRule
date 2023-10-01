package com.example.pocpmd.pmd;

import net.sourceforge.pmd.lang.java.ast.ASTMethodDeclaration;
import net.sourceforge.pmd.lang.java.rule.AbstractJavaRule;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.regex.Pattern;


@SuppressWarnings("PMD")
public class ApplicationDotYaml extends AbstractJavaRule {

    private static final String YAML_FILE_PATH = "/Users/alghanem/Documents/Yousef Repository/POC-Pmd/src/main/resources/application.yml";

    @Override
    public Object visit(final ASTMethodDeclaration node, final Object data) {


        try {
            Map<String, Object> yamlData = new Yaml().load(Files.newInputStream(Paths.get(YAML_FILE_PATH)));
            System.out.println("validateSpringApplicationName:" + validateSpringApplicationName(yamlData));
            if (!validateSpringApplicationName(yamlData)) {
                addViolation(data, node);
            }
        } catch (IOException exception) {
            System.out.println("Error while getting file");
        }
        return super.visit(node, data);
    }

    private boolean validateSpringApplicationName(final Map<String, Object> yamlData) {
        if (yamlData == null || !yamlData.containsKey("spring")) {
            return false;
        }

        Map<String, Object> yamlDataSpring = (Map<String, Object>) yamlData.get("spring");
        if (yamlDataSpring.containsKey("application")) {
            Map<String, Object> yamlDataApplication = (Map<String, Object>) yamlDataSpring.get("application");

            if (yamlDataApplication.containsKey("name")) {
                String name = yamlDataApplication.get("name").toString();
                return Pattern.matches("^(ab-).*(-service|-ms)$", name);
            }
        }

        return false;
    }

}
