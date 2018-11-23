<<<<<<< HEAD
import java.sql.{Connection,DriverManager}
try{
	Class.forName("com.mysql.cj.jdbc.Driver")
	var connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "")
	val statement = connection.createStatement
	val rs = statement.executeQuery("SELECT * FROM personal")

	println("-----------------------------")
	mainMethod()

	def mainMethod(){
		println("Do you currently have an account with us? Y/N")
		var answer = scala.io.StdIn.readLine()
		answer match{
			case "Y" => memberOptions()
			case "N" => createAccount()
			case _ =>{ println("Invalid input")
				sys.exit(0)
			}
		}
	}

	def createAccount()={
		println("Please enter your name and then your address")
		var Name = scala.io.StdIn.readLine()
		var Address = scala.io.StdIn.readLine()
		println("Save record? Y/N")
		var save = scala.io.StdIn.readLine()
		save match{
			case "Y" => {
				statement.executeUpdate("insert into personal values(null,'"+Name+"','"+Address+"')")
				val regrs = statement.executeQuery("SELECT max(regno) as YourReg from personal")
				regrs.next
				val regfind = regrs.getInt("YourReg")
				println("Your account number is "+regfind+". Hold onto it!")
			}
			case "N" => println("Record not saved!")
			case _ => println("Invalid option! Record not processed")
		}
		anythingElse()	
	}

	def memberOptions(){
		println("Please enter your account number")
		var regno = scala.io.StdIn.readInt()
		showInfo(regno)		
		println("Would you like to deposit money (1), withdraw money(2), or show balance(3)?")
		var bankoption = scala.io.StdIn.readInt()
		bankoption match{
			case 1 => withdep("deposit", regno)
			case 2 => withdep("withdraw", regno)
			case 3 => {
				println("Your balance is: "+showBalance(regno))
				anythingElse
				}
			case _ => println("Invalid option!")
		}
	}

	def withdep(option:String, regno:Int)={
		println("Please enter the amount of money you would like to "+option)		
		var amount = scala.io.StdIn.readInt()	
		println("Do you want to save this transaction? Y/N")
		var save = scala.io.StdIn.readLine()		
		save match{
			case "Y" => { 
				if(option=="withdraw"){
					var balance = showBalance(regno)
					if(balance>=amount){
						statement.executeUpdate("insert into "+option+ " values("+regno+","+amount+",now())")
						println("Transaction saved.")
					}
					else{
						println("Not enough funds")
					}
				}
				else{
					statement.executeUpdate("insert into "+option+ " values("+regno+","+amount+",now())")
				println("Transaction saved.")
				}
			}
			case "N" => println("Transaction cancelled")
			case _ => println("Invalid option! Transaction not processed")
		}
		anythingElse()		
	}

	def showBalance(regno:Int):Int={
		val witrs = statement.executeQuery("SELECT SUM(amount) as totalwithdrawals FROM withdraw where regno="+regno)
		witrs.next
		val totalwithdrawals=witrs.getInt("totalwithdrawals")
		val deprs = statement.executeQuery("SELECT SUM(amount) as totaldeposits FROM deposit where regno="+regno)
		deprs.next
		val totaldeposits=deprs.getInt("totaldeposits")
		val balance = totaldeposits-totalwithdrawals
		return balance	
	}

	def showInfo(regno:Int){
		val name1 = statement.executeQuery("SELECT * FROM personal where RegNo="+regno)
		name1.next
		val name2 = name1.getString("name")
		println(name2)		
		val address1 = statement.executeQuery("SELECT * FROM personal where RegNo="+regno)
		address1.next
		val address2 = address1.getString("address")
		println(address2)
	}	

	def anythingElse(){
		println("Would you like to do anything else? Y/N ")
		var anythingelse = scala.io.StdIn.readLine()
		anythingelse match{
			case "Y" => memberOptions()
			case "N" => {
				println("Thank you for using our bank!")
				sys.exit(0)
			}
			case _ => println("Invalid option!")
		}
	}	
}

catch{
	case e:Exception => println(e)
=======
import java.sql.{Connection,DriverManager}
try{
	Class.forName("com.mysql.cj.jdbc.Driver")
	var connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "")
	val statement = connection.createStatement
	val rs = statement.executeQuery("SELECT * FROM personal")

	println("-----------------------------")
	mainMethod()

	def mainMethod(){
		println("Do you currently have an account with us? Y/N")
		var answer = scala.io.StdIn.readLine()
		answer match{
			case "Y" => memberOptions()
			case "N" => createAccount()
			case _ =>{ println("Invalid input")
				sys.exit(0)
			}
		}
	}

	def createAccount()={
		println("Please enter your name and then your address")
		var Name = scala.io.StdIn.readLine()
		var Address = scala.io.StdIn.readLine()
		println("Save record? Y/N")
		var save = scala.io.StdIn.readLine()
		save match{
			case "Y" => {
				statement.executeUpdate("insert into personal values(null,'"+Name+"','"+Address+"')")
				val regrs = statement.executeQuery("SELECT max(regno) as YourReg from personal")
				regrs.next
				val regfind = regrs.getInt("YourReg")
				println("Your account number is "+regfind+". Hold onto it!")
			}
			case "N" => println("Record not saved!")
			case _ => println("Invalid option! Record not processed")
		}
		anythingElse()	
	}

	def memberOptions(){
		println("Please enter your account number")
		var regno = scala.io.StdIn.readInt()
		showInfo(regno)		
		println("Would you like to deposit money (1), withdraw money(2), or show balance(3)?")
		var bankoption = scala.io.StdIn.readInt()
		bankoption match{
			case 1 => withdep("deposit", regno)
			case 2 => withdep("withdraw", regno)
			case 3 => {
				println("Your balance is: "+showBalance(regno))
				anythingElse
				}
			case _ => println("Invalid option!")
		}
	}

	def withdep(option:String, regno:Int)={
		println("Please enter the amount of money you would like to "+option)		
		var amount = scala.io.StdIn.readInt()	
		println("Do you want to save this transaction? Y/N")
		var save = scala.io.StdIn.readLine()		
		save match{
			case "Y" => { 
				if(option=="withdraw"){
					var balance = showBalance(regno)
					if(balance>=amount){
						statement.executeUpdate("insert into "+option+ " values("+regno+","+amount+",now())")
						println("Transaction saved.")
					}
					else{
						println("Not enough funds")
					}
				}
				else{
					statement.executeUpdate("insert into "+option+ " values("+regno+","+amount+",now())")
				println("Transaction saved.")
				}
			}
			case "N" => println("Transaction cancelled")
			case _ => println("Invalid option! Transaction not processed")
		}
		anythingElse()		
	}

	def showBalance(regno:Int):Int={
		val witrs = statement.executeQuery("SELECT SUM(amount) as totalwithdrawals FROM withdraw where regno="+regno)
		witrs.next
		val totalwithdrawals=witrs.getInt("totalwithdrawals")
		val deprs = statement.executeQuery("SELECT SUM(amount) as totaldeposits FROM deposit where regno="+regno)
		deprs.next
		val totaldeposits=deprs.getInt("totaldeposits")
		val balance = totaldeposits-totalwithdrawals
		return balance	
	}

	def showInfo(regno:Int){
		val name1 = statement.executeQuery("SELECT * FROM personal where RegNo="+regno)
		name1.next
		val name2 = name1.getString("name")
		println(name2)		
		val address1 = statement.executeQuery("SELECT * FROM personal where RegNo="+regno)
		address1.next
		val address2 = address1.getString("address")
		println(address2)
	}	

	def anythingElse(){
		println("Would you like to do anything else? Y/N ")
		var anythingelse = scala.io.StdIn.readLine()
		anythingelse match{
			case "Y" => memberOptions()
			case "N" => {
				println("Thank you for using our bank!")
				sys.exit(0)
			}
			case _ => println("Invalid option!")
		}
	}	
}

catch{
	case e:Exception => println(e)
>>>>>>> 034c38abf93efb9f86cd31dae9ea155fac8622b8
}