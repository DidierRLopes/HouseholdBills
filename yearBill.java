
import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Didier Lopes
 */
public class yearBill {
    String year;
    List<monthBill> months = new ArrayList<>();
    
    yearBill (String _year, ArrayList<monthBill> _months){
        year = _year;
        months = _months;
    }  
    
}
