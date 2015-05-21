package org.baumann.andrew.archive.planner;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;

public class OutputWriter {

	public OutputWriter(Model model) {
		System.out.println("Processing complete.");
		int disc = 1;
		Collections.sort(model.getIncludedFiles());
		PrintWriter printWriter = null;
		try {
			printWriter = new PrintWriter(model.getOutputFile(), "UTF-8");
			printWriter.println("Disc " + disc + ":");
			for(File file : model.getIncludedFiles()) {
				printWriter.println(file.getAbsolutePath());
				
				
			}
			
		} catch (IOException e) {
			throw new AbnormalExitException(e.getMessage(), e);
		} finally {
			printWriter.close();
		}
		
	}

}
