package com.example.autocolorsprueba

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.ViewAssertion

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import org.junit.Test


class ConsultasActivityTest {

    @Test
    fun testRecyclerViewInitialization() {
        // Inicia la actividad
        ActivityScenario.launch(ConsultasActivity::class.java)

        // Espera un tiempo razonable para que la actividad se inicie completamente
        Thread.sleep(2000)

        // Verifica que el RecyclerView esté presente
        Espresso.onView(withId(R.id.recyclerConsultas))
            .check(matches(isDisplayed()))

        // Verifica que el RecyclerView tenga al menos un elemento visible
        Espresso.onView(withId(R.id.recyclerConsultas))
            .check(RecyclerViewItemCountAssertion.greaterThan(0))

        // Verifica que el RecyclerView tenga un adaptador establecido
        Espresso.onView(withId(R.id.recyclerConsultas))
            .check { view, _ ->
                val recyclerView = view as RecyclerView
                val adapter = recyclerView.adapter
                val cochesList = adapter?.itemCount
                assertThat(cochesList).
            }
    }

    // Clase de aserción personalizada para contar elementos de RecyclerView
    class RecyclerViewItemCountAssertion(private val matcher: Int) : ViewAssertion {
        override fun check(view: View?, noViewFoundException: NoMatchingViewException?) {
            if (noViewFoundException != null) {
                throw noViewFoundException
            }

            val recyclerView = view as RecyclerView
            val adapter = recyclerView.adapter
            assertThat(adapter?.itemCount ?: 0).isAtLeast(matcher)
        }

        companion object {
            fun greaterThan(expected: Int): RecyclerViewItemCountAssertion {
                return RecyclerViewItemCountAssertion(expected)
            }
        }
    }
}