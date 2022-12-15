package pe.edu.idat.tiendamonturas_v01.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.zxing.integration.android.IntentIntegrator
import pe.edu.idat.tiendamonturas_v01.R
import pe.edu.idat.tiendamonturas_v01.databinding.ActivityScannerBinding

class ScannerActivity : AppCompatActivity() {

    private lateinit var binding:ActivityScannerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityScannerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnInitScan.setOnClickListener{initScanner()}
    }
    private fun initScanner(){
        IntentIntegrator(this).initiateScan()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        val result=IntentIntegrator.parseActivityResult(requestCode,resultCode,data)
        if(result!=null){
            if(result.contents==null){
                Toast.makeText(this,"Cancelado",Toast.LENGTH_SHORT).show()

            }else{
                Toast.makeText(this,"El valor es: ${result.contents}",Toast.LENGTH_SHORT).show()

            }
        }else{
            super.onActivityResult(requestCode, resultCode, data)
        }


    }
}