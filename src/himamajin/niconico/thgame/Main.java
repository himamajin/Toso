package himamajin.niconico.thgame;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

public class Main extends JavaPlugin{
	
	
    //�n���^�[�̃`�[��
	protected static final String TEAM_RED_NAME = "team_red";
    //�����҂̃`�[��
	protected static final String TEAM_BLUE_NAME = "team_blue";
	//�S���҂̃`�[��
	protected static final String TEAM_YELLOW_NAME = "team_black";
 
	//�R�R��ւ�̏����͓K���Ȃ�ŋC�ɂ��Ȃ��Ă悵
	protected Team teamRed;
	protected Team teamBlue;
    protected Team teamYellow;
	
	
	
	@Override
	//�v���O�C�����ǂݍ��܂ꂽ��
	public void onEnable() {
		
		//'toso'�Ƃ����R�}���h���ł��ꂽ��Command���Ăяo��
        getCommand("toso").setExecutor(new Command());

		//Listener���������Ă���N���X��o�^����
		getServer().getPluginManager().registerEvents(new EventListener(), this);
		
		
		
		
		/*�`�[���̐ݒ蓙�X*/
	       // ���C���X�R�A�{�[�h���擾���܂��B
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard board = manager.getMainScoreboard();
 
        // �`�[�������ɓo�^����Ă��邩�ǂ����m�F���A
        // �o�^����Ă��Ȃ��Ȃ�V�K�쐬���܂��B
        teamRed = board.getTeam(TEAM_RED_NAME);
        if ( teamRed == null ) {
            teamRed = board.registerNewTeam(TEAM_RED_NAME);
            teamRed.setPrefix(ChatColor.RED.toString());
            teamRed.setSuffix(ChatColor.RESET.toString());
            teamRed.setAllowFriendlyFire(false);
        }
        teamBlue = board.getTeam(TEAM_BLUE_NAME);
        if ( teamBlue == null ) {
            teamBlue = board.registerNewTeam(TEAM_BLUE_NAME);
            teamBlue.setPrefix(ChatColor.BLUE.toString());
            teamBlue.setSuffix(ChatColor.RESET.toString());
            teamBlue.setAllowFriendlyFire(false);
            
        }
        teamYellow = board.getTeam(TEAM_YELLOW_NAME);
        if ( teamYellow == null ) {
            teamYellow = board.registerNewTeam(TEAM_YELLOW_NAME);
            teamYellow.setPrefix(ChatColor.BLACK.toString());
            teamYellow.setSuffix(ChatColor.RESET.toString());
            teamYellow.setAllowFriendlyFire(false);
        }

	}
	//�v���O�C�����ċN���A�I������Ƃ�
	public void onDisable(){
		
	}
	
	
	
	//ArrayList���쐬����
	ArrayList <Player> hunter = new ArrayList <Player> ();//�n���^�[��list
	ArrayList <Player> touso = new ArrayList <Player> ();//�v���C���[��list
	

    
	
}
