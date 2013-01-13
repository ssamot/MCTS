package ssamot.visualitation;

import ssamot.mcts.MCTSNode;
import ssamot.mcts.StatisticsNode;

public class MCTSVisualisationHandler<T extends MCTSNode> {
	public void handle(T father, T child, Visualiser vis) {

		vis.refresh();
		
		MCTSCell cChild = new MCTSCell((StatisticsNode) child);

		MCTSCell cFather = new MCTSCell((StatisticsNode) father);

		try {
			// System.out.println(child.getId()+ " " + father.getId());

			vis.addNode(cFather, cChild, child.getAction() + " ");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(0);
		}
	}
}
