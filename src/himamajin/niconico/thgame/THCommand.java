package himamajin.niconico.thgame;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;

/**
 *	クラスをextendsするとエラーが起きるようなのでstatic修飾子の変数を使う
 *	CommandだとBukkitのCommandと競合するかもなので変えましたm(__)m
 */
public class THCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player player = sender.getServer().getPlayerExact(sender.getName());//コマンドを実行したプレイヤー
		//引数がないとき
		if(args.length==0){
			//コマンドの一覧を説明を表示（仮）
			player.sendMessage(ChatColor.GREEN+"/lb setlobby"+"\n"+"/lb setarena"+"\n"+"/lb emode on/off"+"\n"+"/lb oni Player"+"\n"+"/lb player Player"+"\n"+"/lb start");
			return true;
		}else{

			//コマンドの引数を調べる【elseを使うとごちゃごちゃするので使用していない】
			//各自の型の照合やargsの数等はチェックしていないので追加予定 2014/07/28

			//hunterのとき
			if(args[0].equals("hunter")){
		        Player target = Bukkit.getServer().getPlayerExact(args[1]);
		        if (target == null) {
		            sender.sendMessage(args[1] + " というプレイヤーは見つかりません。");
		            return true;
		        }

		        //targetがすでに登録されていないか確認
		        /**
		         * static修飾子のついた変数は<クラス名>.<変数名>で使用できる
		         */
		        int check = Main.hunter.indexOf(target);
		        if(check == -1){
		        	Main.hunter.add(target);
					Team team = Main.teamYellow;
					team.addPlayer(target);
					String name = target.getPlayerListName();
					player.sendMessage(ChatColor.GREEN+name+"をハンターに追加しました。");
		        }else{
		        	//すでに登録されている場合
		        	player.sendMessage(ChatColor.RED+"このプレイヤーはすでに登録されています。");
		        }


			}
			//playerの時
			if(args[0].equals("player")){

			}
			//randomの時
			if(args[0].equals("random")){

			}
			//bookの時
			if(args[0].equals("book")){

			}
			//missionの時
			if(args[0].equals("mission")){

			}
			//setjailの時
			if(args[0].equals("setjail")){

			}
			//setrvの時
			if(args[0].equals("setrv")){

			}
			//startの時
			if(args[0].equals("start")){

			}
			//stopの時
			if(args[0].equals("stop")){

			}
			//restartの時
			if(args[0].equals("restart")){

			}
			//setarenaの時
			if(args[0].equals("setarena")){

			}
			//setboxの時
			if(args[0].equals("setbox")){

			}
			//boxの時
			if(args[0].equals("box")){

			}
			//rvの時
			if(args[0].equals("rv")){

			}









			else return false;
		}
		return false;
	}

	}
