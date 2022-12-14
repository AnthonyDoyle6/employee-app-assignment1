package ie.setu

import kotlin.math.round


val firstName = "Joe"
val surname = "Soap"

//Find Reference: https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/find.html
listOf("Soap").find { it.startsWith("So") }
val gender = 'm'
val employeeID = 6143
val grossSalary = 67543.21
val payePercentage = 38.5
val prsiPercentage = 5.2
val annualBonus = 1450.50
//Update attempt. Assistance from StackOverflow
val newAnnualBonus = annualBonus.updateannualBonus(2000)
val cycleToWorkMonthlyDeduction = 54.33

fun main(args: Array<String>){

    var input : Int

    do {
        input = menu()
        when(input) {
            1 -> println("Monthly Salary: ${getMonthlySalary()}")
            /*
            I attempted to filter the monthly salary. Reference: https://kotlinlang.org/docs/collection-filtering.html#filter-by-predicate
            val longerThan4 = MonthlySalary.filter { it.length > 4 }
            println(longerThan4)
            */
            2 -> println("Monthly PRSI: ${getMonthlyPRSI()}")
            3 ->println("Monthly PAYE: ${getMonthlyPAYE()}")
            4 -> println("Monthly Gross Pay: ${getGrossMonthlyPay()}")
            5 -> println("Monthly Total Deductions: ${getTotalMonthlyDeductions()}")
            6 -> println("Monthly Net Pay: ${getNetMonthlyPay()}")
            7 -> println(getPayslip())
            -1 -> println("Exiting App")
            else -> println("Invalid Option")
        }
        println()
    } while (input != -1)
}

fun menu() : Int {
    print("""
         Employee Menu for ${getFullName()}
           1. Monthly Salary
           2. Monthly PRSI
           3. Monthly PAYE
           4. Monthly Gross Pay
           5. Monthly Total Deductions
           6. Monthly Net Pay
           7. Full Payslip
          -1. Exit
         Enter Option : """)
         return readLine()!!.toInt()
}

fun getFullName() = when (gender){
        'm', 'M' -> "Mr. $firstName $surname"
        'f', 'F' -> "Ms. $firstName $surname"
        else -> "$firstName $surname"
}

fun getMonthlySalary() = roundTwoDecimals(grossSalary / 12)
fun getMonthlyPRSI() = roundTwoDecimals(getMonthlySalary() * (prsiPercentage / 100))
fun getMonthlyPAYE() = roundTwoDecimals(getMonthlySalary() * (payePercentage / 100))
fun getGrossMonthlyPay() = roundTwoDecimals(getMonthlySalary() + (annualBonus / 12))
fun getTotalMonthlyDeductions() = roundTwoDecimals((getMonthlyPRSI() + getMonthlyPAYE() + cycleToWorkMonthlyDeduction))
fun getNetMonthlyPay() = roundTwoDecimals(roundTwoDecimals(getGrossMonthlyPay() - getTotalMonthlyDeductions()))

fun getPayslip() =
        """
        ______________________________________________________________________
         Monthly Payslip:             ${getFullName()}, ID: $employeeID                  
        ______________________________________________________________________    
              PAYMENT DETAILS (gross pay: ${getGrossMonthlyPay()})                                                                    
        ______________________________________________________________________
                   Salary: ${getMonthlySalary()}
                   Bonus:  ${roundTwoDecimals(annualBonus / 12)}            
        ______________________________________________________________________
              DEDUCTION DETAILS (total Deductions: ${getTotalMonthlyDeductions()})      
        ______________________________________________________________________
                   PAYE: ${getMonthlyPAYE()}                
                   PRSI: ${getMonthlyPRSI()}  
                   Cycle To Work: $cycleToWorkMonthlyDeduction         
        ______________________________________________________________________
             NET PAY: ${getNetMonthlyPay()} 
        ______________________________________________________________________"""


fun roundTwoDecimals(number: Double) = round(number * 100) / 100




