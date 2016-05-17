import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import java.io.IOException;

/**
 * Created by ilya_zhdanov on 14/05/2016.
 */

public class Client2 {

    public static void main(String[] args) throws IOException {
        String basicURL = "https://api.github.com/repos";
        String userName = ""; // ex. colonist-j
        String userRepository = ""; // ex. GithubBackupApplication
        String archiveFormat = ""; // zipball or tarball
        String branchName = ""; // ex master

        userName = "forTest2016";
        userRepository = "GithubBackupApplication";
        archiveFormat = "zipball";
        branchName = "master";

        // String url = “https://api.github.com/repos/”+username+”/“+userRepository+”/“+ArchiveFormat+”/master”
        String url = basicURL+"/"+userName+"/"+userRepository+"/"+archiveFormat+"/"+branchName;

        saveUrl("repoBackup.zip", url);
    }

    public static void saveUrl(final String filename, final String urlString)
            throws MalformedURLException, IOException {
        BufferedInputStream in = null;
        FileOutputStream fout = null;
        try {
            in = new BufferedInputStream(new URL(urlString).openStream());
            fout = new FileOutputStream(filename);

            final byte data[] = new byte[1024];
            int count;
            while ((count = in.read(data, 0, 1024)) != -1) {
                fout.write(data, 0, count);
            }
        } finally {
            if (in != null) {
                in.close();
            }
            if (fout != null) {
                fout.close();
            }
        }
    }

}
//    public static void main(String[] args) {
//
//    }
//    private static void getGithubContentUsingHttpClient(String token, String url) {
//        String basicURL= "https://api.github.com/repos/";
//        String username= "colonist-j";
//        String userRepository="GithubBackupApplication";
//        String archiveFormat="zip";
//
//        String newUrl = "https://" + token + ":x-oauth-basic@" + url;
//        HttpClient client = new DefaultHttpClient();
//        HttpGet request = new HttpGet(newUrl);
//        System.out.println(newUrl);
//        try {
//            HttpResponse response = client.execute(request);
//            String responseString = new BasicResponseHandler().handleResponse(response);
//            System.out.println(responseString);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

