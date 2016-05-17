import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
/**
 * Created by ilya_zhdanov on 14/05/2016.
 */

public class Client3 {

    public static final String REPOS = "repos_url";
    public static final String AUTHORIZATION = "authorization";
    public static final String BASIC = "Basic Y29sb25pc3QtajptYWtlZXZrYTI0";
    public static final String ARCHIVE_URL = "archive_url";

    public static void main(String[] args) throws IOException {

        //https://api.github.com/user?colonist-j=c0923afa5e0170832d6043f993d1bd9b2c690236
        String basicURL = "https://api.github.com/user?";

        String userName = "forTest2016";
        String specificUserRepository = "GithubBackupApplication";
        String archiveFormatZip = "zipball"; //zipball or tarball
        String branchName = "/master";
        String token ="a64bf43152ce57ace7282252a49e723b3f9b1c3b"; //"c0923afa5e0170832d6043f993d1bd9b2c690236";
        String fileName = "repoBackup.zip";


        // String url = “https://api.github.com/repos/”+username+”/“+userRepository+”/“+ArchiveFormat+”/master”
        String url = basicURL + userName + "=" + token;
        System.out.println(url);

        String res1 = getGithubAccessUsingHttpClient(token, basicURL, userName);
        String reposURL = jsonToMap(res1);
        String res2 = getGithubContentUsingHttpClient(reposURL);
        String archiveURL = jsonToArray(res2, specificUserRepository);
        backup(fileName, archiveURL, archiveFormatZip, branchName);
    }

    private static String getGithubAccessUsingHttpClient(String token, String basicURL, String userName) throws IOException {
        String newUrl = basicURL + userName + "=" + token;
        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet(newUrl);
        request.addHeader(AUTHORIZATION, BASIC);
        System.out.println("request:-------------------");
        System.out.println(request.getRequestLine());

        HttpResponse response = client.execute(request);
        System.out.println(response);
        String responseString = new BasicResponseHandler().handleResponse(response);
        System.out.println(responseString);
        return responseString;
    }

    private static String getGithubContentUsingHttpClient(String reposUrl) throws IOException {
        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet(reposUrl);
        System.out.println("request:-------------------");
        System.out.println(request.getRequestLine());
        HttpResponse response = client.execute(request);
        System.out.println(response);
        String responseString = new BasicResponseHandler().handleResponse(response);
        System.out.println("responseString--------" + responseString);
        return responseString;
    }

    private static String jsonToMap(String parametr) throws IOException {
        Map<String, Object> map = new HashMap<String, Object>();
        ObjectMapper mapper = new ObjectMapper();
        // convert JSON string to Map
        map = mapper.readValue(parametr, new TypeReference<Map<String, Object>>() {
        });
        System.out.println(map);
        System.out.println("++++++++++++MAP DONE++++++++++");
        String url = map.get(REPOS).toString();
        System.out.println("----------" + url);
        return url;
    }

    private static String jsonToArray(String parametr, String specificUserRepository) throws IOException {
        String arURL = null;
        JsonFactory f = new JsonFactory();
        ObjectMapper mapper = new ObjectMapper();
        JsonParser jp = f.createJsonParser(parametr);
        // advance stream to START_ARRAY first:
        jp.nextToken();
        // and then each time, advance to opening START_OBJECT
        while (jp.nextToken() == JsonToken.START_OBJECT) {
            Map<String, Object> userData = mapper.readValue(jp, Map.class);
            arURL = (String) userData.get(ARCHIVE_URL);
            System.out.println(arURL);
            System.out.println(arURL.contains(specificUserRepository));
        }
        return arURL;
    }


     private static void backup(String filename, String arURL, String archiveFormatZip, String branchName) throws  IOException {
        String  urlString=arURL+archiveFormatZip+"/"+branchName;
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

        /*JsonFactory jsonFactory = new JsonFactory();
        JsonParser jp = jsonFactory.createJsonParser(responseString);
        jp.setCodec(new ObjectMapper());
        JsonNode jsonNode = jp.readValueAsTree();
        readJsonData(jsonNode);*/
        // getRepository(responseString);
    /*}

    private static void getRepository(String responseString) {

    }*/

       /* private static void jsonToMap(String t) throws IOException {

            HashMap<String, String> map = new HashMap<String, String>();
            JSONObject jObject = new JSONObject(t);
            Iterator<?> keys = jObject.keys();

            while( keys.hasNext() ){
                String key = (String)keys.next();
                String value = jObject.getString(key);
                map.put(key, value);

            }

            System.out.println("json : "+jObject);
            System.out.println("map : "+map);
        }*/

    /*static void readJsonData(JsonNode jsonNode) {
        Iterator<Map.Entry<String, JsonNode>> ite = jsonNode.getFields();
        while(ite.hasNext()){
            Map.Entry<String, JsonNode> entry = ite.next();
            if(entry.getValue().isObject()) {
                readJsonData(entry.getValue());
            } else {
                System.out.println("key:"+entry.getKey()+", value:"+entry.getValue());
            }
        }*/

