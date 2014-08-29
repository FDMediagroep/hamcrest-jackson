package nl.fd.hamcrest.jackson;

import com.fasterxml.jackson.databind.JsonNode;
import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.TypeSafeMatcher;

import static org.hamcrest.Matchers.is;

/**
 * Matcher for checking if an object is a JSON integer.
 */
public class IsJsonInt extends TypeSafeMatcher<JsonNode> {

    private int value;

    public IsJsonInt(int value) {
        this.value = value;
    }

    @Override
    protected boolean matchesSafely(JsonNode item) {
        int actualValue = item.asInt();
        return is(value).matches(actualValue);
    }

    @Override
    public void describeMismatchSafely(JsonNode item, Description mismatchDescription) {
        int actualValue = item.asInt();
        is(value).describeMismatch(actualValue, mismatchDescription);
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("isJsonInt(").appendText(String.valueOf(value)).appendText(")");
    }

    @Factory
    public static IsJsonInt isJsonInt(int value) {
        return new IsJsonInt(value);
    }

}
