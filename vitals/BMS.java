package vitals;

import java.util.HashMap;
import java.util.Map;

public class BMS {

	private Map<String, String> breaches = new HashMap<String, String>();
	private Map<String, String> warnings = new HashMap<String, String>();

	public boolean rangeCheck(String type, float value, float lowRange, float upperRange) {
		boolean isOk = true;

		if (value < lowRange) {
			isOk = false;
			breaches.put(type, Constants.IS_LOW);
		} else if (value > upperRange) {
			isOk = false;
			breaches.put(type, Constants.IS_HIGH);
		}

		checkMinLevelWarning(type, value, lowRange, upperRange, Constants.TOLERANCE_PERCENTAGE);
		checkMaxLevelWarning(type, value, upperRange, Constants.TOLERANCE_PERCENTAGE);

		return isOk;
	}

	public boolean onlyUpperRangeCheck(String type, float value, float upperRange) {
		boolean isOk = true;

		if (value > upperRange) {
			isOk = false;
			breaches.put(type, Constants.IS_HIGH);
		}

		checkMaxLevelWarning(type, value, upperRange, Constants.TOLERANCE_PERCENTAGE);

		return isOk;
	}

	public boolean checkMinLevelWarning(String type, float value, float lowRange, float upperRange, float percentage) {
		boolean willNotFail = true;

		float delta = (upperRange / 100) * percentage;
		if (value >= lowRange && value <= (lowRange + delta)) {
			willNotFail = false;
			warnings.put(type, Constants.IS_LOW);
		}

		return willNotFail;
	}

	public boolean checkMaxLevelWarning(String type, float value, float maxRange, float percentage) {
		boolean willNotFail = true;

		float delta = (maxRange / 100) * percentage;
		if (value >= (maxRange - delta) && value <= maxRange) {
			willNotFail = false;
			warnings.put(type, Constants.IS_HIGH);
		}

		return willNotFail;
	}
	
	public void printBreaches() {
		breaches.forEach((k, v) -> System.out.println((k + " breach " + v)));
	}
	
	public void printWarnings() {
		warnings.forEach((k, v) -> System.out.println((k + " warn " + v)));
	}
}
