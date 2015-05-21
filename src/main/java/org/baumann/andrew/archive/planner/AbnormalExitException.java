package org.baumann.andrew.archive.planner;

import java.io.IOException;

public class AbnormalExitException extends RuntimeException {

    private static final long serialVersionUID = 6947426521520579010L;

    public AbnormalExitException(String string) {
        super(string);
    }

	public AbnormalExitException(String message, IOException e) {
		super(message, e);
	}

}
