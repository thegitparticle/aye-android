package com.example.toastgoand.quick;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewbinding.ViewBinding;

import com.example.toastgoand.BaseActivity;
import com.example.toastgoand.databinding.ActivityStreamScreenBinding;

public class StreamScreenActivity extends BaseActivity {

    private ActivityStreamScreenBinding binding;
    private StreamScreenViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityStreamScreenBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        viewModel = new ViewModelProvider(this).get(StreamScreenViewModel.class);

    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        IsFinish("Want to close app?");
    }

    public void IsFinish(String alertmessage) {

        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:
                        android.os.Process.killProcess(android.os.Process.myPid());
                        // This above line close correctly
                        //finish();
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(alertmessage)
                .setPositiveButton("Yes", dialogClickListener)
                .setNegativeButton("No", dialogClickListener).show();

    }

    @NonNull
    @Override
    public ViewBinding binding() {
        return null;
    }
}