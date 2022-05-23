package com.epam.template;

import com.epam.Client;
import org.junit.Test;

import static org.junit.Assert.*;

public class TemplateEngineTest {

    @Test
    public void testSystemReplacesVariablePlaceholdersFromATemplateWithValues() throws Exception {
        TemplateEngine templateEngine = new TemplateEngine();
        String message = templateEngine.generateMessage(new Template("byeBye", "HelloWorld", "I am body"), new Client("ira"));

        assertFalse(message.contains("#{body}") && message.contains("#{subject}"));

    }

    @Test(expected = Exception.class)
    public void testSystemThrowAnExceptionIfAtLeastOnePlaceholderIsNotProvided() throws Exception {
        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.generateMessage(new Template("bestRegards", "I am subject", ""), new Client("h"));
    }

    @Test
    public void testSystemIgnoresValuesWhichAreNotFromTheTemplate() throws Exception {
        TemplateEngine templateEngine = new TemplateEngine();
        String s = templateEngine.generateMessage(new Template("testFile", "I am subject", "I am body"), new Client("j"));

        assertEquals("I am subject\n\n\nI am body\n#{tag}\n\nBye bye!\n", s);
    }

    @Test
    public void testSystemPassesValuesInTemplateThatConsistOfTagAndBrackets() throws Exception {
        TemplateEngine templateEngine = new TemplateEngine();
        String s = templateEngine.generateMessage(new Template("seeYouSoon", "I am subject", "I am body"), new Client("p"));

        assertEquals("I am subject\n\n\nI am body\n\nSee you soon!\n", s);
    }

    @Test
    public void testSystemSupportsLatin1CharSet() {

    }

}
