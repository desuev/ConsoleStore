/**
 * Created by desuev on 14.03.16.
 */
import java.io.IOException;
import java.util.*;

public class labFirst
{

    public static void main(String[] args) throws IOException
    {
        Store store = new Store();
        Scanner scanner = new Scanner(System.in);
        Boolean run = true;
        while(run)
        {
            store.Show();
            int pick;
            pick = scanner.nextInt();
            switch (pick)
            {
                case 1:
                    store.Sell();
                    break;
                case 2:
                    store.Supply();
                    break;
                case 3:
                    store.List();
                    break;
                case 4:
                    run = false;
                    break;
                default:
                    break;
            }
        }
    }
}
