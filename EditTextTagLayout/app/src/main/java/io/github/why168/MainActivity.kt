package io.github.why168

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import rx.subscriptions.CompositeSubscription
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

class MainActivity : AppCompatActivity() {

    private val PASSPHRASE_LENGTH = 12

    private var subscriptions: CompositeSubscription? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initAll();
    }

    private fun initAll() {
        this.subscriptions = CompositeSubscription()
        initWordList()

        initSignInPassphraseView()
        initClickListeners()
    }


    private fun initWordList() {
        try {

            val stream = resources.openRawResource(R.raw.word)
            val reader = BufferedReader(InputStreamReader(stream))
            val wordList = reader.readText().split("\n")


            // val wordList = MnemonicCode().wordList
            addWordListToViewPasshraseView(wordList)
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun addWordListToViewPasshraseView(wordList: List<String>) {
        passphraseInputView.setWordList(wordList as ArrayList<String>)
    }


    private fun initSignInPassphraseView() {

        passphraseInputView
                .setOnPassphraseFinishListener(this::handlePassphraseFinished)
                .setOnPassphraseUpdateListener(this::updateSignInButton);

    }

    private fun initClickListeners() {


    }

    private fun handlePassphraseFinished(passphrase: List<String>) {
        signIn.setText(R.string.sign_in)
        signIn.setBackgroundResource(R.drawable.background_with_radius_primary_color)
        signIn.setEnabled(true)
    }

    private fun updateSignInButton(approvedWords: Int) {
        val wordsLeft = PASSPHRASE_LENGTH - approvedWords
        if (wordsLeft > 0) {
            val wordsLeftString = getResources().getQuantityString(R.plurals.words, wordsLeft, wordsLeft)
            disableSignIn(wordsLeftString)
        }
    }

    private fun disableSignIn(string: String) {
        signIn.setText(string)
        signIn.setBackgroundResource(R.drawable.background_with_radius_disabled)
        signIn.setEnabled(false)
    }
}
