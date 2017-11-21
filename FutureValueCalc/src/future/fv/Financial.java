/*
Author: Scott Payton
Assignment: Ex_21-2
Date:11/19/2017 
Description: In this exercise, you’ll add a Clear button to the GUI version of the Future Value Calculator. 
*/

package future.fv;   //class is located in this package
public class Financial { //Start financial  class

     //a static method passing double and ints 


	public static double calculateFutureValue(double monthlyInvestment, double yearlyInterestRate, int years) {
		// TODO Auto-generated method stub

        // convert yearly values to monthly values
        double monthlyInterestRate = yearlyInterestRate / 12 / 100; //declaring the monthly interest rate 
        int months = years * 12;         //declaring the variable for years
        // calculate the future value
        double futureValue = 0; //initialize future value to 0
        for (int i = 1; i <= months; i++) { //begin for loop
            futureValue += monthlyInvestment; //addition auto caster 
            double monthlyInterestAmount = futureValue * monthlyInterestRate; //declaring monthly interest amount
            futureValue += monthlyInterestAmount; //addition auto caster  
        } //end for loop 
        
        return futureValue; //returning future value 
     //end static calculate FV method
		
	}

	
}//end class Financial
