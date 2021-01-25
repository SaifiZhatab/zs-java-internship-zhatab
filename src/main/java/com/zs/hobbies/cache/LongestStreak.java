package main.java.com.zs.hobbies.cache;

public class LongestStreak {
    private int chess_streak;
    private int badminton_streak;
    private int videoWatching_streak;
    private int travelling_streak;

    public LongestStreak(int chess_streak) {
        this.chess_streak = chess_streak;
    }


    public int getChess_streak() {
        return chess_streak;
    }

    public void setChess_streak(int chess_streak) {
        this.chess_streak = chess_streak;
    }

    public int getBadminton_streak() {
        return badminton_streak;
    }

    public void setBadminton_streak(int badminton_streak) {
        this.badminton_streak = badminton_streak;
    }

    public int getVideoWatching_streak() {
        return videoWatching_streak;
    }

    public void setVideoWatching_streak(int videoWatching_streak) {
        this.videoWatching_streak = videoWatching_streak;
    }

    public int getTravelling_streak() {
        return travelling_streak;
    }

    public void setTravelling_streak(int travelling_streak) {
        this.travelling_streak = travelling_streak;
    }
}
