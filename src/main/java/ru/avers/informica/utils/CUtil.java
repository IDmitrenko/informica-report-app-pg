package ru.avers.informica.utils;

import com.google.common.base.CharMatcher;
import com.google.common.io.BaseEncoding;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.text.ParseException;
import java.util.*;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.Inflater;
import java.util.zip.InflaterOutputStream;

/**
 *
 * @author Dias
 */
@Slf4j
public class CUtil {

    public static boolean isStringNullOrEmpty(String pVal) {
        return pVal == null || pVal.isEmpty();
    }
    
    public static boolean isStringNullOrBlank(String pVal) {
        return pVal == null || pVal.trim().isEmpty();
    }    

    public static String concatCollection(Collection pCol, String pSeparator) {
        if (pCol == null) {
            return "null";
        }
        StringBuilder builder = new StringBuilder();
        String separator = "";
        for (Object item : pCol) {
            builder.append(separator).append(item);
            separator = pSeparator;
        }
        return builder.toString();
    }

    public static <A, B> Map<B, A> revertMap(Map<A, B> pSrc) {
        if(pSrc == null) {
            return null;
        }
        Map<B, A> rv = new HashMap<B, A>();
        for(Map.Entry<A, B> abEntry: pSrc.entrySet()) {
            rv.put(abEntry.getValue(), abEntry.getKey());
        }
        return rv;
    }

    public static List<String> getListOfTimeZonesIds() {
        List<String> listIds = new ArrayList<String>(Arrays.asList(TimeZone.getAvailableIDs()));
        if (!listIds.contains(TimeZone.getDefault().getID())) {
            listIds.add(TimeZone.getDefault().getID());
        }
        return listIds;
    }
    
    //  список серверных таймзон отсортированных по id
    public static List<TimeZone> getListOfTimeZones() {
        List<TimeZone> rv = new ArrayList<TimeZone>();
        
        for (String tzId: getListOfTimeZonesIds()) {
            rv.add(TimeZone.getTimeZone(tzId));
        }

        //  sort by id
        Collections.sort(rv, new Comparator<TimeZone>() {
            public int compare(TimeZone pTz1, TimeZone pTz2) {
                return pTz1.getID().compareTo(pTz2.getID());
            }
        });

//        log.debug("x_rv = {}", x_rv);
        
        return rv;
    }
    
    public static byte[] hexToBytes(String pStr) throws ParseException {
        if(pStr == null) {
            return null;
        }
        if(pStr.length() % 2 != 0) {
            throw new ParseException("Wrong length of input string (is not even)", 0);
        }

        Pattern pattern = Pattern.compile("[^0-9A-F]+");
        Matcher matcher = pattern.matcher(pStr);
        if(matcher.find()) {
            MatchResult mr = matcher.toMatchResult();
            throw new ParseException("Wrong symbol in input string", mr.start());
        }
        else {
            int len = pStr.length() / 2;
            byte[] buffer = new byte[len];
            for(int i = 0; i < len; ++i) {
                buffer[i] = (byte) Integer.parseInt(pStr.substring(i * 2, i * 2 + 2), 16);
            }
            return buffer;
        }
    }

    static final protected char[] S_HEX_ARRAY = "0123456789ABCDEF".toCharArray();
    static public String bytesToHex(byte[] pBytes) {
        char[] hexChars = new char[pBytes.length * 2];
        for(int j = 0; j < pBytes.length; j++ ) {
            int v = pBytes[j] & 0xFF, ind = j * 2;
            hexChars[ind] = S_HEX_ARRAY[v >>> 4];
            hexChars[ind + 1] = S_HEX_ARRAY[v & 0x0F];
        }
        return new String(hexChars);
    }    
    
    public static int getOffset(int pOffset, int pTotal, int pPosSelected, int pPageSize) {
        if (pPosSelected > -1) {
            pOffset = pPosSelected - pPosSelected % pPageSize;
        } else if (pOffset >= pTotal) {
            pOffset = pTotal - pTotal % pPageSize;
        }
        return pOffset;
    }
    
    public static Properties loadProperties(String pFilename)
            throws FileNotFoundException, IOException {
        return loadProperties(new FileInputStream(pFilename));
    }
    public static Properties loadProperties(InputStream pIs) throws IOException {
        Properties rv = new Properties();
        rv.load(pIs);
        return rv;
    }
    
    static public InputStream createInputStreamFromString(String pStr) {
        return new ByteArrayInputStream(pStr.getBytes());
    }
    static public InputStream createInputStreamFromString(String pStr, String pCharsetName)
            throws UnsupportedEncodingException {
        return new ByteArrayInputStream(
                isStringNullOrEmpty(pCharsetName) ? pStr.getBytes() :
                        pStr.getBytes(pCharsetName));
    }
    
    public static String loadFileToStr(String pFn) throws FileNotFoundException, IOException {
        return loadFileToStr(pFn, null);
    }
    public static String loadFileToStr(String pFn, String pCharsetName)
            throws FileNotFoundException, IOException {
        FileInputStream fis = new FileInputStream(pFn);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int c;
        while((c = fis.read()) != -1) {
            baos.write(c);
        }
        String rv = (isStringNullOrEmpty(pCharsetName) ?
                                         baos.toString() :
                baos.toString(pCharsetName));
        fis.close();
        baos.close();
        return rv;
    }
    static public byte[] loadFile(String pFn) throws FileNotFoundException, IOException {
        FileInputStream fis = new FileInputStream(pFn);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            int c;
            while((c = fis.read()) != -1) {
                baos.write(c);
            }
            return baos.toByteArray();
        } finally {
            fis.close();
            baos.close();
        }
    }
    
    public static void writeStrToFile(String pFn, String pData)
            throws FileNotFoundException, IOException {
        writeStrToFile(pFn, pData, null);
    }
    public static void writeStrToFile(String pFn, String pData, String pEncoding)
            throws FileNotFoundException, IOException {
        FileOutputStream fos = new FileOutputStream(pFn);
        fos.write(pEncoding == null ? pData.getBytes() : pData.getBytes(pEncoding));
        fos.close();
    }
    
    /**
     * Поиск в списке мапов первого мапа с указанным значением указанного ключа
     * @param pList список мапов
     * @param pKey ключ мапа
     * @param pKeyValue значение ключа мапа
     * @return 
     */
    public static Map<String, Object> findMapInList(List<Map<String, Object>> pList,
                                                    String pKey, Object pKeyValue) {
        for (Map<String, Object> item : pList) {
            if (item.containsKey(pKey) && equals(item.get(pKey), pKeyValue)) {
                return item;
            }
        }
        return null;
    }
    
    public static List<Map<String, Object>> findMapsInList(List<Map<String,Object>> pList,
                                                           String pKey, Object pKeyValue) {
        List<Map<String, Object>> res = new ArrayList<Map<String, Object>>();
        for (Map<String, Object> item : pList) {
            if (item.containsKey(pKey) && equals(item.get(pKey), pKeyValue)) {
                res.add(item);
            }
        }
        return res.isEmpty() ? null : res;
    }    

    public static String generateUnique(Set<String> pSet,
                                        Integer pMaxLen,
                                        String pAlphabet) {
        String rv = null;
        if (pMaxLen == null || pAlphabet == null || pAlphabet.isEmpty()) {
            //  TODO
        } else {
            Set<Integer> set = new HashSet<Integer>();
            for (String str: pSet) {
                set.add(toInt(str, pAlphabet));
            }
            List<Integer> list = new ArrayList<Integer>(set);
            Collections.sort(list);
            int listSize = list.size();
            Integer val = list.get(listSize - 1) + 1;
            String str = toStr(val, pAlphabet);
            if (str.length() <= pMaxLen) {
                rv = str;
            } else {
                for (int i = 0; i < listSize - 1; ++i) {
                    Integer elem = list.get(i), nextElem = list.get(i + 1);
                    if (nextElem - elem > 1) {
                        str = toStr(elem + 1, pAlphabet);
                        if (str.length() <= pMaxLen) {
                            rv = str;
                        }
                        break;
                    }
                }
            }
        }
        return rv;
    }
    
    private static int toInt(String pVal, String pAlphabet) {
        if (pAlphabet == null || pAlphabet.isEmpty()) {
            throw new IllegalArgumentException("Alphabet is empty");
        }
        
        if (pVal == null || pVal.isEmpty()) {
            return 0;
        }
        
        int basis = pAlphabet.length(), valLen = pVal.length(), rv = 0;
        for (int degree = 0; degree < valLen; ++degree) {
            char ch = pVal.charAt(valLen - 1 - degree);
            int pos = pAlphabet.indexOf(ch);
            rv += pos * (int) Math.pow(basis, degree);
        }
        return rv;
    }

    private static String toStr(int pVal, String pAlphabet) {
        if (pAlphabet == null || pAlphabet.isEmpty()) {
            throw new IllegalArgumentException("Alphabet is empty");
        }
        String rv = "";
        int basis = pAlphabet.length();
        
        do {
            int ind = pVal % basis;
            pVal /= basis;
            rv = pAlphabet.charAt(ind) + rv;
        } while(pVal > 0);
        
        return rv;
    }
    
    /**
     * Вернуть первый найденный ключ мапа по значению мапа
     * @param <K>
     * @param <V>
     * @param pMap
     * @param pValue
     * @return 
     */
    public static <K,V> K getMapFirstKeyByValue(Map<K,V> pMap, V pValue)
    {
        for (Map.Entry<K,V> entrySet : pMap.entrySet()) {
            if (equals(entrySet.getValue(), pValue)) {
                return entrySet.getKey();
            }
        }
        return null;
    }    
    
    static public <K,V> boolean putToMap(Map<K, List<V>> pSrc, K pK, V pV) {
        List<V> vList = pSrc.get(pK);
        if (vList == null) {
            vList = new ArrayList<V>();
            pSrc.put(pK, vList);
        }
        return vList.add(pV);
    }
    static public <K,V> boolean putAllToMap(Map<K, List<V>> pSrc, K pK,
                                            Collection<V> pVCollection) {
        if (pVCollection == null) {
            return false;
        }
        List<V> vList = pSrc.get(pK);
        if (vList == null) {
            vList = new ArrayList<V>();
            pSrc.put(pK, vList);
        }
        return vList.addAll(pVCollection);
    }
    /**
     * Равенство двух объектов (null safe)
     */
    public static boolean equals(Object pLeft, Object pRight) {
        if (pLeft == pRight) {
            return true;
        }
        if (pLeft == null || pRight == null) {
            return false;
        }
        return pLeft.equals(pRight);
    }
    
    public static <T> List<List<T>> partitionList(List<T> pList, int pPartitionsCount) {
        int listSize = pList.size();
        int elemsInPartition = listSize / pPartitionsCount +
                (listSize % pPartitionsCount == 0 ? 0 : 1);
        List<List<T>> res = new ArrayList<List<T>>();
        for (int i = 0; i < listSize; i += elemsInPartition) {
            res.add(new ArrayList<T>(pList.subList(i, Math.min(listSize, i + elemsInPartition))));
        }
        return res;
    }    
    
    static public byte[] deflate(byte[] pIn, int pMethod) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            DeflaterOutputStream dos = new DeflaterOutputStream(out,
                    new Deflater(pMethod, true));
            try {
                dos.write(pIn);
            } finally {
                dos.close();
            }
            return out.toByteArray();
        } finally {
            out.close();
        }
    }

    static public byte[] inflate(byte[] pIn) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            InflaterOutputStream ios = new InflaterOutputStream(out, new Inflater(true));
            try {
                ios.write(pIn);
            }
            finally {
                ios.close();
            }
            return out.toByteArray();
        }
        finally {
            out.close();
        }
    }
    //
    //  guava encoding
    //
    public static String encode(String pText, BaseEncoding pEnc) {
        log.debug("{}", pText);
        String rv;
        try {
            rv = pEnc.encode(pText.getBytes(Const.S_ENCODING_UTF8));
        } catch(UnsupportedEncodingException ex) {
            log.error("encode", ex);
            rv = null;
        }
        log.debug("retval = {}", rv);
        return rv;
    }
    
    public static String decode(String pText, BaseEncoding pEnc){
        log.debug("{}", pText);
        String rv;
        try {
            rv = new String(pEnc.decode(pText), Const.S_ENCODING_UTF8);
        } catch(Exception ex) {
            log.error("decode", ex);
            rv = null;
        }
        log.debug("retval = {}", rv);
        return rv;
    }
    
    final static private BaseEncoding S_BASE_64 = BaseEncoding.base64();
    public static String base64encode(String pText) {
        return encode(pText, S_BASE_64);
    }
    public static String base64decode(String pText) {
        return decode(pText, S_BASE_64);
    }

    private static final String S_LINE_SEPARATOR = System.getProperty("line.separator");
    // Base64 transfer encoding for MIME (RFC 2045), Maximum encoded line length = 76
    // 76 используется в sun.misc.BASE64Encoder
    private static final int S_CHARS_PER_LINE = 76;
    static public byte[] encodeToBase64(byte[] pIn) throws IOException {
        return BaseEncoding.base64().withSeparator(S_LINE_SEPARATOR, S_CHARS_PER_LINE)
                .encode(pIn).getBytes();
    }
    static public byte[] decodeFromBase64(byte[] pIn) throws IOException {
        // Удалить из p_in все WHITESPACE символы (http://docs.guava-libraries.googlecode.com/git/javadoc/com/google/common/base/CharMatcher.html#WHITESPACE)
        // в том числе переносы строки (\n и \r\n)
        String stringToDecode = CharMatcher.WHITESPACE.removeFrom(new String(pIn));
        return BaseEncoding.base64().decode(stringToDecode);
    }

}
