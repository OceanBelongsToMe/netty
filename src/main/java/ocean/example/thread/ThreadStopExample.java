package ocean.example.thread;

import java.util.concurrent.TimeUnit;

/**
 * <一句话描述>
 *
 * @author wangyang
 * @version [需求编号, 2018/8/1]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class ThreadStopExample
{
    private static boolean stop;

    public static void main(String[] args)
        throws InterruptedException
    {
        Thread workThread = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                int i = 0;
                while (!stop)
                {
                    i++;
                    try
                    {
                        TimeUnit.SECONDS.sleep(1);
                        System.out.println(i);
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        });
        workThread.start();
        TimeUnit.SECONDS.sleep(3);
        stop = true;
        System.out.println(3>>2);
    }
}
