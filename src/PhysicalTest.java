//import java.util.List;
//
//import lejos.nxt.Button;
//import lejos.nxt.LCD;
//import rubiksCube.RobotActionsTranslator.RobotSolvingAction;
//
////import rubiksCube.RobotActionsTranslator.RobotSolvingAction;
//
//public class PhysicalTest {
//
//	public static void main(String[] args) {
////		Cube cube = CubeUtils.initialDoneCube();
////
////		cube.twistBackFace(true);
////		cube.twistFrontFace(false);
////		cube.twistLeftFace(true);
////		cube.twistRightFace(false);
////		cube.twistBackFace(true);
////		cube.twistFrontFace(false);
//
//		Robot r = new Robot();
//		RobotUtils.calibrateColorSensor(r);
//
//		System.out.println("Please locate a cube, press Enter and let the magic begin");
//		Button.ENTER.waitForPressAndRelease();
//		LCD.clear();
//
//		Cube cube = r.scanCube();
//		if (!CubeValidation.isValidCube(cube)) {
//			System.out.println("Invalid cube");
//			CubeUtils.printCubeNXT(cube);
//			return;
//		}
//		CubeUtils.printCubeNXT(cube);
//
//		List<cmd> algoCMDList = Logic.mainAlgorithm(cube);
//
////		System.out.println(algoCMDList.size());
////		for (cmd c : algoCMDList) {
////			System.out.println(c);
////		}
//
//		List<RobotSolvingAction> robotSolvingList = RobotActionsTranslator.translateCommandList(algoCMDList);
//
//		for(RobotSolvingAction action : robotSolvingList) {
//			if(action == RobotSolvingAction.ROBOT_FLIP) {}
//			RobotUtils.executeAction(r, action);
//		}
//		r.armUp();
//		r.fullRotate(false);
//	}
//}
