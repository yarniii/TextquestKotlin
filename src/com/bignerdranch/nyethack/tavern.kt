import java.io.File
import com.bignerdranch.nyethack.extensions.random as randomizer

const val TavernName="Taernyl's Folly"

val patronList= mutableListOf("Eli","Mordoc","Sophie")
val lastname=listOf("Ironfoot","Fernsworth","Baggins")
val uniquePatrons= mutableSetOf<String>()
val menuList=File("data/tavern-menu-data.txt")
        .readText()
        .split("\n")

val patronGold = mutableMapOf<String,Double>()

val readOnlyPatronList=patronList.toList()
fun main() {
    if (patronList.contains("Eli")) {
        println("The tavern master says:Eli's in the back playing cards")
    } else {
        println("The tavern master says: Eli isn't here")
    }
    if (patronList.containsAll(listOf("Sophie", "Mordoc"))) {
        println("The tavern master says: Yea, they're seated by the stew kettle.")
    } else {
        println("The tavern master says: Nay, they departed hours ago.")
    }

    (0..9).forEach{
        val first=patronList.random()
        val last=lastname.random()
        val name="$first $last"
        uniquePatrons+=name
    }
    uniquePatrons.forEach{
        patronGold[it]=6.0
    }
    var orderCount = 0
    while (orderCount<=9){
        placeOrder(uniquePatrons.random(),
        menuList.random())
        orderCount++
    }
    displayPatronBalance()

}
private fun displayPatronBalance(){
    patronGold.forEach{ (patron, balance) ->
        println("$patron, balance: ${"%.2f".format(balance)}")
    }
}

private fun toDragonSpeak(phrase: String)=
    phrase.replace(Regex("[aeiou]")){
        when (it.value){
            "a"->"4"
            "e"->"3"
            "i"->"1"
            "o"->"0"
            "u"->"|_|"
            else->it.value
        }

}
fun performPurchase(price: Double, patronName: String){
    val totalPurse=patronGold.getValue(patronName)
    patronGold[patronName]=totalPurse-price
}

private fun placeOrder(patronName:String,menuData:String){
    val indexOfApostrophe=TavernName.indexOf('\'')
    val tavernMaster=TavernName.substring(0 until indexOfApostrophe)
    println("$patronName speaks with $tavernMaster about their order")
    val data=menuData.split(',')
    val(type,name,price)=menuData.split(',')
    val messeage="$patronName buys a $name ($type) for $price. "
    println(messeage)
    performPurchase(price.toDouble(),patronName)
    val phrase=if (name=="Dragon's breath"){
        "$patronName exclaims ${toDragonSpeak("Ah delicious $name!")}"
    }
    else{
        "$patronName says: Thanks for the $name"
    }
    println(phrase)


}