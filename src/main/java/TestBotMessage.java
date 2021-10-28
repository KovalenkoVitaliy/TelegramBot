import lombok.SneakyThrows;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TestBotMessage extends TelegramLongPollingBot {

    public TestBotMessage(DefaultBotOptions options) {
        super(options);
    }

    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {
        Message message =  update.getMessage();
        if (message.hasText()) {
            String slovo = message.getText();
            System.out.println(slovo);
            if(slovo.matches(".*[о,О][т,Т][к,К][а,А][т,Т].*")){
                execute(SendMessage.builder()
                        .chatId(message.getChatId().toString())
                        .text("Warning!!!!\nНе откатывайся, не создав точку!")
                        .build());
            }
        }

    }

    @Override
    public String getBotUsername() {
        return "@EPKTestBot";
    }

    public String getBotToken() {
        return "2096670140:AAGD8VNBBnprP_rtm5jrN3icNRHl1b5y8TQ";
    }

    @SneakyThrows
    public static void main(String[] args) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE HH:mm");
        TestBotCurrency bot = new TestBotCurrency(new DefaultBotOptions());
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(bot);
        while (true) {
            Date yy = new Date();
            String currentDate = simpleDateFormat.format(yy);
            if (currentDate.substring(0, currentDate.indexOf(" ")).equals("пятница") &&
                    currentDate.substring(currentDate.indexOf(" ") + 1, currentDate.length()).equals("15:00")) {
                bot.execute(SendMessage.builder()
                        .chatId("830731547")
                        .text("@vs_telegram2019 На планирование бегом")
                        .build());
            }
            Thread.sleep(65000);
        }
    }
}