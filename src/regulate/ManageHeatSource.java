package regulate;

import application.ManageControlInterface;
import application.Constants.StatusEnum;

/**
 * This class represents the logic for managing the heat source component of the Regulate temperature function.
 * @author calgiles3
 *
 */
public class ManageHeatSource implements ManageControlInterface {

	@Override
	public boolean executeRound(StatusEnum mode, double temperature, int[] desiredRange) {
		return mode == StatusEnum.NORMAL && temperature < desiredRange[0];
	}

}
