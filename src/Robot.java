import lejos.nxt.Button;
import lejos.nxt.ColorSensor;
import lejos.nxt.LCD;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.nxt.UltrasonicSensor;
import lejos.util.Delay;
import rubiksCube.Cube.Color;


public class Robot {

    private static final int flip_first = -120;
    private static final int flip_second = -flip_first;

    private static final int right_rotate_first = 320;
    private static final int right_rotate_second = -50;

    private static final int left_rotate_first = -right_rotate_first;
    private static final int left_rotate_second = -right_rotate_second;

    private static final int arm_up = 0;
    private static final int arm_down = -135;
    
    private static final int sensor_off_the_cube = 0;
    private static final int sensor_on_center = 195;
    private static final int sensor_on_edge = 144;
    private static final int sensor_on_corner = 127;

    private static final int ultrasonic_threshold_min = 9;
    private static final int ultrasonic_threshold_max = 50;

    private ArmState currentArmState;
    private SensorState currentSensorState;
    

    public enum ArmState{
        ARM_UP(1),
        ARM_DOWN(-1);

        private final int value;
        ArmState(int val){
            this.value = val;
        }

        public int getValue() {
            return value;
        }
    }

    public enum SensorState{					
    	SENSOR_ON_CENTER(3),
    	SENSOR_ON_CORNER(2),
        SENSOR_ON_EDGE(1),
        SENSOR_OFF_CUBE(0);

        private final int value;
        SensorState(int val){
            this.value = val;
        }

        public int getValue() {
            return value;
        }
    }

    public Robot(){
        currentArmState = ArmState.ARM_UP;
        currentSensorState = SensorState.SENSOR_OFF_CUBE;
    }
    
    public Robot(ArmState armState, SensorState sensorState){
        currentArmState = armState;
        currentSensorState = sensorState;
    }
    
    public void reset() {
    	sensorOffCube();
    	armUp();
    }
    
    public ArmState getCurrentArmState() {
    	return currentArmState;
    }
    
    public void setCurrentArmState(ArmState armState) {
    	currentArmState = armState;
    }
    
    public SensorState getCurrentSensorState() {
    	return currentSensorState;
    }
    
    public void setCurrentSensorState(SensorState sensorState) {
    	currentSensorState = sensorState;
    }

    public void rightRotate(){
        armUp();
        Motor.A.rotate(right_rotate_first + right_rotate_second);
    }
    
    public void rightHalfRotate(){
        armUp();
        Motor.A.rotate((right_rotate_first + right_rotate_second)/2);
    }

    public void leftRotate(){
        armUp();
        Motor.A.rotate(left_rotate_first + left_rotate_second);
    }
    
    public void lefttHalfRotate(){
        armUp();
        Motor.A.rotate((left_rotate_first + left_rotate_second)/2);
    }
    
    public void fullRotate(boolean parallelism) {
        armUp();
        Motor.A.rotate(4*(right_rotate_first + right_rotate_second), parallelism);
    }

    public void rightBottomTwist(){
    	armDown();
        Motor.A.rotate(right_rotate_first);
        Motor.A.rotate(right_rotate_second);
    }

    public void leftBottomTwist(){
    	armDown();
        Motor.A.rotate(left_rotate_first);
        Motor.A.rotate(left_rotate_second);
    }

    public void flip(){
        armDown();	
        Motor.C.rotate(flip_first);
        Motor.C.rotate(flip_second);
    }
    
    public void armUp() {
    	Motor.C.rotateTo(arm_up);
    	currentArmState = ArmState.ARM_UP;
    }
    
    public void armDown() {
    	Motor.C.rotateTo(arm_down);
    	currentArmState = ArmState.ARM_DOWN;
    }
    
    public void sensorToCenter() {
    	Motor.B.rotateTo(sensor_on_center);    	
        currentSensorState = SensorState.SENSOR_ON_CENTER;
    }
    
    public void sensorToEdge() {
    	Motor.B.rotateTo(sensor_on_edge);   	
    	currentSensorState = SensorState.SENSOR_ON_EDGE;
    }
    
    public void sensorToCorner() {
    	Motor.B.rotateTo(sensor_on_corner);
    	currentSensorState = SensorState.SENSOR_ON_CORNER;
    }
    
    public void sensorOffCube() {  
    	Motor.B.rotateTo(sensor_off_the_cube);
    	currentSensorState = SensorState.SENSOR_OFF_CUBE;
    }
    
    public int cubeOutOfRange(UltrasonicSensor us) {
    	us.capture();
    	us.ping();
    	if (us.getDistance() < ultrasonic_threshold_min || us.getDistance() > ultrasonic_threshold_max) {
    		System.out.println(us.getDistance());
    		return 0;
    	}
    	System.out.println(us.getDistance());
    	return 1;
    }
    
    public Cube scanCube() {
    	    	
    	Color[][][] cubeMatrix = new Color[6][Cube.getDim()][Cube.getDim()];
    	
        ColorSensor cs = new ColorSensor(SensorPort.S3);
//        cs.setFloodlight(true);
        UltrasonicSensor us = new UltrasonicSensor(SensorPort.S2);
    	
    	Cube.Color[][] faceGrid = scanFace(cs, us);
    	if (faceGrid == null) {
    		reset();
    		return null;
    	}
    	cubeMatrix[5] = faceGrid;
    	
    	flip();
    	Delay.msDelay(250);
    	armUp();
    	faceGrid = scanFace(cs, us);
    	if (faceGrid == null) {
    		reset();
    		return null;
    	}
    	cubeMatrix[3] = faceGrid;
    	
    	flip();
    	Delay.msDelay(250);
    	armUp();
    	faceGrid = scanFace(cs, us);
    	if (faceGrid == null) {
    		reset();
    		return null;
    	}
    	cubeMatrix[4] = faceGrid;
    	
    	flip();
    	Delay.msDelay(250);
    	armUp();
    	faceGrid = scanFace(cs, us);
    	if (faceGrid == null) {
    		reset();
    		return null;
    	}
    	cubeMatrix[1] = faceGrid;
    	
    	rightRotate();
    	flip();
    	Delay.msDelay(250);
    	armUp();
    	faceGrid = scanFace(cs, us);
    	if (faceGrid == null) {
    		reset();
    		return null;
    	}
    	cubeMatrix[2] = faceGrid;
    	
    	flip();
    	Delay.msDelay(250);
    	flip();
    	Delay.msDelay(250);
    	armUp();
    	faceGrid = scanFace(cs, us);
    	if (faceGrid == null) {
    		reset();
    		return null;
    	}
    	cubeMatrix[0] = faceGrid;
    	
        cs.setFloodlight(false);
    	
    	Cube cube = new Cube(cubeMatrix);
    	cube.getLeft().clockwiseFixInnerValues();
    	cube.getFront().clockwiseFixInnerValues();
    	cube.getRight().clockwiseFixInnerValues();
    	cube.getBack().clockwiseFixInnerValues();
    	
    	return cube;
    }
    
    
    public Cube.Color[][] scanFace(ColorSensor cs, UltrasonicSensor us) {
    	
    	Cube.Color[][] faceGrid = new Cube.Color[Cube.getDim()][Cube.getDim()];
    	
        Delay.msDelay(250);
    	sensorToCenter();
    	faceGrid[1][1] = RobotUtils.centerGetRealColor(cs);
//    	System.out.println(faceGrid[1][1]);
//        Button.waitForAnyPress();
//        LCD.clear();
    	int flag = 1;
        
        flag = scanTile(faceGrid, cs, us, 0, 1);
        if (flag == 0) {
        	return null;
        }

//    	fullRotate(true);
    	
        flag = scanTile(faceGrid, cs, us, 0, 2);
        if (flag == 0) {
        	return null;
        }

        flag = scanTile(faceGrid, cs, us, 1, 2);
        if (flag == 0) {
        	return null;
        }

        flag = scanTile(faceGrid, cs, us, 2, 2);
        if (flag == 0) {
        	return null;
        }

        flag = scanTile(faceGrid, cs, us, 2, 1);
        if (flag == 0) {
        	return null;
        }
   	
        flag = scanTile(faceGrid, cs, us, 2, 0);
        if (flag == 0) {
        	return null;
        }

        flag = scanTile(faceGrid, cs, us, 1, 0);
        if (flag == 0) {
        	return null;
        }

        flag = scanTile(faceGrid, cs, us, 0, 0);
        if (flag == 0) {
        	return null;
        }

        sensorOffCube();
    	
    	return faceGrid;
    }
    
    public int scanTile(Cube.Color[][] faceGrid, ColorSensor cs, UltrasonicSensor us, int i, int j) {
//    	if (cubeOutOfRange(us) == 0) {
//    		return 0;
//    	}
    	if (currentSensorState == SensorState.SENSOR_OFF_CUBE)
    		return 0;
    	else if (currentSensorState == SensorState.SENSOR_ON_CORNER || currentSensorState == SensorState.SENSOR_ON_CENTER)
    		sensorToEdge();
    	else
    		sensorToCorner();
    	
    	Delay.msDelay(200);
    	if (i == 1 && j == 1)
        	faceGrid[i][j] = RobotUtils.centerGetRealColor(cs);
    	else
    		faceGrid[i][j] = RobotUtils.edgeGetRealColor(cs);
    	
    	rightHalfRotate();
    	
    	return 1;
    }
    
    
    public Cube.Color[][] scanFaceCalibration(ColorSensor cs, UltrasonicSensor us, double[] centers, double[] edges, double[] corners) {
    	
    	Cube.Color[][] faceGrid = new Cube.Color[Cube.getDim()][Cube.getDim()];
    	
        Delay.msDelay(250);
    	sensorToCenter();
    	
    	double[] currentTileRGB = scanTileCalibration(cs, us);
        RobotUtils.arraysAddition(centers, currentTileRGB);
    	
        for (int i = 0 ; i < 8 ; i++) {
        	currentTileRGB = scanTileCalibration(cs, us);
        	if(i % 2 == 0)
        		RobotUtils.arraysAddition(edges, currentTileRGB);
        	else
        		RobotUtils.arraysAddition(corners, currentTileRGB);
        }
        sensorOffCube();
    	
    	return faceGrid;
    }
    
    public double[] scanTileCalibration(ColorSensor cs, UltrasonicSensor us) {
//    	if (cubeOutOfRange(us) == 0) {
//    		return 0;
//    	}
    	if (currentSensorState == SensorState.SENSOR_OFF_CUBE)
    		return null;
    	
    	Delay.msDelay(200);
    	
    	double[] res = new double[3];
    	res[0] = cs.getColor().getRed();
    	res[1] = cs.getColor().getGreen();
    	res[2] = cs.getColor().getBlue();
    	
    	if (currentSensorState == SensorState.SENSOR_ON_EDGE) {
    		rightHalfRotate();
    		sensorToCorner();
    	}
    	else if(currentSensorState == SensorState.SENSOR_ON_CORNER) {
    		rightHalfRotate();
    		sensorToEdge();
    	}
    	else if(currentSensorState == SensorState.SENSOR_ON_CENTER)
    		sensorToEdge();
    	
    	return res;
    }
}

