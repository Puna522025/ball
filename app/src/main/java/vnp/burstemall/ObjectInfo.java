package vnp.burstemall;

/**
 * Created by pkapo8 on 10/6/2016.
 */

public class ObjectInfo {

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(int imageUrl) {
        this.imageUrl = imageUrl;
    }

    public ObjectInfo(String title, int imageUrl) {

        this.title = title;
        this.imageUrl = imageUrl;
    }

    private String title;
    private int imageUrl;

}
