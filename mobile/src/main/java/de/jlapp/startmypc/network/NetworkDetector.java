package de.jlapp.startmypc.network;

import android.content.Context;
import android.net.DhcpInfo;
import android.net.wifi.WifiManager;
import android.os.Build;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.List;

public class NetworkDetector {

    private NetworkDetector() {
        // Singleton
    }

//    public static String getBroadcastIp() {
//        String found_bcast_address = null;
//        System.setProperty("java.net.preferIPv4Stack", "true");
//        try {
//            Enumeration<NetworkInterface> niEnum = NetworkInterface.getNetworkInterfaces();
//            while (niEnum.hasMoreElements()) {
//                NetworkInterface ni = niEnum.nextElement();
//                if (!ni.isLoopback()) {
//                    for (InterfaceAddress interfaceAddress : ni.getInterfaceAddresses()) {
//
//                        found_bcast_address = interfaceAddress.getBroadcast().toString();
//                        found_bcast_address = found_bcast_address.substring(1);
//
//                    }
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return found_bcast_address;
//    }

//    public static String getBroadcastAddress(Context context) {
//        try {
//            WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
//            DhcpInfo dhcp = wifi.getDhcpInfo();
//            // handle null somehow
//
//            int broadcast = (dhcp.ipAddress & dhcp.netmask) | ~dhcp.netmask;
//            byte[] quads = new byte[4];
//            for (int k = 0; k < 4; k++)
//                quads[k] = (byte) (broadcast >> (k * 8));
//
//            return InetAddress.getByAddress(quads).toString();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return null;
//    }

    // TODO Find a proper method to get the broadcast ip
    public static String getNetworkLocalBroadcastAddressdAsInetAddress() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                if (!intf.isLoopback()) {
                    List<InterfaceAddress> intfaddrs = intf.getInterfaceAddresses();

                    for (int i = 0; i < intfaddrs.size(); i++) {
                        if (intfaddrs.get(i).getBroadcast() == null || !intfaddrs.get(i).getBroadcast().toString().contains("255")) {
                            continue;
                        }

                        String broadcastIp = intfaddrs.get(i).getBroadcast().toString();
                        return broadcastIp.substring(1, broadcastIp.length() - 1);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

//    public static String getLocalIpAddress() {
//        try {
//            for (Enumeration<NetworkInterface> en = NetworkInterface
//                    .getNetworkInterfaces(); en.hasMoreElements();) {
//                NetworkInterface intf = en.nextElement();
//                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
//                    InetAddress inetAddress = enumIpAddr.nextElement();
//                    if (!inetAddress.isLoopbackAddress()) {
//                        return inetAddress.getHostAddress().toString();
//                    }
//                }
//            }
//        } catch (SocketException ex) {}
//        return null;
//    }
//
//    public static String getBroadcast() throws SocketException {
//        System.setProperty("java.net.preferIPv4Stack", "true");
//        for (Enumeration<NetworkInterface> niEnum = NetworkInterface.getNetworkInterfaces(); niEnum.hasMoreElements();) {
//            NetworkInterface ni = niEnum.nextElement();
//            if (!ni.isLoopback()) {
//                for (InterfaceAddress interfaceAddress : ni.getInterfaceAddresses()) {
//                    return interfaceAddress.getBroadcast().toString().substring(1);
//                }
//            }
//        }
//        return null;
//    }
}
