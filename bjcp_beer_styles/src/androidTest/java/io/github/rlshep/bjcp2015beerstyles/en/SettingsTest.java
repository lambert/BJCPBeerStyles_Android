package io.github.rlshep.bjcp2015beerstyles.en;

import androidx.test.espresso.Espresso;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.github.rlshep.bjcp2015beerstyles.BJCPTest;
import io.github.rlshep.bjcp2015beerstyles.MainActivity;
import io.github.rlshep.bjcp2015beerstyles.R;
import io.github.rlshep.bjcp2015beerstyles.matchers.Matchers;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static io.github.rlshep.bjcp2015beerstyles.constants.BjcpConstants.BJCP_2015;
import static io.github.rlshep.bjcp2015beerstyles.constants.BjcpConstants.GUIDELINE_MAP;
import static io.github.rlshep.bjcp2015beerstyles.constants.BjcpConstants.getKeyValue;
import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasToString;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class SettingsTest extends BJCPTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule(MainActivity.class);

    @Before
    public void reset_settings() {
        setGuideline(getKeyValue(GUIDELINE_MAP, BJCP_2015));
        setLanguage("English");
        setSrmSgAbv(true, true, true);
    }

    @Test
    public void settings_gravity() {
        onView(withId(R.id.action_settings)).perform(click());
        onView(withId(R.id.settingGravity)).check(matches(Matchers.hasValueEqualTo("Gravity:")));

        // Switch to Plato
        onView(withId(R.id.settings_plato)).perform(click());
        Espresso.pressBack();

        onData(anything()).inAdapterView(withId(R.id.categoryListView)).atPosition(25).perform(click());
        onData(anything()).inAdapterView(withId(R.id.categoryListView)).atPosition(2).perform(click());
        onView(withId(R.id.sectionsText)).check(matches(Matchers.hasValueEqualTo("11.9°P")));

        // Back to main
        Espresso.pressBack();
        Espresso.pressBack();
        onView(withId(R.id.action_settings)).perform(click());
        onView(withId(R.id.settingGravity)).check(matches(Matchers.hasValueEqualTo("Gravity:")));

        // Switch back to Specific Gravity
        onView(withId(R.id.settings_specific_gravity)).perform(click());
        Espresso.pressBack();
        onData(anything()).inAdapterView(withId(R.id.categoryListView)).atPosition(25).perform(click());
        onData(anything()).inAdapterView(withId(R.id.categoryListView)).atPosition(2).perform(click());
        onView(withId(R.id.sectionsText)).check(matches(Matchers.hasValueEqualTo("1.048")));
    }

    @Test
    public void settings_color() {
        onView(withId(R.id.action_settings)).perform(click());
        onView(withId(R.id.settingColor)).check(matches(Matchers.hasValueEqualTo("Color:")));

        // Switch to EBC
        onView(withId(R.id.settings_ebc)).perform(click());
        Espresso.pressBack();

        onData(anything()).inAdapterView(withId(R.id.categoryListView)).atPosition(25).perform(click());
        onData(anything()).inAdapterView(withId(R.id.categoryListView)).atPosition(2).perform(click());
        onView(withId(R.id.srmText1)).check(matches(Matchers.hasValueEqualTo("30.0")));

        // Back to main
        Espresso.pressBack();
        Espresso.pressBack();
        onView(withId(R.id.action_settings)).perform(click());
        onView(withId(R.id.settingColor)).check(matches(Matchers.hasValueEqualTo("Color:")));

        // Switch back to SRM
        onView(withId(R.id.settings_srm)).perform(click());
        Espresso.pressBack();
        onData(anything()).inAdapterView(withId(R.id.categoryListView)).atPosition(25).perform(click());
        onData(anything()).inAdapterView(withId(R.id.categoryListView)).atPosition(2).perform(click());
        onView(withId(R.id.srmText1)).check(matches(Matchers.hasValueEqualTo("22.0")));
    }

    @Test
    public void settings_mix() {
        onView(withId(R.id.action_settings)).perform(click());

        // Switch to Plato
        onView(withId(R.id.settings_plato)).perform(click());
        onView(withId(R.id.settings_srm)).perform(click());
        Espresso.pressBack();

        onData(anything()).inAdapterView(withId(R.id.categoryListView)).atPosition(25).perform(click());
        onData(anything()).inAdapterView(withId(R.id.categoryListView)).atPosition(2).perform(click());
        onView(withId(R.id.sectionsText)).check(matches(Matchers.hasValueEqualTo("11.9°P")));
        onView(withId(R.id.srmText1)).check(matches(Matchers.hasValueEqualTo("22.0")));
    }

    @Test
    public void settings_spanish() {
        onView(withId(R.id.action_settings)).perform(click());
        onView(withId(R.id.settingLanguageName)).check(matches(Matchers.hasValueEqualTo("Language:")));
        onView(withId(R.id.settings_language)).perform(click());
        onData(hasToString("Español")).perform(click());
        onView(withId(R.id.settings_language)).check(matches(withSpinnerText(containsString("Español"))));
        Espresso.pressBack();
        onView(withId(R.id.categoryListView)).check(matches(Matchers.hasListViewEqualTo("Introducción", 0)));
    }

    @Test
    public void settings_ukranian() {
        onView(withId(R.id.action_settings)).perform(click());
        onView(withId(R.id.settingLanguageName)).check(matches(Matchers.hasValueEqualTo("Language:")));
        onView(withId(R.id.settings_language)).perform(click());
        onData(hasToString("Українська")).perform(click());
        onView(withId(R.id.settings_language)).check(matches(withSpinnerText(containsString("Українська"))));
        Espresso.pressBack();
        onView(withId(R.id.categoryListView)).check(matches(Matchers.hasListViewEqualTo("Вступ", 0)));
    }
}
