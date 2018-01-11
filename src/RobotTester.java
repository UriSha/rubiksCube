//import lejos.nxt.Button;
//import lejos.nxt.ColorSensor;
//import lejos.nxt.ColorSensor.Color;
//import lejos.nxt.LCD;
//import lejos.nxt.Motor;
//import lejos.nxt.SensorPort;
//import lejos.nxt.UltrasonicSensor;
//import rubiksCube.Robot.ArmState;
//import rubiksCube.Robot.SensorState;
//
//public class RobotTester {
//
//	public static void main(String[] args) {
//
////		test1_BasicSolution();
//
////		test2_BasicColorScan();
//
////		test3_sensorTesting();
//
////		test4_sensorTesting2();
//
////		test5_sensorTesting3_colorsCheck();
//
//		test6_scanFace();
//
////		test7_cubeScan();
//
////		test8_ultraSonicSensor();
//
////		Robot r = new Robot();
////		Motor.B.rotate(-195);
////		r.sensorToEdge();
////		ColorSensor cs = new ColorSensor(SensorPort.S3);
////		RobotUtils.edgeGetRealColor(cs);
////		r.rightHalfRotate();
////		Button.ENTER.waitForPressAndRelease();
////		r.sensorOffCube();
//
////		Motor.B.rotate(-144);
//
//	}
//
//
//	/* solver for: right downwards, left upwards, top right, bottom left */
//	public static void test1_BasicSolution() {
//        LCD.drawString("test1_BasicSolution", 0, 0);
//        LCD.clear();
//
//        Robot robot = new Robot();
//
//		robot.rightBottomTwist();
//		robot.flip();
//		robot.flip();
//		robot.rightBottomTwist();
//		robot.rightRotate();
//		robot.flip();
//		robot.rightBottomTwist();
//		robot.flip();
//		robot.flip();
//		robot.rightBottomTwist();
//
//		robot.armUp();
//		robot.fullRotate(false);
//	}
//
//	public static void test2_BasicColorScan() {
//        Robot robot = new Robot();
//
//        robot.sensorToEdge();
//        Button.ENTER.waitForPressAndRelease();
//        LCD.clear();
//
//        ColorSensor cs = new ColorSensor(SensorPort.S3);
//        Color color = cs.getColor();
//        System.out.println(RobotUtils.edgeGetRealColor(cs));
//        System.out.println("Color ID: " + cs.getColorID());
//        System.out.println("Red RGB: " + color.getRed());
//        System.out.println("Green RGB: " + color.getGreen());
//        System.out.println("Blue RGB: " + color.getBlue());
//        Button.ENTER.waitForPressAndRelease();
//
//        robot.sensorOffCube();
//	}
//
//
//	public static void test3_sensorTesting1() {
//		Robot r = new Robot();
//        ColorSensor cs = new ColorSensor(SensorPort.S3);
//
//		r.sensorToCenter();
//		Button.ENTER.waitForPressAndRelease();
//        System.out.println();
//    	System.out.println(RobotUtils.centerGetRealColor(cs));
//        Color color = cs.getColor();
//        System.out.println("Color ID: " + cs.getColorID());
//        System.out.println("Red RGB: " + color.getRed());
//        System.out.println("Green RGB: " + color.getGreen());
//        System.out.println("Blue RGB: " + color.getBlue());
//		Button.ENTER.waitForPressAndRelease();
//
//		r.sensorToEdge();
//		Button.ENTER.waitForPressAndRelease();
//		cs = new ColorSensor(SensorPort.S3);
//        System.out.println();
//		System.out.println(RobotUtils.edgeGetRealColor(cs));
//        color = cs.getColor();
//        System.out.println("Color ID: " + cs.getColorID());
//        System.out.println("Red RGB: " + color.getRed());
//        System.out.println("Green RGB: " + color.getGreen());
//        System.out.println("Blue RGB: " + color.getBlue());
//		Button.ENTER.waitForPressAndRelease();
//
//        Motor.A.rotate(135);
//		Button.ENTER.waitForPressAndRelease();
//
//        r.sensorToCorner();
//        Button.ENTER.waitForPressAndRelease();
//        cs = new ColorSensor(SensorPort.S3);
//        System.out.println();
//        System.out.println(RobotUtils.edgeGetRealColor(cs));
//        color = cs.getColor();
//        System.out.println("Color ID: " + cs.getColorID());
//        System.out.println("Red RGB: " + color.getRed());
//        System.out.println("Green RGB: " + color.getGreen());
//        System.out.println("Blue RGB: " + color.getBlue());
//		Button.ENTER.waitForPressAndRelease();
//
//        Motor.A.rotate(136);
//		Button.ENTER.waitForPressAndRelease();
//
//        r.sensorToEdge();
//		Button.ENTER.waitForPressAndRelease();
//        cs = new ColorSensor(SensorPort.S3);
//        System.out.println();
//		System.out.println(RobotUtils.edgeGetRealColor(cs));
//        color = cs.getColor();
//        System.out.println("Color ID: " + cs.getColorID());
//        System.out.println("Red RGB: " + color.getRed());
//        System.out.println("Green RGB: " + color.getGreen());
//        System.out.println("Blue RGB: " + color.getBlue());
//        Button.ENTER.waitForPressAndRelease();
//
//		Motor.B.rotateTo(0);
//
//	}
//
//	public static void test4_sensorTesting2() {
//
//		Motor.A.rotate(136);
//
//        ColorSensor cs = new ColorSensor(SensorPort.S3);
//        Color color;
//
//		Motor.B.rotateTo(133);
//        Button.ENTER.waitForPressAndRelease();
//    	System.out.println(RobotUtils.edgeGetRealColor(cs));
//        color = cs.getColor();
//        System.out.println("Color ID: " + cs.getColorID());
//        System.out.println("Red RGB: " + color.getRed());
//        System.out.println("Green RGB: " + color.getGreen());
//        System.out.println("Blue RGB: " + color.getBlue());
//        Button.ENTER.waitForPressAndRelease();
//
//        for (int i = 1 ; i <= 0 ; ++i) {
//            LCD.clear();
//			Motor.B.rotate(5);
//	        Button.ENTER.waitForPressAndRelease();
//	    	System.out.println(RobotUtils.edgeGetRealColor(cs));
//	        color = cs.getColor();
//	        System.out.println("Color ID: " + cs.getColorID());
//	        System.out.println("Red RGB: " + color.getRed());
//	        System.out.println("Green RGB: " + color.getGreen());
//	        System.out.println("Blue RGB: " + color.getBlue());
//	        Button.ENTER.waitForPressAndRelease();
//        }
//
//        Motor.B.rotateTo(0);
//
//		Motor.A.rotate(136);
//	}
//
//
//	public static void test5_sensorTesting3_colorsCheck() {
//		LCD.drawString("test5_sensorTesting3", 0, 0);
//        LCD.clear();
//
//		Robot r = new Robot();
//
//		r.sensorToCenter();
//
//        ColorSensor cs = new ColorSensor(SensorPort.S3);
//        cs.setFloodlight(true);
//
//        cs.setFloodlight(false);
//		r.sensorOffCube();
//        Button.ENTER.waitForPressAndRelease();
//	}
//
//
//	public static void test6_scanFace() {
//        LCD.drawString("test6_scanFace", 0, 0);
//        LCD.clear();
//
//        System.out.println("hit any button to scan top face");
//        Button.ENTER.waitForPressAndRelease();
//
//        Robot robot = new Robot();
//        ColorSensor cs = new ColorSensor(SensorPort.S3);
////        cs.setFloodlight(true);
//        UltrasonicSensor us = new UltrasonicSensor(SensorPort.S2);
//
//		Cube.Color[][] faceGrid =  robot.scanFace(cs, us);
//		if (faceGrid == null) {
//			robot.reset();
//    		return;
//    	}
//		for (int i = 0 ; i < 3 ; i++) {
//			for(int j = 0 ; j < 3 ; j++) {
//				System.out.print(faceGrid[i][j].toString().charAt(0));
//			}
//			System.out.println();
//		}
//
//        cs.setFloodlight(false);
//
//        Button.ENTER.waitForPressAndRelease();
//
////		StringBuilder result = new StringBuilder();
////		tempClassForPrint.upAndDownFaces(result, faceGrid);
////		System.out.println(result.toString());
//	}
//
//	public static void test7_cubeScan() {
//		Robot r = new Robot();
//		RobotUtils.calibrateColorSensor(r);
//
//		Cube cube = r.scanCube();
//
//		CubeUtils.printCubeNXT(cube);
//	}
//
//	public static void test8_ultraSonicSensor() {
//        UltrasonicSensor us = new UltrasonicSensor(SensorPort.S2);
//        us.capture();
//        us.ping();
//        System.out.println("Distance: " + us.getDistance());
//        System.out.println("Range: " + us.getRange());
//        Button.ENTER.waitForPressAndRelease();
//	}
//}
