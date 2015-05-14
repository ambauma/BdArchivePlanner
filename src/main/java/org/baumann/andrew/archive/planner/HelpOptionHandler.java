package org.baumann.andrew.archive.planner;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.HelpFormatter;

class HelpOptionHandler implements OptionHandler {

    final OptionsBuilder optionsBuilder;
    
    HelpOptionHandler(OptionsBuilder optionsBuilder) {
        this.optionsBuilder = optionsBuilder;
    }
    
    public void handle(CommandLine commandLine) {
        if(commandLine.hasOption("help")) {
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp( "BdArchivePlanner", optionsBuilder.getOptions());
            throw new NormalExitException();
        }
    }

}
