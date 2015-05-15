package org.baumann.andrew.archive.planner;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

class NumberOfDiscsOptionHandler implements OptionHandler {

    private final Model model;
    
    
    NumberOfDiscsOptionHandler(Model model) {
        this.model = model;
    }

    public void handle(CommandLine commandLine) {
        if(commandLine.hasOption("number")) {
            String value = StringUtils.strip(commandLine.getOptionValue("number"));
            if(StringUtils.isEmpty(value) || !NumberUtils.isDigits(value)) {
                throw new AbnormalExitException("Improper number of \"" + value + "\" given.  Acceptable example: --number 5 ");
            } else {
                model.setDiscNumber(Integer.parseInt(value));
            }
        }
    }

}
