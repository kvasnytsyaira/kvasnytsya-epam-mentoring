package com.epam.template;

import com.epam.Client;
import com.epam.PlaceholderNotProvidedException;
import com.epam.Reader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TemplateEngineTest {

    TemplateEngine templateEngine;
    Reader reader;


    Path templateFileWithoutOnePlaceholder;
    Path templateFileWithExtraTag;
    String FILE_NAME_TEMP_FOR_EXTRA_TAG = "testFile2";
    String FILE_NAME_TEMP_WITHOUT_ONE_PLACEHOLDER = "testFile1";
    String FILE_NAME = "byeBye";

    String SUBJECT = "I am subject";
    String BODY = "I am body";
    String BODY_WITH_EXTRA_VALUE = "I am body\\n#{tag}";

    String TEMPLATE = "#{subject}\\n\\n\\n#{body}\\n\\nBye bye!";
    String TEMPLATE_WITHOUT_ONE_PLACEHOLDER = "#{subject}\\n\\n\\n#{tag}\\n\\nBye bye!";
    String EMAIL = "I am subject\n\n\nI am body\n\nBye bye!\\n\\n";
    String EMAIL_WITH_VALUE_NOT_FROM_TEMPLATE = "I am subject\\n\\n\\nI am body\\n#{tag}\\n\\nBye bye!\n";

    Client client = new Client("a@gmail.com");

    @TempDir
    public static Path tempDir;

    @BeforeEach
    void initialize() throws IOException {
        templateEngine = new TemplateEngine();
        reader = mock(Reader.class);

        templateFileWithoutOnePlaceholder = tempDir.resolve(FILE_NAME_TEMP_WITHOUT_ONE_PLACEHOLDER + ".txt");
        List<String> mail1 = List.of(TEMPLATE_WITHOUT_ONE_PLACEHOLDER);
        Files.write(templateFileWithoutOnePlaceholder, mail1);

        templateFileWithExtraTag = tempDir.resolve(FILE_NAME_TEMP_FOR_EXTRA_TAG + ".txt");
        List<String> mail2 = List.of(TEMPLATE);
        Files.write(templateFileWithExtraTag, mail2);
    }

    @Test
    void testSystemReplacesVariablePlaceholdersFromATemplateWithValues() {
        String message = templateEngine.generateMessage(new Template(FILE_NAME, SUBJECT, BODY), client);

        assertFalse(message.contains("#{body}") && message.contains("#{subject}"));

    }

    @Test
    void testSystemThrowAnExceptionIfAtLeastOnePlaceholderIsNotProvided() {
        when(reader.readMailFromFile(String.valueOf(templateFileWithoutOnePlaceholder)))
                .thenReturn(TEMPLATE_WITHOUT_ONE_PLACEHOLDER);

        assertThrows(PlaceholderNotProvidedException.class,
                () -> templateEngine.generateMessage(
                        new Template(FILE_NAME_TEMP_WITHOUT_ONE_PLACEHOLDER, SUBJECT, ""),
                        client));
    }

    @Test
    void testSystemIgnoresValuesWhichAreNotFromTheTemplate() {
        String s = templateEngine.generateMessage(
                new Template(FILE_NAME_TEMP_FOR_EXTRA_TAG, SUBJECT, BODY_WITH_EXTRA_VALUE), client);

        assertEquals(EMAIL_WITH_VALUE_NOT_FROM_TEMPLATE, s);
//        List.of() vs
//        Arrays.asList();
    }


    @Test
    void testSystemPassesValuesInTemplateThatConsistOfTagAndBrackets() {
        String s = templateEngine.generateMessage(
                new Template(FILE_NAME, SUBJECT, BODY), client);

        assertEquals("I am subject\n\n\nI am body\n\nBye bye!\n", s);
    }

    @Test
    void testSystemSupportsLatin1CharSet() {
//not finished
    }

}
