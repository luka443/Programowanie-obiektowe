import java.net.URL;
import java.util.ArrayList;

public abstract class WebPage {

    public String url;
    protected WebPage(String url) {

    }

    public abstract ArrayList<Satellite> getSatellites();
}