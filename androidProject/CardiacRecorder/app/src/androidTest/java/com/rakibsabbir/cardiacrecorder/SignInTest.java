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
public class SignInTest {

    private IdlingResource mIdlingResource;

    @Rule
    public ActivityScenarioRule<SignIn> activityRule =
            new ActivityScenarioRule<>(SignIn.class);


    @Before
    public void beforeClass() {

        ActivityScenario<SignIn> activityScenario = ActivityScenario.launch(SignIn.class);
        activityScenario.onActivity(new ActivityScenario.ActivityAction<SignIn>() {
            @Override
            public void perform(SignIn activity) {
                mIdlingResource = activity.getIdlingResource();
                // To prove that the test fails, omit this call:
                IdlingRegistry.getInstance().register(mIdlingResource);
            }
        });

    }

    @Test
    public void TestingApplicationName() {
        onView(allOf(withId(R.id.textView), withText("SignIn")));
    }

    @Test
    public void SignInSuccessfulTest()  {
        onView(allOf(withId(R.id.email_login))).perform(ViewActions.typeText("rakib123@gmail.com"));
        pressBack();
        onView(allOf(withId(R.id.password_login))).perform(ViewActions.typeText("12345678"));

        pressBack();

        onView(withId(R.id.loginBtn))
                .perform(click());


        onView(allOf(withId(R.id.textView), withText("Cardiac Recorder"))).check(matches(isDisplayed()));

    }

    @Test
    public void FailedSignInTest() {
        onView(allOf(withId(R.id.email_login))).perform(ViewActions.typeText("rakib123@gmail.com"));
        pressBack();
        // -- password is given wrong -- //
        onView(allOf(withId(R.id.password_login))).perform(ViewActions.typeText("1234567"));
        pressBack();

        onView(withId(R.id.loginBtn))
                .perform(click());

        onView(allOf(withId(R.id.textView), withText("SignIn"))).check(matches(isDisplayed()));


    }

    @After
    public void unregisterIdlingResource() {
        if (mIdlingResource != null) {
            IdlingRegistry.getInstance().unregister(mIdlingResource);
        }
    }


}