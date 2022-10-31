package org.giuseppebnn;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;


public class Main {
    public static void main(String[] args) {

        try {
            TelegramBotsApi botApi = new TelegramBotsApi(DefaultBotSession.class);
            botApi.registerBot(new piBot(Integer.parseInt(args[1]),args[0]));
        }catch(TelegramApiException e){
            System.out.println("TelegramApiException Catched");
            e.printStackTrace();
        }
    }
}