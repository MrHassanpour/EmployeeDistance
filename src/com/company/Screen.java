package com.company;

/**
 * Sharif Uni Employees Program ( Maktab59.Mr.Falahati )
 *
 * Group 3: Alireza HasanPour & Mahdi Leilaz Mehrabadi
 */

public class Screen {
    private static int screenSize = 37;

    public void MessageShow(String message) {
        int length = screenSize - 4;
        for (int i = 0; i < length + 4; i++)
            System.out.print("*");
        System.out.println();
        System.out.print("*");
        for (int i = 0; i < (length + 2 - message.length()) / 2; i++)
            System.out.print(" ");
        System.out.print(message);
        for (int i = 0; i < (length + 2 - message.length()) / 2; i++)
            System.out.print(" ");
        System.out.print("*");
        System.out.println();
        for (int i = 0; i < length + 4; i++)
            System.out.print("*");
        System.out.println();
    }

    public void MessagesInsideMethodeShow(String message) {
        String[] messageArray = message.split("\n");
        int spaceLength = 0;
        String subString;
        boolean longerThanAllowed = false;
        for (int j = 0; j < messageArray.length; j++) {
            if (messageArray[j].length() > 35) {
                subString = messageArray[j].substring(0, 35);
                longerThanAllowed = true;
            } else
                subString = messageArray[j];
            for (int k = 0; k < (longerThanAllowed ? 2 : 1); k++) {
                if (longerThanAllowed && k == 1)
                    subString = messageArray[j].substring(35, messageArray[j].length());
                spaceLength = (screenSize - subString.length()) / 2;
                for (int i = 0; i < spaceLength; i++)
                    System.out.print(" ");
                System.out.print(subString);
                System.out.println();
            }
        }
        for (int i = 0; i < 35; i++)
            System.out.print("-");
        System.out.println();
    }

}
