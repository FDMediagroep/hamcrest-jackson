package nl.fd.hamcrest.jackson;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.NullNode;
import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.TypeSafeMatcher;

import static org.hamcrest.Matchers.is;

/**
 * Matcher for checking if an object is a JSON integer.
 *
 * @param <T> Type that extends JsonNode
 */
public class IsJsonText<T extends JsonNode> extends TypeSafeMatcher<JsonNode> {

    private String text;

    public IsJsonText(String text) {
        this.text = text;
    }

    @Override
    protected boolean matchesSafely(JsonNode item) {
        if (item instanceof NullNode && text == null) {
            return true;
        }
        String actualText = item.asText();
        return is(text).matches(actualText);
    }

    @Override
    public void describeMismatchSafely(JsonNode item, Description mismatchDescription) {
        String actualText = item.asText();
        is(text).describeMismatch(actualText, mismatchDescription);
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("isJsonText(").appendValue(text).appendText(")");
    }

    @Factory
    public static <T extends JsonNode> IsJsonText<T> isJsonText(String text) {
        return new IsJsonText<T>(text);
    }

}
