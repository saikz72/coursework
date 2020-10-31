/**
 * This class contains methods that translates simulation coordinates
 * to screen coordinates
 * @author Saikou Ceesay
 *
 */
public class gUtil {

	//parameters use in the methods
	private static final int HEIGHT = 600;
	private static final double SCALE = HEIGHT/100;

	/**
	 * changes X coordinate to screen X coordinate 
	 * @param X
	 * @return X screen coordinate
	 */

	static int WindowX(double X) {
		return (int) (X*SCALE);

	}

	/**
	 * changes Y coordinate to screen Y coordinate
	 * @param Y
	 * @return Y screen coordinate
	 */

	static int WindowY(double Y) {
		return (int) (HEIGHT - Y*SCALE);
	}

	/**
	 * changes Length to screen Length
	 * @param length
	 * @return screen length
	 */

	static int WindowL(double length) {
		return (int) (length *SCALE);
	}

}

