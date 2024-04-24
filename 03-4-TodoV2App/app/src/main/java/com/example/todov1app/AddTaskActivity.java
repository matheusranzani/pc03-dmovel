package com.example.todov1app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.todov1app.databinding.ActivityAddTaskBinding;

public class AddTaskActivity extends AppCompatActivity {

    ActivityAddTaskBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddTaskBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.addNewButton.setOnClickListener(view -> {
            String name = binding.taskNameEditText.getText().toString();
            String desc = binding.taskDescEditText.getText().toString();

            // Pega a prioridade com base no radio button selecionado
            Priority selectedPriority = null;

            int checkedRadioButtonId = binding.radioGroupPriority.getCheckedRadioButtonId();

            switch (checkedRadioButtonId) {
                case R.id.radioButtonLow:
                    selectedPriority = Priority.LOW;
                    break;
                case R.id.radioButtonMedium:
                    selectedPriority = Priority.MEDIUM;
                    break;
                case R.id.radioButtonHigh:
                    selectedPriority = Priority.HIGH;
                    break;
            }

            // Se a prioridade não for selecionada não salva a tarefa
            if (selectedPriority != null) {
                Task task = new Task(name, desc, selectedPriority);
                Intent i = new Intent();
                i.putExtra("taskAdded", task);
                setResult(RESULT_OK, i);
                AddTaskActivity.this.finish();
            }
        });
    }
}