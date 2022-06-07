package io.github.lincyen.sample.common;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <pre>
 *     공통 유틸리티
 * </pre>
 */
public class CommonUtils {

    /**
     * <pre>
     *     yyyyMMddHHmmss 형식으로 현재 날짜를 가져옵니다.
     * </pre>
     * @return yyyyMMddHHmmss 형식의 {@link String}
     */
    public static synchronized String getyyyyMMddHHmmss() {
        /* yyyyMMddHHmmss Date Format */
        SimpleDateFormat yyyyMMddHHmmss = new SimpleDateFormat("yyyyMMddHHmmss");

        return yyyyMMddHHmmss.format(new Date());
    }

    /**
     *<pre>
     *     yyyyMMddHHmmss 형식으로 현재 날짜를 가져옵니다. / 형식(regExp)을 이용하여 변경합니다.
     *</pre>
     * @param regExp {@link String 변환할 날짜 형식(simpleDateFormat 형식)}
     * @return yyyyMMddHHmmss 형식의 {@link String}
     */
    public static synchronized String getyyyyMMddHHmmss(String regExp) {
        /* yyyyMMddHHmmss Date Format */
        SimpleDateFormat yyyyMMddHHmmss = new SimpleDateFormat(regExp);

        return yyyyMMddHHmmss.format(new Date());
    }

    /**
     * <pre>
     *     yyyyMMdd 형식으로 입력된 날짜(yyyyMMdd) / 형식(regExp)을 이용하여 변경합니다.
     * </pre>
     * @param yyyyMMdd {@link String yyyyMMdd 형식의 날짜}
     * @param regExp {@link String regExp 변환할 날짜 형식(simpleDateFormat 형식)}
     * @return yyyyMMdd 형식의 {@link String}
     */
    public static synchronized String getyyyyMMdd(String yyyyMMdd, String regExp) {

        SimpleDateFormat oriFormat = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat dispformat = new SimpleDateFormat(regExp);
        String dispDate = null;

        Date date;

        try {
            if (yyyyMMdd.length() != 0) {

                date = oriFormat.parse(yyyyMMdd);

                dispDate = dispformat.format(date);

            } else {
                dispDate = "";
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return dispDate;
    }

    /**
     * <pre>
     *     해당 String 에 숫자 존재 여부 체크
     * </pre>
     * @param str {@link String 체크할 문자}
     * @return {@link Boolean 검증여부}
     */
    public static boolean validateNumber(String str) {
        return Pattern.matches("^[0-9]*$", str);
    }

    /**
     * <pre>
     *     이메일 주소의 유효성을 체크합니다.
     *     rfc5322 패턴 규정 준수하지 않습니다.(특수문자 제외함)
     * </pre>
     * @param email {@link String 이메일 주소}
     * @return {@link Boolean 검증여부}
     */
    public static boolean validateEmail(String email) {
        return validateEmail(email, false);
    }

    /**
     * <pre>
     *     이메일 주소의 유효성을 체크합니다.
     * </pre>
     * @param email {@link String 이메일 주소}
     * @param isRFC5322 {@link Boolean rfc5322 표준패턴 사용여부, 데이터베이스 입력시 SQL exception 발생 가능}
     * @return {@link Boolean 검증여부}
     */
    public static boolean validateEmail(String email, boolean isRFC5322) {

        boolean flag = false;

        String regex;

        if (isRFC5322) {
            regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        } else {
            regex = "^[_a-zA-Z0-9-]+(.[_a-zA-Z0-9-]+)*@(?:\\w+\\.)+\\w+$";
        }

        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(email);

        if (matcher.matches()) {
            flag = true;
        }

        return flag;
    }

    /**
     * <pre>
     *     Url 주소의 유효성을 체크합니다.
     * </pre>
     * @param url {@link String url 주소}
     * @return {@link Boolean 검증여부}
     */
    public static boolean validateUrl(String url) {

        boolean flag = false;

        String regex = "^(https?)://([^:/\\s]+)(:([^/]*))?((/[^\\s/]+)*)?/?([^#\\s?]*)(\\?([^#\\s]*))?(#(\\w*))?$";

        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(url);

        if (matcher.matches()) {
            flag = true;
        }

        return flag;
    }

    /**
     * <pre>
     *     IPv4 형식의 문자열 검증
     * </pre>
     * @param ipV4Addr {@link String ipV4 형식의 문자열}
     * @return {@link Boolean 검증여부}
     */
    public static boolean validIPv4Addr(String ipV4Addr) {
        boolean flag = false;

        String regex = "\\A(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)(\\.(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)){3}\\z";

        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(ipV4Addr);

        if (matcher.matches()) {
            flag = true;
        }

        return flag;
    }

    /**
     * <pre>
     *     IPv6 형식의 문자열 검증
     * </pre>
     * @param ipV6Addr {@link String ipV6 형식의 문자열}
     * @return {@link Boolean 검증여부}
     */
    public static boolean validIPv6Addr(String ipV6Addr) {
        boolean flag = false;

        String regex = "\\A(?:[0-9a-fA-F]{1,4}:){7}[0-9a-fA-F]{1,4}\\z";

        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(ipV6Addr);

        if (matcher.matches()) {
            flag = true;
        }

        return flag;
    }

    /**
     * <pre>
     *     해당 String 이 양의 숫자 형식인지 체크
     * </pre>
     * @param str {@link String 양(+)의 숫자 형식의 문자열}
     * @return {@link Boolean 검증여부}
     */
    public static boolean isNumeric(String str) {
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

}