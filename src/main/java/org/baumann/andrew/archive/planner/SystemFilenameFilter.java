package org.baumann.andrew.archive.planner;

import java.io.File;
import java.io.FilenameFilter;

class SystemFilenameFilter implements FilenameFilter {

	@Override
	public boolean accept(File dir, String name) {
		if(name.startsWith(".")) {
			return false;
		} else {
			return true;
		}
	}

}
