import java.io.IOException;
import java.util.Scanner;

/**
 * Created by desuev on 14.03.16.
 */
public class Store
{
    private void showTable()
    {
        final int x = 3, y = 7;
        char table[][] = new char[x][y];
        table[1][0] = table[1][y-1] = '#';
        for(int i = 0; i < y; i++)
        {
            table[0][i] = '#';
            table[2][i] = '#';
        }
        System.out.print("\n");
        for(int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                if (i == 1) {
                    if (j > 0 && j < (y - 1)) {
                        System.out.print("ТОВАР");
                        j = y - 2;
                    } else System.out.print(table[i][j]);
                } else
                    System.out.print(table[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    public void Show()
    {
        System.out.print("1)Продажа товара.\n");
        System.out.print("2)Поставка товара.\n");
        System.out.print("3)Данные по продажам.\n");
        System.out.print("4)Закончить работу.\n");
        System.out.print("Выберите пункт: ");
    }

    public void Sell() throws IOException
    {
        FileDataWorks fw = new FileDataWorks();
        showTable();
        try {
            fw.readFromExcel("store.xls", "store");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scanner scanner = new Scanner(System.in);

        boolean run = true;
      while(run)
      {
        fw.showData("store");
        System.out.println("Выбрите действие (выполнить заказ - 1 , сформировать запрос - 2 , выйти - 3)");
        int pick;
        String sPick;
        pick = scanner.nextInt();
          System.out.println();
          switch (pick) {
              case 1:
                  System.out.println("По какому ключу выполнить поиск ? (ID - 1 , NAME - 2 , Exit - 3)");
                  pick = scanner.nextInt();
                  switch (pick) {
                      case 1:
                          System.out.print("Введите ID товара: ");
                          pick = scanner.nextInt();
                          int place = fw.searchID(pick, "store");
                          if (place == -1) {
                              System.out.println("Товар не найден. ");
                              break;
                          }
                          boolean request = fw.check(place,"store");
                          while (request) {
                              System.out.print("Введите кол-во единиц товара: ");
                              pick = scanner.nextInt();
                              int quantity = pick;
                              request = !fw.listOfRequest(place, quantity);
                          }
                          break;
                      case 2:
                          System.out.print("Введите название товара: ");
                          Scanner scan = new Scanner(System.in);
                          sPick = scan.nextLine();
                          int Place = fw.searchName(sPick, "store");
                          if (Place == -1) {
                              System.out.println("Товар не найден. ");
                              break;
                          }
                          boolean Request = fw.check(Place,"store");
                          while (Request) {
                              System.out.print("Введите кол-во единиц товара: ");
                              pick = scanner.nextInt();
                              int quantity = pick;
                              request = !fw.listOfRequest(Place, quantity);
                          }

                          break;
                      case 3:
                          break;
                      default:
                          break;
                  } break;
              case 2:
                  fw.makeRequest();
                  break;
              case 3:
                  run  = false;
                  break;
          }
      }
        System.out.println();
    }

    public void Supply() throws IOException
    {
        FileDataWorks fw = new FileDataWorks();
        fw.readFromExcel("store.xls", "store");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите ID: ");
        int id = scanner.nextInt();
        int place = fw.searchID(id, "store");
        if(place != -1)
        {
            System.out.print("Введите кол-во поставлямых единиц: ");
            int supply = scanner.nextInt();
            fw.supply(place, supply);
            fw.writeIntoExcel("store.xls", "store");

            System.out.print("Поставка выполнена успешно !\n");
        }
        else
        {
            int price, quant;
            String name;
            System.out.println("Товар с данным ID не найден ! Создайте новый товар.");
            System.out.print("Введите Name: ");
            Scanner scan = new Scanner(System.in);
            name = scan.nextLine();
            System.out.print("Введите Price: ");
            price = scanner.nextInt();
            System.out.print("Введите Quantity: ");
            quant = scanner.nextInt();
            fw.addToFile(id,name,price,quant,"store","store.xls");
        }
    }

    public void List()
    {
      System.out.println("\nСписок продаж.\n");
      FileDataWorks fw = new FileDataWorks();
        try {
            fw.readFromExcel("list.xls", "list");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scanner scanner = new Scanner(System.in);

        boolean run = true;
        while(run) {
            fw.showData("list");
            System.out.println("\n Выберите действие (получить подробную информацию о товаре - 1, выйти - 2)");
            int pick = scanner.nextInt();
            switch (pick) {
                case 1:
                    System.out.println("Введите ID товара: ");
                    pick = scanner.nextInt();
                    int place = fw.searchID(pick,"list");
                    if (place == -1) {
                        System.out.println("Товар не найден. ");
                        break;
                    }
                    fw.check(place, "list");
                    break;
                case 2: run = false;
                    break;
                default:
                    break;
            }
        }
    }
}
