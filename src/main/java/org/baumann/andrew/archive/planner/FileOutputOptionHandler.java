package org.baumann.andrew.archive.planner;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.lang3.StringUtils;

class FileOutputOptionHandler implements OptionHandler {

    private final Model model;
    
    public FileOutputOptionHandler(Model model) {
        this.model = model;
    }

    public void handle(CommandLine commandLine) {
        if(commandLine.hasOption("output")) {
            String value = StringUtils.strip(commandLine.getOptionValue("output"));
            model.setOutputFile(value);
        } else {
            System.out.println("Defaulting output to '" + model.getOutputFile() + "'");
        }
    }

}
