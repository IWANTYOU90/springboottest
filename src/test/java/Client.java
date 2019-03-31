import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Created by zhouchao on 2019/3/30.
 */
public class Client extends SimpleChannelInboundHandler {


    @Override
    protected void messageReceived(ChannelHandlerContext channelHandlerContext, Object msg) throws Exception {
        System.out.println("【client接收到服务器返回的消息】:" + msg);
    }
}
