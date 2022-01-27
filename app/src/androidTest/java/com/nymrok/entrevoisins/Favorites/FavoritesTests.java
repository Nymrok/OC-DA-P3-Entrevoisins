package com.nymrok.entrevoisins.Favorites;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static com.nymrok.entrevoisins.utils.RecyclerViewItemCountAssertion.withItemCount;

import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.nymrok.entrevoisins.MainActivity;
import com.nymrok.entrevoisins.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Random;

@RunWith(AndroidJUnit4.class)
public class FavoritesTests {

    private static final int NEIGHBOURS_GENERATED = 12;

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityRule =
            new ActivityScenarioRule<>(MainActivity.class);

    // Test : Favorites Tab must show only favorites Neighbours
    @Test
    public void favoritesTabMustDisplayOnlyFavorites() {
        final int min = 1;
        final int max = NEIGHBOURS_GENERATED;
        final int random = new Random().nextInt((max - min) + 1);

        // Before : Begin on the Neighbours list
        onView(ViewMatchers.withId(R.id.fragment_neighbours_recyclerview))
                .check(matches(isDisplayed()));

        // Move : Go to Favorites Tab
        onView(withContentDescription("Favorites"))
                .perform(click());

        // Verification : Check if the Favorites list is empty
        onView(ViewMatchers.withId(R.id.fragment_favorites_recyclerview))
                .check(withItemCount(0));

        // Move : Return to Neighbours list
        onView(withContentDescription("My neighbours"))
                .perform(click());

        // Action : Pick someone randomly
        onView(ViewMatchers.withId(R.id.fragment_neighbours_recyclerview))
                .perform(RecyclerViewActions.actionOnItemAtPosition(random, click()));

        // Action : Add this Neighbour to Favorites
        onView(ViewMatchers.withId(R.id.profile_page_button_addToFav))
                .perform(click());

        // Move : Return to Neighbours list
        onView(ViewMatchers.withId(R.id.profile_page_button_backToList))
                .perform(click());

        // Move : Go to Favorites Tab
        onView(withContentDescription("Favorites"))
                .perform(click());

        // Verification : Check if the Favorites list now contains ONE favorite
        onView(ViewMatchers.withId(R.id.fragment_favorites_recyclerview))
                .check(withItemCount(1));

        // Action : Click on this favorite to see his profile
        onView(ViewMatchers.withId(R.id.fragment_favorites_recyclerview))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        // Action : Remove this favorite from Favorites
        onView(ViewMatchers.withId(R.id.profile_page_button_addToFav))
                .perform(click());

        // Move : Return to Favorites list
        onView(ViewMatchers.withId(R.id.profile_page_button_backToList))
                .perform(click());

        // Verification : Check if the Favorites list is now empty like in the beginning
        onView(ViewMatchers.withId(R.id.fragment_favorites_recyclerview))
                .check(withItemCount(0));
    }
}
