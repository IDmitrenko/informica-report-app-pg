/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.edu.eo.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.edu.eo.*;

import javax.xml.ws.BindingProvider;
import javax.xml.ws.handler.Handler;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.Deflater;

/**
 *
 * @author alexanderm
 */
public class Dispatcher {
    final static private Logger s_logger = LoggerFactory.getLogger(Dispatcher.class);
    private static final String s_jdk_jaxws_http_connect_timeout_param = "com.sun.xml.internal.ws.connect.timeout",
                                s_jdk_jaxws_http_request_timeout_param = "com.sun.xml.internal.ws.request.timeout",
                                s_metro_http_connect_timeout_param = "com.sun.xml.ws.connect.timeout",
                                s_metro_http_request_timeout_param = "com.sun.xml.ws.request.timeout";
    
    static public ru.edu.eo.PushDataServiceSoap getService() { return getService(null, null, null, null); } 
    static public ru.edu.eo.PushDataServiceSoap getService(List<Handler> p_handlers) {
        return getService(null, p_handlers, null, null); 
    }
    static public ru.edu.eo.PushDataServiceSoap getService(String p_ws_endpoint) {
        return getService(p_ws_endpoint, null, null, null); 
    }
    
    static public ru.edu.eo.PushDataServiceSoap getServiceDefaultHandlers() { 
        return getServiceDefaultHandlers(null);
    }
    static public ru.edu.eo.PushDataServiceSoap getServiceDefaultHandlers(String p_ws_endpoint) {
        return getService(p_ws_endpoint, getDefaultHandlers(), null, null); 
    }
    static public ru.edu.eo.PushDataServiceSoap getServiceDefaultHandlers(
            String p_ws_endpoint, Integer p_connect_timeout, Integer p_read_timeout) {
        return getService(p_ws_endpoint, getDefaultHandlers(), p_connect_timeout, p_read_timeout); 
    }    
    static public ru.edu.eo.PushDataServiceSoap getService(String p_ws_endpoint,
                                                           List<Handler> p_handlers,
                                                           Integer p_connect_timeout,
                                                           Integer p_read_timeout) {
        s_logger.debug("getService for endpoint: {}", p_ws_endpoint);
        
        ru.edu.eo.PushDataServiceSoapImplService x_service = new ru.edu.eo.PushDataServiceSoapImplService();
        ru.edu.eo.PushDataServiceSoap x_port = x_service.getPushDataServiceSoapImplPort();

        BindingProvider x_bp = (BindingProvider)x_port;
        if(!ru.avers.informica.utils.CUtil.isStringNullOrEmpty(p_ws_endpoint)) {
            x_bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, p_ws_endpoint);
        }
        if(p_handlers != null && !p_handlers.isEmpty()) x_bp.getBinding().setHandlerChain(p_handlers);        
        if (p_connect_timeout != null) {
            x_bp.getRequestContext().put(s_jdk_jaxws_http_connect_timeout_param, p_connect_timeout);
            x_bp.getRequestContext().put(s_metro_http_connect_timeout_param, p_connect_timeout);            
        }
        if (p_read_timeout != null) {
            x_bp.getRequestContext().put(s_jdk_jaxws_http_request_timeout_param, p_read_timeout);
            x_bp.getRequestContext().put(s_metro_http_request_timeout_param, p_read_timeout);
        }
        return x_port;
    }
    
    static public ru.edu.eo.Authentication createAuth(String p_login, String p_passw) {
        ru.edu.eo.Authentication x_rv = new ru.edu.eo.Authentication();
        x_rv.setLogin(p_login);
        x_rv.setPassword(p_passw);
        return x_rv;
    }
     
    static public byte[] toZipAndBase64(byte[] p_val) throws IOException {
        return ru.avers.informica.utils.CUtil.encodeToBase64(toZip(p_val));
    }   
    static public byte[] toZip(byte[] p_val) throws IOException {
        return ru.avers.informica.utils.CUtil.deflate(p_val, Deflater.DEFAULT_COMPRESSION);
    }    
    
    static public ru.edu.eo.PushDataResponse pushData(
                                                ru.edu.eo.PushDataServiceSoap p_service,
                                                byte[] p_item,
                                                String p_login,
                                                String p_passw,
                                                String p_schema_ver,
                                                PushDataType p_push_data_type) throws IOException {
        byte[] x_dst;
        if (PushDataType.AsIs.equals(p_push_data_type))
            x_dst = p_item;
        else
            x_dst = ru.avers.informica.utils.CUtil.encodeToBase64(p_item);
        String x_data = new String(x_dst);
        PushData x_push_data = new PushData();
        x_push_data.setData(x_data);
        x_push_data.setSchema(p_schema_ver);
        return p_service.pushData(x_push_data, createAuth(p_login, p_passw));
    }

    static public ru.edu.eo.PushZipDataResponse pushZipData(
                                                        ru.edu.eo.PushDataServiceSoap p_service,
                                                        byte[] p_item,
                                                        String p_login,
                                                        String p_passw,
                                                        String p_schema_ver,
                                                        PushDataType p_push_data_type) throws IOException {
        byte[] x_dst;
        if (PushDataType.AsIs.equals(p_push_data_type))
            x_dst = p_item;
        else
            x_dst = toZipAndBase64(p_item);
        String x_data = new String(x_dst);
        PushZipData x_push_zip_data = new PushZipData();
        x_push_zip_data.setData(x_data);
        x_push_zip_data.setSchema(p_schema_ver);
        return p_service.pushZipData(x_push_zip_data, createAuth(p_login, p_passw));
    }

    static public ru.edu.eo.PushZipDataAsyncResponse pushZipDataAsync(
                                                        ru.edu.eo.PushDataServiceSoap p_service, 
                                                        byte[] p_item, 
                                                        String p_login,
                                                        String p_passw,
                                                        String p_schema_ver,
                                                        PushDataType p_push_data_type) throws IOException {
        byte[] x_dst;
        if (PushDataType.AsIs.equals(p_push_data_type))
            x_dst = p_item;
        else        
            x_dst = toZipAndBase64(p_item);
        String x_data = new String(x_dst);
        PushZipDataAsync x_push_zip_data = new PushZipDataAsync();
        x_push_zip_data.setData(x_data);
        x_push_zip_data.setSchema(p_schema_ver);
        return p_service.pushZipDataAsync(x_push_zip_data, createAuth(p_login, p_passw));
    }    
        
    public enum ProcessMode { pushZipData, pushZipDataAsync, pushData }
    public enum PushDataType { AsIs, ToZipAndBase64 }
    public static ResponsePushData pushDataEx(ru.edu.eo.PushDataServiceSoap p_service,
                                              byte[] p_item,
                                              String p_login,
                                              String p_passw,
                                              String p_schema_ver,
                                              ProcessMode p_mode,
                                              PushDataType p_push_data_type) throws IOException {
        switch(p_mode) {
            case pushData:
                PushDataResponse x_response = pushData(p_service, p_item, p_login, p_passw, p_schema_ver, p_push_data_type);
                return x_response == null ? null : x_response.getPushDataResult();
            case pushZipData:
                PushZipDataResponse x_sync_response = pushZipData(p_service, p_item, p_login, p_passw, p_schema_ver, p_push_data_type);
                return x_sync_response == null ? null : x_sync_response.getPushZipDataResult();                
            default:
                PushZipDataAsyncResponse x_async_response = pushZipDataAsync(p_service, p_item, p_login, p_passw, p_schema_ver, p_push_data_type);
                return x_async_response == null ? null : x_async_response.getPushZipDataAsyncResult();
        }
    }

    @Deprecated
    public static ResponsePushData pushZipDataEx(ru.edu.eo.PushDataServiceSoap p_service,
                                                 byte[] p_item,
                                                 String p_login,
                                                 String p_passw,
                                                 String p_schema_ver,
                                                 boolean p_async_upload) throws IOException {
        if (p_async_upload) {
            PushZipDataAsyncResponse x_async_response = pushZipDataAsync(p_service, p_item, p_login, p_passw, p_schema_ver, PushDataType.ToZipAndBase64);
            return x_async_response == null ? null : x_async_response.getPushZipDataAsyncResult();
        }
        else {
            PushZipDataResponse x_sync_response = pushZipData(p_service, p_item, p_login, p_passw, p_schema_ver, PushDataType.ToZipAndBase64);
            return x_sync_response == null ? null : x_sync_response.getPushZipDataResult();
        }
    }
    
    static private List<Handler> getDefaultHandlers() {
        List<Handler> x_handler_chain = new ArrayList<Handler>();

        x_handler_chain.add(new CClientHandler());

        return x_handler_chain;
    }
    
}
