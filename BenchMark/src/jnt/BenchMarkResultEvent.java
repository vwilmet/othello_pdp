package jnt;

public interface BenchMarkResultEvent {

	public void onProgress(int progress);
	public void onStart();
	public void onEnd(BenchMarkResult result);
}
