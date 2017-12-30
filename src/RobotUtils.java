import lejos.nxt.Button;
import lejos.nxt.ColorSensor;
import lejos.nxt.LCD;
import lejos.nxt.SensorPort;
import lejos.nxt.UltrasonicSensor;
import lejos.util.Delay;
import rubiksCube.Cube.Color;
import rubiksCube.RobotActionsTranslator.RobotSolvingAction;

public class RobotUtils {
	
	private static double yellowThreshold_edge = 78;
	private static double redThreshold_edge = 38;
	private static double greenThreshold_edge = 38;
	
	private static double yellowThreshold_center = 114;
	private static double redThreshold_center = 59;
	private static double greenThreshold_center = 38;
	
	private static int numberOfCalibrations = 1;
	
	public static Cube.Color edgeGetRealColor(ColorSensor cs){
		int count = 0;
		lejos.nxt.ColorSensor.Color color;
		int id;
		for (int i = 0 ; i <= 5 ; i++) {
			color = cs.getColor();
			id = cs.getColorID();
			switch(id) {
				case 0:
					if (color.getGreen() <= redThreshold_edge) {
//				        System.out.println("Color ID: " + id);
//				        System.out.println("Red RGB: " + color.getRed());
//				        System.out.println("Green RGB: " + color.getGreen());
//				        System.out.println("Blue RGB: " + color.getBlue());
						return Cube.Color.RED;
					}
					else {
//				        System.out.println("Color ID: " + id);
//				        System.out.println("Red RGB: " + color.getRed());
//				        System.out.println("Green RGB: " + color.getGreen());
//				        System.out.println("Blue RGB: " + color.getBlue());
				        return Cube.Color.ORANGE;
					}
				case 1:
//			        System.out.println("Color ID: " + id);
//			        System.out.println("Red RGB: " + color.getRed());
//			        System.out.println("Green RGB: " + color.getGreen());
//			        System.out.println("Blue RGB: " + color.getBlue());
			        if(color.getRed() <= greenThreshold_edge)
			        	return Cube.Color.GREEN;
			        else
			        	return Cube.Color.YELLOW;
				case 2:
//			        System.out.println("Color ID: " + id);
//			        System.out.println("Red RGB: " + color.getRed());
//			        System.out.println("Green RGB: " + color.getGreen());
//			        System.out.println("Blue RGB: " + color.getBlue());
					return Cube.Color.BLUE;
				case 3:
					if (color.getGreen() <= yellowThreshold_edge) {
//				        System.out.println("Color ID: " + id);
//				        System.out.println("Red RGB: " + color.getRed());
//				        System.out.println("Green RGB: " + color.getGreen());
//				        System.out.println("Blue RGB: " + color.getBlue());
				        return Cube.Color.ORANGE;
					}
					else {
//				        System.out.println("Color ID: " + id);
//				        System.out.println("Red RGB: " + color.getRed());
//				        System.out.println("Green RGB: " + color.getGreen());
//				        System.out.println("Blue RGB: " + color.getBlue());
				        if(color.getRed() <= greenThreshold_edge)
				        	return Cube.Color.GREEN;
				        else
				        	return Cube.Color.YELLOW;
					}
				case 6:
//			        System.out.println("Color ID: " + id);
//			        System.out.println("Red RGB: " + color.getRed());
//			        System.out.println("Green RGB: " + color.getGreen());
//			        System.out.println("Blue RGB: " + color.getBlue());
					return Cube.Color.WHITE;
				default:
					break;
				}
		}
		return Cube.Color.NO_COLOR;
	}
	
	public static Cube.Color centerGetRealColor(ColorSensor cs){
		int count = 0;
		lejos.nxt.ColorSensor.Color color;
		int id;
		for (int i = 0 ; i <= 10 ; i++) {
			color = cs.getColor();
			id = cs.getColorID();
			switch(id) {
				case 0:
					if (color.getGreen() <= redThreshold_center)
						return Cube.Color.RED;
					else
						return Cube.Color.ORANGE;
				case 1:
					if(color.getRed() <= greenThreshold_center)
			        	return Cube.Color.GREEN;
			        else
			        	return Cube.Color.YELLOW;
				case 2:
					return Cube.Color.BLUE;
				case 3:
					if (color.getGreen() <= yellowThreshold_center)
						return Cube.Color.ORANGE;
					else {
				        if(color.getRed() <= greenThreshold_center)
				        	return Cube.Color.GREEN;
				        else
				        	return Cube.Color.YELLOW;
					}
				case 6:
					return Cube.Color.WHITE;
				default:
					break;
				}
		}
		return Cube.Color.NO_COLOR;
	}
	
	public static void printFace(Cube.Face face) {
		for(int i = 0 ; i < 3 ; i++) {
			for(int j=0 ; j < 3 ; j++) {
				System.out.print(face.getGrid()[i][j].toString().charAt(0));
				if(j != 2)
					System.out.print(" ");
			}
			System.out.println();
		}
	}

	public static void executeAction(Robot r, RobotSolvingAction action) {
		switch(action) {
			case ROBOT_FLIP:
				r.flip();
				break;
			case ROBOT_LEFT_ROTATE:
				r.leftRotate();
				break;
			case ROBOT_RIGHT_ROTATE:
				r.rightRotate();
				break;
			case ROBOT_LEFT_TWIST:
				r.leftBottomTwist();
				break;
			case ROBOT_RIGHT_TWIST:
				r.rightBottomTwist();
				break;
			default: 
				break;
		}
	}
	
	
	/**
	 * Calibrating the color sensor according to a fixed cube.
	 * Front = Green, Up = Red, Right = Yellow
	 */
	public static void calibrateColorSensor(Robot r) {
		LCD.clear();
		System.out.println("Please locate a fixed cube for calibration and press Enter");
		System.out.println("Red-up, Green-front, Yellow-right");
        Button.ENTER.waitForPressAndRelease();

        ColorSensor cs = new ColorSensor(SensorPort.S3);
//        cs.setFloodlight(true);
        UltrasonicSensor us = new UltrasonicSensor(SensorPort.S2);
        
        double[] redCorners = new double[3];		// RGB: 0-Red, 1-Green, 2-Blue
        double[] redEdges = new double[3];
        double[] redCenters = new double[3];       
        for(int i = 0 ; i < numberOfCalibrations ; i++) {
        	r.scanFaceCalibration(cs, us, redCenters, redEdges, redCorners);
        }
        arrayAveraging(redEdges, 4*numberOfCalibrations);
        arrayAveraging(redCorners, 4*numberOfCalibrations);
        arrayAveraging(redCenters, numberOfCalibrations);
        
        r.flip();
        Delay.msDelay(170);
        r.armUp();
        
        double[] blueCorners = new double[3];
        double[] blueEdges = new double[3];
        double[] blueCenters = new double[3];
        for(int i = 0 ; i < numberOfCalibrations ; i++) {
        	r.scanFaceCalibration(cs, us, blueCenters, blueEdges, blueCorners);
        }
        arrayAveraging(blueEdges, 4*numberOfCalibrations);
        arrayAveraging(blueCorners, 4*numberOfCalibrations);
        arrayAveraging(blueCenters, numberOfCalibrations);
        
        r.flip();
        Delay.msDelay(170);
        r.armUp();
        
        double[] orangeCorners = new double[3];
        double[] orangeEdges = new double[3];
        double[] orangeCenters = new double[3];        
        for(int i = 0 ; i < numberOfCalibrations ; i++) {
        	r.scanFaceCalibration(cs, us, orangeCenters, orangeEdges, orangeCorners);
        }
        arrayAveraging(orangeEdges, 4*numberOfCalibrations);
        arrayAveraging(orangeCorners, 4*numberOfCalibrations);
        arrayAveraging(orangeCenters, numberOfCalibrations);
        
        r.flip();
        Delay.msDelay(170);
        r.armUp();
        
        double[] greenCorners = new double[3];
        double[] greenEdges = new double[3];
        double[] greenCenters = new double[3];
        for(int i = 0 ; i < numberOfCalibrations ; i++) {
        	r.scanFaceCalibration(cs, us, greenCenters, greenEdges, greenCorners);
        }
        arrayAveraging(greenEdges, 4*numberOfCalibrations);
        arrayAveraging(greenCorners, 4*numberOfCalibrations);
        arrayAveraging(greenCenters, numberOfCalibrations);
        
        r.leftRotate();
        r.flip();
        Delay.msDelay(170);
        r.armUp();
        
        double[] whiteCorners = new double[3];
        double[] whiteEdges = new double[3];
        double[] whiteCenters = new double[3];  
        for(int i = 0 ; i < numberOfCalibrations ; i++) {
        	r.scanFaceCalibration(cs, us, whiteCenters, whiteEdges, whiteCorners);
        }
        arrayAveraging(whiteEdges, 4*numberOfCalibrations);
        arrayAveraging(whiteCorners, 4*numberOfCalibrations);
        arrayAveraging(whiteCenters, numberOfCalibrations);
        
        r.flip();
        r.flip(); 
        Delay.msDelay(170);
        r.armUp();
        
        double[] yellowCorners = new double[3];
        double[] yellowEdges = new double[3];
        double[] yellowCenters = new double[3];
        for(int i = 0 ; i < numberOfCalibrations ; i++) {
        	r.scanFaceCalibration(cs, us, yellowCenters, yellowEdges, yellowCorners);
        }
        arrayAveraging(yellowEdges, 4*numberOfCalibrations);
        arrayAveraging(yellowCorners, 4*numberOfCalibrations);
        arrayAveraging(yellowCenters, numberOfCalibrations);
        
        yellowThreshold_edge = (yellowCorners[1] + yellowEdges[1] + orangeCorners[1] + orangeEdges[1])/4;
        yellowThreshold_center = (yellowCenters[1] + orangeCenters[1])/2;
        
        redThreshold_edge = (redCorners[1] + redEdges[1] + orangeCorners[1] + orangeEdges[1])/4;
        redThreshold_center = (redCenters[1] + orangeCenters[1])/2;
        
        greenThreshold_edge = (yellowCorners[0] + yellowEdges[0] + greenCorners[0] + greenEdges[0])/4;
        greenThreshold_center = (yellowCenters[0] + greenCenters[0])/2;
        
        cs.setFloodlight(false);
	}
	
	/**
	 * Adds the values of the second array to the values of the first array
	 */
	public static void arraysAddition(double[] a1, double[] a2) {
		if (a1.length != a2.length)
			return;
		
		for(int i = 0 ; i < a1.length ; i++) {
			a1[i] += a2[i];
		}
	}
	
	/**
	 * Averages the array values by the given factor
	 */
	public static void arrayAveraging(double[] a, int factor) {
		for(int i = 0 ; i < a.length ; i++) {
			a[i] /= (double)factor;
		}
	}
}
