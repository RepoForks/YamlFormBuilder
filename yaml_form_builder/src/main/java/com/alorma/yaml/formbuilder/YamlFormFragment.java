package com.alorma.yaml.formbuilder;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.io.InputStream;

public abstract class YamlFormFragment extends Fragment {

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.form_layout, null, false);
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.form_recyler);
    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    YamlFormAdapter adapter = new YamlFormAdapter(getLayoutInflater(getArguments()));
    recyclerView.setAdapter(adapter);

    YamlForm yamlForm = new YamlFormFactory().read(getFormInputStream());
    adapter.addAll(yamlForm.yamlFormObjects);
  }

  protected abstract InputStream getFormInputStream();
}
