package org.baumann.andrew.archive.planner;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;

class HelpOptionHandler implements OptionHandler {

    final Options options;
    
    HelpOptionHandler(Options options) {
        this.options = options;
    }
    
    public void handle(CommandLine commandLine) {
        if(commandLine.hasOption("help")) {
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp( "BdArchivePlanner", options);
            throw new NormalExitException();
        }
    }

}
