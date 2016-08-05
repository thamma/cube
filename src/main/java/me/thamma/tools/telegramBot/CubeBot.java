package me.thamma.tools.telegramBot;

import me.thamma.cube.model.Algorithm;
import me.thamma.cube.model.Cube;
import me.thamma.cube.model.Metrics;
import me.thamma.utils.CubeUtils;
import org.telegram.telegrambots.TelegramApiException;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendDocument;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

import java.io.FileNotFoundException;
import java.io.InvalidObjectException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.concurrent.ConcurrentLinkedDeque;

public class CubeBot extends TelegramLongPollingBot {

    ConcurrentLinkedDeque<String> subscribers = new ConcurrentLinkedDeque<>();

    private String botToken;

    public CubeBot(String token) {
        this();
        this.botToken = token;
    }

    public CubeBot() {

    }

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

        if (message.getText().equalsIgnoreCase("/start")) {
            sendMessage("Welcome!\nType /help for a list of commands!", message);
        } else if (message.getText().equalsIgnoreCase("/help")) {
            sendMessage("/help\t-\tdisplay this help page\n" +
                            "/analyze [algorithm]\t-\tdisplays general information about the given algorithm\n" +
                            "/minimize [algorithm]\t-\tdisplays the 2-Phase optimal solution of the given algorithm\n",
                    message);
        } else if (message.getText().toLowerCase().startsWith("/testFile")) {
            sendFile("TestString", message);
        } else if (message.getText().toLowerCase().startsWith("/analyze")) {
            String scramble = message.getText().substring(8);
            if (CubeUtils.isValidAlgorithm(scramble)) {
                analyzeAlgorithm(Algorithm.fromScramble(scramble), message);
            } else sendMessage("Could not parse the given algorithm!", message);
        } else if (message.getText().toLowerCase().startsWith("/minimize")) {
            String scramble = message.getText().substring(9);
            if (CubeUtils.isValidAlgorithm(scramble)) {
                Algorithm algorithm = Algorithm.fromScramble(scramble);
                sendMessage("This might take some time…");
                sendMessage(String.format("Optimal solution: %s", CubeUtils.perfectSolve(new Cube().turn(algorithm)).cancelOut()));
            } else sendMessage("Could not parse the given algorithm!", message);
        }
    }

    private void analyzeAlgorithm(Algorithm raw, Message recipient) {
        String spacer = new String(new char[raw.toString().length() + 2]).replace("\0", "-");
        Algorithm algorithm = raw.clone().cancelOut();
        sendMessage(String.format("Raw input: %s\n%s\nCancelled out version: %s\nRotation purged version: %s\nCycles: %s\nOrder: %d\nLength (Q/H/S): %d/%d/%d\n2-Phase suboptimal solution: %s\nFor an optimal solution, see /minimize",
                raw, spacer, algorithm, algorithm.clone().purgeRotations(), algorithm.getCycles(), algorithm.getOrder(), algorithm.length(Metrics.QTM), algorithm.length(Metrics.HTM), algorithm.length(Metrics.STM), CubeUtils.anySolve(new Cube().turn(algorithm)).cancelOut()), recipient);
    }

    private void sendFile(String content, Message message) {
        PrintWriter out = null;
        try {
            out = new PrintWriter("testfile.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        out.println(content);
        SendDocument sendDocumentRequest = new SendDocument();
        sendDocumentRequest.setDocument("testfile.txt");
        sendDocumentRequest.setChatId(message.getChatId().toString());
        try {
            sendDocument(sendDocumentRequest);
        } catch (TelegramApiException e) {
            e.printStackTrace();
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
        CubeBot cubeBot;
        if (args.length > 0)
            cubeBot = new CubeBot(args[0]);
        else
            cubeBot = new CubeBot();
        CubeBot finalCubeBot = cubeBot;
        new Thread(() -> {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
            try {
                telegramBotsApi.registerBot(finalCubeBot);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }).start();
    }
}