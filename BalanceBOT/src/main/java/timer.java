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
					TC.sendMessage("���� �Ǿ����ϴ�.").queue();
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
					TC.sendMessage("-----��ǥ�ð�-----").queue();
					try {
						
						Thread.sleep(5000);
						
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					TC.sendMessage("��ǥ����.").queue();
					mapiagame.checkVote();
					
				}else if(system==2) {
					nextTime=System.currentTimeMillis()+5000;
					TC.sendMessage("���� �Ǿ����ϴ�.").queue();
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
