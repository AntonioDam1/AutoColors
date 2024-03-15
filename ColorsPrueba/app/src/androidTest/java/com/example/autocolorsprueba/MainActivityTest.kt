package com.example.autocolorsprueba
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import org.junit.Test

class MainActivityTest {

    /**
     * Test para probar si carga la MainActivity
     */
    @Test
    fun test_MainActivity_carga() {
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)

        onView(withId(R.id.main)).check(matches(isDisplayed()))
    }

    /**
     * Test para ir de la MainAcivity a la activity de filtrar
     */
    @Test
    fun test_Navegacion_de_MainActivity_a_FiltraryActivity() {

        val activityScenario = ActivityScenario.launch(MainActivity::class.java)

        onView(withId(R.id.filtrar)).perform(ViewActions.click())

        onView(withId(R.id.filtrarActivity)).check(matches(isDisplayed()))

    }

    /**
     *  Test para probar si va de MainActivity a MapasActivity
     */
    @Test
    fun test_Navegacion_de_MainActivity_a_MapasActivity() {

        val activityScenario = ActivityScenario.launch(MainActivity::class.java)

        onView(withId(R.id.corazon)).perform(ViewActions.click())

        onView(withId(R.id.FavsActivity)).check(matches(isDisplayed()))

    }

    fun test_Navegacion_de_MainActivity_a_FavoritosActivity(){
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.FavsActivity)).perform(ViewActions.click())

        onView(withId(R.id.cons)).check(matches(isDisplayed()))


    }

}