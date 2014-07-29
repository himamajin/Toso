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

    //ハンターのチーム
	protected static final String TEAM_RED_NAME = "team_red";
    //逃走者のチーム
	protected static final String TEAM_BLUE_NAME = "team_blue";
	//牢獄者のチーム
	protected static final String TEAM_YELLOW_NAME = "team_black";
 
	//ココらへんの処理は適当なんで気にしなくてよし
	/**
	 * staticをつけて他の打クラスでも使用できるようにする
	 */
	static protected Team teamRed;
	static protected Team teamBlue;
    static protected Team teamYellow;



	@Override
	//プラグインが読み込まれた時
	public void onEnable() {

		//'toso'というコマンドが打たれた時Commandを呼び出す
        getCommand("toso").setExecutor(new THCommand());

		//Listenerを実装しているクラスを登録する
		getServer().getPluginManager().registerEvents(new EventListener(), this);




		/*チームの設定等々*/
	       // メインスコアボードを取得します。
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard board = manager.getMainScoreboard();
 
        // チームが既に登録されているかどうか確認し、
        // 登録されていないなら新規作成します。
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
	
	//プラグインが再起動、終了するとき
	public void onDisable(){

	}
	


	//ArrayListを作成する
	static ArrayList <Player> hunter = new ArrayList <Player> ();//ハンターのlist
	static ArrayList <Player> touso = new ArrayList <Player> ();//プレイヤーのlist


    

}
