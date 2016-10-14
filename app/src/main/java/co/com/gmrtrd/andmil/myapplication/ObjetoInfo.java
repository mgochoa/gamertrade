package co.com.gmrtrd.andmil.myapplication;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by estudiantelis on 13/10/16.
 */
@IgnoreExtraProperties
public class ObjetoInfo {

    String name;
    String from;
    String image;

    public ObjetoInfo() {
    }

    public ObjetoInfo(String name, String from, String image) {
        this.name = name;
        this.from = from;
        this.image = image;
    }

    public String getname() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }

    public String getfrom() {
        return from;
    }

    public void setfrom(String from) {
        this.from = from;
    }

    public String getimage() {
        return image;
    }

    public void setimage(String image) {
        this.image = image;
    }
}
