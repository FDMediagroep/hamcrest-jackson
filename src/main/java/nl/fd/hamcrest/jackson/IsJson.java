package nl.fd.hamcrest.jackson;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;

import java.io.IOException;


/**
 * @author Frans Flippo
 */
public class IsJson extends TypeSafeDiagnosingMatcher<String> {

    private final Matcher<? extends JsonNode> matcher;

    public IsJson(Matcher<? extends JsonNode> matcher) {
        this.matcher = matcher;
    }

    @Override
    protected boolean matchesSafely(String item, Description mismatchDescription) {
        try {
            JsonNode jsonNode = new ObjectMapper().readTree(item);
            if (matcher.matches(jsonNode)) {
                return true;
            } else {
                matcher.describeMismatch(jsonNode, mismatchDescription);
                return false;
            }
        } catch (IOException e) {
            mismatchDescription.appendText("Invalid JSON");
            return false;
        }
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("is JSON matching ").appendDescriptionOf(matcher);
    }

    public static IsJson isJson(Matcher<? extends JsonNode> valueMatcher) {
        return new IsJson(valueMatcher);
    }
}
