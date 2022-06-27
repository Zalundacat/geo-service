import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import ru.netology.i18n.LocalizationService;
import ru.netology.i18n.LocalizationServiceImpl;

import static ru.netology.entity.Country.RUSSIA;
import static ru.netology.entity.Country.USA;

public class LocalizationServiceImplTest {

    static LocalizationService localizationService;

    @BeforeAll
    public static void initSuite() {

        localizationService = Mockito.mock(LocalizationServiceImpl.class);

        Mockito.when(localizationService.locale(RUSSIA)).thenReturn("Добро пожаловать");
        Mockito.when(localizationService.locale(USA)).thenReturn("Welcome");
    }

    @Test
    public void localeRussiaTest() {
        Assertions.assertEquals(localizationService.locale(RUSSIA), "Добро пожаловать");

    }

    @Test
    public void localeUsaTest() {
        Assertions.assertEquals(localizationService.locale(USA), "Welcome");
    }
}