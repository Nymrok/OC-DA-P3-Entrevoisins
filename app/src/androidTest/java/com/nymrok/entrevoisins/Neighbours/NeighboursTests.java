package com.nymrok.entrevoisins.Neighbours;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;

import static com.nymrok.entrevoisins.utils.RecyclerViewItemCountAssertion.withItemCount;

import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.nymrok.entrevoisins.MainActivity;
import com.nymrok.entrevoisins.R;
import com.nymrok.entrevoisins.utils.DeleteViewAction;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class NeighboursTests {

    private static final int NEIGHBOURS_GENERATED = 12;

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test // Test : Neighbours list must contains at least one neighbour
    public void neighboursRecyclerViewShouldNotBeEmpty() {
        // Action : Matches Neighbours recyclerview if it has at least the specified number of children
        onView(ViewMatchers.withId(R.id.fragment_neighbours_recyclerview))
                .check(matches(hasMinimumChildCount(1)));
    }

    @Test // Test : If an item is deleted, the item is no more shown
    public void neighboursDeleteActionShouldRemoveItem() {
        // Before : 12 Neighbours should be generated at launch
        onView(ViewMatchers.withId(R.id.fragment_neighbours_recyclerview))
                .check(withItemCount(NEIGHBOURS_GENERATED));

        // Action : Deleting one of them
        onView(ViewMatchers.withId(R.id.fragment_neighbours_recyclerview))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, new DeleteViewAction()));

        // After : Now only 11 Neighbours should be in the list
        onView(ViewMatchers.withId(R.id.fragment_neighbours_recyclerview))
                .check(withItemCount(NEIGHBOURS_GENERATED - 1));
    }
}
