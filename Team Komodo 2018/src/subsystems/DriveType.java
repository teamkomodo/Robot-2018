package subsystems;

/**
 * Holds a toggleable value that demonstrates
 * either Tank Drive, Arcade1 Drive, or Arcade2 Drive
 */
public class DriveType {
	private int value;

	private static final int TANK_VALUE = 0;
	private static final int ARCADE_1_VALUE = 1;
	private static final int ARCADE_2_VALUE = 2;

	public static final DriveType TANK = new DriveType(TANK_VALUE);
	public static final DriveType ARCADE_1 = new DriveType(ARCADE_1_VALUE);
	public static final DriveType ARCADE_2 = new DriveType(ARCADE_2_VALUE);
	
	private DriveType (int v) {
		value = v;
	}
	
	public Boolean isTank() {
		return value == TANK_VALUE;
	}

	public Boolean isArcade1() {
		return value == ARCADE_1_VALUE;
	}

	public Boolean isArcade2() {
		return value == ARCADE_2_VALUE;
	}
	
	public String toString() {
		if (isTank())
			return "Tank Drive";
		else if (isArcade1())
			return "Arcade1 Drive";
		else if (isArcade2())
			return "Arcade1 Drive";
		else
			return "[ERROR] Drive";
	}
}
