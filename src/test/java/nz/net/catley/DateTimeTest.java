package nz.net.catley;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.TimeZone;

public class DateTimeTest extends TestCase {
    public DateTimeTest( String testName )
    {
        super( testName );
    }
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    public void testParsesOutTheLatLong() {
        try {
            DateTime dateTime = new DateTime("2013-07-10 02:52:49,-44.490947,171.220966");
            assertEquals(dateTime.lat, -44.490947);
            assertEquals(dateTime.lng, 171.220966);
        } catch (Exception e) {
            assertTrue(false);
        }
    }

    public void testOutputsWhatIsInputWhenSetToUTC() {
        try {
            DateTime dateTime = new DateTime("2013-07-10 02:52:49,-44.490947,171.220966");
            dateTime.setTz(TimeZone.getTimeZone("UTC"));
            assertEquals(
                    dateTime.toCsvString(),
                    "2013-07-10 02:52:49,-44.490947,171.220966,UTC,2013-07-10T02:52:49"
            );
        } catch (Exception e) {
            assertTrue(false);
        }
    }

    public void testConvertsToAucklandTime() {
        try {
            DateTime dateTime = new DateTime("2013-07-10 02:52:49,-44.490947,171.220966");
            dateTime.setTz(TimeZone.getTimeZone("Pacific/Auckland"));
            assertEquals(
                    dateTime.toCsvString(),
                    "2013-07-10 02:52:49,-44.490947,171.220966,Pacific/Auckland,2013-07-10T14:52:49"
            );
        } catch (Exception e) {
            assertTrue(false);
        }
    }

    public void testThrowsIfThereAreNotEnoughCsvFields() {
        try {
            new DateTime("2013-07-10 02:52:49,-44.490947");
            assertTrue(false);
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Not enough fields.");
        }
    }

    public void testThrowsIfTheSecondAndThirdFieldsAreNotNumbers() {
        try {
            new DateTime("2013-07-10 02:52:49,-44.490947,a");
            assertTrue(false);
        } catch (Exception e) {
            assertEquals(e.getMessage(), "For input string: \"a\"");
        }
    }
}
