package com.company.client;

import com.company.utily.CommandLine;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientTcpTransport {

    CommandLine CL;


    CommandLine execute(CommandLine commandLine) throws Exception {
        this.CL = commandLine;
        Socket clientSocket = new Socket("localhost", 9000);

        ObjectOutputStream objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());

        objectOutputStream.writeObject(CL);
        objectOutputStream.flush();

        ObjectInputStream objectInputStream = new ObjectInputStream(clientSocket.getInputStream());

        var what = objectInputStream.readObject();
        if (what instanceof CommandLine){
            CL = (CommandLine) what;
        } else{
            System.out.println(what.toString());
        }

        objectInputStream.close();
        objectOutputStream.close();

        clientSocket.close();
        objectInputStream.close();
        objectOutputStream.close();
        return CL;
    }
}