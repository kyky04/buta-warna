package id.stimik.garut.models;

public class ItemLevel {
    long id;
    String level;
    boolean lock;

    public ItemLevel(String level) {
        this.level = level;
    }

    public boolean isLock() {
        return lock;
    }

    public void setLock(boolean lock) {
        this.lock = lock;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
