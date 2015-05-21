package org.baumann.andrew.archive.planner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

final class OptionsBuilder {

    private Options options;
    private List<OptionHandler> optionHandlers;
    private CommandLine commandLine;

    OptionsBuilder() {
        options = new Options();
        optionHandlers = new ArrayList<OptionHandler>();
    }

    OptionsBuilder add(String shortCommand, String longCommand, boolean flag, String description) {
        options.addOption(shortCommand, longCommand, flag, description);
        return this;
    }
    
    OptionsBuilder add(OptionHandler optionHandler) {
        optionHandlers.add(optionHandler);
        return this;
    }

    OptionsBuilder parse(String... args) {
        CommandLineParser parser = new DefaultParser();
        try {
            commandLine = parser.parse(options, args);
        } catch (ParseException e) {
            throw new AbnormalExitException("Command Parsing failed.  Reason:  " + e.getMessage());
        }
        return this;
    }

    OptionsBuilder handleCommands() {
        for (OptionHandler optionHandler : optionHandlers) {
            optionHandler.handle(commandLine);
        }
        return this;
    }
    
    void doOutput(final Model model) {
        new OutputWriter(model);
    }

    Options getOptions() {
        return options;
    }

}
