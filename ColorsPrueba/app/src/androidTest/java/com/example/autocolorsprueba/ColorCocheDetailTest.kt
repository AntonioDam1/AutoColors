package com.example.autocolorsprueba
import androidx.test.espresso.matcher.RootMatchers
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import android.widget.Toast
import org.junit.Assert.*
import org.junit.Test
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import kotlinx.coroutines.runBlocking
import org.junit.runner.RunWith


class ColorCocheDetailTest{

    /**
     * Prueba la clase ColorCocheDetail y que sus elementos estén visibles
     */
    @Test
    fun testViewsDisplayed() {
        // Inicia la actividad
        ActivityScenario.launch(ColorCocheDetail::class.java)
        Thread.sleep(3000)


        // Verifica que los elementos principales estén presentes y visibles
        onView(withId(R.id.alphaTileViewColor)).check(matches(isDisplayed()))
        onView(withId(R.id.alphaTileViewOriginal)).check(matches(isDisplayed()))
        onView(withId(R.id.textViewModeloDetail)).check(matches(isDisplayed()))
        onView(withId(R.id.textViewAnioDetail)).check(matches(isDisplayed()))
        onView(withId(R.id.textViewMatchDetail)).check(matches(isDisplayed()))
        onView(withId(R.id.textViewColorOriginal)).check(matches(isDisplayed()))
        onView(withId(R.id.tvHexadecimalOriginalDetail)).check(matches(isDisplayed()))
        onView(withId(R.id.tvNombrePinturaDetail)).check(matches(isDisplayed()))
        onView(withId(R.id.tvMarcaDetail)).check(matches(isDisplayed()))
        onView(withId(R.id.logo)).check(matches(isDisplayed()))
        onView(withId(R.id.buttonBorrar)).check(matches(isDisplayed()))
        onView(withId(R.id.buttonFav)).check(matches(isDisplayed()))
    }


}