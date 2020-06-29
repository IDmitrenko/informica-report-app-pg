package ru.avers.informica.infcfg.utils;

import lombok.extern.slf4j.Slf4j;
import ru.avers.informica.infcfg.Config;

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
public class InformicaConfigLoader {
    final private static JAXBContext S_JAXB_CTX_INF;
    static {
        JAXBContext jaxbContext = null;
        try {
            jaxbContext = JAXBContext.newInstance(Config.class);
        } catch (Exception ex) {
            log.error("instantiate JAXBContext for " + Config.class.getName(), ex);
        }
        S_JAXB_CTX_INF = jaxbContext;
    }  
    
    public static Config loadInformica(String pFileName) {
        Config configInformica = null;
        try {
            InputStream is = new FileInputStream(pFileName);
            configInformica = ru.avers.informica.utils.xml.CUtil
                    .<Config>reestablish(is, Config.class, S_JAXB_CTX_INF);
            is.close();
        } catch (FileNotFoundException ex) {
            log.error("Informica config file not found", ex);
        } catch (IOException ex) {
            log.error("getInformicaConfig", ex);
        }
        return configInformica;
    }

    public static Config loadConfigInformica(String pFileName) {
        return loadInformica(pFileName);
    }
}
