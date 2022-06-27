import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;

import static ru.netology.entity.Country.RUSSIA;
import static ru.netology.entity.Country.USA;
import static ru.netology.geo.GeoServiceImpl.*;

public class GeoServiceImplTest {

    static GeoService geoService;

    @BeforeAll
    public static void initSuite() {
        geoService = Mockito.mock(GeoServiceImpl.class);

        Mockito.when(geoService.byIp(LOCALHOST)).thenReturn(new Location(null, null, null, 0));
        Mockito.when(geoService.byIp(MOSCOW_IP)).thenReturn(new Location("Moscow", RUSSIA, "Lenina", 15));
        Mockito.when(geoService.byIp(NEW_YORK_IP)).thenReturn(new Location("New York", USA, " 10th Avenue", 32));
        Mockito.when(geoService.byIp(Mockito.startsWith("172."))).thenReturn(new Location("Moscow", Country.RUSSIA, null, 0));
        Mockito.when(geoService.byIp(Mockito.startsWith("96."))).thenReturn(new Location("New York", Country.USA, null, 0));

        Mockito.when(geoService.byCoordinates(Mockito.anyDouble(), Mockito.anyDouble())).thenThrow(new RuntimeException());


    }

    @Test
    public void byIpLocalhostTest() {
        Assertions.assertNull(geoService.byIp("127.0.0.1").getCountry());
    }

    @ParameterizedTest
    @ValueSource(strings = {"172.0.32.11", "172.123.12.19"})
    public void byIpRussiaTest(String ip) {
        Assertions.assertEquals(RUSSIA, geoService.byIp(ip).getCountry());
    }

    @ParameterizedTest
    @ValueSource(strings = {"96.76.156.289", "96.14.111.300"})
    public void byIpUsaTest(String ip) {
        Assertions.assertEquals(USA, geoService.byIp(ip).getCountry());
    }

    @Test
    public void byCoordinatesTest() {
        Assertions.assertThrows(RuntimeException.class, () -> geoService.byCoordinates(165.43, 58.31));
    }
}