package com.flag.test.charandint;

/**
 * convert bytes to float
 *
 * @author xuj
 * @version 0.0.0
 * @since 2017-03-06-14:44
 */
public class BytesAndFloat {
    public static void main(String[] args) {
        print();
    }

    private static void print(){
        byte[] bytes = new byte[]{(byte) 0xff, (byte) 0xff, (byte) 0xfe, (byte) 0xff};
        float f = bytes2Int(bytes, 0, 4) * 0.000001f;
        System.out.println(String.format("%6f", f));
    }

    private static long bytes2Int(byte[] ary,int offset,int length){
        long value=0;
        for(int i=0;i<length;i++){
            value |= (long)(ary[offset+i]&0xFF)<<(length-1-i)*8;
        }
        return value;
    }
}
