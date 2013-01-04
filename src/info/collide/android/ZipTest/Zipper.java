package info.collide.android.ZipTest;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import org.zeroturnaround.zip.ZipUtil;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Zipper extends Activity {

    private View button;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                File dir = Environment.getExternalStorageDirectory();
                File testfile = new File(dir, "test.txt");
                File zipfile = new File(dir, "test.zip");
                File outputdir = new File(dir.getAbsolutePath() + "/test/");
                if (!outputdir.exists()) {
                    outputdir.mkdirs();
                }
                try {
                    FileWriter writer = new FileWriter(testfile);
                    writer.append("This\n");
                    writer.append("is\n");
                    writer.append("a\n");
                    writer.append("zt-zip test\n");
                    writer.append("on\n");
                    writer.append("Android!\n");
                    writer.flush();
                    writer.close();

                    // zt-zip "magic" to pack a file
                    ZipUtil.packEntry(testfile, zipfile);
                    // zt-zip "magic" to unpack a file
                    ZipUtil.unpack(zipfile, outputdir);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
    }
}
