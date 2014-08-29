package nl.fd.hamcrest.jackson;

import com.fasterxml.jackson.databind.JsonNode;
import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.TypeSafeMatcher;

import java.util.ArrayList;
import java.util.List;

import static nl.fd.hamcrest.jackson.IsJsonBoolean.isJsonBoolean;
import static nl.fd.hamcrest.jackson.IsJsonInt.isJsonInt;
import static nl.fd.hamcrest.jackson.IsJsonText.isJsonText;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;

/**
 * Matcher for checking if an object is a JSON array.
 *
 * @param <T> Type that extends JsonNode
 */
public class IsJsonArray<T extends JsonNode> extends TypeSafeMatcher<JsonNode> {

    private List<Matcher<? super JsonNode>> matchers = new ArrayList<>();

    @SuppressWarnings("unchecked")
    public IsJsonArray(Object... array) {
        for (Object o : array) {
            if (o instanceof Matcher) {
                matchers.add((Matcher<? super JsonNode>) o);
            } else if (o instanceof Integer) {
                matchers.add(isJsonInt((Integer) o));
            } else if (o instanceof Boolean) {
                matchers.add(isJsonBoolean((Boolean) o));
            } else if (o instanceof String) {
                matchers.add(isJsonText((String) o));
            } else {
                throw new IllegalArgumentException("Cannot create Json field matcher for type " + o.getClass());
            }
        }
    }

    @Override
    protected boolean matchesSafely(JsonNode item) {
        if (! (item.isArray())) {
            return false;
        }
        List<JsonNode> list = jsonArrayToList(item);
        if (matchers.size() == 0) {
            return Matchers.emptyIterable().matches(list);
        }
        return contains(matchers).matches(list);
    }

    @Override
    protected void describeMismatchSafely(JsonNode item, Description mismatchDescription) {
        if (!(item.isArray())) {
            mismatchDescription.appendValue(item).appendText(" is not an array");
        }
        List<JsonNode> list = jsonArrayToList(item);
        if (matchers.size() == 0) {
            Matchers.emptyIterable().describeMismatch(list, mismatchDescription);
        } else {
            contains(matchers).describeMismatch(list, mismatchDescription);
        }
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("isJsonArray(").appendValue(matchers).appendText(")");
    }

    @Factory
    public static <T extends JsonNode> IsJsonArray<T> isJsonArray(Object... array) {
        return new IsJsonArray<T>(array);
    }

    private List<JsonNode> jsonArrayToList(JsonNode item) {
        List<JsonNode> list = new ArrayList<>(item.size());
        for (JsonNode anItem : item) {
            list.add(anItem);
        }
        return list;
    }

}
