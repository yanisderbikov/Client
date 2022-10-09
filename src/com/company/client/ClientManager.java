package com.company.client;

import com.company.utily.CommandEnum;
import com.company.utily.CommandLine;

import java.util.Scanner;

public class ClientManager{
    final String clientName;
    final Parser parser = new Parser();
    ClientTcpTransport clientTcpTransport = new ClientTcpTransport();
    
    public ClientManager(String clientName){
        this.clientName = clientName;
    }

    // TODO: 26.09.2022 добавить в CommandLine clientName, чтобы он везде гулял с ним
    public void start() {

        while (true) {
            CommandLine commandLine = parser.scan();
            commandLine.clientName = clientName;
            if (commandLine.command.equalsIgnoreCase("EXIT")) {
                System.out.println("Программа завершена");
                break;
            }
            if (check(CommandEnum.values(), commandLine.command)) {
//                Комманда прошла проверку и готова отпраивться на сервер
                try {
                    // изначально в commandLine.serverWaitForAnswer = true;
                    commandLine.serverWaitForAnswer = true;
                    while (commandLine.serverWaitForAnswer){
                        commandLine = clientTcpTransport.execute(commandLine);
                        System.out.println(commandLine.servAnswer);
                        if (commandLine.needArgs){
                            commandLine = sendArgs(commandLine);
                        }
                    }
                    System.out.println("Command executed successfully");
                } catch (Exception e) {
                    System.out.println("Server is out, try later");
                }
            }else {
                System.out.printf("Command \"%s\" doesn't exist, try again\n",commandLine.command);
            }
        }
        System.out.println("Программа закрыта");
    }

    boolean check(CommandEnum[] commandEnums, String cmd){
        for (var line : commandEnums) {
            if (cmd.equalsIgnoreCase(line.toString())) {
                return true;
            }
        }
        return false;
    }

    CommandLine sendArgs(CommandLine commandLine){

        if(commandLine.servAnswer.equals("show me parameters for creation")){
            commandLine = argForCreatDragon(commandLine);
        } else if(commandLine.oneArgNeed){
            commandLine = oneArgSend(commandLine);
        }
        else {
            System.out.println("че хотел ???");
            commandLine.serverWaitForAnswer = false;
        }
        return commandLine;
    }

    CommandLine oneArgSend(CommandLine cl){
        // принимается только один аргумент
        Scanner scanner;
        while (true){
            scanner = new Scanner(System.in);
            if (scanner.hasNextLong()) {
                long y = scanner.nextLong();
                cl.args.add(0, String.valueOf(y));
                return cl;
            }
        }
    }

    CommandLine argForCreatDragon(CommandLine cl){
        // TODO: 14.09.2022 реализовать ввод команд в той-же строчке;
        // TODO: 14.09.2022 если неправильно введено слово/параметр, вывести ошибку
        Scanner scanner = new Scanner(System.in);
        System.out.println("Dragon name is: ");
        String name = scanner.nextLine();
        cl.args.add(0, name);
        System.out.println("Coordinates of dragon: ");
        while (true){
            scanner = new Scanner(System.in);
            System.out.println("coordinate x: (int)");
            if (scanner.hasNextInt()) {
                int x = scanner.nextInt();
                cl.args.add(1, String.valueOf(x));
                break;
            }
        } // coordinate x
        while (true){
            scanner = new Scanner(System.in);
            System.out.println("coordinate y: (float)");
            if (scanner.hasNextFloat()) {
                float y = scanner.nextFloat();
                cl.args.add(2, String.valueOf(y));
                break;
            }
        } // coordinate y
        while (true){
            scanner = new Scanner(System.in);
            System.out.println("Dragon's age: ");
            if (scanner.hasNextInt()) {
                int age = scanner.nextInt();
                cl.args.add(3, String.valueOf(age));
                break;
            }
        } // age
        while (true){
            System.out.println("Choose one of the color: \n" +
                    "GREEN\n" +
                    "YELLOW\n" +
                    "ORANGE\n" +
                    "WHITE\n" +
                    "BROWN");
            scanner = new Scanner(System.in);
            String color = scanner.nextLine();
            if (color.equals("GREEN") || color.equals("YELLOW") || color.equals("ORANGE") || color.equals("WHITE") || color.equals("BROWN")){
                cl.args.add(4, color);
                break;
            }
        } // color
        while (true){
            System.out.println("Choose one of the type: \n" +
                    "WATER\n" +
                    "UNDERGROUND\n" +
                    "AIR\n" +
                    "FIRE");
//            scanner = new Scanner(System.in);
            String type = scanner.nextLine();
            if (type.equals("WATER") || type.equals("UNDERGROUND") || type.equals("AIR") || type.equals("CHAOTIC_EVIL") || type.equals("FIRE")){
                cl.args.add(5, type);
                break;
            }
        } // type
        while (true){
            System.out.println("Choose one of the character: \n" +
                    "WISE\n" +
                    "EVIL\n" +
                    "CHAOTIC\n" +
                    "CHAOTIC_EVIL\n" +
                    "FICKLE");
//            scanner = new Scanner(System.in);
            String character = scanner.nextLine();
            if (character.equals("WISE") || character.equals("EVIL") || character.equals("CHAOTIC") || character.equals("CHAOTIC_EVIL") || character.equals("FICKLE")){
                cl.args.add(6, character);
                break;
            }
        } // character
        while (true){
            scanner = new Scanner(System.in);
            System.out.println("Dragon's cave: (long)");
            if (scanner.hasNextLong()) {
                long cave = scanner.nextLong();
                cl.args.add(7, String.valueOf(cave));
                break;
            }
        } // cave
        return cl;
    }

}


//        while (going) {
//            CommandLine commandLine = parser.scan();
//            if (storageManager.checkKey(commandLine.command)) {
//                System.out.printf("Your command is: %s \n", commandStr);
//                storageManager.runCommand(commandLine);
//            } else {
//                System.out.printf("Команды \"%s\" не существует\n\n", commandLine.command);
//            }
//        }
//
//
//
//
//
//
//
//
//             //  неизвастная строка
//            // тут возникает nullPointException, если команда введена  не та
////            try {
////                commandStr = commandLine.command;
////
////                if (storageManager.getCommandMapFromData().containsKey(commandLine.command)){
////                    System.err.println("YEEEEES");
////                    System.out.printf("Your command is: %s \n", commandStr);
////                }
////
////                Command command  = storageManager.getCommandMapFromData().get(commandStr);
////                System.out.printf("Your command is: %s \n", commandStr);
////                command.execute(commandLine.args);
////                storageManager.addCommandToHistory(commandStr);
////            }catch (NullPointerException nullPE){
////                System.err.printf("Команды \"%s\" не существует\n\n", commandLine.command);
////            }catch (Exception exc){
////                throw new RuntimeException(exc);
////            }
////            if (checkCommandTrue(commandLine)) {
////                Command command  = commandMap.get(commandLine.command);
////                // тут возникает nullPointException, если команда введена  не та
////
////                boolean commandResult = command.execute(commandLine.args);
////                if (commandResult) {
////                    System.out.println("команда выполнилась успешно");
////                } else {
////                    System.out.println("пизда");
////
////                }
////            }else {
////                System.err.println("Команды не существует");
////            }
//        }