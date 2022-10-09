package com.company.command;

import com.company.utily.CommandLine;

public interface Command {

    CommandLine execute(CommandLine commandLine);
    //то что будет происходить далее в посдующих классаах

}
