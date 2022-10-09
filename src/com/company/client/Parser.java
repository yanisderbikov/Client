package com.company.client;


import com.company.utily.CommandLine;

import java.util.Scanner;



public class Parser {

//    парсер должен уже отфильтрованно подавать команду

    public CommandLine scan() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {}

        CommandLine commandLine = new CommandLine();

        System.out.print("Input a command: ");
        Scanner scanner = new Scanner(System.in);

        String line = scanner.nextLine();

        Scanner buf = new Scanner(line);
        commandLine.command = buf.next();// отправилась первавя слово входящей команды

        while (true) {
            if (!buf.hasNext()) {
                break;
            }
            commandLine.args.add(buf.next());// отправилась вторая слово и сл. входящей комманды

        }


        return commandLine;
    }

}
