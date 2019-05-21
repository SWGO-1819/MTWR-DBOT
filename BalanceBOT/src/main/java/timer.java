import net.dv8tion.jda.api.entities.TextChannel;
public class timer extends Thread{
	
	TextChannel TC = null;
	public static boolean flag=true;
	public static Integer system = 0;
	public static double nextTime=0;
	public timer(TextChannel TC) {
		this.TC=TC;
	}
	public void timerStop() {
		flag=false;
		system=0;
	}
	/*public void gamestart() {
		// TODO Auto-generated method stub
		if(system!=null) {
			flag=false;
			system=0;
		}
		return;
	}*/
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
			while(flag) {
				
				if(system==0) {
					nextTime=System.currentTimeMillis()+5000;
					TC.sendMessage("낮이 되었습니다.").queue();
					mapiagame.checkWin();
					mapiagame.check();
					try {
						
						Thread.sleep(5000);
						
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}else if(system==1) {
					nextTime=System.currentTimeMillis()+5000;
					TC.sendMessage("-----투표시간-----").queue();
					try {
						
						Thread.sleep(5000);
						
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					TC.sendMessage("투표종료.").queue();
					mapiagame.checkVote();
					
				}else if(system==2) {
					nextTime=System.currentTimeMillis()+5000;
					TC.sendMessage("밤이 되었습니다.").queue();
					mapiagame.check();
					try {
						
						Thread.sleep(40000);
						
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
				}
				
				system=(system+1)%3;
			}
	}
	
}
