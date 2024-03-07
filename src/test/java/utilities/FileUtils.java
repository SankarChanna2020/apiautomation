package utilities;

import java.io.*;

public class FileUtils {


    public String readFileAsString(String filePath)  {

        String fileName = System.getProperty("user.dir") + filePath;
        String fileAsString = null;

        try {
            fileAsString = readConfigFile(fileName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return fileAsString;
    }


    public String readConfigFile(String fileName) throws IOException{

        InputStream stream = new FileInputStream(fileName);
        BufferedReader bufread = new BufferedReader(new InputStreamReader(stream));

        String line = bufread.readLine();
        StringBuilder sb = new StringBuilder();

        while(line != null){

            sb.append(line).append("\n");
            line = bufread.readLine();
        }

        String fileAsString = sb.toString();


        return fileAsString;
    }


}
