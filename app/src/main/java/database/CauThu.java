package database;

/**
 * Created by DELL-PC on 9/27/2016.
 */
public class CauThu {

    private Integer Id;
    private String Hoten;
    private String Quoctich;

    public CauThu() {
    }

    public String getHoten() {
        return Hoten;
    }

    public void setHoten(String hoten) {
        Hoten = hoten;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getQuoctich() {
        return Quoctich;
    }

    public void setQuoctich(String quoctich) {
        Quoctich = quoctich;
    }
}
