package streak.game;

public class Config {
    private String path;
    private int numButtons;
    private String soundPath;
    private boolean hasSound;

    public Config (String path, String soundPath, int numButtons, boolean hasSound) {
        this.path = path;
        this.numButtons = numButtons;
        this.soundPath = soundPath;
        this.hasSound = hasSound;
    }

    public int getNumButtons() {
        return numButtons;
    }
    public String getPath() {
        return path;
    }
    public String getSoundPath() { return  soundPath; }
    public boolean isHasSound() { return hasSound; }
}
