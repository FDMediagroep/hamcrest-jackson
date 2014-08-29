package nl.fd.hamcrest.jackson.test;

import com.fasterxml.jackson.databind.node.BooleanNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import nl.fd.hamcrest.jackson.IsJsonBoolean;
import org.hamcrest.StringDescription;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 */
public class IsJsonBooleanTest {

    private JsonNodeFactory jsonNodeFactory;

    @Before
    public void setup() {
        jsonNodeFactory = JsonNodeFactory.instance;
    }

    @Test
    public void testMatches() {
        // Given
        BooleanNode booleanNode = jsonNodeFactory.booleanNode(true);
        IsJsonBoolean matcher = new IsJsonBoolean(true);

        // When
        boolean matches = matcher.matches(booleanNode);

        // Then
        assertTrue(matches);
    }

    @Test
    public void testMatches_false() {
        // Given
        BooleanNode booleanNode = jsonNodeFactory.booleanNode(false);
        IsJsonBoolean matcher = new IsJsonBoolean(true);

        // When
        boolean matches = matcher.matches(booleanNode);

        // Then
        assertFalse(matches);
    }

    @Test
    public void testDescribeTo() {
        // Given
        IsJsonBoolean matcher = new IsJsonBoolean(false);
        StringDescription description = new StringDescription();

        // When
        matcher.describeTo(description);

        // Then
        assertEquals("isJsonBoolean(false)", description.toString());
    }

    @Test
    public void testDescribeMismatch() {
        // Given
        IsJsonBoolean matcher = new IsJsonBoolean(true);
        StringDescription description = new StringDescription();
        BooleanNode booleanNode = jsonNodeFactory.booleanNode(false);

        // When
        matcher.describeMismatch(booleanNode, description);

        // Then
        assertEquals("was \"false\"", description.toString());
    }

}
