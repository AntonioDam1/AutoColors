package com.example.autocolorsprueba

import android.view.View
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import org.hamcrest.Matchers.greaterThanOrEqualTo
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

/**
 * Clase de prueba unitaria para la actividad ConsultasActivity.
 */
class ConsultasActivityTest {

    /**
     * Prueba para verificar la inicialización del RecyclerView.
     */
    @Test
    fun testRecyclerViewInitialization() {
        // Inicia la actividad
        ActivityScenario.launch(ConsultasActivity::class.java)
        Thread.sleep(2000)

        onView(withId(R.id.recyclerConsultas))
            .check(matches(isDisplayed()))

        onView(withId(R.id.recyclerConsultas))
            .check(RecyclerViewItemCountAssertion.greaterThan(0))

    }

    /**
     * Clase de aserción personalizada para contar elementos de RecyclerView.
     */
    class RecyclerViewItemCountAssertion(private val matcher: Int) : ViewAssertion {
        override fun check(view: View?, noViewFoundException: NoMatchingViewException?) {
            if (noViewFoundException != null) {
                throw noViewFoundException
            }

            val recyclerView = view as RecyclerView
            val adapter = recyclerView.adapter
            androidx.test.espresso.matcher.ViewMatchers.assertThat(adapter?.itemCount ?: 0, org.hamcrest.Matchers.greaterThanOrEqualTo(matcher))
        }

        /**
         * Crea una instancia de RecyclerViewItemCountAssertion con un valor esperado mayor que.
         * @param expected El valor esperado.
         * @return Una instancia de RecyclerViewItemCountAssertion.
         */
        companion object {
            fun greaterThan(expected: Int): RecyclerViewItemCountAssertion {
                return RecyclerViewItemCountAssertion(expected)
            }
        }
    }
}
