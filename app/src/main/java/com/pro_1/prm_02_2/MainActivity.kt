package com.pro_1.prm_02_2

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Color
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.io.IOException
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var entryLayout: LinearLayout
    private lateinit var addEntryButton: Button
    private lateinit var passwordInput: EditText
    private lateinit var addPhotoButton: Button
    private lateinit var imageView: ImageView
    private var entryCounter = 0

    private val REQUEST_IMAGE_CAPTURE = 1

    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addPhotoButton = findViewById(R.id.add_photo_button)
        imageView = findViewById(R.id.image_view)

        addPhotoButton.setOnClickListener {
            dispatchTakePictureIntent()
        }

        dbHelper = DatabaseHelper(this)

        showPasswordDialog()
    }

    private fun dispatchTakePictureIntent() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
            != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.CAMERA),
                REQUEST_IMAGE_CAPTURE
            )
        } else {
            val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_IMAGE_CAPTURE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                dispatchTakePictureIntent()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            val imageBitmap = data?.extras?.get("data") as Bitmap
            imageView.setImageBitmap(imageBitmap)
        }
    }

    private fun showPasswordDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Wprowadź hasło")

        val input = EditText(this)
        passwordInput = input
        builder.setView(input)

        builder.setPositiveButton("OK") { dialog, which ->
            if (isPasswordCorrect()) {
                dialog.dismiss()
                showEntriesMenu()
            } else {
                showPasswordDialog()
            }
        }

        builder.setCancelable(false)

        val passwordDialog = builder.create()
        passwordDialog.show()
    }

    private fun isPasswordCorrect(): Boolean {
        val password = "0000"
        return passwordInput.text.toString() == password
    }

    private fun showEntriesMenu() {
        entryLayout = findViewById(R.id.entry_layout)
        addEntryButton = findViewById(R.id.add_entry_button)

        addEntryButton.setOnClickListener {
            createNewEntry()
        }
    }

    private fun createNewEntry() {
        val entryWrapper = LinearLayout(this)
        entryWrapper.orientation = LinearLayout.VERTICAL
        entryWrapper.setBackgroundColor(Color.parseColor("#EEEEEE"))
        val layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        layoutParams.setMargins(0, 16, 0, 0)

        val location = getLocation()
        val locationName = getLocationName(location)

        val entryEditText = EditText(this)
        val editText = EditText(this)
        val entryNumber = ++entryCounter

        entryEditText.hint = "Tytuł wpisu $entryNumber - Lokalizacja: $locationName"
        entryEditText.setBackgroundColor(Color.parseColor("#CCCCCC"))
        entryEditText.setPadding(16, 8, 16, 8)

        entryWrapper.addView(entryEditText)
        entryWrapper.addView(editText)

        entryLayout.addView(entryWrapper, layoutParams)

        saveEntry(entryEditText.text.toString(), editText.text.toString(), locationName)
    }

    private fun saveEntry(title: String, content: String, location: String) {
        val db = dbHelper.writableDatabase

        val values = ContentValues().apply {
            put(EntryContract.EntryTable.COLUMN_TITLE, title)
            put(EntryContract.EntryTable.COLUMN_CONTENT, content)
            put(EntryContract.EntryTable.COLUMN_LOCATION, location)
        }

        val newRowId = db.insert(EntryContract.EntryTable.TABLE_NAME, null, values)

        db.close()
    }

    private fun getLocation(): Location? {
        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ),
                PERMISSIONS_REQUEST_LOCATION
            )
            return null
        }
        return locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
    }

    private fun getLocationName(location: Location?): String {
        var cityName = ""
        val geoCoder = Geocoder(this, Locale.getDefault())
        try {
            val addresses = geoCoder.getFromLocation(location?.latitude ?: 0.0, location?.longitude ?: 0.0, 1)
            if (addresses != null && addresses.isNotEmpty()) {
                cityName = addresses[0].locality ?: ""
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return cityName
    }

    companion object {
        private const val PERMISSIONS_REQUEST_LOCATION = 100
    }
}
