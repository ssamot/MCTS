package ssamot.visualitation;

import com.mxgraph.view.mxGraph;

public class MCTSGraph extends mxGraph {
	// Overrides method to disallow edge label editing
	public boolean isCellEditable(Object cell) {
		return !getModel().isEdge(cell);
	}

	public String convertValueToString(Object cell)
	{
		//if (cell instanceof MCTSCell)
		//{	
			MCTSCell mCell = (MCTSCell)cell;
			return mCell.getPlayerId() + ": " + ",\n" +  mCell.getVisits() + ",\n" + mCell.getValue(); 
		//}

		//return super.convertValueToString(cell);
	}
	
}
