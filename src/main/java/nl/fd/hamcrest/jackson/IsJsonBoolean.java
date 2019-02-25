package nl.fd.hamcrest.jackson;

import com.fasterxml.jackson.databind.JsonNode;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import static org.hamcrest.Matchers.is;

/**
 * Matcher for checking if an object is a JSON boolean.
 *
 * @param <T> Type that extends JsonNode
 */
public class IsJsonBoolean<T extends JsonNode> extends TypeSafeMatcher<JsonNode> {

    private boolean value;

    public IsJsonBoolean(boolean value) {
        this.value = value;
    }

    @Override
    protected boolean matchesSafely(JsonNode item) {
        boolean actualValue = item.asBoolean();
        return is(value).matches(actualValue);
    }

    @Override
    public void describeMismatchSafely(JsonNode item, Description mismatchDescription) {
        String actualText = item.asText();
        is(value).describeMismatch(actualText, mismatchDescription);
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("isJsonBoolean(").appendText(String.valueOf(value)).appendText(")");
    }

    public static <T extends JsonNode> IsJsonBoolean<T> isJsonBoolean(boolean value) {
        return new IsJsonBoolean<T>(value);
    }

}
