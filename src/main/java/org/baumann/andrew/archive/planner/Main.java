package org.baumann.andrew.archive.planner;



/**
 * Entry point for the application. Includes package protected setters and
 * getters for default output and error streams, for testing purposes.
 * 
 * @author ambauma
 *
 */
public final class Main {
	
	public static void main(String[] args) {
	    try {
	        Model model = new Model();
    	    OptionsBuilder optionsBuilder = new OptionsBuilder();
            optionsBuilder.add("h", "help", false, "List commands for this application")
                    .add("i", "include", true, "Folders to include, comma separated, and wrapped in quotes")
                    .add(new FileListOptionHandler(model, FileEnum.INCLUDE))
                    .add("e", "exclude", true, "Folders to exclude, comma separated, and wrapped in quotes")
                    .add(new FileListOptionHandler(model, FileEnum.EXCLUDE))
                    .add(new HelpOptionHandler(optionsBuilder)).add("s", "size", true, "Sets the disc size")
                    .add(new SizeOfDiscsOptionHandler(model))
                    .add("n", "number", true, "Sets the number of discs available")
                    .add(new NumberOfDiscsOptionHandler(model))
                    .add("o", "output", true, "File name with path for output")
                    .add(new FileOutputOptionHandler(model));
    	    
    	    optionsBuilder.parse(args).handleCommands().doOutput(model);
	    } catch (NormalExitException nee) {
	        //Do Nothing
	    } catch (AbnormalExitException aee) {
	        System.err.println(aee.getMessage());
	    }
	    
	}
}
