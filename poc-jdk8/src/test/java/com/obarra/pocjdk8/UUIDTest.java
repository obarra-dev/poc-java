package com.obarra.pocjdk8;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UUIDTest
{

    @Test
    public void fromString() {
        UUID uuid = UUID.fromString("018b1b0d-443f-99f2-9e63-94296d8a72b6");
        assertEquals("018b1b0d-443f-99f2-9e63-94296d8a72b6", uuid.toString());
    }

    @Test
    public void singleConstructor() {
        UUID uuid = new UUID(0, 0);
        assertEquals("00000000-0000-0000-0000-000000000000", uuid.toString());
    }


    @Test
    public void version() {
        UUID uuid = new UUID(0, 0);
        assertEquals(0, uuid.version());
        assertEquals(0, uuid.variant());

        uuid = UUID.fromString("018b1b0d-443f-99f2-9e63-94296d8a72b6");
        assertEquals(9, uuid.version());
        assertEquals(2, uuid.variant());
    }


}
