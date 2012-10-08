package ssamot.mcts.backpropagators;

import java.util.List;

import ssamot.mcts.MCTSNode;

public interface Backpropagator<T extends MCTSNode> {
    public void backpropagate(List<T> nodes, List<Double> reward);
}
