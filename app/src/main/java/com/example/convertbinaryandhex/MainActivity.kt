package com.example.convertbinaryandhex

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText


class MainActivity : AppCompatActivity() {
    private val user_input by lazy(LazyThreadSafetyMode.NONE){findViewById<EditText>(R.id.UserInput)}
    private val binary_number by lazy(LazyThreadSafetyMode.NONE){findViewById<EditText>(R.id.Binary)};
    private val hexa_number by lazy(LazyThreadSafetyMode.NONE){findViewById<EditText>(R.id.hexa)};


    private var list = mutableListOf<Int>()
    private var hex_list = mutableListOf<String>()

    private val TAG="decimal-to-binary";

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val convert_button: Button =findViewById(R.id.convert)

        convert_button.setOnClickListener(){
            binary_number.setText(" ")
            var input = user_input.text.toString()

            list.clear()
            hex_list.clear()


            dectobinary(input)
            dectohex(input)
        }

    }

    /* ------------------------------------------------------------------------------------------ */
    /* ------------------------------------------------------------------------------------------ */
    /* ------------------------------------------------------------------------------------------ */

    private fun dectobinary(value:String){
        var quotient = value.toInt();

        //start of while loop
        while (quotient!=0) {

            val remainder = quotient % 2;
            quotient = quotient / 2;

            Log.d(TAG, "Remainder: $remainder")
            Log.d(TAG, "quotient: $quotient")

            list.add(remainder)
        }
        binary_number.setText(list.toString().reversed()
            .replace(",", "")  //remove the commas
            .replace("[", "")  //remove the right bracket
            .replace("]", "")  //remove the left bracket
            .trim()//remove trailing spaces from partially initialized arrays)

        )

    }
    /* ------------------------------------------------------------------------------------------ */
    /* ------------------------------------------------------------------------------------------ */
    /* ------------------------------------------------------------------------------------------ */

    private fun dectohex(value:String){
        var quotient = value.toInt();
        while (quotient!=0) {

            var remainder = quotient % 16;
            quotient = quotient / 16;

            var hex_conversion = remainder.toString()

            when(hex_conversion){
                "10"->hex_conversion="A"
                "11"->hex_conversion="B"
                "12"->hex_conversion="C"
                "13"->hex_conversion="D"
                "14"->hex_conversion="E"
                "15"->hex_conversion="F"
            }


            hex_list.add(hex_conversion)
            hexa_number.setText(hex_list.toString().reversed()
                .replace(" ","")
                .replace(",", "")  //remove the commas
                .replace("[", "")  //remove the right bracket
                .replace("]", "")  //remove the left bracket
                .trim()
            )

        }


    }
    /* ------------------------------------------------------------------------------------------ */
    /* ------------------------------------------------------------------------------------------ */
    /* ------------------------------------------------------------------------------------------ */

    fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }

}