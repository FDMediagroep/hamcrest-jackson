package nl.fd.hamcrest.jackson;

import com.fasterxml.jackson.databind.JsonNode;
import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;

import static org.hamcrest.CoreMatchers.allOf;

/**
 * Matcher for checking if an object has a JSON field.
 */
public class HasJsonField extends TypeSafeDiagnosingMatcher<JsonNode> {

    private String fieldName;
    private Matcher<?> valueMatcher;

    public HasJsonField(String fieldName, Matcher<?> valueMatcher) {
        this.fieldName = fieldName;
        this.valueMatcher = valueMatcher;
    }

    @Override
    protected boolean matchesSafely(JsonNode item, Description mismatchDescription) {
        JsonNode jsonNode = item.get(fieldName);
        if (jsonNode == null) {
            mismatchDescription.appendText("no ").appendText(fieldName).appendText(" found");
            return false;
        }
        if (!valueMatcher.matches(jsonNode)) {
            mismatchDescription.appendText("value of field ").appendText(fieldName).appendText(" did not match ").appendValue(valueMatcher).appendText(":\n");
            valueMatcher.describeMismatch(jsonNode, mismatchDescription);
            return false;
        }
        return true;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("hasJsonField(").appendValue(fieldName).appendText(")");
    }

    @Factory
    public static <T extends JsonNode> HasJsonField hasJsonField(String property, int value) {
        return new HasJsonField(property, IsJsonInt.isJsonInt(value));
    }

    @Factory
    public static <T extends JsonNode> HasJsonField hasJsonField(String property, boolean value) {
        return new HasJsonField(property, IsJsonBoolean.isJsonBoolean(value));
    }

    @Factory
    public static <T extends JsonNode> HasJsonField hasJsonField(String property, String value) {
        return new HasJsonField(property, IsJsonText.isJsonText(value));
    }

    @Factory
    @SuppressWarnings("unchecked")
    public static <T extends JsonNode> HasJsonField hasJsonField(String property, Matcher<?>... valueMatchers) {
        return new HasJsonField(property, allOf((Matcher<? super Object>[]) valueMatchers));
    }

}
