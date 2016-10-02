package pl.com.bms.picture;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.net.MalformedURLException;
import java.net.URL;

public class Picture {

    private Long id;
    private String title;
    private String imageUrl;

    public Picture(String title, URL imageUrl) {
        this.title = title;
        this.imageUrl = imageUrl.toString();
    }

    @Deprecated
    Picture() {}

    public URL getUrl() {
        try {
            return new URL(this.imageUrl);

        } catch (MalformedURLException e) {
            throw new IllegalStateException("Image url is invalid", e);
        }
    }

    public String getImageUrlAsString() {
        return this.imageUrl;
    }

    public void setId(Long id) {
      this.id = id;
    }

    public Long getId() {
      return this.id;
    }
}
