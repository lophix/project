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

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) throws Exception {
        int readable = msg.readableBytes();
        if (readable < 25){
            return;
        }

        msg.markReaderIndex();
        msg.skipBytes(2);

    }

    static final int CAR_ID_LENGTH = 17;

    private DataPackage analysis(ByteBuf msg){
        DataPackage dataPack = new DataPackage();
        dataPack.setCmm(msg.readByte());
        dataPack.setReply(msg.readByte());
        dataPack.setCarId(analysisCarId(msg));
        return dataPack;
    }

    private String analysisCarId(ByteBuf byteBuf){
        byte[] bytes = new byte[CAR_ID_LENGTH];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = byteBuf.readByte();
        }
        return ByteAnalysisUtil.bytes2String(bytes);
    }
}
