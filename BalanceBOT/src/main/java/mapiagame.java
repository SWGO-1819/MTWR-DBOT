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
		TextChannel TC = event.getJDA().getTextChannelById(event.getTextChannel().getId());//채팅ID
		Guild setGuild=event.getGuild();
		GuildController GC = new GuildController(setGuild);
		Member mm=setGuild.getMember(user);//멤버
		String message = event.getMessage().getContentRaw();//메세지담음
		if (message.equals("!stop")) {
            TC.sendMessage("Understood!").queue();
            event.getJDA().removeEventListener(this); // stop listening
        }
		
		if(message.equals("!마피아게임시작")) {
			flag=1;
			this.ready=event;
			timer.flag=true;
			TC.sendMessage("시작").queue();
		}
		if(message.split(" ")[0].equals("!역할정하기")&&flag==1&&message.charAt(0)=='!') {
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
				voted.put(member.get(i).getUser().getName().toString(),0);//팀을 저장
			}
			while(true) {
				if(i>=leaght) {
					break;
				}
				Random random = new Random();
				int team = random.nextInt(3);
				
				
				if(team==0&&mapia<mapiafull) {
					
					peaple.put(member.get(i).getUser().getName().toString(),team);//팀을 저장
					
					String id = message.split(" ")[1];
		            Guild guild = VC.getGuild();
		            
		            mm=guild.getMemberById(member.get(i).getUser().getId().toString());//유저id가지고와서 멤버에 넣기
		        	user=mm.getUser();//찾은 멤버를 유저에 넣음
		        	sendPrivateMessage(user, "마피아입니다");//그 유저에게 넣음
		        	mapiaUser[mapiaLeagth]=user;
		        	mapiaLeagth++;
					mapia++;
					i++;
					continue;
				}
				else if(team==1&&docter<docterfull) {
					
					peaple.put(member.get(i).getUser().getName().toString(),team);//팀을 저장
					
					String id = message.split(" ")[1];
		            Guild guild = VC.getGuild();
		            
		            mm=guild.getMemberById(member.get(i).getUser().getId().toString());//유저id가지고와서 멤버에 넣기
		        	user=mm.getUser();//찾은 멤버를 유저에 넣음
		        	sendPrivateMessage(user, "의사입니다");//그 유저에게 넣음
		        	
					docter++;
					i++;
					continue;
				}else if(team==2) {
					
					peaple.put(member.get(i).getUser().getName().toString(),team);//팀을 저장
					String id = message.split(" ")[1];
		            Guild guild = VC.getGuild();
		            
		            mm=guild.getMemberById(member.get(i).getUser().getId().toString());//유저id가지고와서 멤버에 넣기
		        	user=mm.getUser();//찾은 멤버를 유저에 넣음
		        	sendPrivateMessage(user, "시민입니다");//그 유저에게 넣음
		        	i++;
		        	continue;
				}else if(team==3&&police<policefull) {
					
					peaple.put(member.get(i).getUser().getName().toString(),team);//팀을 저장
					
					String id = message.split(" ")[1];
		            Guild guild = VC.getGuild();
		            
		            mm=guild.getMemberById(member.get(i).getUser().getId().toString());//유저id가지고와서 멤버에 넣기
		            policeUser=mm.getUser();//찾은 멤버를 유저에 넣음
		        	sendPrivateMessage(policeUser, "경찰입니다");//그 유저에게 넣음
		        	
		        	
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
				sendPrivateMessage(mapiaUser[mapiaLeagth],"마피아리스트:");//그 유저에게 넣음
				int k=0;
				while(k<mapiafull) {
					if(mapiaUser[k]==null) {
						break;
					}
					sendPrivateMessage(mapiaUser[mapiaLeagth], mapiaUser[k].getName());//그 유저에게 넣음
					k++;
				}
				mapiaLeagth++;
			}
		}
		else if(message.equals("!게임시작")&&flag==1&&message.charAt(0)=='!') {
			
			try {
				
				timer = new timer(TC);
				timer.start();
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(timer!=null&&message.equals("!시간확인")&&flag==1) {
			StringBuffer messageContent = new StringBuffer();
			messageContent.append(String.format("%.0f",timer.nextTime-System.currentTimeMillis()));
			TC.sendMessage(messageContent).queue();
			messageContent.delete(0,messageContent.length());																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																								
		}
		else if(message.equals("!마피아게임종료")&&flag==1) {
			stop();
		}
		
		
		
		/*if(peaple.size()<=3&&flag==1) {//승리 조건 실행
			checkWin(event);
		}*/
		if(message.split(" ")[0].equals("!실험")) {
			String tesst = message.split(" ")[1];
			VoiceChannel VC = setGuild.getVoiceChannelsByName("일반",true).get(0);
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
	
	
	public void onPrivateMessageReceived(PrivateMessageReceivedEvent event) {//봇에게 메세지 보낼때의 이벤트
        if (event.getAuthor().isBot()) return;
        String message = event.getMessage().getContentRaw();
        String man=event.getAuthor().getName().toString();
        if(message.split(" ")[0].equals("!help")){//도움말
        	String[] help = new String[3];
        	help[0]="!처형 사람이름 ->해당하는 이름의 시민을 살해합니다. 마피아만 쓸 수 있습니다. ";
        	help[1]="!치료 사람이름 ->해당하는 이름의 시민을 살립니다. 의사만 쓸 수 있습니다. ";
        	help[2]="!수사 사람이름 ->해당하는 이름의 직업을 알 수 있습니다. 경찰만 쓸 수 있습니다. ";
        	for(int i=0;i<help.length;i++) {
        		sendPrivateMessage(event.getAuthor(), help[i]);
        	}
        }
        if(timer.system==2&&flag==1) {
	        if(message.split(" ")[0].equals("!처형")&&executeChance==0){//마피아
	        	String[] mapia=getKey(peaple, 0);
			        for(int i=0;i<mapia.length;i++) {
			        	if(man.equals(mapia[i].toString())) {
			        		executionMan = message.split(" ")[1];
			        		if(peaple.get(executionMan)==null) {
			        			sendPrivateMessage(event.getAuthor(), "그런사람은 없습니다.");
				        		executionMan=null;
			        		}
			        		appointedChance=1;
			            	break;
			            }
			        }
	        	
	        }
	        else if(message.split(" ")[0].equals("!치료")&&cureChance==0){//의사
	        	String[] docter=getKey(peaple, 1);
		        for(int i=0;i<docter.length;i++) {
		        	if(man.equals(docter[i].toString())) {
		            	cureMan = message.split(" ")[1];
		            	if(peaple.get(cureMan)==null) {
		        			sendPrivateMessage(event.getAuthor(), "그런사람은 없습니다.");
		        			cureMan=null;
		        		}
		            	break;
		            }
		        }
	        }
	        else if(message.split(" ")[0].equals("!수사")&&appointedChance==0){//경찰
	        	String[] police=getKey(peaple, 3);
		        for(int i=0;i<police.length;i++) {
		        	if(man.equals(police[i].toString())) {
		        		appointedMan = message.split(" ")[1];
		        		
		        		if(peaple.get(appointedMan)==null) {
		        			sendPrivateMessage(event.getAuthor(), "그런사람은 없습니다.");
		        			appointedMan=null;
		        		}
		        		break;
		            }
		        }
	        }
        }
    }
	public static void sendPrivateMessage(User user, String content) {//개인에게 메세지 보내는 메소드
        // openPrivateChannel provides a RestAction<PrivateChannel>
        // which means it supplies you with the resulting channel
        user.openPrivateChannel().queue((channel) ->
        {
            channel.sendMessage(content).queue();
        });
    }
	public static String[] getKey(HashMap<String, Integer> m, Integer value) { //key로 value 가져옴
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
	public static void checkDay(MessageReceivedEvent ready2){//아침이 되면 밤에 했던 행동들 실행
		TextChannel TC = ready2.getJDA().getTextChannelById(ready2.getTextChannel().getId());//채팅ID
		Guild setGuild=ready2.getGuild();
		GuildController GC = new GuildController(setGuild);
		VoiceChannel VC = setGuild.getVoiceChannelsByName(voiceChat,true).get(0);
		List<Member> member = VC.getMembers();
		if(executionMan!=null||cureMan!=null||appointedMan!=null) {
			if(executionMan!=null&&cureMan!=null&&executionMan.equals(cureMan)) {
				TC.sendMessage(executionMan+"님이 의사에게 살려졌습니다.").queue();
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
					TC.sendMessage(executionMan+"님이 살해당하셨습니다.").queue();
					
				}
				
			}
			
			if(appointedMan!=null) {
				int type=peaple.get(appointedMan);
				if(type==0) {
					sendPrivateMessage(policeUser, appointedMan+"는 마피아입니다.");//그 유저에게 넣음
				}
				else if(type==1) {
					sendPrivateMessage(policeUser, appointedMan+"는 의사입니다.");//그 유저에게 넣음
				}
				else if(type==2) {
					sendPrivateMessage(policeUser, appointedMan+"는 시민입니다.");//그 유저에게 넣음
				}
			}
			executionMan=null;
			cureMan=null;
		}
		return;
	}
	public static void checkWin() {//누가 승리한지 체크
		TextChannel TC = ready.getJDA().getTextChannelById(ready.getTextChannel().getId());//채팅ID
		Guild setGuild=ready.getGuild();
		GuildController GC = new GuildController(setGuild);
		VoiceChannel VC = setGuild.getVoiceChannelsByName(voiceChat,true).get(0);
		List<Member> member = VC.getMembers();
		String[] mapia=getKey(peaple,0);
		String[] docter=getKey(peaple,1);
		String[] citizen=getKey(peaple,2);
		String[] polices=getKey(peaple,3);
		
		if(checkNull(mapia)==0||mapia[0]==null) {
			TC.sendMessage("시민의 승리").queue();
			stop();
		}else if(mapia[0]!=null) {
			if(checkNull(mapia)>0&&checkNull(citizen)+checkNull(docter)+checkNull(polices)<=checkNull(mapia)) {
				
				System.out.println(checkNull(mapia));
				TC.sendMessage("마피아의 승리").queue();
				stop();
			}
		}
		
	}
	public static void check() {
		if(ready!=null) {
			Guild setGuild=ready.getGuild();
			GuildController GC = new GuildController(setGuild);
			if(timer.system==0&&flag==1) {//아침이면 행동 실행
				
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
			if(timer.system==2&&flag==1) {//밤이면 행동 실행
				
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
		TextChannel TC = ready.getJDA().getTextChannelById(ready.getTextChannel().getId());//채팅ID
		if(timer!=null&&timer.system==1&&flag==1) {
			MessageHistory mh = new MessageHistory(TC);
			List<Message> msgs = mh.retrievePast(20).complete();
			
			int i=1;
			String name=null;
			int vote=0;
			
			while(true) {
				String msg = msgs.get(i).getContentRaw();
				if(msg.equals("-----투표시간-----")) {
					break;
				}
				
					int k=0;
					if(msg.split(" ")[0].equals("!투표")) {
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
			
			
			
			
			//가장 투표가 많이 된 사람을 삭제
			Map.Entry<String, Integer> maxEntry = null;
			Map.Entry<String, Integer> nextMaxEntry = null;
			for (Map.Entry<String, Integer> entry : voted.entrySet()) {

			    if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0) {
			    	maxEntry = entry; //compareTo를 이용해 제일 높은 map값이 maxEntry에 저장됨
			    }
			}
			for (Map.Entry<String, Integer> entry : voted.entrySet()) {
			    if (maxEntry == null || (entry.getValue().compareTo(maxEntry.getValue()) > 0 && entry.getKey()!=maxEntry.getKey())) {
			    	
			    	nextMaxEntry = entry; //compareTo를 이용해 제일 높은 map값이 maxEntry에 저장됨

			    }
			}
			
			
			//voted.clear();//순위 초기화
			
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
					TC.sendMessage(maxName+"님이 처형당하셨습니다.").queue();
				}else {
					TC.sendMessage("아무도 처형당하지 않았습니다.").queue();
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
					TC.sendMessage(maxName+"님이 처형당하셨습니다.").queue();
				}else {
					TC.sendMessage("아무도 처형당하지 않았습니다.").queue();
				}
			}
		
		}
	}
	public static void stop() {
		TextChannel TC = ready.getJDA().getTextChannelById(ready.getTextChannel().getId());//채팅ID
		flag=0;
		timer.timerStop();
		peaple=null;
		voted=null;
		ready=null;
		mapiaUser=null;
		TC.sendMessage("종료").queue();
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
    
    if(message.equalsIgnoreCase("!ㅎㅎ")) {
    	System.out.println(event.getAuthor().toString());
        sendPrivateMessage(event.getAuthor(), "Testing");
    }
}*/
