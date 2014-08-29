package nl.fd.hamcrest.jackson.test;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import nl.fd.hamcrest.jackson.IsJsonArray;
import org.hamcrest.StringDescription;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 */
public class IsJsonArrayTest {

    private JsonNodeFactory jsonNodeFactory;

    @Before
    public void setup() {
        jsonNodeFactory = JsonNodeFactory.instance;
    }

    @Test
    public void testMatches() {
        // Given
        ArrayNode arrayNode = jsonNodeFactory.arrayNode().add(1).add(3).add(5);
        IsJsonArray matcher = new IsJsonArray(1, 3, 5);

        // When
        boolean matches = matcher.matches(arrayNode);

        // Then
        assertTrue(matches);
    }

    @Test
    public void testMatches_noMatch() {
        // Given
        ArrayNode arrayNode = jsonNodeFactory.arrayNode().add(1).add(3).add(5);
        IsJsonArray matcher = new IsJsonArray(1, 3, 5, 7);

        // When
        boolean matches = matcher.matches(arrayNode);

        // Then
        assertFalse(matches);
    }

    @Test
    public void testDescribeTo() {
        // Given
        IsJsonArray matcher = new IsJsonArray(1, "3", false);
        StringDescription description = new StringDescription();

        // When
        matcher.describeTo(description);

        // Then
        assertEquals("isJsonArray(<[isJsonInt(1), isJsonText(\"3\"), isJsonBoolean(false)]>)", description.toString());
    }

}
