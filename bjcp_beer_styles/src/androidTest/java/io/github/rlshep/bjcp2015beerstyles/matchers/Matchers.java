package io.github.rlshep.bjcp2015beerstyles.matchers;

import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.apache.commons.lang.StringUtils;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import io.github.rlshep.bjcp2015beerstyles.domain.Category;

public class Matchers {
    public static Matcher<View> hasValueEqualTo(final String content) {

        return new TypeSafeMatcher<View>() {

            @Override
            public void describeTo(Description description) {
                description.appendText("Has EditText/TextView the value:  " + content);
            }

            @Override
            public boolean matchesSafely(View view) {
                boolean matches = false;

                if (!(view instanceof TextView) && !(view instanceof EditText)) {
                    matches = false;
                }
                if (view != null) {
                    String text;
                    if (view instanceof TextView) {
                        text = ((TextView) view).getText().toString();
                    } else {
                        text = ((EditText) view).getText().toString();
                    }

                    matches = StringUtils.containsIgnoreCase(text, content);
                }
                return matches;
            }
        };
    }

    public static Matcher<View> hasListViewEqualTo(final String expected, final int i) {

        return new TypeSafeMatcher<View>() {

            @Override
            public void describeTo(Description description) {
                description.appendText("Has ListView the value:  " + expected);
            }

            @Override
            public boolean matchesSafely(View view) {
                boolean matches = false;

                if (view != null && view instanceof ListView) {
                    Category category = (Category) ((ListView) view).getItemAtPosition(i);

                    matches = StringUtils.equals(expected, category.getName().trim());
                }

                return matches;
            }
        };
    }

    public static Matcher<View> hasCountEqualTo(final int expected) {

        return new TypeSafeMatcher<View>() {

            @Override
            public void describeTo(Description description) {
                description.appendText("Has ListView the count of:  " + expected);
            }

            @Override
            public boolean matchesSafely(View view) {
                boolean matches = false;

                if (view != null && view instanceof ListView) {
                    matches = (expected == ((ListView) view).getCount());
                }

                return matches;
            }
        };
    }
}
