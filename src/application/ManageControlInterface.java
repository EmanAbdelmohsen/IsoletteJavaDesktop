package application;

import application.Constants.StatusEnum;

public interface ManageControlInterface {
	public boolean executeRound (StatusEnum mode, double temperature, int[] desiredRange);
}
