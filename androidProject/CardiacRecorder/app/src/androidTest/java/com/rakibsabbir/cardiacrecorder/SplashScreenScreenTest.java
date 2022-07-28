package com.rakibsabbir.cardiacrecorder;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.allOf;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.google.firebase.auth.FirebaseAuth;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class SplashScreenScreenTest {

    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    @Rule
    public ActivityScenarioRule<SplashScreen> activityRule =
            new ActivityScenarioRule<>(SplashScreen.class);


    @Test
    public void toSignInTest() {
        if(firebaseAuth.getCurrentUser()!=null)
        {
            onView(allOf(withId(R.id.textView), withText("Cardiac Recorder")));
            return;
        }
        onView(withId(R.id.signin_splash))
                .perform(click());

        onView(allOf(withId(R.id.textView), withText("SignIn")));
    }

    @Test
    public void toSignUpTest() {
        if(firebaseAuth.getCurrentUser()!=null)
        {
            onView(allOf(withId(R.id.textView), withText("Cardiac Recorder")));
            return;
        }
        onView(withId(R.id.signup_splash))
                .perform(click());

        onView(allOf(withId(R.id.textView), withText("Sign Up")));
    }

}