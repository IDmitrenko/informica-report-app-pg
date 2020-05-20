package ru.avers.informica.utils;

import com.google.common.base.CharMatcher;
import com.google.common.io.BaseEncoding;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
public class CUtil {
    private static final Logger s_logger = LoggerFactory.getLogger(CUtil.class);
    
    public static boolean isStringNullOrEmpty(String p_val) {
        return p_val == null || p_val.isEmpty();
    }
    
    public static boolean isStringNullOrBlank(String p_val) {
        return p_val == null || p_val.trim().isEmpty();
    }    

    public static String concatCollection(Collection p_col, String p_separator) {
        if (p_col == null) {
            return "null";
        }
        StringBuilder x_builder = new StringBuilder();
        String x_separator = "";
        for (Object x_item : p_col) {
            x_builder.append(x_separator).append(x_item);
            x_separator = p_separator;
        }
        return x_builder.toString();
    }

    public static <A, B> Map<B, A> revertMap(Map<A, B> p_src) {
        if(p_src == null) {
            return null;
        }
        Map<B, A> x_rv = new HashMap<B, A>();
        for(Map.Entry<A, B> x_entry: p_src.entrySet()) {
            x_rv.put(x_entry.getValue(), x_entry.getKey());
        }
        return x_rv;
    }

    public static List<String> getListOfTimeZonesIds() {
        List<String> x_ids = new ArrayList<String>(Arrays.asList(TimeZone.getAvailableIDs()));
        if (!x_ids.contains(TimeZone.getDefault().getID())) {
            x_ids.add(TimeZone.getDefault().getID());
        }
        return x_ids;                
    }
    
    //  список серверных таймзон отсортированных по id
    public static List<TimeZone> getListOfTimeZones() {
        List<TimeZone> x_rv = new ArrayList<TimeZone>();
        
        for (String x_tz_id: getListOfTimeZonesIds()) {
            x_rv.add(TimeZone.getTimeZone(x_tz_id));
        }

        //  sort by id
        Collections.sort(x_rv, new Comparator<TimeZone>() {
            public int compare(TimeZone p_tz1, TimeZone p_tz2) {
                return p_tz1.getID().compareTo(p_tz2.getID());
            }
        });

//        s_logger.debug("x_rv = {}", x_rv);
        
        return x_rv;
    }
    
    public static byte[] hexToBytes(String p_str) throws ParseException {
        if(p_str == null) {
            return null;
        }
        if(p_str.length() % 2 != 0) {
            throw new ParseException("Wrong length of input string (is not even)", 0);
        }

        Pattern x_pattern = Pattern.compile("[^0-9A-F]+");
        Matcher x_matcher = x_pattern.matcher(p_str);
        if(x_matcher.find()) {
            MatchResult x_mr = x_matcher.toMatchResult();
            throw new ParseException("Wrong symbol in input string", x_mr.start());
        }
        else {
            int x_len = p_str.length() / 2;
            byte[] x_buffer = new byte[x_len];
            for(int i = 0; i < x_len; ++i) {
                x_buffer[i] = (byte) Integer.parseInt(p_str.substring(i * 2, i * 2 + 2), 16);
            }
            return x_buffer;
        }
    }

    static final protected char[] s_hex_array = "0123456789ABCDEF".toCharArray();
    static public String bytesToHex(byte[] p_bytes) {
        char[] x_hex_chars = new char[p_bytes.length * 2];
        for(int j = 0; j < p_bytes.length; j++ ) {
            int x_v = p_bytes[j] & 0xFF, x_ind = j * 2;
            x_hex_chars[x_ind] = s_hex_array[x_v >>> 4];
            x_hex_chars[x_ind + 1] = s_hex_array[x_v & 0x0F];
        }
        return new String(x_hex_chars);
    }    
    
    public static int getOffset(int p_offset, int p_total, int p_pos_selected, int p_page_size) {
        if (p_pos_selected > -1) {
            p_offset = p_pos_selected - p_pos_selected % p_page_size;
        } else if (p_offset >= p_total) {
            p_offset = p_total - p_total % p_page_size;
        }
        return p_offset;
    }
    
    public static Properties loadProperties(String p_filename)
            throws FileNotFoundException, IOException {
        return loadProperties(new FileInputStream(p_filename));
    }
    public static Properties loadProperties(InputStream p_is) throws IOException {
        Properties x_rv = new Properties();
        x_rv.load(p_is);
        return x_rv;
    }
    
    static public InputStream createInputStreamFromString(String p_str) {
        return new ByteArrayInputStream(p_str.getBytes());
    }
    static public InputStream createInputStreamFromString(String p_str, String p_charset_name)
            throws UnsupportedEncodingException {
        return new ByteArrayInputStream(
                isStringNullOrEmpty(p_charset_name) ? p_str.getBytes() : p_str.getBytes(p_charset_name));
    }
    
    public static String loadFileToStr(String p_fn) throws FileNotFoundException, IOException {
        return loadFileToStr(p_fn, null);
    }
    public static String loadFileToStr(String p_fn, String p_charset_name) throws FileNotFoundException, IOException {
        FileInputStream x_fis = new FileInputStream(p_fn);
        ByteArrayOutputStream x_baos = new ByteArrayOutputStream();
        int x_c;
        while((x_c = x_fis.read()) != -1) x_baos.write(x_c);
        String x_rv = (isStringNullOrEmpty(p_charset_name) ?
                                         x_baos.toString() :
                x_baos.toString(p_charset_name));
        x_fis.close();
        x_baos.close();
        return x_rv;
    }
    static public byte[] loadFile(String p_fn) throws FileNotFoundException, IOException {
        FileInputStream x_fis = new FileInputStream(p_fn);
        ByteArrayOutputStream x_baos = new ByteArrayOutputStream();
        try {
            int x_c;
            while((x_c = x_fis.read()) != -1) {
                x_baos.write(x_c);
            }
            return x_baos.toByteArray();
        } finally {
            x_fis.close();
            x_baos.close();
        }
    }
    
    public static void writeStrToFile(String p_fn, String p_data) throws FileNotFoundException, IOException {
        writeStrToFile(p_fn, p_data, null);
    }
    public static void writeStrToFile(String p_fn, String p_data, String p_encoding)
            throws FileNotFoundException, IOException {
        FileOutputStream x_fos = new FileOutputStream(p_fn);
        x_fos.write(p_encoding == null ? p_data.getBytes() : p_data.getBytes(p_encoding));
        x_fos.close();
    }
    
    /**
     * Поиск в списке мапов первого мапа с указанным значением указанного ключа
     * @param p_list список мапов
     * @param p_key ключ мапа
     * @param p_key_value значение ключа мапа
     * @return 
     */
    public static Map<String, Object> findMapInList(List<Map<String, Object>> p_list,
                                                    String p_key, Object p_key_value) {
        for (Map<String, Object> x_item : p_list) {
            if (x_item.containsKey(p_key) && equals(x_item.get(p_key), p_key_value)) {
                return x_item;
            }
        }
        return null;
    }
    
    public static List<Map<String, Object>> findMapsInList(List<Map<String,
            Object>> p_list, String p_key, Object p_key_value) {
        List<Map<String, Object>> x_res = new ArrayList<Map<String, Object>>();
        for (Map<String, Object> x_item : p_list) {
            if (x_item.containsKey(p_key) && equals(x_item.get(p_key), p_key_value)) {
                x_res.add(x_item);
            }
        }
        return x_res.isEmpty() ? null : x_res;
    }    

    public static String generateUnique(Set<String> p_set, Integer p_max_len, String p_alphabet) {
        String x_rv = null;
        if (p_max_len == null || p_alphabet == null || p_alphabet.isEmpty()) {
            //  TODO
        } else {
            Set<Integer> x_set = new HashSet<Integer>();
            for (String x_str: p_set) {
                x_set.add(toInt(x_str, p_alphabet));
            }
            List<Integer> x_lst = new ArrayList<Integer>(x_set);
            Collections.sort(x_lst);
            int x_lst_size = x_lst.size();
            Integer x_val = x_lst.get(x_lst_size - 1) + 1;
            String x_str = toStr(x_val, p_alphabet);
            if (x_str.length() <= p_max_len) {
                x_rv = x_str;
            } else {
                for (int i = 0; i < x_lst_size - 1; ++i) {
                    Integer x_elem = x_lst.get(i), x_next_elem = x_lst.get(i + 1);
                    if (x_next_elem - x_elem > 1) {
                        x_str = toStr(x_elem + 1, p_alphabet);
                        if (x_str.length() <= p_max_len) {
                            x_rv = x_str;
                        }
                        break;
                    }
                }
            }
        }
        return x_rv;
    }
    
    private static int toInt(String p_val, String p_alphabet) {
        if (p_alphabet == null || p_alphabet.isEmpty()) {
            throw new IllegalArgumentException("Alphabet is empty");
        }
        
        if (p_val == null || p_val.isEmpty()) {
            return 0;
        }
        
        int x_basis = p_alphabet.length(), x_val_len = p_val.length(), x_rv = 0;
        for (int x_degree = 0; x_degree < x_val_len; ++x_degree) {
            char x_ch = p_val.charAt(x_val_len - 1 - x_degree);
            int x_pos = p_alphabet.indexOf(x_ch);
            x_rv += x_pos * (int) Math.pow(x_basis, x_degree);
        }
        return x_rv;
    }

    private static String toStr(int p_val, String p_alphabet) {
        if (p_alphabet == null || p_alphabet.isEmpty()) {
            throw new IllegalArgumentException("Alphabet is empty");
        }
        String x_rv = "";
        int x_basis = p_alphabet.length();
        
        do {
            int x_ind = p_val % x_basis;
            p_val /= x_basis;
            x_rv = p_alphabet.charAt(x_ind) + x_rv;
        } while(p_val > 0);
        
        return x_rv;
    }
    
    /**
     * Вернуть первый найденный ключ мапа по значению мапа
     * @param <K>
     * @param <V>
     * @param p_map
     * @param p_value
     * @return 
     */
    public static <K,V> K getMapFirstKeyByValue(Map<K,V> p_map, V p_value)
    {
        for (Map.Entry<K,V> x_entry_set : p_map.entrySet()) {
            if (equals(x_entry_set.getValue(), p_value)) {
                return x_entry_set.getKey();
            }
        }
        return null;
    }    
    
    static public <K,V> boolean putToMap(Map<K, List<V>> p_src, K p_k, V p_v) {
        List<V> x_lst = p_src.get(p_k);
        if (x_lst == null) {
            x_lst = new ArrayList<V>();
            p_src.put(p_k, x_lst);
        }
        return x_lst.add(p_v);
    }
    static public <K,V> boolean putAllToMap(Map<K, List<V>> p_src, K p_k, Collection<V> p_v_collection) {
        if (p_v_collection == null) {
            return false;
        }
        List<V> x_lst = p_src.get(p_k);
        if (x_lst == null) {
            x_lst = new ArrayList<V>();
            p_src.put(p_k, x_lst);
        }
        return x_lst.addAll(p_v_collection);
    }
    /**
     * Равенство двух объектов (null safe)
     */
    public static boolean equals(Object p_left, Object p_right) {
        if (p_left == p_right) {
            return true;
        }
        if (p_left == null || p_right == null) {
            return false;
        }
        return p_left.equals(p_right);
    }
    
    public static <T> List<List<T>> partitionList(List<T> p_list, int p_partitions_count) {
        int x_list_size = p_list.size();
        int x_elems_in_partition = x_list_size / p_partitions_count +
                (x_list_size % p_partitions_count == 0 ? 0 : 1);
        List<List<T>> x_res = new ArrayList<List<T>>();
        for (int i = 0; i < x_list_size; i += x_elems_in_partition) {
            x_res.add(new ArrayList<T>(p_list.subList(i, Math.min(x_list_size, i + x_elems_in_partition))));
        }
        return x_res;
    }    
    
    static public byte[] deflate(byte[] p_in, int p_method) throws IOException {
        ByteArrayOutputStream x_out = new ByteArrayOutputStream();
        try {
            DeflaterOutputStream x_dos = new DeflaterOutputStream(x_out, new Deflater(p_method, true));
            try {
                x_dos.write(p_in);
            } finally {
                x_dos.close();
            }
            return x_out.toByteArray();
        } finally {
            x_out.close();
        }
    }

    static public byte[] inflate(byte[] p_in) throws IOException {
        ByteArrayOutputStream x_out = new ByteArrayOutputStream();
        try {
            InflaterOutputStream x_ios = new InflaterOutputStream(x_out, new Inflater(true));
            try {
                x_ios.write(p_in);
            }
            finally {
                x_ios.close();
            }
            return x_out.toByteArray();
        }
        finally {
            x_out.close();
        }
    }
    //
    //  guava encoding
    //
    public static String encode(String p_text, BaseEncoding p_enc) {
        s_logger.debug("{}", p_text);
        String x_rv;
        try {
            x_rv = p_enc.encode(p_text.getBytes(Const.S_ENCODING_UTF8));
        } catch(UnsupportedEncodingException p_ex) {
            s_logger.error("encode", p_ex);
            x_rv = null;
        }
        s_logger.debug("retval = {}", x_rv);
        return x_rv;
    }
    
    public static String decode(String p_text, BaseEncoding p_enc){
        s_logger.debug("{}", p_text);
        String x_rv;
        try {
            x_rv = new String(p_enc.decode(p_text), Const.S_ENCODING_UTF8);
        } catch(Exception p_ex) {
            s_logger.error("decode", p_ex);
            x_rv = null;
        }
        s_logger.debug("retval = {}", x_rv);
        return x_rv;
    }
    
    final static private BaseEncoding s_base64 = BaseEncoding.base64();
    public static String base64encode(String p_text) { return encode(p_text, s_base64); }
    public static String base64decode(String p_text) { return decode(p_text, s_base64); }
    //
    
    private static final String s_line_separator = System.getProperty("line.separator");
    // Base64 transfer encoding for MIME (RFC 2045), Maximum encoded line length = 76
    // 76 используется в sun.misc.BASE64Encoder
    private static final int s_chars_per_line = 76;
    static public byte[] encodeToBase64(byte[] p_in) throws IOException {
        return BaseEncoding.base64().withSeparator(s_line_separator, s_chars_per_line).encode(p_in).getBytes();
    }
    static public byte[] decodeFromBase64(byte[] p_in) throws IOException {
        // Удалить из p_in все WHITESPACE символы (http://docs.guava-libraries.googlecode.com/git/javadoc/com/google/common/base/CharMatcher.html#WHITESPACE)
        // в том числе переносы строки (\n и \r\n)
        String x_string_to_decode = CharMatcher.WHITESPACE.removeFrom(new String(p_in));
        return BaseEncoding.base64().decode(x_string_to_decode);
    }

}
