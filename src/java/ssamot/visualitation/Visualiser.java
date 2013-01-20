package ssamot.visualitation;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;

import com.mxgraph.io.mxCodecRegistry;
import com.mxgraph.io.mxObjectCodec;
import com.mxgraph.layout.mxCompactTreeLayout;
import com.mxgraph.model.mxCell;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;

public class Visualiser extends JFrame {
	// public static drawNode(String parent, String child)
	private mxGraph graph = new mxGraph();
	private HashMap<String, MCTSCell> nodes = new HashMap<String, MCTSCell>();

	
	public HashMap<String, MCTSCell> getNodes() {
		return nodes;
	}
	
	
	public Visualiser() {
		graph.getModel().beginUpdate();
		try {
			graph.setAutoSizeCells(true);

		} finally {
			graph.getModel().endUpdate();
		}

		final mxGraphComponent graphComponent = new mxGraphComponent(graph);
		getContentPane().add(graphComponent);
		
		graphComponent.getGraphControl().addMouseListener(new MouseAdapter()
		{

			public void mousePressed(MouseEvent e)
			{
				Object cell = graphComponent.getCellAt(e.getX(), e.getY());

				if (cell != null)
				{
					System.out.println("cell="+graph.getLabel(cell));
				}
				
				
			}
			
			
		});
	}

	public void init() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 320);
		setVisible(true);
		//graph.
		mxCodecRegistry.addPackage("ssamot.visualitation");
		mxCodecRegistry.register(new mxObjectCodec(new MCTSCell()));
	}
	public static void main(String[] args) {
		Visualiser frame = new Visualiser();
		frame.init();
		

		MCTSCell root = new MCTSCell("root", 1, 2.0, 1);

		MCTSCell child1 = new MCTSCell("child1", 2, 2.0, 2);
		MCTSCell child2 = new MCTSCell("child2", 2, 2.0, 2);
		MCTSCell child3 = new MCTSCell("child3", 2, 2.0, 2);
		MCTSCell child4 = new MCTSCell("child4", 2, 2.0, 2);
		MCTSCell child5 = new MCTSCell("child5", 2, 2.0, 2);

		try {
			frame.addNode(null, root, "1");
			frame.addNode(root, child1, "2");
			frame.addNode(child1, child2, "3");
			frame.addNode(child1, child5, "4");
			frame.addNode(child2, child3, "5");
			frame.addNode(child2, child4, "6");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// frame.mupdate();
		// frame.addNode(root, child3);
		// frame.addNode(child2, child4);

	}

	public void addNode(mxCell father, MCTSCell child, String edgeName) throws Exception {

		if(nodes.containsKey(child.getId())) {
			//System.exit(0);
			//throw new Exception("Node allready in tree");
			// ignore nodes that are allready there
			//System.exit(0);
			return ; 
		}
		
		if(father!=null && !nodes.containsKey(father.getId())) {
			throw new Exception("Unkown Father Node");
		}
		
		nodes.put(child.getId(), child);
		graph.getModel().beginUpdate();
		Object parent = graph.getDefaultParent();
		try {
			// graph.setAutoSizeCells(true);
			// graph.insertVertex(arg0, arg1, arg2, arg3, arg4, arg5, arg6)
		//	Object v1 = graph.insertVertex(parent, null, child, 20, 20, 80,
		//	 80);
			// Object v2 = graph.insertVertex(f, null, child, 20, 20,
			// 80, 30);

			graph.addCell(child, parent);
			if (father != null) {
				//System.out.println("drawing edge");
				MCTSCell edgeFather = nodes.get(father.getId());
				
				graph.insertEdge(parent, null,edgeName, edgeFather, child);
			}
			//mxGraphComponent graphComponent = new mxGraphComponent(graph);
			//getContentPane().add(graphComponent);
		} finally {
			graph.getModel().endUpdate();
			//System.err.println("dneind bup");
		}
		mxCompactTreeLayout layout = new mxCompactTreeLayout(graph);
		layout.setHorizontal(false);
		layout.execute(graph.getDefaultParent());
		
	
		//graph.
		//Thread.sleep(1000);

		// 
	}
	
	public void refresh() {
		// update all values
		for (Map.Entry<String,MCTSCell> entry  : nodes.entrySet()) {
			entry.getValue().setMCTSNodeValue();
		}
		
		graph.refresh();
	}

	
}
