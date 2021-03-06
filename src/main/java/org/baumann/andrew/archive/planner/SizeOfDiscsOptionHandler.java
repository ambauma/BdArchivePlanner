package org.baumann.andrew.archive.planner;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.lang3.StringUtils;

class SizeOfDiscsOptionHandler implements OptionHandler {

    private static final long GBtoBytes = 1000000000L;
    private static final long MBtoBytes = 1000000L;
    private static final long KBtoBytes = 1000L;
    
    private final Model model;
    
    
    SizeOfDiscsOptionHandler(Model model) {
        this.model = model;
    }

    public void handle(CommandLine commandLine) {
        if(commandLine.hasOption("size")) {
            String value = StringUtils.strip(commandLine.getOptionValue("size"));
            if(value.length() < 3) {
                throw new AbnormalExitException("Improper size of \"" + value + "\" given.  Acceptable example: --size 25GB ");
            } else {
                String size = value.substring(0, value.length() - 2);
                String unit = value.substring(value.length() - 2, value.length());
                if(!StringUtils.isNumeric(size)) {
                    throw new AbnormalExitException("Improper size of \"" + value + "\" given.  Acceptable example: --size 25GB ");
                }
                if("GB".equals(unit)) {
                    model.setDiscSize(GBtoBytes * Integer.parseInt(size));
                } else if("MB".equals(unit)) {
                    model.setDiscSize(MBtoBytes * Integer.parseInt(size));
                }  else if("KB".equals(unit)) {
                    model.setDiscSize(KBtoBytes * Integer.parseInt(size));
                }  else if("by".equals(unit)) {
                    model.setDiscSize(KBtoBytes * Integer.parseInt(size));
                } else {
                    throw new AbnormalExitException("Unsupported unit of \"" + unit + "\".  Supported units:  GB, MB, KB");
                }
            }
        } else {
            System.out.println("Defaulting disc size to 25GB.");
            model.setDiscSize(GBtoBytes * 25);
        }

    }

}
