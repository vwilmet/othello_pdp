import com.timermanager.TimerActionEvent;
import com.timermanager.TimerManager;
import com.timermanager.TimerManagerImpl;


public class Main{

	static TimerActionEvent t;
	
	public static void main(String[] args) {
		
		t = new TimerActionEvent() {
			
			@Override
			public void onTimerEnded() {
				System.out.println("timer ended !!");
			}
			
			@Override
			public void onTimerStopped() {
				System.out.println("timer stopped !!");
			}
		};
		
		TimerManager time = new TimerManagerImpl(t);
		time.enableDebug();
		time.startTimer(2);
		
	}
}