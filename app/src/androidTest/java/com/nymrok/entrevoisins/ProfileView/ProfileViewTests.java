package com.nymrok.entrevoisins.ProfileView;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.nymrok.entrevoisins.MainActivity;
import com.nymrok.entrevoisins.R;
import com.nymrok.entrevoisins.di.DI;
import com.nymrok.entrevoisins.models.Neighbour;
import com.nymrok.entrevoisins.service.NeighbourApiService;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

@RunWith(AndroidJUnit4.class)
public class ProfileViewTests {

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityRule =
            new ActivityScenarioRule<>(MainActivity.class);

    // Test : If the user clicks on an item from the Neighbours list, ViewProfileActivity must launch
    @Test
    public void viewProfileContentShouldDisplay() {
        // Action : Performs click on the first item of the list
        onView(ViewMatchers.withId(R.id.fragment_neighbours_recyclerview))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        // Verification : Check if all of the content is displayed
        onView(ViewMatchers.withId(R.id.profile_page_button_backToList))
                .check(matches(isDisplayed()));

        onView(ViewMatchers.withId(R.id.profile_page_avatar_banner))
                .check(matches(isDisplayed()));

        onView(ViewMatchers.withId(R.id.profile_page_title_banner))
                .check(matches(isDisplayed()));

        onView(ViewMatchers.withId(R.id.profile_page_button_addToFav))
                .check(matches(isDisplayed()));

        onView(ViewMatchers.withId(R.id.profile_page_links_title))
                .check(matches(isDisplayed()));

        onView(ViewMatchers.withId(R.id.profile_page_links_postalAddress))
                .check(matches(isDisplayed()));

        onView(ViewMatchers.withId(R.id.profile_page_links_phoneNumber))
                .check(matches(isDisplayed()));

        onView(ViewMatchers.withId(R.id.profile_page_links_socialWebsite))
                .check(matches(isDisplayed()));

        onView(ViewMatchers.withId(R.id.profile_page_description_title))
                .check(matches(isDisplayed()));

        onView(ViewMatchers.withId(R.id.profile_page_description_content))
                .check(matches(isDisplayed()));
    }

    // Test : When ViewProfileActivity is launched, the TextView for the Neighbour name must be filled correctly
    @Test
    public void viewProfileContentNameIsCorrect() {
        // Useful for later
        NeighbourApiService API = DI.getNewInstanceApiService();
        List<Neighbour> Neighbours = API.getNeighbours();

        // Performs click on the first item of the list
        onView(ViewMatchers.withId(R.id.fragment_neighbours_recyclerview))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        // Check if names (banner and title) are the good ones
        onView(ViewMatchers.withId(R.id.profile_page_title_banner))
                .check(matches(withText(Neighbours.get(0).getName())));

        onView(ViewMatchers.withId(R.id.profile_page_links_title))
                .check(matches(withText(Neighbours.get(0).getName())));
    }
}
