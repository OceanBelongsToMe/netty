package ocean.example.netty.websocket;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.websocketx.CloseWebSocketFrame;
import io.netty.handler.codec.http.websocketx.PingWebSocketFrame;
import io.netty.handler.codec.http.websocketx.PongWebSocketFrame;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshaker;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshakerFactory;
import io.netty.util.CharsetUtil;
import util.DateUtil;

import static io.netty.handler.codec.http.HttpUtil.isKeepAlive;
import static io.netty.handler.codec.http.HttpUtil.setContentLength;

/**
 * <一句话描述>
 *
 * @author wangyang
 * @version [需求编号, 2018/10/13]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class WebSocketServerHandler extends SimpleChannelInboundHandler<Object>
{

    private WebSocketServerHandshaker shaker;

    private String url = "ws://localhost:8080/websocket";

    public WebSocketServerHandler()
    {
    }

    //该方法会接受的客户端的请求
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg)
        throws Exception
    {
        //判断msg是否为FullHttpRequest
        if (msg instanceof FullHttpRequest)
        {
            handleHttpRequest(ctx, (FullHttpRequest)msg);
        }
        //判断请求对象是否为WebsocketFrame
        else if (msg instanceof WebSocketFrame)
        {
            //将msg对象转换为WebSocketFrame
            handleWebSocketFrame(ctx, (WebSocketFrame)msg);
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx)
        throws Exception
    {
        ctx.flush();
    }

    private void handleHttpRequest(ChannelHandlerContext ctx, FullHttpRequest msg)
    {
        //http解码失败，或者http请求头Upgrade的值不等于websocket，则返回404
        if (!msg.decoderResult().isSuccess() || !"websocket".equals(msg.headers().get("Upgrade")))
        {
            sendHttpResponse(ctx,
                msg,
                new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_REQUEST));
            return;
        }
        WebSocketServerHandshakerFactory wsf = new WebSocketServerHandshakerFactory(url, null, false);
        shaker = wsf.newHandshaker(msg);
        if (null == shaker)
        {
            //请求头中无Upgrade
            WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(ctx.channel());
        }
        else
        {
            shaker.handshake(ctx.channel(), msg);
        }
    }

    private void handleWebSocketFrame(ChannelHandlerContext ctx, WebSocketFrame msg)
    {
        //是否是closeFrame
        if (msg instanceof CloseWebSocketFrame)
        {
            shaker.close(ctx.channel(), (CloseWebSocketFrame)msg.retain());
            return;
        }
        //是否是PingFrame
        if (msg instanceof PingWebSocketFrame)
        {
            ctx.channel().write(new PongWebSocketFrame(msg.content().retain()));
        }
        //是否是非TextFrame
        if (!(msg instanceof TextWebSocketFrame))
        {
            throw new UnsupportedOperationException(String.format("% unsupported frame", msg.getClass().getName()));
        }
        String text = ((TextWebSocketFrame)msg).text();
        System.out.println(String.format("请求的内容是：%s", text));
        ctx.channel().write(
            new TextWebSocketFrame(text
                + " , 欢迎使用Netty WebSocket服务，现在时刻：" + DateUtil.getCurrentTime(DateUtil.YYYY_MM_DDHHMMSS_FORMAT)));

    }

    private void sendHttpResponse(ChannelHandlerContext ctx, FullHttpRequest request, FullHttpResponse response)
    {
        //如果response的状态码不等于200
        if (response.status().code() != 200)
        {
            //根据response的状态码，以utf-8字符集，创建ByteBuf
            ByteBuf byteBuf = Unpooled.copiedBuffer(response.status().toString(), CharsetUtil.UTF_8);
            //将response的响应体写入ByteBuf
            response.content().writeBytes(byteBuf);
            //将content的长度写入到response的响应头中
            byteBuf.release();
            setContentLength(response, response.content().readableBytes());
        }

        //writeAndFlush
        ChannelFuture f = ctx.channel().writeAndFlush(response);
        //若非keep-alive，或者response的status不等于200，则关闭连接
        if (!isKeepAlive(response) || response.status().code() != 200)
        {
            f.addListener(ChannelFutureListener.CLOSE);
        }

    }

}
