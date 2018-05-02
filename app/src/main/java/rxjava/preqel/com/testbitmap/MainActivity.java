package rxjava.preqel.com.testbitmap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

//一个加载大图的acitivity
public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        String path = "/storage/emulated/0/Download/fc1f4134970a304ec0fb5afcd3c8a786c8175cdf.jpg";
        Bitmap ressult = decodeBitmapForSize(path, 200, 200);
        ImageView.ScaleType scaleType = ImageView.ScaleType.CENTER_CROP;
        imageView.setScaleType(scaleType);
        BitmapDrawable bitmapDrawable = new BitmapDrawable(ressult);
        int aftercount = bitmapDrawable.getBitmap().getByteCount();
        Log.d("TAG","after deal the bytecount:"+ aftercount);
        imageView.setBackground(bitmapDrawable);
    }

    private void initView() {
        this.context = MainActivity.this;
        this.imageView = findViewById(R.id.imageview);
    }


    public   Bitmap decodeBitmapForSize(String path, int width, int height) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        if (width != 0 && height != 0) {
            // decode with inJustDecodeBounds=true to check size
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeResource(context.getResources(),R.drawable.mmm,options);

           // BitmapFactory.decodeFile(path, options);
            // calculate inSampleSize according to the requested size
           options.inSampleSize = calculateInSampleSize(options, width, height);
//           options.in
            Log.d("TAG","after insamplesize:"+ options.inSampleSize);
            options.inJustDecodeBounds = false;
        }
        // decode bitmap with the calculated inSampleSize
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        options.inPurgeable = true;
        options.inInputShareable = true;
       // return BitmapFactory.decodeFile(path, options);
        return BitmapFactory.decodeResource(context.getResources(),R.drawable.bigm,options);
    }

    private static int calculateInSampleSize(BitmapFactory.Options options,
                                             int reqWidth, int reqHeight) {
        // raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int initSize = 1;
        if (height > reqHeight || width > reqWidth) {
            if (width > height) {
                initSize = Math.round((float) height / (float) reqHeight);
            } else {
                initSize = Math.round((float) width / (float) reqWidth);
            }
        }
        return initSize;
    }


}