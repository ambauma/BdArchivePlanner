package org.baumann.andrew.archive.planner;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.lang3.StringUtils;

class NumberOfDiscsOptionHandler implements OptionHandler {

    private final Model model;
    
    
    NumberOfDiscsOptionHandler(Model model) {
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
                if("GB".equalsIgnoreCase(unit)) {
                    model.setDiscSize(Integer.parseInt(size));
                } else {
                    throw new AbnormalExitException("Unsupported unit of \"" + unit + "\".  Supported units:  GB");
                }
            }
            
        } else {
            System.out.println("Defaulting disc size to 25GB.");
        }

    }

}
