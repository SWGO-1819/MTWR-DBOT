import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

public class index {
	public static void main(String args[]) throws Exception{
    	JDA jda = new JDABuilder("NTczMDIwODEyNDU4ODUyMzYy.XMmDaw.b3znlqO5y302oPOdDaXTw8YHbv8").build();
    	jda.addEventListener(new test());
    }
}