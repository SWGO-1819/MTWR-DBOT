
import net.dv8tion.jda.api.events.ReadyEvent;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.managers.GuildController;

import java.text.MessageFormat;
import java.util.Collection;
import java.util.List;

import net.dv8tion.jda.api.entities.*;
public class test extends ListenerAdapter{
	public void onReady(ReadyEvent event) {
		// TODO Auto-generated method stub
	}
	
	
	 
	@Override
	public void onMessageReceived(MessageReceivedEvent event) {
		// TODO Auto-generated method stub
		User user=event.getAuthor();
		if(user.isBot())return;
		TextChannel TC = event.getJDA().getTextChannelById("571683345449353242");//채팅ID
		Guild setGuild=event.getGuild();
		GuildController GC = new GuildController(setGuild);
		Member mm=setGuild.getMember(user);//멤버
		String message = event.getMessage().getContentRaw();//메세지담음
		
		
		String[] command = new String[4];
		command[0]="!예찬";
		command[1]="!흔적지워 채팅ID";
		command[2]="!제비뽑기 사람이름,사람이름 ";
		command[3]="!이름:쓴 사용자의 이름을 보여줍니다";
		if(message.equals("!help")) {
			TC.sendMessage("도움말:\n").queue();
			for(int i=0;i<command.length;i++) {
				TC.sendMessage(command[i]+"\n").queue();
			}
		}
		else if(message.equals("!예찬")) {
			TC.sendMessage("발키리입니다").queue();
		}
		else if(message.split(" ")[0].equals("!흔적지워")) {
			String deleteMS = message.split(" ")[1];
			//MessageHistory mh = new MessageHistory(TC);
			//String test = "gg";
			//Message messageD=event.getMessage();
			//List<Message> msgs = mh.getRetrievedHistory();
			//System.out.println("출력"+messageD.toString());
			//TC.deleteMessages(messageD).complete();
			
			TC.deleteMessageById(deleteMS).complete();
		}
		else if(message.split(" ")[0].equals("!제비뽑기")){
			if(event.getMessage().getMember().getUser().getName().toString().equals("이예찬/19")) {
        		TC.sendMessage("고장났습니다.").queue();
        		event.getJDA().removeEventListener(this);
        	}
			String member = message.split(" ")[1];
			String[] members = member.split(",");
			int randomnum = (int)(Math.random()*members.length-1);
			TC.sendMessage(members[randomnum]).queue();
		}
		else if (message.equals("!stop")) {
            TC.sendMessage("Understood!").queue();
            event.getJDA().removeEventListener(this); // stop listening
        }
		else if (message.equals("!이름")) {
        	if(event.getMessage().getMember().getUser().getName().toString().equals("이예찬/19")) {
        		TC.sendMessage("발키리입니다").queue();
        	}else if(event.getMessage().getMember().getUser().getName().toString().equals("고승원")) {
        		TC.sendMessage("고승원은 탈모입니다").queue();
        	}else {
        		TC.sendMessage(event.getMessage().getMember().getUser().getName().toString()+"입니다").queue();
        	}
        }
		/*else if(message.equals(message)&&event.getMessage().getMember().getUser().getName().toString().equals("안성주")) {
        	TC.sendMessage("사기꾼입니다").queue();
        }*/
        else if(message.split(" ")[0].equals("!음소거")) {
        	String muteUser = message.split(" ")[1];
        	VoiceChannel VC = setGuild.getVoiceChannelsByName("일반",true).get(0);
        	List<Member> member = VC.getMembers();
        	int i=0;
        	while(i<member.size()) {
        		if(member.get(i).getUser().getName().toString().equals(muteUser)) {
        			break;
        		}
        		i++;
        	}
        	GC.setMute(member.get(i), true).queue();
        }else if(message.split(" ")[0].equals("!음소거해제")) {
        	String muteUser = message.split(" ")[1];
        	VoiceChannel VC = setGuild.getVoiceChannelsByName("일반",true).get(0);
        	List<Member> member = VC.getMembers();
        	int i=0;
        	while(i<member.size()) {
        		if(member.get(i).getUser().getName().toString().equals(muteUser)) {
        			break;
        		}
        		
        		i++;
        	}
        	GC.setMute(member.get(i), false).queue();
        }
        
		//채널옮기기
		/*Guild setGuild=event.getGuild();
		GuildController GC = new GuildController(setGuild);
		Member mm=setGuild.getMember(user);
		String message = event.getMessage().getContentRaw();
		VoiceChannel VC = setGuild.getVoiceChannelsByName(message,true).get(0);
		GC.moveVoiceMember(mm, VC).queue();*/
	}
}
