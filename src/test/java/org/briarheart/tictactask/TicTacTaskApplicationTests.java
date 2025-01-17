package org.briarheart.tictactask;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.autoconfigure.mail.MailSenderAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = MailSenderAutoConfiguration.class)
@ActiveProfiles("test")
public class TicTacTaskApplicationTests {
	@Test
	public void contextLoads() {
	}
}
