package himamajin.niconico.thgame;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;

public class Command extends Main implements CommandExecutor {
	
	//
	//toso�R�}���h�����s���ꂽ��
	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, org.bukkit.command.Command cmd, String label, String[] args) {
		Player player = sender.getServer().getPlayerExact(sender.getName());//�R�}���h�����s�����v���C���[
		//�������Ȃ��Ƃ�
		if(args.length==0){
			//�R�}���h�̈ꗗ�������\���i���j
			player.sendMessage(ChatColor.GREEN+"/lb setlobby"+"\n"+"/lb setarena"+"\n"+"/lb emode on/off"+"\n"+"/lb oni Player"+"\n"+"/lb player Player"+"\n"+"/lb start");
			return true;
		}else{
			
			//�R�}���h�̈����𒲂ׂ�yelse���g���Ƃ����Ⴒ���Ⴗ��̂Ŏg�p���Ă��Ȃ��z
			//�e���̌^�̏ƍ���args�̐����̓`�F�b�N���Ă��Ȃ��̂Œǉ��\�� 2014/07/28
			
			//hunter�̂Ƃ�
			if(args[0].equals("hunter")){
		        Player target = getPlayer(args[1]);
		        if (target == null) {
		            sender.sendMessage(args[0] + " �Ƃ����v���C���[�͌�����܂���B");
		            return true;
		        }
		        
		        //target�����łɓo�^����Ă��Ȃ����m�F
		        int check = hunter.indexOf(target);
		        if(check == -1){
		        	hunter.add(target);
					Team team = teamYellow;
					team.addPlayer(target);
					String name = target.getPlayerListName();
					player.sendMessage(ChatColor.GREEN+name+"���n���^�[�ɒǉ����܂����B");
		        }else{
		        	//���łɓo�^����Ă���ꍇ
		        	player.sendMessage(ChatColor.RED+"���̃v���C���[�͂��łɓo�^����Ă��܂��B");
		        }
		        
		        
			}
			//player�̎�
			if(args[0].equals("player")){
				
			}
			//random�̎�
			if(args[0].equals("random")){
				
			}
			//book�̎�
			if(args[0].equals("book")){
				
			}
			//mission�̎�
			if(args[0].equals("mission")){
				
			}
			//setjail�̎�
			if(args[0].equals("setjail")){
				
			}
			//setrv�̎�
			if(args[0].equals("setrv")){
				
			}
			//start�̎�
			if(args[0].equals("start")){
				
			}
			//stop�̎�
			if(args[0].equals("stop")){
				
			}
			//restart�̎�
			if(args[0].equals("restart")){
				
			}
			//setarena�̎�
			if(args[0].equals("setarena")){
				
			}
			//setbox�̎�
			if(args[0].equals("setbox")){
				
			}
			//box�̎�
			if(args[0].equals("box")){
				
			}
			//rv�̎�
			if(args[0].equals("rv")){
				
			}
			
			
			
			
			
			
			
			
			
			
		}
		return false;
	}

	
	    //�I�����C���̃v���C���[���擾���郁�\�b�h
		private Player getPlayer(String name) {
		    for ( Player player : Bukkit.getOnlinePlayers() ) {
		        if ( player.getName().equals(name) ) {
		            return player;
		        }
		    }
		    return null;
		}
}