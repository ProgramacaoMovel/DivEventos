import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.eventos.R
import com.google.android.filament.View

class FeedbackActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.feedback)

        val constraintLayout: ConstraintLayout = findViewById(R.id.constraintLayout)
        val textView14: TextView = findViewById(R.id.textView14)
        val textView15: TextView = findViewById(R.id.textView15)
        val divider: android.view.View? = findViewById(R.id.divider)
        val editTextTextMultiLine: EditText = findViewById(R.id.editTextTextMultiLine)
        val textView16: TextView = findViewById(R.id.textView16)
        val editTextTextEmailAddress2: EditText = findViewById(R.id.editTextTextEmailAddress2)
        val textView17: TextView = findViewById(R.id.textView17)
        val divider2: android.view.View? = findViewById(R.id.divider2)
        val checkBox: CheckBox = findViewById(R.id.checkBox)
        val checkBox2: CheckBox = findViewById(R.id.checkBox2)
        val imageView13: ImageView = findViewById(R.id.imageView13)
        val textView18: TextView = findViewById(R.id.textView18)
        val button8: Button = findViewById(R.id.button8)
        val button9: Button = findViewById(R.id.button9)

        // Aqui você pode definir os ouvintes de eventos e a lógica para cada componente, por exemplo:
        button8.setOnClickListener {
                finish()
        }

        button9.setOnClickListener {
            val feedback = editTextTextMultiLine.text.toString()
            val emailIntent = Intent(Intent.ACTION_SEND).apply {
                type = "message/rfc822"
                putExtra(Intent.EXTRA_EMAIL, arrayOf("feedback@gmail.com"))
                putExtra(Intent.EXTRA_SUBJECT, "Feedback do Aplicativo")
                putExtra(Intent.EXTRA_TEXT, feedback)
            }

            try {
                startActivity(Intent.createChooser(emailIntent, "Enviar email..."))
            } catch (ex: android.content.ActivityNotFoundException) {
                Toast.makeText(this, "Nenhum cliente de email instalado.", Toast.LENGTH_SHORT).show()
            }
        }


        // Adicione mais lógica conforme necessário
    }
}
