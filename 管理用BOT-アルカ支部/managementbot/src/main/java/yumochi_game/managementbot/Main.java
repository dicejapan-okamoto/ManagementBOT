package yumochi_game.managementbot;

import java.util.ArrayList;
import java.util.List;

import javax.security.auth.login.LoginException;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.requests.GatewayIntent;


public class Main extends ListenerAdapter{
	
	private static JDA jda = null;
	private static final String BOT_TOKEN = "MTAyNDE3NDQ0MTI3NTU5Mjc3NA.GWloLb.It3_0cNoh7jt4rB8YrG15dilKQVeEOPBdgmJDM";
	
	public static void main(String[] arg){
		
		try {
			jda = JDABuilder.createDefault(BOT_TOKEN, GatewayIntent.GUILD_MESSAGES)
					.setRawEventsEnabled(true)
					.addEventListeners(new Main())
					.setActivity(Activity.playing("管理係ちゃん起動プログラム"))
					.build();
		} catch(LoginException e) {
			e.printStackTrace();
		}
		
		//登録コマンドを最新のものに更新
		jda.updateCommands().queue();
		
		//呼び出しコマンドの実装
		jda.upsertCommand("/call","BOTを呼ぶ").queue();
		
		//サブコマンドの実装
		jda.upsertCommand("/c","BOTを呼ぶ")
				.addOptions(new OptionData(OptionType.STRING,"subCommand1","/callの簡易版コマンド").setRequired(true))
				.queue();
		
		//戦績記録コマンドの実装
		jda.upsertCommand("/record","戦績を記録").queue();
		
		//サブコマンドの実装
		jda.upsertCommand("/r","戦績を記録")
				.addOptions(new OptionData(OptionType.STRING,"subCommand2","recordの簡易版コマンド").setRequired(true))
				.queue();
		
		//マニュアルコマンドの実装
		jda.upsertCommand("/manual","マニュアルの表示").queue();
		
		//サブコマンドの実装
		jda.upsertCommand("/m","マニュアルの表示")
				.addOptions(new OptionData(OptionType.STRING,"subCommand3","manualの簡易版コマンド").setRequired(true))
				.queue();
	}

@Override
public void onMessageReceived(MessageReceivedEvent e) {
	
	//list宣言
	List<String> boss_List = new ArrayList<String>();
			
	//listにデータ追加
	boss_List.add("ミシャグジ様");
	boss_List.add("機械チルノ");
	boss_List.add("毛玉キング");
	boss_List.add("雲山");
			
	String boss1 = boss_List.get(0);
	String boss2 = boss_List.get(1);
	String boss3 = boss_List.get(2);
	String boss4 = boss_List.get(3);
	
	String msg = e.getMessage().getContentRaw();
	if(msg.equals("TA")) {
		if(!e.getAuthor().equals(jda.getSelfUser())) {
			
			e.getTextChannel().sendMessage("サークルボスのタイムだね。「!record サークルボス名 タイム」と記入してくれたら、記録するよ～").queue();
		}
	} else if(msg.equals("サークルボス")) {
		if(!e.getAuthor().equals(jda.getSelfUser())) {
			
			e.getTextChannel().sendMessage("サークルボスのタイムだね。「!record サークルボス名 タイム」と記入してくれたら、記録するよ～").queue();
		}
	}

	if(msg.equals("!record" + boss1 + "*秒")) {
		if(!e.getAuthor().equals(jda.getSelfUser())) {
			
			e.getTextChannel().sendMessage(boss1 + "のタイムを更新したよ～").queue();
		}
	} else if(msg.equals("!record" + boss2 + "*秒")) {
		if(!e.getAuthor().equals(jda.getSelfUser())) {
			
			e.getTextChannel().sendMessage(boss2 + "のタイムを更新したよ～").queue();
		}
	} else if(msg.equals("!record" + boss3 + "*秒")) {
		if(!e.getAuthor().equals(jda.getSelfUser())) {
			
			e.getTextChannel().sendMessage(boss3 + "のタイムを更新したよ～").queue();
		}
	} else if(msg.equals("!record" + boss4 + "*秒")) {
		if(!e.getAuthor().equals(jda.getSelfUser())) {
			
			e.getTextChannel().sendMessage(boss4 + "のタイムを更新したよ～").queue();
		}
		
	}
	
	if(msg.equals("サークル戦")) {
		if(!e.getAuthor().equals(jda.getSelfUser())) {
			
			e.getTextChannel().sendMessage("サークル戦の記録だね。 本番やる前に、「!start ボス名」と記入してくれたら、管理等するから言ってね！").queue();
		}
	}
	
	if(msg.equals("!start ボス名")) {
		if(!e.getAuthor().equals(jda.getSelfUser())) {
			
			e.getTextChannel().sendMessage("〇〇がボス名の戦闘開始したよ～！！ 〇〇、頑張ってね！！ 終わったら、「!end ボス名 *%（ボスの残り体力）」って記入してね～").queue();
		}
	}
	
	if(msg.equals("!end ボス名 *%")) {
		if(!e.getAuthor().equals(jda.getSelfUser())) {
			
			e.getTextChannel().sendMessage("〇〇のボス名の戦闘終了したよ～。 お疲れ様～！ ちゃんと記録したからね").queue();
		}
	}
	
	if(msg.equals("!cancel ボス名")) {
		if(!e.getAuthor().equals(jda.getSelfUser())) {
			
			e.getTextChannel().sendMessage("〇〇がボス名の戦闘をキャンセルしたよ。").queue();
		}
	}
}

@Override
public void onSlashCommand(SlashCommandEvent e) {
	if(e.getName().equalsIgnoreCase("/record")) {
		e.reply("記録だね？　了解！　記録したいものは何かな？　教えてくれる？（TA（サークルボスでも可） or サークル戦）←こんな感じで答えてね").setEphemeral(false).queue();
	} else if(e.getName().equalsIgnoreCase("/r") && e.getOptions().get(0).getAsString().equalsIgnoreCase("subCommand")) {
		e.reply("記録だね？　了解！　記録したいものは何かな？　教えてくれる？（TA（サークルボスでも可） or サークル戦）←こんな感じで答えてね").setEphemeral(false).queue();
	} else if(e.getName().equalsIgnoreCase("/call")) {
		e.reply("はいは～い、管理係ちゃんだよ。何か用かな？").setEphemeral(false).queue();
	} else if(e.getName().equalsIgnoreCase("/c") && e.getOptions().get(0).getAsString().equalsIgnoreCase("subCommand")) {
		e.reply("はいは～い、管理係ちゃんだよ。何か用かな？").setEphemeral(false).queue();
	}
	
	else {
		e.reply("コマンドが違うみたい。マニュアルを確認してみてね").setEphemeral(false).queue();
	}

}

}