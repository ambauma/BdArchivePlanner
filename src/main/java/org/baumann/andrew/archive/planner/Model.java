package org.baumann.andrew.archive.planner;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Model {
    private long discSize;
    private int discNumber;
    private List<File> includedFiles = new ArrayList<File>();
    private List<File> excludedFiles = new ArrayList<File>();
    
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
    
    public final int getDiscNumber() {
        return discNumber;
    }
    public final void setDiscNumber(int discNumber) {
        this.discNumber = discNumber;
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
