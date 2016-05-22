package empsoft.ufcg.edu.cordeus.models;


import java.io.Serializable;

public class Cordel implements Serializable  {
    private String title;
    private  String passage;
    private String id;
    private int icon;


    public Cordel(String cordelTitle, String passage, int iconColor) {
        this.title = cordelTitle;
        this.passage = passage;
        this.icon = iconColor;
    }


    public int getIcon() {
        return icon;
    }

    public String getTitle() {
        return title;
    }

    public String getPassage() {
        return passage;
    }
}
