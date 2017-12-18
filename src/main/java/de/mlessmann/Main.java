package de.mlessmann;

import de.mlessmann.logging.ConsoleHandler;
import de.mlessmann.logging.FileHandler;
import de.mlessmann.logging.LogFormatter;
import de.mlessmann.logging.LogWrapper;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

/**
 * Created by Life4YourGames on 20.01.17.
 */
public class Main {

    public static void main(String... args) {
        System.out.print("TEST SUCCESSFUL \n");

        LogWrapper l = new LogWrapper("test", true);
        ConsoleHandler console = new ConsoleHandler(System.out);
        console.setFormatter(new LogFormatter());
        console.setLevel(Level.ALL);
        l.addHandler(console);

        FileHandler fileHandler = new FileHandler(new File("test.log"), true);
        fileHandler.setFormatter(new LogFormatter());
        fileHandler.setLevel(Level.ALL);
        try {
            fileHandler.open();
        } catch (IOException ex) {

        }
        l.addHandler(fileHandler);

        l.getLogReceiver().getChild("test2").severe("TEST");
        l.getLogReceiver().getChild("test3").severe("TestException", new Exception("test"));
        l.getLogReceiver().getChild("test4").info("CausedException", new Exception("Lol", new Exception("Caused")));
    }
}
