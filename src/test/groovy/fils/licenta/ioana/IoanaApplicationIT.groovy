package fils.licenta.ioana

import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class IoanaApplicationIT extends Specification {
    def "it should boot Spring application successfully"() {
        expect: "application context exists"
    }
}
