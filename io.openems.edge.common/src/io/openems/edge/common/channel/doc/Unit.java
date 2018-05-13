package io.openems.edge.common.channel.doc;

import io.openems.common.types.OpenemsType;

public enum Unit {
	/* No Unit */
	NONE(""),
	/* Generic */
	PERCENT("%"),
	/* Active Power */
	WATT("W"),
	/* Reactive Power */
	VOLT_AMPERE_REACTIVE("var"),
	/* Apparent Power */
	VOLT_AMPERE("VA"),
	/* Voltage */
	/**
	 * Unit of Voltage [V]
	 */
	VOLT("V"),
	/**
	 * Unit of Voltage [mV]
	 */
	MILLIVOLT("mV", VOLT, -3),
	/* Ampere */
	AMPERE("A"), MILLIAMPERE("mA", AMPERE, -3),
	/* Energy */
	WATT_HOURS("Wh"),
	/*
	 * Frequency
	 */
	/**
	 * Unit of Frequency [Hz]
	 */
	HERTZ("Hz"),
	/**
	 * Unit of Frequency [mHz]
	 */
	MILLIHERTZ("mHz", HERTZ, -3),
	/* Temperature */
	DEGREE_CELCIUS("�C"),
	/**
	 * On/Off
	 */
	ON_OFF("");

	private final Unit baseUnit;
	private final int scaleFactor;
	private final String symbol;

	private Unit(String symbol) {
		this(symbol, null, 0);
	}

	private Unit(String symbol, Unit baseUnit, int scaleFactor) {
		this.symbol = symbol;
		this.baseUnit = baseUnit;
		this.scaleFactor = scaleFactor;
	}

	public Unit getBaseUnit() {
		return baseUnit;
	}

	public int getAsBaseUnit(int value) {
		return (int) (value * Math.pow(10, this.scaleFactor));
	}

	public String getSymbol() {
		return symbol;
	}

	public String format(Object value, OpenemsType type) {
		switch (this) {
		case NONE:
			return value.toString();
		case AMPERE:
		case DEGREE_CELCIUS:
		case HERTZ:
		case MILLIAMPERE:
		case MILLIHERTZ:
		case MILLIVOLT:
		case PERCENT:
		case VOLT:
		case VOLT_AMPERE:
		case VOLT_AMPERE_REACTIVE:
		case WATT:
		case WATT_HOURS:
			return value + " " + this.symbol;
		case ON_OFF:
			boolean booleanValue = (Boolean) value;
			return booleanValue ? "ON" : "OFF";
		}
		return "FORMAT_ERROR"; // should never happen, if 'switch' is complete
	}

	public String formatAsBaseUnit(Object value, OpenemsType type) {
		if (this.baseUnit != null) {
			switch (type) {
			case SHORT:
			case INTEGER:
			case LONG:
			case FLOAT:
				return this.baseUnit.formatAsBaseUnit(this.getAsBaseUnit((int) value), type);
			case BOOLEAN:
				return this.baseUnit.formatAsBaseUnit(value, type);
			}
		} else {
			this.format(value, type);
		}
		return "FORMAT_ERROR"; // should never happen, if 'switch' is complete
	}
}
