package com.epam;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class MailServerTest {
    MailServer mailServer;

    @Before
    public void before() {
        mailServer = mock(MailServer.class);
    }

    @Test
    public void testSending() {
        mailServer.send("a@gmail.com, b@gmail.com", "Hello B!");
        verify(mailServer, times(1)).send("a@gmail.com, b@gmail.com", "Hello B!");
    }
}
