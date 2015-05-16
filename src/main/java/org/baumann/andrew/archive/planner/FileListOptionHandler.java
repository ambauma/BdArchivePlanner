package org.baumann.andrew.archive.planner;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.lang3.StringUtils;

class FileListOptionHandler implements OptionHandler {

    private final Model model;
    private final FileEnum includeOrExclude;
    private final SystemFilenameFilter ignoreHiddenFiles = new SystemFilenameFilter();
    
    FileListOptionHandler(Model model, FileEnum includeOrExclude) {
        this.model = model;
        this.includeOrExclude = includeOrExclude;
    }
    
    public void handle(CommandLine commandLine) {
        String includeOrExcludeString = includeOrExclude.getString();
        if(commandLine.hasOption(includeOrExcludeString)) {
            List<String> folderNames = findFolderNames(commandLine, includeOrExcludeString);
            System.out.println(includeOrExcludeString + ":  ");
            for(String foldername : folderNames) {
                File file = new File(foldername);
                if(file.exists()) {
                    handleFiles(file);
                } else {
                    throw new AbnormalExitException("Folder '" + foldername + "' does not exist.");
                }
                if(FileEnum.INCLUDE.equals(includeOrExclude)) {
                    model.getIncludedFiles().add(file);
                } else {
                    model.getExcludedFiles().add(file);
                }
            }
        }
    }

    private void handleFiles(File file) {
        if (file.isDirectory()) {
            handleDirectory(file);
        } else {
            handleFile(file);
        }
    }

    private void handleDirectory(File file) {
        System.out.print("Found folder '" + file.getAbsolutePath() + "'");
		if(file.listFiles(ignoreHiddenFiles).length == 0){
            System.out.print(System.lineSeparator());
        } else {
            System.out.print(", recursing...");
            System.out.print(System.lineSeparator());
            List<File> files = Arrays.asList(file.listFiles());
            Collections.sort(files);
            for(File subFile : files) {
                handleFiles(subFile);
            }
        }
    }

    private void handleFile(File file) {
        System.out.println("Found file '" + file.getAbsolutePath() + "', size: " + file.length() + " bytes");
    }

    private List<String> findFolderNames(CommandLine commandLine, String includeOrExcludeString) {
        String[] values = commandLine.getOptionValues(includeOrExcludeString);
        StringBuilder valueBuilder = new StringBuilder();
        for(String value : values) {
            valueBuilder.append(value);
        }
        List<String> fileNames = Arrays.asList(StringUtils.split(valueBuilder.toString(), ','));
        return fileNames;
    }

}
