package br.com.digio.androidtest.presentation


import androidx.test.filters.LargeTest
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.platform.app.InstrumentationRegistry

import br.com.digio.androidtest.R

import org.hamcrest.core.IsInstanceOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

import org.hamcrest.Matchers.allOf

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule
    @JvmField
    var mActivityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    private val context = InstrumentationRegistry.getInstrumentation().targetContext

    @Test
    fun onDisplayTitleShouldHaveHelloMaria() {
        val textTitle = context.getString(R.string.hello_maria)
        val textView = onView(
            allOf(
                withId(R.id.title), withText(textTitle),
                isDisplayed()
            )
        )
        textView.check(matches(withText(textTitle)))
    }

    @Test
    fun onDisplayListSpotLightShouldHaveItem() {
        Thread.sleep(1000)
        onView(withId(R.id.recyMainSpotlight)).apply {
            check(matches(hasDescendant(withId(R.id.imgMainItemSpotLight))))
        }

    }

    @Test
    fun onDisplayListProductsShouldHaveItem() {
        Thread.sleep(1000)
        onView(withId(R.id.recyMainProducts)).apply {
            check(matches(hasDescendant(withId(R.id.imgMainItemProducts))))
        }
    }

}
