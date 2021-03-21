import com.carlostambascia.config.AppConfig;
import com.carlostambascia.dao.FlightDAO;
import com.carlostambascia.model.City;
import com.carlostambascia.model.Flight;
import com.carlostambascia.model.FlightPrice;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.jdbc.SqlScriptsTestExecutionListener;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.annotations.Test;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import static com.carlostambascia.model.FlightStatus.NOT_DELAYED;
import static org.assertj.core.api.Assertions.assertThat;

@ContextConfiguration(classes = {AppConfig.class})
@WebAppConfiguration
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        SqlScriptsTestExecutionListener.class
})
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class FlightsITsTest extends AbstractTestNGSpringContextTests {
    private static final BigDecimal ECONOMY = BigDecimal.valueOf(80);
    private static final BigDecimal ECONOMY_PLUS = BigDecimal.valueOf(100);
    private static final BigDecimal FIRST_CLASS = BigDecimal.valueOf(150);
    private static final String DEPARTURE_CITY_NAME = "Buenos Aires";
    private static final String DEPARTURE_IATA_CODE = "bsas";
    private static final String DESTINATION_IATA_CODE = "PTG";
    private static final String DESTINATION_CITY_NAME = "Portugal";
    private static final String AIRLINE = "airline";
    private static final Date DATE_TIME = new Date(92139821893L);
    private static final String AIRLINE_CODE = "airline code";
    @Inject
    private FlightDAO dao;

    @Test
    public void saveFlightShouldAddFlightCorrectly() {
        //given
        final Flight flight = createFlightBuilder().build();
        //when
        final Integer flightId = dao.addFlight(flight);
        //then
        assertThat(flightId).isEqualTo(1);
    }

    @Test
    public void shouldGetFlightByDateShouldRetrieveCorrectFlight() {
        //given
        final Flight flight = createFlightBuilder()
                .airline("some random airline")
                .estimatedDepartureDateTime(new Date(72139821893L))
                .scheduledDepartureDateTime(new Date(72139821893L))
                .build();
        final Flight flight1 = createFlightBuilder().build();
        final Flight flight2 = createFlightBuilder()
                .airline("a different airline")
                .estimatedDepartureDateTime(new Date(82139821893L))
                .scheduledDepartureDateTime(new Date(82139821893L))
                .build();
        dao.addFlight(flight);
        dao.addFlight(flight1);
        dao.addFlight(flight2);
        //when
        final List<Flight> flightsByDate = dao.getFlightsByDate(DATE_TIME);
        //then
        assertThat(flightsByDate).hasSize(1);
        assertThat(flightsByDate).satisfies(foundFlight -> assertThat(foundFlight).usingRecursiveComparison().isEqualTo(flight1));
    }

    private Flight.FlightBuilder createFlightBuilder() {
        return Flight.builder()
                .flightNumber(null)
                .departureCity(new City(DEPARTURE_IATA_CODE, DEPARTURE_CITY_NAME))
                .destinationCity(new City(DESTINATION_IATA_CODE, DESTINATION_CITY_NAME))
                .airline(AIRLINE)
                .airlineCode(AIRLINE_CODE)
                .scheduledDepartureDateTime(DATE_TIME)
                .estimatedDepartureDateTime(DATE_TIME)
                .price(List.of(createFlightPrice()))
                .gate(1)
                .status(NOT_DELAYED);
    }

    private FlightPrice createFlightPrice() {
        return new FlightPrice()
                .withId(null)
                .withEconomy(ECONOMY)
                .withEconomyPlus(ECONOMY_PLUS)
                .withFirstClass(FIRST_CLASS);
    }
}
