/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Didier Lopes
 */
public class bill {
    String nameBill;
    String dateBill;
    String commentBill;
    double totalValue, valuePersonA, valuePersonB;
    double payPersonA, payPersonB;

    bill (String _name, String _date, String _comment, double _totalValue, double _valueA, double _valueB, double _payA, double _payB){
        nameBill = _name;
        dateBill = _date;
        commentBill = _comment;
        totalValue = _totalValue;
        valuePersonA = _valueA;
        valuePersonB = _valueB;
        payPersonA = _payA;
        payPersonB = _payB;
    }     
}
