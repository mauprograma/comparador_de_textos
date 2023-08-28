import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.cursoargentinaprograma.comparador.R
import com.cursoargentinaprograma.comparador.View.MainActivity
import org.junit.Rule
import org.junit.Test

class MainActivityInstrumentedTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun testUIInteraction() {
        // Interaction with UI components
        onView(withId(R.id.tieFirstText)).perform(typeText("Hello"))
        onView(withId(R.id.tieSecondText)).perform(typeText("Hello"))
        onView(withId(R.id.actionCompare)).perform(click())

        // Assertion on UI components
        // Add assertions for the UI components here
    }
}