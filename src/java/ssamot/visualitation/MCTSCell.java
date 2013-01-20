package ssamot.visualitation;

import ssamot.mcts.MCTSNode;
import ssamot.mcts.StatisticsNode;

import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxGeometry;

public class MCTSCell extends mxCell {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3624378828568270311L;

	private int visits;
	private double val;
	private double playerId;

	private StatisticsNode node;
	
	public MCTSCell() {
		super();
	}


	public MCTSCell(String id, int visits, double val, double playerId) {
		//super("");
		this.visits = visits;
		this.val = val;
		this.playerId = playerId;
		//this.node = node;
		this.setId(id);
		this.setVertex(true);
		this.setConnectable(true);
		mxGeometry geometry = new mxGeometry(20, 20, 40, 80);
        geometry.setRelative(false);
        this.setGeometry(geometry);
        //this.setStyle("fillColor=blue");
        this.setValue(playerId + ": " + ",\n" +  visits + ",\n" + val  );
	}
	
	
	
	public MCTSCell(StatisticsNode node) {
		//super("");
		this.visits = (int) node.getStatistics().getN();
		this.val = node.getStatistics().getMean();
		this.playerId = node.getRewardId();
		//this.node = node;
		this.setId(node.hashCode() + " ");
		//System.out.println(node.hashCode());
		this.setVertex(true);
		this.setConnectable(false);
		mxGeometry geometry = new mxGeometry(300, 10, 60, 60);
        geometry.setRelative(false);
        this.setGeometry(geometry);
        this.setStyle("shape=ellipse");
        this.setValue(playerId + ": " + ",\n" +  visits + ",\n" + val  );
        this.node = node;
	}
	
	public void setMCTSNodeValue() {
		java.text.DecimalFormat df = new
				java.text.DecimalFormat("###.####");
		String v = (df.format(node.getStatistics().getMean()));
		this.setValue(playerId + ": " + ",\n" +  (int) node.getStatistics().getN() + ",\n" + v  );
	
	}
	
	public int getVisits() {
		return visits;
	}
	public void setVisits(int visits) {
		this.visits = visits;
	}
	public double getVal() {
		return val;
	}
	public void setVal(double val) {
		this.val = val;
	}
	public double getPlayerId() {
		return playerId;
	}
	public void setPlayerId(double playerId) {
		this.playerId = playerId;
	}


	@Override
	public String toString() {
		return "visits=" + visits + ", \n val=" + val + ", playerId="
				+ playerId;
	}
	
}
