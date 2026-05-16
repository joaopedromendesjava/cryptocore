package cryptocore.infrastructure.output.http;

import cryptocore.application.port.NotificationPort;
import cryptocore.infrastructure.output.dto.TelegramRequestDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@Component
public class TelegramNotificationClient implements NotificationPort {

    private final RestTemplate restTemplate = new RestTemplate();
    private final List<String> chats = List.of("1848425272");
    private static final String URL_BASE_TELEGRAM = "https://api.telegram.org/bot%s/sendMessage";

    @Value("${bot.token}")
    private String botToken;

    @Override
    public void sendPriceAlert(String message) {
        chats.forEach(c -> {sendToChat(c, message);});
    }

    private void sendToChat(String chatId, String text) {
        var httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        try {
            var urlFinal = String.format(URL_BASE_TELEGRAM, botToken);
            var body = new HttpEntity<>(new TelegramRequestDTO(chatId, text, "HTML"));

            restTemplate.exchange(urlFinal, HttpMethod.POST, body, String.class);

        } catch (Exception e){
            log.error("Error when send message to chat",e);
        }
    }
}
