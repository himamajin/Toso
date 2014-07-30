package himamajin.niconico.thgame;

import org.bukkit.entity.Entity;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ExplosionPrimeEvent;

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

}
