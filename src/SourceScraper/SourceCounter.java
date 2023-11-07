package SourceScraper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 *
 * @author Lahndrick Hendricks
 */
public class SourceCounter {

    String searchUrl;
    String baseUrl = "https://www.seek.co.nz/jobs-in-information-communication-technology/engineering-software/";
    String casualUrl = baseUrl + "casual-vacation";
    String contractUrl = baseUrl + "contract-temp";
    String partTimeUrl = baseUrl + "part-time";
    String fullTimeUrl = baseUrl + "full-time";
    String searchString = "displayTotalCount\":\"";
    String foundString = null;
    int count = 0;

    //gets the count of each searched url
    public int getCount() {
        try {
            URL webpage = new URL(searchUrl);
            URLConnection connection = webpage.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;

            while ((line = reader.readLine()) != null) {
                if (line.contains(searchString)) {
                    foundString = line;
                }
            }

            reader.close();

            if (foundString != null) {
                String lines[] = foundString.split(searchString);
                lines = lines[1].split("\",\"");
                count = Integer.parseInt(lines[0]);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return count;
    }

    //getters and setters of url that is searched
    public void setUrl(String url) {
        this.searchUrl = url;
    }

    public int getCasual() {
        this.setUrl(casualUrl);
        return getCount();
    }

    public int getContract() {
        this.setUrl(contractUrl);
        return getCount();
    }

    public int getPartTime() {
        this.setUrl(partTimeUrl);
        return getCount();
    }

    public int getFullTime() {
        this.setUrl(fullTimeUrl);
        return getCount();
    }
}
