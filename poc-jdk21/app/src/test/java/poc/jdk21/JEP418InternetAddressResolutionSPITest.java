package poc.jdk21;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

class JEP418InternetAddressResolutionSPITest {
    @Test
    void getAllByName() throws UnknownHostException {
        InetAddress[] addresses = InetAddress.getAllByName("www.baeldung.com");
        List<String> list = Arrays.stream(addresses).map(InetAddress::getHostAddress).sorted().toList();

        List<String> expected = Stream.of("172.66.43.8", "172.66.40.248",
                "2606:4700:3108:0:0:0:ac42:2b08", "2606:4700:3108:0:0:0:ac42:28f8").sorted().toList();

        Assertions.assertEquals(expected, list);
    }
}
