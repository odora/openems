package io.openems.edge.goodwe.et.ess.applypower;

import io.openems.edge.common.statemachine.StateHandler;
import io.openems.edge.goodwe.et.ess.applypower.ApplyPowerStateMachine.State;
import io.openems.edge.goodwe.et.ess.enums.PowerModeEms;

public class EtEmptyPositivePvHandler extends StateHandler<State, Context> {

	@Override
	public State runAndGetNextState(Context context) {

		context.setMode(PowerModeEms.CHARGE_BAT, context.pvProduction - context.activePowerSetPoint);

		return State.ET_EMPTY_POSITIVE_PV;
	}

}
