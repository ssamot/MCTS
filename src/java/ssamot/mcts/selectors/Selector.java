package ssamot.mcts.selectors;

import ssamot.mcts.MCTSNode;

public interface Selector<T extends MCTSNode> {
    public T selectChild(T node);
}
