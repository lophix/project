package com.flag.project.analysis.decoder;

import com.flag.project.analysis.pojo.DataPackage;
import com.flag.project.analysis.util.ByteAnalysisUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * decode the msg
 *
 * @author xuj
 * @version 0.0.1-SNAPSHOT
 * @since 2017-03-01 11:05
 */
public class MsgDataDecoder extends ByteToMessageDecoder {

    private static final Logger log = LogManager.getLogger(MsgDataDecoder.class);

    static final int MSG_MIN_LENGTH = 25;
    static final int CAR_ID_LENGTH = 17;

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) throws Exception {
        int readable = msg.readableBytes();
        if (readable < MSG_MIN_LENGTH) {
            return;
        }

        msg.skipBytes(2);
        msg.markReaderIndex();
        DataPackage data = analysis(msg);
        if (!checkData(msg, data.getCheckCode())) {
            data.setDirtyData(true);
        }
        out.add(data);
    }

    /**
     * escape bytes , if there have 0xff 0xfc bytes
     * it will be escaped to 0x23 0x23 bytes
     *
     * @param bytes source bytes array
     */
    private void bytesEscape(byte[] bytes) {
        for (int i = 0; i < bytes.length - 1; i++) {
            if (bytes[i] == (byte) 0xff && bytes[i + 1] == (byte) 0xfc) {
                bytes[i] = 0x23;
                bytes[i + 1] = 0x23;
            }
        }
    }

    /**
     * first level analysis
     *
     * @param msg msg
     * @return DataPackage instance
     */
    private DataPackage analysis(ByteBuf msg) {
        DataPackage dataPack = new DataPackage();
        dataPack.setCmm(msg.readByte());
        dataPack.setReply(msg.readByte());
        dataPack.setCarId(analysisCarId(msg));
        dataPack.setEncryptType(msg.readByte());
        dataPack.setDataLength(analysisDataLength(msg));
        dataPack.setData(getDataBytes(msg, dataPack.getDataLength()));
        dataPack.setCheckCode(msg.readByte());
        return dataPack;
    }

    /**
     * analysis data length
     *
     * @param byteBuf msg
     * @return data length
     */
    private short analysisDataLength(ByteBuf byteBuf) {
        byte[] bytes = new byte[2];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = byteBuf.readByte();
        }
        bytesEscape(bytes);
        return ByteAnalysisUtil.bytes2Short(bytes, 0);
    }

    /**
     * analysis car identify
     *
     * @param byteBuf msg
     * @return car id string
     */
    private String analysisCarId(ByteBuf byteBuf) {
        byte[] bytes = new byte[CAR_ID_LENGTH];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = byteBuf.readByte();
        }
        bytesEscape(bytes);
        return ByteAnalysisUtil.bytes2String(bytes);
    }

    /**
     * get the data bytes
     *
     * @param byteBuf    msg
     * @param dataLength data bytes's length
     * @return data bytes
     */
    private byte[] getDataBytes(ByteBuf byteBuf, int dataLength) {
        log.info("data length is {}", dataLength);
        byte[] bytes = new byte[dataLength];
        int length = byteBuf.readableBytes();
        if (length > dataLength) {
            length = dataLength;
        }
        for (int i = 0; i < length; i++) {
            bytes[i] = byteBuf.readByte();
        }
        bytesEscape(bytes);
        return bytes;
    }

    /**
     * check the data
     *
     * @param byteBuf   msg
     * @param checkCode check code from client
     * @return true if get same check code with client
     */
    private boolean checkData(ByteBuf byteBuf, byte checkCode) {
        int checkLength = byteBuf.readerIndex() - 4;
        byteBuf.resetReaderIndex();
        byte check = byteBuf.readByte();
        for (int i = 0; i < checkLength; i++) {
            check ^= byteBuf.readByte();
        }
        byteBuf.skipBytes(1);
        return check == checkCode;
    }
}
