package wndang;


	import java.util.Timer;
	import java.util.TimerTask;
	 
	public class jishi extends TimerTask {
		public static void main(String[] args) {
			Timer timer = new Timer();
			jishi t = new jishi();
			timer.schedule(t, 0, 5000);
		}
	 
		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				zh.jdbcconnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
