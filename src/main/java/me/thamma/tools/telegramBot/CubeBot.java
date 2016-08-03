package me.thamma.tools.telegramBot;

import me.thamma.cube.model.Cube;
import me.thamma.utils.CubeUtils;
import org.telegram.telegrambots.TelegramApiException;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

import java.io.InvalidObjectException;
import java.util.Scanner;
import java.util.concurrent.ConcurrentLinkedDeque;

public class CubeBot extends TelegramLongPollingBot {

    ConcurrentLinkedDeque<String> subscribers = new ConcurrentLinkedDeque<>();

    private String botToken;

    @Override
    public String getBotToken() {
        if (this.botToken == null) {
            System.out.println("Please provide a bot token:");
            this.botToken = new Scanner(System.in).nextLine();
        }
        return this.botToken;
    }

    @Override
    public void onUpdateReceived(Update update) {
        try {
            handleMessage(update);
        } catch (Exception e) {
        }
    }

    @Override
    public String getBotUsername() {
        return "TetrisAINotifications";
    }

    private void handleMessage(Update update) throws InvalidObjectException {

        Message message = update.getMessage();

        if (message.getText().equalsIgnoreCase("/start"))
            sendMessage("Welcome!\nType /help for a list of commands!", message);
        else if (message.getText().equalsIgnoreCase("/help"))
            sendMessage("/help\t-\tdisplay this help page\n" +
                    "/minimize [algorithm]\t-\treturns a minimized version of the provided algorithm", message);
        else if (message.getText().toLowerCase().startsWith("/minimize")) {
            String alg = message.getText().substring(10);
            sendMessage(String.format("I'll try my best!"), message);
            if (CubeUtils.isValidAlgorithm(alg)) {
                sendMessage(String.format("Minimized version: %s", CubeUtils.solve(Cube.fromScramble(alg), 20)), message);
            } else sendMessage("Could not parse the given algorithm!", message);
        }

    }

    private void unregister(Message message) {
        if (subscribers.contains(message.getChatId().toString()))
            subscribers.remove(message.getChatId().toString());
        sendMessage("You are now unregistered!", message.getChatId().toString());
    }

    private void register(Message message) {
        if (!subscribers.contains(message.getChatId().toString()))
            subscribers.add(message.getChatId().toString());
        sendMessage("You are now signed up!", message.getChatId().toString());
    }

    public void sendMessage(Iterable<String> strings) {
        sendMessage(String.join("\n", strings));
    }

    public void sendMessage(String message) {
        for (String subscriber : subscribers)
            sendMessage(message, subscriber);
    }

    public void sendMessage(String message, Message target) {
        sendMessage(message, target.getChatId().toString());
    }

    public void sendMessage(String message, String id) {
        SendMessage sendMessageRequest = new SendMessage();
        sendMessageRequest.setText(message);
        sendMessageRequest.setChatId(id);
        try {
            sendMessage(sendMessageRequest);
        } catch (TelegramApiException e) {
        }
    }

    public static void main(String... args) {
        CubeBot echoBot = new CubeBot();
        new Thread(() -> {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
            try {
                telegramBotsApi.registerBot(echoBot);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }).start();
    }
}