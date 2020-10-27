package io.openems.edge.goodwe.et.ess.applypower;

import io.openems.edge.common.statemachine.StateHandler;
import io.openems.edge.goodwe.et.ess.applypower.ApplyPowerStateMachine.State;
import io.openems.edge.goodwe.et.ess.enums.PowerModeEms;

public class BtChargeHandler extends StateHandler<State, Context> {

	@Override
	public State runAndGetNextState(Context context) {

		context.setMode(PowerModeEms.CHARGE_BAT, context.activePowerSetPoint * -1);

		return State.BT_CHARGE;
	}

}
