package com.example.zhkh.Comunication;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zhkh.ApiInteractions.ApiWorker;
import com.example.zhkh.ApiInteractions.IAuthApi;
import com.example.zhkh.ApiInteractions.Singleton;
import com.example.zhkh.ApiInteractions.pojoes.RawTask;
import com.example.zhkh.ApiInteractions.pojoes.Task;
import com.example.zhkh.ApiInteractions.pojoes.Token;
import com.example.zhkh.ApiInteractions.pojoes.User;
import com.example.zhkh.LogScreenActivity;
import com.example.zhkh.MainActivity;
import com.example.zhkh.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;

public class CreateTaskFragment extends Fragment {


    private Button pushTask;
    private EditText taskDesc;
    private Spinner categories;
    private ImageButton photo1, photo2, photo3;
    private TextView feedbackEmail;
    public CreateTaskFragment() {
    }


    public static CreateTaskFragment newInstance(String param1, String param2) {
        CreateTaskFragment fragment = new CreateTaskFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_create_task, container, false);
        pushTask = (Button) view.findViewById(R.id.sendTuskButton);
        taskDesc = (EditText) view.findViewById(R.id.editTextTask);
        categories = (Spinner) view.findViewById(R.id.category);
        photo1 = (ImageButton) view.findViewById(R.id.imageButPrioriry);
        photo2 = (ImageButton) view.findViewById(R.id.imageButSecondPriority1);
        photo3 = (ImageButton) view.findViewById(R.id.imageButSecondPriority2);
        feedbackEmail = (TextView) view.findViewById(R.id.editTextFeedbackEmail);
        photo2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder ad = new AlertDialog.Builder(getContext());
                ad.setTitle("Выберите источник: ");  // заголовок
                ad.setMessage("Выберите источник изображения."); // сообщение
                ad.setPositiveButton("Галерея", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int arg1) {

                        Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(pickPhoto , 12);
                    }
                });



                ad.setNegativeButton("Сделать фото", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int arg1) {

                        Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(takePicture, 02);//zero can be replaced with any action code

                    }
                });
                ad.setCancelable(true);
                ad.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    public void onCancel(DialogInterface dialog) {
                        Toast.makeText(getContext(), "Вы ничего не выбрали",
                                Toast.LENGTH_LONG).show();
                    }
                });
                ad.show();
            }
        });
        photo3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog.Builder ad = new AlertDialog.Builder(getContext());
                        ad.setTitle("Выберите источник: ");  // заголовок
                        ad.setMessage("Выберите источник изображения."); // сообщение
                        ad.setPositiveButton("Галерея", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int arg1) {

                                Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                startActivityForResult(pickPhoto , 13);
                            }
                        });



                ad.setNegativeButton("Сделать фото", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int arg1) {

                        Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(takePicture, 03);//zero can be replaced with any action code

                    }
                });
                ad.setCancelable(true);
                ad.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    public void onCancel(DialogInterface dialog) {
                        Toast.makeText(getContext(), "Вы ничего не выбрали",
                                Toast.LENGTH_LONG).show();
                    }
                });
                ad.show();
            }
        });
        photo1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder ad = new AlertDialog.Builder(getContext());
                ad.setTitle("Выберите источник: ");  // заголовок
                ad.setMessage("Выберите источник изображения."); // сообщение
                ad.setPositiveButton("Галерея", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int arg1) {

                        Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(pickPhoto , 11);
                    }
                });



                ad.setNegativeButton("Сделать фото", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int arg1) {

                        Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(takePicture, 01);//zero can be replaced with any action code

                    }
                });
                ad.setCancelable(true);
                ad.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    public void onCancel(DialogInterface dialog) {
                        Toast.makeText(getContext(), "Вы ничего не выбрали",
                                Toast.LENGTH_LONG).show();
                    }
                });
ad.show();


            }
        });

        pushTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                ApiWorker aw = new ApiWorker("http://85.143.11.233:8000/");
                IAuthApi aa = aw.getLog();
                RawTask rt = new RawTask();

                rt.setDesc(taskDesc.getText().toString());
                rt.setMail(feedbackEmail.getText().toString());
                rt.setName(Singleton.getInstance().getFio());
                rt.setType((int)categories.getSelectedItemId());


                aa.sendTask(rt).enqueue(new Callback<Token>()
                {
                    @Override
                    public void onResponse(Call<Token> call, Response<Token> response)
                    {
                        if(!response.isSuccessful())
                        {
                            System.out.println("We got some troubles. But server is okay");
                            return;
                        }

                    }

                    @Override
                    public void onFailure(Call<Token> call, Throwable t)
                    {
                        System.out.println(t.getMessage());
                    }

                });

            }});
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode,resultCode,data);
        switch(requestCode) {
            case 01:
                if(resultCode == RESULT_OK){
                    Uri selectedImage = data.getData();
                    photo1.setImageURI(selectedImage);
                }

                break;
            case 11:
                if(resultCode == RESULT_OK){
                    Uri selectedImage = data.getData();
                    photo1.setImageURI(selectedImage);
                }
                break;
            case 02:
                if(resultCode == RESULT_OK){
                    Uri selectedImage = data.getData();
                    photo2.setImageURI(selectedImage);
                }

                break;
            case 12:
                if(resultCode == RESULT_OK){
                    Uri selectedImage = data.getData();
                    photo2.setImageURI(selectedImage);
                }
                break;
            case 03:
                if(resultCode == RESULT_OK){
                    Uri selectedImage = data.getData();
                    photo3.setImageURI(selectedImage);
                }

                break;
            case 13:
                if(resultCode == RESULT_OK){
                    Uri selectedImage = data.getData();
                    photo3.setImageURI(selectedImage);
                }
                break;
        }
    }


}
