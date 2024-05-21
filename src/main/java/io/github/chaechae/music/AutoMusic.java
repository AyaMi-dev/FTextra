package org.FT.server.music;

import org.FT.server.Youtube.YouTubeSearch;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;
import java.util.logging.Logger;

public class AutoMusic {

    private final JavaPlugin plugin;
    private final List<String> songList;
    private final Logger logger;

    public AutoMusic(JavaPlugin plugin, List<String> songList) {
        this.plugin = plugin;
        this.songList = songList;
        this.logger = plugin.getLogger();
    }

    public void startPlayingMusic() {
        logger.info("음악 자동 재생을 시작합니다...");

        new BukkitRunnable() {
            @Override
            public void run() {
                for (String song : songList) {
                    if (YouTubeSearch.searchSong(song)) {
                        logger.info("재생 중: " + song);
                        // 여기에 실제 음악 재생 로직을 추가하세요.
                        // 예를 들어, 외부 음악 재생 API를 호출하거나, 서버 내 음악 재생 명령어를 실행합니다.
                    } else {
                        logger.warning("곡을 찾을 수 없음: " + song);
                    }
                }
            }
        }.runTaskTimer(plugin, 0, 20 * 60 * 5); // 5분마다 반복 실행
    }
}