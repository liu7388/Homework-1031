package com.example.lab3

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView
import com.example.lab3.databinding.ActivityMainBinding // 1. 匯入 binding 類別

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding // 2. 宣告 binding 變數


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Step1 初始化 binding 物件並設定畫面
        // 3. 初始化 binding 物件並設定畫面
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // Step2 設定 btnMora 的點擊事件
        // 4. 透過 binding 物件設定點擊事件
        binding.btnMora.setOnClickListener {
            // Step3 如果 edName 為空，則顯示提示文字
            if (binding.edName.text.isEmpty()) {
                binding.tvText.text = "請輸入玩家姓名"
                return@setOnClickListener
            }
            // Step4 從 edName 取得玩家姓名
            val playerName = binding.edName.text.toString()
            // Step5 使用 (0..2).random() 會回傳 0~2 的整數，以此作為電腦的出拳
            val targetMora = (0..2).random()
            // Step6 透過 radioGroup.checkedRadioButtonId 取得選取的 RadioButton ID，
            // 並透過 when 判斷選取的是哪個 RadioButton ID，並回傳 0~2 作為玩家的出拳
            val myMora = when (binding.radioGroup.checkedRadioButtonId) {
                R.id.btnScissor -> 0
                R.id.btnStone -> 1
                else -> 2
            }
            // Step8 設定玩家姓名、我方出拳、電腦出拳的文字
            binding.tvName.text = "名字\n$playerName"
            binding.tvMyMora.text = "我方出拳\n${getMoraString(myMora)}"
            binding.tvTargetMora.text = "電腦出拳\n${getMoraString(targetMora)}"
            // Step9 判斷玩家和電腦誰獲勝
            when {
                myMora == targetMora -> {
                    binding.tvWinner.text = "勝利者\n平手"
                    binding.tvText.text = "平局，請再試一次！"
                }

                (myMora == 0 && targetMora == 2) ||
                        (myMora == 1 && targetMora == 0) ||
                        (myMora == 2 && targetMora == 1) -> {
                    binding.tvWinner.text = "勝利者\n$playerName"
                    binding.tvText.text = "恭喜你獲勝了！！！"
                }

                else -> {
                    binding.tvWinner.text = "勝利者\n電腦"
                    binding.tvText.text = "可惜，電腦獲勝了！"
                }
            }
        }
    }

    // Step7 傳入 0, 1, 2，回傳對應的文字，分別是剪刀、石頭、布
    private fun getMoraString(mora: Int): String {
        return when (mora) {
            0 -> "剪刀"
            1 -> "石頭"
            else -> "布"
        }
    }
}
