package com.company.client;

import com.company.utily.CommandLine;

import java.util.Scanner;

public class Login {

    ClientTcpTransport clientTcpTransport = new ClientTcpTransport();
    CommandLine commandLine = new CommandLine();

    public String clientName(){
        return commandLine.clientName;
    }

    public boolean accepted() throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("input your Login: ");
        commandLine.clientName = scanner.nextLine();
        System.out.println("input your Password");
        commandLine.args.add(0, scanner.nextLine());

        commandLine.command = "check_login";
        commandLine = clientTcpTransport.execute(commandLine);

        if (commandLine.servAnswer.equals("Y")){
            return true;
        }else {
            return false;
        }
    }


}
