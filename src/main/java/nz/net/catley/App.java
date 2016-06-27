package nz.net.catley;

/**
 * Hello world!
 *
 */
public class App 
{
    public static final String GOOGLE_MAPS_API_KEY = "AIzaSyCqxi_naIZMOjJnD7dK9zfXAUuO1ePscpo";

    public static void main( String[] args ) {
        if (args.length >= 2) {
            TimezoneProcessor timezoneProcessor = new TimezoneProcessor(new TimezoneFinder(GOOGLE_MAPS_API_KEY));
            try {
                timezoneProcessor.process(args[0], args[1]);
            } catch (java.io.IOException e) {
                System.out.println("An io error occurred.");
            }
        } else {
            System.out.println("Two arguments are needed, input filename, and output filename");
        }
    }
}
