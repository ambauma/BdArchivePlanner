package org.baumann.andrew.archive.planner;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

final class ArchivePlannerService {

    private Options options;
    private List<OptionHandler> optionHandlers;
    private CommandLine commandLine;
    private Model model = new Model();

    ArchivePlannerService() {
        options = new Options();
        optionHandlers = new ArrayList<OptionHandler>();
        add("h", "help", false, "List commands for this application");
        add(new HelpOptionHandler(this.getOptions()));
        add("i", "include", true, "Folders to include, comma separated, and wrapped in quotes");
        add(new FileListOptionHandler(model, FileEnum.INCLUDE));
        add("e", "exclude", true, "Folders to exclude, comma separated, and wrapped in quotes");
        add(new FileListOptionHandler(model, FileEnum.EXCLUDE));
        add("s", "size", true, "Sets the disc size");
        add(new SizeOfDiscsOptionHandler(model));
        add("o", "output", true, "File name with path for output");
        add(new FileOutputOptionHandler(model));
    }

    void execute(String[] args) throws Exception {
    	parse(args);
	    handleCommands();
	    doOutput();
    }
    
    private void add(String shortCommand, String longCommand, boolean flag, String description) {
        options.addOption(shortCommand, longCommand, flag, description);
    }
    
    private void add(OptionHandler optionHandler) {
        optionHandlers.add(optionHandler);
    }

    private void parse(String... args) {
        CommandLineParser parser = new DefaultParser();
        try {
            commandLine = parser.parse(options, args);
        } catch (ParseException e) {
            throw new AbnormalExitException("Command Parsing failed.  Reason:  " + e.getMessage());
        }
    }

    private void handleCommands() {
        for (OptionHandler optionHandler : optionHandlers) {
            optionHandler.handle(commandLine);
        }
    }
    
    private void doOutput() throws Exception {
    	List<File> finalIncluded = new ArrayList<File>();
    	for(File excluded : model.getExcludedFiles()) {
    		for( File included : model.getIncludedFiles()) {
    			if(!included.getAbsolutePath().startsWith(excluded.getAbsolutePath())) {
    				finalIncluded.add(included);
    			}
    		}
    		model.setIncludedFiles(finalIncluded);
    	}
        new OutputWriter(model);
    }

    Options getOptions() {
        return options;
    }

}
