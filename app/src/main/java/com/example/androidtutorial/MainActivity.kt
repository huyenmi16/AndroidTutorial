package com.example.androidtutorial


// MainActivity.kt
import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var sharedPref: SharedPreferences
    private lateinit var textViewNumber: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Khởi tạo SharedPreferences
        sharedPref = getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE)

        // Ánh xạ các thành phần giao diện
        textViewNumber = findViewById(R.id.textViewNumber)

        // Thiết lập sự kiện cho các nút màu
        findViewById<Button>(R.id.btnColorBlue).setOnClickListener {
            changeBackgroundColor(Color.BLUE)
        }

        findViewById<Button>(R.id.btnColorRed).setOnClickListener {
            changeBackgroundColor(Color.RED)
        }

        findViewById<Button>(R.id.btnColorPurple).setOnClickListener {
            changeBackgroundColor(Color.MAGENTA)
        }

        findViewById<Button>(R.id.btnColorYellow).setOnClickListener {
            changeBackgroundColor(Color.YELLOW)
        }

        // Thiết lập sự kiện cho nút Count
        findViewById<Button>(R.id.btnCount).setOnClickListener {
            incrementNumber()
        }

        // Thiết lập sự kiện cho nút Reset
        findViewById<Button>(R.id.btnReset).setOnClickListener {
            resetValues()
        }

        // Hiển thị giá trị ban đầu từ SharedPreferences
        displaySavedValues()
    }

    private fun incrementNumber() {
        // Đọc giá trị hiện tại từ SharedPreferences
        val currentValue = sharedPref.getInt(getString(R.string.saved_number_key), 0)

        // Tăng giá trị
        val newValue = currentValue + 1

        // Lưu giá trị mới vào SharedPreferences
        with(sharedPref.edit()) {
            putInt(getString(R.string.saved_number_key), newValue)
            apply()
        }

        // Hiển thị giá trị mới lên TextView
        textViewNumber.text = newValue.toString()
    }

    private fun changeBackgroundColor(color: Int) {
        // Lưu mã màu vào SharedPreferences
        with(sharedPref.edit()) {
            // Ví dụ: putInt(getString(R.string.saved_color_key), color)
            apply()
        }

        // Đổi màu nền của rootView
        findViewById<TextView>(R.id.textViewNumber).setBackgroundColor(color)
    }

    private fun resetValues() {
        // Xóa các giá trị từ SharedPreferences
        with(sharedPref.edit()) {
            remove(getString(R.string.saved_number_key))
            // Xóa giá trị màu nền nếu cần
            // Ví dụ: remove(getString(R.string.saved_color_key))
            apply()
        }

        // Đặt lại giá trị mặc định cho TextView và màu nền
        textViewNumber.text = "0"
        findViewById<TextView>(R.id.textViewNumber).setBackgroundColor(Color.WHITE)
    }

    private fun displaySavedValues() {
        // Đọc và hiển thị giá trị đã lưu từ SharedPreferences
        val savedNumber = sharedPref.getInt(getString(R.string.saved_number_key), 0)
        textViewNumber.text = savedNumber.toString()

        // Đọc và áp dụng màu nền đã lưu (nếu có)
        // Ví dụ: val savedColor = sharedPref.getInt(getString(R.string.saved_color_key), Color.WHITE)
        // findViewById<TextView>(R.id.textViewNumber).setBackgroundColor(savedColor)
    }
}
