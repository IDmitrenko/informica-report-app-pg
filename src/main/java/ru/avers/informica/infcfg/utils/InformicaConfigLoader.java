package ru.avers.informica.infcfg.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class InformicaConfigLoader {
    private static final Logger s_logger = LoggerFactory.getLogger(InformicaConfigLoader.class);
    final private static JAXBContext s_jaxb_ctx_inf;
    static {
        JAXBContext x_jaxb_ctx_inf = null;
        try {
            x_jaxb_ctx_inf = JAXBContext.newInstance(Config.class);
        } catch (Exception p_ex) {
            s_logger.error("instantiate JAXBContext for " + Config.class.getName(), p_ex);
        }
        s_jaxb_ctx_inf = x_jaxb_ctx_inf;
    }  
    
    public static Config loadInformica(String p_file_name) {
        Config configInformica = null;
        try {
            InputStream is = new FileInputStream(p_file_name);
            configInformica = ru.avers.informica.utils.xml.CUtil
                    .<Config>reestablish(is, Config.class, s_jaxb_ctx_inf);
            is.close();
        } catch (FileNotFoundException ex) {
            s_logger.error("Informica config file not found", ex);
        } catch (IOException ex) {
            s_logger.error("getInformicaConfig", ex);
        }
        return configInformica;
    }

    public static Config loadConfigInformica(String p_file_name) {
        return loadInformica(p_file_name);
    }
}
