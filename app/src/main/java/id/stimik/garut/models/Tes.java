package id.stimik.garut.models;

public class Tes {
    String jawaban;
    int gambar;
    boolean betul;

    public boolean isBetul() {
        return betul;
    }

    public void setBetul(boolean betul) {
        this.betul = betul;
    }

    public Tes(String jawaban, int gambar) {
        this.jawaban = jawaban;
        this.gambar = gambar;
    }

    public String getJawaban() {
        return jawaban;
    }

    public void setJawaban(String jawaban) {
        this.jawaban = jawaban;
    }

    public int getGambar() {
        return gambar;
    }

    public void setGambar(int gambar) {
        this.gambar = gambar;
    }
}
