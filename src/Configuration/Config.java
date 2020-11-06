package Configuration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Config {

    private static Config config;
    private static int numInvoke;
    private Properties properties;
    private FileInputStream inputStream;
    private String filename;
    private Config(String fileName) throws IOException {
        numInvoke++;
        try {
            this.filename=fileName;
            inputStream = new FileInputStream(fileName);
            properties = new Properties();
            properties.load(inputStream);
        } catch(FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        }catch(IOException ioe) {
            ioe.printStackTrace();
        } finally {
            inputStream.close();
        }

    }
    public static Config getConfig(String fileName){
        if(config==null) {
            try {
                config = new Config(fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
            return config;
        }
        public static Config getConfig() throws Exception {
        if (numInvoke==0)
            throw new Exception("the config has not instalize before");
        if(config.filename!=null)
            return config;
        return null;

        }
        public String getValue(String key){
        return this.properties.getProperty(key);
    }
}
