/**
 * Created by desuev on 21.03.16.
 */
public class Add {
    int id, price, quant;
    String name;
    Add(int a, String b, int c, int d)
    {
        id = a;
        name = b;
        price = c;
        quant = d;
    }

    Add()
    {

    }

    public void Set(int a, String b, int c, int d)
    {
        id = a;
        name = b;
        price = c;
        quant = d;
    }
}