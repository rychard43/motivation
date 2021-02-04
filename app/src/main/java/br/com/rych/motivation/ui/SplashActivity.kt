package br.com.rych.motivation.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import br.com.rych.motivation.R
import br.com.rych.motivation.utils.Constants
import br.com.rych.motivation.infra.SecurityPreferenses
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity(), View.OnClickListener {
                //inicialização tardia
    private lateinit var mSecurityPreferenses: SecurityPreferenses

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        mSecurityPreferenses = SecurityPreferenses(this)

        buttomSave.setOnClickListener(this)

        verifyName()

    }

    override fun onClick(view: View) {
        val id = view.id
        if (id == R.id.buttomSave) {
            handleSave()
        }
    }

    private fun verifyName(){
        val name = mSecurityPreferenses.getString(Constants.KEY.PERSON_NAME)

        if(name != ""){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun handleSave() {
        val name = editTextName.text.toString()
        if (name != "") {
            mSecurityPreferenses.storeString(Constants.KEY.PERSON_NAME,name)
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        } else {
            Toast.makeText(this, "Informe seu nome", Toast.LENGTH_SHORT).show()
        }
    }
}