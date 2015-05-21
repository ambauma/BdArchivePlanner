package org.baumann.andrew.archive.planner;



/**
 * Entry point for the application. Includes package protected setters and
 * getters for default output and error streams, for testing purposes.
 * 
 * @author ambauma
 *
 */
public final class Main {
	
	public static void main(String[] args) throws Exception {
	    try {
    	    new ArchivePlannerService().execute(args);
	    } catch (NormalExitException nee) {
	        //Do Nothing
	    } catch (AbnormalExitException aee) {
	        System.err.println(aee.getMessage());
	    }
	    
	}
}
