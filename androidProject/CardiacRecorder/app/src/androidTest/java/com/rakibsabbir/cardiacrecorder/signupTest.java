package com.rakibsabbir.cardiacrecorder;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.allOf;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.IdlingResource;
import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class signupTest {
    private IdlingResource mIdlingResource;

    @Rule
    public ActivityScenarioRule<signup> activityRule =
            new ActivityScenarioRule<>(signup.class);


    @Before
    public void beforeClass() {

        ActivityScenario<signup> activityScenario = ActivityScenario.launch(signup.class);
        activityScenario.onActivity(new ActivityScenario.ActivityAction<signup>() {
            @Override
            public void perform(signup activity) {
                mIdlingResource = activity.getIdlingResource();
                // To prove that the test fails, omit this call:
                IdlingRegistry.getInstance().register(mIdlingResource);
            }
        });

    }

    @Test
    public void TestingApplicationName() {
        onView(allOf(withId(R.id.textView), withText("Sign Up"))).check(matches(isDisplayed()));
    }

    @Test
    public void registrationSuccessfulTest()  {
        onView(allOf(withId(R.id.email_register))).perform(ViewActions.typeText("karim@gmail.com"));
        pressBack();
        onView(allOf(withId(R.id.password_register))).perform(ViewActions.typeText("123456"));
        pressBack();
        onView(allOf(withId(R.id.confirm_password_register))).perform(ViewActions.typeText("123456"));
        pressBack();

        onView(withId(R.id.signUpbtn))
                .perform(click());


        onView(allOf(withId(R.id.textView), withText("Cardiac Recorder"))).check(matches(isDisplayed()));

    }
    @Test
    public void TestingRegistrationFailureForUsingSameEmailAgain()  {
        onView(allOf(withId(R.id.email_register))).perform(ViewActions.typeText("karim@gmail.com"));
        pressBack();
        onView(allOf(withId(R.id.password_register))).perform(ViewActions.typeText("123456"));
        pressBack();
        onView(allOf(withId(R.id.confirm_password_register))).perform(ViewActions.typeText("123456"));
        pressBack();

        onView(withId(R.id.signUpbtn))
                .perform(click());


        onView(allOf(withId(R.id.textView), withText("Sign Up"))).check(matches(isDisplayed()));

    }

    @Test
    public void registrationFrailureTestingForMisMatchOfPassword() {

        onView(allOf(withId(R.id.email_register))).perform(ViewActions.typeText("rahima@gmail.com"));
        pressBack();
        onView(allOf(withId(R.id.password_register))).perform(ViewActions.typeText("123456"));
        pressBack();
        // -- confirm password wrong -- //
        onView(allOf(withId(R.id.confirm_password_register))).perform(ViewActions.typeText("123456789"));
        pressBack();

        onView(withId(R.id.signUpbtn))
                .perform(click());

        onView(allOf(withId(R.id.textView), withText("Sign Up"))).check(matches(isDisplayed()));


    }

    @After
    public void notRegisterId() {
        if (mIdlingResource != null) {
            IdlingRegistry.getInstance().unregister(mIdlingResource);
        }
    }

}