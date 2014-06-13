package de.vogella.android.temperature.camera.app;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class MainActivity extends Activity {

  File mediaFile; // 保存先のパス
  File mediaStorageDir =  new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "Camera");
  File[] mediaStorageFiles;
  List<String> thumbnailFiles = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);

      String photoPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getPath() + File.separator + "Camera";
      mediaStorageFiles = new File(photoPath).listFiles();

//      ArrayList<String> list = new ArrayList<String>();
//
//      mediaStorageFiles = new File(mediaStorageDir.getPath()).listFiles();
//
//      for(int i = 0; i < mediaStorageFiles.length; i++){
//        if(mediaStorageFiles[i].isFile() && mediaStorageFiles[i].getName().endsWith(".mp3")){
//          thumbnailFiles.add(mediaStorageFiles[i].getName());
//        }
//      }
//
//      ArrayAdapter<String> adapter = new ArrayAdapter<String>(
//              getApplicationContext(), android.R.layout.simple_list_item_1, thumbnailFiles);
//
//      gridView = (GridView) findViewById(R.id.gridView);
//      gridView.setAdapter(new myAdapter());
//
//      GridView gridView = (GridView) findViewById(R.id.gridView);
//      gridView.setAdapter(adapter);

    }

  // buttonクリックでカメラを起動（intent）
  public void onClick(View view) {
    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

    if (!mediaStorageDir.exists()) {
      if (!mediaStorageDir.mkdir()) {
        Log.d("Camera", "フォルダ作るの失敗したわ");
      }
    }

    // file名を日付から生成して、保存先をアプリの保存領域にセットする
    String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
    mediaFile = new File(mediaStorageDir.getPath() + File.separator + "IMG_" + timeStamp +  ".jpg");
    intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(mediaFile));

    startActivityForResult(intent, 1);
  }

  // カメラを呼び出して、撮影を終えたら呼び出し元のアクティビティに戻りonActivityResultメソッドが呼ばれる
  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    if (requestCode == 1) {

    }
  }

  @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
