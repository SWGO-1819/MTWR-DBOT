
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
		TextChannel TC = event.getJDA().getTextChannelById("571683345449353242");//ä��ID
		Guild setGuild=event.getGuild();
		GuildController GC = new GuildController(setGuild);
		Member mm=setGuild.getMember(user);//���
		String message = event.getMessage().getContentRaw();//�޼�������
		
		
		String[] command = new String[4];
		command[0]="!����";
		command[1]="!�������� ä��ID";
		command[2]="!����̱� ����̸�,����̸� ";
		command[3]="!�̸�:�� ������� �̸��� �����ݴϴ�";
		if(message.equals("!help")) {
			TC.sendMessage("����:\n").queue();
			for(int i=0;i<command.length;i++) {
				TC.sendMessage(command[i]+"\n").queue();
			}
		}
		else if(message.equals("!����")) {
			TC.sendMessage("��Ű���Դϴ�").queue();
		}
		else if(message.split(" ")[0].equals("!��������")) {
			String deleteMS = message.split(" ")[1];
			//MessageHistory mh = new MessageHistory(TC);
			//String test = "gg";
			//Message messageD=event.getMessage();
			//List<Message> msgs = mh.getRetrievedHistory();
			//System.out.println("���"+messageD.toString());
			//TC.deleteMessages(messageD).complete();
			
			TC.deleteMessageById(deleteMS).complete();
		}
		else if(message.split(" ")[0].equals("!����̱�")){
			if(event.getMessage().getMember().getUser().getName().toString().equals("�̿���/19")) {
        		TC.sendMessage("���峵���ϴ�.").queue();
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
		else if (message.equals("!�̸�")) {
        	if(event.getMessage().getMember().getUser().getName().toString().equals("�̿���/19")) {
        		TC.sendMessage("��Ű���Դϴ�").queue();
        	}else if(event.getMessage().getMember().getUser().getName().toString().equals("��¿�")) {
        		TC.sendMessage("��¿��� Ż���Դϴ�").queue();
        	}else {
        		TC.sendMessage(event.getMessage().getMember().getUser().getName().toString()+"�Դϴ�").queue();
        	}
        }
		/*else if(message.equals(message)&&event.getMessage().getMember().getUser().getName().toString().equals("�ȼ���")) {
        	TC.sendMessage("�����Դϴ�").queue();
        }*/
        else if(message.split(" ")[0].equals("!���Ұ�")) {
        	String muteUser = message.split(" ")[1];
        	VoiceChannel VC = setGuild.getVoiceChannelsByName("�Ϲ�",true).get(0);
        	List<Member> member = VC.getMembers();
        	int i=0;
        	while(i<member.size()) {
        		if(member.get(i).getUser().getName().toString().equals(muteUser)) {
        			break;
        		}
        		i++;
        	}
        	GC.setMute(member.get(i), true).queue();
        }else if(message.split(" ")[0].equals("!���Ұ�����")) {
        	String muteUser = message.split(" ")[1];
        	VoiceChannel VC = setGuild.getVoiceChannelsByName("�Ϲ�",true).get(0);
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
        
		//ä�οű��
		/*Guild setGuild=event.getGuild();
		GuildController GC = new GuildController(setGuild);
		Member mm=setGuild.getMember(user);
		String message = event.getMessage().getContentRaw();
		VoiceChannel VC = setGuild.getVoiceChannelsByName(message,true).get(0);
		GC.moveVoiceMember(mm, VC).queue();*/
	}
}
