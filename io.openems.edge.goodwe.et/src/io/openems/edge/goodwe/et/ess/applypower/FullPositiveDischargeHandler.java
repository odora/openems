package io.openems.edge.goodwe.et.ess.applypower;

import io.openems.edge.common.statemachine.StateHandler;
import io.openems.edge.goodwe.et.ess.PowerModeEms;
import io.openems.edge.goodwe.et.ess.applypower.ApplyPowerStateMachine.State;

public class FullPositiveDischargeHandler extends StateHandler<State, Context> {

	@Override
	public State runAndGetNextState(Context context) {
		context.setMode(PowerModeEms.DISCHARGE_BAT, context.activePowerSetPoint - context.pvProduction);

		return State.FULL_POSITIVE_DISCHARGE;
	}

}
