package ru.avers.informica.utils;

//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.ParseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.avers.informica.common.config.CProfile;
import ru.avers.informica.common.config.utils.ConfigLoader;
import ru.avers.informica.dao.filtersort.IFieldFilterParams;
import ru.avers.informica.dto.informica.UchInf;
import ru.avers.informica.filtersinqry.GenericFilter;
import ru.avers.informica.infcfg.Config;
import ru.avers.informica.infcfg.utils.InformicaConfigLoader;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.List;

/**
 *
 * @author Dias
 */

@Slf4j
public class CHelper {
// TODO продолжить ...
/*1
    private static IMapFactory m_default_map_factory;
    
    public static IMapFactory<String, Object> getDefaultMapFactory() {
        if(m_default_map_factory == null)
            m_default_map_factory = new IMapFactory<String, Object>() {
                @Override
                public Map<String, Object> create() { return new HashMap<String, Object>();}
                
                @Override
                public String toStr(Map<String, Object> p_val) {
                    return (p_val == null ? null : JSONObject.toJSONString(p_val));
                }
                @Override
                public Map<String, Object> fromStr(String p_val) {
                    if(CUtil.isStringNullOrEmpty(p_val)) return null;
                    JSONParser x_parser = new JSONParser();
                    try {
                        return (Map)processObject(x_parser.parse(p_val));
                    }
                    catch(ParseException p_pe) {
                        s_logger.error("position: " + p_pe.getPosition(), p_pe);
                        return null;
                    }
                }

                //  перегоним мапы из JSONParser.parse в "наши" мапы -
                //  иначе были проблемы с сериализацией GWT RPC
                private Object processObject(Object p_obj) {
                    Object x_rv = p_obj;
                    if(p_obj instanceof Map) {
                        Map<String, Object> x_map = create();
                        for(Entry x_entry: ((Map<Object, Object>)p_obj).entrySet())
                            x_map.put(x_entry.getKey().toString(), processObject(x_entry.getValue()));
                        x_rv = x_map;
                    }
                    return x_rv;
                }

            };
        return m_default_map_factory;
    }
*/
/*2

    /////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    //  TimeZone
    //
    private static org.avers.gwt.shared.ITransformer<TimeZone, CMapableTimeZone> s_tz2mapable_transformer;
    public static org.avers.gwt.shared.ITransformer<TimeZone, CMapableTimeZone> getTimeZone2MapableTransformer() {
        if(s_tz2mapable_transformer == null) 
           s_tz2mapable_transformer = new org.avers.gwt.shared.ITransformer<TimeZone, CMapableTimeZone>() {
                public CMapableTimeZone to(TimeZone p_src) {
                    return new CMapableTimeZone(getDefaultMapFactory(), p_src.getID(), p_src.getDisplayName());
                }
                public TimeZone from(CMapableTimeZone p_dst) { return TimeZone.getTimeZone(p_dst.getID()); }
           };
        return s_tz2mapable_transformer;
    }
            
    public static CMapableTimeZone getTimeZone() { return getTimeZone(null); }
    
    public static CMapableTimeZone getTimeZone(CMapableUserSettings p_us) {
        CMapableTimeZone x_rv = null;
        if(p_us != null) x_rv = p_us.getTimeZone();
        //В некоторых случаях (i.e. ключ реестра DisableAutoDaylightTimeSet) TimeZone.getTimezone()
        //может возращать Custom timezone которой не будет в списке getAvailableIDs
        //if(x_rv == null) x_rv = getTimeZone2MapableTransformer().to(TimeZone.getTimezone());
        if(x_rv == null) x_rv = getTimeZone2MapableTransformer().to(getDefaultTimeZone());
        return x_rv;
    }

    */
   /**
    * Получить временную зону по умолчанию
    * @return
    * Если временная зона задана в настройках - возвращается она,
    * иначе возвращается временная зона по умолчанию сервера
    */
/*

    public static TimeZone getDefaultTimeZone() {
        // инициализация timezone по умолчанию
        TimeZone x_default_timezone = null;
        s_logger.debug("инициализация временной зоны по умолчанию");
        // получить значение временной зоны из настроек
        String x_config_id_timezone = getConfigProfile().getMisc().getDefaults().getTimezone();
        if (!CUtil.isStringNullOrEmpty(x_config_id_timezone)) {
            s_logger.debug("Временная зона задана в настройках: " + x_config_id_timezone);
            // получить идентификаторы доступных временных зон
            List<String> x_available_ids = CUtil.getListOfTimeZonesIds();
            if (x_available_ids.contains(x_config_id_timezone)) {
                // временная зона задана в настройках и содержится в списке доступных
                x_default_timezone = TimeZone.getTimeZone(x_config_id_timezone);
                s_logger.debug("Временная зона из настроек '" + x_config_id_timezone + "' выбрана в качестве зоны по умолчанию");
            }
            else s_logger.debug("Временная зона из настроек недоступна для выбора");
        }
        if (x_default_timezone == null) {
            x_default_timezone = TimeZone.getDefault();
            s_logger.debug("Временная зона машины '" + x_default_timezone.getID() + "' выбрана в качестве зоны по умолчанию");
            x_default_timezone = TimeZone.getDefault();
        }
        return x_default_timezone;
    }
    
    /////////////////////////////////////////////////////////////////////////////////////////////////////
    
    /////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    //  HttpSession
    //
    public static void saveToSession(HttpSession p_sess, String p_key, Serializable p_data) throws CBaseException {
        Map<String, Serializable> x_map = new HashMap<String, Serializable>();
        x_map.put(p_key, p_data);
        saveToSession(p_sess, x_map);
    }

    public static void saveToSession(HttpSession p_sess, Map<String, Serializable> p_map) throws CBaseException {
        if(p_map == null || p_map.isEmpty()) return;
        try {
            for(Entry<String, Serializable> x_entry: p_map.entrySet()) {
                s_logger.debug("saveToSession: " + String.valueOf(x_entry));
                p_sess.setAttribute(x_entry.getKey(), x_entry.getValue());
            }
        } catch (Exception p_ex) {
            s_logger.error(p_ex.getMessage(), p_ex);
            throw new CBaseException(p_ex);
        }
    }

    public static Serializable getFromSession(HttpSession p_sess, String p_key, boolean p_is_remove) throws CBaseException {
        try {
            Object x_obj = p_sess.getAttribute(p_key);
            
            s_logger.debug("getFromSession: " + p_key + "=" + String.valueOf(x_obj) + "; p_is_remove = " + String.valueOf(p_is_remove));

            if(p_is_remove) p_sess.removeAttribute(p_key);

            return (Serializable)x_obj;
        } catch (Exception p_ex) {
            s_logger.error(p_ex.getMessage(), p_ex);
            throw new CBaseException(p_ex);
        }
    }

    public static void removeFromSession(HttpSession p_sess, String p_key) throws CBaseException {
        try {
            s_logger.debug("removeFromSession: " + p_key );
            p_sess.removeAttribute(p_key);
        } catch (Exception p_ex) {
            s_logger.error(p_ex.getMessage(), p_ex);
            throw new CBaseException(p_ex);
        }
    }
*/
    final private static String S_RELATIVE_SETTINGS_FILENAME = "/settings/config.xml";
    final private static String S_RELATIVE_INFORMICA_CONFIG_FILENAME = "/informica/inf5_yes.xml";

    private static String appHomeFolder;

    public static String getAppHomeFolder() {
        return appHomeFolder;
    }

    public static void setAppHomeFolder(String pVal) {
        appHomeFolder = pVal;
    }

    public static String getSettingsFilename(String sRelativeFilename) {
        return getAppHomeFolder() + sRelativeFilename; }

    public static CProfile getConfigProfile() {
        //  TODO здесь уже должен быть определен id профиля
        return ConfigLoader.loadProfile(getSettingsFilename(S_RELATIVE_SETTINGS_FILENAME),
                "default");
    }

    public static Config getInformicaConfig() {
        return InformicaConfigLoader
                .loadConfigInformica(getSettingsFilename(S_RELATIVE_INFORMICA_CONFIG_FILENAME));
    }

    static public void setFilterFieldType(List<IFieldFilterParams> pFilters, Class<UchInf> pClass) {
        for (IFieldFilterParams filter : pFilters) {
            if (filter instanceof GenericFilter)
                ((GenericFilter)filter).setFieldType(getFieldType(filter.getField(), pClass));
        }
    }

    static public Class<?> getFieldType(String pFieldName, Class<UchInf> pClass) {
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(pClass);
            PropertyDescriptor[] descriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor propDescr : descriptors) {
                if (propDescr.getName().equals(pFieldName)) {
                    return propDescr.getPropertyType();
                }
            }
        } catch (IntrospectionException ex) {
            log.debug("getFieldType", ex);
        }
        return null;
    }

    static public IFieldFilterParams createUchIdFilter(Integer pIdUch) {
        GenericFilter filter = new GenericFilter();
        filter.setField("id");
        filter.setComparison(IFieldFilterParams.ComparisonType.equal);
        filter.setStringValue(String.valueOf(pIdUch));
        filter.setFieldType(Integer.class);
        return filter;
    }

    //===================================================================================================
    
}
