package monitor;

import application.DetectFailure;

/**
 * Class designed to signify the existence of a monitor internal failure.
 * @author calgiles3
 *
 */

public class MonitorFailure extends DetectFailure{

	public String toString() {
		return "There has been a monitor failure.";
	}
}
