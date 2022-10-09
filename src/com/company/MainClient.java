package com.company;

import com.company.client.ClientManager;
import com.company.client.Login;

public class MainClient {
    public static void main(String[] args) {
        final String clientName;
        Login login = new Login();
        try {
            if (login.accepted()) {
                System.out.println("access accepted");
                clientName = login.clientName();
                ClientManager clientManager = new ClientManager(clientName);
                clientManager.start();
            } else {
                System.out.println("access denied");
            }
        } catch (Exception e){
            System.out.println("incorrect input");
        }
    }
}