package ru.avers.informica.utils.xml;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

import javax.xml.bind.*;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Dias
 */
public class CUtil {
    private static final Logger s_logger = LoggerFactory.getLogger(CUtil.class);
    
    final static private String s_default_encoding = ru.avers.informica.utils.Const.S_ENCODING_UTF8;
    
    final static private ThreadLocal<DocumentBuilder> s_doc_builder = new ThreadLocal<DocumentBuilder>() {
        @Override
        protected DocumentBuilder initialValue() {
            DocumentBuilderFactory x_dom_factory = DocumentBuilderFactory.newInstance();
            x_dom_factory.setNamespaceAware(true);
            try {
                return x_dom_factory.newDocumentBuilder();
            } catch (ParserConfigurationException p_ex) {
                s_logger.error("newDocumentBuilder", p_ex);
                throw new RuntimeException(p_ex);
            }
        }
    };
    
    //==============================================================================================
    //
    //  XMLGregorianCalendar
    //
    //==============================================================================================

    static public XMLGregorianCalendar createXMLGregorianCalendar(int p_y, int p_m, int p_d) {
        String x_debug_str = "createXMLGregorianCalendar for y=" + String.valueOf(p_y) +
                ", m=" + String.valueOf(p_m) +
                ", d=" + String.valueOf(p_d);
        s_logger.debug(x_debug_str + ": start");
        
        XMLGregorianCalendar x_rv = null;
        try {
            GregorianCalendar x_gc = (GregorianCalendar) GregorianCalendar.getInstance();
            x_gc.set(p_y, p_m, p_d, 0, 0, 0);
            x_gc.set(GregorianCalendar.MILLISECOND, 0);
            x_rv = DatatypeFactory.newInstance().newXMLGregorianCalendar(x_gc);
        } catch (DatatypeConfigurationException p_ex) {
            s_logger.error(x_debug_str, p_ex);
        }
        s_logger.debug(x_debug_str + ": x_rv=" + String.valueOf(x_rv));
        return x_rv;
    }
    
    static public XMLGregorianCalendar createXMLGregorianCalendar(Date p_dt) {
        String x_debug_str = "createXMLGregorianCalendar for date=" + String.valueOf(p_dt);
        s_logger.debug(x_debug_str + ": start");
        
        XMLGregorianCalendar x_rv = null;
        try {
            GregorianCalendar x_gc = (GregorianCalendar) GregorianCalendar.getInstance();
            x_gc.setTime(p_dt);
            x_rv = DatatypeFactory.newInstance().newXMLGregorianCalendar(x_gc);
//        } catch (DatatypeConfigurationException p_ex) {
        } catch (Exception p_ex) {
            s_logger.error("createXMLGregorianCalendar", p_ex);
        }
        s_logger.debug(x_debug_str + ": x_rv=" + String.valueOf(x_rv));
        return x_rv;
    }
    //==============================================================================================

    //==============================================================================================
    //
    //  JAXB
    //
    //==============================================================================================
    
    static public <T> T reestablish(String p_xml_str, Class<T> p_cls, JAXBContext p_jaxb_ctx) {
        T x_rv = null;
        if (p_xml_str != null) {
            String x_xml_decl = getXmlDecl(p_xml_str), x_enc = null;
            if (!ru.avers.informica.utils.CUtil.isStringNullOrEmpty(x_xml_decl)) {
                x_enc = resolveEncodingFromXmlDecl(x_xml_decl);
            }
            try {
                byte[] x_bytes = (x_enc == null ? p_xml_str.getBytes() : p_xml_str.getBytes(x_enc));
                x_rv = reestablish(new ByteArrayInputStream(x_bytes), p_cls, p_jaxb_ctx);
            } catch (UnsupportedEncodingException p_ex) {
                s_logger.error("getBytes from input str", p_ex);
            }
        }
        return x_rv;
    }
    static public <T> T reestablish(InputStream p_is, Class<T> p_cls, JAXBContext p_jaxb_ctx) {
        T x_rv = null;
        try {
            Unmarshaller x_unmarshaller = p_jaxb_ctx.createUnmarshaller();
            // Выбрасывает исключение при ошибках unmarshal, например когда jaxb не может найти свойство
            // (по умолчанию jaxb не показывает ошибок и возвращает null для не найденных свойств)
            // проверить необходимость флага -Djaxb.debug=true
            // x_unmarshaller.setEventHandler(new javax.xml.bind.helpers.DefaultValidationEventHandler());        
            JAXBElement<T> x_obj = (JAXBElement<T>)x_unmarshaller.unmarshal(new StreamSource(p_is), p_cls);
            x_rv = (x_obj != null ? x_obj.getValue() : null);
        } catch(JAXBException p_ex) {
            s_logger.error("reestablish", p_ex);
        }
        return x_rv;
    }
    static public <T> T reestablish(org.w3c.dom.Node p_node, Class<T> p_cls, JAXBContext p_jaxb_ctx) {
        T x_rv = null;
        try {
            Unmarshaller x_unmarshaller = p_jaxb_ctx.createUnmarshaller();
            JAXBElement<T> x_obj = p_cls == null ?
                     (JAXBElement<T>)x_unmarshaller.unmarshal(p_node) :
                     (JAXBElement<T>)x_unmarshaller.unmarshal(p_node, p_cls);
            x_rv = (x_obj != null ? x_obj.getValue() : null);
        } catch(JAXBException p_ex) {
            s_logger.error("reestablish", p_ex);
        }
        return x_rv;
    }
    
    static public <T> T reestablish(InputStream p_is, JAXBContext p_jaxb_ctx) {
        T x_rv = null;
        try {
            Unmarshaller x_unmarshaller = p_jaxb_ctx.createUnmarshaller();
            JAXBElement<T> x_obj = (JAXBElement<T>)x_unmarshaller.unmarshal(p_is);
            x_rv = (x_obj != null ? x_obj.getValue() : null);
        } catch(JAXBException p_ex) {
            s_logger.error("reestablish", p_ex);
        }
        return x_rv;
    }
    
    public static <T> boolean toOutputStream(
            OutputStream p_os, JAXBElement<T> p_val, JAXBContext p_jaxb_ctx, boolean p_is_formatted) {
        return toOutputStream(p_os, p_val, p_jaxb_ctx, p_is_formatted, null);        
    }
    public static <T> boolean toOutputStream(
            OutputStream p_os, JAXBElement<T> p_val, JAXBContext p_jaxb_ctx,
            boolean p_is_formatted, String p_enc) {
        return toOutputStream(p_os, (Object)p_val, p_jaxb_ctx, p_is_formatted, p_enc);

//        boolean x_rv = false;
//        try {
//            Marshaller x_marshaller = createMarshaller(p_jaxb_ctx, p_is_formatted, p_enc);
//            x_marshaller.marshal(p_val, p_os);
//            x_rv = true;
//        } catch(Exception p_ex) {
//            s_logger.error("toOutputStream", p_ex);
//        }
//        return x_rv;
    }
    
    public static <T> boolean toOutputStream(
            OutputStream p_os, Object p_val, JAXBContext p_jaxb_ctx,
            boolean p_is_formatted, String p_enc) {
        return toOutputStream(p_os, p_val, p_jaxb_ctx,  p_is_formatted,  p_enc, null);
    }
    
    public static <T> boolean toOutputStream(
                                    OutputStream p_os,
                                    Object p_val,
                                    JAXBContext p_jaxb_ctx,
                                    boolean p_is_formatted, 
                                    String p_enc,
                                    String p_schema_location) {
        boolean x_rv = false;
        try {
            Marshaller x_marshaller = createMarshaller(p_jaxb_ctx, p_is_formatted, p_enc, p_schema_location);
            x_marshaller.marshal(p_val, p_os);
            x_rv = true;
        } catch (Exception p_ex) {
            s_logger.error("toOutputStream", p_ex);
        }
        return x_rv;
    }
    
    @SuppressWarnings({"BroadCatchBlock", "TooBroadCatch"})
    public static <T> boolean toOutputStream(
            OutputStream p_os, JAXBElement<T> p_val, JAXBContext p_jaxb_ctx, Map<String,
            Object> p_marshaller_properties) {
        boolean x_rv = false;
        try {
            Marshaller x_marshaller = createMarshaller(p_jaxb_ctx, p_marshaller_properties);
            x_marshaller.marshal(p_val, p_os);
            x_rv = true;
        } catch (Exception p_ex) {
            s_logger.error("toOutputStream", p_ex);
        }
        return x_rv;
    }
    
    public static <T> String toStringXML(JAXBElement<T> p_val,
                                         JAXBContext p_jaxb_ctx, boolean p_is_formatted) {
        return toStringXML(p_val, p_jaxb_ctx, p_is_formatted, null);
    }
    
    public static <T> String toStringXML(
            JAXBElement<T> p_val, JAXBContext p_jaxb_ctx,
            boolean p_is_formatted, String p_enc) {
        String x_rv = null;
        try {
            Marshaller x_marshaller = createMarshaller(p_jaxb_ctx, p_is_formatted, p_enc);
            StringWriter x_w = new StringWriter();
            x_marshaller.marshal(p_val, x_w);
            x_rv = x_w.getBuffer().toString();
            x_w.close();
        } catch (Exception p_ex) {
            s_logger.error("toStringXML", p_ex);
        }
        return x_rv;
    }
    
    public static <T> org.w3c.dom.Node toNode(
            JAXBElement<T> p_val, JAXBContext p_jaxb_ctx, boolean p_is_formatted, String p_enc) {
        org.w3c.dom.Node x_rv = null;
        try {
            Marshaller x_marshaller = createMarshaller(p_jaxb_ctx, p_is_formatted, p_enc);
//            x_rv = createDocumentBuilder().newDocument();
            x_rv = s_doc_builder.get().newDocument();
            x_marshaller.marshal(p_val, x_rv);
        } catch (Exception p_ex) {
            s_logger.error("toNode", p_ex);
        }
        return x_rv;
    }
    
    
    static private Marshaller createMarshaller(JAXBContext p_jaxb_ctx,
                                               boolean p_is_formatted, String p_enc)
            throws JAXBException {
        return createMarshaller(p_jaxb_ctx, p_is_formatted, p_enc, null);
    }
    
    static private Marshaller createMarshaller(
            JAXBContext p_jaxb_ctx, boolean p_is_formatted,
            String p_enc, String p_schema_location)
            throws JAXBException {
        Marshaller x_marshaller = p_jaxb_ctx.createMarshaller();
        x_marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, p_is_formatted);
        if(!ru.avers.informica.utils.CUtil.isStringNullOrEmpty(p_enc))
            x_marshaller.setProperty(Marshaller.JAXB_ENCODING, p_enc);
        if(!ru.avers.informica.utils.CUtil.isStringNullOrEmpty(p_schema_location))
            x_marshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, p_schema_location);
        return x_marshaller;
    }
    
    static private Marshaller createMarshaller(JAXBContext p_jaxb_ctx, Map<String,
            Object> p_marshaller_properties)
                   throws JAXBException {
        Marshaller x_marshaller = p_jaxb_ctx.createMarshaller();
        for (Entry<String, Object> x_prop : p_marshaller_properties.entrySet()) {
            x_marshaller.setProperty(x_prop.getKey(), x_prop.getValue());
        }
        return x_marshaller;
    }    
    //==============================================================================================
  
    //==============================================================================================
    //
    //  Transform
    //
    //==============================================================================================

    static public String toPrettyStr(org.w3c.dom.Node p_node)
            throws TransformerConfigurationException, TransformerException {
        return toStr(p_node, false, true);
    }
    static public String toStr(org.w3c.dom.Node p_node)
            throws TransformerConfigurationException, TransformerException {
        return toStr(p_node, false);
    }
    static public String toStr(org.w3c.dom.Node p_node, boolean p_is_omit_xml_decl)
            throws TransformerConfigurationException, TransformerException {
        return toStr(p_node, p_is_omit_xml_decl, false);
    }
    static public String toStr(org.w3c.dom.Node p_node, String p_method, boolean p_is_omit_xml_decl)
            throws TransformerConfigurationException, TransformerException {
        return toStr(p_node, p_method, p_is_omit_xml_decl, false);
    }
    static public String toStr(org.w3c.dom.Node p_node, boolean p_is_omit_xml_decl, boolean p_is_indent)
            throws TransformerConfigurationException, TransformerException {
        return toStr(p_node, null, p_is_omit_xml_decl, p_is_indent);
    }
    static public String toStr(org.w3c.dom.Node p_node, String p_method, boolean p_is_omit_xml_decl, boolean p_is_indent)
            throws TransformerConfigurationException, TransformerException {
        return toStr(p_node, p_is_omit_xml_decl, p_is_indent, resolveXmlEncoding(p_node), p_method);
    }
    static public String toStr(
            org.w3c.dom.Node p_node, boolean p_is_omit_xml_decl, boolean p_is_indent, String p_enc)
            throws TransformerConfigurationException, TransformerException {
        return toStr(p_node, p_is_omit_xml_decl, p_is_indent, p_enc, null);
    }
    static public String toStr(
                        org.w3c.dom.Node p_node, 
                        boolean p_is_omit_xml_decl, 
                        boolean p_is_indent, 
                        String p_enc,
                        String p_method)
            throws TransformerConfigurationException, TransformerException {
        return toStr(p_node, createFormatProps(p_is_omit_xml_decl, p_is_indent, p_enc, p_method));
    }
    
    //  TODO здесь непонятно действие параметра p_enc ???
    static public Properties createFormatProps(
                                boolean p_is_omit_xml_decl, 
                                boolean p_is_indent, 
                                String p_enc,
                                String p_method) {
        Properties x_oformat = new Properties();
        if (p_enc != null) {
            x_oformat.setProperty(OutputKeys.ENCODING, p_enc);
        }
        if (p_is_indent) {
            x_oformat.setProperty(OutputKeys.INDENT, "yes");
        }
        if (p_is_omit_xml_decl) {
            x_oformat.setProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
        }
        if (p_method != null) {
            x_oformat.setProperty(OutputKeys.METHOD, p_method);
        }
        return x_oformat.isEmpty() ? null : x_oformat;
    }

    //  в строку в текущей кодировке
//    static public String toStr(org.w3c.dom.Node p_node, Properties p_oformat) 
    static private String toStr(org.w3c.dom.Node p_node, Properties p_oformat)
            throws TransformerConfigurationException, TransformerException {
        StringWriter x_sw = new StringWriter();
        toResult(p_node, p_oformat, new javax.xml.transform.stream.StreamResult(x_sw));
        return x_sw.getBuffer().toString();
    }
    
    static public byte[] toBytes(org.w3c.dom.Node p_node, Properties p_oformat)
            throws TransformerConfigurationException, TransformerException, UnsupportedEncodingException {
        ByteArrayOutputStream x_baos = new ByteArrayOutputStream();
        toOutputStream(p_node, p_oformat, x_baos);
        return x_baos.toByteArray();
    }
    static public void saveToFile(
            String p_fn, org.w3c.dom.Node p_node, String p_method,
            boolean p_is_omit_xml_decl, boolean p_is_indent)
            throws FileNotFoundException, TransformerException, IOException {
        saveToFile(p_fn, p_node, p_is_omit_xml_decl, p_is_indent, resolveXmlEncoding(p_node), p_method);
    }
    static public void saveToFile(
            String p_fn, org.w3c.dom.Node p_node, boolean p_is_omit_xml_decl, boolean p_is_indent)
            throws FileNotFoundException, TransformerException, IOException {
        saveToFile(p_fn, p_node, p_is_omit_xml_decl, p_is_indent, resolveXmlEncoding(p_node));
    }
    static public void saveToFile(
            String p_fn, org.w3c.dom.Node p_node, boolean p_is_omit_xml_decl,
            boolean p_is_indent, String p_enc)
            throws FileNotFoundException, TransformerException, IOException {
        saveToFile(p_fn, p_node, p_is_omit_xml_decl, p_is_indent, p_enc, null);
    }
    static public void saveToFile(
                            String p_fn,
                            org.w3c.dom.Node p_node, 
                            boolean p_is_omit_xml_decl, 
                            boolean p_is_indent, 
                            String p_enc,
                            String p_method)
            throws FileNotFoundException, TransformerException, IOException {
        saveToFile(p_fn, p_node, createFormatProps(p_is_omit_xml_decl, p_is_indent, p_enc, p_method));
    }
    static private void saveToFile(String p_fn, org.w3c.dom.Node p_node, Properties p_oformat)
            throws FileNotFoundException, TransformerException, IOException {
        OutputStream x_os = new FileOutputStream(p_fn);
        toOutputStream(p_node, p_oformat, x_os);
        x_os.close();
    }
    static public void toOutputStream(org.w3c.dom.Node p_node, Properties p_oformat, OutputStream p_os)
            throws TransformerConfigurationException, TransformerException {
        toResult(p_node, p_oformat, new javax.xml.transform.stream.StreamResult(p_os));
    }
    
    static public String getXmlStrWithEncInXmlDecl(String p_xml_str, String p_enc) {
        String x_rv = null;
        final String x_default_ver = "1.0";
        
        String x_enc = ru.avers.informica.utils.CUtil.isStringNullOrEmpty(p_enc) ? s_default_encoding : p_enc;
        String x_xml_decl = getXmlDecl(p_xml_str);
        if (x_xml_decl == null) {
            x_rv = constructXmlDecl(x_default_ver, x_enc) + p_xml_str;
        } else {
            String x_src_enc = resolveEncodingFromXmlDecl(x_xml_decl);
            if (x_enc.equalsIgnoreCase(x_src_enc)) {
                x_rv = p_xml_str;
            } else {
                String x_ver = resolveVersionFromXmlDecl(x_xml_decl);
                if (x_ver == null) {
                    x_ver = x_default_ver;
                }
                String x_new_xml_decl = constructXmlDecl(x_ver, x_enc);
                x_rv = x_new_xml_decl + p_xml_str.substring(x_xml_decl.length());
            }
        }
        return x_rv;
    }
    
    static public void saveToFileXmlStr(String p_fn, String p_xml_str)
            throws FileNotFoundException, IOException {
        OutputStream x_os = new FileOutputStream(p_fn);
        toOutputStreamXmlStr(p_xml_str, x_os);
        x_os.close();
    }
    static public void toOutputStreamXmlStr(String p_xml_str, OutputStream p_os)
            throws UnsupportedEncodingException, IOException {
        String x_xml_decl = getXmlDecl(p_xml_str), x_enc = null;
        if (!ru.avers.informica.utils.CUtil.isStringNullOrEmpty(x_xml_decl)) {
            x_enc = resolveEncodingFromXmlDecl(x_xml_decl);
        }
        byte[] x_bytes = ru.avers.informica.utils.CUtil.isStringNullOrEmpty(x_enc) ?
                                                              p_xml_str.getBytes() :
                p_xml_str.getBytes(x_enc);
        p_os.write(x_bytes);
    }
    
    private static void toResult(org.w3c.dom.Node p_node, Properties p_oformat,
                                 javax.xml.transform.Result p_result)
             throws TransformerConfigurationException, TransformerException {
        if (p_node == null) {
            return;
        }
        Transformer x_t = TransformerFactory.newInstance().newTransformer();
//        s_logger.debug("Transformer: {}", x_t);
        if (p_oformat != null) {
            x_t.setOutputProperties(p_oformat);
        }
        x_t.transform(new javax.xml.transform.dom.DOMSource(p_node), p_result);
    }
    
    //  TODO is needed charset?
    static public javax.xml.transform.Source createSourceFromStr(String p_str, String p_charset)
            throws UnsupportedEncodingException {
        if (p_str == null) {
            return null;
        }
        return createSource(
                ru.avers.informica.utils.CUtil.isStringNullOrEmpty(p_charset) ?
                        p_str.getBytes() :
                        p_str.getBytes(p_charset));
    }
    static private javax.xml.transform.Source createSource(byte[] p_data) {
        return new StreamSource(new ByteArrayInputStream(p_data));
    }
    //==============================================================================================

    
    //==============================================================================================
    //
    //  Produce org.w3c.dom.Document
    //
    //==============================================================================================
    
    static public org.w3c.dom.Document loadDocument(String p_str)
            throws ParserConfigurationException, SAXException, IOException {
        org.xml.sax.InputSource x_is = new org.xml.sax.InputSource(new StringReader(p_str));
//        x_is.setEncoding(Charset.defaultCharset().name());
        return loadDocument(x_is);
    }
    
    static public org.w3c.dom.Document loadDocumentFromFile(String p_fn)
            throws FileNotFoundException, ParserConfigurationException, SAXException, IOException {
        InputStream x_is = new FileInputStream(p_fn);
        try {
            return loadDocumentFromInputStream(x_is);
        } finally {
            try {
                x_is.close();
            } catch (IOException p_ex) {
                s_logger.error("close file input stream", p_ex);
                throw p_ex;
            }
        }
    }
    static public org.w3c.dom.Document loadDocumentFromInputStream(InputStream p_is)
            throws ParserConfigurationException, SAXException, IOException {
        org.xml.sax.InputSource x_isrc = new org.xml.sax.InputSource(p_is);
        //            x_isrc.setEncoding(Charset.defaultCharset().name());
        return loadDocument(x_isrc);
    }
    
    //  TODO review to do private
    static public org.w3c.dom.Document loadDocument(org.xml.sax.InputSource p_is) 
            throws ParserConfigurationException, SAXException, IOException {
//        return createDocumentBuilder().parse(p_is);
        return s_doc_builder.get().parse(p_is);
    }
    
//    static private javax.xml.parsers.DocumentBuilder createDocumentBuilder() throws ParserConfigurationException {
//        return createDocumentBuilder(true);
//    }
//    static private javax.xml.parsers.DocumentBuilder createDocumentBuilder(boolean p_is_ns_aware) 
//                                                                                throws ParserConfigurationException {
//        javax.xml.parsers.DocumentBuilderFactory x_dbf = javax.xml.parsers.DocumentBuilderFactory.newInstance();
//        x_dbf.setNamespaceAware(p_is_ns_aware);
//        return x_dbf.newDocumentBuilder();
//    }
    
    static public org.w3c.dom.Document clone(org.w3c.dom.Document p_src_doc)
            throws ParserConfigurationException {
        org.w3c.dom.Document x_doc = p_src_doc.getImplementation().createDocument(
                                        p_src_doc.getNamespaceURI(), null, p_src_doc.getDoctype());
        x_doc.appendChild(x_doc.importNode(p_src_doc.getDocumentElement(), true));
        return x_doc;
    } 

    static public org.w3c.dom.Document cloneByTransformer(org.w3c.dom.Document p_src_doc)
            throws TransformerConfigurationException, TransformerException {
        Properties x_oformat = null;

        //------------------
        //  TODO is needed this block?
        String x_enc = getXmlEncoding(p_src_doc);
        if (x_enc != null) {
            x_oformat = new Properties();
            x_oformat.setProperty(OutputKeys.ENCODING, x_enc);
        }
        //------------------
        
        DOMResult x_result = new DOMResult();
        toResult(p_src_doc, x_oformat, x_result);
        return (org.w3c.dom.Document)x_result.getNode();
    }
    
    static public String getFirstNodeValue(org.w3c.dom.Element p_parent, String p_ns, String p_local_nm) {
        String x_rv = null;
        if (p_parent != null) {
            org.w3c.dom.NodeList x_node_list = p_parent.getElementsByTagNameNS(p_ns, p_local_nm);
            if (x_node_list.getLength() > 0) {
                x_node_list = x_node_list.item(0).getChildNodes();
                for (int i = 0; i < x_node_list.getLength(); ++i) {
                    org.w3c.dom.Node x_node = x_node_list.item(i);
                    if (x_node.getNodeType() == org.w3c.dom.Node.TEXT_NODE) {
                        x_rv = x_node.getNodeValue();
                        break;
                    }
                }
            }
        }
        return x_rv;
    }

    static public org.w3c.dom.Node getFirstChildNodeWithType(org.w3c.dom.Node p_node, short p_type) {
        if (p_node == null) {
            return null;
        }
        org.w3c.dom.Node x_rv = null;
        org.w3c.dom.NodeList x_node_list = p_node.getChildNodes();
        for (int i = 0; i < x_node_list.getLength(); ++i) {
            org.w3c.dom.Node x_node = x_node_list.item(i);
            if (x_node.getNodeType() == p_type) {
                x_rv = x_node;
                break;
            }
        }
        return x_rv;
    }
    
    static private void inspectNodeList(org.w3c.dom.NodeList p_val) {
        if (p_val == null) {
            s_logger.debug("input node list is null");
        }
        int x_len = p_val.getLength();
        s_logger.debug("input node list length: {}", x_len);
        for (int i = 0; i < x_len; ++i) {
            org.w3c.dom.Node x_node = p_val.item(i);
            s_logger.debug(
                    "\r\nnum:{}\r\nBaseURI:{}\r\nLocalName:{}\r\nNamespaceURI:{}\r\nNodeName:{}\r\nNodeType:{}\r\n"
                            + "NodeValue:{}\r\nPrefix:{}\r\nTextContent:{}", 
                    i, x_node.getBaseURI(), x_node.getLocalName(), x_node.getNamespaceURI(), x_node.getNodeName(), 
                    x_node.getNodeType(), x_node.getNodeValue(), x_node.getPrefix(), x_node.getTextContent());
        }
    }
    
    
    //==============================================================================================
    
    static public String resolveXmlEncoding(org.w3c.dom.Node p_node) {
        String x_rv = getXmlEncoding(p_node);
        return x_rv == null ? s_default_encoding : x_rv;
    }
    
    static public String getXmlEncoding(org.w3c.dom.Node p_node) {
        String x_rv = null;
        org.w3c.dom.Document x_doc = 
                p_node instanceof org.w3c.dom.Document ?
                        (org.w3c.dom.Document)p_node : p_node.getOwnerDocument();
        if (x_doc != null) {
            x_rv = x_doc.getXmlEncoding();
        }
        return x_rv;
    }

    final static private String
                        s_enc = "encoding",
                        s_version = "version";
    static public String constructXmlDecl(String p_version, String p_encoding) {
        String x_rv = null, x_ver = "", x_enc = "";
        if (p_version != null && !p_version.isEmpty()) {
            x_ver = (new StringBuilder())
                    .append(" ").append(s_version).append("=\"").append(p_version).append("\"").toString();
        }
        if (p_encoding != null && !p_encoding.isEmpty()) {
            x_enc = (new StringBuilder())
                    .append(" ").append(s_enc).append("=\"").append(p_encoding).append("\"").toString();
        }
        if (!x_ver.isEmpty() || !x_enc.isEmpty()) {
            x_rv = (new StringBuilder()).append("<?xml").append(x_ver).append(x_enc).append("?>").toString();
        }
        return x_rv;
    }
    
    static public String getXmlStrWithoutXmlDecl(String p_xml_str) {
        String x_xml_decl = getXmlDecl(p_xml_str);
        return (x_xml_decl == null ? p_xml_str : p_xml_str.substring(x_xml_decl.length()));
    }
    
    static public boolean hasXmlDecl(String p_doc_str) { return (getXmlDecl(p_doc_str) != null); }
    
    static public String getXmlDecl(String p_doc_str) {
        String x_rv = null;
        String x_pattern = "<\\?xml[^(\\?>)]*\\?>";
        Pattern x_p = Pattern.compile(x_pattern);
        Matcher x_m = x_p.matcher(p_doc_str);
        if (x_m.find()) {
            x_rv = x_m.group(0);
        }
        return x_rv;
    }

    static public String resolveEncodingFromXmlDecl(String p_xml_decl) {
        return resolveValueFromXmlDecl(p_xml_decl, s_enc);
    }

    static public String resolveVersionFromXmlDecl(String p_xml_decl) {
        return resolveValueFromXmlDecl(p_xml_decl, s_version);
    }

    static private String resolveValueFromXmlDecl(String p_xml_decl, String p_what) {
        String x_rv = null;
        String x_pattern = p_what + "[\\s]*=[\\s]*[\"']([^\"']*)[\"']";
        Pattern x_p = Pattern.compile(x_pattern);
        Matcher x_m = x_p.matcher(p_xml_decl);
        if (x_m.find()) {
            x_rv = x_m.group(1);
        }
        return x_rv;
    }
    
}
