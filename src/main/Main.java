package main;

import ui.AppUI;

import java.util.Locale;

public class Main {

    public static void main(String[] args) {
        // ??? set the Locale of JVM US, so the default language
        // would be English. Others could delete the code.
        Locale.setDefault(Locale.US);
        new AppUI();
        System.out.println("AppUI main starting...");
    }

}
