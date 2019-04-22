package regulate;

import application.DetectFailure;

/**
 * Class designed to signify the existence of a regulator internal failure.
 * @author calgiles3
 *
 */

public class RegulatorFailure extends DetectFailure{
	
	public String toString() {
		return "There has been a regulator failure.";
	}
	
}
