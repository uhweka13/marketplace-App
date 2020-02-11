package com.example.firebasedemo

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_edit_profile.*
import java.io.ByteArrayOutputStream

class edit_profile : AppCompatActivity() {

    private lateinit var et_edit_name: EditText
    private lateinit var et_edit_address: EditText
    private lateinit var et_edit_phone: EditText
    private lateinit var im_edit: ImageView
    private lateinit var bt_edit: Button
    private lateinit var edit_name:String
    private lateinit var edit_address:String
    private lateinit var edit_phone:String
    private lateinit var submit_bt: Button
    private lateinit var edit_image: ImageView

    //two constants to specify our actions, either we are picking images from gallery or camera
    private val GALLERY = 11
    private val CAMERA = 12

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        val takePicture: Button = findViewById(R.id.bt_edit_submit)
        val id = intent.getIntExtra("id",-1)
        val name = intent.getStringExtra("name")
        val address = intent.getStringExtra("address")
        val phone = intent.getStringExtra("phone")
//        val image =intent.getByteArrayExtra("image")
        val image = intent.getByteArrayExtra("image")




        et_edit_name = findViewById(R.id.et_edit_name)
        et_edit_address = findViewById(R.id.et_edit_address)
        et_edit_phone = findViewById(R.id.et_edit_phone)
        im_edit = findViewById(R.id.im_edit)



        et_edit_name.setText(name)
        et_edit_address.setText(address)
        et_edit_phone.setText(phone)

        val bmp = BitmapFactory.decodeByteArray(image,0,image.size)
        im_edit.setImageBitmap(bmp)



        takePicture.setOnClickListener(View.OnClickListener {
            //create an object of the alert dialog
            val pictureDialog = AlertDialog.Builder(this)

            // we set out title
            pictureDialog.setTitle("Select Action")

            //we specify the options on this line
            val pictureDialogItems = arrayOf("Select photo from gallery", "Capture photo from camera")
            //we set our actions here. if user select any option what should it do
            pictureDialog.setItems(pictureDialogItems
            ) { dialog, which ->
                when (which) {
                    //action 1 chooses image from the gallery
                    0 -> choosePhotoFromGallary()//this function that performs the action is below
                    //action 2 takes a photo from the camera
                    1 -> takePhotoFromCamera()//this function that perform this action is below
                }
            }
            //always put this line for the dialog to show
            pictureDialog.show()


        })


        val submit_bt: Button = findViewById(R.id.bt_edit_submit)

        submit_bt.setOnClickListener(View.OnClickListener {


            edit_name = et_edit_name.text.toString().trim()

            edit_address = et_edit_address.text.toString().trim()
            edit_phone = et_edit_phone.text.toString().trim()


            val bitmap = (bt_edit_submit.getDrawable() as BitmapDrawable).getBitmap()
            val stream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
            val imageView = stream.toByteArray()


        })
    }


    private fun choosePhotoFromGallary() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun takePhotoFromCamera() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun Button.getDrawable(): Any {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

    }
}
