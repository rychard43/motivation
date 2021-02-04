package br.com.rych.motivation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import br.com.rych.motivation.R
import br.com.rych.motivation.utils.Constants
import br.com.rych.motivation.infra.SecurityPreferenses
import br.com.rych.motivation.repository.MockPhrases
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mSecurityPreferenses: SecurityPreferenses
    private var mPhraseFilter : Int = Constants.PHASEFILTER.ALL

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mSecurityPreferenses = SecurityPreferenses(this)

        textName.text = mSecurityPreferenses.getString(Constants.KEY.PERSON_NAME)

        //inicial
        imageAll.setColorFilter(resources.getColor(R.color.colorAccent))
        handleNewPhrase()

        buttomNewPhrase.setOnClickListener(this)
        imageAll.setOnClickListener(this)
        imageHappy.setOnClickListener(this)
        imageMorning.setOnClickListener(this)
    }

    override fun onClick(view: View) {

        val id = view.id
        val listFilter = listOf(R.id.imageAll, R.id.imageHappy, R.id.imageMorning)

        if(id == R.id.buttomNewPhrase){
            handleNewPhrase()
        }else if (id in listFilter){
            handleFilter(id)
        }

    }

    fun handleNewPhrase(){
        textPhrase.text = MockPhrases().getPhrase(mPhraseFilter)
    }

    fun handleFilter(id: Int){

        imageAll.setColorFilter(resources.getColor(R.color.white))
        imageHappy.setColorFilter(resources.getColor(R.color.white))
        imageMorning.setColorFilter(resources.getColor(R.color.white))

        when(id){
            R.id.imageAll -> {
                imageAll.setColorFilter(resources.getColor(R.color.colorAccent))
                mPhraseFilter = Constants.PHASEFILTER.ALL
            }
            R.id.imageHappy -> {
                imageHappy.setColorFilter(resources.getColor(R.color.colorAccent))
                mPhraseFilter = Constants.PHASEFILTER.HAPPY
            }
            R.id.imageMorning -> {
                imageMorning.setColorFilter(resources.getColor(R.color.colorAccent))
                mPhraseFilter = Constants.PHASEFILTER.MORNING
            }
        }
    }
}