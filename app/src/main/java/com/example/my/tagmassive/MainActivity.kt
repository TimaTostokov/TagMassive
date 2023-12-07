package com.example.my.tagmassive

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.example.my.tagmassive.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val adapter by lazy { AdapterTags(this::setTags) }
    private val savedTags = hashSetOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initAdapter()
        saveTags()
        showTags()
    }

    private fun initAdapter() {
        binding.rvHints.adapter = adapter
    }

    private fun saveTags() {
        binding.ivSend.setOnClickListener {
            savedTags.addAll(findTag(binding.etText.text.toString()))
            binding.etText.text.clear()
        }
    }

    private fun showTags() {
        binding.etText.addTextChangedListener {
            adapter.addData(savedTags(binding.etText.text.toString()))
        }
    }

    private fun findTag(text: String): ArrayList<String> {
        val result = arrayListOf<String>()
        val message = text.split(" ")
        for (i in message) {
            if (i.startsWith("#")) {
                result.add(i)
            }
        }

        return result
    }

    private fun savedTags(text: String): ArrayList<String> {
        val result = arrayListOf<String>()
        val onlineTag = findTag(text)
        for (tag in onlineTag) {
            for (savedTag in savedTags) {
                if (savedTag.contains(tag) && savedTag != tag)
                    result.add(savedTag)
            }
        }

        return result
    }

    private fun setTags(newTag: String) {
        val message = binding.etText.text.toString()
        val splitMessage = message.split(" ").toMutableList()
        val tagIndex = findIndex(splitMessage, message)
        splitMessage[tagIndex] = newTag
        binding.etText.setText(splitMessage.joinToString(" "))
        binding.etText.setSelection(binding.etText.text.length)
    }

    private fun findIndex(splitMessage: MutableList<String>, message: String): Int {
        for (i in findTag(message)) {
            if (!savedTags.contains(i))
                return splitMessage.indexOf(i)
        }
        return 0
    }

}