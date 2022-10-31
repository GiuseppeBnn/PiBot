package org.giuseppebnn;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

class piBot extends TelegramLongPollingBot {
     private InetAddress consoleAddress;
     private Socket socket;
     private int consolePort;
     private OutputStream OutStream;
     private OutputStreamWriter OutStreamWriter;
     private BufferedWriter bufferedWriter;
     private PrintWriter printWriter;

     protected Socket initializeLogSocket(){
         Socket consoleSocket;
         try {
             consoleSocket = new Socket(this.getConsoleAddress(), this.consolePort);
             return consoleSocket;
         }catch(IOException e){e.printStackTrace(); }
         return null;
     }

     public void setConsoleAddress(String stringa){
         try {
             this.consoleAddress = InetAddress.getByName(stringa);
         }catch(UnknownHostException e){e.printStackTrace();}
     }

     public InetAddress getConsoleAddress(){
         return this.consoleAddress;
     }
     public piBot(int port,String consoleStringAddress){
        this.consolePort=port;
        this.setConsoleAddress(consoleStringAddress);
     }
     public void LogToConsole (String message,String idUtente) throws IOException{
         this.socket=this.initializeLogSocket();
         try {
             this.OutStream = this.socket.getOutputStream();
             this.OutStreamWriter = new OutputStreamWriter(OutStream);
             this.bufferedWriter = new BufferedWriter(OutStreamWriter);
             this.printWriter = new PrintWriter(bufferedWriter, true);
         }catch(IOException e){e.printStackTrace();}



         this.printWriter.println(idUtente);
         this.printWriter.flush();
         this.printWriter.println(message);

         try {
             this.socket.close();
             this.printWriter.close();
             this.OutStreamWriter.close();
             this.bufferedWriter.close();
         }catch(IOException e){e.printStackTrace();}

     }

    private final String Token="5429825238:AAFk-tH8LflZfbZrfz_glx1C1S81pBVpCE0";
    private final String Username="raspberryjava2bot";
    @Override
    public String getBotToken(){
        return this.Token;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if(update.hasMessage() && update.getMessage().hasText()){
            int caso=0;

            String messaggioRicevuto= update.getMessage().getText();
            System.out.println(messaggioRicevuto);
            String chatId= update.getMessage().getChatId().toString();
            System.out.println(chatId);

            SendMessage sendMessage=new SendMessage();
            sendMessage.setChatId(update.getMessage().getChatId().toString());

            if(messaggioRicevuto.equals("/start")){
                caso=1;
            }
            switch (caso){
                case 1:                                       //caso /start
                    sendMessage.setText("Benvenuto su JavaBot! Questo è un piccolo EchoBot, ossia ripete i messaggi ricevuti.");
                    try{
                        execute(sendMessage);
                    }catch(TelegramApiException e){ e.printStackTrace();}
                    sendMessage.setText("Scrivi qualsiasi cosa...");
                    try{
                        execute(sendMessage);
                    }catch(TelegramApiException e){ e.printStackTrace();}   break;

                default:                                      //caso default
                    sendMessage.setText(messaggioRicevuto);

                    try{
                        execute(sendMessage);
                    }catch(TelegramApiException e){ e.printStackTrace();}
                    try {
                        this.LogToConsole(messaggioRicevuto, chatId);
                    }catch(IOException e){e.printStackTrace();}

                    break;
            }
        }
    }

    @Override
    public String getBotUsername(){
        return this.Username;
    }
}

