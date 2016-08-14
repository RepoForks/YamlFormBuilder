package com.alorma.yamlreader;

import com.alorma.yaml.formbuilder.YamlFormFragment;
import java.io.InputStream;

public class SampleFormFragment extends YamlFormFragment {
  @Override
  protected InputStream getFormInputStream() {
    return getResources().openRawResource(R.raw.form);
  }
}
