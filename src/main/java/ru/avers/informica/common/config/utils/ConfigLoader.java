package ru.avers.informica.common.config.utils;

import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class ConfigLoader {
    final private static JAXBContext JAXB_CONTEXT;
    static {
        JAXBContext jaxbCtx = null;
        try {
            jaxbCtx = JAXBContext.newInstance(Configuration.class);
        } catch (Exception ex) {
            log.error("instantiate JAXBContext for " + Configuration.class.getName(), ex);
        }
        JAXB_CONTEXT = jaxbCtx;
    }  
    
    public static Configuration load(String pFileName) {
        Configuration config = null;
        try {
            InputStream is = new FileInputStream(pFileName);
            config = ru.avers.informica.utils.xml.CUtil
                    .<Configuration>reestablish(is, Configuration.class, JAXB_CONTEXT);
            is.close();
        } catch (FileNotFoundException ex) {
            log.error("Config file not found", ex);
        } catch (IOException ex) {
            log.error("getConfigProfile", ex);
        }
        return config;
    }

    public static CProfile loadProfile(String pFileName, String pIdProfile) {
        Configuration x_config = load(pFileName);
        return x_config.getProfile(pIdProfile);
    }
}