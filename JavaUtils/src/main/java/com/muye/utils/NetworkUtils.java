package com.muye.utils;

import javax.servlet.http.HttpServletRequest;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.Objects;

/**
 * @Author: huangxy
 * @Date: 2019/7/31 11:28
 */
public class NetworkUtils {
    /**
     * 常量:未知
     */
    public static final String UNKNOWN = "unknown";
    /**
     * 逗号
     */
    public static final String INTERVAL_COMMA = ",";

    /**
     * 默认本地Ip
     */
    public static final String LOCAL_IP = "0:0:0:0:0:0:0:1";

    /**
     * 获取IP地址
     *
     * @param request
     * @return
     */
    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
            if (Objects.equals(LOCAL_IP, ip)) {
                ip = getHostIp();
            }
        }
        if (ip.contains(INTERVAL_COMMA) && ip.split(INTERVAL_COMMA).length > 1) {
            ip = ip.split(INTERVAL_COMMA)[0];
        }
        return ip;
    }

    /**
     * 获取本地Ip
     *
     * @return String
     */
    private static String getHostIp() {
        try {
            Enumeration<NetworkInterface> allNetInterfaces = NetworkInterface.getNetworkInterfaces();
            while (allNetInterfaces.hasMoreElements()) {
                NetworkInterface netInterface = allNetInterfaces.nextElement();
                Enumeration<InetAddress> addresses = netInterface.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    InetAddress ip = addresses.nextElement();
                    //loopback地址即本机地址，IPv4的loopback范围是127.0.0.0 ~ 127.255.255.255
                    if (ip instanceof Inet4Address && !ip.isLoopbackAddress() && !ip.getHostAddress().contains(":")) {
                        //System.out.println("本机的IP = " + ip.getHostAddress());
                        return ip.getHostAddress();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
