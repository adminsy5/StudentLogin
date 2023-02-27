package com.mpiyush3510.studentlogin;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.protobuf.Value;
import com.mpiyush3510.studentlogin.databinding.ActivityStudentSignUpBinding;

import java.util.HashMap;

public class StudentSignUp extends AppCompatActivity {

    ActivityStudentSignUpBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityStudentSignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Listeners();
    }

    private void Listeners()
    {
        binding.BtnSignUp.setOnClickListener(v -> {
            if(isValidSignUp()){
                signUp();
            }
        });
    }

    private void showToast(String Message)
    {
        Toast.makeText(this, Message, Toast.LENGTH_SHORT).show();
    }

    private Boolean isValidSignUp() {
    if(binding.EditName.getText().toString().isEmpty()){
        showToast("Please Enter Name");
        return false;
    } else if (binding.EditCourse.getText().toString().isEmpty()) {
        showToast("Please Enter Your Course");
        return false;
    }else if(binding.EditEmail.getText().toString().isEmpty()){
        showToast("Please Enter Email");
        return false;
    } else if (!Patterns.EMAIL_ADDRESS.matcher(binding.EditEmail.getText().toString()).matches()) {
        showToast("Please Enter Valid Email !");
        return false;
    } else if (binding.EditMno.getText().toString().isEmpty()) {
        showToast("Enter Your Mobile Number");
        return false;
    } else if (binding.EditPassword.getText().toString().isEmpty()) {
        showToast("Password Can't Be Empty");
        return false;
    }else if (binding.EditConfirmPassword.getText().toString().isEmpty()) {
        showToast("Enter Confirm Password");
        return false;
    }else if (!binding.EditPassword.getText().toString().matches(binding.EditConfirmPassword.getText().toString())) {
        showToast("Password Can't be Same");
        return false;
    }
        return true;
    }

    private void signUp()
    {
        isLoading(true);
        FirebaseFirestore database=FirebaseFirestore.getInstance();
        HashMap<String, Value> user=new HashMap<>();
    }

    private void isLoading(Boolean Loading)
    {
        if(Loading){
            binding.BtnSignUp.setVisibility(View.VISIBLE);
            binding.ProgressBar.setVisibility(View.INVISIBLE);
        }else {
            binding.BtnSignUp.setVisibility(View.INVISIBLE);
            binding.ProgressBar.setVisibility(View.VISIBLE);
        }
    }
}