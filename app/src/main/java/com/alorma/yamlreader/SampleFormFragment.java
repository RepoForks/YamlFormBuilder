package com.alorma.yamlreader;

import android.support.v4.util.Pair;
import com.alorma.yaml.formbuilder.YamlFormFragment;
import java.io.InputStream;
import java.util.List;

public class SampleFormFragment extends YamlFormFragment {
  @Override
  protected void formSubmitted(List<Pair<String, Object>> items) {

  }

  @Override
  protected InputStream getFormInputStream() {
    return getResources().openRawResource(R.raw.form);
  }
}
