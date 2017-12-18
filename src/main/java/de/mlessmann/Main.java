package de.mlessmann;

import de.mlessmann.logging.LogWrapper;

/**
 * Created by Life4YourGames on 20.01.17.
 */
public class Main {

    public static void main(String... args) {
        System.out.print("TEST SUCCESSFUL \n");

        LogWrapper l = new LogWrapper("test");
        l.getLogReceiver().getChild("test2").severe("TEST");
        l.getLogReceiver().getChild("test3").severe("TestException", new Exception("test"));
        l.getLogReceiver().getChild("test4").info("CausedException", new Exception("Lol", new Exception("Caused")));
    }
}
