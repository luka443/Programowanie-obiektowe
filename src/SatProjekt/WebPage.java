package SatProjekt;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public abstract class WebPage {
	protected WebPage(String url) {
		
	}
	protected abstract ArrayList getSatellites() throws IOException;
}

