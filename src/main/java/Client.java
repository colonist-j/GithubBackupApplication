import java.io.*;
import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.eclipse.jgit.api.CloneCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;


@SuppressWarnings("deprecation")
    public class Client {

        public static void main(String[] args) {

            String token = "c0923afa5e0170832d6043f993d1bd9b2c690236";

            String url = "github.com/colonist-j/GithubBackupApplication.git";

            // HttpClient Method to get Private Github content with Basic OAuth token
            getGithubContentUsingHttpClient(token, url);

            // URLConnection Method to get Private Github content with Basic OAuth token
           //getGithubContentUsingURLConnection(token, url);
           // cloneRep();
        }

        @SuppressWarnings("resource")
        private static void getGithubContentUsingHttpClient(String token, String url) {
            String newUrl = "https://" + token + ":x-oauth-basic@" + url;
            HttpClient client = new DefaultHttpClient();
            HttpGet request = new HttpGet(newUrl);
            System.out.println(newUrl);
            try {
                HttpResponse response = client.execute(request);
                System.out.println(response);
                String responseString = new BasicResponseHandler().handleResponse(response);
                System.out.println(responseString);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private static void getGithubContentUsingURLConnection(String token, String url) {
            String newUrl = "https://" + url;
            System.out.println(newUrl);
            try {
                URL myURL = new URL(newUrl);
                URLConnection connection = myURL.openConnection();
                token = token + ":x-oauth-basic";
                String authString = "Basic " + Base64.encodeBase64String(token.getBytes());
                connection.setRequestProperty("Authorization", authString);
                InputStream dataInStream = connection.getInputStream();
                System.out.println(dataGetStringFromStream(dataInStream) );
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        // ConvertStreamToString() Utility - we name it as dataGetStringFromStream()
        private static String dataGetStringFromStream(InputStream dataStream) throws IOException {
            if (dataStream != null) {
                Writer stringWriter = new StringWriter();
                char[] dataBuffer = new char[2048];
                try {
                    Reader dataReader = new BufferedReader(new InputStreamReader(dataStream, "UTF-8"));
                    int counter;
                    while ((counter = dataReader.read(dataBuffer)) != -1) {
                        stringWriter.write(dataBuffer, 0, counter);
                    }
                } finally {


                    dataStream.close();
                }
                return stringWriter.toString();
            } else {
                return "No Contents";
            }
        }
//        public static void cloneRep() {
//            String login = "colonist-j";
//            String password = "makeevka24";
//            String rep = "https://github.com/colonist-j/GithubBackupApplication.git";
//            String localDir = "/Users/ilya_zhdanov/Downloads/repository/";
//            try {
//                System.out.println("Connecting...." + login + " : " + password);
//                GitHub gitHub = GitHub.connectUsingPassword(login, password);
//                boolean isValid = gitHub.isCredentialValid();
//                System.out.println("is Valid ? " + isValid);
//                if (isValid) {
//                    GHRepository repository = gitHub.getRepository(rep);
//                    CloneCommand cloneCommand = Git.cloneRepository();
//                    cloneCommand.setDirectory(new File("/Users/ilya_zhdanov/Desktop/sombrero"));
//                    cloneCommand.setNoCheckout(true);
//                    cloneCommand.setRemote( "https://github.com/<username>/<repositoru>.git" );
//                    cloneCommand.setCredentialsProvider( new UsernamePasswordCredentialsProvider( "<username>", "<password>" ) );
//                    cloneCommand.call();
//                }
//            } catch (Exception e) {
//                System.out.println("EXCEPTION....");
//                e.printStackTrace();
//            }
//        }
    }

