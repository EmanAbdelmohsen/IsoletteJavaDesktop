package application;

import application.Constants.StatusEnum;

/**
 * This class is a simple interface class for managing the round execution for each control type (heat and alarm)
 * @author calgiles3
 *
 */

public interface ManageControlInterface {
	public boolean executeRound (StatusEnum mode, double temperature, int[] desiredRange);
}
