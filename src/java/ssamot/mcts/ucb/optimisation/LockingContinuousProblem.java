package ssamot.mcts.ucb.optimisation;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;


public class LockingContinuousProblem extends ContinuousProblem {
	//private final ReentrantLock lock = new ReentrantLock();
	
	//private SynchronousQueue<Object> lock = new SynchronousQueue<Object>(true);
	final CyclicBarrier barrier = new CyclicBarrier(2);
	private double[] x;

	private double score;
	
	private int N; 
	
	@Override
	public double getFtarget() {
		// TODO Auto-generated method stub
		return 0;
	}

	public double[] getX() {
		return x;
	}
	
	public void setScore(double score) {
		this.score = score; 
		this.N++;
	}
	
	public int getN() {
		return N;
	}

	public void unlock() {
		try {
			barrier.await();
			barrier.reset();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public double evaluate(double[] x) {
		// TODO Auto-generated method stub
		this.x = x.clone();
		//lock.add(new Object());
		try {
			barrier.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return score;
	}

}
