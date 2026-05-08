package cryptocore;

import cryptocore.infrastructure.input.websocket.client.BinanceWebSocketClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "cryptocore")
@SpringBootApplication
public class CryptocoreApplication implements CommandLineRunner {

    private final BinanceWebSocketClient binanceWebSocketClient;

    public CryptocoreApplication(BinanceWebSocketClient binanceWebSocketClient) {
        this.binanceWebSocketClient = binanceWebSocketClient;
    }

    public static void main(String[] args) {
		SpringApplication.run(CryptocoreApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
        binanceWebSocketClient.connect();

    }
}
