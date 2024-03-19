package com.obarra.pocjdk8;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class LocalDateTest {

    @Test
    void zonaIds() {
        List<String> zones = ZoneId.getAvailableZoneIds()
                .stream()
                .filter(z -> z.equals("Etc/UCT") || z.equals("Etc/GMT-4"))
                .collect(Collectors.toList());

        Assertions.assertEquals(Arrays.asList("Etc/GMT-4", "Etc/UCT"), zones);
        ZoneId zoneId = ZoneId.systemDefault();
        Assertions.assertEquals("America/Argentina/Catamarca", zoneId.toString());
    }

    @Test
    void zoneIdForLocalDateTime() {
        LocalDateTime localDateTimeUTC = LocalDateTime.now(ZoneId.of("UTC"));
        LocalDateTime localDateTimeLA = LocalDateTime.now(ZoneId.of("America/Los_Angeles"));

        Assertions.assertNotEquals(localDateTimeUTC, localDateTimeLA);
        Assertions.assertEquals(localDateTimeUTC.getDayOfYear(), localDateTimeLA.plusHours(7).getDayOfYear());
        Assertions.assertEquals(localDateTimeUTC.getHour(), localDateTimeLA.plusHours(7).getHour());
    }

    @Test
    void zoneIdForLocalDate() {
        LocalDate localDateUTC = LocalDate.now(ZoneId.of("UTC"));
        LocalDate localDateLA = LocalDate.now(ZoneId.of("America/Los_Angeles"));

        Assertions.assertNotEquals(localDateUTC, localDateLA);
        Assertions.assertEquals(localDateUTC, localDateLA.atTime(LocalTime.now(ZoneId.of("America/Los_Angeles"))).plusHours(7).toLocalDate());
    }

    @Test
    void convertLocalDateTimeToAnotherTimeZone() {
        LocalDateTime oldDateTime = LocalDateTime.parse("2024-04-04T10:00:00");
        LocalDateTime newDateTime = oldDateTime.atZone(ZoneId.of("America/Chicago"))
                .withZoneSameInstant(ZoneId.of("America/New_York"))
                .toLocalDateTime();

        Assertions.assertEquals("2024-04-04T11:00:00", newDateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
    }

    @Test
    void tesss() {
        String ts = "2016-09-12T13:15:17.309Z";
        ZonedDateTime parse =
                ZonedDateTime.parse(ts, DateTimeFormatter.ISO_DATE_TIME)
                        .withZoneSameInstant(ZoneId.systemDefault());
        System.out.println(parse);
        System.out.println(parse.toLocalDateTime());
    }
}
