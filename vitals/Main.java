package vitals;

public class Main {
	static boolean batteryIsOk(float temperature, float soc, float chargeRate) {
		BMS bms = new BMS();
		boolean isTempOk = bms.rangeCheck(Constants.TEMPERATURE, temperature, Constants.LOW_TEMP, Constants.HIGH_TEMP);
		boolean isSocOk = bms.rangeCheck(Constants.SOC, soc, Constants.LOW_SOC, Constants.HIGH_SOC);
		boolean isChargeRateOk = bms.onlyUpperRangeCheck(Constants.CHARGE_RATE, chargeRate, Constants.CHARGE_RATE_LIMIT);
		
		bms.printBreaches();
		bms.printWarnings();
		
		return (isTempOk && isSocOk && isChargeRateOk);
	}

	public static void main(String[] args) {
		assert (batteryIsOk(25, 70, 0.7f) == true);
		assert (batteryIsOk(-1, 70, 0.7f) == false);
		assert (batteryIsOk(2, 70, 0.7f) == false);
		assert (batteryIsOk(35, 81, 0.7f) == false);
		assert (batteryIsOk(35, 18, 0.7f) == false);
		assert (batteryIsOk(35, 50, 1.7f) == false);
	}
}
