package ru.avers.informica.common.config.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ru.avers.informica.common.config.CProfile;
import ru.avers.informica.common.config.Configuration;

import javax.xml.bind.JAXBContext;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author Dias
 */
@SuppressWarnings({"BroadCatchBlock", "TooBroadCatch"})
public class ConfigLoader {
    private static final Logger s_logger = LoggerFactory.getLogger(ConfigLoader.class);
    final private static JAXBContext s_jaxb_ctx;
    static {
        JAXBContext x_jaxb_ctx = null;
        try {
            x_jaxb_ctx = JAXBContext.newInstance(Configuration.class);
        } catch (Exception p_ex) {
            s_logger.error("instantiate JAXBContext for " + Configuration.class.getName(), p_ex);
        }
        s_jaxb_ctx = x_jaxb_ctx;
    }  
    
    public static Configuration load(String p_file_name) {
        Configuration x_config = null;
        try {
            InputStream x_is = new FileInputStream(p_file_name);
            x_config = ru.avers.informica.utils.xml.CUtil
                    .<Configuration>reestablish(x_is, Configuration.class, s_jaxb_ctx);
            x_is.close();
        }
        catch (FileNotFoundException ex) {
            s_logger.error("Config file not found", ex);
        } 
        catch (IOException ex) {
            s_logger.error("getConfigProfile", ex);
        }
        return x_config;
    }
    
    public static CProfile loadProfile(String p_file_name, String p_id_profile) {
        Configuration x_config = load(p_file_name);
        return x_config.getProfile(p_id_profile);
    }
    
}
