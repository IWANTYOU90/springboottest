import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;
/**
 * Created by zhouchao on 2019/3/30.
 */
public class NettyClient implements Runnable {

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {

        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();

        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(eventLoopGroup);

            //绑定channel
            bootstrap.channel(NioSocketChannel.class).option(ChannelOption.TCP_NODELAY,true);
            bootstrap.handler(new ChannelInitializer<SocketChannel>() {

                @Override
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    ChannelPipeline pipeline = socketChannel.pipeline();

                    pipeline.addLast("frameDecoder", new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));
                    pipeline.addLast("frameEncoder", new LengthFieldPrepender(4));
                    pipeline.addLast("decoder", new StringDecoder(CharsetUtil.UTF_8));
                    pipeline.addLast("encoder", new StringEncoder(CharsetUtil.UTF_8));

                    pipeline.addLast("handler", new Client());

                }
            });

            ChannelFuture future = bootstrap.connect("127.0.0.1",5656).sync();
            future.channel().writeAndFlush("Netty Hello Service!" + Thread.currentThread().getName()+":----->:"+Thread.currentThread().getId());
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            eventLoopGroup.shutdownGracefully();
        }


    }

    public static void main(String[] args) throws Exception {

        for (int i = 0; i < 10; i++) {
            new Thread(new NettyClient(),"【this thread】 "+i).start();
        }
    }

}
