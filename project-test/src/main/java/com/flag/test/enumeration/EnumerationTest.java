package com.flag.test.enumeration;

import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.List;

/**
 * @Authuor Administrator
 * @Create 2016-11-01-13:11
 */
public class EnumerationTest {

    public static void main(String[] args) {
        printNetworkInfo();
    }

    private static void printNetworkInfo(){
        Enumeration<NetworkInterface> nis = null;
        try {
            nis = NetworkInterface.getNetworkInterfaces();
            while(nis.hasMoreElements()){
                NetworkInterface anInterface = nis.nextElement();
                List<InterfaceAddress> addressList = anInterface.getInterfaceAddresses();
                for (InterfaceAddress a : addressList){
                    System.out.println(a != null ? a.getAddress().toString() : null);
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }
}
