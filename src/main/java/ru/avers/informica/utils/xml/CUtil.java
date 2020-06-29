package ru.avers.informica.utils.xml;

import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class CUtil {

    final static private String S_DEFAULT_ENCODING = ru.avers.informica.utils.Const.S_ENCODING_UTF8;
    
    final static private ThreadLocal<DocumentBuilder> S_DOC_BUILDER =
            new ThreadLocal<DocumentBuilder>() {
        @Override
        protected DocumentBuilder initialValue() {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            documentBuilderFactory.setNamespaceAware(true);
            try {
                return documentBuilderFactory.newDocumentBuilder();
            } catch (ParserConfigurationException p_ex) {
                log.error("newDocumentBuilder", p_ex);
                throw new RuntimeException(p_ex);
            }
        }
    };
    
    //==============================================================================================
    //
    //  XMLGregorianCalendar
    //
    //==============================================================================================

    static public XMLGregorianCalendar createXMLGregorianCalendar(int pY, int pM, int pD) {
        String debugStr = "createXMLGregorianCalendar for y=" + String.valueOf(pY) +
                ", m=" + String.valueOf(pM) +
                ", d=" + String.valueOf(pD);
        log.debug(debugStr + ": start");
        
        XMLGregorianCalendar rv = null;
        try {
            GregorianCalendar gc = (GregorianCalendar) GregorianCalendar.getInstance();
            gc.set(pY, pM, pD, 0, 0, 0);
            gc.set(GregorianCalendar.MILLISECOND, 0);
            rv = DatatypeFactory.newInstance().newXMLGregorianCalendar(gc);
        } catch (DatatypeConfigurationException p_ex) {
            log.error(debugStr, p_ex);
        }
        log.debug(debugStr + ": x_rv=" + String.valueOf(rv));
        return rv;
    }
    
    static public XMLGregorianCalendar createXMLGregorianCalendar(Date pDt) {
        String debugStr = "createXMLGregorianCalendar for date=" + String.valueOf(pDt);
        log.debug(debugStr + ": start");
        
        XMLGregorianCalendar rv = null;
        try {
            GregorianCalendar gc = (GregorianCalendar) GregorianCalendar.getInstance();
            gc.setTime(pDt);
            rv = DatatypeFactory.newInstance().newXMLGregorianCalendar(gc);
//        } catch (DatatypeConfigurationException p_ex) {
        } catch (Exception p_ex) {
            log.error("createXMLGregorianCalendar", p_ex);
        }
        log.debug(debugStr + ": x_rv=" + String.valueOf(rv));
        return rv;
    }
    //==============================================================================================
    //
    //  JAXB
    //
    //==============================================================================================
    
    static public <T> T reestablish(String pXmlStr, Class<T> pCls, JAXBContext pJaxbCtx) {
        T rv = null;
        if (pXmlStr != null) {
            String xmlDecl = getXmlDecl(pXmlStr), enc = null;
            if (!ru.avers.informica.utils.CUtil.isStringNullOrEmpty(xmlDecl)) {
                enc = resolveEncodingFromXmlDecl(xmlDecl);
            }
            try {
                byte[] bytes = (enc == null ? pXmlStr.getBytes() : pXmlStr.getBytes(enc));
                rv = reestablish(new ByteArrayInputStream(bytes), pCls, pJaxbCtx);
            } catch (UnsupportedEncodingException p_ex) {
                log.error("getBytes from input str", p_ex);
            }
        }
        return rv;
    }
    static public <T> T reestablish(InputStream pIs, Class<T> pCls, JAXBContext pJaxbCtx) {
        T rv = null;
        try {
            Unmarshaller unmarshaller = pJaxbCtx.createUnmarshaller();
            // Выбрасывает исключение при ошибках unmarshal, например когда jaxb не может найти свойство
            // (по умолчанию jaxb не показывает ошибок и возвращает null для не найденных свойств)
            // проверить необходимость флага -Djaxb.debug=true
            // x_unmarshaller.setEventHandler(new javax.xml.bind.helpers.DefaultValidationEventHandler());        
            JAXBElement<T> obj = (JAXBElement<T>)unmarshaller.unmarshal(new StreamSource(pIs), pCls);
            rv = (obj != null ? obj.getValue() : null);
        } catch(JAXBException p_ex) {
            log.error("reestablish", p_ex);
        }
        return rv;
    }
    static public <T> T reestablish(org.w3c.dom.Node pNode, Class<T> pCls, JAXBContext pJaxbCtx) {
        T rv = null;
        try {
            Unmarshaller unmarshaller = pJaxbCtx.createUnmarshaller();
            JAXBElement<T> obj = pCls == null ?
                     (JAXBElement<T>)unmarshaller.unmarshal(pNode) :
                     (JAXBElement<T>)unmarshaller.unmarshal(pNode, pCls);
            rv = (obj != null ? obj.getValue() : null);
        } catch(JAXBException p_ex) {
            log.error("reestablish", p_ex);
        }
        return rv;
    }
    
    static public <T> T reestablish(InputStream pIs, JAXBContext pJaxbCtx) {
        T rv = null;
        try {
            Unmarshaller unmarshaller = pJaxbCtx.createUnmarshaller();
            JAXBElement<T> obj = (JAXBElement<T>)unmarshaller.unmarshal(pIs);
            rv = (obj != null ? obj.getValue() : null);
        } catch(JAXBException p_ex) {
            log.error("reestablish", p_ex);
        }
        return rv;
    }
    
    public static <T> boolean toOutputStream(
            OutputStream pOs, JAXBElement<T> tjaxbElement,
            JAXBContext pJaxbCtx, boolean pIsFormatted) {
        return toOutputStream(pOs, tjaxbElement, pJaxbCtx, pIsFormatted, null);
    }
    public static <T> boolean toOutputStream(
            OutputStream pOs, JAXBElement<T> tjaxbElement, JAXBContext pJaxbCtx,
            boolean pIsFormatted, String pEnc) {
        return toOutputStream(pOs, (Object)tjaxbElement, pJaxbCtx, pIsFormatted, pEnc);
    }
    
    public static <T> boolean toOutputStream(
            OutputStream pOs, Object pVal, JAXBContext pJaxbCtx,
            boolean pIsFormatted, String pEnc) {
        return toOutputStream(pOs, pVal, pJaxbCtx,  pIsFormatted,  pEnc, null);
    }
    
    public static <T> boolean toOutputStream(
                                    OutputStream pOs,
                                    Object pVal,
                                    JAXBContext pJaxbCtx,
                                    boolean pIsFormatted,
                                    String pEnc,
                                    String pSchemaLocation) {
        boolean rv = false;
        try {
            Marshaller marshaller = createMarshaller(pJaxbCtx, pIsFormatted, pEnc, pSchemaLocation);
            marshaller.marshal(pVal, pOs);
            rv = true;
        } catch (Exception p_ex) {
            log.error("toOutputStream", p_ex);
        }
        return rv;
    }
    
    @SuppressWarnings({"BroadCatchBlock", "TooBroadCatch"})
    public static <T> boolean toOutputStream(
            OutputStream pOs, JAXBElement<T> tjaxbElement, JAXBContext pJaxbCtx,
            Map<String,Object> pMarshallerProperties) {
        boolean rv = false;
        try {
            Marshaller marshaller = createMarshaller(pJaxbCtx, pMarshallerProperties);
            marshaller.marshal(tjaxbElement, pOs);
            rv = true;
        } catch (Exception p_ex) {
            log.error("toOutputStream", p_ex);
        }
        return rv;
    }
    
    public static <T> String toStringXML(JAXBElement<T> tjaxbElement,
                                         JAXBContext pJaxbCtx,
                                         boolean pIsFormatted) {
        return toStringXML(tjaxbElement, pJaxbCtx, pIsFormatted, null);
    }
    
    public static <T> String toStringXML(
            JAXBElement<T> tjaxbElement, JAXBContext pJaxbCtx,
            boolean pIsFormatted, String pEnc) {
        String rv = null;
        try {
            Marshaller marshaller = createMarshaller(pJaxbCtx, pIsFormatted, pEnc);
            StringWriter stringWriter = new StringWriter();
            marshaller.marshal(tjaxbElement, stringWriter);
            rv = stringWriter.getBuffer().toString();
            stringWriter.close();
        } catch (Exception p_ex) {
            log.error("toStringXML", p_ex);
        }
        return rv;
    }
    
    public static <T> org.w3c.dom.Node toNode(
            JAXBElement<T> tjaxbElement, JAXBContext pJaxbCtx,
            boolean pIsFormatted, String pEnc) {
        org.w3c.dom.Node rv = null;
        try {
            Marshaller marshaller = createMarshaller(pJaxbCtx, pIsFormatted, pEnc);
//            x_rv = createDocumentBuilder().newDocument();
            rv = S_DOC_BUILDER.get().newDocument();
            marshaller.marshal(tjaxbElement, rv);
        } catch (Exception p_ex) {
            log.error("toNode", p_ex);
        }
        return rv;
    }
    
    
    static private Marshaller createMarshaller(JAXBContext pJaxbCtx,
                                               boolean pIsFormatted, String pEnc)
            throws JAXBException {
        return createMarshaller(pJaxbCtx, pIsFormatted, pEnc, null);
    }
    
    static private Marshaller createMarshaller(
            JAXBContext pJaxbCtx, boolean pIsFormatted,
            String pEnc, String pSchemaLocation)
            throws JAXBException {
        Marshaller marshaller = pJaxbCtx.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, pIsFormatted);
        if (!ru.avers.informica.utils.CUtil.isStringNullOrEmpty(pEnc)) {
            marshaller.setProperty(Marshaller.JAXB_ENCODING, pEnc);
        }
        if (!ru.avers.informica.utils.CUtil.isStringNullOrEmpty(pSchemaLocation)) {
            marshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, pSchemaLocation);
        }
        return marshaller;
    }
    
    static private Marshaller createMarshaller(JAXBContext pJaxbCtx,
                                               Map<String,Object> pMarshallerProperties)
                   throws JAXBException {
        Marshaller marshaller = pJaxbCtx.createMarshaller();
        for (Entry<String, Object> stringObjectEntry : pMarshallerProperties.entrySet()) {
            marshaller.setProperty(stringObjectEntry.getKey(), stringObjectEntry.getValue());
        }
        return marshaller;
    }    
    //==============================================================================================
    //
    //  Transform
    //
    //==============================================================================================

    static public String toPrettyStr(org.w3c.dom.Node pNode)
            throws TransformerConfigurationException, TransformerException {
        return toStr(pNode, false, true);
    }
    static public String toStr(org.w3c.dom.Node pNode)
            throws TransformerConfigurationException, TransformerException {
        return toStr(pNode, false);
    }
    static public String toStr(org.w3c.dom.Node pNode, boolean pIsOmitXmlDecl)
            throws TransformerConfigurationException, TransformerException {
        return toStr(pNode, pIsOmitXmlDecl, false);
    }
    static public String toStr(org.w3c.dom.Node pNode, String pMethod, boolean pIsOmitXmlDecl)
            throws TransformerConfigurationException, TransformerException {
        return toStr(pNode, pMethod, pIsOmitXmlDecl, false);
    }
    static public String toStr(org.w3c.dom.Node pNode, boolean pIsOmitXmlDecl, boolean pIsIndent)
            throws TransformerConfigurationException, TransformerException {
        return toStr(pNode, null, pIsOmitXmlDecl, pIsIndent);
    }
    static public String toStr(org.w3c.dom.Node pNode, String pMethod,
                               boolean pIsOmitXmlDecl, boolean pIsIndent)
            throws TransformerConfigurationException, TransformerException {
        return toStr(pNode, pIsOmitXmlDecl, pIsIndent, resolveXmlEncoding(pNode), pMethod);
    }
    static public String toStr(
            org.w3c.dom.Node pNode, boolean pIsOmitXmlDecl,
            boolean pIsIndent, String pEnc)
            throws TransformerConfigurationException, TransformerException {
        return toStr(pNode, pIsOmitXmlDecl, pIsIndent, pEnc, null);
    }
    static public String toStr(
                        org.w3c.dom.Node pNode,
                        boolean pIsOmitXmlDecl,
                        boolean pIsIndent,
                        String pEnc,
                        String pMethod)
            throws TransformerConfigurationException, TransformerException {
        return toStr(pNode, createFormatProps(pIsOmitXmlDecl, pIsIndent, pEnc, pMethod));
    }
    
    //  TODO здесь непонятно действие параметра p_enc ???
    static public Properties createFormatProps(
                                boolean pIsOmitXmlDecl,
                                boolean pIsIndent,
                                String pEnc,
                                String pMethod) {
        Properties oformat = new Properties();
        if (pEnc != null) {
            oformat.setProperty(OutputKeys.ENCODING, pEnc);
        }
        if (pIsIndent) {
            oformat.setProperty(OutputKeys.INDENT, "yes");
        }
        if (pIsOmitXmlDecl) {
            oformat.setProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
        }
        if (pMethod != null) {
            oformat.setProperty(OutputKeys.METHOD, pMethod);
        }
        return oformat.isEmpty() ? null : oformat;
    }

    //  в строку в текущей кодировке
//    static public String toStr(org.w3c.dom.Node p_node, Properties p_oformat) 
    static private String toStr(org.w3c.dom.Node pNode, Properties pOformat)
            throws TransformerConfigurationException, TransformerException {
        StringWriter sw = new StringWriter();
        toResult(pNode, pOformat, new javax.xml.transform.stream.StreamResult(sw));
        return sw.getBuffer().toString();
    }
    
    static public byte[] toBytes(org.w3c.dom.Node pNode, Properties pOformat)
            throws TransformerConfigurationException, TransformerException, UnsupportedEncodingException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        toOutputStream(pNode, pOformat, baos);
        return baos.toByteArray();
    }
    static public void saveToFile(
            String pFn, org.w3c.dom.Node pNode, String pMethod,
            boolean pIsOmitXmlDecl, boolean pIsIndent)
            throws FileNotFoundException, TransformerException, IOException {
        saveToFile(pFn, pNode, pIsOmitXmlDecl, pIsIndent, resolveXmlEncoding(pNode), pMethod);
    }
    static public void saveToFile(
            String pFn, org.w3c.dom.Node pNode, boolean pIsOmitXmlDecl, boolean pIsIndent)
            throws FileNotFoundException, TransformerException, IOException {
        saveToFile(pFn, pNode, pIsOmitXmlDecl, pIsIndent, resolveXmlEncoding(pNode));
    }
    static public void saveToFile(
            String pFn, org.w3c.dom.Node pNode, boolean pIsOmitXmlDecl,
            boolean pIsIndent, String pEnc)
            throws FileNotFoundException, TransformerException, IOException {
        saveToFile(pFn, pNode, pIsOmitXmlDecl, pIsIndent, pEnc, null);
    }
    static public void saveToFile(
                            String pFn,
                            org.w3c.dom.Node pNode,
                            boolean pIsOmitXmlDecl,
                            boolean pIsIndent,
                            String pEnc,
                            String pMethod)
            throws FileNotFoundException, TransformerException, IOException {
        saveToFile(pFn, pNode, createFormatProps(pIsOmitXmlDecl, pIsIndent, pEnc, pMethod));
    }
    static private void saveToFile(String pFn, org.w3c.dom.Node pNode, Properties pOformat)
            throws FileNotFoundException, TransformerException, IOException {
        OutputStream os = new FileOutputStream(pFn);
        toOutputStream(pNode, pOformat, os);
        os.close();
    }
    static public void toOutputStream(org.w3c.dom.Node pNode, Properties pOformat, OutputStream pOs)
            throws TransformerConfigurationException, TransformerException {
        toResult(pNode, pOformat, new javax.xml.transform.stream.StreamResult(pOs));
    }
    
    static public String getXmlStrWithEncInXmlDecl(String pXmlStr, String pEnc) {
        String rv = null;
        final String defaultVer = "1.0";
        
        String enc = ru.avers.informica.utils.CUtil.isStringNullOrEmpty(pEnc) ?
                S_DEFAULT_ENCODING : pEnc;
        String xmlDecl = getXmlDecl(pXmlStr);
        if (xmlDecl == null) {
            rv = constructXmlDecl(defaultVer, enc) + pXmlStr;
        } else {
            String srcEnc = resolveEncodingFromXmlDecl(xmlDecl);
            if (enc.equalsIgnoreCase(srcEnc)) {
                rv = pXmlStr;
            } else {
                String ver = resolveVersionFromXmlDecl(xmlDecl);
                if (ver == null) {
                    ver = defaultVer;
                }
                String newXmlDecl = constructXmlDecl(ver, enc);
                rv = newXmlDecl + pXmlStr.substring(xmlDecl.length());
            }
        }
        return rv;
    }
    
    static public void saveToFileXmlStr(String pFn, String pXmlStr)
            throws FileNotFoundException, IOException {
        OutputStream os = new FileOutputStream(pFn);
        toOutputStreamXmlStr(pXmlStr, os);
        os.close();
    }
    static public void toOutputStreamXmlStr(String pXmlStr, OutputStream pOs)
            throws UnsupportedEncodingException, IOException {
        String xmlDecl = getXmlDecl(pXmlStr), enc = null;
        if (!ru.avers.informica.utils.CUtil.isStringNullOrEmpty(xmlDecl)) {
            enc = resolveEncodingFromXmlDecl(xmlDecl);
        }
        byte[] bytes = ru.avers.informica.utils.CUtil.isStringNullOrEmpty(enc) ?
                                                              pXmlStr.getBytes() :
                pXmlStr.getBytes(enc);
        pOs.write(bytes);
    }
    
    private static void toResult(org.w3c.dom.Node pNode, Properties pOformat,
                                 javax.xml.transform.Result pResult)
             throws TransformerConfigurationException, TransformerException {
        if (pNode == null) {
            return;
        }
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
//        s_logger.debug("Transformer: {}", x_t);
        if (pOformat != null) {
            transformer.setOutputProperties(pOformat);
        }
        transformer.transform(new javax.xml.transform.dom.DOMSource(pNode), pResult);
    }
    
    //  TODO is needed charset?
    static public javax.xml.transform.Source createSourceFromStr(String pStr, String pCharset)
            throws UnsupportedEncodingException {
        if (pStr == null) {
            return null;
        }
        return createSource(
                ru.avers.informica.utils.CUtil.isStringNullOrEmpty(pCharset) ?
                        pStr.getBytes() :
                        pStr.getBytes(pCharset));
    }
    static private javax.xml.transform.Source createSource(byte[] pData) {
        return new StreamSource(new ByteArrayInputStream(pData));
    }
    //==============================================================================================
    //
    //  Produce org.w3c.dom.Document
    //
    //==============================================================================================
    
    static public org.w3c.dom.Document loadDocument(String pStr)
            throws ParserConfigurationException, SAXException, IOException {
        org.xml.sax.InputSource is = new org.xml.sax.InputSource(new StringReader(pStr));
//        x_is.setEncoding(Charset.defaultCharset().name());
        return loadDocument(is);
    }
    
    static public org.w3c.dom.Document loadDocumentFromFile(String pFn)
            throws FileNotFoundException, ParserConfigurationException, SAXException, IOException {
        InputStream is = new FileInputStream(pFn);
        try {
            return loadDocumentFromInputStream(is);
        } finally {
            try {
                is.close();
            } catch (IOException ex) {
                log.error("close file input stream", ex);
                throw ex;
            }
        }
    }
    static public org.w3c.dom.Document loadDocumentFromInputStream(InputStream pIs)
            throws ParserConfigurationException, SAXException, IOException {
        org.xml.sax.InputSource isrc = new org.xml.sax.InputSource(pIs);
        //            x_isrc.setEncoding(Charset.defaultCharset().name());
        return loadDocument(isrc);
    }
    
    //  TODO review to do private
    static public org.w3c.dom.Document loadDocument(org.xml.sax.InputSource pIs)
            throws ParserConfigurationException, SAXException, IOException {
//        return createDocumentBuilder().parse(p_is);
        return S_DOC_BUILDER.get().parse(pIs);
    }
    
    static public org.w3c.dom.Document clone(org.w3c.dom.Document pSrcDoc)
            throws ParserConfigurationException {
        org.w3c.dom.Document doc = pSrcDoc.getImplementation().createDocument(
                                        pSrcDoc.getNamespaceURI(),
                null, pSrcDoc.getDoctype());
        doc.appendChild(doc.importNode(pSrcDoc.getDocumentElement(), true));
        return doc;
    } 

    static public org.w3c.dom.Document cloneByTransformer(org.w3c.dom.Document pSrcDoc)
            throws TransformerConfigurationException, TransformerException {
        Properties oformat = null;

        //------------------
        //  TODO is needed this block?
        String enc = getXmlEncoding(pSrcDoc);
        if (enc != null) {
            oformat = new Properties();
            oformat.setProperty(OutputKeys.ENCODING, enc);
        }
        //------------------
        
        DOMResult result = new DOMResult();
        toResult(pSrcDoc, oformat, result);
        return (org.w3c.dom.Document)result.getNode();
    }
    
    static public String getFirstNodeValue(org.w3c.dom.Element pParent,
                                           String pNs,
                                           String pLocalNm) {
        String rv = null;
        if (pParent != null) {
            org.w3c.dom.NodeList nodeList = pParent.getElementsByTagNameNS(pNs, pLocalNm);
            if (nodeList.getLength() > 0) {
                nodeList = nodeList.item(0).getChildNodes();
                for (int i = 0; i < nodeList.getLength(); ++i) {
                    org.w3c.dom.Node node = nodeList.item(i);
                    if (node.getNodeType() == org.w3c.dom.Node.TEXT_NODE) {
                        rv = node.getNodeValue();
                        break;
                    }
                }
            }
        }
        return rv;
    }

    static public org.w3c.dom.Node getFirstChildNodeWithType(org.w3c.dom.Node pNode, short pType) {
        if (pNode == null) {
            return null;
        }
        org.w3c.dom.Node rv = null;
        org.w3c.dom.NodeList nodeList = pNode.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); ++i) {
            org.w3c.dom.Node node = nodeList.item(i);
            if (node.getNodeType() == pType) {
                rv = node;
                break;
            }
        }
        return rv;
    }
    
    static private void inspectNodeList(org.w3c.dom.NodeList pNodeList) {
        if (pNodeList == null) {
            log.debug("input node list is null");
        }
        int listLength = pNodeList.getLength();
        log.debug("input node list length: {}", listLength);
        for (int i = 0; i < listLength; ++i) {
            org.w3c.dom.Node node = pNodeList.item(i);
            log.debug(
                    "\r\nnum:{}\r\nBaseURI:{}\r\nLocalName:{}\r\nNamespaceURI:{}\r\nNodeName:{}\r\nNodeType:{}\r\n"
                            + "NodeValue:{}\r\nPrefix:{}\r\nTextContent:{}", 
                    i, node.getBaseURI(), node.getLocalName(), node.getNamespaceURI(), node.getNodeName(),
                    node.getNodeType(), node.getNodeValue(), node.getPrefix(), node.getTextContent());
        }
    }
    
    
    //==============================================================================================
    
    static public String resolveXmlEncoding(org.w3c.dom.Node pNode) {
        String rv = getXmlEncoding(pNode);
        return rv == null ? S_DEFAULT_ENCODING : rv;
    }
    
    static public String getXmlEncoding(org.w3c.dom.Node pNode) {
        String rv = null;
        org.w3c.dom.Document doc =
                pNode instanceof org.w3c.dom.Document ?
                        (org.w3c.dom.Document)pNode : pNode.getOwnerDocument();
        if (doc != null) {
            rv = doc.getXmlEncoding();
        }
        return rv;
    }

    final static private String
            S_ENC = "encoding",
                        S_VERSION = "version";

    static public String constructXmlDecl(String pVersion, String pEncoding) {
        String rv = null, ver = "", enc = "";
        if (pVersion != null && !pVersion.isEmpty()) {
            ver = (new StringBuilder())
                    .append(" ").append(S_VERSION).append("=\"")
                    .append(pVersion).append("\"").toString();
        }
        if (pEncoding != null && !pEncoding.isEmpty()) {
            enc = (new StringBuilder())
                    .append(" ").append(S_ENC).append("=\"")
                    .append(pEncoding).append("\"").toString();
        }
        if (!ver.isEmpty() || !enc.isEmpty()) {
            rv = (new StringBuilder()).append("<?xml")
                    .append(ver).append(enc).append("?>").toString();
        }
        return rv;
    }
    
    static public String getXmlStrWithoutXmlDecl(String pXmlStr) {
        String xmlDecl = getXmlDecl(pXmlStr);
        return (xmlDecl == null ? pXmlStr : pXmlStr.substring(xmlDecl.length()));
    }
    
    static public boolean hasXmlDecl(String pDocStr) {
        return (getXmlDecl(pDocStr) != null);
    }
    
    static public String getXmlDecl(String pDocStr) {
        String rv = null;
        String stringPattern = "<\\?xml[^(\\?>)]*\\?>";
        Pattern pattern = Pattern.compile(stringPattern);
        Matcher matcher = pattern.matcher(pDocStr);
        if (matcher.find()) {
            rv = matcher.group(0);
        }
        return rv;
    }

    static public String resolveEncodingFromXmlDecl(String pXmlDecl) {
        return resolveValueFromXmlDecl(pXmlDecl, S_ENC);
    }

    static public String resolveVersionFromXmlDecl(String pXmlDecl) {
        return resolveValueFromXmlDecl(pXmlDecl, S_VERSION);
    }

    static private String resolveValueFromXmlDecl(String pXmlDecl, String pWhat) {
        String rv = null;
        String stringPattern = pWhat + "[\\s]*=[\\s]*[\"']([^\"']*)[\"']";
        Pattern pattern = Pattern.compile(stringPattern);
        Matcher matcher = pattern.matcher(pXmlDecl);
        if (matcher.find()) {
            rv = matcher.group(1);
        }
        return rv;
    }
    
}
