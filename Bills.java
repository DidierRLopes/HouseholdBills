
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Didier Lopes
 */
public class Bills {
    static Double personAreceiveFromPersonB = 0.00;
    static List<yearBill> ALL = new ArrayList<>();
    
    public static void main(String[] args) throws IOException  {
        
    }
    
    public static void firstLoadOfAll(){
        File direct = new File("C:\\HouseholdBills");
        
        if(!direct.exists())
            direct.mkdir();
        
        File file = new File("C:\\HouseholdBills\\parfb.txt");
        
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(Bills.class.getName()).log(Level.SEVERE, null, ex);
            }
            BufferedWriter writeArq;
            try {
                writeArq = new BufferedWriter(new FileWriter("C:\\HouseholdBills\\parfb.txt"));
                writeArq.write(personAreceiveFromPersonB.toString());
                writeArq.close();
            } catch (IOException ex) {
                Logger.getLogger(Bills.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        try {
            BufferedReader readArq = new BufferedReader(new FileReader("C:\\HouseholdBills\\parfb.txt"));
            String parfb = readArq.readLine();
            personAreceiveFromPersonB = Double.parseDouble(parfb);
        } catch (IOException ex) {
            Logger.getLogger(Bills.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void loadBills() throws FileNotFoundException, IOException{  
        File[] files = new File("C:\\HouseholdBills").listFiles();
       
        for (File file : files) {
            if (file.isDirectory()) {
                String year = file.getName();
                ArrayList<monthBill> monthsYear;
                monthsYear = new ArrayList<>();

                yearBill YB = new yearBill(year, monthsYear);

                File[] files2 = new File("C:\\HouseholdBills\\" + year).listFiles();
                    
                for (File file2 : files2) {
                    if (file2.isFile()) {
                        int length = file2.getName().length();
                        String month = file2.getName().substring(0, length-4);

                        ArrayList<bill> billsMonth;
                        billsMonth = new ArrayList<>();
                        monthBill MB = new monthBill(month, billsMonth);

                        FileReader arq = new FileReader(file2);

                        try (BufferedReader readArq = new BufferedReader(arq)) {
                            String name, date, comment, data;
                            Double totalValue, valueA, valueB, paymentA, paymentB;

                            while ((data = readArq.readLine()) != null) {
                                name = data.substring(0, data.indexOf(';'));

                                data = readArq.readLine();
                                date = data.substring(0, data.indexOf(';'));

                                comment = readArq.readLine();
                                while (!comment.contains(";")){
                                    data = readArq.readLine();
                                    comment = comment.concat('\n' + data);
                                }
                                comment = comment.substring(0, comment.indexOf(';'));

                                data = readArq.readLine();
                                totalValue = Double.parseDouble(data.substring(0, data.indexOf(';')));

                                data = readArq.readLine();
                                valueA = Double.parseDouble(data.substring(0, data.indexOf(';')));
                                valueB = Double.parseDouble(data.substring(data.indexOf(';')+1, data.lastIndexOf(';')));

                                data = readArq.readLine();
                                paymentA = Double.parseDouble(data.substring(0, data.indexOf(';')));
                                paymentB = Double.parseDouble(data.substring(data.indexOf(';')+1, data.lastIndexOf(';')));

                                data = readArq.readLine();

                                bill B = new bill(name, date, comment, totalValue, valueA, valueB, paymentA, paymentB);
                                MB.bills.add(B);   
                            }
                            readArq.close();
                        } catch(Exception E){
                        }
                    YB.months.add(MB);
                    }
                }
                ALL.add(YB);    
            }   
        }              
    }
                
    public static void saveBill(String name, String date, String comment, double totalValue, double valueA, double valueB, double paymentA, double paymentB) throws IOException{
        String yearInString = date.substring(date.lastIndexOf("/") + 1, date.length());
        String monthInString = date.substring(date.indexOf("/") + 1, date.lastIndexOf("/"));
        
        File file = new File("C:\\HouseholdBills\\" + yearInString + "\\"+ monthInString +".txt");
        
        FileWriter arq = new FileWriter(file, true);

        try (BufferedWriter writeArq = new BufferedWriter(arq)) {
            writeArq.write(name + ";");
            writeArq.newLine();
            writeArq.write(date + ";");
            writeArq.newLine();
            writeArq.write(comment + ";");
            writeArq.newLine();
            writeArq.write(Double.toString(totalValue) + ";");
            writeArq.newLine();
            writeArq.write(Double.toString(valueA) + ";" + Double.toString(valueB) + ";");
            writeArq.newLine();
            writeArq.write(Double.toString(paymentA) + ";" + Double.toString(paymentB) + ";");
            writeArq.newLine();
            writeArq.newLine();    
        }
        arq.close();
        
        miniCalculator(totalValue, valueA, valueB, paymentA);
    }
    
    public static void createFile(String date){
        try {
            String yearInString = date.substring(date.lastIndexOf("/") + 1, date.length());
            String monthInString = date.substring(date.indexOf("/") + 1, date.lastIndexOf("/"));
            File directory = new File("C:\\HouseholdBills\\" + yearInString);
            if(!directory.exists())
                directory.mkdir();
            File file = new File("C:\\HouseholdBills\\" + yearInString + "\\"+ monthInString +".txt");
            if(!file.exists())
                file.createNewFile();
            
        } catch (IOException ex) {
            Logger.getLogger(Bills.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void createBill(String name, String date, String comment, double totalValue, double valuePersonA, double valuePersonB, double paymentPersonA, double paymentPersonB){
        bill b = new bill(name, comment, date, totalValue, valuePersonA, valuePersonB, paymentPersonA, paymentPersonB);
        String yearInString = date.substring(date.lastIndexOf("/") + 1, date.length());
        String monthInString = date.substring(date.indexOf("/") + 1, date.lastIndexOf("/"));
        
        int i = 0, j=0, indexYear = -1, indexMonth = -1;
        File[] files = new File("C:\\HouseholdBills").listFiles();
        for (File file : files) {
            if (file.isDirectory() && yearInString.equals(file.getName()))
                indexYear = i;
            i++;
        }
        yearBill YB;
        if(indexYear == -1){
            ArrayList<monthBill> monthsYear = new ArrayList<>();
            YB = new yearBill(yearInString, monthsYear);
        }
        else     
            YB = ALL.get(indexYear);
        
        File[] files2 = new File("C:\\HouseholdBills\\" + yearInString).listFiles();
        for (File file2 : files2) {
            int length = file2.getName().length();
            if (file2.isFile() && monthInString.equals(file2.getName().substring(0, length-4)))
                indexMonth = j;
            j++;
        }
        
        monthBill MB;
        if(indexMonth == -1){
            ArrayList<bill> billsMonth = new ArrayList<>();
            MB = new monthBill(monthInString, billsMonth);
        }
        else
            MB = ALL.get(indexYear).months.get(indexMonth);
        
        if(indexYear == -1 && indexMonth == -1){
            MB.bills.add(b);
            YB.months.add(MB);
            ALL.add(YB);
        }
        else
            ALL.get(indexYear).months.get(indexMonth).bills.add(b);
        
    }   
    
    public static void getBill(String name, String date, String comment, double totalValue, double valuePersonA, double valuePersonB, double paymentPersonA, double paymentPersonB){
        String yearInString = date.substring(date.lastIndexOf("/") + 1, date.length());
        String monthInString = date.substring(date.indexOf("/") + 1, date.lastIndexOf("/"));
        
        int i = 0, j=0, indexYear = -1, indexMonth = -1;
        File[] files = new File("C:\\HouseholdBills").listFiles();
        for (File file : files) {
            if (file.isDirectory() && yearInString.equals(file.getName()))
                indexYear = i;
            i++;
        }
        yearBill YB = ALL.get(indexYear);
        
        File[] files2 = new File("C:\\HouseholdBills\\" + yearInString).listFiles();
        for (File file2 : files2) {
            int length = file2.getName().length();
            if (file2.isFile() && monthInString.equals(file2.getName().substring(0, length-4)))
                indexMonth = j;
            j++;
        }
        
        monthBill MB = ALL.get(indexYear).months.get(indexMonth);

    }   
    
    public static void miniCalculator(double totalValue, double valuePersonA, double valuePersonB, double paymentPersonA){
        Double aux = 0.00;
        aux = totalValue - valuePersonA - valuePersonB;
        aux = aux/2.0;
        aux = aux + valuePersonA;
        aux = paymentPersonA - aux;
        personAreceiveFromPersonB = personAreceiveFromPersonB + aux;
        
        
        try (PrintWriter pw = new PrintWriter(new File("C:\\HouseholdBills\\parfb.txt"))) {
            pw.write("");
            pw.flush();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Bills.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        BufferedWriter writeArq;
        try {
            writeArq = new BufferedWriter(new FileWriter("C:\\HouseholdBills\\parfb.txt"));
            writeArq.write(personAreceiveFromPersonB.toString());
            writeArq.close();
        } catch (IOException ex) {
            Logger.getLogger(Bills.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   
    
    public static ArrayList<String> listOfYears(){
        ArrayList<String> years = new ArrayList<>();

        for(yearBill YB : ALL){
            years.add(YB.year);
        }   
        return years;
    }

    public static ArrayList<String> listOfMonths(int yearIndice){
        ArrayList<String> months = new ArrayList<>();
        
        for(monthBill MB : ALL.get(yearIndice).months){
            switch(MB.month){
                case "01": months.add("Janeiro"); break;
                case "02": months.add("Fevereiro"); break;
                case "03": months.add("Março"); break;
                case "04": months.add("Abril"); break;
                case "05": months.add("Maio"); break;    
                case "06": months.add("Junho"); break;    
                case "07": months.add("Julho"); break;    
                case "08": months.add("Agosto"); break;
                case "09": months.add("Setembro"); break;
                case "10": months.add("Outubro"); break;
                case "11": months.add("Novembro"); break; 
                case "12": months.add("Dezembro"); break; 
                default:
            }
        }   
        return months;
    }
    
    public static ArrayList<String> listOfDays(int yearIndice, int monthIndice){
        ArrayList<String> days = new ArrayList<>();
        
        for(bill b : ALL.get(yearIndice).months.get(monthIndice).bills){
            if(b.dateBill.substring(0, b.dateBill.indexOf("/")).charAt(0) == '0')
                days.add(b.dateBill.substring(1, b.dateBill.indexOf("/")));
            else
                days.add(b.dateBill.substring(0, b.dateBill.indexOf("/")));
        }   
        return days;
    }
    
    public static boolean isDateValid(String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);
        try {
            Date beforeToday = dateFormat.parse(date.trim());
            Date today = new Date(System.currentTimeMillis());
            if(beforeToday.after(today))
                return false;
        } catch (ParseException pe) {
            return false;
        }   
    return true;
    }
 
}
