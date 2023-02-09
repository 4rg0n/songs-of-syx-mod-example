package jake.example;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

/**
 * I guess you can do some basic unit testing of your code when you mock the required game dependencies.
 * But running a test against the game itself might be tricky.
 */
public class ExampleTest {
    @Test
    void iDoNothing() {
        // there's Assertj for assertions
        Assertions.assertThat("text").isEqualTo("text");

        // and there's Mockito for mocking
        IAmUseless iAmUselessMock = Mockito.mock(IAmUseless.class);
    }

    static class IAmUseless {

    }
}
