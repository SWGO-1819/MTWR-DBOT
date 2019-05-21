import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageHistory;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.managers.GuildController;

public class mapiagame extends ListenerAdapter{
	static int flag=0;
	static final int mapiafull=3;
	static final int docterfull=1;
	static final int policefull=1;
	static String voiceChat=null;
	static String executionMan=null;
	static String cureMan=null;
	static String appointedMan=null;
	static User policeUser=null;
	static int executeChance=0;
	static int cureChance=0;
	static int appointedChance=0;
	static MessageReceivedEvent ready = null;
	static User[] mapiaUser = new User[mapiafull];
	
	static HashMap<String, Integer> peaple = new HashMap<String, Integer>();
	static HashMap<String, Integer> voted = new HashMap<String, Integer>();
	static ArrayList<String> voteChance = new ArrayList<String>();
	static timer timer=null;
	
	@Override
	public void onReady(ReadyEvent event) {
		// TODO Auto-generated method stub
		
	}
	public void onMessageReceived(MessageReceivedEvent event){
		User user=event.getAuthor();
		if(user.isBot())return;
		TextChannel TC = event.getJDA().getTextChannelById(event.getTextChannel().getId());//ä��ID
		Guild setGuild=event.getGuild();
		GuildController GC = new GuildController(setGuild);
		Member mm=setGuild.getMember(user);//���
		String message = event.getMessage().getContentRaw();//�޼�������
		if (message.equals("!stop")) {
            TC.sendMessage("Understood!").queue();
            event.getJDA().removeEventListener(this); // stop listening
        }
		
		if(message.equals("!���Ǿư��ӽ���")) {
			flag=1;
			this.ready=event;
			timer.flag=true;
			TC.sendMessage("����").queue();
		}
		if(message.split(" ")[0].equals("!�������ϱ�")&&flag==1&&message.charAt(0)=='!') {
			voiceChat = message.split(" ")[1];
			setGuild = TC.getGuild();
        	VoiceChannel VC = setGuild.getVoiceChannelsByName(voiceChat,true).get(0);
        	List<Member> member = VC.getMembers();
			int mapia=0;
			int docter=0;
			int police=0;
			int leaght=VC.getMembers().size();
			int i=0;
			int mapiaLeagth=0;
			for(int j=0; j<leaght;j++) {
				voted.put(member.get(i).getUser().getName().toString(),0);//���� ����
			}
			while(true) {
				if(i>=leaght) {
					break;
				}
				Random random = new Random();
				int team = random.nextInt(3);
				
				
				if(team==0&&mapia<mapiafull) {
					
					peaple.put(member.get(i).getUser().getName().toString(),team);//���� ����
					
					String id = message.split(" ")[1];
		            Guild guild = VC.getGuild();
		            
		            mm=guild.getMemberById(member.get(i).getUser().getId().toString());//����id������ͼ� ����� �ֱ�
		        	user=mm.getUser();//ã�� ����� ������ ����
		        	sendPrivateMessage(user, "���Ǿ��Դϴ�");//�� �������� ����
		        	mapiaUser[mapiaLeagth]=user;
		        	mapiaLeagth++;
					mapia++;
					i++;
					continue;
				}
				else if(team==1&&docter<docterfull) {
					
					peaple.put(member.get(i).getUser().getName().toString(),team);//���� ����
					
					String id = message.split(" ")[1];
		            Guild guild = VC.getGuild();
		            
		            mm=guild.getMemberById(member.get(i).getUser().getId().toString());//����id������ͼ� ����� �ֱ�
		        	user=mm.getUser();//ã�� ����� ������ ����
		        	sendPrivateMessage(user, "�ǻ��Դϴ�");//�� �������� ����
		        	
					docter++;
					i++;
					continue;
				}else if(team==2) {
					
					peaple.put(member.get(i).getUser().getName().toString(),team);//���� ����
					String id = message.split(" ")[1];
		            Guild guild = VC.getGuild();
		            
		            mm=guild.getMemberById(member.get(i).getUser().getId().toString());//����id������ͼ� ����� �ֱ�
		        	user=mm.getUser();//ã�� ����� ������ ����
		        	sendPrivateMessage(user, "�ù��Դϴ�");//�� �������� ����
		        	i++;
		        	continue;
				}else if(team==3&&police<policefull) {
					
					peaple.put(member.get(i).getUser().getName().toString(),team);//���� ����
					
					String id = message.split(" ")[1];
		            Guild guild = VC.getGuild();
		            
		            mm=guild.getMemberById(member.get(i).getUser().getId().toString());//����id������ͼ� ����� �ֱ�
		            policeUser=mm.getUser();//ã�� ����� ������ ����
		        	sendPrivateMessage(policeUser, "�����Դϴ�");//�� �������� ����
		        	
		        	
		        	police++;
					i++;
					continue;
				}
			}
			mapiaLeagth=0;
			while(mapiaLeagth<mapiafull) {
				if(mapiaUser[mapiaLeagth]==null) {
					break;
				}
				sendPrivateMessage(mapiaUser[mapiaLeagth],"���ǾƸ���Ʈ:");//�� �������� ����
				int k=0;
				while(k<mapiafull) {
					if(mapiaUser[k]==null) {
						break;
					}
					sendPrivateMessage(mapiaUser[mapiaLeagth], mapiaUser[k].getName());//�� �������� ����
					k++;
				}
				mapiaLeagth++;
			}
		}
		else if(message.equals("!���ӽ���")&&flag==1&&message.charAt(0)=='!') {
			
			try {
				
				timer = new timer(TC);
				timer.start();
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(timer!=null&&message.equals("!�ð�Ȯ��")&&flag==1) {
			StringBuffer messageContent = new StringBuffer();
			messageContent.append(String.format("%.0f",timer.nextTime-System.currentTimeMillis()));
			TC.sendMessage(messageContent).queue();
			messageContent.delete(0,messageContent.length());																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																								
		}
		else if(message.equals("!���Ǿư�������")&&flag==1) {
			stop();
		}
		
		
		
		/*if(peaple.size()<=3&&flag==1) {//�¸� ���� ����
			checkWin(event);
		}*/
		if(message.split(" ")[0].equals("!����")) {
			String tesst = message.split(" ")[1];
			VoiceChannel VC = setGuild.getVoiceChannelsByName("�Ϲ�",true).get(0);
			List<Member> member = VC.getMembers();
			peaple.remove(executionMan);
			int i=0;
			
			while(i<member.size()) {
        		if(member.get(i).getUser().getName().toString().equals(tesst)) {
        			GC.setMute(member.get(i), true).queue();
        			break;
        		}
        		i++;
        	}
			
		}
	}
	
	
	public void onPrivateMessageReceived(PrivateMessageReceivedEvent event) {//������ �޼��� �������� �̺�Ʈ
        if (event.getAuthor().isBot()) return;
        String message = event.getMessage().getContentRaw();
        String man=event.getAuthor().getName().toString();
        if(message.split(" ")[0].equals("!help")){//����
        	String[] help = new String[3];
        	help[0]="!ó�� ����̸� ->�ش��ϴ� �̸��� �ù��� �����մϴ�. ���ǾƸ� �� �� �ֽ��ϴ�. ";
        	help[1]="!ġ�� ����̸� ->�ش��ϴ� �̸��� �ù��� �츳�ϴ�. �ǻ縸 �� �� �ֽ��ϴ�. ";
        	help[2]="!���� ����̸� ->�ش��ϴ� �̸��� ������ �� �� �ֽ��ϴ�. ������ �� �� �ֽ��ϴ�. ";
        	for(int i=0;i<help.length;i++) {
        		sendPrivateMessage(event.getAuthor(), help[i]);
        	}
        }
        if(timer.system==2&&flag==1) {
	        if(message.split(" ")[0].equals("!ó��")&&executeChance==0){//���Ǿ�
	        	String[] mapia=getKey(peaple, 0);
			        for(int i=0;i<mapia.length;i++) {
			        	if(man.equals(mapia[i].toString())) {
			        		executionMan = message.split(" ")[1];
			        		if(peaple.get(executionMan)==null) {
			        			sendPrivateMessage(event.getAuthor(), "�׷������ �����ϴ�.");
				        		executionMan=null;
			        		}
			        		appointedChance=1;
			            	break;
			            }
			        }
	        	
	        }
	        else if(message.split(" ")[0].equals("!ġ��")&&cureChance==0){//�ǻ�
	        	String[] docter=getKey(peaple, 1);
		        for(int i=0;i<docter.length;i++) {
		        	if(man.equals(docter[i].toString())) {
		            	cureMan = message.split(" ")[1];
		            	if(peaple.get(cureMan)==null) {
		        			sendPrivateMessage(event.getAuthor(), "�׷������ �����ϴ�.");
		        			cureMan=null;
		        		}
		            	break;
		            }
		        }
	        }
	        else if(message.split(" ")[0].equals("!����")&&appointedChance==0){//����
	        	String[] police=getKey(peaple, 3);
		        for(int i=0;i<police.length;i++) {
		        	if(man.equals(police[i].toString())) {
		        		appointedMan = message.split(" ")[1];
		        		
		        		if(peaple.get(appointedMan)==null) {
		        			sendPrivateMessage(event.getAuthor(), "�׷������ �����ϴ�.");
		        			appointedMan=null;
		        		}
		        		break;
		            }
		        }
	        }
        }
    }
	public static void sendPrivateMessage(User user, String content) {//���ο��� �޼��� ������ �޼ҵ�
        // openPrivateChannel provides a RestAction<PrivateChannel>
        // which means it supplies you with the resulting channel
        user.openPrivateChannel().queue((channel) ->
        {
            channel.sendMessage(content).queue();
        });
    }
	public static String[] getKey(HashMap<String, Integer> m, Integer value) { //key�� value ������
		Guild setGuild=ready.getGuild();
		GuildController GC = new GuildController(setGuild);
		VoiceChannel VC = setGuild.getVoiceChannelsByName(voiceChat,true).get(0);
		List<Member> member = VC.getMembers();
		String[] man=null;
		if(value==0) {
			man=new String[mapiafull];
		}else if(value==1) {
			man=new String[docterfull];
		}else if(value==2) {
			man=new String[member.size()-mapiafull+docterfull+policefull];
		}else if(value==3) {
			man=new String[policefull];
		}
		int i=0;
		if(m!=null) {
			for(String o: m.keySet()) {
				if(m.get(o).equals(value)) {
					man[i]=o;
				}
				i++;
			}
			return man; 
		}
		return null;
	}
	public static void checkDay(MessageReceivedEvent ready2){//��ħ�� �Ǹ� �㿡 �ߴ� �ൿ�� ����
		TextChannel TC = ready2.getJDA().getTextChannelById(ready2.getTextChannel().getId());//ä��ID
		Guild setGuild=ready2.getGuild();
		GuildController GC = new GuildController(setGuild);
		VoiceChannel VC = setGuild.getVoiceChannelsByName(voiceChat,true).get(0);
		List<Member> member = VC.getMembers();
		if(executionMan!=null||cureMan!=null||appointedMan!=null) {
			if(executionMan!=null&&cureMan!=null&&executionMan.equals(cureMan)) {
				TC.sendMessage(executionMan+"���� �ǻ翡�� ��������ϴ�.").queue();
			}
			else if(executionMan!=null) {
				if(peaple.get(executionMan)!=null) {
					peaple.remove(executionMan);
					for(int j=0; j<VC.getMembers().size();j++) {
						if(member.get(j).getUser().getName().equals(executionMan)) {
							GC.setMute(member.get(j), true).queue();
							break;
						}
					}
					TC.sendMessage(executionMan+"���� ���ش��ϼ̽��ϴ�.").queue();
					
				}
				
			}
			
			if(appointedMan!=null) {
				int type=peaple.get(appointedMan);
				if(type==0) {
					sendPrivateMessage(policeUser, appointedMan+"�� ���Ǿ��Դϴ�.");//�� �������� ����
				}
				else if(type==1) {
					sendPrivateMessage(policeUser, appointedMan+"�� �ǻ��Դϴ�.");//�� �������� ����
				}
				else if(type==2) {
					sendPrivateMessage(policeUser, appointedMan+"�� �ù��Դϴ�.");//�� �������� ����
				}
			}
			executionMan=null;
			cureMan=null;
		}
		return;
	}
	public static void checkWin() {//���� �¸����� üũ
		TextChannel TC = ready.getJDA().getTextChannelById(ready.getTextChannel().getId());//ä��ID
		Guild setGuild=ready.getGuild();
		GuildController GC = new GuildController(setGuild);
		VoiceChannel VC = setGuild.getVoiceChannelsByName(voiceChat,true).get(0);
		List<Member> member = VC.getMembers();
		String[] mapia=getKey(peaple,0);
		String[] docter=getKey(peaple,1);
		String[] citizen=getKey(peaple,2);
		String[] polices=getKey(peaple,3);
		
		if(checkNull(mapia)==0||mapia[0]==null) {
			TC.sendMessage("�ù��� �¸�").queue();
			stop();
		}else if(mapia[0]!=null) {
			if(checkNull(mapia)>0&&checkNull(citizen)+checkNull(docter)+checkNull(polices)<=checkNull(mapia)) {
				
				System.out.println(checkNull(mapia));
				TC.sendMessage("���Ǿ��� �¸�").queue();
				stop();
			}
		}
		
	}
	public static void check() {
		if(ready!=null) {
			Guild setGuild=ready.getGuild();
			GuildController GC = new GuildController(setGuild);
			if(timer.system==0&&flag==1) {//��ħ�̸� �ൿ ����
				
				if(voiceChat!=null) {
					executeChance=0;
					cureChance=0;
					appointedChance=0;
					VoiceChannel VC = setGuild.getVoiceChannelsByName(voiceChat,true).get(0);
					List<Member> member = VC.getMembers();
					for(int j=0; j<VC.getMembers().size();j++) {
						for (Map.Entry<String, Integer> entry : peaple.entrySet()) {
							if(member.get(j).getUser().getName().equals(entry.getKey())) {
								GC.setMute(member.get(j), false).queue();
							}
						}
						
					}
					checkDay(ready);
				}
			}
			if(timer.system==2&&flag==1) {//���̸� �ൿ ����
				
				if(voiceChat!=null) {
					VoiceChannel VC = setGuild.getVoiceChannelsByName(voiceChat,true).get(0);
					List<Member> member = VC.getMembers();
					for(int j=0; j<VC.getMembers().size();j++) {
						GC.setMute(member.get(j), true).queue();
					}
				}
			}
		}
	}
	public static void checkVote() {
		Guild setGuild=ready.getGuild();
		GuildController GC = new GuildController(setGuild);
		TextChannel TC = ready.getJDA().getTextChannelById(ready.getTextChannel().getId());//ä��ID
		if(timer!=null&&timer.system==1&&flag==1) {
			MessageHistory mh = new MessageHistory(TC);
			List<Message> msgs = mh.retrievePast(20).complete();
			
			int i=1;
			String name=null;
			int vote=0;
			
			while(true) {
				String msg = msgs.get(i).getContentRaw();
				if(msg.equals("-----��ǥ�ð�-----")) {
					break;
				}
				
					int k=0;
					if(msg.split(" ")[0].equals("!��ǥ")) {
						if(voteChance.size()==0) {
							name=msg.split(" ")[1];
							
							voted.put(name, 1);
							voteChance.add(msgs.get(i).getAuthor().getName());
						}else {
							while(k<voteChance.size()) {
								if(voteChance.get(k).equals(msgs.get(i).getAuthor().getName())) {
									
									name=msg.split(" ")[1];
									int j=voted.get(name)+1;
									voted.put(name, j);
									voteChance.add(msgs.get(i).getAuthor().getName());
								}
								k++;
							}
						}
					}
				i++;
			}
			
			
			
			
			//���� ��ǥ�� ���� �� ����� ����
			Map.Entry<String, Integer> maxEntry = null;
			Map.Entry<String, Integer> nextMaxEntry = null;
			for (Map.Entry<String, Integer> entry : voted.entrySet()) {

			    if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0) {
			    	maxEntry = entry; //compareTo�� �̿��� ���� ���� map���� maxEntry�� �����
			    }
			}
			for (Map.Entry<String, Integer> entry : voted.entrySet()) {
			    if (maxEntry == null || (entry.getValue().compareTo(maxEntry.getValue()) > 0 && entry.getKey()!=maxEntry.getKey())) {
			    	
			    	nextMaxEntry = entry; //compareTo�� �̿��� ���� ���� map���� maxEntry�� �����

			    }
			}
			
			
			//voted.clear();//���� �ʱ�ȭ
			
			VoiceChannel VC = setGuild.getVoiceChannelsByName(voiceChat,true).get(0);
			List<Member> member = VC.getMembers();
			if(nextMaxEntry!=null) {
				if(maxEntry.getValue()!=nextMaxEntry.getValue()) {
					String maxName = maxEntry.getKey();
					peaple.remove(maxName);
					for(int j=0; j<VC.getMembers().size();j++) {
						if(member.get(j).getUser().getName().equals(maxName)) {
							GC.setMute(member.get(j), true).queue();
						}
					}
					TC.sendMessage(maxName+"���� ó�����ϼ̽��ϴ�.").queue();
				}else {
					TC.sendMessage("�ƹ��� ó�������� �ʾҽ��ϴ�.").queue();
				}
			}else {
				String maxName = maxEntry.getKey();
				
				if(maxName!=null&&maxEntry.getValue()>0) {
					peaple.remove(maxName);
					for(int j=0; j<VC.getMembers().size();j++) {
						if(member.get(j).getUser().getName().equals(maxName)) {
							GC.setMute(member.get(j), true).queue();
						}
					}
					TC.sendMessage(maxName+"���� ó�����ϼ̽��ϴ�.").queue();
				}else {
					TC.sendMessage("�ƹ��� ó�������� �ʾҽ��ϴ�.").queue();
				}
			}
		
		}
	}
	public static void stop() {
		TextChannel TC = ready.getJDA().getTextChannelById(ready.getTextChannel().getId());//ä��ID
		flag=0;
		timer.timerStop();
		peaple=null;
		voted=null;
		ready=null;
		mapiaUser=null;
		TC.sendMessage("����").queue();
	}
	public static int checkNull(String [] s) {
		if(s==null) {
			return 0;
		}
		return s.length;
	}
}
/*
public void onPrivateMessageReceived(PrivateMessageReceivedEvent event) {
    if (event.getAuthor().isBot()) return;
    String message = event.getMessage().getContentRaw();
    
    if(message.equalsIgnoreCase("!����")) {
    	System.out.println(event.getAuthor().toString());
        sendPrivateMessage(event.getAuthor(), "Testing");
    }
}*/
