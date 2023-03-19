package rpcraft.lab.datatypes;

public enum eNUMTabletMode  {
    TELEPORT("teleport"),
    NOTHING("nothing"),
    SLEEP("sleepmode"),
    EAT("eatmode");

    private final String name;
    private eNUMTabletMode(String name) {
        this.name = name;
    }

    public String getMode() {
        return name;
    }

    // Get the next mode in the list, cycling back to the beginning when reaching the end
    public eNUMTabletMode getNextMode() {
        int nextMode = (this.ordinal() + 1) % eNUMTabletMode.values().length;
        return eNUMTabletMode.values()[nextMode];
    }

}
