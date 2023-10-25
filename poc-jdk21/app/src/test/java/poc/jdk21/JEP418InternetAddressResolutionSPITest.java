package poc.jdk21;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;

class JEP418InternetAddressResolutionSPITest {
    @Test
    void defaultEncodingIsUTF8() throws UnknownHostException {
        InetAddress[] addresses = InetAddress.getAllByName("www.baeldung.com");
        List<String> list = Arrays.stream(addresses).map(InetAddress::getHostAddress).toList();

        List<String> expected = List.of("172.66.43.8", "172.66.40.248",
                "2606:4700:3108:0:0:0:ac42:2b08", "2606:4700:3108:0:0:0:ac42:28f8");

        Assertions.assertEquals(expected, list);
    }
}
