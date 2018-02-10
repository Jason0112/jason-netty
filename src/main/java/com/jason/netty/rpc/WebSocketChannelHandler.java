package com.jason.netty.rpc;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.stream.ChunkedWriteHandler;


/**
 * date: 2018/2/10
 * description :
 *
 * @author : jason
 */
public class WebSocketChannelHandler extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        channel.pipeline().addLast("http-codec", new HttpServerCodec());
        channel.pipeline().addLast("aggregator", new HttpObjectAggregator(65536));
        channel.pipeline().addLast("http-chunked", new ChunkedWriteHandler());
        channel.pipeline().addLast("rpc", new WebSocketHandler());
    }
}
