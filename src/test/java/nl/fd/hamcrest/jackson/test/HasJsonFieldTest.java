package nl.fd.hamcrest.jackson.test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import nl.fd.hamcrest.jackson.HasJsonField;
import org.hamcrest.Matcher;
import org.hamcrest.StringDescription;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 */
public class HasJsonFieldTest {

    private ObjectNode book;
    private JsonNodeFactory jsonNodeFactory;

    @Before
    public void setup() {
        jsonNodeFactory = JsonNodeFactory.instance;
        book = jsonNodeFactory.objectNode();
        book.put("title", "Romeo and Juliette");
        ObjectNode author = jsonNodeFactory.objectNode();
        author.put("name", "William Shakespeare");
        author.put("dateOfBirth", "1564-04-23");
        book.put("author", author);
        book.put("publicationYear", 1960);
        book.put("paperback", true);
    }

    @Test
    public void testMatches() throws IOException {
        // Given
        HasJsonField hasJsonFieldMatcher = new HasJsonField("title", is(jsonNodeFactory.textNode("Romeo and Juliette")));

        // When
        boolean matches = hasJsonFieldMatcher.matches(book);

        // Then
        assertTrue(matches);
    }

    @Test
    public void testMatches_noMatch() throws IOException {
        // Given
        HasJsonField hasJsonFieldMatcher = new HasJsonField("title", is(jsonNodeFactory.textNode("Hamlet")));

        // When
        boolean matches = hasJsonFieldMatcher.matches(book);

        // Then
        assertFalse(matches);
    }

    @Test
    public void testMatches_noField() throws IOException {
        // Given
        HasJsonField hasJsonFieldMatcher = new HasJsonField("publisher", is(jsonNodeFactory.textNode("Simon & Brown")));

        // When
        boolean matches = hasJsonFieldMatcher.matches(book);

        // Then
        assertFalse(matches);
    }

    @Test
    public void testDescribe() throws IOException {
        // Given
        HasJsonField hasJsonFieldMatcher = new HasJsonField("publisher", is(jsonNodeFactory.textNode("Simon & Brown")));

        // When
        StringDescription description = new StringDescription();
        hasJsonFieldMatcher.describeTo(description);

        // Then
        assertEquals("hasJsonField(\"publisher\")", description.toString());
    }

    @Test
    public void testHasJsonField_int() {
        // Given

        // When
        Matcher<JsonNode> matcher = HasJsonField.hasJsonField("publicationYear", 1960);

        // Then
        assertThat(book, matcher);
    }

    @Test
    public void testHasJsonField_String() {
        // Given

        // When
        Matcher<JsonNode> matcher = HasJsonField.hasJsonField("title", "Romeo and Juliette");

        // Then
        assertThat(book, matcher);
    }

    @Test
    public void testHasJsonField_boolean() {
        // Given

        // When
        Matcher<JsonNode> matcher = HasJsonField.hasJsonField("paperback", true);

        // Then
        assertThat(book, matcher);
    }

    @Test
    public void testHasJsonField_matchers() {
        // Given

        // When
        Matcher<JsonNode> matcher = HasJsonField.hasJsonField("author", HasJsonField.hasJsonField("name", "William Shakespeare"), HasJsonField.hasJsonField("dateOfBirth", "1564-04-23"));

        // Then
        assertThat(book, matcher);
    }
}
