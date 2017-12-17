import java.util.List;

//import rubiksCube.RobotActionsTranslator.RobotSolvingAction;

public class PhysicalTest {

	public static void main(String[] args) {
		Cube cube = CubeUtils.initialDoneCube();
		
		cube.twistBackFace(true);
		cube.twistFrontFace(false);
		cube.twistLeftFace(true);
		cube.twistRightFace(false);
		cube.twistBackFace(true);
		cube.twistFrontFace(false);

		List<cmd> algoCMDList = Logic.mainAlgorithm(cube);
		
		System.out.println(algoCMDList.size());
		for (cmd c : algoCMDList) {
			System.out.println(c);
		}
		 
//		List<RobotSolvingAction> robotSolvingList = RobotActionsTranslator.translateCommandList(algoCMDList);
//		
//		Robot r = new Robot();
//		
//		for(RobotSolvingAction action : robotSolvingList) {
//			if(action == RobotSolvingAction.ROBOT_FLIP) {}
//			RobotUtils.executeAction(r, action);
//		}
//		r.armUp();	
		
	}
}
