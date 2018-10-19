/**
 * <一句话描述>
 *
 * @author wangyang
 * @version [需求编号, 2018/10/17]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class StaticTest
{
    public static void main(String[] args)
    {

//输出顺序和 a, b 的值

        staticFunction();
    }

    static StaticTest st = new StaticTest();

    static
    {

        System.out.println("1");
    }

    {
        System.out.println("2");
    }

    StaticTest()
    {

        System.out.println("3");

        System.out.println("a = " + a + "b=" + b);

    }

    public static void staticFunction()
    {

        System.out.println("4");
    }

    int a = 110;

    static int b = 112;
}
