package com.hababk.delivery.activity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import com.hababk.delivery.R;
import com.hababk.delivery.utils.util.Logger;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddItemActivity extends AppCompatActivity {
    @BindView(R.id.add_item_camera_iv)
    ImageView mItemImageView;
    @BindView(R.id.add_item_spinner_tv)
    Spinner mItemTypeSpinner;
    private ArrayAdapter<String> mItemTypeAdapter;
    private static final int CAMERA_INTENT_CODE = 101;
    private String mCurrentPhotoPath = "";
    private String[] itemcat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_left_white);
            actionBar.setTitle(R.string.add_item);
        }
        ButterKnife.bind(this);
        init();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void init() {
        initVariables();
        setSpinnerAdapter();
    }

    private void initVariables() {
        itemcat = getResources().getStringArray(R.array.item_type);
        mItemImageView.setColorFilter(getResources().getColor(R.color.LightGray));
    }

    private void setSpinnerAdapter() {
        mItemTypeAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item) {
            @Override
            public boolean isEnabled(int position) {
                return position != 0 && super.isEnabled(position);
            }
        };
        mItemTypeAdapter.clear();
        mItemTypeAdapter.addAll(itemcat);
        mItemTypeSpinner.setAdapter(mItemTypeAdapter);
    }

    @OnClick({R.id.add_item_camera_iv})
    public void onClickCameraIv() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File photoFile = null;
        /*File photoFile=null;
        try {
            photoFile = createImageFile();
        }catch (IOException ex) {
            Logger.LogDebug("",ex.getMessage());
        }
        *///if(photoFile!=null) {
        //Uri photoUri= FileProvider.getUriForFile(this,"com.example.android.fileprovider",photoFile);
        try {
            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(createImageFile()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //}
        Intent chooserIntent = Intent.createChooser(galleryIntent, getString(R.string.select_image));
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[]{cameraIntent});
        startActivityForResult(chooserIntent, CAMERA_INTENT_CODE);
    }

    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(imageFileName, ".jpg", storageDir);
        String currentPhotoPath = "file:" + image.getAbsolutePath();
        return image;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == CAMERA_INTENT_CODE) {
            if (data.getData() != null) {
                Uri uri = data.getData();
                mCurrentPhotoPath = getRealPathFromURI(uri);
                Logger.LogDebug("Hello gallery", mCurrentPhotoPath);
                if (!mCurrentPhotoPath.isEmpty()) {
                    Bitmap bitmap = BitmapFactory.decodeFile(mCurrentPhotoPath);
                    mItemImageView.setImageBitmap(bitmap);
                    mItemImageView.setColorFilter(0);
                    mItemImageView.setPadding(0, 0, 0, 0);
                }
            } else {
                Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                Uri uri = getImageUri(this, bitmap);
                File finalFile = new File(getRealPathFromURI(uri));
                mCurrentPhotoPath = finalFile.getPath();
                Logger.LogDebug("Hello Camera", mCurrentPhotoPath);
                if (!mCurrentPhotoPath.isEmpty()) {
                    Bitmap bitmap2 = BitmapFactory.decodeFile(mCurrentPhotoPath);
                    mItemImageView.setImageBitmap(bitmap2);
                    mItemImageView.setColorFilter(0);
                    mItemImageView.setPadding(0, 0, 0, 0);
                }
            }
        }
    }

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    public String getRealPathFromURI(Uri uri) {
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
        return cursor.getString(idx);
    }

}
