import java.sql.{Connection,DriverManager}

class inputException extends Exception{}

class DBFApplication{

	Class.forName("com.mysql.cj.jdbc.Driver")
	var connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "")
	val statement = connection.createStatement

	def mainMethod(){
		try{
			println("-------------------------------------")
			println("Welcome to the banking application! Do you currently have an account with us? Y/N")
			var answer = scala.io.StdIn.readLine()
			answer match{
				case "Y"|"y" => memberOptions()
				case "N"|"n" => createAccount()
				case _ =>{ 
					var Y = new inputException
					throw Y
				}
			}
		}
		catch{
			case a:inputException => {
				println("Invalid input! Please try again")
				mainMethod()
			}
		}
	}

	private def createAccount()={
		try{
			println("Please enter your name and then your address")
			var Name = scala.io.StdIn.readLine()
			var Address = scala.io.StdIn.readLine()
			println("Save record? Y/N")
			var save = scala.io.StdIn.readLine()
			save match{
				case "Y"|"y" => {
					statement.executeUpdate("insert into personal values(null,'"+Name+"','"+Address+"')")
					val regrs = statement.executeQuery("SELECT max(regno) as YourReg from personal")
					regrs.next
					val regfind = regrs.getInt("YourReg")
					println("Your account number is "+regfind+". Hold onto it!")
				}
				case "N"|"n" => println("Record not saved!")
				case _ => {
					var Y = new inputException
					throw Y
				}
			}
			anythingElse()
		}
		catch{
			case a:inputException => {
				println("Invalid input! Account not processed")
				anythingElse()
			}
		}
	}
 
	private def memberOptions(){
		try{
			println("Please enter your account number")
			var regno = scala.io.StdIn.readInt()
			showInfo(regno)		
			println("Would you like to deposit money (1), withdraw money(2), show balance(3), or go back(4)?")
			var bankoption = scala.io.StdIn.readInt()
			bankoption match{
				case 1 => withdep("deposit", regno)
				case 2 => withdep("withdraw", regno)
				case 3 => {
					println("Your balance is: "+showBalance(regno))
					anythingElse
					}
				case 4 => mainMethod()
				case _ => {
					var Y = new inputException
					throw Y
				}
			}
		}
		catch{
			case a:inputException => {
				println("Invalid input! Please try again.")
				memberOptions()
			}
			case b:NumberFormatException => {
				println("Invalid input! Please try again.")
				memberOptions()
			}
			
		}
	}

	private def withdep(option:String, regno:Int)={
		try{
			println("Please enter the amount of money you would like to "+option)		
			var amount = scala.io.StdIn.readInt()	
			println("Do you want to save this transaction? Y/N")
			var save = scala.io.StdIn.readLine()		
			save match{
				case "Y"|"y" => { 
					if(option=="withdraw"){
						if(showBalance(regno)>=amount){
							statement.executeUpdate("insert into "+option+ " values("+regno+","+amount+",now())")
							println("Transaction saved. Your balance is now "+showBalance(regno))
						}
						else{
							println("Not enough funds")
						}
					}
					else{
						statement.executeUpdate("insert into "+option+ " values("+regno+","+amount+",now())")
						println("Transaction saved. Your balance is now "+showBalance(regno))
					}
				}
				case "N"|"n" => println("Transaction cancelled")
				case _ => {
					var Y = new inputException
					throw Y
				}
			}
			anythingElse()
		}
		catch{
			case a:inputException => {
				println("Invalid input! Transaction not processed.")
				anythingElse()
			}
			case b:NumberFormatException => {
				println("Invalid input! Transaction cancelled.")
				anythingElse()
			}
		}		
	}

	private def showBalance(regno:Int):Int={
		val witrs = statement.executeQuery("SELECT SUM(amount) as totalwithdrawals FROM withdraw where regno="+regno)
		witrs.next
		val totalwithdrawals=witrs.getInt("totalwithdrawals")
		val deprs = statement.executeQuery("SELECT SUM(amount) as totaldeposits FROM deposit where regno="+regno)
		deprs.next
		val totaldeposits=deprs.getInt("totaldeposits")
		val balance = totaldeposits-totalwithdrawals
		return balance		
	}

	private def showInfo(regno:Int){
		val personalrs = statement.executeQuery("SELECT * FROM personal where RegNo="+regno)
		if(personalrs.next){
			val name2 = personalrs.getString("name")	
			val address2 = personalrs.getString("address")
			println(name2+"\n"+address2)
		}
		else{
			println("Account number does not exist! Please try again.")
			memberOptions()
		}		
	}	

	private def anythingElse(){
		try{
			println("Would you like to do anything else? Y/N ")
			var anythingelse = scala.io.StdIn.readLine()
			anythingelse match{
				case "Y"|"y" => mainMethod()
				case "N"|"n" => {
					println("Thank you for using our bank!")
					sys.exit(0)
				}
				case _ => {
					var Y = new inputException
					throw Y
				}
			}
		}
		catch{
			case a:Exception => { 
				println("Invalid option! Try again.")
				anythingElse()
			}
		}
	}	
}

var user = new DBFApplication()
user.mainMethod()