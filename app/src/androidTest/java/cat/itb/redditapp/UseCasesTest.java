package cat.itb.redditapp;


import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;
import androidx.test.runner.AndroidJUnit4;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;

import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class UseCasesTest {

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityTestRule = new ActivityScenarioRule<MainActivity>(MainActivity.class);

    @Test
    public void logInAndUploadAPostWithText() throws InterruptedException {
        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.editTextTextPersonName),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.fragment_container),
                                        0),
                                5),
                        isDisplayed()));
        appCompatEditText.perform(replaceText("prueba@test.com"), closeSoftKeyboard());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.editTextTextPassword),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.fragment_container),
                                        0),
                                6),
                        isDisplayed()));
        appCompatEditText2.perform(replaceText("patata123"), closeSoftKeyboard());

        ViewInteraction materialButton = onView(
                allOf(withId(R.id.button3), withText("Continue"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.fragment_container),
                                        0),
                                10),
                        isDisplayed()));
        materialButton.perform(click());
        Thread.sleep(1000);
        ViewInteraction bottomNavigationItemView = onView(
                allOf(withId(R.id.page_3),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.bottom_navigation),
                                        0),
                                2),
                        isDisplayed()));
        bottomNavigationItemView.perform(click());

        ViewInteraction navigationMenuItemView = onView(
                allOf(withId(R.id.text_post),
                        childAtPosition(
                                allOf(withId(R.id.design_navigation_view),
                                        childAtPosition(
                                                withId(R.id.bottom_drawer),
                                                0)),
                                1),
                        isDisplayed()));
        navigationMenuItemView.perform(click());

        ViewInteraction materialTextView = onView(
                allOf(withId(R.id.com_picker), withText("Choose a community"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.fragment_container),
                                        0),
                                4),
                        isDisplayed()));
        materialTextView.perform(click());

        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.recycler_view_community),
                        childAtPosition(
                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                1)));
        recyclerView.perform(actionOnItemAtPosition(0, click()));
        Thread.sleep(2000);

        //RecyclerViewTest
        onView(withId(R.id.com_picker)).check(matches(withText("r/memes")));

        ViewInteraction textInputEditText = onView(withId(R.id.post_title));
        textInputEditText.perform(replaceText("This is a test"), closeSoftKeyboard());

        ViewInteraction textInputEditText2 = onView(withId(R.id.post_optional_text));
        textInputEditText2.perform(replaceText("Yeeees"), closeSoftKeyboard());
        Thread.sleep(1000);
        ViewInteraction materialTextView2 = onView(withId(R.id.post));

        materialTextView2.perform(click());
    }
    @Test
    public void logOutTest() {
        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.editTextTextPersonName),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.fragment_container),
                                        0),
                                5),
                        isDisplayed()));
        appCompatEditText.perform(replaceText("prueba@test.com"), closeSoftKeyboard());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.editTextTextPassword),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.fragment_container),
                                        0),
                                6),
                        isDisplayed()));
        appCompatEditText2.perform(replaceText("patata123"), closeSoftKeyboard());

        ViewInteraction materialButton = onView(
                allOf(withId(R.id.button3), withText("Continue"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.fragment_container),
                                        0),
                                10),
                        isDisplayed()));
        materialButton.perform(click());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ViewInteraction viewGroup = onView(
                allOf(withId(R.id.include2),
                        withParent(allOf(withId(R.id.drawer_layout),
                                withParent(withId(android.R.id.content)))),
                        isDisplayed()));
        viewGroup.check(matches(isDisplayed()));

        ViewInteraction appCompatImageButton = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.include2),
                                childAtPosition(
                                        withId(R.id.top_app_bar),
                                        0)),
                        0),
                        isDisplayed()));
        appCompatImageButton.perform(click());

        ViewInteraction materialButton2 = onView(
                allOf(withId(R.id.footer_item_2), withText("Log Out"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.navigation_view),
                                        1),
                                1),
                        isDisplayed()));
        materialButton2.perform(click());

        ViewInteraction viewGroup2 = onView(
                allOf(withParent(allOf(withId(R.id.fragment_container),
                        withParent(withId(R.id.include3)))),
                        isDisplayed()));
        viewGroup2.check(matches(isDisplayed()));

        ViewInteraction relativeLayout = onView(
                allOf(withId(R.id.include3),
                        withParent(allOf(withId(R.id.include),
                                withParent(withId(R.id.drawer_layout)))),
                        isDisplayed()));
        relativeLayout.check(matches(isDisplayed()));
    }
    @Test
    public void logInAndLikeAPost() {
        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.editTextTextPersonName),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.fragment_container),
                                        0),
                                5),
                        isDisplayed()));
        appCompatEditText.perform(replaceText("prueba@test.com"), closeSoftKeyboard());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.editTextTextPassword),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.fragment_container),
                                        0),
                                6),
                        isDisplayed()));
        appCompatEditText2.perform(replaceText("patata123"), closeSoftKeyboard());

        ViewInteraction materialButton = onView(
                allOf(withId(R.id.button3), withText("Continue"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.fragment_container),
                                        0),
                                10),
                        isDisplayed()));
        materialButton.perform(click());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ViewInteraction appCompatImageView = onView(
                allOf(withId(R.id.upvote),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.recycler_view),
                                        0),
                                5),
                        isDisplayed()));
        appCompatImageView.perform(click());
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
