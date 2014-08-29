package nl.fd.hamcrest.jackson.test;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ValueNode;
import nl.fd.hamcrest.jackson.IsJsonInt;
import org.hamcrest.StringDescription;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 */
public class IsJsonIntTest {

    private JsonNodeFactory jsonNodeFactory;

    @Before
    public void setup() {
        jsonNodeFactory = JsonNodeFactory.instance;
    }

    @Test
    public void testMatches() {
        // Given
        ValueNode valueNode = jsonNodeFactory.numberNode(Integer.valueOf(10));
        IsJsonInt matcher = new IsJsonInt(10);

        // When
        boolean matches = matcher.matches(valueNode);

        // Then
        assertTrue(matches);
    }

    @Test
    public void testMatches_false() {
        // Given
        ValueNode valueNode = jsonNodeFactory.numberNode(Integer.valueOf(20));
        IsJsonInt matcher = new IsJsonInt(10);

        // When
        boolean matches = matcher.matches(valueNode);

        // Then
        assertFalse(matches);
    }

    @Test
    public void testDescribeTo() {
        // Given
        IsJsonInt matcher = new IsJsonInt(10);
        StringDescription description = new StringDescription();

        // When
        matcher.describeTo(description);

        // Then
        assertEquals("isJsonInt(10)", description.toString());
    }

    @Test
    public void testDescribeMismatch() {
        // Given
        IsJsonInt matcher = new IsJsonInt(10);
        StringDescription description = new StringDescription();

        // When
        matcher.describeMismatch(20, description);

        // Then
        assertEquals("was a java.lang.Integer (<20>)", description.toString());
    }
}
