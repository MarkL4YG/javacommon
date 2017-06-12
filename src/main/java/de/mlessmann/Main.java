package de.mlessmann;

import de.mlessmann.logging.MarkL4YGLogger;

/**
 * Created by Life4YourGames on 20.01.17.
 */
public class Main {

    public static void main(String... args) {
        System.out.print("TEST SUCCESSFUL \n");

        MarkL4YGLogger l = new MarkL4YGLogger("test");
        l.getLogReceiver().getChild("test2").severe("TEST");
        l.getLogReceiver().getChild("test3").severe("TestException", new Exception("test"));
        l.getLogReceiver().getChild("test4").info("CausedException", new Exception("Lol", new Exception("Caused")));
    }
}
