/**
 * Created by desuev on 19.03.16.
 */
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class FileDataWorks {

     int[] ID, price, quantity, IDList, priceList, quantityList;
     String[] name, nameList;
     Add add = new Add();
     int intCounter = 0, stringCounter = 0, intCounterList = 0, stringCounterList = 0,  ad = 0;
     STACK stack = new STACK();

    public void writeIntoExcel(String file, String stream) throws FileNotFoundException, IOException{
        Workbook book = new HSSFWorkbook();
        Sheet sheet = book.createSheet("Store");
        Row row = sheet.createRow(0);
        Cell data = row.createCell(0);
        data.setCellValue("ID");
        data = row.createCell(1);
        data.setCellValue("NAME");
        data = row.createCell(2);
        data.setCellValue("PRICE");
        data = row.createCell(3);
        data.setCellValue("QUANTITY");
        int counter = 0;
        if(stream == "store") {
            for (int i = 0; i < intCounter; i++) {
                row = sheet.createRow(i + 1);
                while (counter != 4) {
                    data = row.createCell(counter);
                    switch (counter) {
                        case 0:
                            data.setCellValue(ID[i]);
                            break;
                        case 1:
                            data.setCellValue(name[i]);
                            break;
                        case 2:
                            data.setCellValue(price[i]);
                            break;
                        case 3:
                            data.setCellValue(quantity[i]);
                            break;
                    }
                    counter++;
                }
                counter = 0;

            }
            if(ad == 1)
            {
                row = sheet.createRow(intCounter+1);
                while (counter != 4) {
                    data = row.createCell(counter);
                    switch (counter) {
                        case 0:
                            data.setCellValue(add.id);
                            break;
                        case 1:
                            data.setCellValue(add.name);
                            break;
                        case 2:
                            data.setCellValue(add.price);
                            break;
                        case 3:
                            data.setCellValue(add.quant);
                            break;
                    }
                    counter++;
                }
            }
        }else if(stream == "list")
        {
            readFromExcel("list.xls", "list");
            for (int i = 0; i < intCounterList; i++) {
                row = sheet.createRow(i+1);
                while (counter != 4) {
                    data = row.createCell(counter);
                    switch (counter) {
                        case 0:
                            data.setCellValue(IDList[i]);
                            break;
                        case 1:
                            data.setCellValue(nameList[i]);
                            break;
                        case 2:
                            data.setCellValue(priceList[i]);
                            break;
                        case 3:
                            data.setCellValue(quantityList[i]);
                            break;
                    }
                    counter++;
                }
                counter = 0;
            }
            if(ad == 1)
            {
                row = sheet.createRow(intCounterList+1);
                while (counter != 4) {
                    data = row.createCell(counter);
                    switch (counter) {
                        case 0:
                            data.setCellValue(add.id);
                            break;
                        case 1:
                            data.setCellValue(add.name);
                            break;
                        case 2:
                            data.setCellValue(add.price);
                            break;
                        case 3:
                            data.setCellValue(add.quant);
                            break;
                    }
                    counter++;
                }
                counter = 0;
            }
        }

        sheet.autoSizeColumn(1);
        book.write(new FileOutputStream(file));
        book.close();
    }

    public void readFromExcel(String file, String stream) throws IOException{
        HSSFWorkbook myExcelBook = new HSSFWorkbook(new FileInputStream(file));
        HSSFSheet myExcelSheet = myExcelBook.getSheetAt(0);
        Iterator<Row> rowIteratorCount = myExcelSheet.iterator();
        Iterator<Row> rowIteratorData = myExcelSheet.iterator();
        rowIteratorData.next();
        while(rowIteratorCount.hasNext())
        {
            Row row = rowIteratorCount.next();
            Iterator<Cell> cellIterator = row.cellIterator();

            while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                if(stream == "store")
                    switch (cell.getCellType()) {
                        case Cell.CELL_TYPE_STRING: stringCounter++; break;
                        case Cell.CELL_TYPE_NUMERIC: intCounter++; break;
                        //case Cell.CELL_TYPE_BOOLEAN: System.out.print(cell.getBooleanCellValue() + "\t"); break;
                        default : break;
                    }
                else if(stream == "list")
                    switch (cell.getCellType()) {
                        case Cell.CELL_TYPE_STRING:
                            stringCounterList++;
                            break;
                        case Cell.CELL_TYPE_NUMERIC:
                            intCounterList++;
                            break;
                        //case Cell.CELL_TYPE_BOOLEAN: System.out.print(cell.getBooleanCellValue() + "\t"); break;
                        default:
                            break;
                    }
            }
        }
        System.out.println();
        if(stream == "store") {
            ID = new int[intCounter / 3];
            price = new int[intCounter / 3];
            quantity = new int[intCounter / 3];
            name = new String[stringCounter - 4];
            intCounter = stringCounter = 0;
        }
        else if (stream == "list")
        {
            IDList = new int[intCounterList / 3];
            priceList = new int[intCounterList / 3];
            quantityList = new int[intCounterList / 3];
            nameList = new String[stringCounterList - 4];
            intCounterList = stringCounterList = 0;
        }

        int pointer = 0;

        while(rowIteratorData.hasNext()) {
            Row row = rowIteratorData.next();
            Iterator<Cell> cellIterator = row.cellIterator();


            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                 if(stream == "store") {
                     switch (cell.getCellType()) {
                         case Cell.CELL_TYPE_STRING:
                             name[stringCounter] = cell.getStringCellValue();
                             stringCounter++;
                             break;
                         case Cell.CELL_TYPE_NUMERIC:
                             switch (pointer) {
                                 case 0:
                                     ID[intCounter] = (int) cell.getNumericCellValue();
                                     pointer = 1;
                                     break;
                                 case 1:
                                     price[intCounter] = (int) cell.getNumericCellValue();
                                     pointer = 2;
                                     break;
                                 case 2:
                                     quantity[intCounter] = (int) cell.getNumericCellValue();
                                     intCounter++;
                                     pointer = 0;
                                     break;
                             }
                             //case Cell.CELL_TYPE_BOOLEAN: System.out.print(cell.getBooleanCellValue() + "\t"); break;
                         default:
                             break;
                     }
                     stack.createStack(intCounter);
                 }
                else if(stream == "list")
                     switch (cell.getCellType()) {
                         case Cell.CELL_TYPE_STRING:  nameList[stringCounterList] = cell.getStringCellValue(); stringCounterList++; break;
                         case Cell.CELL_TYPE_NUMERIC:
                             switch (pointer)
                             {
                                 case 0: IDList[intCounterList] = (int)cell.getNumericCellValue(); pointer = 1; break;
                                 case 1: priceList[intCounterList] = (int)cell.getNumericCellValue(); pointer = 2; break;
                                 case 2: quantityList[intCounterList] = (int)cell.getNumericCellValue(); intCounterList++; pointer = 0; break;
                             }
                             //case Cell.CELL_TYPE_BOOLEAN: System.out.print(cell.getBooleanCellValue() + "\t"); break;
                         default : break;
                     }
            }
        }

        myExcelBook.close();

     }

    public void showData(String stream) {
        if(stream == "store") {
            System.out.println("ID" + "\t" + "NAME" + "\t" + "PRICE" + "\t" + "QUANTITY");
            for (int i = 0; i < intCounter; i++) {
                System.out.print(ID[i] + "\t");
                System.out.print(name[i] + "\t");
                System.out.print((double) price[i] + "\t");
                System.out.print(quantity[i] + "\n");
            }
        }
        else if(stream == "list") {
            System.out.println("ID" + "\t" + "NAME" + "\t" + "PRICE" + "\t" + "QUANTITY");
            for (int i = 0; i < intCounterList; i++) {
                System.out.print(IDList[i] + "\t");
                System.out.print(nameList[i] + "\t");
                System.out.print((double) priceList[i] + "\t");
                System.out.print(quantityList[i] + "\n");
            }
        }
    }

    public void supply(int place, int quant)
    {
        quantity[place] += quant;
    }

    public int searchID(int id, String stream){
        if(stream == "store") {
            System.out.println();
            for (int i = 0; i < intCounter; i++)
                if (ID[i] == id)
                    return i;
            return -1;
        }
        else if(stream == "list")
        {
            System.out.println();
            for (int i = 0; i < intCounterList; i++)
                if (IDList[i] == id)
                    return i;
            return -1;
        }
        return -1;
    }

    public int searchName(String Name, String stream){
        if(stream == "store") {
            System.out.println();
            for (int i = 0; i < stringCounter; i++)
                if (name[i] == Name)
                    return i;
            return -1;
        }
        else if(stream == "list")
        {
            System.out.println();
            for (int i = 0; i < stringCounterList; i++)
                if (nameList[i] == Name)
                    return i;
            return -1;
        }
        return 0;
    }

    public void addToFile(int id, String name, int price, int quantity, String stream, String file)throws FileNotFoundException, IOException
    {
            ad = 1;
            add.Set(id,name,price,quantity);
            writeIntoExcel(file, stream);
            ad = 0;
    }

    public boolean check(int id, String stream){
        if(stream == "store") {
            if (quantity[id] == 0) {
                System.out.println("Товар отсутвует на складе.¯\\ _ (ツ) _ /¯");
                System.out.println();
                return false;
            } else {
                System.out.println("Товар c ID \"" + ID[id] + "\" найден !");//\"" + name[i] + "\" найден !");
                System.out.println("Название: " + name[id]);
                System.out.println("Цена: " + price[id]);
                System.out.println("Кол-во: " + quantity[id]);
            }
            System.out.println();
            return true;
        }
        else if(stream == "list")
        {
            if (quantityList[id] == 0) {
                System.out.println("Товар отсутвует на складе.¯\\ _ (ツ) _ /¯");
                System.out.println();
                return false;
            } else {
                System.out.println("Товар c ID \"" + IDList[id] + "\" найден !");//\"" + name[i] + "\" найден !");
                System.out.println("Название: " + nameList[id]);
                System.out.println("Цена: " + priceList[id]);
                System.out.println("Продано " + quantityList[id] +"шт.");
            }
            System.out.println();
            return true;
        }
        return true;
    }

    public boolean listOfRequest(int position, int Quantity){
        if(Quantity > quantity[position])
        {
            System.out.println("Данно кол-во товара недоступно ! Возьмите меньше.");
            return false;
        }
        quantity[position] -= Quantity;
        stack.add(position, Quantity);
        System.out.println("Товар добавлен ! \n");
        return true;
    }

    public void makeRequest() throws FileNotFoundException, IOException {

        writeIntoExcel("store.xls", "store");
        int position, quant, summ = 0;
        System.out.println("Ваш список заказов:");
        int counter = stack.getDephth() + 1;

       for(int i = 0; i < counter; i++)
       {
           position = stack.popPosition();
           quant = stack.popQuantity();
           System.out.println("Название: " + name[position]);
           System.out.println("Кол-во: " + quant + "\n");
           summ += price[position]*quant;
           addToFile(ID[position], name[position], price[position], quant, "list", "list.xls");
       }

        System.out.println("Сумма заказа: " + summ + "\n");
    }

}