package himamajin.niconico.thgame;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ExplosionPrimeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.scoreboard.Team;

public class EventListener implements Listener {
	
	
	
	@EventHandler
	public void onExplosionPrime(ExplosionPrimeEvent event) {	
	    Entity entity = event.getEntity();
	 
	    // このイベントは、点火されたTNTにより発生したのかどうかを確認します。
	    // （つまり、TNTの爆発はこれで無効化されますが、クリーパーの爆発は無効化されません）
	    if (entity instanceof TNTPrimed) {
	        event.setCancelled(true); // イベントをキャンセルして、爆発を無かったことにする
	        entity.getWorld().createExplosion(entity.getLocation(), 0); // 偽物の爆発を発生させる
	    }
	}
	
	
	@EventHandler
	public	void onPunch(EntityDamageByEntityEvent event){
	 	 if(event.getDamager()	instanceof	Player	&&	event.getEntity()	instanceof	Player){
	 	 	 Player	hunter = (Player)	event.getDamager();
	 	 	 Player	toso = (Player)	event.getEntity();
	 	 	 
		     int ht = Main.hunter.indexOf(hunter);
		     if(ht == -1){
		    	 //ハンターじゃなかった場合
		    	 event.setCancelled(true);
		    	 return;
		     }else{
		    	 //ハンターだった場合
		    	 //捕獲処理
	  		    double xbox = (double) Main.plugin.getConfig().get("jail.x");
	  		    double ybox = (double) Main.plugin.getConfig().get("jail.y");
	  		    double zbox = (double) Main.plugin.getConfig().get("jail.z");
				World world = toso.getWorld();
				Location loc = new Location(world, xbox, ybox, zbox);
				toso.teleport(loc);
				toso.sendMessage(ChatColor.BLUE+"[THgame]あなたは確保されました。");
				Team team = Main.teamYellow;
				team.addPlayer(toso);
				Main.roya.add(toso);
				  for(Player p : Bukkit.getServer().getOnlinePlayers()){
		        	   for(ItemStack item : p.getInventory().getContents()){
		        		   if(item != null){
		        			   if(item.getType().equals(Material.WRITTEN_BOOK)){
		        				   BookMeta meta = (BookMeta) item.getItemMeta();
		        				   if(meta.getTitle().equals("携帯電話")){
		        					   String name = toso.getName();
       								String msg = new String("確保メール"+"\n"+name+"が確保された！");
		        					 meta.addPage(msg);
	        					    item.setItemMeta(meta);
	        					    new THse().runTaskTimer(Main.plugin, 1, 0);
	        						p.sendMessage(ChatColor.GOLD+"メールを受信しました…");
		        				   }
		        			   }
		        		   }
		        	   }
				  }
		     }
	 	 }
	}
}
