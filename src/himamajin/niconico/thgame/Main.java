package himamajin.niconico.thgame;

import java.util.ArrayList;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{
	
	@Override
	//�v���O�C�����ǂݍ��܂ꂽ��
	public void onEnable() {
		
		//'toso'�Ƃ����R�}���h���ł��ꂽ��Command���Ăяo��
        getCommand("test").setExecutor(new Command());

		//Listener���������Ă���N���X��o�^����
		getServer().getPluginManager().registerEvents(new EventListener(), this);
		
		//ArrayList���쐬����
		ArrayList <Player> hunter = new ArrayList <Player> ();

	}
	//�v���O�C�����ċN���A�I������Ƃ�
	public void onDisable(){
		
	}
}
