package ssamot.test;

import junit.framework.TestCase;
import ssamot.mcts.StatisticsNode;
import ssamot.mcts.UCT;

public class UCTTest extends TestCase {
	UCT uct = null;

	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		UCBTestNode rootNode = new UCBTestNode(-1, 0, 0);
		uct = new UCT(rootNode);
		//uct.runForMs(500);
		uct.runForSim(10000);
	}

	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		super.tearDown();
	}

	public void testUCBBestActionCorrect() {

		StatisticsNode bestNode = uct.getHighestScoringChild(uct.getRootNode());
		assertEquals(0,bestNode.getAction());
	}
	
	public void testUCBStochasticNodesCorrect() {
		StatisticsNode bestNode = uct.getHighestScoringChild(uct.getRootNode());
		//bestNode = uct.getHighestScoringChild(bestNode);
		//bestNode = uct.getHighestScoringChild(bestNode);
		//
		assertTrue((bestNode.getChildren().get(0).getStatistics().getN() - bestNode.getChildren().get(1).getStatistics().getN()) >200);
		//bestNode = uct.getHighestScoringChild(bestNode);
	}
	
}