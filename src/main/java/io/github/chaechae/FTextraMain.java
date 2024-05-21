package org.FT.server;

import org.FT.server.Listner.*;
import org.bukkit.plugin.java.JavaPlugin;
import org.FT.server.Youtube.YouTubeSearch;
import org.FT.server.config.MainConfig;
import org.FT.server.music.AutoMusic;
import org.FT.server.music.MusicReload;

import java.util.List;


public class FT extends JavaPlugin {

    private List<String> songList;
    private List<String> 곡리스트;
    private AutoMusic autoMusic;
    private MainConfig mainConfig;


    @Override
    public void onEnable() {
        getLogger().info("플러그인이 활성화 되었습니다");
        mainConfig = new MainConfig(this); // config.yml 파일 생성 및 로드
        getServer().getPluginManager().registerEvents(new BlockInteraction(), this);
        getServer().getPluginManager().registerEvents(new CreeperListner(), this);
        getServer().getPluginManager().registerEvents(new ExplodeListner(), this);
        getServer().getPluginManager().registerEvents(new EnderdragonListner(), this);
        getServer().getPluginManager().registerEvents(new EntityInteraction(), this);
        getServer().getPluginManager().registerEvents(new WitherListner(), this);
        getCommand("am").setExecutor(new MusicReload());
        getCommand("am").setExecutor(new MusicReload());

        saveDefaultConfig();


        loadConfig();


        // Plugin startup logic
    }

    private void loadConfig() {
        reloadConfig();
        getLogger().info("config 파일이 로딩되었습니다");


        getLogger().info("플레이리스트를 로딩 중...");
        for (String key : getConfig().getConfigurationSection("Playlist").getKeys(false)) {
            String song = getConfig().getString("Playlist." + key);
            if (song.matches(".*[ㄱ-ㅎㅏ-ㅣ가-힣]+.*")) { // 한글 포함
                곡리스트.add(song);
                getLogger().info("곡 추가: " + song + " -> 곡 리스트에 추가됨");
            } else { // 영어만 포함
                songList.add(song);
                getLogger().info("곡 추가: " + song + " -> song list에 추가됨");
            }
            if (YouTubeSearch.searchSong(song)) {
                getLogger().info("검색 결과: " + song);
            } else {
                getLogger().warning("곡을 찾을 수 없음: " + song);

            }
        }

        if (곡리스트.size() > 1 || songList.size() > 1) {
            autoMusic = new AutoMusic(this, songList);
            autoMusic.startPlayingMusic();
        } else {
            getLogger().warning("플레이리스트에 최소 2곡 이상 있어야 음악이 자동으로 재생됩니다.");
        }
    }


    @Override
    public void onDisable() {
        getLogger().info("플러그인이 비활성화 되었습니다");
        // Plugin shutdown logic
    }
}

