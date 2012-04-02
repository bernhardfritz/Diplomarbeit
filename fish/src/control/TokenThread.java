package control;

public class TokenThread extends Thread {
	
	public void giveToken() {
		if(!FetchThread.token&&!FeedThread.token) {
			if(FetchThread.requestToken) {
				FetchThread.token=true;
				FetchThread.requestToken=false;
			}
			if(FeedThread.requestToken) {
				FeedThread.token=true;
				FeedThread.requestToken=false;
			}
		}
	}
	
	public void run() {
		while(true) {
			while(FetchThread.token||FeedThread.token) {
				Tool.wait(1000);
			}
			giveToken();
			Tool.wait(1000);
		}
	}
}
