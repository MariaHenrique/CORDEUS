package empsoft.ufcg.edu.cordeus.models;


public class Cordel {
    private String title;
    private int icon;

    public Cordel(String cordelTitle, int iconColor) {
        title = cordelTitle;
        icon = iconColor;
    }

    public int getIcon() {
        return icon;
    }

    public String getTitle() {
        return title;
    }
}
