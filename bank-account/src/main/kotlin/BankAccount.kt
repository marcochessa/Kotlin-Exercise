import java.util.concurrent.Semaphore

class BankAccount {
    var closed: Boolean = false
    var account: Long = 0
    var balance: Long = 0
        get(){
            if(closed==true){
                throw IllegalStateException();
            } else {
                return field
            }
        }

    val s = Semaphore(1)

    fun adjustBalance(amount: Long){
        s.acquire()
        if(closed==false){
            this.balance = this.balance + amount
        } else {
            throw IllegalStateException();
        }
        s.release()
    }

    fun close() {
        this.closed = true
    }
}
