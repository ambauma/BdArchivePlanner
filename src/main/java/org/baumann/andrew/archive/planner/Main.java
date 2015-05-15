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
            optionsBuilder.add("s", "size", true, "Sets the disc size")
                    .add(new SizeOfDiscsOptionHandler(model))
                    .add("n", "number", true, "Sets the number of discs available")
                    .add(new NumberOfDiscsOptionHandler(model))
                    .add("h", "help", false, "List commands for this application")
                    .add(new HelpOptionHandler(optionsBuilder))
                    .add("i", "include", true, "Folders to include")
                    .add("o", "output", true, "File name with path for output")
                    .add("e", "exclude", true, "Folders to exclude");
    	    
    	    optionsBuilder.parse(args).handleCommands().doOutput();
	    } catch (NormalExitException nee) {
	        //Do Nothing
	    } catch (AbnormalExitException aee) {
	        System.err.println(aee.getMessage());
	    }
	    
	}
}
