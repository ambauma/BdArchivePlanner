package org.baumann.andrew.archive.planner;

import java.io.File;
import java.io.PrintWriter;

public class OutputWriter {

	public OutputWriter(Model model) throws Exception {
		int disc = 1;
		long bytes = 0L;
		boolean hasFilesTooLarge = false;
		PrintWriter printWriter = null;
		try {
			printWriter = new PrintWriter(model.getOutputFile(), "UTF-8");
			printWriter.println("Disc " + disc + ":");
			for(File file : model.getIncludedFiles()) {
				long fileSize = file.length();
				if(fileSize >= model.getDiscSize()) {
					hasFilesTooLarge = true;
				} else if((fileSize + bytes) < model.getDiscSize()) {
					bytes += fileSize;
					printWriter.println(file.getAbsolutePath());
				} else if((fileSize + bytes) >= model.getDiscSize()){
					disc++;
					bytes = fileSize;
					printWriter.println("Disc " + disc + ":");
					printWriter.println(file.getAbsolutePath());
				}
			}
			if(hasFilesTooLarge) {
				printWriter.println("Too large for disc:");
				for(File file : model.getIncludedFiles()) {
					long fileSize = file.length();
					if(fileSize >= model.getDiscSize()) {
						printWriter.println(file.getAbsolutePath());
					}
				}
			}
		} finally {
			printWriter.close();
		}
		System.out.println("Processing complete.");
	}

}
