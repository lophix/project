package com.flag.project.analysis.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 轻包装，808传输，高字节在前
 * @author jy_zheng
 *
 */
public class Decode808Utils {

    public static final SimpleDateFormat yyMMddHHmmss = new SimpleDateFormat("yyMMddHHmmss");

    public static final SimpleDateFormat HHmmssSSSS = new SimpleDateFormat("HHmmssSSSS");


    /**
     * @功能: BCD码转为10进制串(阿拉伯数据)
     * @参数: BCD码
     * @结果: 10进制串
     */
    public static String bcd2Str(byte[] bytes,int offset,int length) {

        byte[] b = new byte[length];
        //System.out.println(bytes.length+","+length);
        System.arraycopy(bytes, offset, b, 0, length);


        StringBuffer temp = new StringBuffer(b.length * 2);
        for (int i = 0; i < b.length; i++) {
            temp.append((byte) ((b[i] & 0xf0) >>> 4));
            temp.append((byte) (b[i] & 0x0f));
        }
        String s = temp.toString();
        return s;
    }

    /**
     * 将BCD码转为日期
     * @param bytes
     * @param sdf  时间格式
     * @return
     * @throws ParseException
     */
    public static Date bcd2Date(byte[] bytes,int offset,int length,SimpleDateFormat sdf) throws ParseException{
        String s  = bcd2Str(bytes, offset, length);
        //System.out.println(s);
        return sdf.parse(s);
    }

    /**
     * 高字节在前，转成Int,固定4个字节
     * @param ary
     * @param offset
     * @return
     */
    public static int bytes2Int(byte[] ary,int offset){
        int value;
        value = (int) (((ary[offset] & 0xFF)<<24)
                |((ary[offset+1] & 0xFF)<<16)
                |((ary[offset+2] & 0xFF)<<8)
                |(ary[offset+3] & 0xFF));
        return value;
    }

    /**
     * 高字节在前，转成Int,指定长度，
     * @param ary
     * @param offset
     * @return
     */
    public static int bytes2Int(byte[] ary,int offset,int length){
        int value=0;
        for(int i=0;i<length;i++){
            value = (value| (ary[offset+i]&0xFF)<<(length-1-i)*8);
        }
        return value;
    }

    /**传入的byteLength必须是 bitLength/8+1
     * 获取ary[]中，从offset开始取，取byteLength位，再从byte[]中取biteLength位bit，转成int
     * @param bytes
     * @param byteOffset
     * @param byteLength
     * @param bitOffset
     * @param bitLength
     * @return
     */
    public static int bytes2Int(byte[] bytes,int byteOffset,int byteLength,int bitOffset,int bitLength) {
        //算法优化,如果是整字节的
        if(bitLength*8==bitLength){
            byte[] data = new  byte[4];

            switch (byteLength){

                case 1:
                    System.arraycopy(bytes,byteOffset,data,3,1);
                    break;
                case 2:
                    System.arraycopy(bytes,byteOffset,data,2,2);
                    break;
                case 4:
                    System.arraycopy(bytes,byteOffset,data,0,4);
                     break;
            }
            return bytes2Int(data,0);
        }





        String bit = bytes2BitStr(bytes, byteOffset, byteLength, bitOffset, bitLength);

        return bit2Int(bit);
    }


    /**
     * 将bit转成int
     * @param bit
     * @return
     */
    public static int bit2Int(String bit){
        //bit = String.format("%32s", bit).replace(' ','0');
        return Integer.parseUnsignedInt(bit,2);
    }

    public static int bit2Int(String bit,int offset,int length){
        String s = bit.substring(offset,offset+length);
        return bit2Int(s);
    }

    /**
     * bit转short
     * @param bit
     * @return
     */
    public static short bit2Short(String bit){
        String formatStr = String.format("%16s", bit).replace(' ','0');
        return Short.parseShort(formatStr,2);

    }

    /**
     *
     * @param bit
     * @param offset
     * @param length
     * @return
     */
    public static Short bit2Short(String bit,int offset,int length){
        String s = bit.substring(offset,offset+length);
        return bit2Short(s);
    }


    /**
     *
     * 高位在前，低位在后，固定两个字节
     * 字节数组转化为short
     * @param ary
     * @param offset
     * @return
     */
    public static short bytes2Short(byte[] ary,int offset) {
        short value;
        value = (short) (((ary[offset] & 0xFF)<<8)
                |(ary[offset+1] & 0xFF));
        return value;
    }

    /**传入的byteLength必须是 bitLength/8+1
     * 获取ary[]中，从offset开始取，取byteLength位，再从byte[]中取biteLength位bit，转成short
     * @param bytes
     * @param byteOffset
     * @param byteLength
     * @param bitOffset
     * @param bitLength
     * @return
     */
    public static short bytes2Short(byte[] bytes,int byteOffset,int byteLength,int bitOffset,int bitLength) {
        //算法优化
        if(bitLength*8==bitLength){
            byte[] data = new  byte[2];

            switch (byteLength){

                case 1:
                    System.arraycopy(bytes,byteOffset,data,3,1);
                    break;
                case 2:
                    System.arraycopy(bytes,byteOffset,data,2,2);
                    break;

            }
            return bytes2Short(data,0);
        }

        String bit = bytes2BitStr(bytes, byteOffset, byteLength, bitOffset, bitLength);
        return bit2Short(bit);
    }


    /**传入的byteLength必须是 bitLength/8+1
     * 获取bytes[]中，从byteOffset开始取，取byteLength位，再从byte[]中取biteLength位bit，转成二进制（0/1）字符串
     * @param bytes
     * @param byteOffset
     * @param byteLength
     * @param bitOffset
     * @param bitLength
     * @return
     */
    public static String bytes2BitStr(byte[] bytes , int byteOffset, int byteLength, int bitOffset, int bitLength){


        String s = bytes2BitStr(bytes, byteOffset, byteLength);
        //因为在byte字节中，bit的第一位是在右边，所以要做一个转换
        //如果刚好获取，则不需要反转下标
        if(s.length()>bitLength){
            int length = s.length();
            bitOffset = length - bitOffset - bitLength;
        }

        return s.substring(bitOffset,bitLength+bitOffset);

    }


    /**
     * 获取bytes[]中，从byteOffset开始取，取byteLength位，转成二进制（0/1）字符串
     * @param bytes
     * @param byteOffset
     * @param byteLength
     * @return
     */
    public static String bytes2BitStr(byte[] bytes , int byteOffset, int byteLength){

        StringBuffer sbf = new StringBuffer();

        for(int i = 0;i<byteLength;i++){

            sbf.append(byte2BitStr(bytes[byteOffset+i]));
            //System.out.println(byte2BitStr(bytes[byteOffset+i]));
        }

        return sbf.toString();
    }
    /**
     * 获取bytes[]中，从byteOffset开始取，取byteLength位，转成二进制（0/1）字符串,并反转
     * @param bytes
     * @param byteOffset
     * @param byteLength
     * @return
     */
    public static String bytes2BitStrReverse(byte[] bytes , int byteOffset,int byteLength){

        String s =  bytes2BitStr(bytes, byteOffset,byteLength);
        return strReverse(s);
    }


    /**
     * 字符串翻转
     * @param s
     * @return
     */
    public static String strReverse(String s){
        return new StringBuffer(s).reverse().toString();
    }



    /**
     * Byte转Bit
     */
    public static String byte2BitStr(byte b) {

        StringBuffer sbf = new StringBuffer();
        sbf.append((b >> 7) & 0x1).append((b >> 6) & 0x1).append((b >> 5) & 0x1)
                .append((b >> 4) & 0x1).append((b >> 3) & 0x1).append((b >> 2) & 0x1).append((b >> 1) & 0x1)
                .append((b >> 0) & 0x1);

        return sbf.toString();
    }

    /**
     *  最低位在数组第0位 即 byte b = 6 --> 00000110
     *  byte b[0] =0 ,byte b[1] =1 ,byte b[2] =1 ,byte b[3] =0 ,byte b[4] =0 ,byte b[5] =0 ,byte b[6] =0 ,byte b[7] =0
     * @param b
     * @return
     */
    public static byte[] byte2byte(byte b){

        byte [] bs = new byte[8];

        for(int i = 0 ;i < 8 ; i++){
            bs[i] = (byte)((b >> i)&0x1);
        }

        return bs;
    }


    public static char[] byte2BitChar(byte b){

        return byte2BitStr(b).toCharArray();
    }

    /**
     * 取byte中的第几位转成int
     * 例如：  144 --> 10010000 第一位位0，如果startIndex传3，则返回结果是0
     * @param b
     * @param startIndex  右边第一位下标为0
     * @return
     */
    public static int byte2Int(byte b,int startIndex){
        if(startIndex<0||startIndex>7){
            throw new RuntimeException("this startIndex not in (0-7)");
        }

        return (b>>startIndex)&0x01;
    }

    /**
     * 下标反转
     * 取byte中的第几位转成int
     * 例如：  144 --> 10010000 第一位位0，如果startIndex传3，则返回结果是0
     * @param b
     * @param startIndex  右边第一位下标为0
     * @return
     */
    public static int byte2IntReverse(byte b ,int startIndex){

        startIndex = 7-startIndex;
        return byte2Int(b, startIndex);
    }






    /**
     * Bit转Byte
     */
    public static byte bit2Byte(String byteStr) {

        int re = Integer.parseInt(byteStr, 2);

        return (byte) re;
    }


}
