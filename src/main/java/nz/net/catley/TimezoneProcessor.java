package nz.net.catley;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by mark on 27/06/16.
 */
public class TimezoneProcessor {
    TimezoneFinder timezoneFinder;

    TimezoneProcessor(TimezoneFinder timezoneFinder) {
        this.timezoneFinder = timezoneFinder;
    }

    void process(String inFilename, String outFilename) throws java.io.IOException {
        List<String> outputLines = Files.lines(Paths.get(inFilename))
                .map(line -> {
                    Optional<DateTime> result;
                    try {
                        result = Optional.of(new DateTime(line));
                    } catch (Exception e) {
                        result = Optional.empty();
                    }
                    return result;
                })
                .map(dateTime -> {
                    Optional<DateTime> result = dateTime;
                    try {
                        if (dateTime.isPresent()) {
                            dateTime.get().setTz(timezoneFinder.find(dateTime.get()));
                        }
                    } catch(Exception e) {
                        result = Optional.empty();
                    }
                    return result;
                })
                .map(dateTime -> {
                    if (dateTime.isPresent()) {
                        return dateTime.get().toCsvString();
                    } else {
                        return "";
                    }
                }).collect(Collectors.toList());

        Files.write(Paths.get(outFilename), outputLines);
    }
}
