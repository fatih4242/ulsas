package com.tokersoftware.ulsas.modules.main.view;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tokersoftware.ulsas.R;
import com.tokersoftware.ulsas.classes.LocalDataManager;
import com.tokersoftware.ulsas.classes.NetworkManager;
import com.tokersoftware.ulsas.classes.ProgressDialogManager;
import com.tokersoftware.ulsas.interfaces.ResponseI;
import com.tokersoftware.ulsas.model.ErrorMessage;
import com.tokersoftware.ulsas.modules.login.view.LoginActivity;
import com.tokersoftware.ulsas.modules.main.model.Material;
import com.tokersoftware.ulsas.modules.main.model.Report;
import com.tokersoftware.ulsas.modules.main.viewmodel.MainViewModel;

import org.json.JSONArray;
import org.w3c.dom.Text;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //Activity
    Activity activity = MainActivity.this;

    //ViewModel
    MainViewModel viewModel;

    //View
    LinearLayout changingMaterialsLayout;
    ImageView addMaterialLineBtn, signImage;
    RelativeLayout signViewLayout;
    ScrollView raporLayout;
    TextView openSignBtn;
    Button signSaveBtn, signClearBtn, uploadBtn;

    TextInputEditText servicedName, servicedAddress,
            servicedTelFax, reportedMalfunction, serviceRequestNameSurname,
            serviceRequestCapacity, serviceRequestSerialNo, serviceRequestBrand,
            serviceRequestWeighingMachine, serviceRequestLCType, serviceRequestIndicatorType,
            serviceRequestPowerSource, serviceMaintenancePersonnel;

    CheckBox serviceRequestGuarantee, serviceRequestOutOfWarranty,
            stampControlIsTheCalibrationLockClosed, stampControlStickerExists,
            stampControlIsBoxIndicatorStamped, stampControlIsLoadcellConnectorStamped;

    EditText materialName0, materialCount0, materialSerialNo0,
            stampStatusLast, stampStatusFuture, serviceStartDateTime,
            serviceEndDateTime, transactionsMadeInTheService, deliveryName, deliveryDate;

    ImageView beforeImage0, beforeImage1, beforeImage2, beforeImage3,
            afterImage0, afterImage1, afterImage2, afterImage3;



    //View - For Sign Painting
    private float floatStartX = -1, floatStartY = -1,
            floatEndX = -1, floatEndY = -1;

    private Bitmap bitmap;
    private Canvas canvas;
    private Paint paint = new Paint();

    //ProgressDialog Manager
    ProgressDialogManager progressDialogManager;


    //Variables
    int countOfMaterialLines = 0;

    ArrayList<Material> materialList;
    ArrayList<String> imageArrayBefore;
    ArrayList<String> imageArrayAfter;

    int beforeImage0Code = 100, beforeImage1Code = 101, beforeImage2Code = 102, beforeImage3Code = 103,
            afterImage0Code = 104, afterImage1Code = 105, afterImage2Code = 106, afterImage3Code = 107;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }


    private void init(){
        //ProgressDialogManager
        progressDialogManager = new ProgressDialogManager(this);

        //Variables
        materialList = new ArrayList<>();

        imageArrayBefore = new ArrayList<>();
        imageArrayBefore.add(0, "");
        imageArrayBefore.add(1, "");
        imageArrayBefore.add(2, "");
        imageArrayBefore.add(3, "");


        imageArrayAfter = new ArrayList<>();
        imageArrayAfter.add(0, "");
        imageArrayAfter.add(1, "");
        imageArrayAfter.add(2, "");
        imageArrayAfter.add(3, "");


        //ViewModel
        viewModel = new MainViewModel(this);

        //Check for if the email and password is correct
        viewModel.isSigned(new ResponseI() {
            @Override
            public void responseFromDB(ErrorMessage errorMessage) {
                if (errorMessage.getError() == 1){
                    startActivity(new Intent(activity, LoginActivity.class));
                    finish();
                }
            }
        });

        //Views
        changingMaterialsLayout = findViewById(R.id.changing_materials_layout);
        addMaterialLineBtn = findViewById(R.id.addMaterialLineBtn);
        raporLayout = findViewById(R.id.raporLayout);
        signViewLayout = findViewById(R.id.signViewLayout);
        openSignBtn = findViewById(R.id.signBtn);
        signSaveBtn = findViewById(R.id.signSaveBtn);
        signClearBtn = findViewById(R.id.signClearBtn);
        signImage = findViewById(R.id.signImage);
        uploadBtn = findViewById(R.id.uploadBtn);


        //Edit Text
        stampStatusLast = findViewById(R.id.stampStatusLast);
        stampStatusFuture = findViewById(R.id.stampStatusFuture);
        serviceStartDateTime = findViewById(R.id.serviceStartDateTime);
        serviceEndDateTime = findViewById(R.id.serviceEndDateTime);
        transactionsMadeInTheService = findViewById(R.id.transactionsMadeInTheService);
        deliveryName = findViewById(R.id.deliveryName);
        deliveryDate = findViewById(R.id.deliveryDate);


        //TextInput EditText
        servicedName = findViewById(R.id.servicedName);
        servicedAddress = findViewById(R.id.servicedAddress);
        servicedTelFax = findViewById(R.id.servicedTelFax);
        reportedMalfunction = findViewById(R.id.reportedMalfunction);
        serviceRequestNameSurname = findViewById(R.id.serviceRequestNameSurname);
        serviceRequestCapacity = findViewById(R.id.serviceRequestCapacity);
        serviceRequestSerialNo = findViewById(R.id.serviceRequestSerialNo);
        serviceRequestBrand = findViewById(R.id.serviceRequestBrand);
        serviceRequestWeighingMachine = findViewById(R.id.serviceRequestWeighingMachine);
        serviceRequestLCType = findViewById(R.id.serviceRequestLCType);
        serviceRequestIndicatorType = findViewById(R.id.serviceRequestIndicatorType);
        serviceRequestPowerSource = findViewById(R.id.serviceRequestPowerSource);
        serviceMaintenancePersonnel = findViewById(R.id.serviceMaintenancePersonnel);

        //Check Box
        serviceRequestGuarantee = findViewById(R.id.serviceRequestGuarantee);
        serviceRequestOutOfWarranty = findViewById(R.id.serviceRequestOutOfWarranty);
        stampControlIsTheCalibrationLockClosed = findViewById(R.id.stampControlIsTheCalibrationLockClosed);
        stampControlStickerExists = findViewById(R.id.stampControlStickerExists);
        stampControlIsBoxIndicatorStamped = findViewById(R.id.stampControlIsBoxIndicatorStamped);
        stampControlIsLoadcellConnectorStamped = findViewById(R.id.stampControlIsLoadcellConnectorStamped);

        materialName0 = findViewById(R.id.materialName0);
        materialCount0 = findViewById(R.id.materialCount0);
        materialSerialNo0 = findViewById(R.id.materialSerialNo0);

        beforeImage0 = findViewById(R.id.beforeRaporImage0);
        beforeImage1 = findViewById(R.id.beforeRaporImage1);
        beforeImage2 = findViewById(R.id.beforeRaporImage2);
        beforeImage3 = findViewById(R.id.beforeRaporImage3);

        afterImage0 = findViewById(R.id.afterRaporImage0);
        afterImage1 = findViewById(R.id.afterRaporImage1);
        afterImage2 = findViewById(R.id.afterRaporImage2);
        afterImage3 = findViewById(R.id.afterRaporImage3);

        beforeImage0.setOnClickListener(v -> {
            checkForPermission(beforeImage0Code);
        });
        beforeImage1.setOnClickListener(v -> {
            checkForPermission(beforeImage1Code);
        });
        beforeImage2.setOnClickListener(v -> {
            checkForPermission(beforeImage2Code);
        });
        beforeImage3.setOnClickListener(v -> {
            checkForPermission(beforeImage3Code);
        });
        afterImage0.setOnClickListener(v -> {
            checkForPermission(afterImage0Code);
        });
        afterImage1.setOnClickListener(v -> {
            checkForPermission(afterImage1Code);
        });
        afterImage2.setOnClickListener(v -> {
            checkForPermission(afterImage2Code);
        });
        afterImage3.setOnClickListener(v -> {
            checkForPermission(afterImage3Code);
        });

        //Open Sign View For Sign
        openSignBtn.setOnClickListener( v -> {
            signViewLayout.setVisibility(View.VISIBLE);
            raporLayout.setVisibility(View.GONE);
        });

        //Close the sign view
        signSaveBtn.setOnClickListener( v -> {
            if (canvas != null){
                if (canvas.getSaveCount() > 0 ){
                    openSignBtn.setText("İmzayı Düzelt");
                }
            }
            signViewLayout.setVisibility(View.GONE);
            raporLayout.setVisibility(View.VISIBLE);
        });

        signClearBtn.setOnClickListener( v -> {
           canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
        });

        //Adding material Edit Text line MAX Value is 10
        addMaterialLineBtn.setOnClickListener( v -> {
            if (countOfMaterialLines != 9){
                countOfMaterialLines++;
                createChangingMaterialsView();

            } else addMaterialLineBtn.setVisibility(View.GONE);
        });
        
        uploadBtn.setOnClickListener( v -> {
            if (NetworkManager.isConnectedToNetwork(activity)){
                if (checkForEmptyValues()) {
                    uploadReport();
                }
            } else Toast.makeText(activity, "Lütfen İnternet bağlantınızı kontrol ediniz", Toast.LENGTH_SHORT).show();

        });


    }

    private void uploadReport() {
        progressDialogManager.showProgressDialog("Lütfen Bekleyiniz...", "Rapor Yükleniyor");
        materialList.clear();
        for (int i = 0; i <= countOfMaterialLines; i++) {
            String name = "", count = "", serialNo = "";
            EditText editTextName = null, editTextCount = null, editTextSerialNo = null;
            if (i == 0){
                editTextName = materialName0;
                editTextCount = materialCount0;
                editTextSerialNo = materialSerialNo0;
            } else if (i == 1){
                 editTextName = findViewById(R.id.materialName1);
                 editTextCount = findViewById(R.id.materialCount1);
                 editTextSerialNo = findViewById(R.id.materialSerialNo1);
            } else if (i == 2) {
                 editTextName = findViewById(R.id.materialName2);
                 editTextCount = findViewById(R.id.materialCount2);
                 editTextSerialNo = findViewById(R.id.materialSerialNo2);
            } else if (i == 3) {
                 editTextName = findViewById(R.id.materialName3);
                 editTextCount = findViewById(R.id.materialCount3);
                 editTextSerialNo = findViewById(R.id.materialSerialNo3);
            } else if (i == 4) {
                 editTextName = findViewById(R.id.materialName4);
                 editTextCount = findViewById(R.id.materialCount4);
                 editTextSerialNo = findViewById(R.id.materialSerialNo4);
            } else if (i == 5) {
                 editTextName = findViewById(R.id.materialName5);
                 editTextCount = findViewById(R.id.materialCount5);
                 editTextSerialNo = findViewById(R.id.materialSerialNo5);
            } else if (i == 6) {
                 editTextName = findViewById(R.id.materialName6);
                 editTextCount = findViewById(R.id.materialCount6);
                 editTextSerialNo = findViewById(R.id.materialSerialNo6);
            } else if (i == 7) {
                 editTextName = findViewById(R.id.materialName7);
                 editTextCount = findViewById(R.id.materialCount7);
                 editTextSerialNo = findViewById(R.id.materialSerialNo7);
            }else if (i == 8){
                 editTextName = findViewById(R.id.materialName8);
                 editTextCount = findViewById(R.id.materialCount8);
                 editTextSerialNo = findViewById(R.id.materialSerialNo8);
            }else if (i == 9){
                 editTextName = findViewById(R.id.materialName9);
                 editTextCount = findViewById(R.id.materialCount9);
                 editTextSerialNo = findViewById(R.id.materialSerialNo9);
            }

            name = editTextName.getText().toString();
            count = editTextCount.getText().toString();
            serialNo = editTextSerialNo.getText().toString();

            materialList.add(new Material(name, count, serialNo));
        }

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        String encoded = android.util.Base64.encodeToString(byteArray, Base64.DEFAULT);

        String materialArray = new Gson().toJson(materialList);
        String imageBeforeJsonArray = new Gson().toJson(imageArrayBefore);
        String imageAfterJsonArray = new Gson().toJson(imageArrayAfter);

        Report report = new Report(
               servicedName.getText().toString(),
               servicedAddress.getText().toString(),
               servicedTelFax.getText().toString(),
               reportedMalfunction.getText().toString(),
               serviceRequestNameSurname.getText().toString(),
               String.valueOf(serviceRequestGuarantee.isChecked()),
               String.valueOf(serviceRequestOutOfWarranty.isChecked()),
               serviceRequestCapacity.getText().toString(),
               serviceRequestSerialNo.getText().toString(),
               serviceRequestBrand.getText().toString(),
               serviceRequestWeighingMachine.getText().toString(),
               serviceRequestLCType.getText().toString(),
               serviceRequestIndicatorType.getText().toString(),
               serviceRequestPowerSource.getText().toString(),
               String.valueOf(stampControlIsTheCalibrationLockClosed.isChecked()),
               String.valueOf(stampControlStickerExists.isChecked()),
               String.valueOf(stampControlIsBoxIndicatorStamped.isChecked()),
               String.valueOf(stampControlIsLoadcellConnectorStamped.isChecked()),
               stampStatusLast.getText().toString(),
               stampStatusFuture.getText().toString(),
               serviceMaintenancePersonnel.getText().toString(),
               serviceStartDateTime.getText().toString(),
               serviceEndDateTime.getText().toString(),
               transactionsMadeInTheService.getText().toString(),
               materialArray,
               deliveryName.getText().toString(),
               deliveryDate.getText().toString(),
               encoded,
               imageBeforeJsonArray,
               imageAfterJsonArray

        );

        viewModel.uploadReport(report, new ResponseI() {
            @Override
            public void responseFromDB(ErrorMessage errorMessage) {
                progressDialogManager.dismissProgressDialog();

                if(errorMessage.getError() == 0){
                    Toast.makeText(activity, "Rapor Başarıyla Yüklendi", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(activity, "Hata: " + errorMessage.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean checkForEmptyValues(){
        if (canvas == null){
            Toast.makeText(activity, "Lütfen İmza Kısmını Tamamlayınız.", Toast.LENGTH_SHORT).show();
            return false;
        } else if (canvas.getSaveCount() == 0) {
            Toast.makeText(activity, "Lütfen İmza Kısmını Tamamlayınız.", Toast.LENGTH_SHORT).show();
            return false;
        } else if (servicedName.getText().toString().isEmpty()) {
            Toast.makeText(activity, "Lütfen Hizmet Verilen Kurum İsim kısmını tamamlayınız", Toast.LENGTH_SHORT).show();
            return false;

        } else if (servicedAddress.getText().toString().isEmpty()) {
            Toast.makeText(activity, "Lütfen Hizmet Verilen Kurum Adres kısmını tamamlayınız", Toast.LENGTH_SHORT).show();
            return false;

        } else if (reportedMalfunction.getText().toString().isEmpty()){
            Toast.makeText(activity, "Lütfen Bildirilen ve Tesbit Edilen Arıza kısmını tamamlayınız", Toast.LENGTH_SHORT).show();
            return false;

        } else if (serviceRequestCapacity.getText().toString().isEmpty()){
            Toast.makeText(activity, "Lütfen Servis İsteği Alan Kapasite kısmını tamamlayınız", Toast.LENGTH_SHORT).show();
            return false;

        } else if (serviceRequestSerialNo.getText().toString().isEmpty()){
            Toast.makeText(activity, "Lütfen Servis İsteği Alan Seri no kısmını tamamlayınız", Toast.LENGTH_SHORT).show();
        return false;
        } else if (serviceRequestBrand.getText().toString().isEmpty()){
            Toast.makeText(activity, "Lütfen Servis İsteği Alan Marka kısmını tamamlayınız", Toast.LENGTH_SHORT).show();
            return false;

        } else if (serviceRequestWeighingMachine.getText().toString().isEmpty()){
            Toast.makeText(activity, "Lütfen Servis İsteği Alan Tartı Aleti kısmını tamamlayınız", Toast.LENGTH_SHORT).show();
            return false;

        } else if (serviceRequestLCType.getText().toString().isEmpty()){
            Toast.makeText(activity, "Lütfen Servis İsteği Alan LC Tipi kısmını tamamlayınız", Toast.LENGTH_SHORT).show();
            return false;

        } else if (serviceRequestIndicatorType.getText().toString().isEmpty()){
            Toast.makeText(activity, "Lütfen Servis İsteği Alan İndikatör Tipi kısmını tamamlayınız", Toast.LENGTH_SHORT).show();
            return false;

        } else if (serviceRequestPowerSource.getText().toString().isEmpty()){
            Toast.makeText(activity, "Lütfen Servis İsteği Alan Güç Kaynağı kısmını tamamlayınız", Toast.LENGTH_SHORT).show();
            return false;

        } else if (stampStatusLast.getText().toString().isEmpty()){
            Toast.makeText(activity, "Lütfen Damga Durumu Son kısmını tamamlayınız", Toast.LENGTH_SHORT).show();
            return false;

        }  else if (stampStatusFuture.getText().toString().isEmpty()){
            Toast.makeText(activity, "Lütfen Damga Durumu Gelecek kısmını tamamlayınız", Toast.LENGTH_SHORT).show();
            return false;

        } else if (serviceMaintenancePersonnel.getText().toString().isEmpty()){
            Toast.makeText(activity, "Lütfen Servis Bakım Personeli kısmını tamamlayınız", Toast.LENGTH_SHORT).show();
            return false;

        } else if (serviceStartDateTime.getText().toString().isEmpty()){
            Toast.makeText(activity, "Lütfen Servis - Bakım Başlama Tarihi kısmını tamamlayınız", Toast.LENGTH_SHORT).show();
            return false;

        } else if (serviceEndDateTime.getText().toString().isEmpty()){
            Toast.makeText(activity, "Lütfen Servis - Bakım Bitiş Tarihi kısmını tamamlayınız", Toast.LENGTH_SHORT).show();
            return false;

        } else if (transactionsMadeInTheService.getText().toString().isEmpty()){
            Toast.makeText(activity, "Lütfen Servis - Bakım Esnasında Yapılan İşlemler kısmını tamamlayınız", Toast.LENGTH_SHORT).show();
            return false;

        } else if (deliveryName.getText().toString().isEmpty()){
            Toast.makeText(activity, "Lütfen Teslim Kurum Adına kısmını tamamlayınız", Toast.LENGTH_SHORT).show();
            return false;

        } else if (deliveryDate.getText().toString().isEmpty()){
            Toast.makeText(activity, "Lütfen Teslim Tarih kısmını tamamlayınız", Toast.LENGTH_SHORT).show();
            return false;

        } else if(materialName0.getText().toString().isEmpty()
                || materialCount0.getText().toString().isEmpty()
                || materialSerialNo0.getText().toString().isEmpty()){
            Toast.makeText(activity, "Lütfen Mazleme Değerlerini giriniz", Toast.LENGTH_SHORT).show();
            return false;

        }

        return true;
    }

    private void createChangingMaterialsView(){
        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
          LinearLayout.LayoutParams.MATCH_PARENT,
          LinearLayout.LayoutParams.WRAP_CONTENT
        );

        LinearLayout changeMaterialsLayout = new LinearLayout(this);
        changeMaterialsLayout.setLayoutParams(param);
        changeMaterialsLayout.setOrientation(LinearLayout.HORIZONTAL);
        changeMaterialsLayout.setGravity(Gravity.CENTER);



        ViewGroup.LayoutParams editTextParam = new LinearLayout.LayoutParams(
                0,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                1f
        );
        EditText materialNameEditText = new EditText(this);
        materialNameEditText.setLayoutParams(editTextParam);
        materialNameEditText.setTextSize(20);
        materialNameEditText.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        materialNameEditText.setTag("materialName"+countOfMaterialLines);


        EditText materialCountEditText = new EditText(this);
        materialCountEditText.setLayoutParams(editTextParam);
        materialCountEditText.setTextSize(20);
        materialCountEditText.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        materialCountEditText.setTag("materialCount"+countOfMaterialLines);

        ViewGroup.LayoutParams serialNoParam = new LinearLayout.LayoutParams(
                0,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                2f
        );

        EditText serialNoEditText = new EditText(this);
        serialNoEditText.setLayoutParams(serialNoParam);
        serialNoEditText.setTextSize(20);
        serialNoEditText.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        materialNameEditText.setTag("materialSerialNo"+countOfMaterialLines);

        if (countOfMaterialLines == 1){
            materialNameEditText.setId(R.id.materialName1);
            materialCountEditText.setId(R.id.materialCount1);
            serialNoEditText.setId(R.id.materialSerialNo1);
        } else if (countOfMaterialLines == 2) {
            materialNameEditText.setId(R.id.materialName2);
            materialCountEditText.setId(R.id.materialCount2);
            serialNoEditText.setId(R.id.materialSerialNo2);
        } else if (countOfMaterialLines == 3) {
            materialNameEditText.setId(R.id.materialName3);
            materialCountEditText.setId(R.id.materialCount3);
            serialNoEditText.setId(R.id.materialSerialNo3);
        }else if (countOfMaterialLines == 4) {
            materialNameEditText.setId(R.id.materialName4);
            materialCountEditText.setId(R.id.materialCount4);
            serialNoEditText.setId(R.id.materialSerialNo4);
        }else if (countOfMaterialLines == 5) {
            materialNameEditText.setId(R.id.materialName5);
            materialCountEditText.setId(R.id.materialCount5);
            serialNoEditText.setId(R.id.materialSerialNo5);
        }else if (countOfMaterialLines == 6) {
            materialNameEditText.setId(R.id.materialName6);
            materialCountEditText.setId(R.id.materialCount6);
            serialNoEditText.setId(R.id.materialSerialNo6);
        }else if (countOfMaterialLines == 7) {
            materialNameEditText.setId(R.id.materialName7);
            materialCountEditText.setId(R.id.materialCount7);
            serialNoEditText.setId(R.id.materialSerialNo7);
        }else if (countOfMaterialLines == 8) {
            materialNameEditText.setId(R.id.materialName8);
            materialCountEditText.setId(R.id.materialCount8);
            serialNoEditText.setId(R.id.materialSerialNo8);
        }else if (countOfMaterialLines == 9) {
            materialNameEditText.setId(R.id.materialName9);
            materialCountEditText.setId(R.id.materialCount9);
            serialNoEditText.setId(R.id.materialSerialNo9);
        }

        //Add three time
        changeMaterialsLayout.addView(materialNameEditText);
        changeMaterialsLayout.addView(materialCountEditText);
        changeMaterialsLayout.addView(serialNoEditText);

        changingMaterialsLayout.addView(changeMaterialsLayout);
    }

    private void drawPaintSketchImage(){
        if (bitmap == null){
            bitmap = Bitmap.createBitmap(signImage.getWidth(), signImage.getHeight(), Bitmap.Config.ARGB_8888);

            canvas = new Canvas(bitmap);
            paint.setColor(Color.BLUE);
            paint.setAntiAlias(true);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(0);
        }

        canvas.drawLine(
                floatStartX,
                floatStartY - 20,
                floatEndX,
                floatEndY  - 20,
                paint
        );

        signImage.setImageBitmap(bitmap);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (signViewLayout.getVisibility() == View.VISIBLE){
            if (event.getAction() == MotionEvent.ACTION_DOWN){
                floatStartX = event.getX();
                floatStartY = event.getY();
            } else if(event.getAction() == MotionEvent.ACTION_UP){
                floatEndX = event.getX();
                floatEndY = event.getY();

                drawPaintSketchImage();
            } else if (event.getAction() == MotionEvent.ACTION_MOVE){
                floatEndX = event.getX();
                floatEndY = event.getY();

                drawPaintSketchImage();

                floatStartX = event.getX();
                floatStartY = event.getY();
            } else if (event.getAction() == MotionEvent.ACTION_MOVE){
                floatEndX = event.getX();
                floatEndY = event.getY();

                drawPaintSketchImage();
            }
        }

        return super.onTouchEvent(event);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (grantResults.length > 0){
            //Granted
            if (requestCode == beforeImage0Code){
                goToGallery(beforeImage0Code);
            } else if(requestCode == beforeImage1Code){
                goToGallery(beforeImage1Code);
            } else if (requestCode == beforeImage2Code) {
                goToGallery(beforeImage2Code);
            } else if(requestCode == beforeImage3Code){
                goToGallery(beforeImage3Code);
            } else if(requestCode == afterImage0Code){
                goToGallery(afterImage0Code);
            } else if(requestCode == afterImage1Code) {
                goToGallery(afterImage1Code);
            } else if(requestCode == afterImage2Code) {
                goToGallery(afterImage2Code);
            } else if (requestCode == afterImage3Code) {
                goToGallery(afterImage3Code);
            }
            Toast.makeText(activity, "İzin İçi teşekkür ederiz", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(activity, "Uygulamayı Kullanabilmek İçin Lütfen Galeri Erişimine İzin verin.", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && data != null){
            final Uri imageUri = data.getData();
            final InputStream imageStream;
            try {
                imageStream = getContentResolver().openInputStream(imageUri);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            final Bitmap selectedImage2 = BitmapFactory.decodeStream(imageStream);
            String encodedImage = encodeImage(selectedImage2);


            if (requestCode == beforeImage0Code){
                beforeImage0.setImageURI(imageUri);
                imageArrayBefore.set(0, encodedImage);

            } else if(requestCode == beforeImage1Code){
                beforeImage1.setImageURI(imageUri);
                imageArrayBefore.set(1, encodedImage);

            } else if (requestCode == beforeImage2Code) {
                beforeImage2.setImageURI(imageUri);
                imageArrayBefore.set(2, encodedImage);

            } else if(requestCode == beforeImage3Code){
                beforeImage3.setImageURI(imageUri);
                imageArrayBefore.set(3, encodedImage);

            } else if(requestCode == afterImage0Code){
                afterImage0.setImageURI(imageUri);
                imageArrayAfter.set(0, encodedImage);

            } else if(requestCode == afterImage1Code) {
                afterImage1.setImageURI(imageUri);
                imageArrayAfter.set(1, encodedImage);

            } else if(requestCode == afterImage2Code) {
                afterImage2.setImageURI(imageUri);
                imageArrayAfter.set(2, encodedImage);

            } else if (requestCode == afterImage3Code) {
                afterImage3.setImageURI(imageUri);
                imageArrayAfter.set(3, encodedImage);

            }
        }
    }

    private String encodeImage(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG,100,baos);
        byte[] b = baos.toByteArray();
        String encImage = Base64.encodeToString(b, Base64.DEFAULT);

        return encImage;
    }

    private void checkForPermission(int index){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(
                    this,
                    new String[]{ Manifest.permission.READ_EXTERNAL_STORAGE},
                    index
            );
        } else {
            goToGallery(index);
        }
    }

    private void goToGallery(int STORAGE_CODE){
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, STORAGE_CODE);
    }
}