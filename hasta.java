
package hastaneyönetimsistemi;

public class hasta {
    private String ad;
    private String soyad;
    private String tc;
    private String hastalık;
    private int derece;

    public hasta(String ad, String soyad, String tc, String hastalık, int derece) {
        this.ad = ad;
        this.soyad = soyad;
        this.tc = tc;
        this.hastalık = hastalık;
        this.derece = derece;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getSoyad() {
        return soyad;
    }

    public void setSoyad(String soyad) {
        this.soyad = soyad;
    }

    public String getTc() {
        return tc;
    }

    public void setTc(String tc) {
        this.tc = tc;
    }

    public String getHastalık() {
        return hastalık;
    }

    public void setHastalık(String hastalık) {
        this.hastalık = hastalık;
    }

    public int getDerece() {
        return derece;
    }

    public void setDerece(int derece) {
        this.derece = derece;
    }
    
    
}
