
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
public class monthBill {
    String month;
    List<bill> bills = new ArrayList<>();
    
    monthBill (String _month, ArrayList<bill> _bills){
        month = _month;
        bills = _bills;
    }  
}
