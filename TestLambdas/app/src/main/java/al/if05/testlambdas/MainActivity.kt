package al.if05.testlambdas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val suma: (Int, Int) -> Int = { a, b -> a + b }

        // Uso de la expresión lambda
        val resultado = suma(3, 5)
        println("La suma es: $resultado")
    }
}