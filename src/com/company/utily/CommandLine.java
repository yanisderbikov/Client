package com.company.utily;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

public class CommandLine implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    public String command;
    public ArrayList<String> args = new ArrayList<>(); // параметры передаваемые
//    public boolean clientIn; // true если ответов ждать не нужно
    public String servAnswer;
    public boolean serverWaitForAnswer;
    public long dragonID;
    public boolean oneArgNeed;
    public boolean needArgs; // if (true) нужно  поставить serverWaitForAnswer = true;
    public String clientName;
}