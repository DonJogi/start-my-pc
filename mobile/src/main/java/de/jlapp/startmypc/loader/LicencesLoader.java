package de.jlapp.startmypc.loader;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class LicencesLoader extends AsyncTaskLoader<String> {
    private String licences;

    public LicencesLoader(Context context) {
        super(context);
    }

    @Override
    public String loadInBackground() {
        if (!TextUtils.isEmpty(licences)) {
            return licences;
        }

        BufferedReader bufferedReader = null;
        try {
            //StringBuilder sb = new StringBuilder();

            InputStream licencesStream = getContext().getAssets().open("Licenses.txt");

            //bufferedReader = new BufferedReader(new InputStreamReader(licencesStream));

            //String line;
            //while ((line = bufferedReader.readLine()) != null) {
            //    sb.append(line);
            //}

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int read = 0;
            while ((read = licencesStream.read(buffer, 0, buffer.length)) != -1) {
                baos.write(buffer, 0, read);
            }
            baos.flush();
            licences = new String(baos.toByteArray(), "UTF-8");

            //licences = sb.toString();
        } catch (IOException e) {
            // Irrelevant
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (IOException e) {
                // Irrelevant
            }
        }

        return licences;
    }

    private String extract(InputStream inputStream) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int read = 0;
        while ((read = inputStream.read(buffer, 0, buffer.length)) != -1) {
            baos.write(buffer, 0, read);
        }
        baos.flush();
        return  new String(baos.toByteArray(), "UTF-8");
    }
}
