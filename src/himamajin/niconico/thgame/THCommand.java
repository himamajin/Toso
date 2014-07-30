package himamajin.niconico.thgame;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.scoreboard.Team;
import org.bukkit.util.BlockIterator;

/**
 *	クラスをextendsするとエラーが起きるようなのでstatic修飾子の変数を使う
 *	CommandだとBukkitのCommandと競合するかもなので変えましたm(__)m
 */
public class THCommand implements CommandExecutor {

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player player = sender.getServer().getPlayerExact(sender.getName());//コマンドを実行したプレイヤー
		
		//コンフィグを読み込ませるための定義
		final Main PL = Main.plugin;
		
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
		            sender.sendMessage(ChatColor.RED+"[THgame]"+ args[1] + " というプレイヤーは見つかりません");
		            return true;
		        }

		        //targetがすでに登録されていないか確認
		        /**
		         * static修飾子のついた変数は<クラス名>.<変数名>で使用できる
		         */
		        int check = Main.hunter.indexOf(target);
		        if(check == -1){
		        	Main.hunter.add(target);
					Team team = Main.teamRed;
					team.addPlayer(target);
					String name = target.getPlayerListName();
					player.sendMessage(ChatColor.GREEN+"[THgame]"+name+"をハンターに追加しました");
					return true;
		        }else{
		        	//すでに登録されている場合
		        	player.sendMessage(ChatColor.RED+"[THgame]"+"このプレイヤーはすでに登録されています");
		        	return true;
		        }


			}
			//ハンターから逃走者にするとき
	if(args[0].equals("player")){
		        Player target = Bukkit.getServer().getPlayerExact(args[1]);
		        if (target == null) {
		            sender.sendMessage(args[1] + "[THgame]"+" というプレイヤーは見つかりません");
		            return true;
		        }

		        int check = Main.hunter.indexOf(target);
		        if(check == -1){
					String name = target.getPlayerListName();
					player.sendMessage(ChatColor.RED+"[THgame]"+name+"はすでに逃走者です");
					return true;
		        }else{
		        	//arraylist[hunter]から追放。チームを空にする。
		        	Main.hunter.remove(target);
					Team team = Main.teamRed;
					team.removePlayer(target);
					String name = target.getPlayerListName();
					player.sendMessage(ChatColor.GREEN+"[THgame]"+name+"をハンターから逃走者へと変更しました");
					return true;

		        }


			}
			//randomの時
	if(args[0].equals("random")){

			}
			//bookの時
	if(args[0].equals("book")){
	            	if(args.length==1){
			        //toso book だけなので本を配布する。
	            		//逃走者のみ本を配布
	            		for(Player p : Bukkit.getServer().getOnlinePlayers()){
	            	        int check = Main.hunter.indexOf(p);
	        		        if(check == -1){
	        		        	//プレイヤーがハンターではない場合
	        		        	ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
	        		    		BookMeta meta = (BookMeta) book.getItemMeta();
	        		    		meta.setTitle("携帯電話");
	        		    		book.setItemMeta(meta);
	        		    		p.getInventory().addItem(book);
	        		        	p.updateInventory();
	        		        	p.sendMessage(ChatColor.GOLD+"運営より携帯電話が届きました！");
	        		        }else{
	        		        	//ハンターの場合なにもしない
	        		        }
	            		}
	            		return true;
		        	      
		           }
			}
			//missionの時
	if(args[0].equals("mission")){
    	if(args.length==2){
		           for(Player p : Bukkit.getServer().getOnlinePlayers()){
		        	   for(ItemStack item : p.getInventory().getContents()){
		        		   if(item != null){
		        			   if(item.getType().equals(Material.WRITTEN_BOOK)){
		        				   BookMeta meta = (BookMeta) item.getItemMeta();
		        				   if(meta.getTitle().equals("携帯電話")){
		        					   //mission.txtの内容を取得
		        					   FileSystem fs = FileSystems.getDefault();
		        						Path path = fs.getPath("plugins/THgame/book/"+ args[1] +".txt");
        								if(path == null){
	        								player.sendMessage(ChatColor.RED+"[THgame]"+args[1]+"というミッションは存在しません");
	        								return true;
        								}else{
		        						if(Files.exists(path, LinkOption.NOFOLLOW_LINKS)){
		        							try {
		        								List<String> list = Files.readAllLines(path, Charset.forName(System.getProperty("file.encoding")));
		        								//ファイルが存在する場合
		        								//引数を取得
		        								StringBuilder builder = new StringBuilder();
		        								for(String s : list){
		        									StringBuilder sb = new StringBuilder(s);
		        									replace(sb, "<b>", "" + ChatColor.BOLD + "");
		        									replace(sb, "<i>", "" + ChatColor.ITALIC + "");
		        									replace(sb, "<magic>", "" + ChatColor.MAGIC + "");
		        									replace(sb, "<s>", "" + ChatColor.STRIKETHROUGH + "");
		        									replace(sb, "<u>", "" + ChatColor.UNDERLINE + "");
		        									replace(sb, "<aqua>", "" + ChatColor.RESET + "");
		        									replace(sb, "<black>", "" + ChatColor.BLACK + "");
		        									replace(sb, "<blue>", "" + ChatColor.BLUE + "");
		        									replace(sb, "<dark_aqua>", "" + ChatColor.DARK_AQUA + "");
		        									replace(sb, "<dark_blue>", "" + ChatColor.DARK_BLUE + "");
		        									replace(sb, "<dark_gray>", "" + ChatColor.DARK_GRAY + "");
		        									replace(sb, "<dark_purple>", "" + ChatColor.DARK_PURPLE + "");
		        									replace(sb, "<dark_red>", "" + ChatColor.DARK_RED + "");
		        									replace(sb, "<gold>", "" + ChatColor.GOLD + "");
		        									replace(sb, "<gray>", "" + ChatColor.GRAY + "");
		        									replace(sb, "<green>", "" + ChatColor.GREEN + "");
		        									replace(sb, "<light_purple>", "" + ChatColor.LIGHT_PURPLE + "");
		        									replace(sb, "<red>", "" + ChatColor.RED + "");
		        									replace(sb, "<white>", "" + ChatColor.WHITE + "");
		        									replace(sb, "<yellow>", "" + ChatColor.YELLOW + "");
		        									sb.append("\n");
		        									builder.append(new String(sb));
		        								}
		        								String msg = new String(builder);
		 		        					    meta.addPage(msg);
				        					    item.setItemMeta(meta);
		        								
				        					    
				        					    //ここまでごちゃごちゃしすぎて訳わかめ
				        					    //いじらないように
				        					    
				        					    
				        					    //難しかったので今は経験値音５回
				        					    new THse().runTaskTimer(Main.plugin, 1, 0);

				        						p.sendMessage(ChatColor.GOLD+"メールを受信しました…");
		        							} catch (IOException e) {
		        								//ファイルが存在しない場合
		        								e.printStackTrace();
		        							}
		        						}
		        				   }
		        			   }
		        		   }
		        	   }
		           }
		           }
				   player.sendMessage(ChatColor.GREEN+"[THgame]"+args[1]+"を発令しました！");
	        	   return true;
    	}else{
    		//ファイル名が指定されていない時
    		player.sendMessage(ChatColor.RED+"[THgame]ミッションが指定されていません");
    		return true;
    	}
	}
			//setjailの時
	if(args[0].equals("setjail")){
				//牢屋を追加する
				Location loc = player.getLocation();
				double xjail = loc.getX();
				double yjail = loc.getY();
				double zjail = loc.getZ();
				PL.getConfig().set("jail.x", xjail);
				PL.getConfig().set("jail.y", yjail);
				PL.getConfig().set("jail.z", zjail);
				player.sendMessage(ChatColor.GREEN+"[THgame]"+"牢屋を登録しました");
				PL.saveConfig();
				return true;

			}
			//setrvの時
	if(args[0].equals("setrv")){
				//復活ポイントを追加する
				Location loc = player.getLocation();
				double xrv = loc.getX();
				double yrv = loc.getY();
				double zrv = loc.getZ();
				PL.getConfig().set("rv.x", xrv);
				PL.getConfig().set("rv.y", yrv);
				PL.getConfig().set("rv.z", zrv);
				player.sendMessage(ChatColor.GREEN+"[THgame]"+"復活地点を登録しました");
				PL.saveConfig();
				return true;

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
				//逃走者スタート地点を追加する
				Location loc = player.getLocation();
				double xarena = loc.getX();
				double yarena = loc.getY();
				double zarena = loc.getZ();
				PL.getConfig().set("arena.x", xarena);
				PL.getConfig().set("arena.y", yarena);
				PL.getConfig().set("arena.z", zarena);
				player.sendMessage(ChatColor.GREEN+"[THgame]"+"逃走者スタート地点を登録しました");
				PL.saveConfig();
				return true;

			}
			//setboxの時
	if(args[0].equals("setbox")){
    	if(args.length==2){
				Block target = getTargetBlock(player);
				Location loc = target.getLocation();
				int xblock = (int) loc.getX();
				int yblock = (int) loc.getY();
				int zblock = (int) loc.getZ();
				PL.getConfig().set(args[1]+".x", xblock);
				PL.getConfig().set(args[1]+".y", yblock);
				PL.getConfig().set(args[1]+".z", zblock);
				player.sendMessage(ChatColor.GREEN+"[THgame]"+"ハンターボックス"+args[1]+"を設置しました");
				PL.saveConfig();
				return true;
    	}else{
    		player.sendMessage(ChatColor.RED+"[THgame]"+"ハンターボックスの名前を入れてください！");
    		return true;
    	}
	}
			//boxの時
	if(args[0].equals("box")){
	  	if(args.length==2){
	  			//ハンターボックスを開放する。
	  		    int xbox = -1000;//エラーチェックのため
	  		        xbox = (int) PL.getConfig().get(args[1]+".x");
	  		    int ybox = (int) PL.getConfig().get(args[1]+".y");
	  		    int zbox = (int) PL.getConfig().get(args[1]+".z");
	  		    if(xbox == -1000){
	  		    	player.sendMessage(ChatColor.RED+"[THgame]指定されたハンターボックスは存在しません");
	  		    	return true;
	  		    }
				World world = player.getWorld();
	  		    Block box = world.getBlockAt(xbox, ybox, zbox);
	  		    box.setType(Material.AIR);
				Location loc = new Location(world, xbox, ybox, zbox);
	  		    TNTPrimed tnt = (TNTPrimed) player.getWorld().spawnEntity(loc,EntityType.PRIMED_TNT);
	  		    tnt.setFuseTicks(0);
	  		    Bukkit.broadcastMessage(ChatColor.GREEN+"[THgame]ハンターボックスが開放されました！！");
	  		    player.sendMessage(ChatColor.GREEN+"[THgame]"+args[1]+"が開放されました");
	  		    return true;
	  	}
	}
	  			
	  			
	  			
	  			
			//rvの時
	if(args[0].equals("rv")){

			}
		}
		return false;
	}

	private void replace(StringBuilder sb, String from, String to){
		int index = sb.indexOf(from);
		while(index != -1){
			sb.replace(index, index + from.length(), to);
			index += to.length();
			index = sb.indexOf(from, index);
		}
	}
	
	private Block getTargetBlock(Player player) {
		 
	    // 視線上のブロックを100ブロック先まで取得
	    BlockIterator it = new BlockIterator(player, 100);
	 
	    // 手前側から検証を行う。
	    // Blockが取得できた時点でreturnして終了する。
	    while ( it.hasNext() ) {
	 
	        Block block = it.next();
	 
	        if ( block.getType() != Material.AIR ) {
	            // ブロックが見つかった
	            return block;
	        }
	    }
	 
	    // 最後までブロックがみつからなかった
	    return null;
	}
}
