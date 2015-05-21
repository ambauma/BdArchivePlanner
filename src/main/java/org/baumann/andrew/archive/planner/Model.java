package org.baumann.andrew.archive.planner;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Model implements Serializable {

    private static final long serialVersionUID = 7143323132392155894L;
    private String outputFile = "output.txt";
    private long discSize;
    private List<File> includedFiles = new ArrayList<File>();
    private List<File> excludedFiles = new ArrayList<File>();
    
    public String getOutputFile() {
        return outputFile;
    }

    public void setOutputFile(String outputFile) {
        this.outputFile = outputFile;
    }

    /**
     * 
     * @return  size in bits
     */
    public final long getDiscSize() {
        return discSize;
    }
    
    /**
     * 
     * @param discSize  size in bits to set
     */
    public final void setDiscSize(long discSize) {
        this.discSize = discSize;
    }
    
    public final List<File> getIncludedFiles() {
        return includedFiles;
    }
    public final void setIncludedFiles(List<File> includedFiles) {
        this.includedFiles = includedFiles;
    }
    public final List<File> getExcludedFiles() {
        return excludedFiles;
    }
    public final void setExcludedFiles(List<File> excludedFiles) {
        this.excludedFiles = excludedFiles;
    }
    
    
}
