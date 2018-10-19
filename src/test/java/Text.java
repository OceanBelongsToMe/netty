/**
 * <一句话描述>
 *
 * @author wangyang
 * @version [需求编号, 2018/10/18]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class Text {
    public static int k = 0;
    public static Text t1 = new Text("t1");
    public static Text t2 = new Text("t2");
    public static int i = print("i");
    public static int n = 99;
    public int j = print("j");

    {
        print("构造块");
    }
    static {
        print("静态块");
    }

    public Text(String str) {
        System.out.println((++k) + ":" + str + "   i=" + i + "    n=" + n);
        ++i;
        ++n;
    }

    public static int print(String str) {
        System.out.println((++k) + ":" + str + "   i=" + i + "    n=" + n);
        ++n;
        return ++i;
    }

    public static void main(String args[]) {
        Text t = new Text("init");
    }
}
