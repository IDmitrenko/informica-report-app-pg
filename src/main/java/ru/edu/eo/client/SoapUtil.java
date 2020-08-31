/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.edu.eo.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.*;
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.handler.Handler;
import java.io.*;
import java.net.MalformedURLException;
import java.util.*;
//import com.sun.xml.internal.ws.api.message.HeaderList;
//import com.sun.xml.internal.ws.api.message.Header;

/**
 *
 * @author alexanderm
 */
public class SoapUtil {
    final static private Logger s_logger = LoggerFactory.getLogger(SoapUtil.class);

    final static private String s_jdk_jaxws_http_connect_timeout_param = "com.sun.xml.internal.ws.connect.timeout",
                                s_jdk_jaxws_http_request_timeout_param = "com.sun.xml.internal.ws.request.timeout",
                                s_metro_http_connect_timeout_param = "com.sun.xml.ws.connect.timeout",
                                s_metro_http_request_timeout_param = "com.sun.xml.ws.request.timeout";
    
    
    static public void setServiceEndpoint(BindingProvider p_bp, String p_ws_endpoint) {
        setServiceEndpointAndHandlers(p_bp, p_ws_endpoint, null);
    }
    static public void setServiceEndpointAndHandlers(
                                    BindingProvider p_bp,
                                    String p_ws_endpoint,
                                    List<Handler> p_handlers) {
        setServiceParam(p_bp, p_ws_endpoint, p_handlers, null, null);
    } 
    static public void setServiceParam(
                                    BindingProvider p_bp,
                                    String p_ws_endpoint,
                                    List<Handler> p_handlers,
                                    Integer p_connect_timeout,
                                    Integer p_read_timeout) {
        Map<String, Object> x_rc = p_bp.getRequestContext();
        
        if(!ru.avers.informica.utils.CUtil.isStringNullOrEmpty(p_ws_endpoint)) {
            x_rc.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, p_ws_endpoint);
        }
        if(p_handlers != null && !p_handlers.isEmpty()) p_bp.getBinding().setHandlerChain(p_handlers);        
        if (p_connect_timeout != null) {
            x_rc.put(s_jdk_jaxws_http_connect_timeout_param, p_connect_timeout);
            x_rc.put(s_metro_http_connect_timeout_param, p_connect_timeout);            
        }
        if (p_read_timeout != null) {
            x_rc.put(s_jdk_jaxws_http_request_timeout_param, p_read_timeout);
            x_rc.put(s_metro_http_request_timeout_param, p_read_timeout);
        }
    }
    
    //  =============================================================================
    //
    //  JAX-WS: SOAPMessage proccessing
    //
    //  =============================================================================

    static public SOAPMessage call(SOAPMessage p_req, String p_endpoint)
                                            throws UnsupportedOperationException, SOAPException, MalformedURLException {
        return call(p_req, p_endpoint, null);
    }
    static public SOAPMessage call(
            SOAPMessage p_req, String p_endpoint, final Integer p_cnn_timeout, final Integer p_read_timeout)
                                            throws UnsupportedOperationException, SOAPException, MalformedURLException {
        java.net.URLStreamHandler x_handler = null;
        if(p_cnn_timeout != null || p_read_timeout != null)
            x_handler = new java.net.URLStreamHandler() {
                protected java.net.URLConnection openConnection(java.net.URL p_url) throws IOException {
                    // The url is the parent of this stream handler, so must create clone
                    java.net.URL x_clone = new java.net.URL(p_url.toString());
                    java.net.URLConnection x_connection = x_clone.openConnection();
                    // If you cast to HttpURLConnection, you can set redirects
                    // connection.setInstanceFollowRedirects (false); // no redirs
                    x_connection.setConnectTimeout(p_cnn_timeout);
                    x_connection.setReadTimeout(p_read_timeout);

                    // Custom header
//                    connection.addRequestProperty("Developer-Mood", "Happy");
                    return x_connection;
                }
            };
        return call(p_req, p_endpoint, x_handler);

    }
    static public SOAPMessage call(SOAPMessage p_req, String p_endpoint, java.net.URLStreamHandler p_handler)
                                            throws UnsupportedOperationException, SOAPException, MalformedURLException {
        return call(p_req, new java.net.URL(null, p_endpoint, p_handler));
    }
    
    static public SOAPMessage call(SOAPMessage p_req, java.net.URL p_endpoint)
                                                                throws UnsupportedOperationException, SOAPException {
        SOAPConnection x_cnn = SOAPConnectionFactory.newInstance().createConnection();
        SOAPMessage x_rv = x_cnn.call(p_req, p_endpoint);
        x_cnn.close();  
        return x_rv;
    }

    static public Map.Entry<SOAPMessage, String> createSOAPMessageEnc(InputStream p_is, boolean p_is_write_xml_decl)
                                        throws ParserConfigurationException, SAXException, IOException, SOAPException {
        Map.Entry<SOAPMessage, String> x_rv = null;
        if(p_is != null) 
            x_rv = createSOAPMessageEnc(
                    ru.avers.informica.utils.xml.CUtil.loadDocument(new org.xml.sax.InputSource(p_is)), p_is_write_xml_decl);
        return x_rv;
    }    
    static public Map.Entry<SOAPMessage, String> createSOAPMessageEnc(String p_xml_str, boolean p_is_write_xml_decl)
                                        throws SOAPException, ParserConfigurationException, SAXException, IOException {
        Map.Entry<SOAPMessage, String> x_rv = null;
        if(p_xml_str != null) 
            x_rv = createSOAPMessageEnc(ru.avers.informica.utils.xml.CUtil.loadDocument(p_xml_str), p_is_write_xml_decl);
        return x_rv;
    }    
    static public Map.Entry<SOAPMessage, String> createSOAPMessageEnc(
                                    org.w3c.dom.Document p_doc, boolean p_is_write_xml_decl) throws SOAPException {
        Map.Entry<SOAPMessage, String> x_rv = null;
        if(p_doc != null) {
            String x_enc = p_doc.getXmlEncoding();
            SOAPMessage x_soap_msg = createSOAPMessage(new DOMSource(p_doc), x_enc, p_is_write_xml_decl);
            x_rv = new AbstractMap.SimpleEntry<SOAPMessage, String>(x_soap_msg, x_enc);
        }
        return x_rv;
    }    
    
    static public SOAPMessage createSOAPMessage(String p_str) throws SOAPException, UnsupportedEncodingException {
        return createSOAPMessage(ru.avers.informica.utils.xml.CUtil.createSourceFromStr(p_str, null));
    }
    static public SOAPMessage createSOAPMessage(String p_str, String p_charset)
                                                                throws SOAPException, UnsupportedEncodingException {
        return createSOAPMessage(ru.avers.informica.utils.xml.CUtil.createSourceFromStr(p_str, p_charset), p_charset);
    }
    static public SOAPMessage createSOAPMessage(Source p_src) throws SOAPException {
        return createSOAPMessage(p_src, null);
    }    
    static public SOAPMessage createSOAPMessage(Source p_src, String p_enc) throws SOAPException {
        return createSOAPMessage(p_src, p_enc, false);
    }
    static public SOAPMessage createSOAPMessage(Source p_src, String p_enc, boolean p_is_write_xml_decl)
                                                                                                throws SOAPException {
        SOAPMessage x_rv = null;
        if(p_src != null) {
            x_rv = MessageFactory.newInstance().createMessage();
            x_rv.getSOAPPart().setContent(p_src);
            if(p_enc != null) x_rv.setProperty(SOAPMessage.CHARACTER_SET_ENCODING, p_enc);
            if(p_is_write_xml_decl) x_rv.setProperty(SOAPMessage.WRITE_XML_DECLARATION, "true");
            x_rv.saveChanges();
        }
        return x_rv;
    }    

    private static Map<String, Object> getProperties(SOAPMessage p_soap_msg) {
        String[] x_props = new String[2];
        x_props[0] = SOAPMessage.CHARACTER_SET_ENCODING;
        x_props[1] = SOAPMessage.WRITE_XML_DECLARATION;
        return getProperties(p_soap_msg, x_props);
    }
    
    private static Map<String, Object> getProperties(SOAPMessage p_soap_msg, String[] p_props) {
        Map<String, Object> x_rv = new HashMap<String, Object>();
        for(String x_prop: p_props) {
            try {
                Object x_val = p_soap_msg.getProperty(x_prop);
                if(x_val != null) x_rv.put(x_prop, x_val);
            } catch(Exception p_ex) {
                s_logger.error("getProperties of SOAPMessage for property = " + x_prop, p_ex);
            }
        }
        return x_rv.isEmpty() ? null : x_rv;
    }

    public static String resolveEncoding(SOAPMessage p_soap_msg) {
        String x_rv = getEncodingFromMsg(p_soap_msg);
        return x_rv == null ? ru.avers.informica.utils.Const.S_ENCODING_UTF8 : x_rv;
    }
    
    private static String getEncodingFromMsg(SOAPMessage p_soap_msg) {
        String x_rv = getEncodingFromMsgProperty(p_soap_msg);
        if(x_rv == null) x_rv = getEncodingFromValueOfContent(p_soap_msg);
        return x_rv;
    }
    private static String getEncodingFromMsgProperty(SOAPMessage p_soap_msg) {
        String x_rv = null;
        try {
            x_rv = (String)p_soap_msg.getProperty(SOAPMessage.CHARACTER_SET_ENCODING);
        } catch(SOAPException p_ex) {
            s_logger.error("getProperty(SOAPMessage.CHARACTER_SET_ENCODING)", p_ex);
        }
        return x_rv;
    }
    
    private static String getEncodingFromValueOfContent(SOAPMessage p_soap_msg) {
        return getEncodingFromValueOfContent(getValueOfContentTypeMimeHeader(p_soap_msg));
    }
    
    private static final String S_CHARSET = "charset";
    //  see source code of org.apache.axis2.saaj.SOAPMessageImpl
    private static String getEncodingFromValueOfContent(String p_val) {
        String x_charset = null;
        if(p_val != null) {
            int x_ind = p_val.lastIndexOf(S_CHARSET);
            if (x_ind > 0) {
                String x_charset_part = p_val.substring(x_ind);
                int x_charset_ind = x_charset_part.indexOf('=');
                x_charset = x_charset_part.substring(x_charset_ind + 1).trim();
                if((x_charset.startsWith("\"") || x_charset.startsWith("\'"))) {
                    x_charset = x_charset.substring(1, x_charset.length());
                }
                if((x_charset.endsWith("\"") || x_charset.endsWith("\'"))) {
                    x_charset = x_charset.substring(0, x_charset.length() - 1);
                }
                int x_index = x_charset.indexOf(';');
                if(x_index != -1) {
                    x_charset = x_charset.substring(0, x_index);
                }
            }
        }
        return x_charset;
    }
    private static final String S_CONTENT_TYPE = "Content-Type";
    private static String getValueOfContentTypeMimeHeader(SOAPMessage p_soap_msg) {
        String x_vals[] = p_soap_msg.getMimeHeaders().getHeader(S_CONTENT_TYPE);
        return  (x_vals != null) ? x_vals[0] : null;        
    }
    
    
    public static String toInfoString(SOAPMessage p_soap_msg) {
        String x_rv =
            "Implementation: " + p_soap_msg.getClass().getName() + "\r\n" +
            "Content Description: " + String.valueOf(p_soap_msg.getContentDescription()) + "\r\n" +
            "XML Encoding of SOAPPart: " + String.valueOf(ru.avers.informica.utils.xml.CUtil.getXmlEncoding(
                                                                                p_soap_msg.getSOAPPart())) + "\r\n" +
            "Encoding from MimeHeaders: " + String.valueOf(getEncodingFromValueOfContent(p_soap_msg)) + "\r\n" +
            "Properties:";
        Map<String, Object> x_props = getProperties(p_soap_msg);
        if(x_props != null)
            for(Map.Entry<String, Object> x_e: x_props.entrySet())
                x_rv += "\r\n\t" + x_e.getKey() + " = " + String.valueOf(x_e.getValue());

        x_rv += "\r\nMime Headers:";
        Iterator<MimeHeader> x_it = p_soap_msg.getMimeHeaders().getAllHeaders();
        while(x_it.hasNext()) {
            MimeHeader x_mh = x_it.next();
            x_rv += "\r\n\t" + x_mh.getName() + " = " + String.valueOf(x_mh.getValue());
        }
        x_rv += "\r\nSoap Message:\r\n\tSystem encoding:\r\n" + toString(p_soap_msg) + 
                "\r\n\tResolved Encoding:\r\n" + toStringInEncoding(p_soap_msg);
        
        return x_rv;            
    }
    
    //  TODO experemental
    static public void toOutputStream(SOAPMessage p_soap_msg, String p_enc, boolean p_is_xml_decl, OutputStream p_os)
                                                                                    throws SOAPException, IOException {
        boolean x_is_not_enc = ru.avers.informica.utils.CUtil.isStringNullOrEmpty(p_enc);
        if(x_is_not_enc && !p_is_xml_decl) p_soap_msg.writeTo(p_os);
        else {
            if(x_is_not_enc) p_enc = resolveEncoding(p_soap_msg);
            byte[] x_bytes = toStringInEncoding(p_soap_msg).getBytes(p_enc);
            if(p_is_xml_decl)
                p_os.write(ru.avers.informica.utils.xml.CUtil.constructXmlDecl(
                                            p_soap_msg.getSOAPPart().getXmlVersion(), p_enc).getBytes());
            p_os.write(x_bytes);
        }
    }
    
    public static String toStringInEncoding(SOAPMessage p_soap_msg) {
        return toString(p_soap_msg, resolveEncoding(p_soap_msg)); 
    }
    public static String toString(SOAPMessage p_soap_msg) { return toString(p_soap_msg, null); }
    
    public static String toString(SOAPMessage p_soap_msg, String p_charset) {
        String x_rv = null;
        byte[] x_bytes = toBytes(p_soap_msg);
        if(x_bytes != null) 
            try {
                x_rv = p_charset == null ? new String(x_bytes) : new String(x_bytes, p_charset);
            } catch(Exception p_ex) {
                s_logger.error("SOAPMessage to string", p_ex);
            }    

        return x_rv;
    } 
    static public byte[] toBytes(SOAPMessage p_soap_msg) {
        byte[] x_rv = null;
        if(p_soap_msg != null) {
            ByteArrayOutputStream x_baos = new ByteArrayOutputStream();
            try {
                p_soap_msg.writeTo(x_baos);
                x_rv = x_baos.toByteArray();
                x_baos.close();
            } catch(Exception p_ex) {
                s_logger.error("SOAPMessage to bytes", p_ex);
            }    
        }
        return x_rv;
    }
    
    static public String toStrHeaders(SOAPMessage p_msg) {
        String x_rv = null;
        s_logger.debug("start toStrHeaders: {}", p_msg);
        try {
            SOAPHeader x_soap_header = p_msg.getSOAPHeader();
            if(x_soap_header == null) {
                s_logger.debug("x_soap_header is null");
            } else {
                x_rv = "";
                Iterator<SOAPHeaderElement> x_it = x_soap_header.examineAllHeaderElements();
                while(x_it.hasNext()) x_rv += "\r\n" +  ru.avers.informica.utils.xml.CUtil.toPrettyStr(x_it.next());
            }
        } catch (Exception p_ex) {
            s_logger.error("toStrHeaders", p_ex);
        }
        s_logger.debug("finish toStrHeaders: {}", p_msg);
        return x_rv;
    }
    
    
    
    private static final String S_COM_SUN_XML_WS_LOGGER_NAME = "com.sun.xml.ws";
    public static boolean isComSunXmlWsLoggerLevelLessOrEqualFINE() {
        boolean x_rv = false;
        try {
            java.util.logging.Logger x_logger = 
                    java.util.logging.LogManager.getLogManager().getLogger(S_COM_SUN_XML_WS_LOGGER_NAME);
            if(x_logger != null) {
                java.util.logging.Level x_level = x_logger.getLevel();
                if(x_level != null) x_rv = (java.util.logging.Level.FINE.intValue() >= x_level.intValue());
            }
        } catch(Exception p_ex) {
            s_logger.error("isPossibleGetInboundMsg", p_ex);
        }
        return x_rv;
    }
    
    //  =============================================================================

    //  =============================================================================
    //
    //  JAX-WS: Header, HeaderList proccessing
    //
    //  =============================================================================
    
//    public static String toString(HeaderList p_hl) { return toString(p_hl, null); }
//    public static String toString(HeaderList p_hl, String p_encoding) {
//        String x_rv = null;
//        if(p_hl != null) {
//            x_rv = "";
//            for(Header x_h: p_hl) x_rv += "\r\n" + toString(x_h, p_encoding);
//        }
//        return x_rv;
//    }
//    
//    public static String toString(Header p_h) { return toString(p_h, null); }
//    public static String toString(Header p_h, String p_encoding) {
//        String x_rv = null;
//        byte[] x_b = toByteArray(p_h);
//        if(x_b != null) 
//            try {
//               x_rv = p_encoding == null ? new String(x_b) : new String(x_b, p_encoding);
//            } catch (UnsupportedEncodingException p_ex) {
//                s_logger.error("toString", p_ex);
//            }
//        return x_rv;
//    }
//    
//    public static byte[] toByteArray(Header p_h) { return toByteArray(p_h, null); }
//    public static byte[] toByteArray(Header p_h, String p_encoding) {
//        byte[] x_rv = null;
//        ByteArrayOutputStream x_baos = new ByteArrayOutputStream();
//        try {
//            toOutputStream(p_h, x_baos, p_encoding);
//            x_rv = x_baos.toByteArray();
//            x_baos.close();
//        } catch (Exception p_e) {
//            s_logger.error("toByteArray", p_e);
//        } 
//        return x_rv;
//    }
//    
//    private static void toOutputStream(Header p_h, OutputStream p_os, String p_encoding) throws XMLStreamException {
//        if(p_h != null) {
//            XMLOutputFactory x_of = XMLOutputFactory.newFactory();
//            XMLStreamWriter x_writer = p_encoding == null ? 
//                                    x_of.createXMLStreamWriter(p_os) : x_of.createXMLStreamWriter(p_os, p_encoding);
//            p_h.writeTo(x_writer);
//            x_writer.flush();
//            x_writer.close();
//        }
//    }
    
    //  =============================================================================
    
}
