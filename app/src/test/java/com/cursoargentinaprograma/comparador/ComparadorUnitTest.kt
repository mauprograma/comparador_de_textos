import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.curso.android.app.practica.counter.view.MainViewModel
import com.cursoargentinaprograma.comparador.Model.Comparador
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class MainViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var observer: Observer<Comparador>

    private lateinit var viewModel: MainViewModel

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        viewModel = MainViewModel()
        viewModel.comparador.observeForever(observer)
    }

    @Test
    fun testCompareTexts() {
        val textFirst = "PRIMER"
        val textSecond = "TEST"
        viewModel.compareTexts(textFirst, textSecond)

        //Mockito.verify(observer).onChanged(Mockito.any())
        val capturedValue = viewModel.comparador.value
        assert(capturedValue != null)
        assert(capturedValue?.textFirst == textFirst)
        assert(capturedValue?.textSecond == textSecond)
    }
}