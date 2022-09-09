package com.epam.template;

import com.epam.Client;
import com.epam.Reader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.mock;

public class TemplateTest {
    TemplateEngine templateEngine;
    Reader reader;

    String FILE_NAME_TEMP_FOR_EXTRA_TAG = "testFile2";
    String FILE_NAME_TEMP_WITHOUT_ONE_PLACEHOLDER = "testFile1";
    String FILE_NAME = "byeBye";
    String SUBJECT = "I am subject";
    String BODY = "I am body";
    String TEMPLATE = "#{subject}\\n\\n\\n#{body}\\n\\nBye bye!";
    String TEMPLATE_WITHOUT_ONE_PLACEHOLDER = "#{subject}\\n\\n\\n#{tag}\\n\\nBye bye!";
    Client client = new Client("a@gmail.com");


    @BeforeEach
    void initialize() throws IOException {
        templateEngine = new TemplateEngine();
        reader = mock(Reader.class);
        Path of = Path.of("src\\main\\resources\\temp\\" + FILE_NAME_TEMP_WITHOUT_ONE_PLACEHOLDER + ".txt");
        Path absolutePath1 = of.toAbsolutePath();
        List<String> mail1 = List.of(TEMPLATE_WITHOUT_ONE_PLACEHOLDER);
        Files.write(absolutePath1, mail1);
        Path of1 = Path.of("src\\main\\resources\\temp\\" + FILE_NAME_TEMP_FOR_EXTRA_TAG + ".txt");
        Path absolutePath2 = of1.toAbsolutePath();
        List<String> mail2 = List.of(TEMPLATE);
        Files.write(absolutePath2, mail2);
    }

    @ParameterizedTest
    @ValueSource(strings = {"I am subject1", "I am subject2", "I am subject3"})
    void testSystemReplacesVariablePlaceholdersFromATemplateWithValuesMultipleSubjects(String subject) {
        String message = templateEngine.generateMessage(new Template(FILE_NAME, subject, BODY), client);

        assertFalse(message.contains("#{body}") && message.contains("#{subject}"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"I am body1", "I am body2", "I am body3"})
    void testSystemReplacesVariablePlaceholdersFromATemplateWithValuesMultipleBody(String body) {
        String message = templateEngine.generateMessage(new Template(FILE_NAME, SUBJECT, body), client);

        assertFalse(message.contains("#{body}") && message.contains("#{subject}"));
    }
}
