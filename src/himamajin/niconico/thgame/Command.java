package himamajin.niconico.thgame;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Command implements CommandExecutor {
	
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
}